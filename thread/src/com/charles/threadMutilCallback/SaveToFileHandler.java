package com.charles.threadMutilCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 将线程处理的摘要结果写入文件
 *
 * @author Charles
 * @version 1.0
 */
public class SaveToFileHandler implements CallbackHandler {
    @Override
    public void handel(String filename, String hexDigest) {
        //获取项目根路径
        String rootPath = System.getProperty("user.dir");
        String filePath = rootPath + File.separator + "thread" + File.separator + "digest.txt";
        File fileOutput = new File(filePath);
        //带资源的try语句，会自动关闭流
        try (FileOutputStream out = new FileOutputStream(fileOutput, true)) {
            if (!fileOutput.isFile()) {
                if (!fileOutput.createNewFile()) {
                    throw new IOException("文件创建失败：" + filePath);
                }
                if (!fileOutput.setWritable(true)) {
                    throw new IOException("文件权限修改失败：" + filePath);
                }
            }
            String digest = filename + ":" + hexDigest + "\r\n";
            out.write(digest.getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
