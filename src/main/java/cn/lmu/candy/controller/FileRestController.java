package cn.lmu.candy.controller;

import cn.lmu.candy.domain.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.UUID;

/**
 * 图片上传接口
 */
@RestController
@RequestMapping("/api/file")
public class FileRestController {
    @Value("${upload.url}")
    private String uploadUrl;

    @PostMapping("/uploadPicture")
    public ResponseData uploadPicture(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        ResponseData responseData = new ResponseData();

        try {
            // 获取文件名和后缀
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 校验文件类型
            String contentType = file.getContentType();
            if (!Arrays.asList("image/jpeg", "image/png", "image/gif").contains(contentType)) {
                responseData.setSuccess(false);
                responseData.setMsg("文件类型不支持，仅支持 JPG、PNG 和 GIF 格式");
                return responseData;
            }

            // 生成唯一文件名
            String filename = UUID.randomUUID().toString() + suffix;

            // 获取当前 JAR 文件所在目录
            String currentDir = System.getProperty("user.dir");  // 获取当前工作目录
            String uploadDir = currentDir + "/img";  // 拼接 img 目录

            // 确保上传目录存在
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();  // 如果不存在，则创建目录
            }

            // 创建目录并保存文件
            File tempFile = new File(uploadDir, filename);
            Files.createDirectories(tempFile.getParentFile().toPath());  // 确保目录存在
            file.transferTo(tempFile);

            // 返回上传结果
            String fileUrl = uploadUrl + filename; // 假设 URL 映射
            responseData.setSuccess(true);
            responseData.setCode(200);
            responseData.setMsg("图片上传成功！");
            responseData.setData(fileUrl);
        } catch (IOException | IllegalStateException e) {
            responseData.setSuccess(false);
            responseData.setCode(400);
            responseData.setMsg("图片上传失败：" + e.getMessage());
            e.printStackTrace();
        }

        return responseData;
    }
}
