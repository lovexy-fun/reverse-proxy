@echo off
start javaw -Xms300m -Xmx300m -XX:CompressedClassSpaceSize=256M -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -jar reverse-proxy-1.0-SNAPSHOT.jar --spring.config.location=application.yml
