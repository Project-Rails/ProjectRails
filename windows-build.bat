@echo off

REM git submodule init
REM git submodule update
REM cd Rainbow
REM git fetch
REM git branch -f upstream master >/dev/null
REM cd ..
git clone Rainbow Rails

copy windows-patch-applyer.jar Rails
cd Rails

git config commit.gpgsign false

echo Applying patches

java -jar windows-patch-applyer.jar

del windows-patch-applyer.jar

cd ..

java -jar movefiles.jar

mvn package

java -jar movefiles.jar undo

pause