import java.io.*;
import java.util.Scanner;
import java.util.Date;

public class Activity {

    private String fileName = null;

    private String dateCreated = null;

    private String caption = null;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input Filename:");
        String fileName = scan.nextLine();
        System.out.println("Print caption:");
        String caption = scan.nextLine();
        scan.close();
        new Activity(fileName, caption, new Date().toString()).onCreate();
    }

    public Activity(String filename, String dateCreated, String caption) {
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.caption = caption;
    }

    public void onCreate() {
        System.out.println(new FileUploadClient(this.fileName, this.dateCreated, this.caption).uploadFile());
    }
}
