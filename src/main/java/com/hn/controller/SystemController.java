package com.hn.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hn.domain.LoginForm;
import com.hn.domain.UsrAdmin;
import com.hn.domain.UsrRoot;
import com.hn.service.UsrAdminService;
import com.hn.service.UsrRootService;
import com.hn.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* 将封装在result类中的数据以json格式返回给前端浏览器
* */
@Api(tags = "系统控制层")
@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Resource
    private UsrAdminService usrAdminService;
    @Resource
    private UsrRootService usrRootService;

    /**
     * 获取验证码图片响应到浏览器,并将验证码中的值保存到session域中 用于/login校验
     */
    @ApiOperation("获取验证码图片")
    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
        //1.通过utils类CreateVerifiCodeImage获得验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //2.获取验证码图片中的值,并保存在session域中,用于/login校验
        String code = new String(CreateVerifiCodeImage.getVerifiCode());
        session.setAttribute("code", code);
        //3.将获取到的验证码图片响应到浏览器
        ImageIO.write(verifiCodeImage, "JPG", response.getOutputStream());
    }

    /**
     * 登录:进行验证码以及用户输入的账号密码进行校验
     * @return 将校验的结果数据封装到Result类中返回给浏览器
     * 若用户登录成功,则根据(id和用户类型)生成一个token放在Result类一起返回给浏览器
     * @RequestBody:将请求体中的json数据封装到实体类RequestForm中,并将实体类对象作为参数传递给login方法,
     * LoginForm是RequestForm的子类, HttpSession是springmvc中的对象, 用于获取session域中的数据
     */
    @ApiOperation("登陆功能，若登陆成功，将查询到的用户信息、用户类型，封装到id和userType中，生成token返回给前端")
    @PostMapping("/login")
    public Result<Object> login(@ApiParam("封装到实体类中请求体的json数据")
                                @RequestBody LoginForm loginForm, HttpSession session) {
        //1.校验验证码
        String userInputCode = loginForm.getVerifiCode();
        //2.获取session中存放的验证码中的值
        String code = session.getAttribute("code").toString();
        //3.判断session中的验证码的值是否还在 若时间太长 会失效
        if (code == null || "".equals(code)) {
            return Result.fail().message("验证码已失效,请重新获取");
        }
        //4.判断用户输入验证码与实际验证码的值是否相等
        if (!userInputCode.equalsIgnoreCase(code)) {
            return Result.fail().message("验证码输入错误");
        }
        //5.验证码输入正确后 将session中的验证码销毁
        session.removeAttribute("code");
        //6.获取用户类型 根据不同的用户判断输入的账号 密码 是否正确
        Integer userType = loginForm.getUserType();
        Map<String, Object> map = new LinkedHashMap<>();
        if (userType == 1) {
            //6.1登陆成功，将(用户id与用户类型)封装为一个token返回给前端
            //让浏览器通过token再次发送请求进行解析，告诉前端应该前往哪个用户以及哪个类型用户的首页
            UsrRoot usrRoot = usrRootService.selectUsrRootByNameAndPassword(loginForm.getUsername(), MD5.encrypt(loginForm.getPassword()));
            String token = JwtHelper.createToken(usrRoot.getRootId().longValue(), userType);
            map.put("token", token);
            //6.2若查询结果是null,则代表数据库中没有对应的用户名和密码，登陆失败
            if (usrRoot == null) {
                return Result.fail().message("用户名或密码错误");
            }
        } else if (userType == 2) {
            UsrAdmin usrAdmin = usrAdminService.selectUsrAdminByNameAndPassword(loginForm.getUsername(), MD5.encrypt(loginForm.getPassword()));
            String token = JwtHelper.createToken(usrAdmin.getAdminId().longValue(), userType);
            map.put("token", token);
            if (usrAdmin == null) {
                return Result.fail().message("用户名或密码错误");
            }
        }
        return Result.ok(map);
    }


    /**
     * @param token
     * @return
     * @RequestHeader指示方法参数应绑定到传入 HTTP 请求中的标头
     */
    @ApiOperation("将请求头中的token解析成id和用户类型，根据id和类型查询用户信息，将用户信息和用户类型一起返回")
    @RequestMapping("/getUserInfo")
    public Result<Object> getUserInfo(@ApiParam("请求头中的token")
                                      @RequestHeader("token") String token) {
        //1.判断token是否还有效，返回为true代表失效
        if (JwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //2.解析token，获取用户id和用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        if (userType == null) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //3.通过不同的用户类型，根据id查询用户信息，放到Result类中返回给浏览器
        Map<String, Object> map = new LinkedHashMap<>();
        if (userType == 1) {
            UsrRoot usrRoot = usrRootService.selectUsrRootById(userId);
            map.put("userInfo", usrRoot);
            map.put("userType", userType);
        } else if (userType == 2) {
            UsrAdmin usrAdmin = usrAdminService.selectUsrAdminById(userId);
            map.put("userInfo", usrAdmin);
            map.put("userType", userType);
        }
        return Result.ok(map);
    }

    @ApiOperation("修改用户名和密码")
    @PostMapping("updatePwd/{oldPwd}/{newPwd}")
    public Result<Object>updatePwd(@ApiParam("请求头中的token数据") @RequestHeader("token") String token,
                                   @ApiParam("路径参数中的原密码")@PathVariable("oldPwd") String oldPwd,
                                   @ApiParam("路径参数中的新密码")@PathVariable("newPwd") String newPwd){
        //1.判断token是否还有效
        if(JwtHelper.isExpiration(token)){
            return Result.fail().message("token已失效，请重新登录后修改");
        }
        //2.获取用户id
        Long userId = JwtHelper.getUserId(token);
        //3.根据token判断用户类型
        Integer userType = JwtHelper.getUserType(token);
        assert userType != null;
        if(userType==1){
            //3.1对旧密码进行校验
            UsrRoot usrRoot = usrRootService.selectUsrRootById(userId);
            if(!MD5.encrypt(oldPwd).equals(usrRoot.getPassword())){
                return Result.fail().message("原密码输入错误");
            }
            //3.2若原密码输入正确，则将新密码进行加密后修改
            usrRoot.setPassword(MD5.encrypt(newPwd));
            usrRootService.update(usrRoot,new LambdaQueryWrapper<UsrRoot>().eq(UsrRoot::getRootId,userId));
        }
        else if(userType==2){
            UsrAdmin usrAdmin = usrAdminService.selectUsrAdminById(userId);
            if(!MD5.encrypt(oldPwd).equals(usrAdmin.getPassword())){
                return Result.fail().message("原密码输入错误");
            }
            usrAdmin.setPassword(MD5.encrypt(newPwd));
            usrAdminService.update(usrAdmin,new LambdaQueryWrapper<UsrAdmin>().eq(UsrAdmin::getAdminId,userId));
        }
        return Result.ok();

    }
}
