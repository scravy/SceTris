# This file is intended to be used with GNU make
# It was tested with version 3.81
# @author "Julian Fleischer" <scravy>

# This Makefile is intended to ease the development of our project.
# It shall provide rules for
# - building our application,
# - documenting our application,
# - deploying our application (that is: creating a bundle, e.g. a zip-file,
# containing installation items)
# NOTE: As of 2010-09-07 it does not support the latter one.

# Usage: (dollar denotes a terminal, brackets optionals)
# $ make [<target>]
# where target may be one of
# all (default if <target> omitted), html, devdoc, apidoc,
# run-sandpit, clean, maintainer-clean

# IMPORTANT! You will need some libraries to develop, 
#            use ``make devel'' to fetch them.

# Before hacking this file please have a look at $ man make
# or http://www.gnu.org/software/automake/manual/make/index.html#Top


# = INITIAL DEFINITIONS

# == Executables

AJC = ajc
AJC_ARGS = -1.6
AJDOC = ajdoc

JAVAC = javac
JAVAC_ARGS = 

JVM = java
JVM_ARGS = -server

CATALINA_HOME = /opt/local/share/java/tomcat6

# == directories used througout this makefile

DIR_SRC = src
DIR_BIN = web/WEB-INF/classes
DIR_APIDOC = doc/public
DIR_DEVDOC = doc/private
DIR_OTHERS = doc

DIR_LIB = web/WEB-INF/lib

LIBS = ant.jar BCEL.jar junit-4.8.2.jar regexp.jar xercesImpl.jar aspectjrt.jar postgresqlDriver.jar runtime.jar xml-apis.jar


# the following is a work around to get a single space into a variable
# a single space is needed in the than following rule
space = $(empty) $(empty)
# a class path is calculated which contains the original classpath and libraries from DIR_LIB
CP = $(subst $(space),:,$(sort $(shell find $(DIR_LIB) -type f -name '*.jar' 2> /dev/null) $(subst :, ,$(CLASSPATH)))):$(CATALINA_HOME)/lib/servlet-api.jar

# == gather locations of files
# gather Java sources
JAVA_SOURCES = $(shell find $(DIR_SRC) -type f -name '*.java')
# filter out package-info.java-files from JAVA_SOURCES
JAVA_CLASS_SOURCES = $(filter-out %/package-info.java,$(JAVA_SOURCES))
# gather java package-info.java-files in another variable (import for documentor)
JAVA_PACKAGE_INFOS = $(filter %/package-info.java,$(JAVA_SOURCES))

# gather AspectJ-sources
AJ_SOURCES = $(shell find $(DIR_SRC) -type f -name '*.aj')

# prepare qualified names from path-info
JAVA_TESTS = $(subst /,.,$(subst $(DIR_JAVATESTS)/,,$(patsubst %.java,%,$(JAVA_TESTS_SRC))))

ALL_SOURCES = $(JAVA_SOURCES) $(AJ_SOURCES)

ALL_CLASS_SOURCES = $(JAVA_CLASS_SOURCES) $(AJ_SOURCES)
CLASSES = $(subst $(DIR_SRC)/,$(DIR_BIN)/,$(patsubst %.java,%.class,$(ALL_CLASS_SOURCES)))

# = ACTUAL TARGETS

# == rules actually building something binary / bytecoded

# Builds the whole project. Build will always be a clean one since AspectJ
# needs to recompile everything everytime (okay -interactive is available).
all:
	$(AJC) $(AJC_ARGS) -cp $(CP) -d $(DIR_BIN) -sourceroots $(DIR_SRC)


# == rules building documentation

# generates all documentation
# see http://www.gnu.org/software/automake/manual/make/Standard-Targets.html
html: devdoc apidoc

# creates internal documentation devels may need to understand the code
devdoc:
	$(AJDOC) -cp $(CP) -d $(DIR_DEVDOC) -private $(ALL_SOURCES) $(AJC_ARGS)
	rm -rf ajdocworkingdir

# created documenatation of our public api that is needed to extend
# the application.
apidoc:
	$(AJDOC) -cp $(CP) -d $(DIR_APIDOC) -public $(ALL_SOURCES) $(AJC_ARGS)
	rm -rf ajdocworkingdir

# == rules that simply execute an application

# this one is used to run the sandpit-HelloWorld-application
# which is just a demonstration of AspectJ
run-sandpit:
	@$(JVM) $(JVM_ARGS) -cp $(CP):$(DIR_BIN) de.fu.sandpit.HelloWorld


# == rules for setting up a development environment
devel: $(LIBS)

%.jar:
	@mkdir -p $(DIR_LIB)
	wget -P $(DIR_LIB) http://page.mi.fu-berlin.de/scravy/score/$@

clean-libs:
	rm -rf $(DIR_LIB)


# == other rules

# see http://www.gnu.org/software/automake/manual/make/Phony-Targets.html
.PHONY: all html clean maintainer-clean devdoc hackerdoc apidoc tests webtests javatests

# for clean, maintainer-clean (but also all, html, etc.)
# see http://www.gnu.org/software/automake/manual/make/Standard-Targets.html
clean:
	rm -rf $(DIR_BIN) $(DIR_JAVATESTSBIN)

# this one removes nearly anything which could be built using this Makefile
maintainer-clean:
	rm -rf $(DIR_BIN) $(DIR_JAVATESTSBIN) $(DIR_APIDOC) $(DIR_DEVDOC) $(DIR_WEBDOC) $(DIR_OTHERS)

