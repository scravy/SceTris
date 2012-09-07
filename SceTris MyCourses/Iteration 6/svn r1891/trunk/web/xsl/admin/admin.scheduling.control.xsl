<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

	<xsl:import href="common.xsl" />
	<xsl:import href="../lego/form-builder.xsl" />

	<xsl:template mode="content" match="vars:meta">
<!--		<xsl:call-template name="form-builder">
			<xsl:with-param name="form">quickSchedulingControl</xsl:with-param>
			<xsl:with-param name="hideOnSuccess" select="boolean(0)" />	
		</xsl:call-template> -->


		<form method="post">
			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="'foobar.department'" />
				<xsl:with-param name="label" select="'Department'" />
				<xsl:with-param name="options">
					<xsl:for-each select="vars:departments/item:departments">
						<option value="{@id}">
							<xsl:value-of select="@name" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>

			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="'foobar.academicTerm'" />
				<xsl:with-param name="label" select="'Academic Term'" />
				<xsl:with-param name="options">
					<xsl:for-each select="vars:academicTerms/item:academicTerms">
						<option value="{@id}">
							<xsl:value-of select="@name" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>
			
			<input type="hidden" name="~formType" value="de.fu.scetris.web.mods.forms.SchedulingControl"/>
			<input type="hidden" name="~formName" value="foobar"/>
			
			<p class="formControls">
			<xsl:choose>
				<!--
				<xsl:when test="//vars:status='ready'">
					<xsl:call-template name="input-submit">
						<xsl:with-param name="id" select="'start'"/>
						<xsl:with-param name="name" select="'command'"/>
						<xsl:with-param name="label" select="'start'"/>
					</xsl:call-template>
					<xsl:call-template name="input-submit">
						<xsl:with-param name="id" select="'stop'"/>
						<xsl:with-param name="name" select="'command'"/>
						<xsl:with-param name="label" select="'stop'"/>
						<xsl:with-param name="disabled" select="'disabled'"/>
					</xsl:call-template>				
				</xsl:when>
				-->
				
				<xsl:when test="//vars:status='ready'">
					<input type="submit" value="start" name="foobar.but_start" />
					<input type="submit" value="resume" name="foobar.but_resume" />
					<input disabled="disabled" type="submit" value="stop" name="foobar.but_stop" />
					<input type="submit" value="publish" name="foobar.but_publish" />
				</xsl:when>

				<xsl:when test="//vars:status='stopping'">
					<input disabled="disabled" type="submit" value="start" name="foobar.but_start" />
					<input disabled="disabled" type="submit" value="resume" name="foobar.but_resume" />
					<input disabled="disabled" type="submit" value="stop" name="foobar.but_stop" />
					<input disabled="disabled" type="submit" value="publish" name="foobar.but_publish" />
				</xsl:when>

				<xsl:when test="//vars:status='running'">
					<input disabled="disabled" type="submit" value="start" name="foobar.but_start" />
					<input disabled="disabled" type="submit" value="resume" name="foobar.but_resume" />
					<input type="submit" value="stop" name="foobar.but_stop" />
					<input disabled="disabled" type="submit" value="publish" name="foobar.but_publish" />
				</xsl:when>
			</xsl:choose>
			</p>
		</form>
		<br />
		<br />
<!--
		<p>
			Status:
			<xsl:value-of select="//vars:status" />
		</p>
		<p>
			<xsl:if test="//vars:scheduleScore">
				<xsl:variable name="hardC" select="vars:scheduleScore/item:scheduleScore/@numberOfHardConstraintsSatisfied"/>
				<xsl:variable name="hardCSum" select="vars:scheduleScore/item:scheduleScore/@numberOfHardConstraints"/>
				<xsl:variable name="softC" select="vars:scheduleScore/item:scheduleScore/@numberOfSoftConstraintsSatisfied"/>
				<xsl:variable name="softCSum" select="vars:scheduleScore/item:scheduleScore/@numberOfSoftConstraints"/>
				<xsl:variable name="hard_done" select="((550 * $hardC) div $hardCSum)" />
				<xsl:variable name="soft_done" select="((550 * $softC) div $softCSum)" />


				<xsl:variable name="hardCx" select="vars:scheduleScore/item:scheduleScore/@sumHardConstraintsSatisfied"/>
				<xsl:variable name="hardCSumx" select="vars:scheduleScore/item:scheduleScore/@sumHardConstraints"/>
				<xsl:variable name="softCx" select="vars:scheduleScore/item:scheduleScore/@sumSoftConstraintsSatisfied"/>
				<xsl:variable name="softCSumx" select="//vars:scheduleScore/item:scheduleScore/@sumSoftConstraints"/>
				<xsl:variable name="hardx_done" select="((550 * $hardCx) div $hardCSumx)" />
				<xsl:variable name="softx_done" select="((550 * $softCx) div $softCSumx)" />
				
				
				<xsl:if test="$hardCSum>0">
					<p>
					hard constraints:
					<xsl:value-of select="$hardC" />
					of
					<xsl:value-of select="$hardCSum" />
					<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {550-$hard_done}px; border: 1px solid #D1D1D1; border-left: {$hard_done}px solid #D1D1D1;"/>
				
					</p>
				</xsl:if>
				<xsl:if test="$softCSum>0">
					<p>
					soft constraints:
					<xsl:value-of select="$softC" />
					of
					<xsl:value-of select="$softCSum" />
					<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {550-$soft_done}px; border: 1px solid #D1D1D1; border-left: {$soft_done}px solid #D1D1D1;"/>
					</p>
				</xsl:if>
				
				<hr/>
				
				
				<xsl:if test="$hardCSumx>0">
					<p>
					hard constraints:
					<xsl:value-of select="$hardCx" />
					of
					<xsl:value-of select="$hardCSumx" />
					<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {550-$hardx_done}px; border: 1px solid #D1D1D1; border-left: {$hardx_done}px solid #D1D1D1;"/>
					</p>
				</xsl:if>
				<xsl:if test="$softCSumx>0">
					<p>				
					soft constraints:
					<xsl:value-of select="$softCx" />
					of
					<xsl:value-of select="$softCSumx" />
					<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {550-$softx_done}px; border: 1px solid #D1D1D1; border-left: {$softx_done}px solid #D1D1D1;"/>
					<br style="clear: both;"/>
					</p>			
				</xsl:if>	
				
			</xsl:if>
		</p>
-->		
	</xsl:template>

</xsl:transform>