import java.io.*;
import java.net.*;
public class FileUploadClient {

    private String fileName;

    private String dateCreated;

    private String caption;

    public FileUploadClient(String fileName, String dateCreated, String caption) {
        this.fileName = fileName;

        this.dateCreated = dateCreated;

        this.caption = caption;
    }
    public String uploadFile() {
        String listing = "";
        try {
            Socket socket = new Socket("localhost", 8999);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();
            FileInputStream fis = new FileInputStream(fileName);
            byte[] bytes = fis.readAllBytes();
            out.write(bytes);
            out.write(fileName.getBytes());
            out.write(caption.getBytes());
            out.write(dateCreated.getBytes());

            socket.shutdownOutput();
            fis.close();
            System.out.println("Came this far\n");
            String filename = "";
            while ((filename = in.readLine()) != null) {
                listing += filename;
            }
            socket.shutdownInput();
        } catch (Exception e) {
            System.err.println(e);
        }
        return listing;
    }
}