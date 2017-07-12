import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePDF extends Thread{
	
	private ArrayList<HashMap<String, Object>> list;
	private int padding = 10;
	
	public GeneratePDF(ArrayList<HashMap<String, Object>> list){
		this.list = list;
	}
	
	public void run(){
		
		try{
			
			Document document = new Document(PageSize.A4, padding, padding, padding, padding);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("layout00.pdf"));
			
			document.open();
			
			for(int i=0; i<list.size(); i++){
				int layout = (int)list.get(i).get("layout");
				switch(layout){
				case 0:
					CreateLayout01 layout01 = new CreateLayout01();
					layout01.create(document, writer);
					break;
				case 1:
					CreateLayout02 layout02 = new CreateLayout02();
					layout02.create(document, writer);
					break;
				case 2:
					CreateLayout03 layout03 = new CreateLayout03();
					layout03.create(document, writer);
					break;
				case 3:
					CreateLayout04 layout04 = new CreateLayout04();
					layout04.create(document, writer);
					break;
				case 4:
					CreateLayout05 layout05 = new CreateLayout05();
					layout05.create(document, writer);
					break;
				}
//				if(i < list.size() -1){
//					document.newPage();
//				}
			}
			
			document.close();
			
			System.out.println("Done!");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
