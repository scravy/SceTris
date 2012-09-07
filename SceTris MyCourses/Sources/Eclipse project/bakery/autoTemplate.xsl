<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform xmlns:date="http://exslt.org/dates-and-times" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:j="http://technodrom.scravy.de/2010/meta/acgt/java" xmlns:q="http://technodrom.scravy.de/2010/queries" version="1.0" extension-element-prefixes="date">
  <xsl:import href="lib/common.xsl"/>
  <xsl:output method="text"/>
  <xsl:param name="relation">#manager</xsl:param>
  <xsl:param name="managerName">RelationManager</xsl:param>
  <xsl:param name="targetPackage">de.fu.scetris.data</xsl:param>
  <xsl:param name="weavePackage">de.fu.weave</xsl:param>
  <xsl:param name="weavePackageOrm">de.fu.weave.orm</xsl:param>
  <xsl:param name="weavePackageOrmAnnotation">de.fu.weave.orm.annotation</xsl:param>
  <xsl:param name="weavePackageXml">de.fu.weave.xml</xsl:param>
  <xsl:param name="weavePackageXmlAnnotation">de.fu.weave.xml.annotation</xsl:param>
  <xsl:param name="junction">de.fu.junction</xsl:param>
  <xsl:param name="installer">unknown</xsl:param>
  <xsl:template match="/en:relations">
    <xsl:variable name="fileName">
      <xsl:choose>
        <xsl:when test="$relation = '#manager-interface'">
          <xsl:value-of select="concat($managerName, 'Interface')"/>
        </xsl:when>
        <xsl:when test="$relation = '#manager'">
          <xsl:value-of select="$managerName"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$relation"/>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:text>.java</xsl:text>
    </xsl:variable>
    <xsl:text>/* </xsl:text>
    <xsl:value-of select="$fileName"/>
    <xsl:text> / </xsl:text>
    <xsl:value-of select="date:date()"/>
    <xsl:text>
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using </xsl:text>
    <xsl:value-of select="system-property('xsl:vendor')"/>
    <xsl:text>
 */

/**
 * @formatter:off
 */
package </xsl:text>
    <xsl:value-of select="$targetPackage"/>
    <xsl:text>;

</xsl:text>
    <xsl:choose>
      <xsl:when test="$relation = '#manager-interface'">
        <xsl:call-template name="manager-interface"/>
      </xsl:when>
      <xsl:when test="$relation = '#manager'">
        <xsl:call-template name="manager"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:apply-templates select="en:entity | en:relationship"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824023">
    <xsl:text>"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>"</xsl:text>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824064">
    <xsl:text>public static final String </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> = "</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>";
	</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824082">
    <xsl:if test="not(@hidden = 'true')">
      <xsl:text>
		@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.Pos(</xsl:text>
      <xsl:value-of select="position()"/>
      <xsl:text>)
		@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.Field("</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>")</xsl:text>
      <xsl:if test="not(@nullable = 'true')">
        <xsl:text> @</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Required</xsl:text>
      </xsl:if>
      <xsl:if test="@use = 'id'">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.FormControl(</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Control.HIDDEN)</xsl:text>
      </xsl:if>
      <xsl:if test="@use = 'timestamp'">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.FormControl(</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Control.HIDDEN)</xsl:text>
      </xsl:if>
      <xsl:if test="@type = 'text'">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.FormControl(</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Control.TEXTAREA)</xsl:text>
      </xsl:if>
      <xsl:if test="@min">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Min(</xsl:text>
        <xsl:value-of select="@min"/>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <xsl:if test="@max">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Max(</xsl:text>
        <xsl:value-of select="@max"/>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <xsl:if test="@step">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Step(</xsl:text>
        <xsl:value-of select="@step"/>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <xsl:if test="@only">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Only(</xsl:text>
        <xsl:text>"</xsl:text>
        <xsl:value-of select="@only"/>
        <xsl:text>"</xsl:text>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <xsl:if test="@match">
        <xsl:text>
		@</xsl:text>
        <xsl:value-of select="$weavePackage"/>
        <xsl:text>.Form.Match(</xsl:text>
        <xsl:text>"</xsl:text>
        <xsl:value-of select="@match"/>
        <xsl:text>"</xsl:text>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <xsl:text>
		@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.ActiveValidator("</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>$validator")
		@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.ActiveConverter("</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>$converter")</xsl:text>
    </xsl:if>
    <xsl:if test="@ref">
      <xsl:text>
		@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.Alternatives("</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>$alternatives")
		@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.FormControl(</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.Form.Control.ALTERNATIVES)</xsl:text>
    </xsl:if>
    <xsl:text>
		public </xsl:text>
    <xsl:if test="@java-profile">
      <xsl:value-of select="@java-profile"/>
    </xsl:if>
    <xsl:if test="not(@java-profile)">
      <xsl:if test="@ref">
        <xsl:if test="@nullable = 'true'">
          <xsl:text>Integer</xsl:text>
        </xsl:if>
        <xsl:if test="not(@nullable = 'true')">
          <xsl:text>int</xsl:text>
        </xsl:if>
      </xsl:if>
      <xsl:if test="not(@ref)">
        <xsl:call-template name="type"/>
      </xsl:if>
    </xsl:if>
    <xsl:text> </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>;</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>
		
		public java.util.Map&lt;</xsl:text>
      <xsl:text>Integer</xsl:text>
      <xsl:text>,java.lang.String&gt; </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>$alternatives = new java.util.TreeMap&lt;</xsl:text>
      <xsl:text>Integer</xsl:text>
      <xsl:text>,java.lang.String&gt;();</xsl:text>
    </xsl:if>
    <xsl:text>
		</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824204">
    <xsl:if test="not(@java-profile)">
      <xsl:text>
			</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = $object.</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:if test="not(@ref)">
        <xsl:text>get</xsl:text>
      </xsl:if>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>();</xsl:text>
    </xsl:if>
    <xsl:if test="@java-profile">
      <xsl:text>
			try {
			</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = </xsl:text>
      <xsl:if test="@nullable = 'true'">
        <xsl:text>!$object.has</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
          <xsl:with-param name="first" select="false"/>
        </xsl:call-template>
        <xsl:text>() ? null : </xsl:text>
      </xsl:if>
      <xsl:text>new </xsl:text>
      <xsl:value-of select="@java-profile"/>
      <xsl:text>($object.get</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>());
			} catch (Exception $exc) { }</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824233">
    <xsl:text>/**
	 * </xsl:text>
    <xsl:value-of select="dc:title[@xml:lang = 'en' or not(@xml:lang)]"/>
    <xsl:text>
	 * &lt;p&gt;
	 * </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
    <xsl:text>
	 */
	</xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="@nullable = 'true'">
      <xsl:text> = null</xsl:text>
    </xsl:if>
    <xsl:text>;</xsl:text>
    <xsl:text>
	</xsl:text>
    <xsl:text>boolean changed_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = false;</xsl:text>
    <xsl:text>
	</xsl:text>
    <xsl:text>boolean fetched_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = false;</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>
	</xsl:text>
      <xsl:if test="@nullable = 'true'">
        <xsl:text>Integer</xsl:text>
      </xsl:if>
      <xsl:if test="not(@nullable = 'true')">
        <xsl:text>int</xsl:text>
      </xsl:if>
      <xsl:text> ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>;</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824292">
    <xsl:if test="@ref">
      <xsl:text>ref</xsl:text>
    </xsl:if>
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = result.get</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>Int</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:call-template name="javasqltype"/>
    </xsl:if>
    <xsl:text>("</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>");</xsl:text>
    <xsl:if test="@nullable = 'true'">
      <xsl:text>
			if (result.wasNull()) {
				</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = null;
			}</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:text>
			fetched_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = true;</xsl:text>
    </xsl:if>
    <xsl:text>
			</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824331">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824342">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824350">
    <xsl:text>
		this._</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>;</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>
		this.ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>.id();</xsl:text>
    </xsl:if>
    <xsl:text>
		this.fetched_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = true;</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824374">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824385">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824393">
    <xsl:text>
			this._</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>;</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>
			this.ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>.id();</xsl:text>
    </xsl:if>
    <xsl:text>
			this.fetched_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = true;</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824476">
    <xsl:text>\"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\", </xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824480">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>\"</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>\"</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824487">
    <xsl:text>?, </xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824489">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>?</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824500">
    <xsl:if test="@nullable = 'true'">
      <xsl:if test="not(@default)">
        <xsl:text>
			if (</xsl:text>
        <xsl:if test="@ref">
          <xsl:text>ref</xsl:text>
        </xsl:if>
        <xsl:text>_</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text> != null) {</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:if test="not(@default)">
      <xsl:text>
				stmt.set</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>Int</xsl:text>
      </xsl:if>
      <xsl:if test="not(@ref)">
        <xsl:call-template name="javasqltype"/>
      </xsl:if>
      <xsl:text>(i++, </xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>);</xsl:text>
    </xsl:if>
    <xsl:if test="@nullable = 'true'">
      <xsl:if test="not(@default)">
        <xsl:text>
			} else {
				</xsl:text>
        <xsl:if test="@default">
          <xsl:text>stmt.set</xsl:text>
          <xsl:call-template name="javasqltype"/>
          <xsl:text>(i++, </xsl:text>
          <xsl:choose>
            <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
            <xsl:when test="@type = 'string'">
              <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="@default"/>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:text>);
				</xsl:text>
        </xsl:if>
        <xsl:if test="not(@default)">
          <xsl:text>stmt.setNull(i++, </xsl:text>
          <xsl:if test="@type and not(@ref)">
            <xsl:call-template name="javasqltype2">
              <xsl:with-param name="type" select="@type"/>
            </xsl:call-template>
          </xsl:if>
          <xsl:if test="@ref">java.sql.Types.INTEGER</xsl:if>
          <xsl:text>);
				</xsl:text>
        </xsl:if>
        <xsl:text>
			}</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:if test="@default">
      <xsl:if test="not(@nullable = 'true')">
        <xsl:text>
			if (manager.isNull(_</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>)) {
				stmt.set</xsl:text>
        <xsl:call-template name="javasqltype"/>
        <xsl:text>(i++, _</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text> = </xsl:text>
        <xsl:choose>
          <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
          <xsl:when test="@type = 'string'">
            <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="@default"/>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>);
			} else {
				stmt.set</xsl:text>
        <xsl:call-template name="javasqltype"/>
        <xsl:text>(i++, _</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>);
			}</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:text>
			</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824557">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			stmt.set</xsl:text>
      <xsl:call-template name="javasqltype"/>
      <xsl:text>(i++, _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>);</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824580">
    <xsl:text>changed_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = false;
			</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824590">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
	public void pushChanges(</xsl:text>
      <xsl:call-template name="type"/>
      <xsl:text> timekey) throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = timekey;
		pushChanges();
	}</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824614">
    <xsl:text>\"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\" = ?"
			+ ", </xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824618">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>\"</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>\" = ?</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824625">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>\"</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>\" = ? AND </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824634">
    <xsl:if test="@nullable = 'true'">
      <xsl:if test="not(@default)">
        <xsl:text>
			if (</xsl:text>
        <xsl:if test="@ref">
          <xsl:text>ref</xsl:text>
        </xsl:if>
        <xsl:text>_</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text> != null) {</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:if test="not(@default)">
      <xsl:text>
				stmt.set</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>Int</xsl:text>
      </xsl:if>
      <xsl:if test="not(@ref)">
        <xsl:call-template name="javasqltype"/>
      </xsl:if>
      <xsl:text>(i++, </xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>);</xsl:text>
    </xsl:if>
    <xsl:if test="@nullable = 'true'">
      <xsl:if test="not(@default)">
        <xsl:text>
			} else {
				</xsl:text>
        <xsl:if test="@default">
          <xsl:text>stmt.set</xsl:text>
          <xsl:call-template name="javasqltype"/>
          <xsl:text>(i++, </xsl:text>
          <xsl:choose>
            <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
            <xsl:when test="@type = 'string'">
              <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="@default"/>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:text>);
				</xsl:text>
        </xsl:if>
        <xsl:if test="not(@default)">
          <xsl:text>stmt.setNull(i++, </xsl:text>
          <xsl:if test="@type and not(@ref)">
            <xsl:call-template name="javasqltype2">
              <xsl:with-param name="type" select="@type"/>
            </xsl:call-template>
          </xsl:if>
          <xsl:if test="@ref">java.sql.Types.INTEGER</xsl:if>
          <xsl:text>);
				</xsl:text>
        </xsl:if>
        <xsl:text>
			}</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:if test="@default">
      <xsl:if test="not(@nullable = 'true')">
        <xsl:text>
			if (manager.isNull(_</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>)) {
				stmt.set</xsl:text>
        <xsl:call-template name="javasqltype"/>
        <xsl:text>(i++, </xsl:text>
        <xsl:choose>
          <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
          <xsl:when test="@type = 'string'">
            <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="@default"/>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>);
			} else {
				stmt.set</xsl:text>
        <xsl:call-template name="javasqltype"/>
        <xsl:text>(i++, _</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>);
			}</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:text>
			</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824689">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			</xsl:text>
      <xsl:call-template name="type"/>
      <xsl:text> currentTimekey = _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>;
			</xsl:text>
      <xsl:call-template name="type"/>
      <xsl:text> newTimekey = </xsl:text>
      <xsl:choose>
        <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
        <xsl:when test="@type = 'string'">
          <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="@default"/>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:text>;
			stmt.set</xsl:text>
      <xsl:call-template name="javasqltype"/>
      <xsl:text>(i++, newTimekey);
			stmt.set</xsl:text>
      <xsl:call-template name="javasqltype"/>
      <xsl:text>(i++, currentTimekey);</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824713">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = newTimekey;</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35824720">
    <xsl:text>changed_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = false;
			</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825163">
    <xsl:text>"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>"</xsl:text>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825196">
    <xsl:text>public static final String </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> = "</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>";
	</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825204">
    <xsl:text>/**
	 * </xsl:text>
    <xsl:value-of select="dc:title[@xml:lang = 'en' or not(@xml:lang)]"/>
    <xsl:text>
	 * &lt;p&gt;
	 * </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
    <xsl:text>
	 * @since Iteration2
	 */
	</xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="@nullable = 'true'">
      <xsl:text> = null</xsl:text>
    </xsl:if>
    <xsl:text>;
	boolean changed_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = false;
	boolean fetched_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = false;
	</xsl:text>
    <xsl:if test="@ref">
      <xsl:if test="@nullable = 'true'">
        <xsl:text>Integer</xsl:text>
      </xsl:if>
      <xsl:if test="not(@nullable = 'true')">
        <xsl:text>int</xsl:text>
      </xsl:if>
      <xsl:text> ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>;</xsl:text>
    </xsl:if>
    <xsl:text>
	</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825270">
    <xsl:text>
			</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>ref</xsl:text>
    </xsl:if>
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = result.get</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>Int</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:call-template name="javasqltype"/>
    </xsl:if>
    <xsl:text>("</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>");</xsl:text>
    <xsl:if test="@nullable = 'true'">
      <xsl:text>
			if (result.wasNull()) {
				</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = null;
			}</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:text>
			fetched_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = true;</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825318">
    <xsl:text>
			this.</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>ref</xsl:text>
    </xsl:if>
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = result.get</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>Int</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:call-template name="javasqltype"/>
    </xsl:if>
    <xsl:text>("\"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\"");</xsl:text>
    <xsl:if test="@nullable = 'true'">
      <xsl:text>
			if (result.wasNull()) {
				this.</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = null;
			}</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:text>
			this.fetched_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = true;</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825372">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825390">
    <xsl:text>
		this._</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>;</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>
		this.ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>.id();</xsl:text>
    </xsl:if>
    <xsl:text>
		this.fetched_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = true;</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825420">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825429">
    <xsl:text>
		this._</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>;</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>
		this.ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>.id();</xsl:text>
    </xsl:if>
    <xsl:text>
		this.fetched_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> = true;</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825514">
    <xsl:text>,"
			+ "\"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\"</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825518">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>, \"</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>\"</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825525">
    <xsl:text>, ?</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825527">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>, ?</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825538">
    <xsl:if test="@nullable = 'true'">
      <xsl:if test="not(@default)">
        <xsl:text>
			if (</xsl:text>
        <xsl:if test="@ref">
          <xsl:text>ref</xsl:text>
        </xsl:if>
        <xsl:text>_</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text> != null) {</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:if test="not(@default)">
      <xsl:text>
				stmt.set</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>Int</xsl:text>
      </xsl:if>
      <xsl:if test="not(@ref)">
        <xsl:call-template name="javasqltype"/>
      </xsl:if>
      <xsl:text>(i++, </xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>);</xsl:text>
    </xsl:if>
    <xsl:if test="@nullable = 'true'">
      <xsl:if test="not(@default)">
        <xsl:text>
			} else {
				</xsl:text>
        <xsl:if test="@default">
          <xsl:text>stmt.set</xsl:text>
          <xsl:call-template name="javasqltype"/>
          <xsl:text>(i++, </xsl:text>
          <xsl:choose>
            <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
            <xsl:when test="@type = 'string'">
              <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="@default"/>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:text>);
				</xsl:text>
        </xsl:if>
        <xsl:if test="not(@default)">
          <xsl:text>stmt.setNull(i++, </xsl:text>
          <xsl:if test="@type and not(@ref)">
            <xsl:call-template name="javasqltype2">
              <xsl:with-param name="type" select="@type"/>
            </xsl:call-template>
          </xsl:if>
          <xsl:if test="@ref">java.sql.Types.INTEGER</xsl:if>
          <xsl:text>);
				</xsl:text>
        </xsl:if>
        <xsl:text>
			}</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:if test="@default">
      <xsl:if test="not(@nullable = 'true')">
        <xsl:text>
			if (manager.isNull(_</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>)) {
				stmt.set</xsl:text>
        <xsl:call-template name="javasqltype"/>
        <xsl:text>(i++, _</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text> = </xsl:text>
        <xsl:choose>
          <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
          <xsl:when test="@type = 'string'">
            <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="@default"/>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>);
			} else {
				stmt.set</xsl:text>
        <xsl:call-template name="javasqltype"/>
        <xsl:text>(i++, _</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>);
			}</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:text>
			</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825595">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			stmt.set</xsl:text>
      <xsl:call-template name="javasqltype"/>
      <xsl:text>(i++, timekey(true));</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825626">
    <xsl:text>\"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\" = ?, "
			+ "</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825632">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>\"</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>\" = ?</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825638">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			+ "\"</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>\" = ?"</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825644">
    <xsl:text>
			+ " AND \"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\" = ?"</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825651">
    <xsl:if test="@nullable = 'true'">
      <xsl:text>
			if (</xsl:text>
      <xsl:if test="@ref">
        <xsl:text>ref</xsl:text>
      </xsl:if>
      <xsl:text>_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> != null) {</xsl:text>
    </xsl:if>
    <xsl:text>
				stmt.set</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>Int</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:call-template name="javasqltype"/>
    </xsl:if>
    <xsl:text>(i++, </xsl:text>
    <xsl:if test="@ref">
      <xsl:text>ref</xsl:text>
    </xsl:if>
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>);</xsl:text>
    <xsl:if test="@nullable = 'true'">
      <xsl:text>
			} else {
				stmt.setNull(i++, </xsl:text>
      <xsl:if test="@type and not(@ref)">
        <xsl:call-template name="javasqltype2">
          <xsl:with-param name="type" select="@type"/>
        </xsl:call-template>
      </xsl:if>
      <xsl:if test="@ref">java.sql.Types.INTEGER</xsl:if>
      <xsl:text>);
			}</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825676">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			</xsl:text>
      <xsl:call-template name="type"/>
      <xsl:text> currentTimekey = _</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text>;
			</xsl:text>
      <xsl:call-template name="type"/>
      <xsl:text> newTimekey = </xsl:text>
      <xsl:choose>
        <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
        <xsl:when test="@type = 'string'">
          <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="@default"/>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:text>;
			stmt.set</xsl:text>
      <xsl:call-template name="javasqltype"/>
      <xsl:text>(i++, newTimekey);
			stmt.set</xsl:text>
      <xsl:call-template name="javasqltype"/>
      <xsl:text>(i++, currentTimekey);</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825694">
    <xsl:text>
			stmt.set</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>Int</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:call-template name="javasqltype"/>
    </xsl:if>
    <xsl:text>(i++, </xsl:text>
    <xsl:if test="@ref">
      <xsl:text>ref</xsl:text>
    </xsl:if>
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>);</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825715">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
			_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = newTimekey;</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825727">
    <xsl:if test="@use = 'timestamp'">
      <xsl:text>
	public void pushChanges(</xsl:text>
      <xsl:call-template name="type"/>
      <xsl:text> timekey) throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text> = timekey;
		pushChanges();
	}</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825750">
    <xsl:text>
			+ " \"</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>\" = ?</xsl:text>
    <xsl:if test="position() != last()">
      <xsl:text> AND </xsl:text>
    </xsl:if>
    <xsl:text>"</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35825760">
    <xsl:text>
			stmt.set</xsl:text>
    <xsl:if test="@ref">
      <xsl:text>Int</xsl:text>
    </xsl:if>
    <xsl:if test="not(@ref)">
      <xsl:call-template name="javasqltype"/>
    </xsl:if>
    <xsl:text>(i++, </xsl:text>
    <xsl:if test="@ref">
      <xsl:text>ref</xsl:text>
    </xsl:if>
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>);</xsl:text>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826097">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826109">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826123">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826138">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826410">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826426">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826444">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826459">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826711">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826726">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826735">
    <xsl:text>, _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826746">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826762">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826775">
    <xsl:text>, _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826785">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826801">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826810">
    <xsl:text>, _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826820">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826836">
    <xsl:text>, </xsl:text>
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826850">
    <xsl:text>, _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826901">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826913">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826923">
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826937">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826949">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826964">
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826978">
    <xsl:text>
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35826991">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35827005">
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35827022">
    <xsl:text>
	 *
	 * @param _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35827035">
    <xsl:call-template name="type"/>
    <xsl:text> _</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:attribute" mode="manager_id35827049">
    <xsl:text>_</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString">
        <xsl:value-of select="@name"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:if test="position() != last()">
      <xsl:text>, </xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="q:queries" mode="manager_id35827365">
    <xsl:text>stmt.executeUpdate(</xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="translate(./text(), '&#10;', ' ')"/>
    </xsl:call-template>
    <xsl:text>);
			</xsl:text>
  </xsl:template>
  <xsl:template match="text()|*|@*" mode="manager_id35827365"/>
  <xsl:template name="manager"><xsl:text>
</xsl:text><xsl:text>/**
 *
 */
public class </xsl:text><xsl:value-of select="$managerName"/><xsl:text> extends </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.GenericRelationManager
	implements java.lang.Cloneable, </xsl:text><xsl:value-of select="$managerName"/><xsl:text>Interface {
	
	</xsl:text><xsl:text>java.util.Map&lt;String,</xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.Relation&gt; knownRelations = new java.util.TreeMap&lt;String,</xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.Relation&gt;();</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Constructs a new RelationManager
	 */
	public </xsl:text><xsl:value-of select="$managerName"/><xsl:text>() {
		super();
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * 
	 */
	public </xsl:text><xsl:value-of select="$managerName"/><xsl:text>(</xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.ConnectionManager $connectionManager) {
		super($connectionManager);
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>public </xsl:text><xsl:value-of select="$managerName"/><xsl:text> clone() {
		try {
			return (</xsl:text><xsl:value-of select="$managerName"/><xsl:text>) super.clone();
		} catch (Exception $exc) {
			return null;
		}
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>
	</xsl:text><xsl:apply-templates select="en:relationship" mode="manager_id35826556"/><xsl:text>
	</xsl:text><xsl:text>
	</xsl:text><xsl:apply-templates select="en:entity" mode="manager_id35826876"/><xsl:text>
	
	</xsl:text>/**
	 */
	public boolean install() throws <xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException</xsl:text> {<xsl:text>
		try {
			beginTransaction();
			java.sql.Statement stmt = connectionManager().getConnection().createStatement();
			</xsl:text><xsl:for-each select="document($installer)/q:queries/q:query"><xsl:text>stmt.executeUpdate(</xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="translate(./text(), '&#10;', ' ')"/></xsl:call-template><xsl:text>);
			</xsl:text></xsl:for-each><xsl:text>
			commitTransaction();
			return true;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
	</xsl:text>}<xsl:text>
	
	</xsl:text>/**
	 */
	public boolean dropSchema() throws <xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException</xsl:text> {<xsl:text>
		java.lang.String query = "DROP SCHEMA IF EXISTS \"scetris\" CASCADE;";
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
		return true;
	</xsl:text>}<xsl:text>
}
</xsl:text></xsl:template>
  <xsl:template name="manager-interface">
    <xsl:text>
public interface </xsl:text>
    <xsl:value-of select="$managerName"/>
    <xsl:text>Interface extends </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.RelationManager {</xsl:text>
    <xsl:apply-templates select="en:entity" mode="manager_id35826085"/>
    <xsl:text>
</xsl:text>
    <xsl:apply-templates select="en:relationship" mode="manager_id35826363"/>
    <xsl:text>

	public static class RelationManagerFactory {
		private final </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.ConnectionManager $connectionManager;
	
		RelationManagerFactory() {
			this.$connectionManager = null;
		}
		
		RelationManagerFactory(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.ConnectionManager $connectionManager) {
			this.$connectionManager = $connectionManager;
		}
	
		public static RelationManagerFactory newFactory() {
			return new RelationManagerFactory();
		}
		
		public static RelationManagerFactory newFactory(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.ConnectionManager $connectionManager) {
			if ($connectionManager == null) return newFactory();
			return new RelationManagerFactory($connectionManager);
		}
	
		public </xsl:text>
    <xsl:value-of select="$managerName"/>
    <xsl:text>Interface newInstance() {
			if ($connectionManager == null) {
				return new </xsl:text>
    <xsl:value-of select="$managerName"/>
    <xsl:text>();
			} else {
				return new </xsl:text>
    <xsl:value-of select="$managerName"/>
    <xsl:text>($connectionManager);
			}
		}
		
		public static </xsl:text>
    <xsl:value-of select="$managerName"/>
    <xsl:text>Interface newInstance(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.ConnectionManager $connectionManager) {
			return new </xsl:text>
    <xsl:value-of select="$managerName"/>
    <xsl:text>($connectionManager);
		}
	}
}
</xsl:text>
  </xsl:template>
  <xsl:template match="en:entity">
    <xsl:variable name="entity-name" select="@name"/>
    <xsl:variable name="javaizedName">
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
        <xsl:with-param name="first" test="false"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:if test="$javaizedName = $relation"><xsl:text>/**
 * OO-Representation of the entity-relation </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>
 * &lt;p&gt;
 * </xsl:text><xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/><xsl:text>
 */
@</xsl:text><xsl:value-of select="$weavePackageOrmAnnotation"/><xsl:text>.Entity(name = "</xsl:text><xsl:value-of select="@name"/><xsl:text>")
@</xsl:text><xsl:value-of select="$weavePackageOrmAnnotation"/><xsl:text>.Relation(name = </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text>, requiredSqlCols = {</xsl:text><xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35824023"/><xsl:text>})
@</xsl:text><xsl:value-of select="$weavePackage"/><xsl:text>.xml.annotation.XmlElement("</xsl:text><xsl:value-of select="@name"/><xsl:text>")
public class </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> extends </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.GenericEntity implements </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.Entity </xsl:text><xsl:if test="@use = 'user'"><xsl:text>, </xsl:text><xsl:value-of select="$weavePackage"/><xsl:text>.User</xsl:text></xsl:if><xsl:text> {
	</xsl:text><xsl:text>final </xsl:text><xsl:value-of select="$managerName"/><xsl:text> manager;</xsl:text><xsl:text>
	</xsl:text><xsl:text>boolean exists</xsl:text><xsl:text> = false</xsl:text><xsl:text>;</xsl:text><xsl:text>

	</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824064"/><xsl:text>

	</xsl:text><xsl:text>/**
	 * 
	 */
	public abstract static class Form extends </xsl:text><xsl:value-of select="$targetPackage"/><xsl:text>.formsupport.AbstractForm {
		public static final long serialVersionUID = </xsl:text><xsl:value-of select="position() * (position() + 235934)"/><xsl:text>L;
		
		</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824082"/><xsl:text>
		
		public Form setValues(</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> $object) {
			</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824204"/><xsl:text>
			return this;
		}
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824233"/><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Constructs the entity from an SQL ResultSet.
	 * &lt;p&gt;
	 * This Constructor is not publicly available as it should be used by {@link </xsl:text><xsl:value-of select="$managerName"/><xsl:text>} only.
	 * @param result The SQL ResultSet
	 */
	</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(</xsl:text><xsl:value-of select="$managerName"/><xsl:text> manager, java.sql.ResultSet result) throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824292"/><xsl:text>
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
		exists = true;
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Constructs the entity </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) </xsl:text><xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35824331"/><xsl:text>
	 */
	</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(</xsl:text><xsl:value-of select="$managerName"/><xsl:text> manager, </xsl:text><xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35824342"/><xsl:text>) {
		timekey(true);
		this.manager = manager;</xsl:text><xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35824350"/><xsl:text>
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Constructs the entity </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> and initializes all attributes.</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824374"/><xsl:text>
	 */
	</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(</xsl:text><xsl:value-of select="$managerName"/><xsl:text> manager, final boolean full, </xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824385"/><xsl:text>) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824393"/><xsl:text>
		}
	}</xsl:text><xsl:if test="@use = 'user'"><xsl:text>
	
	@Override
	public String loginName() {
		return getLoginName(); </xsl:text><xsl:text>
	}</xsl:text></xsl:if><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Returns the unique identifier for this entity.
	 * @since Iteration2
	 */
	public </xsl:text><xsl:for-each select="en:attribute[@use = 'id']"><xsl:call-template name="type"/></xsl:for-each><xsl:text> id() {
		return _</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/></xsl:call-template><xsl:text>;
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>@Override
	public String toString() {
		return "</xsl:text><xsl:value-of select="@name"/><xsl:text>: \"" + this._</xsl:text><xsl:if test="en:attribute[@use = 'title']"><xsl:value-of select="en:attribute[@use = 'title']/@name"/></xsl:if><xsl:if test="not(en:attribute[@use = 'title'])"><xsl:value-of select="en:attribute[@use = 'id']/@name"/></xsl:if><xsl:text> + "\" (#" + this._</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/></xsl:call-template><xsl:text> + ")";
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/*@Override
	public int hashCode() {
		return </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.GenericRelationManager._hashCode(this._</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/></xsl:call-template><xsl:text>);
	}*/</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link </xsl:text><xsl:value-of select="$managerName"/><xsl:text>#new</xsl:text><xsl:value-of select="@name"/><xsl:text>} rather than {@link </xsl:text><xsl:value-of select="$managerName"/><xsl:text>#create</xsl:text><xsl:value-of select="@name"/><xsl:text>).
	 */
	@Override
	public </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> create() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		String query = "INSERT INTO " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text> + " (</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824476"/><xsl:apply-templates select="en:attribute" mode="manager_id35824480"/><xsl:text>)"
			+ " VALUES (</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824487"/><xsl:apply-templates select="en:attribute" mode="manager_id35824489"/><xsl:text>)</xsl:text><xsl:if test="en:attribute[@use = 'id']/@serial = 'true'"><xsl:text> RETURNING </xsl:text><xsl:value-of select="en:attribute[@use = 'id']/@name"/></xsl:if><xsl:text>;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824500"/><xsl:apply-templates select="en:attribute" mode="manager_id35824557"/><xsl:text>
			</xsl:text><xsl:if test="not(en:attribute[@use = 'id']/@serial = 'true')"><xsl:text>stmt.execute();
			</xsl:text></xsl:if><xsl:if test="en:attribute[@use = 'id']/@serial = 'true'"><xsl:text>java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/></xsl:call-template><xsl:text> = keys.get</xsl:text><xsl:for-each select="en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(1);
			} else {
				throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException("no key was generated. phail.");
			}</xsl:text></xsl:if><xsl:text>
			</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824580"/><xsl:text>
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}</xsl:text><xsl:text>
	</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824590"/><xsl:text>
	</xsl:text><xsl:text>/**
	 * Updates the associated data inside the database
	 */
	@Override
	public void pushChanges() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text> + " SET </xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824614"/><xsl:apply-templates select="en:attribute" mode="manager_id35824618"/><xsl:text> "
			+ "WHERE </xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824625"/><xsl:value-of select="en:attribute[@use = 'id']/@name"/><xsl:text> = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824634"/><xsl:apply-templates select="en:attribute" mode="manager_id35824689"/><xsl:text>
			stmt.set</xsl:text><xsl:for-each select="en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.OutdatedRecordException();
			}</xsl:text><xsl:apply-templates select="en:attribute" mode="manager_id35824713"/><xsl:text>
			</xsl:text><xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35824720"/><xsl:text>
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(query, e);
		}
	}</xsl:text><xsl:text>
	
	</xsl:text>/**
	 */
	public void pullChanges() throws <xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException</xsl:text> {<xsl:text>
		pullChanges(0);
	</xsl:text>}<xsl:text>
	
	</xsl:text><xsl:text>/**
	 *
	 */
	@Override
	public void pullChanges(int depth) throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		</xsl:text>throw new RuntimeException("Not yet implemented");<xsl:text>
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Deletes the associated data inside the database
	 */
	@Override
	public void delete() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		String query = "DELETE FROM " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text> + " WHERE \"</xsl:text><xsl:value-of select="en:attribute[@use = 'id']/@name"/><xsl:text>\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text><xsl:for-each select="en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(1, _</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/></xsl:call-template><xsl:text>);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
		exists = false;
	}</xsl:text><xsl:text>

	</xsl:text><xsl:text>@Override
	public boolean exists() {
		return exists;
	}</xsl:text><xsl:text>
	</xsl:text><xsl:text>
	public int compareTo(</xsl:text><xsl:value-of select="@name"/><xsl:text> entity) {
		if (!exists) {
			if (this == entity) return 0;
			if (this.hashCode() &lt;= entity.hashCode()) return -7;
			return 7;
		}
		if (entity == null) return -4711;
		if (equals(entity)) {
			return 0;
		}
		return </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.GenericRelationManager.compareValues(id(), entity.id());
	}</xsl:text><xsl:text>
	</xsl:text><xsl:text>
	public int compareTo(</xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof </xsl:text><xsl:value-of select="@name"/><xsl:text>) {
			return compareTo((</xsl:text><xsl:value-of select="@name"/><xsl:text>) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}</xsl:text><xsl:text>
	</xsl:text><xsl:text>
	public boolean equals(</xsl:text><xsl:value-of select="@name"/><xsl:text> entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof </xsl:text><xsl:value-of select="@name"/><xsl:text>) {
			return equals((</xsl:text><xsl:value-of select="@name"/><xsl:text>) obj);
		}
		return false;
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:for-each select="/en:relations/en:relationship[@subject = $entity-name]"><xsl:text>/**
	 * Retrieves the first java-object which is related to this java-object as via </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>.
	 * &lt;p&gt;
	 * This is the subject of </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>.
	 
	 * @return The object regarding </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> where this (the java-object you call objectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> on) is the subject.
	 * @since Iteration3
	 */
	public </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@object"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> objectOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@object"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt; coll = objectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Retrieves all objects which are related to this object as via </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>. If you know that there will be only
	 * 1:1 relationships of the type </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>, {@link #objectOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>} is what you are looking for.
	 * &lt;p&gt;
	 * This is the subject of </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>.
	 * @return The object regarding </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> where this (the java-object you call objectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> on) is the subject.
	 */
	public java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@object"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt; objectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		String query = "SELECT * FROM " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @object, '&quot;')"/></xsl:call-template><xsl:text>
			+ " WHERE \"</xsl:text><xsl:value-of select="/en:relations/en:entity[@name = current()/@object]/en:attribute[@use = 'id']/@name"/><xsl:text>\" IN (SELECT \"</xsl:text><xsl:value-of select="en:attribute[@use = 'object']/@name"/><xsl:text>\" FROM "
			+ </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text> + " WHERE \"</xsl:text><xsl:value-of select="en:attribute[@use = 'subject']/@name"/><xsl:text>\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text><xsl:for-each select="/en:relations/en:entity[@name = current()/@subject]/en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@object"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt; ret = new java.util.ArrayList&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@object"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt;();
			while (result.next()) {
				ret.add(new </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@object"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 */
	public java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>&gt; whereSubjectOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		String query = "SELECT * FROM " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text>
			+ " WHERE \"</xsl:text><xsl:value-of select="en:attribute[@use = 'subject']/@name"/><xsl:text>\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text><xsl:for-each select="/en:relations/en:entity[@name = current()/@subject]/en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>&gt; ret = new java.util.ArrayList&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>&gt;();
			while (result.next()) {
				ret.add(new </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
	}</xsl:text><xsl:text>
	
	</xsl:text></xsl:for-each><xsl:for-each select="/en:relations/en:relationship[@object = $entity-name]"><xsl:text>/**
	 * Retrieves the first java-object which is related to this java-object as via </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>.
	 * &lt;p&gt;
	 * This is the object of </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>.
	 * @return The subject regarding </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> where this (the java-object you call subjectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> on) is the object.
	 * @since Iteration3
	 */
	public </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@subject"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> subjectOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@subject"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt; coll = subjectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Retrieves all java-objects which are related to this java-object as via </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>. If you know that there will be only
	 * 1:1 relationships of the type </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>, {@link #subjectOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>} is what you are looking for.
	 * &lt;p&gt;
	 * This is the object of </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>.
	 * @return A Collection of subjects regarding </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> where this (the java-object you call subjectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text> on) is the object.
	 */
	public java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@subject"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt; subjectsOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		String query = "SELECT * FROM " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @subject, '&quot;')"/></xsl:call-template><xsl:text>
			+ " WHERE \"</xsl:text><xsl:value-of select="/en:relations/en:entity[@name = current()/@subject]/en:attribute[@use = 'id']/@name"/><xsl:text>\" IN (SELECT \"</xsl:text><xsl:value-of select="en:attribute[@use = 'subject']/@name"/><xsl:text>\" FROM "
			+ </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text> + " WHERE \"</xsl:text><xsl:value-of select="en:attribute[@use = 'object']/@name"/><xsl:text>\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text><xsl:for-each select="/en:relations/en:entity[@name = current()/@object]/en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@subject"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt; ret = new java.util.ArrayList&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@subject"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>&gt;();
			while (result.next()) {
				ret.add(new </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="@subject"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(query, e);
		}
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>&gt; whereObjectOf</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>() throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException {
		String query = "SELECT * FROM " + </xsl:text><xsl:call-template name="escapeLiterally"><xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/></xsl:call-template><xsl:text>
			+ " WHERE \"</xsl:text><xsl:value-of select="en:attribute[@use = 'object']/@name"/><xsl:text>\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text><xsl:for-each select="/en:relations/en:entity[@name = current()/@object]/en:attribute[@use = 'id']"><xsl:call-template name="javasqltype"/></xsl:for-each><xsl:text>(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>&gt; ret = new java.util.ArrayList&lt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>&gt;();
			while (result.next()) {
				ret.add(new </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="first" select="boolean(false)"/><xsl:with-param name="theString" select="@name"/></xsl:call-template><xsl:text>(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException(e);
		}
	}</xsl:text></xsl:for-each><xsl:if test="@use = 'user'"><xsl:text>
	
	</xsl:text><xsl:text>@Override
	public boolean hasPrivilege(String privilege) {
		if (isSuperUser()) {
			return true;
		}
		String query = "SELECT may(?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			
			java.sql.ResultSet result = manager.executeQuery(stmt);
			result.next();
			return result.getBoolean(1);
		} catch (java.sql.SQLException e) {
			return false;
		}
	}</xsl:text><xsl:text>

	</xsl:text><xsl:text>@Override
	public boolean hasPrivilege(String privilege, Object target) {
		return hasPrivilege(privilege, target.toString());
	}</xsl:text><xsl:text>

	</xsl:text><xsl:text>@Override
	public boolean hasPrivilege(String privilege, String target) {
		if (isSuperUser()) {
			return true;
		}
		String query = "SELECT may(?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			
			java.sql.ResultSet result = manager.executeQuery(stmt);
			result.next();
			return result.getBoolean(1);
		} catch (java.sql.SQLException e) {
			return false;
		}
	}</xsl:text><xsl:text>
	
	</xsl:text><xsl:text>@Override
	public boolean isSuperUser() {
		return getIsSuperuser();
	}</xsl:text></xsl:if><xsl:text>
	
	</xsl:text><xsl:for-each select="en:attribute"><xsl:text>
	</xsl:text><xsl:if test="not(@ref)"><xsl:if test="not(@type = 'password')"><xsl:text>
	@</xsl:text><xsl:value-of select="$weavePackageXmlAnnotation"/><xsl:text>.XmlAttribute("</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>")</xsl:text></xsl:if></xsl:if><xsl:if test="@ref"><xsl:text>
	@</xsl:text><xsl:value-of select="$weavePackageXmlAnnotation"/><xsl:text>.XmlElement("</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>")
	@</xsl:text><xsl:value-of select="$weavePackageXmlAnnotation"/><xsl:text>.XmlDependency("is</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>Fetched")</xsl:text></xsl:if><xsl:text>
	@</xsl:text><xsl:value-of select="$weavePackageOrmAnnotation"/><xsl:text>.Attribute(name = "</xsl:text><xsl:value-of select="@name"/><xsl:text>"</xsl:text><xsl:if test="@serial"><xsl:text>, serial = true</xsl:text></xsl:if><xsl:if test="@nullable = 'true'"><xsl:text>, nullable = true</xsl:text></xsl:if><xsl:if test="@use = 'id'"><xsl:text>, primary = true</xsl:text></xsl:if><xsl:if test="@hidden = 'true'"><xsl:text>, hidden = true</xsl:text></xsl:if><xsl:if test="@use"><xsl:text>, use = "</xsl:text><xsl:value-of select="@use"/><xsl:text>"</xsl:text></xsl:if><xsl:if test="@ref"><xsl:text>, ref = </xsl:text><xsl:value-of select="@ref"/><xsl:text>.class</xsl:text></xsl:if><xsl:text>)
	public </xsl:text><xsl:call-template name="type"/><xsl:text> get</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>()</xsl:text><xsl:if test="@ref"><xsl:text> throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.DatabaseException</xsl:text></xsl:if><xsl:text> {</xsl:text><xsl:if test="@nullable = 'true'"><xsl:text>
		if (</xsl:text><xsl:if test="@ref"><xsl:text>ref</xsl:text></xsl:if><xsl:text>_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> == null) {
			return null;
		}</xsl:text></xsl:if><xsl:if test="@ref"><xsl:text>
		if (!fetched_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>) {
			_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = manager.get</xsl:text><xsl:call-template name="type"/><xsl:text>(ref_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>);
			fetched_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = true;
		}</xsl:text></xsl:if><xsl:text>
		return _</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>;
	}</xsl:text><xsl:if test="@nullable = 'true'"><xsl:text>
	</xsl:text><xsl:text>
	public boolean has</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>() {
		return _</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> != null;
	}</xsl:text></xsl:if><xsl:text>
	
	</xsl:text><xsl:if test="@ref"><xsl:text>/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean is</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>Fetched() {
		return fetched_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>;
	}</xsl:text></xsl:if><xsl:text>
	
	</xsl:text><xsl:if test="not(@use = 'id')"><xsl:text>/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean is</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>Changed() {
		return changed_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>;
	}</xsl:text></xsl:if><xsl:if test="@ref"><xsl:text>
	</xsl:text><xsl:text>
	@</xsl:text><xsl:value-of select="$weavePackage"/><xsl:text>.xml.annotation.XmlAttribute("</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>")
	public </xsl:text><xsl:if test="@nullable = 'true'"><xsl:text>Integer</xsl:text></xsl:if><xsl:if test="not(@nullable = 'true')"><xsl:text>int</xsl:text></xsl:if><xsl:text> ref</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>() {
		return ref_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>;
	}</xsl:text></xsl:if><xsl:text>
	</xsl:text><xsl:if test="@use = 'timestamp'"><xsl:text>
	public </xsl:text><xsl:call-template name="type"/><xsl:text> timekey() {
		return _</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>;
	}
	
	public boolean isValidTimekey(</xsl:text><xsl:call-template name="type"/><xsl:text> key) {
		return </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.GenericRelationManager.compareValues(_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>, key) == 0;
	}
	
	public void checkTimekey(</xsl:text><xsl:call-template name="type"/><xsl:text> key) throws </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new </xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.OutdatedRecordException();
		}
	}</xsl:text></xsl:if><xsl:text>
</xsl:text></xsl:for-each><xsl:text>
	</xsl:text><xsl:for-each select="en:attribute"><xsl:if test="@use = 'timestamp'"><xsl:text>@Override
	protected </xsl:text><xsl:call-template name="type"/><xsl:text> timekey(boolean update) {
		_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = </xsl:text><xsl:choose><xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when><xsl:when test="@type = 'string'"><xsl:value-of select="concat('&quot;', @default, '&quot;')"/></xsl:when><xsl:otherwise><xsl:value-of select="@default"/></xsl:otherwise></xsl:choose><xsl:text>;
		return _</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>;
	}</xsl:text></xsl:if><xsl:text>
	
	</xsl:text><xsl:if test="not(@use = 'id')"><xsl:if test="not(@use = 'timestamp')"><xsl:text>/**
	 * Sets the value of &lt;code&gt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>&lt;/code&gt;. </xsl:text><xsl:if test="@nullable = 'true'"><xsl:text>You may specify null as value.</xsl:text></xsl:if><xsl:text>
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see is</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>Changed()} will return true. If you specify
	 * the same value as the old value is</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>Changed() will return the same as it did
	 * before calling &lt;code&gt;set</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(...)&lt;/code&gt;.
	 * </xsl:text><xsl:if test="not(@nullable = 'true')"><xsl:text>
	 * @throws IllegalArgumentException if the value is &lt;code&gt;null&lt;/code&gt; (since &lt;code&gt;</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>&lt;/code&gt; is not nullable)</xsl:text></xsl:if><xsl:text>
	 * @since Iteration2
	 */
	public </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="parent::*/@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> set</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>(</xsl:text><xsl:call-template name="type"/><xsl:text> value) {
		</xsl:text><xsl:if test="not(@nullable = 'true')"><xsl:text>if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> is not nullable, null given");
		}</xsl:text></xsl:if><xsl:text>
		</xsl:text><xsl:if test="@nullable = 'true'"><xsl:text>if (value == null) {
			clear</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>();
			return this;
		}</xsl:text></xsl:if><xsl:text>
		if (</xsl:text><xsl:value-of select="$weavePackageOrm"/><xsl:text>.GenericRelationManager.compareValues(</xsl:text><xsl:if test="@ref"><xsl:text>ref</xsl:text></xsl:if><xsl:text>_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text>, value</xsl:text><xsl:if test="@ref"><xsl:text>._id</xsl:text></xsl:if><xsl:text>) == 0) return this;
		changed_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = true;</xsl:text><xsl:if test="@ref"><xsl:text>
		ref_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = value._id;
		fetched_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = true;</xsl:text></xsl:if><xsl:text>
		_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = value;
		return this;
	}</xsl:text><xsl:if test="@nullable = 'true'"><xsl:text>
	
	</xsl:text><xsl:text>/**
	 * </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public </xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString" select="parent::*/@name"/><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text> clear</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param><xsl:with-param name="first" select="false"/></xsl:call-template><xsl:text>() {
		if (_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> == null</xsl:text><xsl:if test="@ref"><xsl:text> &amp;&amp; ref_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> == null</xsl:text></xsl:if><xsl:text>) {
			return this;
		}
		_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = null;</xsl:text><xsl:if test="@ref"><xsl:text>
		ref_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = null;</xsl:text></xsl:if><xsl:text>
		changed_</xsl:text><xsl:call-template name="javaize"><xsl:with-param name="theString"><xsl:value-of select="@name"/></xsl:with-param></xsl:call-template><xsl:text> = true;
		return this;
	}</xsl:text></xsl:if></xsl:if></xsl:if><xsl:text>
</xsl:text></xsl:for-each><xsl:text>
}
</xsl:text></xsl:if>
  </xsl:template>
  <xsl:template match="en:entity" mode="manager_id35826085">
    <xsl:text>
	public java.sql.SQLXML export</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(boolean with_schema) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> new</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826097"/>
    <xsl:text>);
	
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> create</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826109"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> fullyCreate</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35826123"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> fullyNew</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35826138"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int offset, int limit, </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int offset, int limit, </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filter, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int limit, </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filter, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filter, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>() throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:for-each select="en:attribute[@use = 'id']">
      <xsl:call-template name="type"/>
    </xsl:for-each>
    <xsl:text> id) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public void delete</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter... filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public void delete</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:for-each select="en:attribute[@use = 'id']">
      <xsl:call-template name="type"/>
    </xsl:for-each>
    <xsl:text> id) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
</xsl:text>
  </xsl:template>
  <xsl:template match="en:entity" mode="manager_id35826876">
    <xsl:text>/**
	 * 
	 */
	public java.sql.SQLXML export</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(boolean with_schema) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return export(</xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text>, with_schema);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Instantiates (but does not {@link </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>#create()}) a </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> with only required attributes.</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826901"/>
    <xsl:text>
	 * @return A new entity-object which is minimally initialized and not yet created in the database.
	 * @since Iteration2
	 */
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> new</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826913"/>
    <xsl:text>) {
		return new </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(this, </xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826923"/>
    <xsl:text>);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Creates a </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> with only required attributes.</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826937"/>
    <xsl:text>
	 * @return A new entity object which is minimally initialized and also existing in the database.
	 * @since Iteration2
	 */
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> create</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826949"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> entity = new</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]" mode="manager_id35826964"/>
    <xsl:text>);
		entity.create();
		return entity;
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Creates a </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> and initializes all attributes.</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35826978"/>
    <xsl:text>
	 * @return A new entity object which is fully initialized and represented in the database.
	 * @since Iteration2
	 */
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> fullyCreate</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35826991"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> entity = fullyNew</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35827005"/>
    <xsl:text>);
		entity.create();
		return entity;
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Instantiates (but does not {@link </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>#create()}) a </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> and initializes all attributes.</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35827022"/>
    <xsl:text>
	 * @return A new entity object which is fully initialized but not represented in the database.
	 * @since Iteration2
	 */
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> fullyNew</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35827035"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> entity = new </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(this, true, </xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]" mode="manager_id35827049"/>
    <xsl:text>);
		return entity;
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Gets </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> entities matching the given filters.
	 *
	 * @param offset In a dataset of 200 items the offset is the position of an item in this set. Selection will start at this point.
	 * @param limit An upper bound for the number of entities to fetch.
	 * @param filters Any number (including zero) of filters.
	 * @throws IllegalArgumentException If offset is negative.
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int offset, int limit, </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		if (offset &lt; 0) {
			throw new IllegalArgumentException("Offset must be 0 or positive.");
		}
		java.lang.StringBuilder limitString = new StringBuilder();
		limitString.append(" LIMIT ");
		if (limit &gt; -1) {
			limitString.append(limit);
		} else {
			limitString.append("ALL");
		}
		if (offset &gt; 0) {
			limitString.append(" OFFSET " + offset);
		}
		String whereString = makeWhereString(filters);
		java.lang.String query = "SELECT * FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			java.sql.ResultSet result = executeQuery(assignFilters(stmt, filters));
			java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; resultCollection = new java.util.ArrayList&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt;();
			while (result.next()) {
				resultCollection.add(new </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException("Database is not available (is it set up?)", e);
		}
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * 
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int offset, int limit, </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filter, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(offset, limit, filter);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * 
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int offset, int limit, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(offset, limit, null, joinColumns);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * 
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(int limit, </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filter, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(0, limit, filter, joinColumns);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * 
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filter, String... joinColumns) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(0, -1, filter, joinColumns);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
     * 
     *
     * @param filters Any number (including zero) of filters.
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(0, -1, filters);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
     * 
     *
     * @param filters Any number (including zero) of filters.
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>&gt; get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>() throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(0, -1);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Gets a single </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> entity by its ID.
	 *
	 * @param id The id of the </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> retrieve
	 * @return The entity or null if no entity is identified by the given id.
	 */
	public </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text> get</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:for-each select="en:attribute[@use = 'id']">
      <xsl:call-template name="type"/>
    </xsl:for-each>
    <xsl:text> id) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		String query = "SELECT * FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + " WHERE \"</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/>
    </xsl:call-template>
    <xsl:text>\" = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text>
    <xsl:for-each select="en:attribute[@use = 'id']">
      <xsl:call-template name="javasqltype"/>
    </xsl:for-each>
    <xsl:text>(1, id);
			java.sql.ResultSet result = executeQuery(stmt);
			if (!result.next()) {
				return null;
			}
			return new </xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(this, result);
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(e);
		}
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Deletes the entities matching the given Filters
	 */
	public void delete</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter... filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		String whereString = (filters.length &gt; 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(e);
		}
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Deletes the entity that is identified by the given id.
	 */
	public void delete</xsl:text>
    <xsl:value-of select="@name"/>
    <xsl:text>(</xsl:text>
    <xsl:for-each select="en:attribute[@use = 'id']">
      <xsl:call-template name="type"/>
    </xsl:for-each>
    <xsl:text> id) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		String query = "DELETE FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + " WHERE \"</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name"/>
    </xsl:call-template>
    <xsl:text>\" = ?;";
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			stmt.set</xsl:text>
    <xsl:for-each select="en:attribute[@use = 'id']">
      <xsl:call-template name="javasqltype"/>
    </xsl:for-each>
    <xsl:text>(1, id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(e);
		}
	}</xsl:text>
    <xsl:if test="@use = 'user'">
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	protected java.sql.ResultSet executeQuery(java.sql.PreparedStatement $stmt) throws java.sql.SQLException {
		return super.executeQuery($stmt);
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>/**
	 * Returns a list of </xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text> as Users. Basically this is the same as {@link #get</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.Filter)}.
	 * &lt;p&gt;
	 * This method is required by {@see de.fu.weave} for basic actions like login and logout. It differs from &lt;code&gt;get</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>(Filter...)&lt;/code&gt; in that it does not return </xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>-objects but User-objects.
	 *
	 * @since Iteration2
	 */
	@Override
	public java.util.List&lt;? extends </xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.User&gt; fetchUsers(</xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.Filter filter) throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		return getPerson(filter);
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>/**
	 * Returns a User identified by the given id. Basically this is the same as {@link #get</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>(int)}. It differs from get</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>(int) in that it does not return a </xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>-objects but a User-object.
	 * @since Iteration2
	 */
	@Override
	public </xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.User fetchUser(int id) throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		return getPerson(id);
	}</xsl:text>
      <xsl:text>
	</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:relationship">
    <xsl:variable name="javaizedName">
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString">
          <xsl:value-of select="@name"/>
        </xsl:with-param>
        <xsl:with-param name="first" test="false"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:if test="$javaizedName = $relation">
      <xsl:text>/**
 * OO-Representation of the relationship-relation </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>
 * &lt;p&gt;
 * </xsl:text>
      <xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]"/>
      <xsl:text>
 * @since Iteration2
 */
@</xsl:text>
      <xsl:value-of select="$weavePackageOrmAnnotation"/>
      <xsl:text>.Relationship(name = "</xsl:text>
      <xsl:value-of name="theString" select="@name"/>
      <xsl:text>", subject = </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>.class, object = </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>.class)
@</xsl:text>
      <xsl:value-of select="$weavePackageOrmAnnotation"/>
      <xsl:text>.Relation(name = </xsl:text>
      <xsl:call-template name="escapeLiterally">
        <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
      </xsl:call-template>
      <xsl:text>, requiredSqlCols = {</xsl:text>
      <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825163"/>
      <xsl:text>})
@</xsl:text>
      <xsl:value-of select="$weavePackage"/>
      <xsl:text>.xml.annotation.XmlElement("</xsl:text>
      <xsl:value-of name="theString" select="@name"/>
      <xsl:text>")
public class </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> extends </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.GenericRelationship {

	</xsl:text>
      <xsl:text>final </xsl:text>
      <xsl:value-of select="$managerName"/>
      <xsl:text> manager;</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:text>boolean exists</xsl:text>
      <xsl:text> = false</xsl:text>
      <xsl:text>;</xsl:text>
      <xsl:text>

	</xsl:text>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825196"/>
      <xsl:text>

	</xsl:text>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825204"/>
      <xsl:text>
	
	</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> subject;</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:text>int</xsl:text>
      <xsl:text> subject_refid;</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> object;</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:text>int</xsl:text>
      <xsl:text> object_refid;</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$managerName"/>
      <xsl:text> manager, </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> subject, </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> object, java.sql.ResultSet result) throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {</xsl:text>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825270"/>
      <xsl:text>	
			this.subject_refid = ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'subject']/@name"/>
      </xsl:call-template>
      <xsl:text>;
			this.object_refid = ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'object']/@name"/>
      </xsl:call-template>
      <xsl:text>;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$managerName"/>
      <xsl:text> manager, java.sql.ResultSet result) throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {</xsl:text>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825318"/>
      <xsl:text>
			this.subject_refid = ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'subject']/@name"/>
      </xsl:call-template>
      <xsl:text>;
			this.object_refid = ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'object']/@name"/>
      </xsl:call-template>
      <xsl:text>;
			this.subject = manager.get</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(this.subject_refid);
			this.object = manager.get</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException(e);
		}
		this.exists = true;
	}</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$managerName"/>
      <xsl:text> manager, </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> subject, </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> object</xsl:text>
      <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825372"/>
      <xsl:text>) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'subject']/@name"/>
      </xsl:call-template>
      <xsl:text> = this.subject = subject;
		this.ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'subject']/@name"/>
      </xsl:call-template>
      <xsl:text> = this.subject_refid = subject.id();
		this._</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'object']/@name"/>
      </xsl:call-template>
      <xsl:text> = this.object = object;
		this.ref_</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="en:attribute[@use = 'object']/@name"/>
      </xsl:call-template>
      <xsl:text> = this.object_refid = object.id();
		timekey(true);
		</xsl:text>
      <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825390"/>
      <xsl:text>
	}</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$managerName"/>
      <xsl:text> manager, boolean full, </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> subject, </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> object</xsl:text>
      <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825420"/>
      <xsl:text>) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		</xsl:text>
      <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825429"/>
      <xsl:text>
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> subject() throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		if (subject == null) {
			subject = manager.get</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@subject"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(subject_refid);
		}
		return subject;
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> object() throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		if (object == null) {
			object = manager.get</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@object"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>(object_refid);
		}
		return object;
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public boolean equals(Object obj) {
		if (obj instanceof </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>) {
			return equals((</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text>) obj);
		}
		return false;
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>public boolean equals(</xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> relationship) {
		try {
			return relationship.subject().equals(subject())
				&amp;&amp; relationship.object().equals(object());
		} catch (</xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException e) {
			return false;
		}
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public </xsl:text>
      <xsl:call-template name="javaize">
        <xsl:with-param name="theString" select="@name"/>
        <xsl:with-param name="first" select="false"/>
      </xsl:call-template>
      <xsl:text> create() throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		String query = "INSERT INTO " + </xsl:text>
      <xsl:call-template name="escapeLiterally">
        <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
      </xsl:call-template>
      <xsl:text> + " (\"</xsl:text>
      <xsl:value-of select="en:attribute[@use = 'subject']/@name"/>
      <xsl:text>\", \"</xsl:text>
      <xsl:value-of select="en:attribute[@use = 'object']/@name"/>
      <xsl:text>\"</xsl:text>
      <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825514"/>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825518"/>
      <xsl:text>)"
			+ " VALUES (?, ?</xsl:text>
      <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825525"/>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825527"/>
      <xsl:text>)</xsl:text>
      <xsl:if test="en:attribute[@use = 'id']/@serial = 'true'">
        <xsl:text> RETURNING </xsl:text>
        <xsl:value-of select="en:attribute[@use = 'id']/@name"/>
      </xsl:if>
      <xsl:text>;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());</xsl:text>
      <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825538"/>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825595"/>
      <xsl:text>
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public boolean exists() {
		return exists;
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public void pushChanges() throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		if (!exists) {
			create();
			return;
		}</xsl:text>
      <xsl:if test="en:attribute[not(@use = 'subject') and not(@use = 'object') and not(@use = 'timestamp')]">
        <xsl:text>
		String query = "UPDATE " + </xsl:text>
        <xsl:call-template name="escapeLiterally">
          <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
        </xsl:call-template>
        <xsl:text> + " SET </xsl:text>
        <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825626"/>
        <xsl:text>"
			+ "</xsl:text>
        <xsl:apply-templates select="en:attribute" mode="manager_id35825632"/>
        <xsl:text> WHERE "</xsl:text>
        <xsl:apply-templates select="en:attribute" mode="manager_id35825638"/>
        <xsl:apply-templates select="en:attribute[@name = current()/en:primary/en:ref/@field and @name != current()/@subject and @name != current()/@object]" mode="manager_id35825644"/>
        <xsl:text>;
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;</xsl:text>
        <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35825651"/>
        <xsl:apply-templates select="en:attribute" mode="manager_id35825676"/>
        <xsl:apply-templates select="en:attribute[@name = current()/en:primary/en:ref/@field and @name != current()/@subject and @name != current()/@object]" mode="manager_id35825694"/>
        <xsl:text>
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new </xsl:text>
        <xsl:value-of select="$weavePackageOrm"/>
        <xsl:text>.OutdatedRecordException(</xsl:text>
        <xsl:call-template name="escapeLiterally">
          <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
        </xsl:call-template>
        <xsl:text> + '#' + id());
			}</xsl:text>
        <xsl:apply-templates select="en:attribute" mode="manager_id35825715"/>
        <xsl:text>
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
        <xsl:value-of select="$weavePackageOrm"/>
        <xsl:text>.DatabaseException(query, e);
		}</xsl:text>
      </xsl:if>
      <xsl:text>
	}</xsl:text>
      <xsl:text>
	</xsl:text>
      <xsl:apply-templates select="en:attribute" mode="manager_id35825727"/>
      <xsl:text>
	
	</xsl:text>
      <xsl:text>@Override
	public void delete() throws </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException {
		String query = "DELETE FROM " + </xsl:text>
      <xsl:call-template name="escapeLiterally">
        <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
      </xsl:call-template>
      <xsl:text>
			+ " WHERE "</xsl:text>
      <xsl:apply-templates select="en:attribute[@name = current()/en:primary/en:ref/@field and @name != current()/@subject and @name != current()/@object]" mode="manager_id35825750"/>
      <xsl:text>;
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;</xsl:text>
      <xsl:apply-templates select="en:attribute[@name = current()/en:primary/en:ref/@field and @name != current()/@subject and @name != current()/@object]" mode="manager_id35825760"/>
      <xsl:text>
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
      <xsl:value-of select="$weavePackageOrm"/>
      <xsl:text>.DatabaseException(query, e);
		}
		exists = false;
	}</xsl:text>
      <xsl:text>
	
	</xsl:text>
      <xsl:for-each select="en:attribute">
        <xsl:text>
	</xsl:text>
        <xsl:if test="not(@ref)">
          <xsl:if test="not(@type = 'password')">
            <xsl:text>
	@</xsl:text>
            <xsl:value-of select="$weavePackageXmlAnnotation"/>
            <xsl:text>.XmlAttribute("</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
            </xsl:call-template>
            <xsl:text>")</xsl:text>
          </xsl:if>
        </xsl:if>
        <xsl:if test="@ref">
          <xsl:text>
	@</xsl:text>
          <xsl:value-of select="$weavePackageXmlAnnotation"/>
          <xsl:text>.XmlElement("</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>")
	@</xsl:text>
          <xsl:value-of select="$weavePackageXmlAnnotation"/>
          <xsl:text>.XmlDependency("is</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
            <xsl:with-param name="first" select="false"/>
          </xsl:call-template>
          <xsl:text>Fetched")</xsl:text>
        </xsl:if>
        <xsl:text>
	@</xsl:text>
        <xsl:value-of select="$weavePackageOrmAnnotation"/>
        <xsl:text>.Attribute(name = "</xsl:text>
        <xsl:value-of select="@name"/>
        <xsl:text>"</xsl:text>
        <xsl:if test="@serial">
          <xsl:text>, serial = true</xsl:text>
        </xsl:if>
        <xsl:if test="@nullable = 'true'">
          <xsl:text>, nullable = true</xsl:text>
        </xsl:if>
        <xsl:if test="@use = 'id'">
          <xsl:text>, primary = true</xsl:text>
        </xsl:if>
        <xsl:if test="@hidden = 'true'">
          <xsl:text>, hidden = true</xsl:text>
        </xsl:if>
        <xsl:if test="@use">
          <xsl:text>, use = "</xsl:text>
          <xsl:value-of select="@use"/>
          <xsl:text>"</xsl:text>
        </xsl:if>
        <xsl:if test="@ref">
          <xsl:text>, ref = </xsl:text>
          <xsl:value-of select="@ref"/>
          <xsl:text>.class</xsl:text>
        </xsl:if>
        <xsl:text>)
	public </xsl:text>
        <xsl:call-template name="type"/>
        <xsl:text> get</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
          <xsl:with-param name="first" select="false"/>
        </xsl:call-template>
        <xsl:text>()</xsl:text>
        <xsl:if test="@ref">
          <xsl:text> throws </xsl:text>
          <xsl:value-of select="$weavePackageOrm"/>
          <xsl:text>.DatabaseException</xsl:text>
        </xsl:if>
        <xsl:text> {</xsl:text>
        <xsl:if test="@nullable = 'true'">
          <xsl:text>
		if (</xsl:text>
          <xsl:if test="@ref">
            <xsl:text>ref</xsl:text>
          </xsl:if>
          <xsl:text>_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text> == null) {
			return null;
		}</xsl:text>
        </xsl:if>
        <xsl:if test="@ref">
          <xsl:text>
		if (!fetched_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>) {
			_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text> = manager.get</xsl:text>
          <xsl:call-template name="type"/>
          <xsl:text>(ref_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>);
			fetched_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text> = true;
		}</xsl:text>
        </xsl:if>
        <xsl:text>
		return _</xsl:text>
        <xsl:call-template name="javaize">
          <xsl:with-param name="theString">
            <xsl:value-of select="@name"/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text>;
	}</xsl:text>
        <xsl:if test="@nullable = 'true'">
          <xsl:text>
	</xsl:text>
          <xsl:text>
	public boolean has</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
            <xsl:with-param name="first" select="false"/>
          </xsl:call-template>
          <xsl:text>() {
		return _</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text> != null;
	}</xsl:text>
        </xsl:if>
        <xsl:text>
	
	</xsl:text>
        <xsl:if test="@ref">
          <xsl:text>/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean is</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
            <xsl:with-param name="first" select="false"/>
          </xsl:call-template>
          <xsl:text>Fetched() {
		return fetched_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>;
	}</xsl:text>
        </xsl:if>
        <xsl:text>
	
	</xsl:text>
        <xsl:if test="not(@use = 'id')">
          <xsl:text>/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean is</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
            <xsl:with-param name="first" select="false"/>
          </xsl:call-template>
          <xsl:text>Changed() {
		return changed_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>;
	}</xsl:text>
        </xsl:if>
        <xsl:if test="@ref">
          <xsl:text>
	</xsl:text>
          <xsl:text>
	@</xsl:text>
          <xsl:value-of select="$weavePackage"/>
          <xsl:text>.xml.annotation.XmlAttribute("</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>")
	public </xsl:text>
          <xsl:if test="@nullable = 'true'">
            <xsl:text>Integer</xsl:text>
          </xsl:if>
          <xsl:if test="not(@nullable = 'true')">
            <xsl:text>int</xsl:text>
          </xsl:if>
          <xsl:text> ref</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
            <xsl:with-param name="first" select="false"/>
          </xsl:call-template>
          <xsl:text>() {
		return ref_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>;
	}</xsl:text>
        </xsl:if>
        <xsl:text>
	</xsl:text>
        <xsl:if test="@use = 'timestamp'">
          <xsl:text>
	public </xsl:text>
          <xsl:call-template name="type"/>
          <xsl:text> timekey() {
		return _</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>;
	}
	
	public boolean isValidTimekey(</xsl:text>
          <xsl:call-template name="type"/>
          <xsl:text> key) {
		return </xsl:text>
          <xsl:value-of select="$weavePackageOrm"/>
          <xsl:text>.GenericRelationManager.compareValues(_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>, key) == 0;
	}
	
	public void checkTimekey(</xsl:text>
          <xsl:call-template name="type"/>
          <xsl:text> key) throws </xsl:text>
          <xsl:value-of select="$weavePackageOrm"/>
          <xsl:text>.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new </xsl:text>
          <xsl:value-of select="$weavePackageOrm"/>
          <xsl:text>.OutdatedRecordException();
		}
	}</xsl:text>
        </xsl:if>
        <xsl:text>
</xsl:text>
      </xsl:for-each>
      <xsl:text>
	
	</xsl:text>
      <xsl:for-each select="en:attribute">
        <xsl:if test="@use = 'timestamp'">
          <xsl:text>@Override
	protected </xsl:text>
          <xsl:call-template name="type"/>
          <xsl:text> timekey(boolean update) {
		_</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text> = </xsl:text>
          <xsl:choose>
            <xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
            <xsl:when test="@type = 'string'">
              <xsl:value-of select="concat('&quot;', @default, '&quot;')"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="@default"/>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:text>;
		return _</xsl:text>
          <xsl:call-template name="javaize">
            <xsl:with-param name="theString">
              <xsl:value-of select="@name"/>
            </xsl:with-param>
          </xsl:call-template>
          <xsl:text>;
	}</xsl:text>
        </xsl:if>
        <xsl:text>
	
	</xsl:text>
        <xsl:if test="not(@use = 'id')">
          <xsl:if test="not(@use = 'timestamp')">
            <xsl:text>/**
	 * Sets the value of &lt;code&gt;</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
            </xsl:call-template>
            <xsl:text>&lt;/code&gt;. </xsl:text>
            <xsl:if test="@nullable = 'true'">
              <xsl:text>You may specify null as value.</xsl:text>
            </xsl:if>
            <xsl:text>
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see is</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
              <xsl:with-param name="first" select="false"/>
            </xsl:call-template>
            <xsl:text>Changed()} will return true. If you specify
	 * the same value as the old value is</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
              <xsl:with-param name="first" select="false"/>
            </xsl:call-template>
            <xsl:text>Changed() will return the same as it did
	 * before calling &lt;code&gt;set</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
              <xsl:with-param name="first" select="false"/>
            </xsl:call-template>
            <xsl:text>(...)&lt;/code&gt;.
	 * </xsl:text>
            <xsl:if test="not(@nullable = 'true')">
              <xsl:text>
	 * @throws IllegalArgumentException if the value is &lt;code&gt;null&lt;/code&gt; (since &lt;code&gt;</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text>&lt;/code&gt; is not nullable)</xsl:text>
            </xsl:if>
            <xsl:text>
	 * @since Iteration2
	 */
	public </xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString" select="parent::*/@name"/>
              <xsl:with-param name="first" select="false"/>
            </xsl:call-template>
            <xsl:text> set</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
              <xsl:with-param name="first" select="false"/>
            </xsl:call-template>
            <xsl:text>(</xsl:text>
            <xsl:call-template name="type"/>
            <xsl:text> value) {
		</xsl:text>
            <xsl:if test="not(@nullable = 'true')">
              <xsl:text>if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> is not nullable, null given");
		}</xsl:text>
            </xsl:if>
            <xsl:text>
		</xsl:text>
            <xsl:if test="@nullable = 'true'">
              <xsl:text>if (value == null) {
			clear</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
                <xsl:with-param name="first" select="false"/>
              </xsl:call-template>
              <xsl:text>();
			return this;
		}</xsl:text>
            </xsl:if>
            <xsl:text>
		if (</xsl:text>
            <xsl:value-of select="$weavePackageOrm"/>
            <xsl:text>.GenericRelationManager.compareValues(</xsl:text>
            <xsl:if test="@ref">
              <xsl:text>ref</xsl:text>
            </xsl:if>
            <xsl:text>_</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
            </xsl:call-template>
            <xsl:text>, value</xsl:text>
            <xsl:if test="@ref">
              <xsl:text>._id</xsl:text>
            </xsl:if>
            <xsl:text>) == 0) return this;
		changed_</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
            </xsl:call-template>
            <xsl:text> = true;</xsl:text>
            <xsl:if test="@ref">
              <xsl:text>
		ref_</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> = value._id;
		fetched_</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> = true;</xsl:text>
            </xsl:if>
            <xsl:text>
		_</xsl:text>
            <xsl:call-template name="javaize">
              <xsl:with-param name="theString">
                <xsl:value-of select="@name"/>
              </xsl:with-param>
            </xsl:call-template>
            <xsl:text> = value;
		return this;
	}</xsl:text>
            <xsl:if test="@nullable = 'true'">
              <xsl:text>
	
	</xsl:text>
              <xsl:text>/**
	 * </xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public </xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString" select="parent::*/@name"/>
                <xsl:with-param name="first" select="false"/>
              </xsl:call-template>
              <xsl:text> clear</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
                <xsl:with-param name="first" select="false"/>
              </xsl:call-template>
              <xsl:text>() {
		if (_</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> == null</xsl:text>
              <xsl:if test="@ref">
                <xsl:text> &amp;&amp; ref_</xsl:text>
                <xsl:call-template name="javaize">
                  <xsl:with-param name="theString">
                    <xsl:value-of select="@name"/>
                  </xsl:with-param>
                </xsl:call-template>
                <xsl:text> == null</xsl:text>
              </xsl:if>
              <xsl:text>) {
			return this;
		}
		_</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> = null;</xsl:text>
              <xsl:if test="@ref">
                <xsl:text>
		ref_</xsl:text>
                <xsl:call-template name="javaize">
                  <xsl:with-param name="theString">
                    <xsl:value-of select="@name"/>
                  </xsl:with-param>
                </xsl:call-template>
                <xsl:text> = null;</xsl:text>
              </xsl:if>
              <xsl:text>
		changed_</xsl:text>
              <xsl:call-template name="javaize">
                <xsl:with-param name="theString">
                  <xsl:value-of select="@name"/>
                </xsl:with-param>
              </xsl:call-template>
              <xsl:text> = true;
		return this;
	}</xsl:text>
            </xsl:if>
          </xsl:if>
        </xsl:if>
        <xsl:text>
</xsl:text>
      </xsl:for-each>
      <xsl:text>
}
</xsl:text>
    </xsl:if>
  </xsl:template>
  <xsl:template match="en:relationship" mode="manager_id35826363">
    <xsl:text>
	public java.util.List&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt; get</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.util.List&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt; get</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>() throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public void delete</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter... filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public void delete</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> fullyNew</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826410"/>
    <xsl:text>);
	
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> fullyCreate</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826426"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> new</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826444"/>
    <xsl:text>);
	
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> create</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826459"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
	
	public java.sql.SQLXML export</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(boolean with_schema) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException;
</xsl:text>
  </xsl:template>
  <xsl:template match="en:relationship" mode="manager_id35826556">
    <xsl:text>/**
	 *
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt; get</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter[] filters = {
			</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.filters.Filters.eq("</xsl:text>
    <xsl:value-of select="en:attribute[@use = 'subject']/@name"/>
    <xsl:text>", subject.id()),
			</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.filters.Filters.eq("</xsl:text>
    <xsl:value-of select="en:attribute[@use = 'object']/@name"/>
    <xsl:text>", object.id())
		};
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = makeWhereString(filters);
		java.lang.String query = "SELECT * FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			java.sql.ResultSet result = executeQuery(assignFilters(stmt, filters));
			java.util.List&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt; resultCollection = new java.util.ArrayList&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt;();
			while (result.next()) {
				resultCollection.add(new </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(this, subject, object, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException("Database is not available (is it set up?)", e);
		}
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 *
	 */
	public java.util.List&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt; get</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>() throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter[] filters = new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter[0];
		java.lang.StringBuilder limitString = new StringBuilder();
		String whereString = makeWhereString(filters);
		java.lang.String query = "SELECT * FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + whereString + limitString.toString();
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			java.sql.ResultSet result = executeQuery(assignFilters(stmt, filters));
			java.util.List&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt; resultCollection = new java.util.ArrayList&lt;</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>&gt;();
			while (result.next()) {
				resultCollection.add(new </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(this, result));
			}
			return resultCollection;
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(query, e);
		} catch (NullPointerException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException("Database is not available (is it set up?)", e);
		}
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Deletes the relationships matching the given filters.
	 */
	public void delete</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.Filter... filters) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		String whereString = (filters.length &gt; 0) ? " WHERE " + translateToSQL(filters) : "";
		java.lang.String query = "DELETE FROM " + </xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text> + whereString;
		try {
			java.sql.PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			assignFilters(stmt, filters).execute();
		} catch (java.sql.SQLException e) {
			throw new </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException(e);
		}
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Deletes the relationship identified by the given subject and object.
	 */
	public void delete</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		delete</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.filters.Filters.all(
			</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.filters.Filters.eq("</xsl:text>
    <xsl:value-of select="en:attribute[@use = 'subject']/@name"/>
    <xsl:text>", subject.id()),
			</xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.filters.Filters.eq("</xsl:text>
    <xsl:value-of select="en:attribute[@use = 'object']/@name"/>
    <xsl:text>", object.id())));
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Creates a new relationship between two Entities. Only the object will be created, not it’s corresponding row in the database.
     * </xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826711"/>
    <xsl:text>
	 */
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> fullyNew</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826726"/>
    <xsl:text>) {
		return new </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(this, true, subject, object</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826735"/>
    <xsl:text>);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * </xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826746"/>
    <xsl:text>
	 */
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> fullyCreate</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826762"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> obj = fullyNew</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(subject, object</xsl:text>
    <xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826775"/>
    <xsl:text>);
		obj.create();
		return obj;
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
     * </xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826785"/>
    <xsl:text>
	 */
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> new</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826801"/>
    <xsl:text>) {
		return new </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(this, subject, object</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826810"/>
    <xsl:text>);
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 * Creates a new relationship between two Entities. The Relationship is automatically created in the database.
     * </xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826820"/>
    <xsl:text>
	 */
	public </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> create</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@subject"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> subject, </xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@object"/>
      <xsl:with-param name="first" select="false"/>
    </xsl:call-template>
    <xsl:text> object</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826836"/>
    <xsl:text>) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text> obj = new</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(subject, object</xsl:text>
    <xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]" mode="manager_id35826850"/>
    <xsl:text>);
		obj.create();
		return obj;
	}</xsl:text>
    <xsl:text>
	
	</xsl:text>
    <xsl:text>/**
	 *
	 */
	public java.sql.SQLXML export</xsl:text>
    <xsl:call-template name="javaize">
      <xsl:with-param name="theString" select="@name"/>
      <xsl:with-param name="first" select="boolean(0)"/>
    </xsl:call-template>
    <xsl:text>(boolean with_schema) throws </xsl:text>
    <xsl:value-of select="$weavePackageOrm"/>
    <xsl:text>.DatabaseException {
		return export(</xsl:text>
    <xsl:call-template name="escapeLiterally">
      <xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')"/>
    </xsl:call-template>
    <xsl:text>, with_schema);
	}</xsl:text>
    <xsl:text>
	</xsl:text>
  </xsl:template>
  <xsl:template match="text()|*|@*"/>
  <xsl:template name="comment-common">
    <xsl:text>@since Iteration2</xsl:text>
  </xsl:template>
  <xsl:template name="type">
    <xsl:choose>
      <xsl:when test="@ref">
        <xsl:value-of select="@ref"/>
      </xsl:when>
      <xsl:when test="@type = 'string'">String</xsl:when>
      <xsl:when test="@type = 'text'">String</xsl:when>
      <xsl:when test="@type = 'integer'">
        <xsl:if test="@nullable = 'true'">Integer</xsl:if>
        <xsl:if test="not(@nullable = 'true')">int</xsl:if>
      </xsl:when>
      <xsl:when test="@type = 'float'">
        <xsl:if test="@nullable = 'true'">Float</xsl:if>
        <xsl:if test="not(@nullable = 'true')">float</xsl:if>
      </xsl:when>
      <xsl:when test="@type = 'double'">
        <xsl:if test="@nullable = 'true'">Double</xsl:if>
        <xsl:if test="not(@nullable = 'true')">double</xsl:if>
      </xsl:when>
      <xsl:when test="@type = 'boolean'">
        <xsl:if test="@nullable = 'true'">Boolean</xsl:if>
        <xsl:if test="not(@nullable = 'true')">boolean</xsl:if>
      </xsl:when>
      <xsl:when test="@type = 'timestamp'">java.sql.Timestamp</xsl:when>
      <xsl:when test="@type = 'time'">java.sql.Time</xsl:when>
      <xsl:when test="@type = 'date'">java.sql.Date</xsl:when>
      <xsl:when test="@type = 'password'">String</xsl:when>
      <xsl:otherwise>
        <xsl:text>Object</xsl:text>
        <xsl:message>Unknown type <xsl:value-of select="@type"/>. Using 'Object' instead.</xsl:message>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="javasqltype">
    <xsl:choose>
      <xsl:when test="@ref">
        <xsl:value-of select="@ref"/>
      </xsl:when>
      <xsl:when test="@type = 'string'">String</xsl:when>
      <xsl:when test="@type = 'text'">String</xsl:when>
      <xsl:when test="@type = 'integer'">Int</xsl:when>
      <xsl:when test="@type = 'float'">Float</xsl:when>
      <xsl:when test="@type = 'double'">Double</xsl:when>
      <xsl:when test="@type = 'boolean'">Boolean</xsl:when>
      <xsl:when test="@type = 'timestamp'">Timestamp</xsl:when>
      <xsl:when test="@type = 'time'">Time</xsl:when>
      <xsl:when test="@type = 'date'">Date</xsl:when>
      <xsl:when test="@type = 'password'">String</xsl:when>
      <xsl:otherwise>
        <xsl:text>Object</xsl:text>
        <xsl:message>Unknown type <xsl:value-of select="@type"/>. Using 'Object' instead.</xsl:message>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="javasqltype2">
    <xsl:param name="type"/>
    <xsl:choose>
      <xsl:when test="$type = 'string'">java.sql.Types.VARCHAR</xsl:when>
      <xsl:when test="$type = 'text'">java.sql.Types.LONGVARCHAR</xsl:when>
      <xsl:when test="$type = 'integer'">java.sql.Types.INTEGER</xsl:when>
      <xsl:when test="$type = 'float'">java.sql.Types.FLOAT</xsl:when>
      <xsl:when test="$type = 'double'">java.sql.Types.DOUBLE</xsl:when>
      <xsl:when test="$type = 'boolean'">java.sql.Types.BOOLEAN</xsl:when>
      <xsl:when test="$type = 'timestamp'">java.sql.Types.TIMESTAMP</xsl:when>
      <xsl:when test="$type = 'date'">java.sql.Types.DATE</xsl:when>
      <xsl:when test="$type = 'time'">java.sql.Types.TIME</xsl:when>
      <xsl:when test="$type = 'password'">java.sql.Types.VARCHAR</xsl:when>
      <xsl:otherwise>
        <xsl:text>Object</xsl:text>
        <xsl:message>Unknown javasql-type2 <xsl:value-of select="$type"/>. Using 'Object' instead.</xsl:message>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
</xsl:transform>
