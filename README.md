![Rails](https://cdn.discordapp.com/attachments/280480189945872385/315651907156705280/logo.png)
=======
[![Build status](https://ci.appveyor.com/api/projects/status/kyyec15kp2qj8wu9/branch/master?svg=true)](https://ci.appveyor.com/project/IsaiahPatton/projectrails-mirror/branch/master) ![Minecraft](https://img.shields.io/badge/Minecraft-1.12-green.svg) ![Discord](https://img.shields.io/badge/Discord-https%3A%2F%2Fdiscord.gg%2FrPmgVN6-blue.svg)

Rails is a Minecraft server mod, updated from [Rainbow](https://project-rainbow.org).

Builds: <https://projectrails.org/releases>

Website: <https://projectrails.org>

Plugins: <https://projectrails.org/resources>

Build
=====

This project uses maven. The project can be build by running the following commands:
```
./scriptrails patch
```
```
java -jar movefiles.jar
```
```
mvn package
```
```
java -jar movefiles.jar undo
```

Or on windows just run
```
windows-build.bat
```

Creating new patches
=====

1. build ProjectRails
2. cd into the generated Rails folder and edit the files you need.
3. git commit your changes (in Rails folder)
3. generate patches (run ```git format-patch origin/master -N -o "../Rainbow-Patches"```)
4. cd back into the main folder and git commit the patches.

Mappings
=====
Mappings are under the Mod Coder Pack licence.
Mappings that are not released by MCP are created by CodeCrafter47.
