<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	exclude-result-prefixes="vars item lang form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:call-template name="intro" />

	<!-- The following is one single call to the form-builder template,
	     which will set up us the form! -->
	<xsl:call-template name="form-builder">
		<!-- it gets two parameters, the name of the form as well as stuff to mix in -->
		<xsl:with-param name="form">builder</xsl:with-param>
		<xsl:with-param name="mixin" select="document('')/*/form:builder/*" />
		<!-- in this case, the mixed in stuff is taken from the stylesheet itself
		     via document("") - which refers to the current file. See //form:builder -->
	</xsl:call-template>
</xsl:template>

<!-- The following contains additional code to include to the form -->
<form:builder>
	<form:additional pos="1.0">
		<!-- This is hooked into at position 1.0 - by convention, the elements are
		     sorted by floating point numbers, where the part before the dot refers
		     to the group and the part after to the sorting within that group.
		     This means there are form controls having @pos 1.1, 1.2, ... and 1.0 is
		     inserted before 1.1 (since 1 is less than 1.1) -->
		<h2>
			<!-- Since we're actually in some kind of meta-mode here (we can not make
			     use of XSL-T here, since it is parsed using document("")) we are able
			     to pull in lang-strings much more easily using the lang:namespace -->
			<lang:new-user />
		</h2>
		<h3><lang:basic-info /></h3>
	</form:additional>
	
	<form:additional pos="2.0">
		<h3><lang:credentials /></h3>
	</form:additional>
	
	<form:additional pos="3.0">
		<h3><lang:misc /></h3>
	</form:additional>
</form:builder>

</xsl:transform>
