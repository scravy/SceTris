<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">

	<h2>Change current AT</h2>
	<xsl:variable name="cat" select="//vars:currentAT/item:currentAT" />
	<p>Current :: <b><xsl:value-of select="$cat/@name"/></b></p>
	<form method="post">
		<xsl:call-template name="input-alternatives">
			<xsl:with-param name="id" select="'foobar.at'" />
			<xsl:with-param name="label" select="'Academic Term'" />
			<xsl:with-param name="options">
				<xsl:for-each select="vars:ats/item:ats">
					<option value="{@id}">
						<xsl:value-of select="@name" />
					</option>
				</xsl:for-each>
			</xsl:with-param>
		</xsl:call-template>
	<p class="formControls">
		<input type="submit" value="submit" name="foobar.but_commit" />
	</p>
	
	<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.ChangeCurrentAcademicTerm"/>
	<input type="hidden" name="~formName" value="foobar"/>	
	</form>
</xsl:template>


<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

<xsl:template mode="dep" match="text()" />

</xsl:transform>
