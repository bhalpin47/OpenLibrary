<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:text>&lt;books&gt;</xsl:text>
		<xsl:for-each select="books/book">
			<xsl:text>&lt;book&gt;</xsl:text>
			<xsl:text>&lt;bookid&gt;</xsl:text>
			<xsl:value-of select="bookid" />
			<xsl:text>&lt;/bookid&gt;</xsl:text>
			<xsl:text>&lt;title&gt;</xsl:text>
			<xsl:value-of select="title" />
			<xsl:text>&lt;/title&gt;</xsl:text>
			<xsl:text>&lt;statusCode&gt;</xsl:text>
			<xsl:value-of select="statusCode" />
			<xsl:text>&lt;/statusCode&gt;</xsl:text>
			<xsl:text>&lt;/book&gt;</xsl:text>
		</xsl:for-each>
		<xsl:text>&lt;/books&gt;</xsl:text>
	</xsl:template>
</xsl:stylesheet>