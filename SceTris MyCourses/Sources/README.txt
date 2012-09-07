This is the source code for the Project “myCourses” which was taken out by the team “SceTris” during the SCORE Contest 2011.

The source files are available in two “styles”, one is a complete Eclipse-ready-made project (you’ll need the AspectJ Weaving Tools to load the project properly), the other one is a clean checkout from our SVN repository.


== GETTING STARTED

You can develop without using an IDE by using the clean checkout, however you will at least need Apache Ant 1.7 or later. To setup a development environment, simply use create your build.properties using
$ ant make-properties
and do a 
$ ant full-setup

The properties in the resulting build.properties-list are almost self-explanatory, however they are describes in detail, just have a look at the build.xml with a browser of your choice.

Afterwards you can start developing and go on using
$ ant rebuild

For a full list of targets and descriptions, see
$ ant -p
or open `build.xml` with a browser of your choice, e.g. firefox. There is an XSL-T Stylesheet that will show a nicely formatted version.


== RUNNING

Once you built scetris (you will not have to do so in the Eclipse project, with or without eclipse) you may run scetris using Apache Tomcat or using Eclipse Jetty. For the latter just type
$ ant start-scetris
Or from within eclipse start `de.fu.scetris.Scetris`, which will run a main-executable and start SceTris @ localhost:8081

If you need to setup the databaseagain, use
$ ant setup-database
Note that it is necessary to shutdown tomcat (or stop jetty) first, in order to kill all connected sessions.

You mal also want to create test-data
$ ./mkTestdata.sh

Note that for the latter you need a POSIX-Shell-Interpreter.

WARNING! Both setup-database and mkTestdata will purge all existing data from your database!


== AUTO GENERATED CODE

We made heavy use of auto generated code. Since we came up with our own solution, you will need to have a POSIX-environment with `xsltproc` and `GNU Make` installed. You should not experience any problems under Mac OS X, Cygwin or most Linux- and BSDish distributions. Note that you only need to auto generate code if you wish to alter the relational schema which is to be found at
> bakery/Relations.xml

For the following you will need to have your working directory changed to bakery
$ cd bakery

Using
$ make
you can create the java-classes, documentation and an sql-install-script.
$ make clean
removes them
$ make install
will install Java-classed to src/de/fu/scetris/data

To hook into auto generated code, please use AspectJ. Aspect are located at
> src/de/fu/scetris/data/aspects
which is to say in the package `de.fu.scetris.data.aspects`


Happy course scheduling!
