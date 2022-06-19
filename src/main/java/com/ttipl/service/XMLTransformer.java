package com.ttipl.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.CommandLineOptions;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Starter;
import org.apache.fop.image.FopImageFactory;
import org.apache.fop.messaging.MessageHandler;

/**
 *
 * @author Pradeep Yarlagadda, Copyright (c) 2009, ThoughtGreen Technologies Pvt
 *         Ltd. Created Date Aug 19, 2009
 */
public class XMLTransformer {
	private String xslFile = null;

	// private String outPut=null;
	/**
	 * This function takes the xml and xsl streamsource, transform it and return the
	 * string DoTransform XMLTransformer String
	 * 
	 * @param Xml
	 * @param Xsl
	 * @return String
	 */
	private static String DoTransform(StreamSource Xml, StreamSource Xsl)
	{

		java.lang.System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
		StreamResult outResult = new StreamResult();
		java.io.ByteArrayOutputStream outputXmlResult = new java.io.ByteArrayOutputStream();
		outResult.setOutputStream(outputXmlResult);
		TransformerFactory trans = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = trans.newTransformer(Xsl);
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		}
		// transformer.setParameter("dir", "output");
		try {
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(Xml, outResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return outputXmlResult.toString();
	}

	public String GetTransformedString(StringReader xmlObj, String format, String reportFileName, String xslPath,
			String reportsPath, String batch, String reportName) throws Exception, TransformerException 
	{
		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat();
		sf.applyPattern("dd-MM-yyyy_HH-mm-s");
		String reportExt = sf.format(new java.util.Date());
		// String reportExt=StringUtils.getTodayDate();
		String filename = null;
		String tempname = reportFileName;
		reportFileName = xslPath + "/" + reportFileName;
		if (batch == null || "0".equalsIgnoreCase(batch.trim()) || "".equalsIgnoreCase(batch.trim())) {
			batch = "";
		} else {
			batch = batch + "_";
		}
		try {
			if (format.equals("pdf")) {
				reportFileName += "_fo";
			}
			xslFile = reportFileName + ".xsl";
			// System.out.println(xslFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			StreamSource inputXml_Source = new StreamSource(xmlObj);
			StreamSource inputXsl_Source = new StreamSource(new java.io.FileInputStream(xslFile));
			inputXsl_Source.setSystemId(xslFile);
			File f = null;
			// f=new File(location+"\\\\"+reportFileName+"_"+reportExt+"."+format);
			f = new File(reportFileName + "." + format);
			try {
				if (!format.equals("pdf")) {
					BufferedWriter out = new BufferedWriter(new FileWriter(f));
					// out.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					// out.close();
					return DoTransform(inputXml_Source, inputXsl_Source).toString();
				}
				if (format.equals("pdf")) {
					f = new File(reportFileName + ".fo");
					BufferedWriter outs = new BufferedWriter(new FileWriter(f));
					// String output=DoTransform(inputXml_Source, inputXsl_Source).toString();
					outs.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					outs.close();
					// System.out.println("File Path "+f.getAbsolutePath());
					filename = batch + reportName + "_" + reportExt + ".pdf";
					String args[] = { "-fo", f.getAbsolutePath(), "-pdf",
							reportsPath + File.separator + batch + reportName + "_" + reportExt + ".pdf" };
					CommandLineOptions options = null;
					FopImageFactory.resetCache();
					try {
						options = new CommandLineOptions(args);
						Starter starter = options.getStarter();
						starter.run();
						// return output;
					} catch (FOPException e) {
						MessageHandler.errorln("" + e.getMessage());
						if (options != null) {
							if (options.isDebugMode().booleanValue()) {
								filename = null;
								e.printStackTrace();
							}
						}
					} catch (java.io.FileNotFoundException e) {
						MessageHandler.errorln("" + e.getMessage());
						System.exit(1);
					}
				} // if
			} catch (Exception e) {
				filename = null;
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	public String GetTransformedStringBatchWise(StringReader xmlObj, String format, String reportFileName,
			String xslPath, String reportsPath, String batch, String reportName, String petDate, String petlocation)
			throws Exception, TransformerException {
		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat();
		sf.applyPattern("dd-MM-yyyy_HH-mm-s");
		String reportExt = sf.format(new java.util.Date());
		String filename = null;
		String tempname = reportFileName;
		reportFileName = xslPath + "/" + reportFileName;
		if (batch == null || "0".equalsIgnoreCase(batch.trim()) || "".equalsIgnoreCase(batch.trim())) {
			batch = "ALL";
		}
		try {
			if (format.equals("pdf")) {
				reportFileName += "_fo";
			}
			xslFile = reportFileName + ".xsl";
			// System.out.println(xslFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			StreamSource inputXml_Source = new StreamSource(xmlObj);
			StreamSource inputXsl_Source = new StreamSource(new java.io.FileInputStream(xslFile));
			inputXsl_Source.setSystemId(xslFile);
			File f = null;
			// f=new File(location+"\\\\"+reportFileName+"_"+reportExt+"."+format);
			f = new File(reportFileName + "." + format);
			try {
				if (!format.equals("pdf")) {
					BufferedWriter out = new BufferedWriter(new FileWriter(f));
					// out.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					// out.close();
					return DoTransform(inputXml_Source, inputXsl_Source).toString();
				}
				if (format.equals("pdf")) {
					f = new File(reportFileName + ".fo");
					BufferedWriter outs = new BufferedWriter(new FileWriter(f));
					// String output=DoTransform(inputXml_Source, inputXsl_Source).toString();
					outs.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					outs.close();
					// System.out.println("File Path "+f.getAbsolutePath());
					filename = petDate + "_Batch-" + batch + "_" + reportName + ".pdf";
					String args[] = { "-fo", f.getAbsolutePath(), "-pdf",
							reportsPath + File.separator + petDate + "_Batch-" + batch + "_" + reportName + ".pdf" };
					CommandLineOptions options = null;
					FopImageFactory.resetCache();
					try {
						options = new CommandLineOptions(args);
						Starter starter = options.getStarter();
						starter.run();
						// return output;
					} catch (FOPException e) {
						MessageHandler.errorln("" + e.getMessage());
						if (options != null) {
							if (options.isDebugMode().booleanValue()) {
								filename = null;
								e.printStackTrace();
							}
						}
					} catch (java.io.FileNotFoundException e) {
						MessageHandler.errorln("" + e.getMessage());
						System.exit(1);
					}
				} // if
			} catch (Exception e) {
				filename = null;
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	public String GetTransformedStringDayEnd(StringReader xmlObj, String format, String reportFileName, String xslPath,
			String reportsPath, String batch, String reportName) throws Exception, TransformerException {
		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat();
		sf.applyPattern("dd-MM-yyyy_HH-mm-s");
		String reportExt = sf.format(new java.util.Date());
		String filename = null;
		String tempname = reportFileName;
		reportFileName = xslPath + "/" + reportFileName;
		try {
			if (format.equals("pdf")) {
				reportFileName += "_fo";
			}
			xslFile = reportFileName + ".xsl";
			// System.out.println(xslFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			StreamSource inputXml_Source = new StreamSource(xmlObj);
			StreamSource inputXsl_Source = new StreamSource(new java.io.FileInputStream(xslFile));
			inputXsl_Source.setSystemId(xslFile);
			File f = null;
			// f=new File(location+"\\\\"+reportFileName+"_"+reportExt+"."+format);
			f = new File(reportFileName + "." + format);
			try {
				if (!format.equals("pdf")) {
					BufferedWriter out = new BufferedWriter(new FileWriter(f));
					// out.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					// out.close();
					return DoTransform(inputXml_Source, inputXsl_Source).toString();
				}
				if (format.equals("pdf")) {
					f = new File(reportFileName + ".fo");
					BufferedWriter outs = new BufferedWriter(new FileWriter(f));
					// String output=DoTransform(inputXml_Source, inputXsl_Source).toString();
					outs.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					outs.close();
					// System.out.println("File Path "+f.getAbsolutePath());
					filename = reportName + "_" + reportExt + ".pdf";
					String args[] = { "-fo", f.getAbsolutePath(), "-pdf",
							reportsPath + File.separator + reportName + "_" + reportExt + ".pdf" };
					CommandLineOptions options = null;
					FopImageFactory.resetCache();
					try {
						options = new CommandLineOptions(args);
						Starter starter = options.getStarter();
						starter.run();
						// return output;
					} catch (FOPException e) {
						MessageHandler.errorln("" + e.getMessage());
						if (options != null) {
							if (options.isDebugMode().booleanValue()) {
								filename = null;
								e.printStackTrace();
							}
						}
					} catch (java.io.FileNotFoundException e) {
						MessageHandler.errorln("" + e.getMessage());
						System.exit(1);
					}
				} // if
			} catch (Exception e) {
				filename = null;
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	public String GetTransformedStringForDetailed(StringReader xmlObj, String format, String reportFileName,
			String xslPath, String reportsPath, String bibNumber, String pid, int session)
			throws Exception, TransformerException {
		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat();
		sf.applyPattern("dd-MM-yyyy_HH-mm-s");
		String reportExt = sf.format(new java.util.Date());
		String filename = null;
		String tempname = reportFileName;
		reportFileName = xslPath + "/" + reportFileName;
		try {
			if (format.equals("pdf")) {
				reportFileName += "_fo";
			}
			xslFile = reportFileName + ".xsl";
			// System.out.println(xslFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			StreamSource inputXml_Source = new StreamSource(xmlObj);
			StreamSource inputXsl_Source = new StreamSource(new java.io.FileInputStream(xslFile));

			inputXsl_Source.setSystemId(xslFile);
			File f = null;

			// f=new File(location+"\\\\"+reportFileName+"_"+reportExt+"."+format);
			f = new File(reportFileName + "." + format);
			try {
				if (!format.equals("pdf")) {
					BufferedWriter out = new BufferedWriter(new FileWriter(f));
					// out.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					// out.close();
					return DoTransform(inputXml_Source, inputXsl_Source).toString();
				}

				if (format.equals("pdf")) {
					f = new File(reportFileName + ".fo");
					BufferedWriter outs = new BufferedWriter(new FileWriter(f));
					// String output=DoTransform(inputXml_Source, inputXsl_Source).toString();
					outs.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					outs.close();
					// System.out.println("File Path "+f.getAbsolutePath());
					if ("".equalsIgnoreCase(bibNumber))
						filename = "Batch-" + session + "-" + tempname + ".pdf";
					else
						filename = "Batch-" + session + "_Bib-" + bibNumber + "_" + pid + "_" + tempname + ".pdf";
					String args[] = { "-fo", f.getAbsolutePath(), "-pdf", reportsPath + File.separator + filename };
					CommandLineOptions options = null;
					try {
						options = new CommandLineOptions(args);
						Starter starter = options.getStarter();
						starter.run();
						// return output;
					} catch (FOPException e) {
						MessageHandler.errorln("" + e.getMessage());
						if (options != null) {
							if (options.isDebugMode().booleanValue()) {
								filename = null;
								e.printStackTrace();
							}
						}
					} catch (java.io.FileNotFoundException e) {
						MessageHandler.errorln("" + e.getMessage());

						System.exit(1);
					}
				} // if
			} catch (Exception e) {
				filename = null;
				e.printStackTrace();
			}
		} catch (Exception e)

		{
			e.printStackTrace();
		}

		return filename;
	}

	public String GetTransformedMultipleString(StringReader xmlObj, String format, String reportFileName,
			String outputFile) throws Exception, TransformerException {

		java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat();
		sf.applyPattern("dd-MM-yyyy_HH-mm-s");
		String reportExt = sf.format(new java.util.Date());
		// String tempname=reportFileName;
		try {
			if (format.equals("pdf")) {
				reportFileName += "_fo";
			}
			xslFile = reportFileName + ".xsl";
			// System.out.println(xslFile);
			// System.out.println(outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			StreamSource inputXml_Source = new StreamSource(xmlObj);
			StreamSource inputXsl_Source = new StreamSource(new java.io.FileInputStream(xslFile));
			inputXsl_Source.setSystemId(xslFile);
			File f = null;
			// f=new File(location+"\\\\"+reportFileName+"_"+reportExt+"."+format);
			f = new File(outputFile + "." + format);
			try {
				if (!format.equals("pdf")) {
					BufferedWriter out = new BufferedWriter(new FileWriter(f));
					// out.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					// out.close();
					return DoTransform(inputXml_Source, inputXsl_Source).toString();
				}
				if (format.equals("pdf")) {
					f = new File(reportFileName + ".fo");
					BufferedWriter outs = new BufferedWriter(new FileWriter(f));
					// String output=DoTransform(inputXml_Source, inputXsl_Source).toString();
					outs.write(DoTransform(inputXml_Source, inputXsl_Source).toString());
					outs.close();
					// System.out.println("File Path "+f.getAbsolutePath());

					String args[] = { "-fo", f.getAbsolutePath(), "-pdf",
							"Reports/sys" + File.separator + outputFile + reportExt + ".pdf" };
					CommandLineOptions options = null;
					try {
						options = new CommandLineOptions(args);
						Starter starter = options.getStarter();
						starter.run();
						// return output;
					} catch (FOPException e) {
						MessageHandler.errorln("" + e.getMessage());
						if (options != null) {
							if (options.isDebugMode().booleanValue()) {
								e.printStackTrace();
							}
						}
					} catch (java.io.FileNotFoundException e) {
						MessageHandler.errorln("" + e.getMessage());
						if (options != null) {
							if (options.isDebugMode().booleanValue()) {
								e.printStackTrace();
							}
						}
						System.exit(1);
					}
				} // if

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e)

		{
			e.printStackTrace();
		}

		return null;
	}

}
