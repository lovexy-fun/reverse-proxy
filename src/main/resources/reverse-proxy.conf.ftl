<#list confs as conf> 
server {
    listen ${conf.selfPort};
    proxy_pass ${conf.proxyIp}:${conf.proxyPort};
<#list conf.allowIps as allowIp>
    allow ${allowIp};
</#list>
    deny all;
    access_log logs/${conf.proxyIp}_${conf.proxyPort}_access.log proxy;
    error_log logs/error.log;
}
</#list>