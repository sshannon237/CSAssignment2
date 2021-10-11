package UploadServer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;

public class UploadServerThread extends Thread {
    private Socket socket = null;

    public UploadServerThread(Socket socket) {
        super("UploadServerThread");
        this.socket = socket;
    }

   public void run() {
      System.out.println("LOG: Running thread.");
      try {
         // Creates a HttpServletRequest instance
         InputStream in = socket.getInputStream();
         HttpServletRequest req = new HttpServletRequest(new BufferedReader(new InputStreamReader(in)));


         // Creates a HttpServletResponse instance
         DataOutputStream out =
               new DataOutputStream(socket.getOutputStream());
         HttpServletResponse res = new HttpServletResponse(out);

         // Reads the Header from the input Stream and gets the image size as int
         BufferedReader bufferedReader = req.getInputStream();
         String input = "";
         String inputLine = "";
         while(!(inputLine = bufferedReader.readLine()).equals("") ) {
            input += inputLine + "\n";
            if(inputLine.contains("Content-Type")){
               String[] boundary = inputLine.split("=", 2);
               req.setBoundary(boundary[1]);
            }
         }

          boolean fromBrowser = true;
            Class<UploadServlet> test = UploadServlet.class;
            Method post = test.getDeclaredMethod("doPost",
                  HttpServletRequest.class, HttpServletResponse.class,
                  boolean.class);
            Method get = test.getDeclaredMethod("doGet",
                  HttpServletRequest.class, HttpServletResponse.class);

            if (input.contains("GET / ")) {
                get.invoke(test.getDeclaredConstructor().newInstance(), req,
                      res);
            } else if (input.contains("POST")) {
                post.invoke(test.getDeclaredConstructor().newInstance(), req,
                      res, fromBrowser);
            }
            socket.close();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException | InstantiationException e) {
            e.printStackTrace();
        }
    }
//         if (input.contains("GET / ")) {
//            httpServlet.doGet(req, res, true);
//         } else if (input.contains("POST")) {
//
//            httpServlet.doPost(req, res);
//         } else {
//            httpServlet.doGet(req, res, false);
//         }
//         socket.close();
//
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }
}
