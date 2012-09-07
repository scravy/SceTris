<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

	<xsl:template name="login-form">
		<form method="post" name="login" action="../~login/" id="login-form">
			<xsl:call-template name="login-form-content" />
		</form>
	</xsl:template>

	<xsl:template name="login-form-content">

		<xsl:call-template name="input-text">
			<xsl:with-param name="id">username</xsl:with-param>
		</xsl:call-template>

		<xsl:call-template name="input-password">
			<xsl:with-param name="id">password</xsl:with-param>
		</xsl:call-template>
		
		<xsl:call-template name="input-submit">
			<xsl:with-param name="id">submit-login</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template name="input-checkbox">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />
		
		<p class="formField">
			<label for="{$id}">
				<xsl:value-of select="$label" />
			</label>			
			<input type="checkbox" id="{$id}" name="{$name}" class="input-checkbox" value="true">
				<xsl:if test="$value = 'true'">
					<xsl:attribute name="checked">checked</xsl:attribute>
				</xsl:if>
			</input>
		</p>
	</xsl:template>
	
	<xsl:template name="input-password">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />

		<p class="formField">
			<label for="{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="password" id="{$id}" name="{$name}" class="input-password text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
					<xsl:value-of select="$value" />
				</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>

	<xsl:template name="input-text">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />

		<p class="formField">
			<label for="{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="text" id="{$id}" name="{$name}" class="input-text text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
					<xsl:value-of select="$value" />
				</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>

	<xsl:template name="input-date">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />

		<p class="formField">
			<label for="{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="text" id="{$id}" name="{$name}" class="js-datepicker input-date text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
					<xsl:value-of select="$value" />
				</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>
	
	<xsl:template name="input-alternatives">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<!-- <xsl:param name="selected" />  -->
		<xsl:param name="options" />
		
		<p class="formField">
			<label for="{$id}"><xsl:value-of select="$label" /></label>
			<select id="{$id}" name="{$name}">
				<option value="0"><xsl:value-of select="'...'" /></option>
				<xsl:copy-of select="$options" />
			</select>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
		
	</xsl:template>

	<xsl:template name="input-submit">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
		</xsl:param>
		<p class="formControls">
			<input type="submit" id="{$id}" name="{$name}" value="{$label}" class="button-input" />
		</p>
	</xsl:template>






<xsl:template name="textinput">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="value" />

	<!-- adapterpattern :) -->
	<xsl:call-template name="input-text">
		<xsl:with-param name="id" select="$attr" />
		<xsl:with-param name="label" select="$label" />
		<xsl:with-param name="info" select="'is missing in textinput'" />
		<xsl:with-param name="value" select="$value" />
	</xsl:call-template>
	
	<!--
	<div>
		<label for="{$label}">
			<xsl:value-of select="$label" />
		</label>
	</div>
	<input type="text" id="{$label}" name="{$attr}" value="{$value}" />
	<br /> -->
</xsl:template>


<xsl:template name="selectinput">
	<xsl:param name="label" />
	<div>
		<label for="{$label}">
			<xsl:value-of select="$label" />
		</label>
	</div>
</xsl:template>



<xsl:template name="checkboxinput">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="value" />
	
	<div>
		<label for="{$label}">
			<xsl:value-of select="$label" />
		</label>
	</div>
	<input type="checkbox" id="{$label}" name="{$attr}" value="true">
		<xsl:if test="$value='true'" >
			<xsl:attribute name="checked">
				<xsl:value-of select="'checked'" />
			</xsl:attribute>
		</xsl:if>
	</input>
	<br />
</xsl:template>



<xsl:template name="selectperson">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="options" />
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="$label" />
	</xsl:call-template>
	<select id="{$label}" name="{$attr}">
		<xsl:for-each select="$options">
			<xsl:sort select="@id" />
			<option>
				<xsl:if test="//vars:data/item:data/@programManager=@id">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
				<xsl:value-of select="@lastName" />, <xsl:value-of select="@firstName" />
			</option>
		</xsl:for-each>
	</select><br />
</xsl:template>


<xsl:template name="selectprogram">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="options" />
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="$label" />
	</xsl:call-template>
	<select id="{$label}" name="{$attr}">
		<xsl:for-each select="$options">
			<xsl:sort select="@id" />
			<option>
				<!-- <xsl:if test="//vars:data/item:data/@programManager=@id">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>	-->
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
				<xsl:value-of select="@id" /> :: <xsl:value-of select="@academicTerm" />#<xsl:value-of select="@department" />
			</option>
		</xsl:for-each>
	</select><br />
</xsl:template>

<xsl:template name="selectcourse">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="options" />
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="$label" />
	</xsl:call-template>
	<select id="{$label}" name="{$attr}">
		<xsl:for-each select="$options">
			<xsl:sort select="@id" />
			<option>
				<!-- <xsl:if test="//vars:data/item:data/@programManager=@id">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>	-->
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
				<xsl:value-of select="@name" />
			</option>
		</xsl:for-each>
	</select><br />
</xsl:template>



<xsl:template name="listplayer_old">
	<xsl:param name="action" />
	<xsl:param name="limit" />
	<xsl:param name="pages" />
	<xsl:param name="page" />

	<div id="listplayer" class="">
		<form method="get" action="{$action}">
		<!-- <input type="hidden" name="page" value="{$page}" /> -->
		Entries per page: <input type="text" name="limit" value="{$limit}" size="2" /> <input type="submit" value="show" />
		<br />
		<xsl:if test="$pages">
			Page:
		</xsl:if>	
		<ul>
			<xsl:for-each select="$pages">
				<li><a href="?page={.}&amp;limit={$limit}">
					<xsl:if test=".=$page">
						<xsl:attribute name="class">currentpage</xsl:attribute>
					</xsl:if>
					<xsl:value-of select=".+1" />
				</a></li>
			</xsl:for-each>
		</ul>
		</form>
		
	</div>
</xsl:template>


<xsl:template name="listplayer">
	<xsl:param name="action" />
	<xsl:variable name="limit" select="vars:limit" />
	<xsl:variable name="page" select="number(vars:page)" />
	<xsl:variable name="pages" select="vars:pages/item:pages" />

	<div id="listplayer" class="">
		<form method="get" action="{$action}">
		<!-- <input type="hidden" name="page" value="{$page}" /> -->
		Entries par page: <input type="text" name="limit" value="{$limit}" size="2" /> <input type="submit" value="show" />
		<br />
		<xsl:if test="$pages">
			Page:
		</xsl:if>	
		<ul>
			<xsl:for-each select="$pages">
				<li><a href="?page={.}&amp;limit={$limit}">
					<xsl:if test=".=$page">
						<xsl:attribute name="class">currentpage</xsl:attribute>
					</xsl:if>
					<xsl:value-of select=".+1" />
				</a></li>
			</xsl:for-each>
		</ul>
		</form>
		
	</div>
</xsl:template>







</xsl:transform>
