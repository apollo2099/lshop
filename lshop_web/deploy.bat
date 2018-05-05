@echo off
cd /d %~p0
if errorlevel 1 goto error
del /S /F /Q *.jar
mvn clean dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib -DincludeScope=runtime compile

:error
echo Enter the path occurred error! Go to the root path by youself and excute.
pause