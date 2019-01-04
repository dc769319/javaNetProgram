package com.charles.thread;

/**
 * 创建多个子线程，子线程通过回调函数显示处理的结果
 * @author Charles
 * @version 1.0
 */
public class DigestCallbackInterface {

    private String filename;

    private DigestCallbackInterface(String filename) {
        this.filename = filename;
    }

    /**
     * 计算，启动线程
     */
    private void calculate() {
        //传入类的引用
        Thread th = new Thread(new DigestThreadCallback(this.filename, this));
        th.start();
    }

    /**
     * 子线程中的回调方法
     *
     * @param filename  文件名
     * @param hexDigest 摘要
     */
    void printCallback(String filename, String hexDigest) {
        System.out.println(filename + ":" + hexDigest);
    }

    /**
     * 入口
     * @param args 命令行参数列表
     */
    public static void main(String[] args) {
        for (String filename : args) {
            DigestCallbackInterface dtc = new DigestCallbackInterface(filename);
            dtc.calculate();
        }
    }
}
