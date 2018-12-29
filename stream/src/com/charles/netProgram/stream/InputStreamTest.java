package com.charles.netProgram.stream;

import java.io.*;

public class InputStreamTest {
    public static void main(String[] args) {
        InputStreamTest test = new InputStreamTest();
        try {
            test.createFile();
            test.copyContent();
            System.out.println("文件复制成功");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 把a文件的内容复制到b文件
     * @throws IOException 文件内容复制异常
     */
    private void copyContent() throws IOException {
        //获取项目根路径
        String rootPath = System.getProperty("user.dir");
        String filePath = rootPath + File.separator + "stream" + File.separator + "input.txt";
        String descPath = rootPath + File.separator + "stream" + File.separator + "output.txt";
        File fileToRead = new File(filePath);
        if (!fileToRead.exists()) {
            throw new IOException("待读取文件不存在");
        }
        byte[] buf = new byte[1024];
        InputStream bis = new BufferedInputStream(new FileInputStream(fileToRead));
        OutputStream bos = new BufferedOutputStream(new FileOutputStream(descPath));
        int len;
        while (-1 != (len = bis.read(buf))) {
            bos.write(buf, 0, len);
        }
        bos.flush();
        bos.close();
        bis.close();
    }

    /**
     * 创建目的文件
     * @throws IOException 创建文件异常
     */
    private void createFile() throws IOException {
        //获取项目根路径
        String rootPath = System.getProperty("user.dir");
        String filePath = rootPath + File.separator + "stream" + File.separator + "output.txt";
        File newFile = new File(filePath);
        if (!newFile.exists() && !newFile.createNewFile()) {
            throw new IOException("待输出文件创建失败");
        }
        if (!newFile.setWritable(true)) {
            throw new IOException("待输出文件权限获取失败");
        }
    }
}
