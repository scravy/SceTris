JAVAC	= javac
JAVA	= java
JAVADOC	= javadoc
SRCDIR	= src
BINDIR	= bin
DOCDIR	= doc
JAVACOPTS	= -Xlint:unchecked
JAVAOPTS	= 
PACKAGES	= scheduler
SEED	= -47111337

SOURCES	= $(wildcard $(SRCDIR)/*/*.java)
OBJS	= $(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES))

all: create_bin_dir $(OBJS)

run: all
	$(JAVA) $(JAVAOPTS) -classpath $(BINDIR) scheduler.Client -qq

sample: all
	$(JAVA) $(JAVAOPTS) -classpath $(BINDIR) scheduler.Client -qq -s -2084076251537843021

verbose: all
	$(JAVA) $(JAVAOPTS) -classpath $(BINDIR) scheduler.Client

benchmark1: all
	$(JAVA) $(JAVAOPTS) -classpath $(BINDIR) scheduler.Benchmark -f ju1 -s $(SEED)

benchmark2: all
	$(JAVA) $(JAVAOPTS) -classpath $(BINDIR) scheduler.Benchmark -f ju2 -s $(SEED)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) $(JAVACOPTS) -d $(BINDIR) -sourcepath $(SRCDIR) $^

javadoc: $(SOURCES) create_doc_dir
	$(JAVADOC) -private -sourcepath $(SRCDIR) -classpath $(BINDIR) -d $(DOCDIR) $(PACKAGES)

create_bin_dir:
	mkdir -p $(BINDIR)
	
create_doc_dir:
	mkdir -p $(DOCDIR)

.PHONY: clean

cleandoc:
	rm -rf $(DOCDIR)

cleanbin:
	rm -rf $(BINDIR)

clean: cleandoc cleanbin
	rm -rf $(SRCDIR)/*.class
	rm -rf $(SRCDIR)/*/*.class
