XML_FILES = $(wildcard src/*.xml)
PDF_FILES = $(patsubst src/%.xml,pdf/%.pdf,$(XML_FILES))

pdf: $(PDF_FILES) use-cases.pdf

use-cases.pdf: use-cases.tex
	pdflatex $<
	pdflatex $<
	rm -f *.aux *.log *.toc *.out use-cases.tex use-cases.xml

use-cases.tex: use-cases.xml
	xsltproc -o $@ use-cases-latex.xsl $<

use-cases.xml: $(XML_FILES)
	echo '<use-case-collection xmlns="http://technodrom.scravy.de/2010/use-case">' > $@
	cat $(XML_FILES) >> $@
	echo '</use-case-collection>' >> $@

pdf/%.pdf: pdf/%.tex
	pdflatex -output-directory pdf $<
	rm -f pdf/*.aux pdf/*.log pdf/*.toc pdf/*.out

pdf/%.tex: src/%.xml
	xsltproc -o $@ use-cases-latex.xsl $<

clean:
	rm -f pdf/* use-cases.pdf

.PHONY: clean
