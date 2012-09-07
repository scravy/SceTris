
MANAGER=RelationManager
PARAMS=--stringparam targetPackage de.fu.scetris.data --stringparam managerName ${MANAGER}
RELATIONS=${shell xsltproc --stringparam javaize yes lib/listRelations.xsl Relations.xml}
JAVAFILES=${patsubst %,java/%.java,${RELATIONS}}

all: html sql java #pdf svg

java: ${JAVAFILES} java/${MANAGER}.java

java/${MANAGER}.java: Relations.full.xml autoTemplate.xsl
	xsltproc --stringparam relation '#manager' ${PARAMS} autoTemplate.xsl Relations.full.xml > $@

java/%.java: Relations.full.xml autoTemplate.xsl
	xsltproc --stringparam relation ${*F} ${PARAMS} autoTemplate.xsl Relations.full.xml > $@

pgsql: create_all.sql
	psql84 -1 -f create_all.sql -d postgres > psql-install.log
	@echo `cat psql-install.log | grep 'CREATE TABLE' | wc -l` tables created
	@echo `cat psql-install.log | grep 'CREATE FUNCTION' | wc -l` functions created
	rm psql-install.log

install: java
	mv java/*.java ../src/de/fu/scetris/data

autoTemplate.xsl: lib/java2.metaTemplate.xsl templates/java2.xml
	xsltproc lib/java2.metaTemplate.xsl templates/java2.xml > autoTemplate.xsl

pdf: erm.pdf uml.pdf

erm.pdf: erm.dot
	neato -Gepsilon=.00000001 erm.dot -Tpdf -oerm.pdf
	
uml.pdf: uml.dot
	echo fun
	#twopi uml.dot -Tpdf -ouml.pdf
	
svg: erm.svg uml.svg 

erm.svg: erm.dot
	neato erm.dot -Tsvg -oerm.svg
	
uml.svg: uml.dot
	dot uml.dot -Tsvg -ouml.svg
	
erm.dot: Relations.full.xml lib/erm.xsl
	xsltproc lib/erm.xsl Relations.full.xml > erm.dot
	
uml.dot: Relations.full.xml lib/uml.xsl
	xsltproc lib/uml.xsl Relations.full.xml > uml.dot

sql: create_all.sql

create_all.sql: create.sql create_procedures.sql
	cat create.sql create_procedures.sql > create_all.sql

create.sql: Relations.full.xml lib/sql.xsl
	xsltproc lib/sql.xsl Relations.full.xml > $@

html: Relations.xhtml

Relations.xhtml: Relations.full.xml lib/xhtml.xsl
	xsltproc lib/xhtml.xsl Relations.full.xml > $@

Relations.full.xml: Relations.xml lib/prepare.xsl
	xsltproc lib/prepare.xsl Relations.xml > $@

.PHONY: all html pdf svg clean sql install java pgsql

clean:
	rm -rf Relations.full.xml Relations.xhtml create.sql create_all.sql *.java *.log *.pdf java/* autoTemplate.xsl *.o *.hi javalx

