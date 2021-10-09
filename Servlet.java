import java.net.Socket;
import java.io.*;

public abstract class Servlet {

    /**
     * Takes an HTTP request, determines if it is get or post, then calls the appropriate doGet or do Post method.
     * @param socket
     */
    public void service(Socket socket) {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            inputLine = in.readLine();
            if (inputLine.contains("GET / ")) {
                System.out.println("LOG: calling doGet().");
                doGet(in, out);
            }
            if (inputLine.contains("POST")) {
                System.out.println("LOG: calling doPost().");
                doPost(in, out);
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            
            System.out.println("ERROR:");
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    // TODO: define your own HttpRequest as well as HttpResponse classes to contain and
    // parse the incoming and outgoing HTTP messages over TCP

    /**
     * Returns the HTML for the resource
     * @param request
     * @param response
     * @throws IOException
     */
    abstract void doGet(BufferedReader request, DataOutputStream response) throws IOException;

    /**
     * uploads the picture and returns a list of the files in the path.
     * @param request
     * @param response
     * @throws IOException
     */
    abstract void doPost(BufferedReader request, DataOutputStream response) throws IOException;
}
