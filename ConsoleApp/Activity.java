package ConsoleApp;

import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Activity {
   private String dirName = null; 
   public static void main(String[] args) throws IOException {
      System.out.println("Please Enter Your File Path and File Name: ");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String fileName = reader.readLine();
      reader.close();

      new Activity().onCreate(fileName);
   }
   public Activity() {
   }
   public void onCreate(String fileName) {
      System.out.println(new UploadClient().uploadFile(fileName));
   }
}
