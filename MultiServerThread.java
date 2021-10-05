import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
    private Socket socket = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String inputLine;
            inputLine = in.readLine();
            if (inputLine.contains("GET / ")) {

                out.writeBytes("HTTP/1.1 200 OK\r\n");
                out.writeBytes("Content-Type: text/html\r\n\r\n");

                String htmlPage = "<!DOCTYPE html>" +
                        "<html><head><title>File Upload Form</title></head>" +
                        "<body><h1>Upload file</h1>" +
                        "<form method=\"POST\" action=\"upload\" " +
                        "enctype=\"multipart/form-data\">" +
                        "<input type=\"file\" name=\"fileName\"/><br/><br/>" +
                        "Caption: <input type=\"text\" name=\"caption\"<br/><br/>" +
                        "<br />" +
                        "Date: <input type=\"date\" name=\"date\"<br/><br/>" +
                        "<br />" +
                        "<input type=\"submit\" value=\"Submit\"/>" +
                        "</form>" +
                        "</body></html>";
                out.writeBytes(htmlPage);

            }
            if (inputLine.contains("POST")) {
                //handle post of form and image file
                String topPart = "<!DOCTYPE html><html><body><ul>";
                String bottomPart = "</ul></body></html>";
                out.writeBytes("HTTP/1.1 200 OK\r\n");
                out.writeBytes("Content-Type: text/html\r\n\r\n");
                out.writeBytes(topPart+getListing("C:\\tomcat\\webapps\\photogallery\\images") + bottomPart);
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getListing(String path) {
        String dirList = null;
        File dir = new File(path);
        String[] chld = dir.list();
        for (int i = 0; i < chld.length; i++) {
            if ((new File(path + chld[i])).isDirectory())
                dirList += "<li><button type=\"button\">" + chld[i] + "</button></li>";
            else
                dirList += "<li>" + chld[i] + "</li>";
        }
        return dirList;
    }
    }