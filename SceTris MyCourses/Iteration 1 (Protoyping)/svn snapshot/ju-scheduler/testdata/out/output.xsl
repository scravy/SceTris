<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
		<body>
		<h2>Room bookings</h2>
		<xsl:for-each select="booking/room">
			<h3><xsl:value-of select="@name"/></h3>
			<table border="1">
				<xsl:for-each select="slot[@day='1']">
					<xsl:for-each select="booking/room/slot[@time=@time]">
						<tr>
							<td>Day <xsl:value-of select="@day"/></td>
							<td>Slot <xsl:value-of select="@time"/></td>
							<td><xsl:value-of select="course"/></td>
						</tr>
					</xsl:for-each>
				</xsl:for-each>
			</table>
		</xsl:for-each>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

