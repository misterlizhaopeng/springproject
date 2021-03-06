package com.lp;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class MyZkWatcher implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        // zookeeper配置数据存放路径
        String path = "/usernamex";
        // 连接zookeeper并且注册一个默认的监听器
        zk = new ZooKeeper("192.168.25.141:2181", 5000, //
                new MyZkWatcher());
        // 等待zk连接成功的通知
        connectedSemaphore.await();

        // 获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zk.getData (path, true, stat) ));

        zk.getData("/go", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        }, null);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {//zk连接成功通知事件
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                } catch (Exception e) {
                }

            }
        }
    }
}
