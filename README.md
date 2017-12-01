<h1 align=center>PDFMaker</h1>
<p align=center></p>

<br><br>
## Overview
It is a program that produces one complete PDF file with five layouts. This program uses the itextpdf library to generate pdf files.
<br><br>

## Layouts
<ul>
  <li>Layout 1 - Please refer to <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/layout01.pdf">this</a> file.<li>
  <li>Layout 2 - Please refer to <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/layout02.pdf">this</a> file.<li>
  <li>Layout 3 - Please refer to <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/layout03.pdf">this</a> file.<li>
  <li>Layout 4 - Please refer to <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/layout04.pdf">this</a> file.<li>
  <li>Layout 5 - Please refer to <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/layout05.pdf">this</a> file.<li>
</ul>

<br><br>

## Features

> <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/src/DbConnectionPools.java">Database Connection</a>
```java
  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pageconnected", "pageconnected", "123456");
```

> <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/src/GeneratePDF.java">Create a document and add pages using layout.</a>
```java
  // Create a document
  Document document = new Document(PageSize.A4, padding, padding, padding, padding);
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(title));
  
  ...
  
  // Add pages using layout
  for(int i=0; i<list.size(); i++){
    HashMap<String, Object> map = list.get(i);
		int layout = (int)map.get("layout");
		switch(layout){
		case 0:
			CreateLayout01 layout01 = new CreateLayout01();
			layout01.create(document, writer, map);
			break;
		case 1:
			CreateLayout02 layout02 = new CreateLayout02();
			layout02.create(document, writer, map);
			break;
		case 2:
			CreateLayout03 layout03 = new CreateLayout03();
			layout03.create(document, writer, map);
			break;
		case 3:
			CreateLayout04 layout04 = new CreateLayout04();
			layout04.create(document, writer, map);
			break;
		case 4:
			CreateLayout05 layout05 = new CreateLayout05();
			layout05.create(document, writer, map);
			break;
		case -1:
			CreateLayoutImage layoutImage = new CreateLayoutImage();
			layoutImage.create(document, writer, map);
			break;
		}
	}
```

<br><br>

## Result
Please refer to <a href="https://github.com/pooi/PDFMaker/blob/master/PDFMaker/layout00.pdf">this</a> file.
