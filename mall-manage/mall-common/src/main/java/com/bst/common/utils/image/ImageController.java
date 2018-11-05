package com.bst.common.utils.image;


import com.bst.common.constants.HttpConstants;
import com.bst.common.exception.ImageHandleException;
import com.bst.common.modle.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

/**
 * 审核
 */
@RestController
@RequestMapping("/image")
@Api(value = "COMMON-IMAGE",description = "图片上传接口描述")
public class ImageController {

    @Resource
    private Properties dfsProperties;

    @Value("${fast-dfs.config.group}")
    private String fastDFSConfigGroupName;

    @Value("${fast-dfs.config.httpServerPath}")
    private String httpServerPath;

    @PostMapping("upload")
    @ApiOperation(value = "上传图片(适应插件)",notes = "返回类型为String类型的")
    public String uploadImage(MultipartFile file, HttpServletResponse response) {
        String imageUrl = "";
        try {
            //获取后缀名进行拼接
            String filename = file.getOriginalFilename();
            String extName;
            if (Objects.requireNonNull(filename).contains(".")) {
                extName = filename.substring(filename.lastIndexOf(".") + 1);
            } else {
                String contentType = file.getContentType();
                extName = Objects.requireNonNull(contentType).split("/")[1];
            }
            CheckImgFormat(extName);
            FastDFSClient fastDFSClient = new FastDFSClient(dfsProperties);
            //使用FastDFS客户端进行上传
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", filename);
            metaList[1] = new NameValuePair("fileExtName", extName);
            metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getBytes().length));
            String path = fastDFSClient.uploadFile(fastDFSConfigGroupName, file.getBytes(), extName, metaList);
            imageUrl = "/" + path;

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return imageUrl;
    }

    /**
     * : 校验图片格式
     *
     * @param extName 文件尾缀
     */
    private void CheckImgFormat(String extName) {
        if (!(extName.equalsIgnoreCase("JPEG")
                || extName.equalsIgnoreCase("PNG")
                || extName.equalsIgnoreCase("GIF")
                || extName.equalsIgnoreCase("JPG")
                || extName.equalsIgnoreCase("BMP"))) {
            throw new ImageHandleException("请上传我们支持的图片格式: jpeg, png, gif, jpg, bmp");
        }
    }

    @ApiOperation(value = "上传图片",notes = "返回类型为对象")
    @PostMapping("uploadSingleImage")
    public Result uploadSingleImage(MultipartFile file) {
        Result result = new Result();
        try {
            //获取后缀名进行拼接
            String filename = file.getOriginalFilename();
            String extName;
            if (Objects.requireNonNull(filename).contains(".")) {
                extName = filename.substring(filename.lastIndexOf(".") + 1);
            } else {
                String contentType = file.getContentType();
                extName = Objects.requireNonNull(contentType).split("/")[1];
            }
            CheckImgFormat(extName);
            FastDFSClient fastDFSClient = new FastDFSClient(dfsProperties);
            //使用FastDFS客户端进行上传
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", filename);
            metaList[1] = new NameValuePair("fileExtName", extName);
            metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getBytes().length));
            String path = fastDFSClient.uploadFile(fastDFSConfigGroupName, file.getBytes(), extName, metaList);
            String imageUrl = "/" + path;

            result.setData(imageUrl);
            result.setStatus(HttpConstants.SUCCESS);
            result.setMsg("上传成功！");
        } catch (ImageHandleException e) {
            e.printStackTrace();
            result.setMsg(e.getMessage());
            result.setStatus(HttpConstants.UNKNOW);
            result.setMsg("上传失败！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(HttpConstants.UNKNOW);
            result.setMsg("上传失败！");
        }
        return result;
    }

}
