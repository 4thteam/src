package com.esb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by 余文彪 on 2017/7/16.
 */
public class JMSToolsOperate {
    // 定义并创建链接工厂
    private ConnectionFactory factory = new ActiveMQConnectionFactory(
            "tcp://localhost:61616");
    // 定义到消息队列的链接
    private Connection connection;
    // 定义本次链接的会话
    private Session session;

    public void sendMsg(String message, String queueName) {
        try {
            // 创建连接
            connection = factory.createConnection();
            // 打开连接
            connection.start();
            // 创建本次链接的会话
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列，如果本队列存在则使用之，否则创建之
            Destination queue = session.createQueue(queueName);
            // 创建一个消息
            Message msg = session.createTextMessage(message);
            // 创建一个生产者,即消息的发送者，并指定他要发送消息的目的地（即消息队列）
            MessageProducer producer = session.createProducer(queue);
            // 发送消息
            producer.send(msg);
            // 释放资源
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public String receiveMsg(String queueName) {
        String message = null;
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination queue = session.createQueue(queueName);
            // 创建一个消息的消费者，即消息的接受者，并指定他的接受目的地（即从哪个消息队列里取消息）
            MessageConsumer consumer = session.createConsumer(queue);
            // 接受消息
            message = ((TextMessage) consumer.receive()).getText();
            // 释放资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
