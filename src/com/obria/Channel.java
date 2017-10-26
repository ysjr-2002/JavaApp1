package com.obria;

public class Channel {

    private String readerIp;
    private String gateIp;

    public String getGateIp() {
        return gateIp;
    }

    public String getReaderIp() {
        return readerIp;
    }

    public void setGateIp(String gateIp) {
        this.gateIp = gateIp;
    }

    public void setReaderIp(String readerIp) {
        this.readerIp = readerIp;
    }

    public static Channel parseChannel(String data) {

        //|是转义字符
        String[] array = data.split("\\|");
        Channel channel = new Channel();
        channel.setReaderIp(array[0]);
        channel.setGateIp(array[1]);
        return channel;
    }
}
