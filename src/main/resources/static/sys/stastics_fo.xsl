<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:saxon="http://icl.com/saxon"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	extension-element-prefixes="saxon">
	<xsl:template match="/row">
		<fo:root language="IT"
			xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="A4-portrail" page-height="297mm" page-width="210mm"
					margin-top="10mm" margin-bottom="5mm" margin-left="5mm"
					margin-right="5mm">
					<fo:region-body margin-top="5.5cm"
						margin-bottom="3cm" margin-left="3mm" margin-right="7mm" />
					<fo:region-before region-name="xsl-region-before"
						extent="55mm" display-align="before" margin-top="10mm"
						margin-right="10mm" margin-left="20mm" />
					<fo:region-after region-name="xsl-region-after"
						extent="15mm" display-align="after" precedence="true"
						margin-right="10mm" />

				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrail">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block font-family="Helvetica, Arial, sans"
						border-color="black" border-width="0.2mm" border-style="solid">
					</fo:block>
					<!-- <fo:block font-family="Helvetica, Arial, sans" font-size="10px" 
						line-height="15pt"> <fo:inline font-family="Helvetica, Arial, sans" font-size="10px" 
						text-align="start" line-height="15pt" space-after="6cm"> Page No. <fo:page-number 
						/> </fo:inline> <fo:inline font-family="Helvetica, Arial, sans" font-size="10px" 
						line-height="15pt"> Copyright 2020 </fo:inline> </fo:block> -->
					<fo:block font-family="Helvetica, Arial, sans"
						font-size="10px" line-height="15pt" text-align="end">

						<!-- <fo:inline font-family="Helvetica, Arial, sans" font-size="10px" 
							line-height="15pt"> Copyright 2020 </fo:inline> -->
						<!-- <fo:inline text-align="end"> -->
						Page
						<fo:page-number />
						of
						<fo:page-number-citation ref-id="end" />
						<!-- </fo:inline> -->
					</fo:block>
				</fo:static-content>

				<fo:static-content flow-name="xsl-region-before">

					<fo:block text-align="center" font-weight="bold">Candidate
						Q&amp;A Statistics Report
					</fo:block>
					<fo:block font-family="Helvetica, Arial, sans"
						font-size="10px" padding-top="20px">
					</fo:block>

					<fo:table table-layout="fixed">
						<fo:table-column column-width="13cm" />
						<fo:table-column column-width="6cm" />
						<fo:table-body font-size="95%">
							<fo:table-row height="20pt">
								<fo:table-cell background-color="#FFFFFF"
									padding-left="2px" height="0.6cm" text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" padding-right="10mm" font-size="10px">
										<fo:inline font-weight="bold"> Organization: </fo:inline>
										<xsl:value-of select="organization" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px">
										<fo:inline font-weight="bold"> Exam Code:
										</fo:inline>
										<xsl:value-of select="examCode" />
									</fo:block>
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" padding-right="10mm" font-size="10px">
										<fo:inline font-weight="bold"> Candidate Name:
										</fo:inline>
										<xsl:value-of select="name" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" padding-right="10mm" font-size="10px">
										<fo:inline font-weight="bold"> H.T Number:
										</fo:inline>
										<xsl:value-of select="HTNumber" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px">
										<fo:inline font-weight="bold"> Post Name:
										</fo:inline>
										<xsl:value-of select="postName" />
									</fo:block>
								</fo:table-cell>

								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px">
										<fo:inline font-weight="bold"> Exam Location: </fo:inline>
										<xsl:value-of select="examLocation" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" padding-right="10mm" font-size="10px">
										<fo:inline font-weight="bold"> Total Questions:
										</fo:inline>
										<xsl:value-of select="totalQuestions" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px">
										<fo:inline font-weight="bold"> Exam Duration: </fo:inline>
										<xsl:value-of select="examDuration" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="20pt">
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" padding-right="10mm" font-size="10px">
										<fo:inline font-weight="bold"> Report Generated On:
										</fo:inline>
										<xsl:value-of select="generatedTime" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px">
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block font-family="Helvetica, Arial, sans"
						font-size="10px" padding-top="10px">
					</fo:block>
					<fo:block font-family="Helvetica, Arial, sans"
						line-height="15pt" font-size="10px" border-color="black"
						border-width="0.2mm" border-style="solid">
					</fo:block>
					<fo:block font-family="Helvetica, Arial, sans"
						font-size="5px" padding-top="20px">
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body"
					border-collapse="collapse" reference-orientation="0">
					<fo:table table-layout="fixed" width="19cm"
						font-size="10pt" text-align="left" display-align="center"
						space-after="5mm" table-omit-header-at-break="true">
						<fo:table-column column-width="2.5cm" />
						<fo:table-column column-width="10.5cm" />
						<fo:table-column column-width="2.5cm" />
						<fo:table-column column-width="3.5cm" />

						<fo:table-header>

							<fo:table-row height="1.5cm">
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="start" width="2cm">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px" font-weight="bold">S.No
									</fo:block>
								</fo:table-cell>
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="left" width="9cm">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px" font-weight="bold">
										Question
									</fo:block>
								</fo:table-cell>

								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="left" width="2.5cm">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px" font-weight="bold">
										Time
										Answered
									</fo:block>
								</fo:table-cell>
								<fo:table-cell display-align="center"
									background-color="#FFFFFF" padding-left="2px" height="0.6cm"
									text-align="left" width="3cm">
									<fo:block font-family="Helvetica, Arial, sans"
										line-height="15pt" font-size="10px" font-weight="bold">
										Remaining Time
									</fo:block>
								</fo:table-cell>

							</fo:table-row>
						</fo:table-header>
						<xsl:for-each select="list">
							<fo:table-body font-size="100%">
								<fo:table-row height="3cm">

									<fo:table-cell display-align="center"
										background-color="#FFFFFF" padding-left="2px" height="0.6cm"
										text-align="start">
										<fo:block font-family="Helvetica, Arial, sans"
											line-height="15pt" font-size="9px" width="2cm">

											<xsl:value-of select="position()" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell display-align="center"
										background-color="#FFFFFF" padding-left="2px" height="0.6cm"
										text-align="left" width="9cm">
										<fo:block font-family="Helvetica, Arial, sans"
											line-height="15pt" font-size="9px">
											<xsl:value-of select="question" />
										</fo:block>
									</fo:table-cell>

									<fo:table-cell display-align="center"
										background-color="#FFFFFF" padding-left="2px" height="0.6cm"
										text-align="left">
										<fo:block font-family="Helvetica, Arial, sans"
											line-height="15pt" font-size="9px">
											<xsl:value-of select="timeAnswered" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell display-align="center"
										background-color="#FFFFFF" padding-left="2px" height="0.6cm"
										text-align="left">
										<fo:block font-family="Helvetica, Arial, sans"
											line-height="15pt" font-size="9px">

											<xsl:value-of select="remainingTime" />

										</fo:block>
									</fo:table-cell>


								</fo:table-row>
							</fo:table-body>
						</xsl:for-each>

					</fo:table>
					<fo:block id="end"></fo:block>
				</fo:flow>

			</fo:page-sequence>
		</fo:root>

	</xsl:template>
</xsl:transform>