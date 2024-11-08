package org.example;

import com.google.gson.*;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Objects;

public class TextToPdf {
  String x="<img \n" +
      " src=\"Screenshot 2024-07-22 182345.jpg\" \n" +
      " style=\"margin-top:-4px;width:13px;height:15px\" ></img>";
  String y="<img \n" +
      " src=\"Screenshot 2024-07-22 182110.jpg\" \n" +
      " style=\"margin-top:-4px;width:17px;height:17px\" ></img>";
  String z="<img \n" +
      " src=\"Screenshot 2024-07-22 182244.jpg\" \n" +
      " style=\"margin-top:-4px;width:22px;height:22px\" ></img>";

  String a="<img \n" +
      " src=\"Screenshot 2024-07-25 191933.jpg\" \n" +
      " style=\"margin-top:-4px;width:22px;height:22px\" ></img>";

  String b="<img \n" +
      " src=\"Screenshot 2024-07-25 195310.jpg\" \n" +
      " style=\"margin-top:-4px;width:22px;height:22px\" ></img>";

  //Screenshot 2024-07-25 195310
    public void converterClass(String configString) throws IOException, DocumentException {
    String outputPdfFile = "C:\\Users\\aditi_bhowmick\\Documents\\myJSON.pdf";
    StringBuilder html=new StringBuilder();
    Integer epicsTotal = 0, storyTotal =0, taskTotal=0;
    Double sasvaTotal=0.0D, mannualTotal=0.0D;
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (JsonArray) jsonParser.parse(configString);
    html.append("<!DOCTYPE html>\n" +
        "<html>\n" +
        "\n" +
        "<head>\n" +
        "  <style>\n" +
        "  @page { size: A4 landscape;}\n" +
        "  h1 {text-align: center; font-size: 2}\n" +
        "        table{\n" +
        "            width: 100%;\n" +
        "        }\n" +
        "        th{\n" +
        "           font-size:14px;\n" +
        "           text-align: center;\n" +
        "           background-color: #D7FFFF;\n" +
        "        }\n" +
        "        table {\n" +
        "        border-collapse: collapse;\n"+
        "        border: 1px solid grey;}\n" +
        "        th,td{" +
        "           border: 1px solid grey; \n" +
        "           padding: 8px;\n"+
        "          }\n" +
        "    </style>\n" +
        "</head>\n" +
        "\n" +
        "<body>\n" +
        "<h1 style=\"font-size:20px;\"> SASVA Custom Release Plan - Purchase Invoice Manager</font></h1>\n");
        html.append("<h1 align=\"centre\" style=\"font-size:15px;\">").append(z).append(" epics:").append("xyse").append("&nbsp;&nbsp;").append(y).append(" user story:").append("yuio").append("&nbsp;&nbsp;").append(x).append(" tasks:").append("uiop").append("</h1>\n");
        html.append("<h1 align=\"centre\" style=\"font-size:15px;\">").append(a).append("Total efforts with SASVA:").append("withSasva").append("&nbsp;&nbsp;").append(b).append("Total efforts without SASVA:").append("withoutSasva").append("&nbsp;&nbsp;").append("</h1>\n");
        html.append("<table>\n" +
        "  <tr>\n" +
        "    <th style=\"width:30%\";>Title</th>\n" +
        "    <th style=\"width:55%\">Description</th>\n" +
        "    <th>Effort with Sasva(days)</th>\n" +
        "    <th>Effort without Sasva(days)</th>\n" +
        "  </tr>");
    for(int i=0; i<=jsonArray.size()-1; i++) {
      JsonObject s = jsonArray.get(i).getAsJsonObject();
      String arr = String.valueOf(s.get("title"));
      html.append("<tr><td colspan=\"4\" style=\"font-size:12px;\">").append("Use case: " + arr.substring(1, arr.length() - 1)).append("</td></tr>");
      JsonArray epicarrays = (JsonArray) s.get("epics");
      if (Objects.nonNull(epicarrays)) {
        epicsTotal= epicsTotal + epicarrays.size();
        for (int j = 0; j <= epicarrays.size() - 1; j++) {
          JsonObject s1 = epicarrays.get(j).getAsJsonObject();
          String Epictitle = String.valueOf(s1.get("title"));
          String Epicdesc = (String.valueOf(s1.get("description"))).replaceAll("\\\\n", "<br>");
          String withSasvaEffortEpic = String.valueOf(s1.get("aiEffort"));
          String mannualEffortEpic = String.valueOf(s1.get("manualEfforts"));
          sasvaTotal= sasvaTotal+s1.get("aiEffort").getAsDouble();
          mannualTotal=mannualTotal+s1.get("manualEfforts").getAsDouble();
          html.append("<tr><td style=\"font-size:12px;\">").append(z).append(Epictitle.substring(1, Epictitle.length() - 1)).append("</td>");
          html.append(System.lineSeparator());
          html.append("<td style=\"font-size:12px;\">").append(Epicdesc.substring(1, Epicdesc.length() - 1)).append("</td>");
          html.append("<td align=\"center\" style=\"font-size:12px;\">").append(withSasvaEffortEpic.substring(1, withSasvaEffortEpic.length() - 1)).append("</td>");
          html.append("<td align=\"center\" style=\"font-size:12px;\">").append(mannualEffortEpic.substring(1, mannualEffortEpic.length() - 1)).append("</td></tr>");
          JsonArray storiesarrays = (JsonArray) s1.get("stories");
          storyTotal=storyTotal + storiesarrays.size();
          for (int k = 0; k <= storiesarrays.size() - 1; k++) {
            JsonObject s2 = storiesarrays.get(k).getAsJsonObject();
            String storytitle = String.valueOf(s2.get("title"));
            String storydesc = String.valueOf(s2.get("description")).replaceAll("\\\\n", "<br>");
            String withSasvaEffortStory = String.valueOf(s2.get("aiEffort"));
            String mannualEffortStory = String.valueOf(s2.get("manualEfforts"));
            sasvaTotal= sasvaTotal+s2.get("aiEffort").getAsDouble();
            mannualTotal=mannualTotal+s2.get("manualEfforts").getAsDouble();
            JsonArray tasksarrays = (JsonArray) s2.get("tasks");
              html.append("<tr><td style=\"font-size:12px;\">").append(y).append(storytitle.substring(1, storytitle.length() - 1)).append("</td>");
              html.append(System.lineSeparator());
              html.append("<td style=\"font-size:12px;\"><br>").append(storydesc.substring(1, storydesc.length() - 1)).append("<br> </td>");
              html.append("<td align=\"center\" style=\"font-size:12px;\">").append(withSasvaEffortStory.substring(1, withSasvaEffortStory.length() - 1)).append("</td>");
              html.append("<td align=\"center\" style=\"font-size:12px;\">").append(mannualEffortStory.substring(1, mannualEffortStory.length() - 1)).append("</td></tr>");
              taskTotal = taskTotal + tasksarrays.size();

              for (int l = 0; l <= tasksarrays.size() - 1; l++) {
                JsonObject s3 = tasksarrays.get(l).getAsJsonObject();
                String title = String.valueOf(s3.get("title"));
                String desc = String.valueOf(s3.get("description")).replaceAll("\\\\n", "<br>");
                String withSasvaEffortTask = String.valueOf(s3.get("aiEffort"));
                String mannualEffortTask = String.valueOf(s3.get("manualEfforts"));
                sasvaTotal= sasvaTotal + s3.get("aiEffort").getAsDouble();
                mannualTotal=mannualTotal+s3.get("manualEfforts").getAsDouble();
                html.append("<tr><td style=\"font-size:12px;\">").append(x).append(title.substring(1, title.length() - 1)).append("</td>");
                html.append(System.lineSeparator());
                html.append("<td style=\"font-size:12px;\">").append(desc.substring(1, desc.length() - 1)).append("</td>");
                html.append("<td align=\"center\" style=\"font-size:12px;\">").append(withSasvaEffortTask.substring(1, withSasvaEffortTask.length() - 1)).append("</td>");
                html.append("<td align=\"center\" style=\"font-size:12px;\">").append(mannualEffortTask.substring(1, mannualEffortTask.length() - 1)).append("</td></tr>");
              }
            }
          }
      }
    }

    html.append("</table>\n" +
        "</body>\n" +
        "\n" +
        "</html>");
    html.replace(html.indexOf("xyse"), html.indexOf("xyse")+4, String.valueOf(epicsTotal));
    html.replace(html.indexOf("yuio"),html.indexOf("yuio")+4, String.valueOf(storyTotal));
    html.replace(html.indexOf("uiop"),html.indexOf("uiop")+4, String.valueOf(taskTotal));
      html.replace(html.indexOf("withSasva"),html.indexOf("withSasva")+9, String.valueOf(Math.round(sasvaTotal)));
      html.replace(html.indexOf("withoutSasva"),html.indexOf("withoutSasva")+12, String.valueOf(Math.round(mannualTotal)));
    String xhtmli=new downloadFile().htmlToXhtml(html.toString());
    new downloadFile().xhtmlToPdf(xhtmli,outputPdfFile);
  }

}
