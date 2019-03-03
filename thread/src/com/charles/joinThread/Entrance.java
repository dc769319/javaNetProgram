package com.charles.joinThread;

/**
 * 主线程join子线程，等待子线程执行完
 */
public class Entrance {
    public static void main(String[] args) {
        DigestThread[] digestThreads = new DigestThread[args.length];
        for (int i = 0; i < args.length; i++) {
            digestThreads[i] = new DigestThread(args[i]);
            (digestThreads[i]).start();
        }
        try {
            for (int j = 0; j < args.length; j++) {
                DigestThread digestThread = digestThreads[j];
                //主线程连接子线程，等待子线程结束运行
                digestThread.join();
                StringBuffer strBuf = new StringBuffer();
                strBuf.append(args[j]);
                strBuf.append(":");
                strBuf.append(digestThread.getDigest());
                System.out.println(strBuf);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
