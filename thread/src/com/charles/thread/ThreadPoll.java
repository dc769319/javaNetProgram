package com.charles.thread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 主线程轮询子线程，等待处理结果
 * @author Charles
 * @version 1.0
 */
public class ThreadPoll extends Thread {

    private String result;

    private String filename;

    public ThreadPoll(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(this.filename);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            DigestInputStream dis = new DigestInputStream(in, md);
            while (-1 != dis.read()) ;
            dis.close();
            byte[] digest = md.digest();

            StringBuilder strBuild = new StringBuilder(this.filename);
            strBuild.append(":");
            //转换为16进制字符串
            strBuild.append(DatatypeConverter.printHexBinary(digest));
            this.result = strBuild.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getResult() {
        return this.result;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoll[] tp = new ThreadPoll[args.length];

        for (int i = 0; i < args.length; i++) {
            tp[i] = new ThreadPoll(args[i]);
            tp[i].start();
        }
        //轮询方式，等待子线程返回结果
        for (int i = 0; i < args.length; i++) {
            while (true) {
                String result = tp[i].getResult();
                if (result != null) {
                    System.out.println(result);
                    break;
                }
                Thread.sleep(1000);
            }
        }
    }
}
