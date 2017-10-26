package com.obria;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpComServer {

    private int port = 0;
    DatagramSocket udp = null;
    Thread thread = null;
    UdpListener lisenter;

    public UdpComServer(int port) {
        this.port = port;
    }

    public void start(UdpListener lisenter) {
        try {
            this.lisenter = lisenter;
            udp = new DatagramSocket(port);
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        receive();
                    }
                }
            });
            thread.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void receive() {

        try {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            udp.receive(packet);


            byte[] data = packet.getData();

            final String barcode = new String(data, "UTF-8");
            InetAddress address = packet.getAddress();

            String ip = address.toString();
            final String myIp = ip.substring(ip.indexOf("/") + 1);

            Thread workerThrea = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (lisenter != null) {
                            lisenter.OnGetCode(barcode, myIp);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            workerThrea.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void test() {


    }

}
