import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	public static void main(String[] args) throws DocumentException, IOException {
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("layout", 0);
		list.add(map);
		
		map = new HashMap<>();
		map.put("layout", 1);
		list.add(map);
		
		map = new HashMap<>();
		map.put("layout", 2);
		list.add(map);
		
		map = new HashMap<>();
		map.put("layout", 3);
		list.add(map);
		
		map = new HashMap<>();
		map.put("layout", 4);
		list.add(map);
		
		map = new HashMap<>();
		map.put("layout", -1);
		list.add(map);
		
		GeneratePDF g = new GeneratePDF(list);
		g.start();
		
		try{
			g.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
