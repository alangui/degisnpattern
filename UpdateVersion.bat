echo off

echo [INFO]input newVersion:

set /p newVersion= 

echo [INFO] input %newVersion% start update

call mvn clean versions:set -DnewVersion=%newVersion%

echo [INFO] input %newVersion% end update