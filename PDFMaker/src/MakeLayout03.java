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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class MakeLayout03 {
	
	private static int padding = 10;

	public static void main(String[] args) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		Document document = new Document(PageSize.A4, padding, padding, padding, padding);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("layout03.pdf"));

		document.setMargins(padding, padding, padding, padding);
		
		float width = PageSize.A4.getWidth() - padding*2;
		float height = PageSize.A4.getHeight();
		
		String title = "제목";
		String content = "내용내용내용내용내용내용내용내용내내용내용내용내용내용내용내용내용내용내용내용";
		String imageUrl = "136632479348083320170610154426.png";
		ArrayList<String> referenceList = new ArrayList<>();
		referenceList.add("http://naver.com");
		referenceList.add("http://daum.net");
		
		String reference = "";
		for(int i=0; i<referenceList.size(); i++){
			reference += referenceList.get(i);
			if(i < referenceList.size()-1){
				reference += "\n";
			}
		}

		BaseFont objBaseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font objFont = new Font(objBaseFont, 12);
		  
		document.open();
		
//		Add Image

		float imgWidth = PageSize.A4.getWidth();
		float imgHeight = height;
		{
			Image tempImg = Image.getInstance(imageUrl);
			float origWidth = tempImg.getWidth();
			float origHeight = tempImg.getHeight();
			tempImg.scaleAbsoluteWidth(imgWidth);
			tempImg.scaleAbsoluteHeight(origHeight*imgWidth/origWidth);
		    Image img = cropImage(tempImg, writer, 0, 0, imgWidth, imgHeight); 
			
		    img.setAbsolutePosition(0, document.top()-imgHeight+padding);
		    document.add(img);
		}
	    
//	    Add title
	    
	    int titleFontSize = 18;
	    while(objBaseFont.getWidthPoint(title, titleFontSize) > width-(padding*2)){
	    	titleFontSize -= 1;
	    }
	    
//	    Add content
	    
	    float contentHeight = (height/2) - (padding*2) - getHeight(width, reference, 10) - getHeight(width, title, titleFontSize) - 10;
	    
	    int contentFontSize = 12;
	    while(getHeight(width, content, contentFontSize) > contentHeight){
	    	contentFontSize -= 1;
	    }
	    
	    float totalHeight = (padding*2) + getHeight(width, reference, 10) + getHeight(width, title, titleFontSize) + getHeight(width, content, contentFontSize) + 10;
	    
	    float emptyHeight = height - totalHeight - padding;
	    float emptyFontSize = (float) (emptyHeight / 1.5);
	    document.add(new Paragraph(" ", new Font(objBaseFont, emptyFontSize)));
	    
	    
	    
	    PdfContentByte canvas = writer.getDirectContent();
	    PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.7f);
	    BaseColor bColor = new BaseColor(0, 0, 0);
	    canvas.setColorFill(bColor);
	    canvas.setGState(gs1);
	    canvas.rectangle(0, 0, width, height-emptyHeight);
	    canvas.fillStroke();

	    PdfContentByte canvas2 = writer.getDirectContent();
	    PdfGState gs2 = new PdfGState();
        gs2.setFillOpacity(1.0f);
//	    canvas.setColorFill(bColor);
	    canvas2.setGState(gs2);
	    canvas2.rectangle(0, 0, width, height-emptyHeight);
//	    canvas.fillStroke();
	    
	    ColumnText ct = new ColumnText(canvas2);
//	    ct.setSimpleColumn(rect);
	    ct.setSimpleColumn(padding, 0, width-padding, height-emptyHeight - padding);
//	    ct.addElement(new Paragraph(title, fontBuilder(objBaseFont, titleFontSize, BaseColor.WHITE)));
	    
	    
	    ct.addElement(new Paragraph(title, fontBuilder(objBaseFont, titleFontSize, BaseColor.WHITE)));
	    ct.addElement(new Paragraph(content, fontBuilder(objBaseFont, contentFontSize, BaseColor.WHITE)));
	    
//	    float emptyContentHeight = contentHeight - getHeight(width, content, contentFontSize);
//	    if(emptyContentHeight > 10){
//	    	emptyFontSize = (float)(emptyContentHeight / 1.5);
//		    document.add(new Paragraph(" ", new Font(objBaseFont, emptyFontSize)));
//	    }
	    
	    LineSeparator sep = new LineSeparator();
	    sep.setOffset(-10);
	    sep.setLineColor(new BaseColor(128, 128, 128));
	    ct.addElement(sep);
	    canvas2.setColorStroke(BaseColor.BLACK);
	    canvas2.stroke();
	    ct.addElement(new Paragraph(" ", new Font(objBaseFont, (float) (10.0f/1.5))));
	    
	    Paragraph referenceP = new Paragraph(reference, fontBuilder(objBaseFont, 10, new BaseColor(128, 128, 128)));
	    ct.addElement(referenceP);

	    ct.go();
	    
	    document.close();
	    
	    System.out.println("Done");

	}
	
	public static Font fontBuilder(BaseFont bf, float fontSize, BaseColor color){
		Font font = new Font(bf, fontSize);
		font.setColor(color);
		return font;
	}
	
	public static float getHeight(float width, String str, int size) throws DocumentException, IOException{
		
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

	public static float getOneLineByteLength(float width, int fontSize) throws DocumentException, IOException{
		
		String testStr = "아";
		
		BaseFont objBaseFont = BaseFont.createFont("NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		float bfWidth = objBaseFont.getWidthPoint(testStr, fontSize);
		float bfNum = ((width-(padding*2)) / bfWidth)-1;
//		System.out.println("bfWidth : " + bfWidth + ", bfNum : " + bfNum + ", byte : " + ("아".getBytes().length * bfNum));
		
		return testStr.getBytes().length * bfNum;
		
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
