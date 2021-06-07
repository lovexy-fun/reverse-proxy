# reverse-proxy
Reverse proxy system based on nginx.

## 关于项目

适用于公司内网中访问服务器需要认证的情景。在一台已经认证的机器上启动服务，其他机器通过此服务即可访问内网中的服务器。

使用nginx通过简单的配置可以轻易实现反向代理功能，但是配置操作不友好，查看代理端口不方便，因此使用SpringBoot+MybatisPlus+Sqlite+Layui+Nginx搭建了一套可以由web界面进行管理，nginx负责代理的系统。
