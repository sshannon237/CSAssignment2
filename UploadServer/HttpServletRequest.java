package UploadServer;

import java.io.BufferedReader;

public class HttpServletRequest {
   private BufferedReader inputStream = null;

   private String boundary;

   /** Constructor */
   public HttpServletRequest(BufferedReader inputStream) {
      this.inputStream = inputStream;
   }

   /** Getter method for inputStream(instance variable). */
   public BufferedReader getInputStream() {return inputStream;}

   public void setBoundary(String boundary){
      this.boundary = boundary;
   }

   public String getBoundary() {
      return boundary;
   }
}
