package UploadServer;

import java.io.OutputStream;

public class HttpServletResponse {
   private OutputStream outputStream = null;

   /** Constructor. */
   public HttpServletResponse(OutputStream outputStream) {
      this.outputStream = outputStream;
   }

   /** Getter method for outputStream(instance variable). */
   public OutputStream getOutputStream() {return outputStream;}
}