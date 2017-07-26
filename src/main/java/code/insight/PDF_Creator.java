package code.insight;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDF_Creator {
	
	
	
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
	    URL url = new URL(imageUrl);
	    InputStream is = url.openStream();
	    OutputStream os = new FileOutputStream(destinationFile);

	    byte[] b = new byte[2048];
	    int length;

	    while ((length = is.read(b)) != -1) {
	        os.write(b, 0, length);
	    }

	    is.close();
	    os.close();
	}

	public static void main(String[] args) {
		
		
		
		// Creating PDF document object
		PDDocument document = new PDDocument();
		PDRectangle rectangle=new PDRectangle();
		String imageUrl = "https://cdn2.iconfinder.com/data/icons/aspneticons_v1.0_Nov2006/lock_16x16.gif";
	    String destinationFile = "d:/image.png";
	    

		try {
			saveImage(imageUrl, destinationFile);
			for (int i = 0; i < 2; i++) { // Creating a blank page PDPage
				PDPage blankPage = new PDPage();

				// Adding the blank page to the document
//				document.addPage(blankPage);
				rectangle.setLowerLeftX(0);
	            rectangle.setLowerLeftY(0);
	            rectangle.setUpperRightX(600);
	            rectangle.setUpperRightY(680);
	            
//	            blankPage.setMediaBox(rectangle);
	            blankPage.setCropBox(rectangle);
	            document.addPage(blankPage);
			}
			
			

			// Retrieving the pages of the document
			PDPage page = document.getPage(0);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

			String text = "Lorem ipsum dolor sit amet, consectetur adipiscing <img src='http://kihijbn' >elit. Pellentesque id augue at eros vulputate rhoncus nec ut leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam quis hendrerit tortor. Nulla id interdum enim. Vivamus laoreet cursus enim nec condimentum. Aenean varius eros magna, sed vulputate metus ornare ac. Nam eget velit pulvinar, imperdiet mauris ac, auctor metus. Curabitur elit neque, sodales ac scelerisque sed, interdum ac erat. Integer enim ante, feugiat vitae purus ac, ultricies commodo justo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec semper tempor enim et volutpat. Vivamus at elit at turpis ultrices ullamcorper nec nec lectus. Cras finibus nunc vitae ligula laoreet, et viverra urna consequat. Quisque vel suscipit felis. Nullam non auctor arcu.";
			
			String[] array = null;
			String text2 = null;
			array = WordUtils.wrap(text, 50).split("\\r?\\n");
	        for (int i=0; i< array.length; i++) {
	        	contentStream.beginText();
	        	contentStream.newLineAtOffset(50,600-i*15);
	        	System.out.println(text2);
	        	text2 = array[i];
	                contentStream.showText(text2);
	                contentStream.endText(); 
	            }

		      
//		      contentStream = new PDPageContentStream(document, page);

		    //Creating PDImageXObject object
		      PDImageXObject pdImage = PDImageXObject.createFromFile("d:/image.png",document);
		      
		      
		    //Drawing the image in the PDF document
		      contentStream.drawImage(pdImage, 300,230);

		      System.out.println("Image inserted");
			// Ending the content stream
//			contentStream.endText();

			System.out.println("Content added");

			// Closing the content stream
			contentStream.close();
			
			
			
			
			/*AccessPermission accessPermission = new AccessPermission();
			StandardProtectionPolicy spp = new StandardProtectionPolicy("1234","1234",accessPermission);
			spp.setEncryptionKeyLength(128);
			spp.setPermissions(accessPermission);
			document.protect(spp);
			System.out.println("Document encrypted");*/
			
			// Saving the document
			document.save("D:/my_Pdf.pdf");
			System.out.println("PDF created");

			// Closing the document
			document.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}
}
