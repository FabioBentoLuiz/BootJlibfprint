**Consider using the newer  implementation (raspi-libfprint) is available [here](https://github.com/FabioBentoLuiz/BootJlibfprint)**.

**A blog post with further details you can find [here](https://fbentoluiz.io/libfprint)**.

# BootJlibfprint

This is a simple implementation of Jlibfprint (https://github.com/eduardobogoni/jlibfprint#jlibfprint) in a Spring Boot application.

It has no error handling and is part of a initial research with the intent of analyse if it would be possible implement libfprint with a web based interface.

Currently is works just in Linux.

#Jlibfprint Installation
Follow the steps described at https://github.com/eduardobogoni/jlibfprint#jlibfprint to compile libfprint_jni.

In the section to edit the file JlibFprint_jni/src/Makefile.am, I've found the Phil Lavin's hints (https://github.com/phil-lavin/libfprint-java) usefull:
> You need to add 5 things here, in the same style as the example below:

> - The path to the libfprint source directory. In my case, /home/pi/fprint/libfprint/libfprint-0.5.1
> - The path from point 1 but this time as a -L param. In my case, /home/pi/fprint/libfprint/libfprint-0.5.1
> - The path to the libfprint/nbis/include directory. In my case, /home/pi/fprint/libfprint/libfprint-0.5.1/libfprint/nbis/include
> - The system path which contains jni.h (hint use the locate command). In my case, /usr/lib/jvm/jdk-7-oracle-armhf/include
> - The system path which contains jni_md.h (hint use the locate command). In my case, /usr/lib/jvm/jdk-7-oracle-armhf/include/linux.

> A full example of that line is:
```
ADD_INCLUDES=-I/home/pi/fprint/libfprint/libfprint-0.5.1 -L/home/pi/fprint/libfprint/libfprint-0.5.1  -I/home/pi/fprint/libfprint/libfprint-0.5.1/libfprint/nbis/include -I/usr/lib/jvm/jdk-7-oracle-armhf/include  -I/usr/lib/jvm/jdk-7-oracle-armhf/include/linux
```

#Spring Boot project
After compile libfprint_jni and the Jlibfprint java project, change the pom.xml file of the project BootJlibfprint to set **jlibfprint-1.0-SNAPSHOT.jar** as a dependency. In the following example, both projects are in the same workspace. 
```
<dependency>
			<groupId>jlibfprint</groupId>
			<artifactId>jlibfprint</artifactId>
			<version>1.0-SNAPSHOT</version>
			<scope>system</scope>
			<systemPath>${project.basedir}/../JlibFprint/target/jlibfprint-1.0-SNAPSHOT.jar</systemPath>
</dependency>
```
To debug on Spring Tool Suite or Eclipse, add the environment variable JLIBFPRINT_JNI=/usr/local/lib/libfprint_jni.so on Run -> Run Configurations -> Environment.

#Configuration - application.properties
```
server.port=9000
spring.datasource.name=test
spring.thymeleaf.cache=false
spring.h2.console.enabled=true
spring.h2.console.path=/console
spring.datasource.platform=h2
```
With the default configuration, the application will be available on port 9000. Ex: http://localhost:9000.

It uses h2 in memory database, so be aware that every time that you restart the application, the database is erased. The database is accessible on /console, no password is necessary. Ex: http://localhost:9000/console.


#Contributors
Copyright (C) 2016 Fabio Bento Luiz bento_85@hotmail.com

Many thanks to:
- Copyright (C) 2012 Fabio Scippacercola nonplay.programmer@gmail.com
- Copyright (C) 2012 Agostino Savignano savag88@gmail.com
- Copyright (C) 2015 Kinshuk Bairagi me@kinshuk.in

Look the Libfprint license file for more informations. See http://www.freedesktop.org/wiki/Software/fprint/libfprint
 
 
