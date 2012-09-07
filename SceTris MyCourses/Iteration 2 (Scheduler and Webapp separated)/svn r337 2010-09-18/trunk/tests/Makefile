# Author: Julian

DIR_SRC = src
DIR_BIN = bin
DIR_LIB = ../web/WEB-INF/lib

TESTS_SRC = $(shell find $(DIR_SRC) -type f -name '*.java')
TESTS_CLASSES = $(patsubst %.java,%.class,$(TESTS_SRC))
TESTS = $(subst /,.,$(subst $(DIR_SRC)/,,$(patsubst %.java,%,$(DIR_SRC))))

CP = $(subst $(space),:,$(sort $(shell find $(DIR_LIB) -type f -name '*.jar' 2> /dev/null) $(subst :, ,$(CLASSPATH))))


all: $(TESTS_CLASSES)

%.class: %.java
	@mkdir -p $(DIR_JAVATESTSBIN)
	$(JAVAC) $(JAVAC_ARGS) -cp $(CP):$(DIR_BIN) -d $(DIR_BIN) $(DIR_SRC)

# build testsl
tests: $(JAVA_TESTS_SRC)
	

# execute tests	
report:
	$(JVM) $(JVM_ARGS) -cp $(CP):$(DIR_JAVATESTSBIN):$(DIR_BIN) org.junit.runner.JUnitCore $(JAVA_TESTS)

