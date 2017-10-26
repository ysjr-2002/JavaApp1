package com.obria;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {


    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // write your code here
        logger.info("系统启动");
        logger.info("请刷二维码");


        Config.getInstance().Read();

        UdpComServer udpComServer = new UdpComServer(Config.getInstance().getPort());
        udpComServer.start(new UdpListener() {
            @Override
            public void OnGetCode(String barcode, String readerIp) throws InterruptedException {

                Channel channel = Config.getInstance().getChannel(readerIp);
                if (channel == null) {
                    logger.info("数据来源非法");
                    return;
                }

                barcode = barcode.trim();
                logger.info("二维码=" + barcode);
                logger.info("读卡器=" + readerIp);
                new HttpUtil().Post(barcode, new HttpCallbackListener() {
                    @Override
                    public void onGetResponse(String response) {

                        JSONObject object = JSON.parseObject(response);
                        int status = object.getInteger("status");
                        String info = object.getString("info");
                        Object data = object.get("data");

                        MegviiGate gate = new MegviiGate(channel.getGateIp());
                        gate.Open();
                        gate.Dispose();

                        logger.info("开闸=" + channel.getGateIp());
                    }

                    @Override
                    public void onException(Exception ex) {

                        System.out.println(ex.getMessage());
                    }
                });
            }
        });

    }
}
