<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:exsl="http://exslt.org/common"
	xmlns:str="http://exslt.org/strings"
	xmlns:uc="http://technodrom.scravy.de/2010/use-case">

<xsl:output method="text" />

<xsl:template name="header">\documentclass[a4paper,10pt]{scrartcl}
\usepackage[T1]{fontenc}
\usepackage[utf8x]{inputenc}
\usepackage{amsmath}
\usepackage[a4paper]{geometry}
\usepackage{amssymb}
\usepackage{graphicx}
\usepackage{booktabs}
\usepackage{longtable}
\usepackage{url}
\usepackage[usenames,dvipsnames]{color}
\usepackage[colorlinks,pdfpagelabels,pdfstartview = FitH,bookmarksopen = true,bookmarksnumbered = true,linkcolor=black,plainpages = false,hypertexnames = false,citecolor = black,urlcolor=black] {hyperref}
\setlength{\parindent}{0cm}
\setlength{\defaultaddspace}{1pt}
\setcounter{secnumdepth}{0}

\title{Use Cases<xsl:call-template name="category" />}
\author{David Bialik, Julian Fleischer, Hagen Mahnke,\\ Konrad Reiche, André Zoufahl}

\begin{document}
\sffamily

\maketitle

\begin{center}
</xsl:template>

<xsl:template name="footer">
\end{center}

\end{document}
</xsl:template>

<xsl:template name="use-case">
	<xsl:param name="with-title" select="boolean(1)" />\begin{tabular}{|c|p{0.69\textwidth}|}
<xsl:if test="$with-title">\hline
 &amp; <xsl:call-template name="title" />\\</xsl:if>
\hline
Actors &amp; <xsl:call-template name="actors" /> \\
\hline
Scope &amp; <xsl:call-template name="scope" /> \\
\hline
Precondition &amp; <xsl:call-template name="pre" /> \\
\hline 
Postcondition &amp; <xsl:call-template name="post" /> \\
\hline
Postcondition on Success &amp; <xsl:call-template name="success" /> \\
\hline
Basic Course of Events &amp; <xsl:call-template name="events" /> \\
\hline
Alternative Paths &amp; <xsl:call-template name="alternate" /> \\
\hline
Open Questions &amp; <xsl:call-template name="questions" /> \\
\hline
Solved issues &amp; <xsl:call-template name="solved" /> \\
\hline
Implementation Notes &amp; <xsl:call-template name="notes" /> \\
\hline
Implementation Status &amp; <xsl:call-template name="status" /> \\
\hline
\end{tabular}
</xsl:template>

<xsl:template match="/uc:use-case-collection">
	<xsl:call-template name="header" />
	<xsl:text>&#xA;\pdfbookmark[1]{Contents}{toc}
\tableofcontents&#xA;</xsl:text>
	<xsl:for-each select="uc:use-cases">
		<xsl:sort select="uc:category" />
		<xsl:text>&#xA;\section{Category: </xsl:text>
		<xsl:apply-templates mode="latex" select="uc:category" />
		<xsl:text>}&#xA;&#xA;</xsl:text>
		<xsl:apply-templates mode="collection" select="uc:use-case" />
	</xsl:for-each>
	<xsl:if test="uc:use-case">
		<xsl:text>&#xA;\section{Uncategorized use cases}&#xA;</xsl:text>
		<xsl:apply-templates mode="collection" select="uc:use-case" />
	</xsl:if>
	<xsl:call-template name="footer" />
</xsl:template>

<xsl:template mode="collection" match="uc:use-case">
	<xsl:text>\subsection{</xsl:text>
	<xsl:value-of select="position()" />
	<xsl:text>. </xsl:text>
	<xsl:apply-templates mode="latex" select="uc:title" />
	<xsl:text>}&#xA;</xsl:text>
	<xsl:call-template name="use-case">
		<xsl:with-param name="with-title" select="boolean(0)" />
	</xsl:call-template>
</xsl:template>

<xsl:template match="/uc:use-cases">
	<xsl:call-template name="header" />
	<xsl:for-each select="uc:use-case">
		<xsl:if test="position() > 1">
			<xsl:text>\vskip 1ex&#xA;</xsl:text>
		</xsl:if>
		<xsl:call-template name="use-case" />
		<xsl:text>&#xA;</xsl:text>
	</xsl:for-each>
	<xsl:call-template name="footer" />
</xsl:template>

<xsl:template match="/uc:use-case">
	<xsl:call-template name="header" />
	<xsl:call-template name="use-case" />
	<xsl:call-template name="footer" />
</xsl:template>

<xsl:template name="actors">
	<xsl:for-each select="uc:actor">
		<xsl:if test="position() > 1">, </xsl:if>
		<xsl:apply-templates mode="latex" />
	</xsl:for-each>
</xsl:template>

<xsl:template name="scope">
	<xsl:apply-templates mode="latex" select="uc:scope" />
</xsl:template>

<xsl:template name="pre">
	<xsl:apply-templates mode="latex" select="uc:pre | uc:pre-condition" />
</xsl:template>

<xsl:template name="post">
	<xsl:apply-templates mode="latex" select="uc:post | uc:post-condition" />
</xsl:template>

<xsl:template name="success">
	<xsl:apply-templates mode="latex" select="uc:success | uc:on-success" />
</xsl:template>

<xsl:template name="events">
	<xsl:if test="not(uc:ev | uc:action | uc:event | uc:act)">-</xsl:if>
	<xsl:for-each select="uc:ev | uc:action | uc:event | uc:act">
		<xsl:text>&#xA;</xsl:text>
		<xsl:value-of select="concat('\textbf{', position(), '.', '} ')" />
		<xsl:apply-templates mode="latex" />
		<xsl:text>&#xA;</xsl:text>
	</xsl:for-each>
</xsl:template>

<xsl:template name="alternate">
	<xsl:if test="not(.//uc:or | .//uc:else)">-</xsl:if>
	<xsl:for-each select="uc:ev | uc:action | uc:event | uc:act">
		<xsl:variable name="pos" select="position()" />
		<xsl:for-each select="uc:or | uc:else">
			<xsl:text>&#xA;</xsl:text>
			<xsl:value-of select="concat('\textbf{', $pos, substring('abcdefghijklmnopqrstuvxwyz', position(), 1), '.} ')" />
			<xsl:apply-templates mode="latex" />
			<xsl:text>&#xA;</xsl:text>
		</xsl:for-each>
	</xsl:for-each>
</xsl:template>

<xsl:template name="title">
	<xsl:text>\textbf{</xsl:text>
	<xsl:apply-templates mode="latex" select="uc:title" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template name="notes">
	<xsl:if test="not(uc:note)">-</xsl:if>
	<xsl:for-each select="uc:note">
		<xsl:text>&#xA;\textbullet{} </xsl:text>
		<!-- <xsl:value-of select="concat('\textbf{(', position(), ')} ')" /> -->
		<xsl:apply-templates mode="latex" />
		<xsl:text>&#xA; </xsl:text>
	</xsl:for-each>
</xsl:template>

<xsl:template name="status">
	<xsl:if test="not(uc:status | uc:news)">-</xsl:if>
	<xsl:for-each select="uc:status | uc:news">
		<xsl:text>&#xA;\textbullet{} </xsl:text>
		<xsl:apply-templates mode="latex" />
		<xsl:text>&#xA;</xsl:text>		
	</xsl:for-each>
</xsl:template>

<xsl:template name="category">
	<xsl:if test="uc:category">
		<xsl:text>: \emph{</xsl:text>
		<xsl:apply-templates mode="latex" select="uc:category" />
		<xsl:text>}</xsl:text>
	</xsl:if>
	<xsl:if test="not(uc:category)">
		<xsl:text> – Project Scetris</xsl:text>
	</xsl:if>
</xsl:template>

<xsl:template name="questions">
	<xsl:if test="not(uc:question[not(uc:answer) and not(uc:a)] | uc:q[not(uc:answer) and not(uc:a)])">-</xsl:if>
	<xsl:for-each select="uc:question[not(uc:answer) and not(uc:a)] | uc:q[not(uc:answer) and not(uc:a)]">
		<xsl:text>&#xA;</xsl:text>
		<xsl:text>\textbf{Q:} </xsl:text>
		<xsl:apply-templates mode="latex" />
		<xsl:text>&#xA;</xsl:text>
	</xsl:for-each>
</xsl:template>

<xsl:template name="solved">
	<xsl:if test="not(uc:question[uc:answer or uc:a] | uc:q[uc:answer or uc:a])">-</xsl:if>
	<xsl:for-each select="uc:question[uc:answer or uc:a] | uc:q[uc:answer or uc:a]">
		<xsl:text>&#xA;</xsl:text>
		<xsl:text>\textbf{Q:} </xsl:text>
		<xsl:apply-templates mode="latex" />
		<xsl:for-each select="uc:answer | uc:a">
			<xsl:text>&#xA;&#xA;\textbf{A:} </xsl:text>
			<xsl:apply-templates mode="latex" />
		</xsl:for-each>
		<xsl:text>&#xA;</xsl:text>
	</xsl:for-each>
</xsl:template>

<xsl:template mode="latex" match="text()">
	<xsl:choose>
		<xsl:when test="function-available('str:replace')">
			<xsl:value-of select="translate(str:replace(str:replace(str:replace(str:replace(str:replace(str:replace(str:replace(str:replace(str:replace(str:replace(., '\', '\textbackslash '), '&amp;', '\&amp;'), '}', '\}'), '{', '\{'), '$', '\$'), '^', '\^{}'), '~', '\~{}'), '_', '\_'), '%', '\%'), '#', '\#'),  '&#xA;', '')" />
		</xsl:when>
		<xsl:otherwise>
			<xsl:call-template name="str:replace">
				<xsl:with-param name="string" select="translate(string(.), '&#xA;', '')" />
				<xsl:with-param name="search">
					<string>\</string>
					<string>&amp;</string>
					<string>{</string>
					<string>}</string>
					<string>$</string>
					<string>^</string>
					<string>~</string>
					<string>_</string>
					<string>%</string>
					<string>#</string>
				</xsl:with-param>
				<xsl:with-param name="replace">
					<string>\textbackslash </string>
					<string>\&amp;</string>
					<string>\{</string>
					<string>\}</string>
					<string>\$</string>
					<string>\^{}</string>
					<string>\~{}</string>
					<string>\_</string>
					<string>\%</string>
					<string>\#</string>
				</xsl:with-param>
			</xsl:call-template>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template mode="latex" match="uc:nl | uc:br">
	<xsl:text>\\</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:verbatim">
	<xsl:text>\begin{verbatim}</xsl:text>
	<xsl:value-of select="." />
	<xsl:text>\end{verbatim}</xsl:text>
</xsl:template>
<xsl:template mode="latex" match="uc:b | uc:bf">
	<xsl:text>\textbf{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:u | uc:underline">
	<xsl:text>\underline{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:sc | uc:caps">
	<xsl:text>\textsc{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:sl">
	<xsl:text>\textsl{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:it | uc:i">
	<xsl:text>\textit{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:tt | uc:code">
	<xsl:text>\texttt{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:em">
	<xsl:text>\emph{</xsl:text>
	<xsl:apply-templates mode="latex" />
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template mode="latex" match="uc:x | uc:tex">
	<xsl:value-of select="." />
</xsl:template>

<xsl:template mode="latex" match="uc:a | uc:answer | uc:ev | uc:action | uc:event | uc:act | uc:or | uc:else" />

<xsl:template mode="latex" match="uc:m">
	<xsl:text>$</xsl:text>
	<xsl:value-of select="." />
	<xsl:text>$</xsl:text>
</xsl:template>


<xsl:template name="str:replace">
	<xsl:param name="string" select="''" />
   <xsl:param name="search" select="/.." />
   <xsl:param name="replace" select="/.." />
   <xsl:choose>
      <xsl:when test="not($string)" />
      <xsl:when test="not($search)">
        <xsl:value-of select="$string" />
      </xsl:when>
      <xsl:when test="function-available('exsl:node-set')">
         <!-- this converts the search and replace arguments to node sets
              if they are one of the other XPath types -->
         <xsl:variable name="search-nodes-rtf">
           <xsl:copy-of select="$search" />
         </xsl:variable>
         <xsl:variable name="replace-nodes-rtf">
           <xsl:copy-of select="$replace" />
         </xsl:variable>
         <xsl:variable name="replacements-rtf">
            <xsl:for-each select="exsl:node-set($search-nodes-rtf)/node()">
               <xsl:variable name="pos" select="position()" />
               <replace search="{.}">
                  <xsl:copy-of select="exsl:node-set($replace-nodes-rtf)/node()[$pos]" />
               </replace>
            </xsl:for-each>
         </xsl:variable>
         <xsl:variable name="sorted-replacements-rtf">
            <xsl:for-each select="exsl:node-set($replacements-rtf)/replace">
               <xsl:sort select="string-length(@search)" data-type="number" order="descending" />
               <xsl:copy-of select="." />
            </xsl:for-each>
         </xsl:variable>
         <xsl:call-template name="str:_replace">
            <xsl:with-param name="string" select="$string" />
            <xsl:with-param name="replacements" select="exsl:node-set($sorted-replacements-rtf)/replace" />
         </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
         <xsl:message terminate="yes">
            ERROR: template implementation of str:replace relies on exsl:node-set().
         </xsl:message>
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="str:_replace">
  <xsl:param name="string" select="''" />
  <xsl:param name="replacements" select="/.." />
  <xsl:choose>
    <xsl:when test="not($string)" />
    <xsl:when test="not($replacements)">
      <xsl:value-of select="$string" />
    </xsl:when>
    <xsl:otherwise>
      <xsl:variable name="replacement" select="$replacements[1]" />
      <xsl:variable name="search" select="$replacement/@search" />
      <xsl:choose>
        <xsl:when test="not(string($search))">
          <xsl:value-of select="substring($string, 1, 1)" />
          <xsl:copy-of select="$replacement/node()" />
          <xsl:call-template name="str:_replace">
            <xsl:with-param name="string" select="substring($string, 2)" />
            <xsl:with-param name="replacements" select="$replacements" />
          </xsl:call-template>
        </xsl:when>
        <xsl:when test="contains($string, $search)">
          <xsl:call-template name="str:_replace">
            <xsl:with-param name="string" select="substring-before($string, $search)" />
            <xsl:with-param name="replacements" select="$replacements[position() > 1]" />
          </xsl:call-template>      
          <xsl:copy-of select="$replacement/node()" />
          <xsl:call-template name="str:_replace">
            <xsl:with-param name="string" select="substring-after($string, $search)" />
            <xsl:with-param name="replacements" select="$replacements" />
          </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
          <xsl:call-template name="str:_replace">
            <xsl:with-param name="string" select="$string" />
            <xsl:with-param name="replacements" select="$replacements[position() > 1]" />
          </xsl:call-template>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

</xsl:transform>