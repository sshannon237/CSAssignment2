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
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
         HttpServletResponse res = new HttpServletResponse(out);

         // Creates a HttpServlet Instance
         HttpServlet httpServlet = new UploadServlet();



//         httpServlet.doGet(req, res);

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
         String inputLine = "";
         while ( !(inputLine += bufferedReader.readLine()).equals("") ) {
            inputLine +="\n";
            System.out.println(inputLine);
         }
//         inputLine = bufferedReader.readLine();
         System.out.println(inputLine);


         ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte[] content = new byte[1];
          int bytesRead = -1;
          while( ( bytesRead = in.read( content ) ) != -1 ) {
             baos.write( content, 0, bytesRead );
          }
          String s = baos.toString();
          System.out.println(s);
          System.out.println(baos.toString());

         httpServlet.doGet(req, res);
//           httpServlet.doPost(req, res);

//         if (s.substring(0,6).contains("GET / ")) {
//            httpServlet.doGet(baos, res);
//         } else if (s.substring(0,6).contains("POST")) {
//            httpServlet.doPost(baos, res);
//         } else {
//            httpServlet.doPost(baos, res);
//         }
         socket.close();
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
