all: html create.sql

pdf:


svg:


sql: create.sql

create.sql: entities-full.xml sql.xsl
	xsltproc sql.xsl entities-full.xml > $@

html: relations.html

relations.html: entities-full.xml xhtml.xsl
	xsltproc xhtml.xsl entities-full.xml > $@

entities-full.xml: entities.xml prepare.xsl
	xsltproc prepare.xsl entities.xml > $@


.PHONY: all html pdf svg clean sql

clean:
	rm -rf entities-full.xml relations.html create.sql
