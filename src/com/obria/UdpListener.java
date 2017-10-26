package com.obria;

public interface UdpListener {

    void OnGetCode(String code, String ipAddress) throws InterruptedException;
}
