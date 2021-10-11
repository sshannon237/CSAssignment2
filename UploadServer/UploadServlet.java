package UploadServer;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class UploadServlet extends HttpServlet {
   String DIR_NAME = "./images/";

   protected void doGet(HttpServletRequest request, HttpServletResponse response) {
      DataOutputStream out = response.getOutputStream();
      // new DataOutputStream(response.getOutputStream());
      try {
         System.out.println("doGet method called");

         out.writeBytes("HTTP/1.1 200 OK\r\n");
         out.writeBytes("Content-Type: text/html\r\n\r\n");

         String htmlPage = "<!DOCTYPE html>" + "<html><head><title>File " + "Upload Form</title></head>"
               + "<body><h1>Upload file</h1>" + "<form method=\"POST\" action=\"upload\" "
               + "enctype=\"multipart/form-data\">" + "<input type=\"file\" name=\"fileName\"/><br/><br/>"
               + "Caption: <input type=\"text\" name=\"caption\"<br/><br/>" + "<br />"
               + "Date: <input type=\"date\" name=\"date\"<br/><br/>" + "<br />"
               + "<input type=\"submit\" value=\"Submit\"/>" + "</form>" + "</body></html>";
         out.writeBytes(htmlPage);
      } catch (IOException e) {
         System.err.println(e);
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response, boolean fromBrowser) {
      DataOutputStream out = response.getOutputStream();
      
      System.out.println("LOG: doPost method called");
      try {
         if (fromBrowser) {
            BufferedReader in = request.getBufferedReader();
            String inputLine = "";
            String input = "";
            String endOfRequest = request.getBoundary() + "--";
            while (!(inputLine = in.readLine()).contains(endOfRequest)) {
               input += inputLine + "\n";
            }

            String[] body = input.split("--" + request.getBoundary());

            String[] filePart = body[1].split("\n", 4);
            String captionPart = body[2];
            String datePart = body[3];

            String untrimmedfileName = filePart[1].split("filename=")[1];
            String fileName = untrimmedfileName.substring(1, untrimmedfileName.length() - 1);
            byte[] file = filePart[3].trim().getBytes();

            String caption = captionPart.split("name=\"caption\"")[1].trim();
            String date = datePart.split("name=\"date\"")[1].trim();

            OutputStream os = new FileOutputStream(new File("images\\" + date + caption + fileName));
            os.write(file);

            String topPart = "<!DOCTYPE html><html><body><ul>";
            String bottomPart = "</ul></body></html>";
            out.writeBytes("HTTP/1.1 200 OK\r\n");
            out.writeBytes("Content-Type: text/html\r\n\r\n");
            out.writeBytes(topPart + getListing("C:\\tomcat\\webapps" + "\\photogallery\\images") + bottomPart);
         } else {
            InputStream in = request.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] content = new byte[1];
            int bytesRead = -1;
            while ((bytesRead = in.read(content)) != -1) {
               baos.write(content, 0, bytesRead);
            }
            OutputStream outputStream = new FileOutputStream(new File(DIR_NAME + "hello" + ".png"));
            baos.writeTo(outputStream);
            outputStream.close();

            File dir = new File(DIR_NAME);
            String[] chld = dir.list();
            List<String> images = new ArrayList<String>(Arrays.asList(chld));
            List<String> sortedImages = images.stream().sorted().collect(Collectors.toList());
            sortedImages.forEach(System.out::println);
         }

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