package code.insight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextPDFCreator {

	public static void main(String[] args) throws IOException, DocumentException {

		try {
			String html = "<html><body> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque id augue at eros vulputate rhoncus nec ut leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam quis hendrerit tortor. Nulla id interdum enim. Vivamus laoreet cursus enim nec condimentum. Aenean varius eros magna, sed vulputate metus ornare ac. Nam eget velit pulvinar, imperdiet mauris ac, auctor metus. Curabitur elit neque, sodales ac scelerisque sed, interdum ac erat. Integer enim ante, feugiat vitae purus ac, ultricies commodo justo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec semper tempor enim et volutpat. Vivamus at elit at turpis ultrices ullamcorper nec nec lectus. Cras finibus nunc vitae ligula laoreet, et viverra urna consequat. Quisque vel suscipit felis. Nullam non auctor arcu </body></html>";
			OutputStream file = new FileOutputStream(new File("d:\\Test.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(html));
			document.close();
			file.close();
			System.out.println("file created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
