import java.io.*;

public class FileUploadServlet extends Servlet {

    void doGet(BufferedReader request, DataOutputStream response) throws IOException {
        
        response.writeBytes("HTTP/1.1 200 OK\r\n");
        response.writeBytes("Content-Type: text/html\r\n\r\n");

        String htmlPage = "<!DOCTYPE html>" + "<html><head><title>File Upload Form</title></head>"
                + "<body><h1>Upload file</h1>" + "<form method=\"POST\" action=\"upload\" "
                + "enctype=\"multipart/form-data\">" + "<input type=\"file\" name=\"fileName\"/><br/><br/>"
                + "Caption: <input type=\"text\" name=\"caption\"<br/><br/>" + "<br />"
                + "Date: <input type=\"date\" name=\"date\"<br/><br/>" + "<br />"
                + "<input type=\"submit\" value=\"Submit\"/>" + "</form>" + "</body></html>";
        response.writeBytes(htmlPage);
    }

    void doPost(BufferedReader request, DataOutputStream response) throws IOException {
        String topPart = "<!DOCTYPE html><html><body><ul>";
        String bottomPart = "</ul></body></html>";
        response.writeBytes("HTTP/1.1 200 OK\r\n");
        response.writeBytes("Content-Type: text/html\r\n\r\n");
        response.writeBytes(topPart + getListing("C:\\tomcat\\webapps\\photogallery\\images") + bottomPart);
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
