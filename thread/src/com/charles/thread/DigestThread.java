package com.charles.thread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Charles
 * @version 1.0
 */
public class DigestThread extends Thread {

    private String filename;

    public static void main(String[] args) {

        for (String filename : args) {
            DigestThread dt = new DigestThread(filename);
            dt.start();
        }
    }

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(this.filename);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            DigestInputStream dis = new DigestInputStream(in, md);
            while (-1 != dis.read());
            dis.close();
            byte[] digest = md.digest();

            StringBuilder strBuild = new StringBuilder(this.filename);
            strBuild.append(":");
            //转换为16进制字符串
            strBuild.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(strBuild);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }
}
