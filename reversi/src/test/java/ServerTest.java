import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ashamsiev on 20.10.2015
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(58080);

        System.out.println("server initialized");
        Socket socket = serverSocket.accept();

        System.out.println("client accepted");
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getLocalAddress());
        System.out.println(socket.getLocalSocketAddress());
        System.out.println(socket.getPort());
        System.out.println(socket.getRemoteSocketAddress());
        System.out.println(socket.getLocalPort());
        System.out.println(socket.getReuseAddress());


        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        out.write(12);
        in.read();
        out.write(12);
        in.read();
        out.write(12);
        in.read();
        out.write(12);
        in.read();
        out.write(2);
        in.read();
    }
}
