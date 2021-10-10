package UploadServer;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.Arrays;
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
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      try {
         InputStream in = request.getInputStream();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] content = new byte[1];
         int bytesRead = -1;
         while( ( bytesRead = in.read( content ) ) != -1 ) {
            baos.write( content, 0, bytesRead );
         }
         OutputStream outputStream = new FileOutputStream(new File(DIR_NAME + "hello" + ".png"));
         baos.writeTo(outputStream);
         outputStream.close();
         PrintWriter out = new PrintWriter(response.getOutputStream(), true);

         File dir = new File(DIR_NAME);
         String[] chld = dir.list();
         List<String> images = new ArrayList<String>(Arrays.asList(chld));
         List<String> sortedImages = images.stream().sorted().collect(Collectors.toList());
         sortedImages.forEach(System.out::println);

      } catch(Exception ex) {
         System.err.println(ex);
      }
   }
}