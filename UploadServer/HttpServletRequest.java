package UploadServer;

import java.io.BufferedReader;

public class HttpServletRequest {
   private BufferedReader inputStream = null;

   private int contentLength;

   /** Constructor */
   public HttpServletRequest(BufferedReader inputStream) {
      this.inputStream = inputStream;
   }

   /** Getter method for inputStream(instance variable). */
   public BufferedReader getInputStream() {return inputStream;}

   public void setContentLength(int contentLength){
      this.contentLength = contentLength;
   }

   public int getContentLength() {
      return contentLength;
   }
}
