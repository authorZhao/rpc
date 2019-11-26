package com.git.mq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.jms.*;

public class MqListener implements MessageListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message m) {
        System.out.println(m.toString());
        try {
            if (m instanceof TextMessage) { //接收文本消息
                TextMessage message = (TextMessage) m;
                System.out.println("文本消息:" + message.getText());
            } else if (m instanceof MapMessage) { //接收键值对消息
                MapMessage message = (MapMessage) m;
//                System.out.println(message.getLong("age"));
//                System.out.println(message.getDouble("sarray"));
//                System.out.println(message.getString("username"));
                System.out.println("键值对消息 ");
            } else if (m instanceof StreamMessage) { //接收流消息
                StreamMessage message = (StreamMessage) m;
                System.out.println("流消息:" + message.readString());
                System.out.println("流消息:" + message.readLong());
            } else if (m instanceof BytesMessage) { //接收字节消息
                byte[] b = new byte[1024];
                int len = -1;
                BytesMessage message = (BytesMessage) m;
                while ((len = message.readBytes(b)) != -1) {
                    System.out.println("字节消息:" + new String(b, 0, len));
                    String jsonstr = new String(b, 0, len);
                    String s = objectMapper.writeValueAsString(jsonstr);

                    System.out.println("接收成功");
                }
            } else if (m instanceof ObjectMessage) { //接收对象消息
                ObjectMessage message = (ObjectMessage) m;
//                User user = (User)message.getObject();
                System.out.println("对象消息");
            } else {
                System.out.println(m);
            }
        } catch (JMSException | JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}