package com.obria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Config {

    private Config() {

    }

    static Config config;

    public static Config getInstance() {

        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public void Read() {

        Properties properties = new Properties();
        InputStream inputStream = Config.class.getResourceAsStream("/my.properties");

        try {
            properties.load(inputStream);

            String auto = properties.getProperty("auto");
            String port = properties.getProperty("udpPort");
            String server = properties.getProperty("server");

            setAuto(Boolean.parseBoolean(auto));
            setPort(Integer.parseInt(port));
            setServer(server);

            List.add(Channel.parseChannel(properties.getProperty("chann1_in")));
            List.add(Channel.parseChannel(properties.getProperty("chann1_out")));

            List.add(Channel.parseChannel(properties.getProperty("chann2_in")));
            List.add(Channel.parseChannel(properties.getProperty("chann2_out")));

            List.add(Channel.parseChannel(properties.getProperty("chann3_in")));
            List.add(Channel.parseChannel(properties.getProperty("chann3_out")));

            List.add(Channel.parseChannel(properties.getProperty("chann4_in")));
            List.add(Channel.parseChannel(properties.getProperty("chann4_out")));

            List.add(Channel.parseChannel(properties.getProperty("chann5_in")));
            List.add(Channel.parseChannel(properties.getProperty("chann5_out")));

            List.add(Channel.parseChannel(properties.getProperty("chann6_in")));
            List.add(Channel.parseChannel(properties.getProperty("chann6_out")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Channel getChannel(String readerIp) {
        Channel channel = null;
        for (Integer i = 0; i < List.size(); i++) {
            Channel temp = List.get(i);
            String shit = temp.getReaderIp();
            if (shit.equals(readerIp)) {
                channel = temp;
                break;
            }
        }
        return channel;
    }

    private boolean auto;
    private int port;
    private String server;

    public boolean getAuto() {
        return auto;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setServer(String server) {
        this.server = server;
    }


    private ArrayList<Channel> List = new ArrayList<Channel>();
}
