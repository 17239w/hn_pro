package com.hn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.hn.domain.UsrAdmin;
import com.hn.service.UsrAdminService;
import com.hn.utils.MD5;
import com.hn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "UsrAdmin管理层")
@RestController
@RequestMapping("/sms/UsrAdminController")
public class UsrAdminController {
    @Resource
    private UsrAdminService usrAdminService;

    /**
     * Admin拥有添加或者更新UsrAdmin信息的权限
     * @param usrAdmin
     * @return
     */
    @ApiOperation("admin增加/更新UsrAdmin信息")
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
     * 根据姓名查询UsrAdmin信息
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
        //根据姓名模糊查询
        Page<UsrAdmin>page=usrAdminService.page(new Page<>(pn,pageSize),new LambdaQueryWrapper<UsrAdmin>()
                .like(StrUtil.isNotBlank(AdminName),UsrAdmin::getName,AdminName).orderByAsc(UsrAdmin::getAdminId));
        return Result.ok(page);
    }
}
