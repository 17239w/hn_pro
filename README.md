# 流量监管平台

## 1.登陆模块
1. 获取验证码图片响应到浏览器
2. 进行验证码以及用户输入的账号密码进行校验
3. 解析token，跳转到对应的用户类型界面，其中，UsrAdmin==1,UsrRoot==2
## 2.用户模块
### 2.1 UsrAdmin
1. 展示所有UsrAdmin信息
2. 添加或者更新UsrAdmin信息
3. 分页查询admin信息
4. 以Excel格式导出所有UsrAdmin信息
### 2.2 UsrRoot
1. 展示所有UsrRoot信息
2. 添加或者更新UsrRoot信息
3. 添加或者更新UsrAdmin信息
4. 分页查询root信息
5. 逻辑删除Admin信息
6. 以Excel格式导出UsrRoot信息
## 3.文件模块
1. 配置      
    * max-file-size: 1024MB # 单个文件大小
    * max-request-size: 10240MB # 总文件大小（允许存储文件的文件夹大小）
2. 上传文件
3. 删除文件
4. 展示数据库中所有的文件数据
5. 下载文件
## 4.注销模块
重定向到登陆界面


