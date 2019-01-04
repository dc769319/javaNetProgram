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

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(this.filename);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            DigestInputStream dis = new DigestInputStream(in, md);
            while (-1 != dis.read()) ;
            String digest = DatatypeConverter.printHexBinary(md.digest());
            DigestCallbackInterface.printCallback(this.filename, digest);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

    public DigestThreadCallback(String filename) {
        this.filename = filename;
    }
}
