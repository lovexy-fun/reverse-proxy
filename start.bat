@echo off
set BASEPATH=%~dp0
set PIDFILE=%BASEPATH%nginx\logs\nginx.pid
if exist %PIDFILE% (
	del %PIDFILE%
)
set JAR=reverse-proxy.jar
set JVM_ARGS=-Xms300m -Xmx300m -XX:CompressedClassSpaceSize=256M -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m
start javaw %JVM_ARGS% -jar %JAR%