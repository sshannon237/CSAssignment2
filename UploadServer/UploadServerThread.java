package UploadServer;

import java.lang.reflect.Constructor;
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
            OutputStream baos = new ByteArrayOutputStream();
            HttpServletResponse res = new HttpServletResponse(baos);

            // Creates a HttpServlet Instance
            HttpServlet httpServlet = new UploadServlet();

            BufferedReader bufferedReader =
                  new BufferedReader(new InputStreamReader(in));
            String inputLine;
            inputLine = bufferedReader.readLine();
            System.out.println(inputLine);

            if (inputLine.contains("GET / ")) {
                httpServlet.doGet(req, res);
            } else if (inputLine.contains("POST")) {
                httpServlet.doPost(req, res);
            } else {
                httpServlet.doGet(req, res);
            }
            Class<UploadServlet> test = UploadServlet.class;
            Constructor<?> const1 = test.getConstructor();
            Method post = test.getDeclaredMethod("doPost",
                  HttpServletRequest.class, HttpServletResponse.class);
            Method get = test.getDeclaredMethod("doGet",
                  HttpServletRequest.class, HttpServletResponse.class);

            post.invoke(test.getDeclaredConstructor().newInstance(), req, res);
            get.invoke(test.getDeclaredConstructor().newInstance(), req, res);
            socket.close();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
