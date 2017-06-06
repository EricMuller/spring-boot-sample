package com.emu.apps.sample.client;


import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class Test {
    public static void main(String[] args)
            throws Exception {
        String httpsURL = "https://192.168.1.100/test";
        URL myurl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
//        HttpsURLConnection.setDefaultHostnameVerifier(
//                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }

        in.close();
    }
}


