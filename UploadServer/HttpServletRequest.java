package UploadServer;

import java.io.BufferedReader;
import java.io.InputStream;

public class HttpServletRequest {
   private BufferedReader bufferedReader = null;

   private InputStream inputStream = null;

   private String boundary;

   /** Constructor */
   public HttpServletRequest(BufferedReader bufferedReader, InputStream inputStream) {
      this.bufferedReader = bufferedReader;
      this.inputStream = inputStream;
   }

   /** Getter method for inputStream(instance variable). */
   public BufferedReader getBufferedReader() {return bufferedReader;}

   public InputStream getInputStream() {return inputStream;}

   public void setBoundary(String boundary){
      this.boundary = boundary;
   }

   public String getBoundary() {
      return boundary;
   }
}
