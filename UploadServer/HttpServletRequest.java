package UploadServer;

import java.io.InputStream;

public class HttpServletRequest {
   private InputStream inputStream = null;

   /** Constructor */
   public HttpServletRequest(InputStream inputStream) {
      this.inputStream = inputStream;
   }

   /** Getter method for inputStream(instance variable). */
   public InputStream getInputStream() {return inputStream;}
}
