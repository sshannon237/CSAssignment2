package UploadServer;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class UploadServlet extends HttpServlet {
   String DIR_NAME = "./images/";

   protected void doGet(HttpServletRequest request, HttpServletResponse response, boolean fromBrowser) {
      DataOutputStream out = response.getOutputStream();
      // new DataOutputStream(response.getOutputStream());
      if (fromBrowser) {
         try {
            System.out.println("doGet method called");

            out.writeBytes("HTTP/1.1 200 OK\r\n");
            out.writeBytes("Content-Type: text/html\r\n\r\n");

            String htmlPage = "<!DOCTYPE html>" + "<html><head><title>File Upload Form</title></head>"
                    + "<body><h1>Upload file</h1>" + "<form method=\"POST\" action=\"upload\" "
                    + "enctype=\"multipart/form-data\">" + "<input type=\"file\" name=\"fileName\"/><br/><br/>"
                    + "Caption: <input type=\"text\" name=\"caption\"<br/><br/>" + "<br />"
                    + "Date: <input type=\"date\" name=\"date\"<br/><br/>" + "<br />"
                    + "<input type=\"submit\" value=\"Submit\"/>" + "</form>" + "</body></html>";
            out.writeBytes(htmlPage);
         } catch (IOException e) {
            System.err.println(e);
         }
      } else {
         System.out.println(getJSONListing("C:\\tomcat\\webapps\\photogallery\\images"));
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      DataOutputStream out = response.getOutputStream();
      BufferedReader in = request.getInputStream();
      try {
         System.out.println("doPost method called");
         String inputLine = "";
         String input = "";
         String endOfRequest = request.getBoundary() + "--";
         while(!(inputLine = in.readLine()).contains(endOfRequest) ) {
            input += inputLine + "\n";
         }
         System.out.println(input);
         String[] body = input.split(request.getBoundary());

         //System.out.println(body[1]);

        // byte[] image = body[1].getBytes();


         String topPart = "<!DOCTYPE html><html><body><ul>";
         String bottomPart = "</ul></body></html>";
         out.writeBytes("HTTP/1.1 200 OK\r\n");
         out.writeBytes("Content-Type: text/html\r\n\r\n");
         out.writeBytes(topPart + getListing("C:\\tomcat\\webapps\\photogallery\\images") + bottomPart);

      } catch (Exception ex) {
         System.err.println(ex);
      }
   }

   private String getListing(String path) {
      String dirList = "";
      File dir = new File(path);
      String[] child = dir.list();
      List<String> images = new ArrayList<>(Arrays.asList(child));
      List<String> sortedImages = images.stream().sorted().collect(Collectors.toList());
      for (int i = 0; i < sortedImages.size(); i++) {
         if ((new File(path + sortedImages.get(i))).isDirectory())
            dirList += "<li><button type=\"button\">" + sortedImages.get(i) + "</button></li>";
         else
            dirList += "<li>" + sortedImages.get(i) + "</li>";
      }
      return dirList;
   }
   private String getJSONListing(String path) {
      String dirList = "{\n";
      File dir = new File(path);
      String[] chld = dir.list();
      for (int i = 0; i < chld.length; i++) {
            dirList += "\n  " + chld[i];
      }
      dirList += "\n}";
      return dirList;
   }
}