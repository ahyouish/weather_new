@echo off
REM Weather Forecasting App GUI Launcher
REM Set Java paths
set JAVA_HOME=C:\jdk-21.0.2+13
set MAVEN_HOME=%CD%\apache-maven-3.9.6
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%

echo Building Weather Forecasting App...
mvn clean package -q -DskipTests

echo.
echo ====================================
echo Starting Weather Forecasting GUI App
echo ====================================
echo.

java -jar target\weather-app.jar

pause
