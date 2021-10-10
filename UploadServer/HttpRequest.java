package UploadServer;

import java.io.InputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class HttpRequest {
//   private InputStream inputStream = null;
   private ServletRequest request;

   /** Constructor */
   public HttpRequest(ServletRequest request) {
      this.request = request;
//      this.inputStream = inputStream;
   }

   /** Getter method for inputStream(instance variable). */
   public ServletRequest getPart() {
         return (HttpRequest) super._getHttpServletRequest().getPart(name);
      ;
   }
}
