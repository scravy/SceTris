<?xml version="1.0" encoding="UTF-8"?>

<xsl:transform version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:rel="http://technodrom.scravy.de/2010/relations">

<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />

<xsl:param name="managerName" />

<xsl:template match="/rel:relations">

<project name="scetris/meta/temp" default="all">
	<target name="all">
		<xsl:for-each select="rel:entity | rel:relationship">
		
		<property name="{@name}-output" location="java/{@name}.java" />
		
		<exec executable="xsltproc">
			<arg value="-o" />
			<xsl:text disable-output-escaping="yes">
			&lt;arg value="${</xsl:text><xsl:value-of select="@name" /><xsl:text disable-output-escaping="yes">-output}" /&gt;
			</xsl:text>
			<!--<arg value="${{@name}-output}" />-->
			<arg value="--stringparam" />
			<arg value="relation" />
			<arg value="{@name}" />
			<arg value="--stringparam" />
			<arg value="targetPackage" />
			<arg value="de.fu.scetris.data" />
			<arg value="--stringparam" />
			<arg value="managerName" />
			<arg value="{$managerName}" />
			<arg value="autoTemplate.xsl" />
			<arg value="Relations.full.xml" />
		</exec>
		
		<!--<xslt in="Relations.full.xml" out="java/{@name}.java" style="autoTemplate.xsl">
			<param name="relation" expression="{@name}" />
			<param name="installer" expression="create.xml" />
			<param name="targetPackage" expression="de.fu.scetris.data" />
			<param name="managerName" expression="{$managerName}" />
		</xslt>-->
		</xsl:for-each>
	</target>
</project>

</xsl:template>

</xsl:transform>