package com.obria;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    private static final String privateKey = "H7~c6aWd^{p/X3fq";

    public void Post(String code, HttpCallbackListener listener) {

        String sign = privateKey + code;
        sign = MD5Util.getMD5(sign);

        String postData = "code=" + code + "&sign=" + sign;

        HttpURLConnection connection;

        try {
            URL url = new URL(Config.getInstance().getServer());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(10 * 1000);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();

            if (listener != null) {
                listener.onGetResponse(sb.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            if (listener != null) {
                listener.onException(ex);
            }
        }
    }
}
