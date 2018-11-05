package com.bst.backcommon.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作辅助类
 *
 * @author wanna
 * @date 2018-7-13
 */
public class FileUtil extends FileUtils {

    /**
     * 创建目录
     *
     * @param path 路径
     * @throws IOException 创建失败
     */
    public static void mkdirs(String path) throws IOException {
        mkdirs(new File(path));
    }

    /**
     * 创建目录
     *
     * @param file 文件
     * @throws IOException 创建失败
     */
    public static void mkdirs(File file) throws IOException {
        if (!file.exists()) {
            forceMkdir(file);
        }
    }

    /**
     * 创建文件
     *
     * @param filePath 文件路径
     * @throws IOException 创建失败
     */
    public static void createNewFile(String filePath) throws IOException {
        createNewFile(new File(filePath));
    }

    /**
     * 创建文件
     *
     * @param file 文件
     * @throws IOException 创建失败
     */
    public static void createNewFile(File file) throws IOException {
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                throw new IOException("文件创建失败");
            }
        }
    }
}
