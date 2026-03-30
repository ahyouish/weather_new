@echo off
REM Weather Forecasting App GUI Launcher using Maven JavaFX Plugin
REM Set Java paths
set JAVA_HOME=C:\jdk-21.0.2+13
set MAVEN_HOME=%CD%\apache-maven-3.9.6
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%

echo Building and launching Weather Forecasting GUI App...
mvn clean javafx:run

pause
