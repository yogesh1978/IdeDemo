package org.example;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) throws Exception {

    JsonToPdfConvertor jsonToPdfConvertor=new JsonToPdfConvertor();
    downloadFile d=new downloadFile();
    TextToPdf t=new TextToPdf();
    //t.texttohtml();
    t.converterClass(Files.readString(Path.of("Text567")));
    //d.download();
    //AsposeTest asposeTest=new AsposeTest();
    //asposeTest.conv();
    //asposeTest.conv("C:\\Users\\aditi_bhowmick\\Downloads\\SASVA Dashboard_12.html");

   // RequestGitModel s=new RequestGitModel();
    //s.setName("Aditi");
    //s.setDescription("Testing");

    //String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\aditi_bhowmick\\Downloads\\test.txt")));

    //jsonToPdfConvertor.convertPdf("{\"releaseName\":\"onlinetrading\",\"themeType\":\"CustomRelease\",\"gitConnectorId\":1,\"gitUrl\":\"\",\"scmBranchName\":\"main\",\"state\":\"new\",\"jiraConnectorId\":2,\"projectkey\":\"10035\"}");
    //jsonToPdfConvertor.convertPdf(content);
    //jsonToPdfConvertor.createPdf("C:\\Users\\aditi_bhowmick\\Documents\\myJSON.pdf");

    /**RequestGitModel requestGitModel=new RequestGitModel();

    requestGitModel.setName("TEst34797");
    requestGitModel.setDescription("This is your first repository");

    TestClass testing=new TestClass();

    CallApiGitHub callApiGitHub=new CallApiGitHub();

    callApiGitHub.createRepo(requestGitModel);**/

    System.out.println("Successful");
  }
}