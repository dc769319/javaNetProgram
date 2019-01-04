package com.charles.thread;

/**
 * @author Charles
 * @version 1.0
 */
public class DigestCallbackInterface {
    public static void main(String[] args) {
        for (String filename : args) {
            Thread th = new Thread(new DigestThreadCallback(filename));
            th.start();
        }
    }

    /**
     * 子线程中的回调方法
     * @param filename 文件名
     * @param hexDigest 摘要
     */
    static void printCallback(String filename, String hexDigest) {
        System.out.println(filename + ":" + hexDigest);
    }
}
