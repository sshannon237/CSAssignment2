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
        try {
            // Creates a HttpServletRequest instance
            InputStream in = socket.getInputStream();
            HttpServletRequest req = new HttpServletRequest(in);

            // Creates a HttpServletResponse instance
            DataOutputStream out =
                  new DataOutputStream(socket.getOutputStream());
            HttpServletResponse res = new HttpServletResponse(out);

            // Creates a HttpServlet Instance
//            HttpServlet httpServlet = new UploadServlet();

            // Reads the Header from the input Stream and gets the image size
            // as int
            BufferedReader bufferedReader =
                  new BufferedReader(new InputStreamReader(in));
            String input = "";
            String inputLine = "";
            int imageSize = 0;
            while (!(inputLine = bufferedReader.readLine()).equals("")) {
                input += inputLine + "\n";
                if (inputLine.contains("Content-Length")) {
                    String[] contentLength = inputLine.split(" ", 2);
                    imageSize = Integer.parseInt(contentLength[1]);
                }
            }
            System.out.println(imageSize);

            //         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         byte[] content = new byte[1];
//         int bytesRead = -1;
//         while( ( bytesRead = in.read( content ) ) != -1 ) {
//            baos.write( content, 0, bytesRead );
//         }
//         String s = baos.toString();
//         System.out.println(s);
//         System.out.println(baos.toString());


            Class<UploadServlet> test = UploadServlet.class;
            Method post = test.getDeclaredMethod("doPost",
                  HttpServletRequest.class, HttpServletResponse.class);
            Method get = test.getDeclaredMethod("doGet",
                  HttpServletRequest.class, HttpServletResponse.class);

            if (input.contains("GET / ")) {
                get.invoke(test.getDeclaredConstructor().newInstance(), req,
                      res);
            } else if (input.contains("POST")) {
               post.invoke(test.getDeclaredConstructor().newInstance(), req, res);
            }
            socket.close();
        } catch (NoSuchMethodException | InvocationTargetException |
              IllegalAccessException | IOException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
