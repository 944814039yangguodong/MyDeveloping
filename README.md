# 项目说明

## 线上部署地址

系统前端首页：<http://82.157.174.105/>

swagger线上接口测试页：<http://82.157.174.105/swagger-ui.html>

## 用户账户密码

|登陆身份|用户名|密码|
|------|------|------|
|校级负责人|1000|123456|
|系级负责人|3333|123456|
|审核老师|2222|123456|
|普通老师|1111|123456|
|学生1|1234|123456|

## 部署环境从dev环境到prod环境需要修改的内容

### application.yml: 
dev >>> prod

### Constant.java: 
ROOT_PATH 修改注释

### common.js: 
IP 修改注释