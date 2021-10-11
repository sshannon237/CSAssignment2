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
         HttpServletRequest req = new HttpServletRequest(new BufferedReader(new InputStreamReader(in)));


         // Creates a HttpServletResponse instance
         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
         HttpServletResponse res = new HttpServletResponse(out);


         // Creates a HttpServlet Instance
         HttpServlet httpServlet = new UploadServlet();

         // Reads the Header from the input Stream and gets the image size as int
         BufferedReader bufferedReader = req.getInputStream();
         String input = "";
         String endOfRequest = "";
         String inputLine = "";
         while(!(inputLine = bufferedReader.readLine()).equals("") ) {
            input += inputLine + "\n";
            if(inputLine.contains("Content-Type")){
               String[] boundary = inputLine.split("=", 2);
               req.setBoundary(boundary[1]);
            }
         }
         System.out.println(endOfRequest);
         // System.out.println(imageSize);

         //         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         byte[] content = new byte[1];
//         int bytesRead = -1;
//         while( ( bytesRead = in.read( content ) ) != -1 ) {
//            baos.write( content, 0, bytesRead );
//         }
//         String s = baos.toString();
//         System.out.println(s);
//         System.out.println(baos.toString());

         if (input.contains("GET / ")) {
            httpServlet.doGet(req, res);
         } else if (input.contains("POST")) {

            httpServlet.doPost(req, res);
         }
         socket.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
