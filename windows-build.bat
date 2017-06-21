@echo off

git submodule init
git submodule update
cd Rainbow
git fetch
git branch -f origin master >/dev/null
cd ..
git clone Rainbow Rails

copy windows-patch-applyer.jar Rails
cd Rails

git config commit.gpgsign false

echo Applying patches

java -jar windows-patch-applyer.jar

del windows-patch-applyer.jar

cd ..

java -jar movefiles.jar

java -jar minify.jar MakeJar

mvn package

java -jar movefiles.jar undo

pause