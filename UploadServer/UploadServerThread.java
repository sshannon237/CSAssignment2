package UploadServer;

import java.net.*;
import java.io.*;

public class UploadServerThread extends Thread {
   private Socket socket = null;

   public UploadServerThread(Socket socket) {
      super("UploadServerThread");
      this.socket = socket;
   }

   public void run() {
      try {
         // Creates a HttpServletRequest instance
         InputStream in = socket.getInputStream();
         HttpServletRequest req = new HttpServletRequest(in);

         // Creates a HttpServletResponse instance
         OutputStream output = new ByteArrayOutputStream();
         HttpServletResponse res = new HttpServletResponse(output);

         // Creates a HttpServlet Instance
         HttpServlet httpServlet = new UploadServlet();

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
         String inputLine;
         inputLine = bufferedReader.readLine();

         if (inputLine.contains("GET / ")) {
            httpServlet.doGet(req, res);
         } else if (inputLine.contains("POST")) {
            httpServlet.doGet(req, res);
         } else {
            httpServlet.doGet(req, res);
         }
         // System.out.println();
         // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
         // String inputLine;
      
         // ByteArrayOutputStream baos = new ByteArrayOutputStream();  
         // byte[] content = new byte[1];
         // int bytesRead = -1;      
         // while( ( bytesRead = in.read( content ) ) != -1 ) {  
         //    baos.write( content, 0, bytesRead );  
         // }
         // inputLine = baos.toString();
         // System.out.println(inputLine);
         // if (inputLine.contains("GET / ")) {
         //    httpServlet.doGet(req, res);
         // } else if (inputLine.contains("POST")) {
         //    httpServlet.doPost(req, res);
         // } else {
         //    httpServlet.doPost(req, res);
         // }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
