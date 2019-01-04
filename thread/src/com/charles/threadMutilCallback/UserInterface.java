package com.charles.threadMutilCallback;

/**
 * 程序入口，接受命令行参数，按文件数量，启动相应数量的子线程，每个子线程处理一个文件。
 * 子线程结束时调用传入的多个处理器对结果进行处理
 *
 * @author Charles
 * @version 1.0
 */
public class UserInterface {
    private String filename;

    public static void main(String[] args) {
        for (String filename : args) {
            UserInterface ui = new UserInterface(filename);
            ui.calculate();
        }
    }

    /**
     * 计算
     */
    private void calculate() {
        PrintHandler ph = new PrintHandler();
        SaveToFileHandler fh = new SaveToFileHandler();
        DigestRunnable runnable = new DigestRunnable(this.filename);
        //注册多个回调处理器
        runnable.registerHandler(ph);
        runnable.registerHandler(fh);
        //启动新线程
        Thread th = new Thread(runnable);
        th.start();
    }

    private UserInterface(String filename) {
        this.filename = filename;
    }
}
