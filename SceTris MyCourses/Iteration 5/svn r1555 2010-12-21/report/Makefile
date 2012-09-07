summary_report.pdf: summary_report.tex content/*.tex
	pdflatex $< 
	bibtex $(basename $<) 
	pdflatex $< 
	pdflatex $<

clean:
	rm -f *.pdf *.aux *.bbl *.log *.blg *.toc *.out

.PHONY: clean
