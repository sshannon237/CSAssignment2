package ConsoleApp;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UploadClient {
    private String fileName;
    private String dateCreated;
    private String keyword;

    public UploadClient(String fileName, String dateCreated, String keyword) {
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.keyword = keyword;
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
            socket.shutdownOutput();
            fis.close();

            System.out.println("Here is the listing of sorted images: ");
            File dir = new File("./images/");
            String[] children = dir.list();
            List<String> images = new ArrayList<String>(Arrays.asList(children));
            List<String> sortedImages = images.stream().sorted().collect(Collectors.toList());
            sortedImages.forEach(System.out::println);
            socket.shutdownInput();

        } catch (Exception e) {
            System.err.println(e);
        }
        return listing;
    }
}