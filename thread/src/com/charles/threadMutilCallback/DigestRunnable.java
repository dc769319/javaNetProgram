package com.charles.threadMutilCallback;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Charles
 * @version 1.0
 */
public class DigestRunnable implements Runnable {

    private String filename;

    /**
     * 处理器数组，用于注册回调的对象实例
     */
    private ArrayList<CallbackHandler> handlers = new ArrayList<>();

    public void registerHandler(CallbackHandler handler) {
        this.handlers.add(handler);
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(this.filename);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            DigestInputStream dis = new DigestInputStream(in, md);
            while (-1 != dis.read()) ;
            String digest = DatatypeConverter.printHexBinary(md.digest());
            //遍历对象实例，循环处理
            for (CallbackHandler handler : this.handlers) {
                handler.handel(this.filename, digest);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

    DigestRunnable(String filename) {
        this.filename = filename;
    }
}
