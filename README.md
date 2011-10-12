# tuOCCI Open Cloud Computing Interface Framework
The tuOCCI Open Cloud Computing Interface Framework is a 100% Java&#8482; implementation
of the [Open Cloud Computing Interface (OCCI)](http://occi-wg.org/) family of
specifications. More specifically, it provides a full implementation of the
[OCCI Core (GFD.183)](http://ogf.org/documents/GFD.183.pdf),
[OCCI Infrastructure (GFD.184)](http://ogf.org/documents/GFD.184.pdf), and
[OCCI RESTful HTTP Rendering (GFD.185)](http://ogf.org/documents/GFD.185.pdf)
specifications, and passes the [doyouspeakOCCI](http://doyouspeakocci.appspot.com)
compliance tests.


## How to use
tuOCCI was hard to implement, but is simple to use. Therefore, you can integrate
tuOCCI into your project in three simple steps:

 1. Use the tuOCCI [Annotations](https://github.com/irf/tuocci/wiki/annotations)
    and [Interfaces](https://github.com/irf/tuocci/wiki/interfaces) to make your
    types first-class OCCI citizens.
 1. Tell the [tuOCCI Configuration](https://github.com/irf/tuocci/wiki/configuration)
    which of your classes you would like to expose.
 1. Run the [tuOCCI Service](https://github.com/irf/tuocci/wiki/service) in your
    favorite container.

For specific questions, please also take a look at the [FAQ](https://github.com/irf/tuocci/wiki/faq).


## Where to get
tuOCCI will be available through various channels, providing easy access to both
source and binary distributions. Please take a look at the
[tuOCCI Installation Guide](https://github.com/irf/tuocci/wiki/installation) for
a detailed explanation on how to setup tuOCCI.

### Sources and binaries
Packages with the source distribution can be obtained by two ways:

 * By [checking out](http://help.github.com/git-cheat-sheets/) the source code
   via `git checkout`
 * By fetching a repository [tarball](https://github.com/irf/tuocci/tarball/master)
   or [zipfile](https://github.com/irf/tuocci/tarball/master)

Alternatively, you might want to pick one of the advertised downloads (click on
the "Downloads" button in the upper right of the tuOCCI home at
[GitHub](https://github.com/irf/tuocci).

### Maven
tuOCCI can be used directly from within [Apache Maven](http://maven.apache.org).
Assuming that the corresponding jarfiles are in your local repository, add one or
more (depending what you need) of the following lines to your `pom.xml`:

```xml
<!-- tuOCCI Core Model Implementation -->
<dependency>
    <groupId>de.irf.it.tuocci</groupId>
    <artifactId>tuocci-core</artifactId>
    <version>${tuocci.version}</version>
</dependency>
<!-- tuOCCI Infrastructure Model Implementation (depends on tuocci-core) -->
<dependency>
    <groupId>de.irf.it.tuocci</groupId>
    <artifactId>tuocci-infrastructure</artifactId>
    <version>${tuocci.version}</version>
</dependency>
<!-- tuOCCI HTTP Rendering Implementation (depends on tuocci-core and tuocci-infrastructure) -->
<dependency>
    <groupId>de.irf.it.tuocci</groupId>
    <artifactId>tuocci-http</artifactId>
    <version>${tuocci.version}</version>
</dependency>
```

A package release to [Maven Central](http://search.maven.org/) will be coming
soon.


### Linux
tuOCCI aims to provide packages for a number of Linux distributions. Currently,

 * [Ubuntu](http://www.ubuntulinux.com),
 * [Fedora](http://fedoraproject.org), and
 * [openSUSE](http://www.opensuse.org)

are under evaluation. Stay tuned for updates!


## Contributing
tuOCCI aims to be a community effort, and help is always welcome. Please contact
us on the mailing lists to learn more.

### License
We think that tuOCCI should be available to everyone with the upmost amount of
freedom. To make sure that contributions to tuOCCI itself remain perpetually free,
the code has been developed under the [GNU Lesser General Public License, Version 3](http://www.gnu.org/licenses/lgpl-3.0.html).
The documentation coming with tuOCCI is available under a
[Creative Commons Attribution Share-Alike 3.0 License](http://creativecommons.org/licenses/by-sa/3.0/).

### Issues
If you think that you have discovered a bug in tuOCCI, or you would like to see
an additional feature in the future, please use the
[tuOCCI GitHub Tracker](https://github.com/irf/tuocci/issues) to submit an issue.

### Patches
You are welcome to contribute code for any kind of recorded issue. However, patches
via email are not accepted. Rather than that, please [fork](http://help.github.com/fork-a-repo/)
our repository, commit the patch, and send us a [pull request](http://help.github.com/send-pull-requests/)
&mdash;we will then have a look at it. Remember to add a link to the issue you aim to fix.
