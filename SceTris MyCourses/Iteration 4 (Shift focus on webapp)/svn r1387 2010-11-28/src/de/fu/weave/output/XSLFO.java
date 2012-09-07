/* XSLFO.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.output;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;

import de.fu.weave.OutputConverter;

/**
 * 
 * @author André Zoufahl
 * @since Iteration4
 */
public class XSLFO implements OutputConverter {

	private final FopFactory fopFactory = FopFactory.newInstance();

	@Override
	public void convert(final Source source, final StreamResult result) {
		OutputStream out = result.getOutputStream();
		if (out == null) {
			throw new IllegalArgumentException();
		}
		try {
			Fop fop = fopFactory.newFop(getMimeType(), out);

			InputStream resource = getClass().getResourceAsStream("XSLFO.xsl");
			if (resource == null) {
				throw new FileNotFoundException("XSLFO.xsl");
			}
			Source xsx = new StreamSource(resource);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(xsx);
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(source, res);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getMimeType() {
		return "application/pdf";
	}

	@Override
	public boolean isAppliedAfterNativeTemplate() {
		return true;
	}

}
