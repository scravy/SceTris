<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="vars:step/text() = 1">
	<form method="post">
		<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.NewCourseElementInstance"/>
		<input type="hidden" name="~formName" value="xkcd"/>
		<xsl:call-template name="input-text">
			<xsl:with-param name="id" select="'xkcd.count'"/>
			<xsl:with-param name="label" select="'Number of new Elements'" />
		</xsl:call-template>		
		<p class="formControls">
			<input type="submit" value="submit" name="xkcd.commit" />
		</p>
	</form>
	</xsl:if>
	
	<xsl:if test="vars:step/text() = 2">
		<xsl:variable name="mode" select="vars:mode/text()"/>
		<xsl:if test="$mode = 'firstSetup'">
		<form method="post">
			<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.NewCourseElementInstanceMetadata"/>
			<input type="hidden" name="~formName" value="xkcd"/>
			<input type="hidden" name="xkcd.mode" value="$mode"/>
			
			<!-- n*m -->
			<xsl:call-template name="setFirstCEIdata">
				<xsl:with-param name="count" select="//vars:count/text()"/>
			</xsl:call-template>		
			
			
			<p class="formControls">
				<input type="submit" value="submit" name="xkcd.commit" />
			</p>
		</form>
		</xsl:if>
		
		<xsl:if test="$mode = 'changeExisting'">
			<form method="post">
				<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.EditCourseElementInstanceMetadata"/>
				<input type="hidden" name="~formName" value="xkcd"/>
				<input type="hidden" name="xkcd.mode" value="{$mode}"/>
				
				<!-- n*m -->
				<xsl:for-each select="vars:ceis/item:ceis">
					<xsl:variable name="qwerty" select="@id"/>		
					<input type="hidden" name="xkcd.ceiid" value="{@id}"/>
					<xsl:call-template name="setFirstCEIdata">
						<xsl:with-param name="count" select="0"/>
						<xsl:with-param name="ce_prop" select="@courseElement"/>
						<xsl:with-param name="lec_prop" select="@lecturer"/>
						<xsl:with-param name="dura_prop" select="@duration"/>
						
					</xsl:call-template>	
					
					<xsl:for-each select="//vars:posfeature/item:posfeature">
						<p class="formField">
							<label for="@name">
								<xsl:value-of select="@name" />
							</label>
							<input id="@name" class="text-input input-numeric" type="text" name="newRoom.featureQuantity" value="0" />
							-
							<input id="@name" class="text-input input-numeric" type="text" name="newRoom.featureQuantity" value="0" />
						</p>
						<!--<xsl:call-template name="input-text">
							<xsl:with-param name="label" select="@name" />
							 <xsl:if test="">
							<xsl:if test="//vars:needs/item:needs[current()/@id = @feature]">
								<xsl:with-param name="value" select="//vars:needs/item:needs[$querty = @elementInstance]/@quantityBetter" />
							</xsl:if> 
						</xsl:call-template>
						-->
					</xsl:for-each>
				</xsl:for-each>
				<p class="formControls">
					<input type="submit" value="submit" name="xkcd.commit" />
				</p>
			</form>

		</xsl:if>
	</xsl:if>

</xsl:template>

<xsl:template name="setFirstCEIdata">
	<xsl:param name="count"/>
	<xsl:param name="ce_prop">
		<xsl:value-of select="-1"/>
	</xsl:param>
	<xsl:param name="lec_prop">
		<xsl:value-of select="-1"/>
	</xsl:param>	
	<xsl:param name="dura_prop">
			<xsl:value-of select="-1"/>
	</xsl:param>
	<h3><xsl:value-of select="concat($ce_prop, ' :: ', $lec_prop )"/></h3>
			<xsl:variable name="gid" select="generate-id(.)"/>
			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="$gid" />
				<xsl:with-param name="name" select="'xkcd.courseelement'" />
				<xsl:with-param name="label" select="'Courseelement'" />
				<xsl:with-param name="options">
					<xsl:for-each select="//vars:courseelements/item:courseelements">
						<option value="{@id}">
							<xsl:if test="@id = $ce_prop">
								<xsl:attribute name="selected">
									<xsl:value-of select="'selected'"/>
								</xsl:attribute>
							</xsl:if>						
							<xsl:value-of select="@name" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>
			
			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="$gid" />
				<xsl:with-param name="name" select="'xkcd.lecturer'" />
				<xsl:with-param name="label" select="'Lecturer'" />
				<xsl:with-param name="options">
					<xsl:for-each select="//vars:lecturer/item:lecturer">
						<option value="{@id}">
							<xsl:if test="@id = $lec_prop">
								<xsl:attribute name="selected">
									<xsl:value-of select="'selected'"/>
								</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="concat(@lastName,', ',@firstName)" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>
			
			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="$gid" />
				<xsl:with-param name="name" select="'xkcd.duration'" />
				<xsl:with-param name="label" select="'Duration'" />
				<xsl:with-param name="options">
					<xsl:for-each select="//vars:durations/item:durations">
						<option value="{text()}">
							<xsl:if test="text() = $dura_prop">
								<xsl:attribute name="selected">
									<xsl:value-of select="'selected'"/>
								</xsl:attribute>
							</xsl:if>
							<xsl:value-of select="concat(text(),' Timeslots')" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>
			
	<xsl:if test="$count &gt; 1">
		<xsl:call-template name="setFirstCEIdata">
			<xsl:with-param name="count" select="$count - 1"/>
		</xsl:call-template>
	</xsl:if>
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
