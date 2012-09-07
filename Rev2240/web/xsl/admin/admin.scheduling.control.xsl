<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

	<xsl:import href="common.xsl" />
	<xsl:import href="../lego/form-builder.xsl" />

	<xsl:template mode="content" match="vars:meta">
		<xsl:choose>
			<xsl:when test="vars:mode/text() = 'scheduling'">
				<xsl:variable name="curr" select="vars:current/item:current"/>
				
				<h2>Scheduler is running !</h2>
				
				<p> 
				Program <b><xsl:value-of select="$curr/@id"/></b><br />
				Academic Term :: <b><xsl:value-of select="$curr/vars:academicTerm/item:academicTerm/@name"/></b><br />
				Department :: <b><xsl:value-of select="$curr/vars:department/item:department/@name"/></b><br />
				</p>
				<!-- iframe here ! -->
				<iframe name="schedulerScore" src="schedulerPanelScore" scrolling="no" style="width: 100%; height: 100px; border: none;"> </iframe>
				 	
				<p>
					You can stop the scheduling by pressing <b><a href="schedulerPanel?action=stop">here</a></b>
				</p>			
			</xsl:when>
		</xsl:choose>
		<xsl:if test="vars:mode/text() = 'scheduling'">
					</xsl:if>

		<xsl:if test="vars:mode/text() = 'showStatusInfo'">
			<xsl:variable name="curr" select="vars:current/item:current"/>
			<xsl:variable name="status">
				<xsl:choose>
					<xsl:when test="$curr/@published = 'true'">
						<xsl:value-of select="'published'"/>
					</xsl:when>
					<xsl:when test="$curr/@freezed = 'true'">
						<xsl:value-of select="'freezed'"/>
					</xsl:when>
					<xsl:when test="vars:current_helper">
						<xsl:value-of select="'scheduled'"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="'never scheduled'"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			
			
			<h2>Statusreport from Program <xsl:value-of select="$curr/@id"/></h2>
			<p>current status :: <b><xsl:value-of select="$status"/></b></p>
			<p>possible actions :: 
			<a href="?action=start">Start</a><xsl:text> </xsl:text>
			<xsl:if test="vars:current_helper">
			<a href="?action=resume">Resume</a><xsl:text> </xsl:text>
			<a href="?action=freeze">Freeze</a><xsl:text> </xsl:text>
			</xsl:if>			
			<xsl:if test="$status = 'freezed'">
			<a href="?action=publish">Publish</a><xsl:text> </xsl:text>
			</xsl:if>
			</p>

			<xsl:if test="$status != 'never scheduled'">
				<!-- present conflicts here -->
				<xsl:if test="vars:lecturerOverlap/item:lecturerOverlap or vars:roomOverlap/item:roomOverlap">
					<h2>Conflicts</h2>
					<xsl:if test="vars:lecturerOverlap/item:lecturerOverlap">
						<h4>Lecturer with overlapping Courses</h4>
						<ul>
						<xsl:for-each select="vars:lecturerOverlap/item:lecturerOverlap">
							<xsl:variable name="ref" select="substring-before(substring-after(item:lecturerOverlap/@key, '#'), ')')"/>
							<xsl:variable name="lect" select="//vars:lecturer/item:lecturer[@id = $ref]"/>
							<li>
								<a href="{$servlet-path}/admin/showTimetableByLecturer/{$ref}">
									<xsl:value-of select="concat($lect/@lastName, ', ', $lect/@firstName)"/>
								</a> 
								<!--(<xsl:value-of select="item:lecturerOverlap/text()"/>)-->
							</li>
						</xsl:for-each>
						</ul>
					</xsl:if>
					<xsl:if test="vars:roomOverlap/item:roomOverlap">
						<h4>Rooms with overlapping Courses</h4>
						<ul>
						<xsl:for-each select="vars:roomOverlap/item:roomOverlap">
							<xsl:variable name="ref" select="substring-before(substring-after(item:roomOverlap/@key, '#'), ')')"/>
							<xsl:variable name="room" select="//vars:room/item:room[@id = $ref]"/>

							<li>
								<a href="{$servlet-path}/admin/showRoom/{$ref}">
									<xsl:value-of select="concat($room/vars:building/item:building/@name, ' - ',$room/@number)"/>
									<xsl:if test="$room/@name != ''">
										<xsl:value-of select="concat(' (',$room/@name,')')"/>
									</xsl:if>
								</a> 
								<!--(<xsl:value-of select="item:roomOverlap/text()"/>)-->
							</li>
						</xsl:for-each>
						</ul>
					</xsl:if>
				</xsl:if>
			</xsl:if>

		</xsl:if>
		
		<xsl:if test="vars:mode/text() = 'selectProgram'">
			<form method="post">
				<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.SchedulingControl"/>
				<input type="hidden" name="~formName" value="foobar"/>	
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
				<p class="formControls">
					<input type="submit" value="submit" name="foobar.but_commit" />
				</p>
			</form>
		</xsl:if>
	
	
	
	<!-- 
			<p>
			Status:
			<xsl:value-of select="//vars:status" />
		</p>
			<xsl:if test="//vars:scheduleScore">
			<p>
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
				</p>		
			</xsl:if>
			
			<xsl:if test="vars:lecturerOverlap or vars:roomOverlap">
				<h2>Conflicts</h2>
				<xsl:if test="vars:lecturerOverlap">
					<h4>Lecturer with overlapping Courses</h4>
					<ul>
					<xsl:for-each select="vars:lecturerOverlap/item:lecturerOverlap">
						<xsl:variable name="ref" select="substring-before(substring-after(item:lecturerOverlap/@key, '#'), ')')"/>
						<li>
							<a href="{$servlet-path}/admin/showTimetableByLecturer/{$ref}">
								<xsl:value-of select="item:lecturerOverlap/@key"/> -> <xsl:value-of select="$ref"/>
							</a> 
							(<xsl:value-of select="item:lecturerOverlap/text()"/>)
						</li>
					</xsl:for-each>
					</ul>
				</xsl:if>
				<xsl:if test="vars:roomOverlap">
					<h4>Rooms with overlapping Courses</h4>
					<ul>
					<xsl:for-each select="vars:roomOverlap/item:roomOverlap">
						<xsl:variable name="ref" select="substring-before(substring-after(item:roomOverlap/@key, '#'), ')')"/>
						<li>
							<a href="{$servlet-path}/admin/showRoom/{$ref}">
								<xsl:value-of select="item:roomOverlap/@key"/> -> <xsl:value-of select="$ref"/>
							</a> 
							(<xsl:value-of select="item:roomOverlap/text()"/>)
						</li>
					</xsl:for-each>
					</ul>
				</xsl:if>
			</xsl:if>
	-->
	</xsl:template>

</xsl:transform>