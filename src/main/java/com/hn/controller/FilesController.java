package com.hn.controller;

import com.hn.service.FilesService;
import com.hn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
    public Result<Object> uploadfiles(@ApiParam("封装请求体中的文件的二进制数据")
                                      @RequestPart("multipartFile") MultipartFile multipartFile )throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        /*形成新的文件名fileName:
        UUID.randomUUID().toString()生成一个随机的UUID。
        .replace("-","") 将 UUID 中的连字符 - 替换为空串，从而得到只包含字母和数字的字符串。
        .toLowerCase()将字符串转换为小写字母。
        .concat(originalFilename.substring(originalFilename.lastIndexOf(".")))将原始文件名的文件扩展名添加到字符串末尾，从而形成新的文件名。*/
        String fileName= UUID.randomUUID().toString().replace("-","").toLowerCase().
                concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        //文件保存路径：concat()拼接文件名和路径名
        String savePath="D:/SpringBootCodes/hn_pro/src/main/resources/static/upload".concat(fileName);
        //将客户端上传的文件保存到服务器硬盘上，savePath为文件保存路径
        multipartFile.transferTo(new File(savePath));
        //返回文件路径
        return Result.ok("upload/".concat(fileName));
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


}
