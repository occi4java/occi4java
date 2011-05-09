OCCI4Java
---------

The project implements the Open Cloud Computing Interface specification of the [OCCI WG](http://www.occi-wg.org).

Contribute
----------

Issue
You have a issue? You are welcome to send us a message. Or just create a issue.

Patch
You a patch for us? Please do NOT send us an email. Just [fork](http://help.github.com/fork-a-repo/) our repository, commit the patch and we will have a look if it fixes the issue.

Feature
You want to add a new feature to our implementation, just send us a message, we will check it. Otherwise you can [fork](http://help.github.com/fork-a-repo/) our repository and add it yourself.

Implementation Details
----------------------

The project is divided in three parts, core, infrastructure and http. Every part corresponds to a part of the OCCI specification.

To handle HTTP requests the [RESTlet Framework](http://www.restlet.org/) was used.

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

Just go to the main folder of the project and type:
    
    $ mvn install

If you dont want to build the project yourself, just check the download area. There you will find the current release.


Usage
-----

After you have build the project with maven you can start the created jar file. The jar file will be placed in http/target/http-{version}-packaging.

You can start it with:

    $ java -jar http-{version}.jar

The jar file will start a HTTP server on the localhost at port 8182 (default). 

### Properties

In our implementation we use property files to configure the webserver and the logger.

The files are located in the Core part under:

    /src/main/resources/conf

If you have already build the project you will find a /conf folder where you can change the configuration.


Changelog
---------

0.2
  * Overworked the build process
  * Some minor bug fixes

(c) 2011 Sebastian Laag, Sebastian Heckmann
