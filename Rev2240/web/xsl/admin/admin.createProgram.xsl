<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<h2><xsl:value-of select="$mod-lang/createProgram" /></h2>
	<xsl:if test="vars:step/text() = 1 or vars:step/text() = 2">
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newProgram</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
	</xsl:call-template>
	</xsl:if>
	
	
	
	<xsl:if test="vars:step/text() = 4">
		Select Start and Enddates if different from defaultvalues
		Also change Mainlecturer (default is pm)
		<form method="post">
			<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.NewProgramStepMainLecturer"/>
			<input type="hidden" name="~formName" value="xkcd"/>
			<xsl:apply-templates />
			
			<p class="formControls">
				<input type="submit" value="submit" name="xkcd.commit" />
			</p>
		</form>
	</xsl:if>
</xsl:template>

<xsl:template match="item:courseinstance">
	<h3><xsl:value-of select="vars:instanceOf/item:instanceOf/@name"/>, <xsl:value-of select="vars:program/item:program/vars:academicTerm/item:academicTerm/@name" /></h3> 
	<xsl:variable name="gid" select="generate-id(.)"/>
	<xsl:variable name="myml" select="@mainLecturer" />
	
	<xsl:call-template name="input-hidden">
		<xsl:with-param name="name" select="'xkcd.ci'"/>
		<xsl:with-param name="value" select="@id" />
	</xsl:call-template>
	
	<xsl:call-template name="input-alternatives">
		<xsl:with-param name="id" select="$gid" />
		<xsl:with-param name="name" select="'xkcd.ml'" />
		<xsl:with-param name="label" select="'Mainlecturer'" />
		<xsl:with-param name="options">
			<xsl:for-each select="//vars:lecturer/item:lecturer">
				<option value="{@id}">
					<xsl:if test="@id = $myml">
						<xsl:attribute name="selected">
							<xsl:value-of select="'selected'"/>
						</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="concat(@lastName,', ',@firstName)" />
				</option>
			</xsl:for-each>
		</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<xsl:template mode="path" match="vars:meta">

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
