<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
		<body>
		<h2>Room bookings</h2>
		<xsl:for-each select="booking/room">
			<h3><xsl:value-of select="@name"/></h3>
			<table border="0" cellspacing="5">
				<xsl:for-each select="course">
					<tr>
						<td><b><xsl:value-of select="@name"/></b></td>
						<td><xsl:value-of select="@lecturer"/></td>
						<td>Tag <xsl:value-of select="@day"/></td>
					</tr><tr>
						<td></td>
						<td><xsl:value-of select="@duration"/>h</td>
						<td>Zeit <xsl:value-of select="@time"/></td>
					</tr>
				</xsl:for-each>
			</table>
		</xsl:for-each>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

