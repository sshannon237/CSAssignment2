package ConsoleApp;

import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Activity {

   private static String fileName;
   private static String keyword;
   private static String dateCreated;

   public Activity(String fileName, String dateCreated, String keyword) {
      this.fileName = fileName;
      this.dateCreated = dateCreated;
      this.keyword = keyword;
   }

   public static void main(String[] args) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("Please Enter Your File Path and File Name: ");
      fileName = reader.readLine();

      System.out.println("Please Enter The Date Created: yyyy/mm/dd");
      dateCreated = reader.readLine();

      System.out.println("Please Enter A Caption:");
      keyword = reader.readLine();

      reader.close();

      new Activity(fileName, dateCreated, keyword).onCreate();
   }
   public void onCreate() {
      System.out.println(new UploadClient(this.fileName, this.dateCreated,
            this.keyword).uploadFile());
   }
}
