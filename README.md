OCCI4Java
---------

The project implements the Open Cloud Computing Interface specification.
http://www.occi-wg.org

Implementation Details
----------------------

The project is divided in three parts, core, infrastructure and http. Every part corresponds to a part of the OCCI specification.

To handle HTTP requests the RESTlet Framework was used.

The project can be build with Maven.


FAQ
---

Q: How can i connect my resource management framework to occi?
A: In folder: infrastructure/src/main/java/occi/infrastructure/interfaces there are all interfaces that have to be implemented. You can implement them directly or with Spring Dependency Injection.

Q: How can i use Spring Dependency Injection?
A: In folder: infrastructure/src/main/java/occi/infrastructure/injection there is the necessary class to inject all interfaces. A xml file to use dependency injection could look like this:
    <beans xmlns="http://www.springframework.org/schema/beans"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
    	xmlns:context="http://www.springframework.org/schema/context"
    	xsi:schemaLocation="http://www.springframework.org/schema/beans
               http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
               http://www.springframework.org/schema/aop
               http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
               http://www.springframework.org/schema/context
               http://www.springframework.org/schema/context/spring-context-2.5.xsd">
    
    	<bean id="computeInterface" class="occi.libvirt.manager.VmManager" />
    	<bean id="storageInterface" class="occi.libvirt.manager.StorageManager" />
    	<bean id="networkInterface" class="occi.libvirt.manager.NetworkManager" />
    	<bean id="xmlInterface" class="occi.libvirt.vm.VirtualMachineMarshaller" />

    	<bean id="Injection" class="occi.infrastructure.injection.Injection">
		<property name="computeInterface" ref="computeInterface" />
		<property name="storageInterface" ref="storageInterface" />
    		<property name="networkInterface" ref="networkInterface" />
    		<property name="xmlInterface" ref="xmlInterface" />
    	</bean>
    </beans>

Build Project
-------------

Just go to the main folder of the project and type:
    $ mvn install

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


