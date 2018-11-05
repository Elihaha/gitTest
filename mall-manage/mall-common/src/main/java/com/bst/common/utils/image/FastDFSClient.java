package com.bst.common.utils.image;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.util.Properties;

/**
 * @author gan
 * @desc:  FastDFS工具类
 */
public class FastDFSClient {

    private TrackerClient trackerClient;
    private TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient1 storageClient;

    public FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    public FastDFSClient(Properties properties) throws Exception {
        if (properties == null) {
            throw new MyException("未FastDFS配置参数！");
        }
        ClientGlobal.initByProperties(properties);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName 文件全路径
     * @param extName 文件扩展名，不包含（.）
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }


    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent 文件的内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(String group_name, byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
        //       public String upload_file1(String group_name, byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {

        String result = storageClient.upload_file1(group_name,fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }


}
