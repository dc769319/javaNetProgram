package com.charles.thread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Charles
 * @version 1.0
 */
public class DigestThreadCallback implements Runnable {

    private String filename;

    private DigestCallbackInterface callback;

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(this.filename);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            DigestInputStream dis = new DigestInputStream(in, md);
            while (-1 != dis.read()) ;
            String digest = DatatypeConverter.printHexBinary(md.digest());
            //通过调用引用的方法，实现回调
            this.callback.printCallback(this.filename, digest);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 注入文件名、主线程类的引用
     * @param filename 文件名
     * @param callback 主线程类的引用
     */
    DigestThreadCallback(String filename, DigestCallbackInterface callback) {
        this.filename = filename;
        this.callback = callback;
    }
}
