package com.core;

import com.esb.JMSToolsOperate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoreServer {

    public static void main(String[] args) {
        new CoreServer();
    }

    public CoreServer() {
        System.out.println("CORE端已经启动！");
        JMSToolsOperate toolsOperate = new JMSToolsOperate();
        ExecutorService executor = Executors.newCachedThreadPool();//创建线程池

        while (true) {
            //从消息队列中取出消息
            String message = toolsOperate.receiveMsg("E2C");
            System.out.println("CORE端已经成功的从消息队列E2C中取出消息 " + message);

            //为每一个消息开启一个线程
            HandleTask task = new HandleTask(message);
            executor.execute(task);
        }
    }

    //处理消息的内部类
    class HandleTask implements Runnable {

        private String message = null;
        JMSToolsOperate toolsOperate = new JMSToolsOperate();

        public HandleTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            //对消息进行处理
            String result = new ServerController(message).doAction();
            System.out.println("CORE端已经成功的处理消息！");

            //将处理结果放入消息队列
            toolsOperate.sendMsg(result, "C2E");
            System.out.println("CORE端已经成功的向消息队列C2E中发送处理后的信息 " + result);
        }
    }
}
