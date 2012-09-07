<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns="http://www.w3.org/1999/xhtml" 
		xmlns:meta="http://technodrom.scravy.de/2010/data" 
		xmlns:item="http://technodrom.scravy.de/2010/item" 
		xmlns:ent="http://technodrom.scravy.de/2010/relations/c" 
		xmlns:dc="http://dublincore.org/documents/dcmi-namespace/" 
		xmlns:fn="http://www.w3.org/2005/xpath-functions" >

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" cdata-section-elements="style" />

<xsl:param name="lang" select="'en'" />

<!-- MAIN TEMPLATE -->
<xsl:template match="form" name="form_construct">
	<xsl:param name="action" />
	<xsl:param name="method" />
	<xsl:param name="name" />
	<xsl:param name="entity" />

	<form>
		<xsl:attribute name="name">
			<xsl:value-of select="$name" />
		</xsl:attribute>	
		<xsl:attribute name="action">
			<xsl:value-of select="$action" />
		</xsl:attribute>
		<xsl:attribute name="method">
			<xsl:value-of select="$method" />
		</xsl:attribute>	
		
		
		<!-- <xsl:apply-templates mode="getAttributesForEntity" select="document('../../meta/entities-full.xml')/entity[@name!=$entity]" /> -->
		<h3> Values for entity
			'<xsl:value-of select="$entity" />'
		</h3>
		
		<fieldset>
			<legend>source</legend>
				<xsl:apply-templates mode="entities" select="document('Relations.full.xml')/ent:relations/ent:entity[@name=$entity]" />
		</fieldset>

		<!-- <fieldset>
			<legend>relations</legend>
			<xsl:apply-templates mode="relations" select="document('Relations.full.xml')/ent:relations/ent:entity[@name=$entity and /*/@ref!='']" />
		</fieldset> -->
		
		<xsl:call-template name="form_buttons" />
	</form>
</xsl:template>

<xsl:template mode="relations" match="ent:attribute">
	<option><xsl:value-of select="'Daten'" /></option>
	<option><xsl:value-of select="'der Entität'" /></option>	
	<option><xsl:value-of select="@ref" /></option>	
	<option><xsl:value-of select="'müssen'" /></option>	
	<option><xsl:value-of select="'geladen werden'" /></option>
</xsl:template>


<xsl:template mode="entities" match="ent:attribute">
	<div>
		<xsl:choose>
			<xsl:when test="@hidden='true' or @type='timestamp'">
				<xsl:attribute name="class"><xsl:value-of select="'hidden'" /></xsl:attribute>
			</xsl:when>
			<xsl:otherwise>
				<xsl:attribute name="class"><xsl:value-of select="'xmltr'" /></xsl:attribute>
			</xsl:otherwise>
		</xsl:choose>
		
		
		<div>
			<label>
				<xsl:attribute name="for"><xsl:value-of select="@name" /></xsl:attribute>
				<xsl:if test="dc:title[@xml:lang=$lang]!=''">
					<xsl:attribute name="title"><xsl:value-of select="dc:title[@xml:lang=$lang]" /></xsl:attribute>
				</xsl:if>
				<xsl:value-of select="@name" />
			</label>
			<!-- hints for the inputfield -->
			<xsl:if test="dc:description[@xml:lang=$lang]!=''">
					<input type="button" value="?" class="helptrigger" />
					<span class="help">
						<xsl:value-of select="dc:description[@xml:lang=$lang]" />
					</span>
			</xsl:if>
		</div>
		
		<div>		
		<xsl:choose>
			<xsl:when test="@ref!=''">
				<!-- relations are dealed seperately -->
				<select>
					<xsl:apply-templates mode="relations" select="." />
				</select>
			</xsl:when>
			<xsl:when test="@type='integer' or @type='float' or @type='double' or @type='string'">
					<input>
						<xsl:attribute name="type"><xsl:value-of select="'text'" /></xsl:attribute>
						<xsl:attribute name="size"><xsl:value-of select="50" /></xsl:attribute>
						<xsl:attribute name="id"><xsl:value-of select="@name" /></xsl:attribute>
						<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
					</input>
					<xsl:if test="@default!=''">
						<xsl:attribute name="value"><xsl:value-of select="@default" /></xsl:attribute>
					</xsl:if>
			</xsl:when>
			<xsl:when test="@type='password'">
				<input>
					<xsl:attribute name="type"><xsl:value-of select="'password'" /></xsl:attribute>
					<xsl:attribute name="size"><xsl:value-of select="30" /></xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="@name" /></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
				</input>
				<input>
					<xsl:attribute name="type"><xsl:value-of select="'password'" /></xsl:attribute>
					<xsl:attribute name="size"><xsl:value-of select="30" /></xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="concat(@name,'_confirm')" /></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="concat(@name,'_confirm')" /></xsl:attribute>				
					<xsl:attribute name="class"><xsl:value-of select="'helptrigger'" /></xsl:attribute>				
				</input>
				<span class="help">
					<xsl:value-of select="'Re-enter your password here.'" />
				</span>
			</xsl:when>
			<xsl:when test="@type='date'">
					<input>
						<xsl:attribute name="type"><xsl:value-of select="'text'" /></xsl:attribute>
						<xsl:attribute name="size"><xsl:value-of select="20" /></xsl:attribute>
						<xsl:attribute name="id"><xsl:value-of select="@name" /></xsl:attribute>
						<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
						<xsl:attribute name="class"><xsl:value-of select="'helptrigger'" /></xsl:attribute>				
					</input>
					<span class="help">
						<xsl:value-of select="'Use format DD-MM-YYYY'" />
					</span>
			</xsl:when>
			<xsl:when test="@type='datetime'">
					<input>
						<xsl:attribute name="type"><xsl:value-of select="'text'" /></xsl:attribute>
						<xsl:attribute name="size"><xsl:value-of select="30" /></xsl:attribute>
						<xsl:attribute name="id"><xsl:value-of select="@name" /></xsl:attribute>
						<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
						<xsl:attribute name="class"><xsl:value-of select="'helptrigger'" /></xsl:attribute>				
					</input>
					<span class="help">
						<xsl:value-of select="'Use format DD-MM-YYYY HH:MM'" />
					</span>
			</xsl:when>
			<xsl:when test="@type='text'">
				<textarea>
					<xsl:attribute name="cols"><xsl:value-of select="50" /></xsl:attribute>
					<xsl:attribute name="rows"><xsl:value-of select="10" /></xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="@name" /></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
				</textarea>
			</xsl:when>		
			<xsl:when test="@type='boolean'">
				<input>
					<xsl:attribute name="type"><xsl:value-of select="'checkbox'" /></xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="@name" /></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
				</input>
			</xsl:when>				
			<xsl:when test="@type='timestamp'">
				&lt; hidden &gt;
			</xsl:when>	
			<xsl:otherwise>
				<xsl:value-of select="local-name()" />
			</xsl:otherwise>	
		</xsl:choose>
		</div>
		
	</div>
</xsl:template>



<xsl:template name="form_buttons">
	<input type="reset" name="reset" value="reset" />
	<input type="submit" name="submit" value="submit" />
</xsl:template>


<xsl:template match="@*|text()" mode="entities" />


</xsl:transform>