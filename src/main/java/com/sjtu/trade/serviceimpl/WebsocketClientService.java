package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.config.ApplicationHelper;
import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.entity.OrderBlotterEntity;
import com.sjtu.trade.utils.RedisUtil;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.List;

@Component
@ClientEndpoint
public class WebsocketClientService {

    // 服务端的IP和端口号
    @Value("${traderCompany}")
    private String traderCompany;
    private static final String URL = "localhost:8090";

    private OrderBlotterDao orderBlotterDao = (OrderBlotterDao)ApplicationHelper.getBean("orderBlotterDao");
    private OrderBookService orderBookService = (OrderBookService) ApplicationHelper.getBean("orderBookService");
    private RedisUtil redisUtil = (RedisUtil)ApplicationHelper.getBean("redisUtil");
    private Session session;

    @PostConstruct
    void init() {
        try {
            // 本机地址
            //String hostAddress = InetAddress.getLocalHost().getHostAddress();
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String wsUrl = "ws://" + URL + "/websocket/" + traderCompany;
            URI uri = URI.create(wsUrl);
            session = container.connectToServer(WebsocketClientService.class, uri);
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Hello World!");
        this.session = session;
    }

    /**
     * 接收消息
     * @param text
     */
    @OnMessage
    public void onMessage(String text) {
        //System.out.println(text);
        JSONObject object = JSONObject.fromObject(text);
        String futureName = (String)object.get("futureName");
        if(futureName!=null) {
            orderBookService.sendMessages(text);
        }
        else{
            JSONObject object1 = JSONObject.fromObject(text);
            String order = (String)object.get("order");

            JSONObject object2 = JSONObject.fromObject(order);

            OrderBlotterEntity temp =  (OrderBlotterEntity) JSONObject.toBean(object2, OrderBlotterEntity.class);

            OrderBlotter result = new OrderBlotter(temp,"M");
            cacheManager(result);

            orderBlotterDao.Create(result);
        }
    }

    /**
     * 异常处理
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClosing() throws IOException {
        session.close();
    }

    private void cacheManager(OrderBlotter orderBlotter){
        System.out.println(orderBlotter.getFutureName());
        long cacheSize =  redisUtil.lGetListSize(orderBlotter.getFutureName());
        System.out.println("cacheSize"+cacheSize);
        if(cacheSize>120){
            List<Object> resultlist =  redisUtil.lGet(orderBlotter.getFutureName(),0,-1);
            resultlist.add(com.alibaba.fastjson.JSONObject.toJSON(orderBlotter));
            resultlist.subList(80,121);
            redisUtil.lSet(orderBlotter.getFutureName(),resultlist);
        }
        else{
            List<Object> resultlist =  redisUtil.lGet(orderBlotter.getFutureName(),0,-1);
            resultlist.add(com.alibaba.fastjson.JSONObject.toJSON(orderBlotter));
            redisUtil.lSet(orderBlotter.getFutureName(),resultlist);
        }
    }
}
