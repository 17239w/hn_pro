package com.hn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.hn.domain.UsrAdmin;
import com.hn.domain.UsrRoot;
import com.hn.service.UsrAdminService;
import com.hn.service.UsrRootService;
import com.hn.utils.MD5;
import com.hn.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "UsrRoot管理层")
@RestController
@RequestMapping("/sms/UsrRootController")
public class UsrRootController {

    @Resource
    private UsrRootService usrRootService;
    @Resource
    private UsrAdminService usrAdminService;


    /**
     * Root拥有添加或者更新UsrRoot信息的权限
     *
     * @param usrRoot
     * @return
     */
    @ApiOperation("root添加/更新UsrRoot信息")
    @PostMapping("/saveOrUpdateUsrRoot")
    public Result<Object> saveOrUpdateUsrRoot(@ApiParam("请求体中没用id为存储 有id为更新")
                                              @RequestBody UsrRoot usrRoot) {
        Long RootId = usrRoot.getRootId();
        //1.如果id不存在,则新增UsrRoot
        if (RootId == null) {
            usrRoot.setPassword(MD5.encrypt(usrRoot.getPassword()));
            usrRootService.save(usrRoot);
        }
        //2.如果id存在,则根据id更新UsrAdmin
        else {
            usrRootService.update(usrRoot, new LambdaQueryWrapper<UsrRoot>().eq(UsrRoot::getRootId, RootId));
        }
        return Result.ok();
    }

    /**
     * Root还拥有添加或者更新UsrAdmin信息的权限
     *
     * @param usrAdmin
     * @return
     */
    @ApiOperation("root添加/更新UsrAdmin信息")
    @PostMapping("/saveOrUpdateUsrAdmin")
    public Result<Object> saveOrUpdateUsrAdmin(@ApiParam("请求体中封装的UsrRoot的json信息")
                                                   @RequestBody UsrAdmin usrAdmin) {
        Long AdminId = usrAdmin.getAdminId();
        //1.如果id不存在,则新增UsrAdmin
        if (AdminId == null) {
            usrAdmin.setPassword(MD5.encrypt(usrAdmin.getPassword()));
            usrAdminService.save(usrAdmin);
        }
        //2.如果id存在,则根据id更新UsrAdmin
        else {
            usrAdminService.update(usrAdmin, new LambdaQueryWrapper<UsrAdmin>().eq(UsrAdmin::getAdminId, AdminId));
        }
        return Result.ok();
    }

    /**
     * 根据姓名模糊查询Root信息，分页带条件
     *
     * @param pn
     * @param pageSize
     * @param RootName
     * @return
     */
    @ApiOperation("分页查询root信息")
    @GetMapping("/getAllRoot/{pn}/{pageSize}")
    public Result<Object> getAllRoot(@ApiParam("当前页码") Integer pn,
                                     @ApiParam("每页显示的Root数量") Integer pageSize,
                                     @ApiParam("模糊条件，要查询的Root姓名") String RootName) {
        //1.构造查询条件
        LambdaQueryWrapper<UsrRoot> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //2.根据id升序排列
        lambdaQueryWrapper.like(StrUtil.isNotBlank(RootName),UsrRoot::getName,RootName).
                like(StrUtil.isNotBlank(RootName),UsrRoot::getName,RootName).orderByAsc(UsrRoot::getRootId);
        //3.分页查询,并封装到page对象中
        Page<UsrRoot> page = usrRootService.page(new Page<>(pn, pageSize), lambdaQueryWrapper);
        //4.返回结果
        return Result.ok(page);
    }


    /**
     * Root拥有删除Admin的权限
     * 逻辑删除 将deleted_flag设置为1
     * @param adminIds
     * @return
     */
    @ApiOperation("root删除admin")
    @DeleteMapping("/deleteAdmin")
    public Result<Object> deleteAdmin(@ApiParam("要删除的admin的id集合")
                                      @RequestBody List<Long> adminIds) {

        //删除一个admin用户(更新deleted_flag)
        if (adminIds.size() == 1) {
            //如果id对应的deleted_flag=0的话，代表没有被删除
            // 若要删除该id对应的用户，需要将deleted_flag设置为1，代表已经被删除
            if (usrAdminService.getById(adminIds.get(0)).getDeletedFlag() == 0) {
                UsrAdmin admin = new UsrAdmin();
                admin.setDeletedFlag(1L);
                usrAdminService.update(admin, new LambdaQueryWrapper<UsrAdmin>().eq(UsrAdmin::getAdminId, adminIds.get(0)));
            }
            //如果id对应的deleted_flag=1的话，代表已经被删除,则不能再删除此用户
            else {
                return Result.fail("该用户已经被删除,不可重复删除");
            }
        }

        //删除多个admin用户
        else {
            //遍历集合，将集合中的每一个id对应的用户的deleted_flag设置为1
            for (Long adminId : adminIds) {
                //如果id对应的deleted_flag=0的话，代表没有被删除
                // 若要删除该id对应的用户，需要将deleted_flag设置为1，代表已经被删除
                if (usrAdminService.getById(adminId).getDeletedFlag() == 0) {
                    UsrAdmin admin = new UsrAdmin();
                    admin.setDeletedFlag(1L);
                    usrAdminService.update(admin, new LambdaQueryWrapper<UsrAdmin>().eq(UsrAdmin::getAdminId, adminId));
                }
                //如果id对应的deleted_flag=1的话，代表已经被删除,则不能再删除此用户
                else {
                    return Result.fail("该用户已经被删除,不可重复删除");
                }
            }
        }
        return Result.ok();
    }
}
