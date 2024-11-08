package org.example;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
//import com.aspose.*;
//import com.aspose.cells.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import com.openhtmltopdf.extend.UserAgentCallback;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.xhtmlrenderer.css.constants.IdentValue;
import org.xhtmlrenderer.css.value.FontSpecification;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;


public class downloadFile {
  public String htmlToXhtml(String html) {
    org.jsoup.nodes.Document document = Jsoup.parse(html);
    document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
    return document.html();
  }

  //private FileDownloader(){}

  // public static void main(String args[]) throws IOException{
  // download("http://pdfobject.com/pdf/sample.pdf", new File("sample.pdf"));
  //}

  public void download() throws IOException, DocumentException {
    try{
    String outputPdfFile = "C:\\Users\\aditi_bhowmick\\Documents\\myJSON.pdf";
    //String fileN=this.htmlToXhtml("C:\\Users\\aditi_bhowmick\\Downloads\\SASVA Dashboard.mhtml");
    /**Path filename = Path.of("C:\\Users\\aditi_bhowmick\\Downloads\\Java HTML to PDF - HTML to PDF Converter _ products.aspose.html");
    String outputPdfFile = "C:\\Users\\aditi_bhowmick\\Documents\\myJSON.pdf";
    try {
      URL u = new URL("http" + ':' + '/' + '/' + "10.206.61.1:8086/releases");//http://10.206.61.1:8086/releases/create
      URLConnection urlconnect = u.openConnection();//http://10.206.60.128:8081/psma-int/api/v1/persistent/llm?prompt=get%20once%20we%20receive%20from%20release%20theme
      InputStream stream = urlconnect.getInputStream();
      int i;
      StringBuffer buffer = new StringBuffer();
      while ((i = stream.read()) != -1) {
        //buffer.append((char) i);
        Files.writeString(filename, buffer.append((char) i));
      }*/


      //org.jsoup.nodes.Document doc = Jsoup.connect("http://10.206.61.1:8086/releases").get();
      //org.jsoup.nodes.Document doc1=new org.jsoup.nodes.Document("C:\\Users\\aditi_bhowmick\\Downloads\\SASVA Dashboard 2.htm");

      Path fileName
          = Path.of("Example4.html");
      String s=Files.readString(fileName);
      String xhtmli=htmlToXhtml(s);
      //org.jsoup.nodes.Document document = Jsoup.parse(s, "UTF-8");
      //document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
      File inputHTML = new File("C:\\Users\\aditi_bhowmick\\Downloads\\SASVA Dashboard_12.html");

      //xhtmlToPdfOpen(document,outputPdfFile);
      //Files.writeString(fileName,xhtmli);
      ////AsposeTest asposeTest=new AsposeTest();
      //asposeTest.conv("C:\\Users\\aditi_bhowmick\\Downloads\\SASVA Dashboard_12.xhtml");
      xhtmlToPdf(xhtmli,outputPdfFile);
      //String outputPdfFile = "C:\\Users\\aditi_bhowmick\\Documents\\myJSON.pdf";
      //Files.writeString(filename, doc.html());
      //String outputPdfFile = "C:\\Users\\aditi_bhowmick\\Documents\\myJSON.pdf";
      //Workbook workbook = new Workbook("Example.html");
      //workbook.save(outputPdfFile);**/
      //String outputPdfFile = "C:\\
      // Users\\aditi_bhowmick\\Documents\\myJSON.pdf";
      /**Document document = new Document();
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPdfFile));
      document.open();
      //Workbook workbook = new Workbook("C:\\Users\\aditi_bhowmick\\Downloads\\SASVA Dashboard.mhtml");
      //workbook.save(outputPdfFile);
      XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(fileN));
      //XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("Example.html"));
      document.close();*/
    } catch (Exception e) {
      e.getMessage();
    }
  }
  public void xhtmlToPdf(String xhtml, String outFileName) throws IOException, DocumentException {
    File output = new File(outFileName);
    ITextRenderer iTextRenderer = new ITextRenderer();
    SharedContext contxt = new SharedContext();
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLUE);
    //iTextRenderer.getFontResolver().addFont(contxt, font.getSize());
    contxt = iTextRenderer.getSharedContext();
    contxt.setPrint(true);
    contxt.setInteractive(false);
    contxt.getCss();
    iTextRenderer.setDocumentFromString(xhtml);
    iTextRenderer.layout();
    OutputStream os = new FileOutputStream(output);
    iTextRenderer.createPDF(os);
    os.close();
  }
  private static void xhtmlToPdfOpen(org.jsoup.nodes.Document document, String outFileName) throws IOException, DocumentException {
    try (OutputStream os = new FileOutputStream(outFileName)) {
      PdfRendererBuilder builder = new PdfRendererBuilder();
      builder.withUri(outFileName);
      builder.toStream(os);
      builder.withW3cDocument(new W3CDom().fromJsoup(document), "/");
      builder.run();
    }
  }

}


