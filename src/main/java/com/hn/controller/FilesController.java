package com.hn.controller;

import com.hn.service.FilesService;
import com.hn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Api(tags = "文件管理层")
@RestController
@RequestMapping("/sms/FilesController")
public class FilesController {
    @Resource
    private FilesService filesService;

    /**
     * 上传文件
     */
    @ApiOperation("上传文件")
    @PostMapping("/uploadfiles")
    public Result<Object> uploadfiles(@RequestParam(value = "file", required = true) MultipartFile file) {
        try {
            // 本地文件保存位置
            String uploadPath = "D:/SpringBootCodes/hn_pro/src/main/resources/static/upload";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
//            //打印上传文件位置
//            log.info(uploadDir.getAbsolutePath());
            // 本地文件
            File localFile = new File(uploadPath + File.separator + file.getOriginalFilename());
            // 写文件到本地
            file.transferTo(localFile);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
        return Result.ok();
    }

    /**
     * 删除文件
     */
    @ApiOperation("删除文件")
    @DeleteMapping("/deletefiles")
    public Result<Object> deletefiles(@RequestParam("fileName") String fileName){
        //文件保存路径
        String savePath="D:/SpringBootCodes/hn_pro/src/main/resources/static/upload";
        //根据文件名创建文件对象
        File file=new File(savePath+fileName);
        //如果文件存在且不是目录，删除该文件
        if(file.exists()&&!file.isDirectory()){
            if(file.delete()){
                //删除成功
                return Result.ok();
            }
            else {
                //删除失败，返回错误信息
                return Result.fail("文件删除失败");
            }
        }
        //文件不存在或者是目录，返回错误
        else{
            return Result.fail().message("该文件不存在");
        }
    }

    /**
     * 展示数据库中所有的文件数据
     */
    @ApiOperation("展示所有文件")
    @GetMapping("/showfiles")
    public Result<Object> showfiles(){
        List<Object> files = filesService.listObjs();
        return Result.ok(files);
    }

    /**
     * 下载文件
     */
    @ApiOperation("下载文件")
    @GetMapping("/downloadfiles")
    public void downloadfiles(HttpServletResponse response) {
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition",
                "attachment;filename=file_" + System.currentTimeMillis() + ".hprof");

        // 从文件读到servlet response输出流中
        File file = new File("D:/SpringBootCodes/hn_pro/src/main/resources/static/upload");
        try (FileInputStream inputStream = new FileInputStream(file);) { // try-with-resources
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
