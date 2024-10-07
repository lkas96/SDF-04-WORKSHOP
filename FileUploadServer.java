import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadServer {
    public static void main(String[] args) throws IOException {

        //java -cp classes FileUploadServer 3000
        int port = Integer.parseInt(args[0]);

        //Create a Server Socket with the port number passed in
        ServerSocket server = new ServerSocket(port);
        System.out.printf("Uploader server listening on port %d\n", port);

        while(true){
            Socket conn = server.accept();
            ReceiveFile recvFile = new ReceiveFile();
            recvFile.receiveFile(conn);

            conn.close();
        }

    }
}
