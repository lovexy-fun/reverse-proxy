# reverse-proxy
Reverse proxy system based on nginx.

## 关于项目

适用于公司内网中访问服务器需要认证的情景。在一台已经认证的机器上启动服务，其他机器通过此服务即可访问内网中的服务器。

使用nginx通过简单的配置可以轻易实现反向代理功能，但是配置操作不友好，查看代理端口不方便，因此使用SpringBoot+MybatisPlus+Sqlite+Layui+Nginx搭建了一套可以由web界面进行管理，nginx负责代理的系统。

## 使用方法

到 [release](https://github.com/lovexy-fun/reverse-proxy/releases) 页面下载，解压后修改`application.yml`文件中的系统登录账号和密码，双击start.bat运行。