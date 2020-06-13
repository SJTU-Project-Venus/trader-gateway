package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.config.ApplicationHelper;
import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.OrderBlotter;
import com.sjtu.trade.entity.OrderBlotterEntity;
import com.sjtu.trade.utils.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@Component
@ClientEndpoint
public class WebsocketClientServiceM {
    // 服务端的IP和端口号
    @Value("${traderCompany}")
    private String traderCompany;
    @Value("${brokerWsM}")
    private String URL;

    private OrderBlotterDao orderBlotterDao = (OrderBlotterDao) ApplicationHelper.getBean("orderBlotterDao");
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
            session = container.connectToServer(WebsocketClientServiceM.class, uri);
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
        System.out.println("完成连接");
        this.session = session;
    }
    public void heartFelt(){
        if(!session.isOpen()){
            System.out.println("断开连接了");
            try {
                // 本机地址
                //String hostAddress = InetAddress.getLocalHost().getHostAddress();
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                String wsUrl = "ws://" + URL + "/websocket/" + traderCompany;
                URI uri = URI.create(wsUrl);
                session = container.connectToServer(WebsocketClientServiceM.class, uri);
            } catch (DeploymentException | IOException e) {
                e.printStackTrace();
            }
        }
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
            System.out.println(order);
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
        if(cacheSize>=120){
            redisUtil.lSet(orderBlotter.getFutureName(),com.alibaba.fastjson.JSONObject.toJSON(orderBlotter));
            redisUtil.deleteValues(orderBlotter.getFutureName(),0,80);
        }
        else{
            redisUtil.lSet(orderBlotter.getFutureName(),com.alibaba.fastjson.JSONObject.toJSON(orderBlotter));
        }
    }
}
