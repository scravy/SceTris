<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item" xmlns:fn="http://www.w3.org/2005/xpath-functions">

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="/">
<html>
	<head>
		<title>State of the art</title>
		<style type="text/css">
		table, tr, td {
	border-collapse: collapse;
	/* border: 1px solid black; */
}

td {
	padding: .25em 1em;
}

th {
	border-bottom: 1px solid black;	
}

table {
	margin: 1em;
}
		
		</style>

	</head>
	<body>
		<xsl:choose>
			<xsl:when test="//vars:idle">
				<form method="post">
					<input type="submit" value="Start" name="start"/>
				</form>
				<form method="post">
					<input disabled="disabled" type="submit" value="Stop" name="stop"/>

				</form>
				<p>Status: <xsl:value-of select="//vars:status"/></p>
			</xsl:when>
			<xsl:when test="//vars:Start">
				<form method="post">
					<input disabled="disabled" type="submit" value="Start" name="start"/>
				</form>
				<form method="post">

					<input type="submit" value="Stop" name="stop"/>
				</form>
				<p>Status: <xsl:value-of select="//vars:status"/></p>
			</xsl:when>
		</xsl:choose>
		<p>

		
			<xsl:for-each select="//vars:roomTimetable/item:roomTimetable">

				<h1>Room: <xsl:value-of select="@roomName"/></h1>
				<table><tr>
				<th>Time</th>
				<th>Monday</th>
				<th>Tuesday</th>
				<th>Wednesday</th>

				<th>Thursday</th>
				<th>Friday</th>
				</tr>
				<tr>
				<td>08:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='08:00']" />
				</tr>

				<tr>
				<td>09:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='09:00']" />
				</tr><tr>
				<td>10:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='10:00']" />
				</tr><tr>
				<td>11:00</td>

				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='11:00']" />
				</tr><tr>								
				<td>12:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='12:00']" />
				</tr><tr>								
				<td>13:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='13:00']" />
				</tr><tr>								
				<td>14:00</td>

				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='14:00']" />
				</tr><tr>							
				<td>15:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='15:00']" />
				</tr><tr>								
				<td>16:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='16:00']" />
				</tr><tr>								
				<td>17:00</td>

				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='17:00']" />
				</tr>
				<tr>								
				<td>18:00</td>
				<xsl:apply-templates select="vars:fixtures/item:fixtures/vars:timetableList/item:timetableList[@time='18:00']" />
				</tr>								
				</table>
				<!-- 
				<xsl:for-each select="vars:fixtures/item:fixtures">
					<xsl:value-of select="@day"/><br/>
					each day
					<xsl:for-each select="//vars:timetableList/item:timetableList[@time='09:00']">
							[<xsl:value-of select="@time"/>] <xsl:value-of select="@courseName"/> (<xsl:value-of select="@lecturerName"/>)<br/>
						</xsl:for-each>
					</xsl:for-each> -->
				</xsl:for-each>

		</p>


	</body>
</html>
</xsl:template>

<xsl:template match="item:timetableList">
	<td>
		<xsl:if test="@courseName">
			<xsl:value-of select="substring-before(@courseName,':')" /><br />
			<xsl:value-of select="substring-after(@courseName,':')" /><br />

			<span style="color:#DDDDDD">(<xsl:value-of select="@lecturerName" />)</span>
		</xsl:if>
		<xsl:if test="not(@courseName)">
			-
		</xsl:if>
	</td>
</xsl:template>

</xsl:transform>
