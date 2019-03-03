package com.charles.joinThread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  线程
 * @author Charles
 * @version 1.0
 */
public class DigestThread extends Thread {
    private String filename;

    private String digest;

    DigestThread(String filename) {
        this.filename = filename;
    }

    public String getDigest() {
        return this.digest;
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
            //转换为16进制字符串。线程执行完将结果赋给属性
            this.digest = DatatypeConverter.printHexBinary(digest);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }
}
