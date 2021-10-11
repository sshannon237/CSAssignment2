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

         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
         String input = "";
         String inputLine = "";
         while(!(inputLine = bufferedReader.readLine()).equals("") ) {
            input += inputLine + "\n";
         }
         System.out.println(input);

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
