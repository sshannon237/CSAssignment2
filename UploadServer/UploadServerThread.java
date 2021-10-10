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

         httpServlet.doPost(req, res);


      } catch (Exception e) { e.printStackTrace(); }
   }
}
