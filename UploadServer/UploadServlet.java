package UploadServer;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Arrays;
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
         String inputLine;
         inputLine = in.readLine();
         System.out.print(inputLine);
         inputLine = in.readLine();
         System.out.print(inputLine);
         inputLine = in.readLine();
         System.out.print(inputLine);
         inputLine = in.readLine();
         System.out.print(inputLine);
         // ByteArrayOutputStream baos = new ByteArrayOutputStream();
         // byte[] content = new byte[1];
         // int bytesRead = -1;
         // for(int i = 0; i < request.getContentLength(); i++) {
         //    baos.write( content, 0, bytesRead );
         // }
         // String s = baos.toString();
         // System.out.println(s);
         // System.out.println(baos.toString());
         // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
         // String inputLine;
         // while((inputLine = bufferedReader.readLine()) != null ) {
         //    System.out.println(inputLine);
         // }
         // StringBuilder result = new StringBuilder();
         // do {
         //    result.append((char) in.read());
         // } while (in.available() > 0);
         // System.out.println(result);

         // String[] formData = result.toString().split("------WebKitFormBoundaryneJb0sBKfo3bdF7H",5);

         // System.out.println(formData[0]);
         // System.out.println(formData[1]);
         // System.out.println(formData[2]);
         // System.out.println(formData[3]);


         // File dir = new File(DIR_NAME);
         // String[] chld = dir.list();
         // List<String> images = new ArrayList<String>(Arrays.asList(chld));
         // List<String> sortedImages =
         // images.stream().sorted().collect(Collectors.toList());
         // sortedImages.forEach(System.out::println);

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
      String dirList = null;
      File dir = new File(path);
      String[] chld = dir.list();
      for (int i = 0; i < chld.length; i++) {
         if ((new File(path + chld[i])).isDirectory())
            dirList += "<li><button type=\"button\">" + chld[i] + "</button></li>";
         else
            dirList += "<li>" + chld[i] + "</li>";
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