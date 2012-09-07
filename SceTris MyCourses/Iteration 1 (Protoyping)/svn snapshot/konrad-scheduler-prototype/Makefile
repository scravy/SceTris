JAVAC	= javac
JAVA	= java
SRCDIR	= src
BINDIR	= bin

SOURCES	= $(wildcard $(SRCDIR)/*/*.java)
OBJS	= $(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES))

all: create_bin_dir $(OBJS)

run: all
	$(JAVA) -classpath $(BINDIR) view.Main -d xml/test.xml

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR) -sourcepath $(SRCDIR) $^

create_bin_dir:
	mkdir -p $(BINDIR)

.PHONY: clean

clean:
	rm -rf $(BINDIR)
	rm -rf $(SRCDIR)/*.class
	rm -rf $(SRCDIR)/*/*.class

