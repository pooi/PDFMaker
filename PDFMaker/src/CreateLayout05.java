import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.pdf.draw.LineSeparator;

public class CreateLayout05 {
	
	private int padding = 10;

	public void create(Document document, PdfWriter writer) throws DocumentException, IOException {
		// TODO Auto-generated method stub
//		Document document = new Document(PageSize.A4, padding, padding, padding, padding);
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("layout05.pdf"));

		document.setMargins(padding, padding, padding, padding);
		document.newPage();
		
		float width = PageSize.A4.getWidth() - padding*2;
		float height = PageSize.A4.getHeight();
		
		String title = "제목";
		String content = "내용내용내용내용내용\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n내용내용내용내내용내용내용내용내용내용내용내용내용내용내용";
		ArrayList<String> referenceList = new ArrayList<>();
		referenceList.add("http://naver.com");
		referenceList.add("http://news.sbs.co.kr/news/endPage.do?news_id=N1004287114");
		
		String reference = "";
		for(int i=0; i<referenceList.size(); i++){
			reference += referenceList.get(i);
			if(i < referenceList.size()-1){
				reference += "\n";
			}
		}

		BaseFont objBaseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font objFont = new Font(objBaseFont, 12);
		  
//		document.open();
		
	    
//	    float emptyFontSize = (float) ((imgHeight-padding) / 1.5);
//	    document.add(new Paragraph(" ", new Font(objBaseFont, emptyFontSize)));
	    
//	    Add title
	    
	    int titleFontSize = 18;
	    while(objBaseFont.getWidthPoint(title, titleFontSize)/2 > width-(padding*2)){
	    	titleFontSize -= 1;
	    }
	    document.add(new Paragraph(title, new Font(objBaseFont, titleFontSize)));
	    
	    
//	    Add content
	    
	    float contentHeight = height - (padding*2) - getHeightEng(width - padding*2, reference, 10) - getHeight(width, title, titleFontSize) - 10;
	    
	    int contentFontSize = 12;
	    while(getHeight(width, content, contentFontSize) > contentHeight){
	    	contentFontSize -= 1;
	    }
	    document.add(new Paragraph(content, new Font(objBaseFont, contentFontSize)));
	    
//	    Add reference
	    
	    float emptyContentHeight = contentHeight - getHeight(width, content, contentFontSize);
	    if(emptyContentHeight > 10){
	    	float emptyFontSize = (float)(emptyContentHeight / 1.5);
		    document.add(new Paragraph(" ", new Font(objBaseFont, emptyFontSize)));
	    }
	    
	    LineSeparator sep = new LineSeparator();
	    sep.setOffset(-10);
	    sep.setLineColor(new BaseColor(230, 230, 230));
	    document.add(sep);
	    document.add(new Paragraph(" ", new Font(objBaseFont, (float) (10.0f/1.5))));
	    
	    Paragraph referenceP = new Paragraph(reference, fontBuilder(objBaseFont, 10, new BaseColor(128, 128, 128)));
	    document.add(referenceP);
	    
//	    document.close();
	    
	    System.out.println("Done");

	}
	
	public Font fontBuilder(BaseFont bf, float fontSize, BaseColor color){
		Font font = new Font(bf, fontSize);
		font.setColor(color);
		return font;
	}
	
	public float getHeight(float width, String str, int size) throws DocumentException, IOException{
		
		int line = 0;
		float height = 0;
		float oneLineByteLength = 0.0f;
		
		oneLineByteLength = getOneLineByteLength(width, size);
		
		for(String s : str.split("\n")){
			line += 1;
			line += (s.getBytes().length/oneLineByteLength);
		}
		height = (float) ((size*1.5) * line);
		
		return height;
		
	}
	
	public float getHeightEng(float width, String str, int size) throws DocumentException, IOException{
		
		int line = 0;
		float height = 0;
		float oneLineByteLength = 0.0f;
		
		oneLineByteLength = getOneLineByteLength(width, size)/2;
		
		for(String s : str.split("\n")){
			line += 1;
			line += (s.getBytes().length/oneLineByteLength);
		}
		height = (float) ((size*1.5) * line);
		
		return height;
		
	}

	public float getOneLineByteLength(float width, int fontSize) throws DocumentException, IOException{
		
		String testStr = "아";
		
		BaseFont objBaseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		float bfWidth = objBaseFont.getWidthPoint(testStr, fontSize);
		float bfNum = ((width-(padding*2)) / bfWidth)-1;
//		System.out.println("bfWidth : " + bfWidth + ", bfNum : " + bfNum + ", byte : " + ("아".getBytes().length * bfNum));
		
		return testStr.getBytes().length * bfNum;
		
	}
	
	public Image cropImage(Image image, PdfWriter writer, float x, float y, float width, float height) throws DocumentException {
	    PdfContentByte cb = writer.getDirectContent();
	    PdfTemplate t = cb.createTemplate(width, height);
	    
	    float origWidth = image.getScaledWidth();
	    float origHeight = image.getScaledHeight();
	    t.addImage(image, origWidth, 0, 0, origHeight, -x, -y);
	    return Image.getInstance(t);
	}

}
