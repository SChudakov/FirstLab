<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Sudents Govenment Events</title>
            </head>
            <body>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>first name</th>
                        <th>middle name</th>
                        <th>last name</th>
                        <th>faculty</th>
                        <th>sub faculty</th>
                        <th>branch of study</th>
                        <th>begin</th>
                        <th>end</th>
                    </tr>
                    <xsl:for-each select="//event">
                        <tr>
                            <th>
                                <xsl:value-of select="last_first_middle_name/first_name"/>
                            </th>
                            <th>
                                <xsl:value-of select="last_first_middle_name/middle_name"/>
                            </th>
                            <th>
                                <xsl:value-of select="last_first_middle_name/last_name"/>
                            </th>
                            <th>
                                <xsl:value-of select="faculty"/>
                            </th>
                            <th>
                                <xsl:value-of select="sub-faculty"/>
                            </th>
                            <th>
                                <xsl:value-of select="branch-of-study"/>
                            </th>
                            <th>
                                <xsl:value-of select="time/begin"/>
                            </th>
                            <th>
                                <xsl:value-of select="time/end"/>
                            </th>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>