package com.charles.threadMutilCallback;

/**
 * 将线程的摘要结果输出到控制台
 * @author Charles
 * @version 1.0
 */
public class PrintHandler implements CallbackHandler {
    @Override
    public void handel(String filename, String hexDigest) {
        System.out.println(filename + ":" + hexDigest);
    }
}
