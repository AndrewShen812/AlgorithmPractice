package com.sy.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {
    public static void main(String[] args) {
        SendTask sendTask = new SendTask("Hello from SendTask!");
        RcvTask rcvTask = new RcvTask(sendTask);
        new Thread(rcvTask).start();
        new Thread(sendTask).start();
    }

    private static class SendTask implements Runnable {

        PipedOutputStream pos;
        private String toBeSent;

        private SendTask(String toBeSent) {
            pos = new PipedOutputStream();
            this.toBeSent = toBeSent;
        }

        @Override
        public void run() {
            if (toBeSent != null) {
                try {
                    pos.write(toBeSent.getBytes());
                    pos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class RcvTask implements Runnable {
        private PipedInputStream pis;
        public RcvTask(SendTask sendTask) {
            pis = new PipedInputStream();
            try {
                pis.connect(sendTask.pos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int readCnt = 0;

            try {
                while ((readCnt = pis.read(buffer, 0, buffer.length)) != -1) {
                    String rcvData = new String(buffer, 0, readCnt);
                    System.out.println("received: " + rcvData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
