<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

	<xsl:import href="./fuberlin/global.xsl" />
	<xsl:import href="./fuberlin/lego.xsl" />

	<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"
		encoding="UTF-8" />

	<xsl:template mode="content" match="vars:meta">

		<form method="post">
			<br />
			<xsl:choose>
				<xsl:when test="//vars:status='ready'">
					<input type="submit" value="start" name="command" />
					<input disabled="disabled" type="submit" value="stop" name="command" />
				</xsl:when>

				<xsl:when test="//vars:status='stopping'">
					<input disabled="disabled" type="submit" value="start" name="command" />
					<input disabled="disabled" type="submit" value="stop" name="command" />
				</xsl:when>

				<xsl:when test="//vars:status='running'">
					<input disabled="disabled" type="submit" value="start" name="command" />
					<input type="submit" value="stop" name="command" />
				</xsl:when>
			</xsl:choose>


			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="'department'" />
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
				<xsl:with-param name="id" select="'academicTerm'" />
				<xsl:with-param name="label" select="'Academic Term'" />
				<xsl:with-param name="options">
					<xsl:for-each select="vars:academicTerms/item:academicTerms">
						<option value="{@id}">
							<xsl:value-of select="@name" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>
		</form>

		<br />
		<br />

		<p>
			Status:
			<xsl:value-of select="//vars:status" />
		</p>
		<p>
			hard constraints:
			<xsl:value-of
				select="//vars:scheduleScore/item:scheduleScore/@numberOfHardConstraintsSatisfied" />
			of
			<xsl:value-of
				select="//vars:scheduleScore/item:scheduleScore/@numberOfHardConstraints" />
			<br />
			soft constraints:
			<xsl:value-of
				select="//vars:scheduleScore/item:scheduleScore/@numberOfSoftConstraintsSatisfied" />
			of
			<xsl:value-of
				select="//vars:scheduleScore/item:scheduleScore/@numberOfSoftConstraints" />
			<br />
		</p>



		<p>
			<ul>
				<xsl:for-each select="//vars:roomTimetable/item:roomTimetable">
					<li>
						Room:
						<xsl:value-of select="@roomName" />
						<ul>
							<xsl:for-each
								select="vars:dayTimetableCollection/item:dayTimetableCollection">
								<li>
									<xsl:value-of select="@dayName" />
									<ul>
										<xsl:for-each
											select="vars:timeSlotCollection/item:timeSlotCollection">
											<li>
												<xsl:value-of select="@timeName" />
												:

												<xsl:if test="vars:courseCollection">
													<xsl:for-each select="vars:courseCollection/item:courseCollection">
														<xsl:value-of select="@courseName" />
														(
														<xsl:value-of select="@lecturerName" />
														)
													</xsl:for-each>
												</xsl:if>
											</li>

										</xsl:for-each>
									</ul>
								</li>
							</xsl:for-each>
						</ul>
					</li>
				</xsl:for-each>
			</ul>
		</p>
	</xsl:template>

	<!-- Subtemplates -->
	<xsl:template match="item:academicTerms">
		<option>
			<xsl:attribute name="value">
			<xsl:value-of select="@id" />
			</xsl:attribute>
			<xsl:value-of select="@name" />
		</option>
	</xsl:template>

	<xsl:template match="item:departments">
		<option>
			<xsl:attribute name="value">
			<xsl:value-of select="@id" />
			</xsl:attribute>
			<xsl:value-of select="@name" />
		</option>
	</xsl:template>


</xsl:transform>