JAVAC	= javac
JAVA	= java
JAVADOC	= javadoc
SRCDIR	= src
BINDIR	= bin
DOCDIR	= doc
JAVACOPTS	= -Xlint:unchecked
JAVAOPTS	= ''
PACKAGES	= model view controller xml

SOURCES	= $(wildcard $(SRCDIR)/*/*.java)
OBJS	= $(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES))

all: create_bin_dir $(OBJS)

run: all
	$(JAVA) $(JAVAOPTS) -classpath $(BINDIR) view.Main -d xml/test.xml

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) $(JAVACOPTS) -d $(BINDIR) -sourcepath $(SRCDIR) $^

javadoc: $(SOURCES) create_doc_dir
	$(JAVADOC) -private -sourcepath $(SRCDIR) -classpath $(BINDIR) -d $(DOCDIR) $(PACKAGES)

create_bin_dir:
	mkdir -p $(BINDIR)
	
create_doc_dir:
	mkdir -p $(DOCDIR)

.PHONY: clean

clean:
	rm -rf $(BINDIR)
	rm -rf $(SRCDIR)/*.class
	rm -rf $(SRCDIR)/*/*.class

