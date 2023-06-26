package com.hn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.hn.domain.UsrAdmin;
import com.hn.domain.UsrRoot;
import com.hn.service.UsrAdminService;
import com.hn.utils.MD5;
import com.hn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "UsrAdmin管理层")
@RestController
@RequestMapping("/sms/UsrAdminController")
public class UsrAdminController {
    @Resource
    private UsrAdminService usrAdminService;

    /**
     * 展示所有UsrAdmin信息
     * @return
     */
    @ApiOperation("展示所有UsrAdmin信息")
    @GetMapping("/selectAllUsrAdmin")
    public Result<List<UsrAdmin>> selectAllUsrAdmin() {
        List<UsrAdmin> usrAdminList = usrAdminService.list();
        return Result.ok(usrAdminList);
    }

    /**
     * Admin拥有添加或者更新UsrAdmin信息的权限
     * @param usrAdmin
     * @return
     */
    @ApiOperation("增加/更新UsrAdmin")
    @PostMapping("/saveOrUpdateUsrAdmin")
    public Result<Object> saveOrUpdateUsrAdmin(@ApiParam("请求体中封装的UsrAdmin的json信息")
                                               @RequestBody UsrAdmin usrAdmin) {
        Long AdminId= usrAdmin.getAdminId();
        //1.如果id不存在,则新增UsrAdmin
        if(AdminId==null){
            usrAdmin.setPassword(MD5.encrypt(usrAdmin.getPassword()));
            usrAdminService.save(usrAdmin);
        }
        //2.如果id存在,则根据id更新UsrAdmin
        else{
            usrAdminService.update(usrAdmin,new LambdaQueryWrapper<UsrAdmin>().eq(UsrAdmin::getAdminId,AdminId));
        }
        return Result.ok();
    }

    /**
     * 模糊查询Admin信息
     * @param pn
     * @param pageSize
     * @param AdminName
     * @return
     */
    @ApiOperation("分页查询admin信息")
    @GetMapping("/getAllAdmin/{pn}/{pageSize}")
    public Result<Object> getAllAdmin(@ApiParam("当前页码")@PathVariable("pn")Integer pn,
                                      @ApiParam("每页显示的Admin数量")@PathVariable("pageSize")Integer pageSize,
                                      @ApiParam("模糊条件，要查询的Admin姓名")String AdminName){
        //1.构造查询条件
        LambdaQueryWrapper<UsrAdmin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //2.根据姓名模糊查询
        lambdaQueryWrapper.like(StrUtil.isNotBlank(AdminName),UsrAdmin::getName,AdminName).
                like(StrUtil.isNotBlank(AdminName),UsrAdmin::getAdminId,AdminName).orderByAsc(UsrAdmin::getAdminId);
        //3.分页查询,并封装到page对象中
        Page<UsrAdmin> page = usrAdminService.page(new Page<>(pn, pageSize), lambdaQueryWrapper);
        //4.返回结果
        return Result.ok(page);
    }

    /**
     * 导出Excel表格
     */
    @ApiOperation("导出Excel表格")
    @GetMapping("/excel/download")
    public void download(HttpServletResponse response){
        try{
            //1.清除当前HTTP响应的缓冲区
            response.reset();
            //2.将HTTP响应的内容类型设置为“application/vnd.ms-excel”
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //3.设置Content-Disposition响应头
            response.setHeader("Content-Disposition",
                    "attachment;filename=UsrAdmin_excel_"+System.currentTimeMillis()+".xls");
            usrAdminService.downloadExcel_UsrAdmin(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
