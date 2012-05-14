There will be no further development of occi4java. The project has been moved to:
https://github.com/irf/tuocci





---------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------


OCCI4Java
---------

The project implements the Open Cloud Computing Interface specification of the [OCCI WG](http://www.occi-wg.org).

Contribute
----------

Issue
You have a issue? You are welcome to send us a message. Or just create a issue.

Patch
You have a patch for us? Please do NOT send us an email. Just [fork](http://help.github.com/fork-a-repo/) our repository, commit the patch and we will have a look if it fixes the issue.

Feature
You want to add a new feature to our implementation, just send us a message, we will check it. Otherwise you can [fork](http://help.github.com/fork-a-repo/) our repository and add it yourself.

Implementation Details
----------------------

The project is divided in three parts, core, infrastructure and http. Every part corresponds to a part of the OCCI specification.

To handle HTTP requests the [RESTlet Framework](http://www.restlet.org) was used.

The project can be build with Maven.


FAQ
---

Q: How can i connect my resource management framework to occi?
A: In folder: infrastructure/src/main/java/occi/infrastructure/interfaces there are all interfaces that have to be implemented. You can implement them directly or with Spring Dependency Injection.

Q: How can i use Spring Dependency Injection?
A: In folder: infrastructure/src/main/java/occi/infrastructure/injection there is the necessary class to inject all interfaces.
[Here](https://github.com/occi4java/libvirt4occi/blob/master/src/main/resources/beans.xml) is a example xml file for Spring DI.

Build Project
-------------

If you want to build the project including the debian package you have to install some packages:

    apt-get install debhelper build-essential

Once they are installed you will find the built debian package in /distribution/target.


Just go to the main folder of the project and type:
    
    $ mvn install

If there are any problems, when building the project, you just can try.

    $ mvn clean install

If you dont want to build the project yourself, just check the download area. There you will find the current release.


Usage
-----

After you have build the project with maven you can start the created jar file. The jar file will be placed in distribution/target/distribution-{version}-packaging.

You can start it with:

    $ java -jar http-{version}.jar

The jar file will start a HTTP server on the localhost at port 8182 (default).

Type "help" to get informed about the console.

### Properties

In our implementation we use property files to configure the webserver and the logger.

The files are located in the Core part under:

    /src/main/resources/conf

If you have already build the project you will find a /conf folder where you can change the configuration.

Debian Package
--------------

If you don´t want to build occi4java yourself, we are happy to provide a debian package, which will be build by invoking in distribution folder:

    mvn package -P generate-jar

The generated package can be easily installed with:

    dpkg -i occi4java_{version}_all.deb

The package installs all necessary files and includes a daemon to start and stop the occi server.

    /etc/init.d/occi4java start|stop|restart|status

All logger information can be found in /var/log/occi4java/occi4java.log

Config files are located under /usr/share/occi4java/conf. If you change a parameter you have to restart occi4java.

Requirements
------------

* Maven 3.x.x
* Java 1.6

To build the debian package you need a debian-based Linux distribution like Ubuntu.


Changelog
---------

0.5

  * Overworked build process. Debian package build is outsourced in a maven profile
  * Version numbers on every modules were removed
  * Merged fixes from a fork (thanks to nilupa)

0.3.1

  * Overworked namespaces
  * Changed from junit to testng

0.3

  * Integrated process to make a deb file

0.2.1

  * Integrated antlr grammar

0.2

  * Overworked the build process
  * Some minor bug fixes
  
0.1

  * Initial release

(c) 2012 Sebastian Laag, Sebastian Heckmann
