package vn.kms.phucvo.hazelcast_demo.infrastructure;

import java.io.Serializable;

public class EchoTask implements Serializable, Runnable {
    private final String msg;

    public EchoTask(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println("echo:" + msg);
    }
}
