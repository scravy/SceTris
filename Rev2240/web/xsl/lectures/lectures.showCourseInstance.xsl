<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:variable name="ci" select="vars:ci/item:ci"/>
	<xsl:variable name="iof" select="$ci/vars:instanceOf/item:instanceOf"/>
	<xsl:variable name="ml"  select="$ci/vars:mainLecturer/item:mainLecturer"/>
	<xsl:variable name="pro" select="$ci/vars:program/item:program"/>
	
	<h1>
		<xsl:value-of select="$iof/@name"/>
		-
		<xsl:value-of select="$pro/vars:academicTerm/item:academicTerm/@name"/>
	</h1>	

	<!-- course instance info -->
	<dl>
		<dt>Timespan</dt>
		<dd><xsl:value-of select="$ci/@start"/> - <xsl:value-of select="$ci/@end"/></dd>
		
		<dt>Mainlecturer</dt>
		<dd><xsl:value-of select="concat($ml/@firstName,' ',$ml/@lastName)"/></dd>
	
		<dt>More infos to come</dt>
		<dd>probably</dd>
	</dl>
	<br />	
	<h3>Course dates</h3>
			
	<xsl:choose>
		<xsl:when test="vars:mode/text() = 'buildForm'">
			<form method="post">
				<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.Enrollment"/>
				<input type="hidden" name="~formName" value="foobar"/>	
				<input type="hidden" name="foobar.ci" value="{$ci/@id}"/>
		
		
				<xsl:apply-templates select="vars:ce_req/item:ce_req">
					<xsl:sort select="not(@required)"/>
					<xsl:with-param name="buildForm" select="1"/>
				</xsl:apply-templates>
			
				<p class="formControls">
					<input type="submit" value="submit" name="foobar.but_commit" />
				</p>
			</form>
		
		</xsl:when>
		<xsl:otherwise>
			<xsl:apply-templates select="vars:ce_req/item:ce_req">
				<xsl:sort select="not(@required)"/>
			</xsl:apply-templates>
			<p class="formControls">
				<a href="?action=enroll" class="abutton"><input type="button" value="enroll"/></a>
			</p>
		</xsl:otherwise>
	</xsl:choose>



		

	
</xsl:template>


<xsl:template match="item:ce_req">
	<xsl:param name="buildForm">
		<xsl:value-of select="0"/>
	</xsl:param>
	
		<table>
		<tr>
			<th colspan="6" style="background-color: #EDEDED;">
				<b><xsl:value-of select="vars:type/item:type/@name"/></b>
				Required :: <xsl:value-of select="@duration"/>
			</th>
		</tr>
		<tr>
			<th>Attend</th>
			<th>Date</th>
			<th>Location</th>
			<th>Type</th>
			<th>Duration</th>
			<th>Required</th>
		</tr>
		
		<xsl:apply-templates select="//vars:ceis/item:ceis[@courseElement = current()/@id]">
			<xsl:sort select="number(@startingTimeslot)" data-type="number"/>
			<xsl:with-param name="buildForm" select="$buildForm"/>
		</xsl:apply-templates>
		
		</table>

</xsl:template>




<xsl:template match="item:ceis">
	<xsl:param name="buildForm">
		<xsl:value-of select="0"/>
	</xsl:param>
	
			<xsl:variable name="foobar" select="@id"/>
			<tr>
				<xsl:if test="$buildForm &gt; 0">
					<td>
						<input type="checkbox" name="foobar.cei" value="{$foobar}">
						<xsl:if test="//vars:myenrolls/item:myenrolls[@elementInstance = $foobar]">
							<xsl:attribute name="checked">
								<xsl:value-of select="'checked'"/>
							</xsl:attribute>
						</xsl:if>
						</input>
					</td>
				</xsl:if>
				<xsl:if test="$buildForm &lt; 1">
					<td>
						<input type="checkbox" name="foobar.cei" value="{$foobar}">
						<xsl:attribute name="disabled">
							<xsl:value-of select="'disabled'"/>
						</xsl:attribute>
						<xsl:if test="//vars:myenrolls/item:myenrolls[@elementInstance = $foobar]">
							<xsl:attribute name="checked">
								<xsl:value-of select="'checked'"/>
							</xsl:attribute>
						</xsl:if>
						</input>
					</td>
				</xsl:if>

				<td>
					<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/vars:day/item:day/@name"/>,
					<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/@startingTime"/>	
				</td>
				<td>
					<a href="{$servlet-path}/lectures/showRoom/{//vars:eitpir/item:eitpir[@elementInstance=$foobar]/@room}">
						<xsl:value-of select="//vars:eitpir/item:eitpir[@elementInstance=$foobar]/vars:room/item:room/vars:building/item:building/@name"/>,
						<xsl:value-of select="//vars:eitpir/item:eitpir[@elementInstance=$foobar]/vars:room/item:room/@name"/>
					</a>
				</td>
				<td>
					<xsl:value-of select="vars:courseElement/item:courseElement/vars:type/item:type/@name"/>
				</td>
				<td>
					<xsl:value-of select="@duration"/>
				</td>
				<td>
					<xsl:if test="not(vars:courseElement/item:courseElement/@required='false')">
						<xsl:value-of select="'is required'"/>
					</xsl:if>
				</td>
			</tr>
</xsl:template>

<xsl:template mode="path" match="vars:meta">

</xsl:template>

</xsl:transform>
