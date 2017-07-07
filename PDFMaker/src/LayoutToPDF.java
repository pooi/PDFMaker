import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class LayoutToPDF {

public static float getHeight(String str, int size){
		
		int line = 0;
		float height = 0;
		// size is 10 then one paragraph height is 15.3
		// size is 12 then one paragraph height is 18
		// size is 18 then one paragraph height is 28.06
		switch(size){
		case 10:
			for(String s : str.split("\n")){
				line += 1;
				line += (s.getBytes().length/183);
			}
			height = 15.3f * line;
			break;
		case 12:
			for(String s : str.split("\n")){
				line += 1;
				line += (s.getBytes().length/151);
			}
			height = 18.0f * line;
			break;
		case 18:
			for(String s : str.split("\n")){
				line += 1;
				line += (s.getBytes().length/100);
			}
			height = 28.06f * line;
			break;
		}
		
		return height;
		
	}

	public static void main(String[] args) throws DocumentException, IOException {
		// TODO Auto-generated method stub
//		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ITextTest.pdf"));document.open();
//		document.add(new Paragraph("First page of the document.하하하"));
//		document.add(new Paragraph(
//				"Some more text on the \first page with different color and font type.", 
//				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)
//				));
//		document.close();
		
//		BaseFont objBaseFont = BaseFont.createFont("font/NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		int padding = 10;
		
		Document document = new Document(PageSize.A4, padding, padding, padding, padding);
		System.out.println(PageSize.A4.getWidth() + ", " + PageSize.A4.getHeight());
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
		
		String title = "제목";
		String content = "내용내용내용내용내용내용내용내용내내용내용내용내용내용내용내용내용내용내용1내용";
		String content2 = "내용내용내용내용내용내용내용내용내내용내용내용내용내용내용내용내용내용내용1내용내용내용내용내용내용내용내용내용내내용내용내용내용내용내용내용내용내용내용1내용내용내용내용내용내용내용내용내용내내용내용내용내용내용내용내용내용내용내용1내용";
		System.out.println(content.getBytes().length);
		
		int line = 0;
		for(String s : content.split("\n")){
			line += 1;
			line += (s.getBytes().length/151);
		}
		System.out.println(line);
		  
		document.open();
		
		
		  
		BaseFont objBaseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font objFont = new Font(objBaseFont, 12);
		// one paragraph height is 18 pixel
//		document.add(new Paragraph("First Page", objFont));
//		document.add(new Paragraph("한글", objFont));

		Paragraph p = new Paragraph(content, new Font(objBaseFont, 18));
		System.out.println(p.getChunks().get(0).getWidthPoint());
		
//		float ascent = objBaseFont.getAscentPoint("Some String", 18);
//		float descent = objBaseFont.getDescentPoint("Some String", 18);
//		float height2 = ascent - descent;
//		System.out.println("height : " + height2);
		
//			System.out.println((i+1) + "page making ...");
		String imageUrl = "136632479348083320170610154426.png";

//	        Image image2 = Image.getInstance(new URL(imageUrl));
//	        document.add(image2);
			
		float width = PageSize.A4.getWidth();
		float height = PageSize.A4.getHeight();
		
		float imgWidth = width;
		float imgHeight = height*2/5;

        
		Image tempImg = Image.getInstance(imageUrl);
		float origWidth = tempImg.getWidth();
		float origHeight = tempImg.getHeight();
		tempImg.scaleAbsoluteWidth(imgWidth);
		tempImg.scaleAbsoluteHeight(origHeight*imgWidth/origWidth);
        Image img = cropImage(tempImg, writer, 0, 0, imgWidth, imgHeight); 
			
//	        img.scaleAbsoluteWidth(imgWidth);
//	        img.scaleAbsoluteHeight(imgHeight);
        img.setAbsolutePosition(0, document.top()-imgHeight+padding);
        document.add(img);
        
        for(int i=0; i<22; i++){
        	document.add(new Paragraph("\n", new Font(objBaseFont, 10)));
        }
        System.out.println(imgHeight);
        
        document.add(new Paragraph(title, new Font(objBaseFont, 18)));
    	document.add(new Paragraph("\n", new Font(objBaseFont, 6)));
        document.add(new Paragraph(content, new Font(objBaseFont, 18)));
	        
//	        if(i < 5-1){
//	        	document.newPage();
//	        }
//		PdfContentByte canvas = writer.getDirectContent();
//		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, padding, document.top() - imgHeight - 10, 0);
		
        
//		PdfTemplate t = writer.getDirectContent().createTemplate(imgWidth, imgHeight);
//		t.rectangle(0, 0, imgWidth, imgHeight);
//		t.clip();
//		t.newPath();
//		t.addImage(img, width, 0, 0, height, 0, 0);
//		document.add(t);
//        document.setPageSize(img);
//        document.newPage();
//        img.setAbsolutePosition(0, document.top()-imgHeight);
//        img.scaleAbsolute(imgWidth, imgHeight);
//        document.add(img);
		  
		document.close();
		
		System.out.println("Done");

		
	}
	
	public static Image cropImage(Image image, PdfWriter writer, float x, float y, float width, float height) throws DocumentException {
	    PdfContentByte cb = writer.getDirectContent();
	    PdfTemplate t = cb.createTemplate(width, height);
	    
	    float origWidth = image.getScaledWidth();
	    float origHeight = image.getScaledHeight();
	    t.addImage(image, origWidth, 0, 0, origHeight, -x, -y);
	    return Image.getInstance(t);
	}

}
