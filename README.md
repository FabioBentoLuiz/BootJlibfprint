# BootJlibfprint
It is a simple implementation of Jlibfprint (https://github.com/eduardobogoni/jlibfprint#jlibfprint) + Spring Boot. 

#Jlibfprint Installation
Follow the steps described at https://github.com/eduardobogoni/jlibfprint#jlibfprint to compile libfprint_jni.

To edit the file JlibFprint_jni/src/Makefile.am, I've found the Phil Lavin tips (https://github.com/phil-lavin/libfprint-java) usefull:
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

#Contributors
Copyright (C) 2016 Fabio Bento Luiz fabioluiz@outlook.de

Many thanks to:
- Copyright (C) 2012 Fabio Scippacercola nonplay.programmer@gmail.com
- Copyright (C) 2012 Agostino Savignano savag88@gmail.com
- Copyright (C) 2015 Kinshuk Bairagi me@kinshuk.in

Look the Libfprint license file for more informations. See http://www.freedesktop.org/wiki/Software/fprint/libfprint
 
 
