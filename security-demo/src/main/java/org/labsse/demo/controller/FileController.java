package org.labsse.demo.controller;

import org.apache.commons.io.IOUtils;
import org.labsse.demo.dto.FileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author lijiechu
 * @create on 2018/12/20
 * @description
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String folder  = "/Users/lijiechu/Documents/";

    @PostMapping
    public FileInfo uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime()+".txt");
        // 阿里云OSS或其余文件服务器
        // 先利用file.getInputStream
        file.transferTo(localFile);
        return new FileInfo(localFile.getPath());
    }

    @GetMapping("/{id}")
    public void downloadFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * JDK7之后try()语法可以将一些打开流的声明放到括号内，在try语句块结束之后将自动关闭流
         * 由此可以省略需要在finally中手动关闭流的语句
         */
        try (InputStream inputStream = new FileInputStream(new File(folder, id+".txt"));
             OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }
}
