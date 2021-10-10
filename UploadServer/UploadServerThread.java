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
         OutputStream baos = new ByteArrayOutputStream(); 
         HttpServletResponse res = new HttpServletResponse(baos);

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

         OutputStream out = socket.getOutputStream();
         out.write(((ByteArrayOutputStream) baos).toByteArray());
         socket.close();

      } catch (Exception e) { e.printStackTrace(); }
   }
}
