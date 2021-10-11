package UploadServer;

import java.io.DataOutputStream;

public class HttpServletResponse {
   private DataOutputStream outputStream = null;

   /** Constructor. */
   public HttpServletResponse(DataOutputStream outputStream) {
      this.outputStream = outputStream;
   }

   /** Getter method for outputStream(instance variable). */
   public DataOutputStream getOutputStream() {return outputStream;}
}