import java.net.*;

public class MultiServerThread extends Thread {
    private Socket socket = null;
    private Servlet servlet;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
        this.servlet = new FileUploadServlet();
    }

    public void run() {
        System.out.println("LOG: Running new thread");
        servlet.service(socket);
    }
}
