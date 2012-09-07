<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Start</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Willkommen im Vorlesungsverzeichnis!</h1>
	
	<h2>Ankündigungen</h2>
	
	<div class="news">
		<div class="news-item">
			<h3>Modelchecking fällt aus</h3>
			<p>Nach der Attacke eines wütenden Mobs von vermummten Gestalten auf Univ.-Prof. Dr. Karay Scelm muss die heutige Veranstaltung zum Thema Modelchecking leider ausfallen! <em>(Sekretariat)</em></p>
		</div>
		<div class="news-item">
			<h3>Theoretische Philosophie findet statt!</h3>
			<p>Entgegen der Ankündigung von letztem Dienstag findet die Vorlesung “Theoretische Philosophie” nun doch statt! <em>(Gregor Ambet)</em></p>
		</div>
	</div>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

</xsl:transform>
