package com.mycompany.baitapnhom1.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkUtil {
    private static String getIPv4Address() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp())
                    continue;
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && iface.getDisplayName().contains("Wireless-AC")) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (
                SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static String getUserURI(){
        return "http://" + getIPv4Address() + ":8088/user/ct06";
    }
}
