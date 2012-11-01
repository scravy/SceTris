summary_report.pdf: summary_report.tex content/*.tex images/*.png images/*.pdf images/app/*.*
	pdflatex $< 
	bibtex $(basename $<) 
	pdflatex $< 
	pdflatex $<

tidy:
	rm -f *.aux *.bbl *.log *.blg *.toc *.out

clean:
	rm -f *.pdf *.aux *.bbl *.log *.blg *.toc *.out

.PHONY: clean tidy
