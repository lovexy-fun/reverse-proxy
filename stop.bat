@echo off
for /f "tokens=1" %%i in ('jps -l ^| findstr reverse-proxy.jar') do (
	taskkill /f /pid %%i /t
)
set PIDFILE=%~dp0nginx\logs\nginx.pid
if exist %PIDFILE% (
	del %PIDFILE%
)