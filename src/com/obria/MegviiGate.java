package com.obria;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
开闸
 */
public class MegviiGate {
    private final int PORT = 5000;
    private final String COMMAND_OPEN1 = "on1:01";
    private final String COMMAND_OPEN2 = "on2:01";
    private final String COMMAND_CLOSE = "off1";

    private String gateIp = "";
    private DatagramSocket socket = null;

    public MegviiGate(String gateIp) {
        this.gateIp = gateIp;

        try {
            socket = new DatagramSocket();
        } catch (Exception ex) {

        }
    }

    public void Open() {
        byte[] buffer = GetOpenPackage(COMMAND_OPEN1);
        Send(buffer);
    }

    private void Send(byte[] buffer) {
        try {
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
            packet.setAddress(InetAddress.getByName(this.gateIp));
            packet.setPort(PORT);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] GetOpenPackage(String command) {
        try {
            byte[] buffer = command.getBytes("UTF-8");
            return buffer;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void Dispose() {
        if (socket != null) {
            socket.close();
        }
    }
}