package com.sjtu.trade.serviceimpl;

import com.sjtu.trade.config.ApplicationHelper;
import com.sjtu.trade.dao.OrderBlotterDao;
import com.sjtu.trade.entity.OrderBlotter;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;

@Component
@ClientEndpoint
public class WebsocketClientService {

    // 服务端的IP和端口号
    private static final String URL = "localhost:8090";

    private OrderBlotterDao orderBlotterDao = (OrderBlotterDao)ApplicationHelper.getBean("orderBlotterDao");
    private OrderBookService orderBookService = (OrderBookService) ApplicationHelper.getBean("orderBookService");
    private Session session;

    @PostConstruct
    void init() {
        try {
            // 本机地址
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String wsUrl = "ws://" + URL + "/websocket/" + hostAddress;
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

            OrderBlotter result =  (OrderBlotter) JSONObject.toBean(object2, OrderBlotter.class);

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

}
