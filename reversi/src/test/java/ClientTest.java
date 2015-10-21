import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by ashamsiev on 20.10.2015
 */
public class ClientTest {
    public static void main(String[] args){

        try(
            Socket socket = new Socket("localhost", 58080);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
        ) {
            int answer;

            out.write(32);

//            Вот так читать все байты из стрима
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            byteArrayOutputStream.write(in);
//            byteArrayOutputStream.toByteArray();

            while ((answer = in.read()) != 2 ) {
                System.out.println("from server: " + answer);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.write(32);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
