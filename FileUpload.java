import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class FileUpload{
    public static void main(String[] args) throws IOException {
        
        //Host:port
        String[] terms = args[0]. split(":");
        String host = terms[0];
        int port = Integer.parseInt(terms[1]);

        //Read the file
        File f = new File(args[1]);
        if (!f.exists() || !f.isFile()){ //if does not exists, or not a file, exit
            System.err.printf("%s is not a file\n", args[1]);
            System.exit(-1);
        }

        String fileName = f.getName();
        long fileSize = f.length();

        System.out.printf("Upload file %s of size %d bytes\n",fileName, fileSize);
        System.out.printf("Connecting to upload server %s on port %s\n", host, port);

        Socket sock = new Socket(host, port);
        
        SendFile sendFile = new SendFile();
        sendFile.upload(sock, f);

        sock.close();

    }
}