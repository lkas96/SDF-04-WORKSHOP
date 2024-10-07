import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.*;

public class ReceiveFile {

    public void receiveFile(Socket sock) throws IOException {

        //Open socket for reading
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is); //reading from what is sent from outputstream from sendfile

        //Read filename
        String fileName = dis.readUTF();

        //Read filesize
        long fileSize = dis.readLong();

        //Create the output file
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        //create 4k buffer
        byte[] buff = new byte[4 * 1024];
        int bytesRead = 0;
        int bytesReceived = 0;

        System.out.printf("");

        while (bytesReceived < fileSize) {
            //Number of bytes read
            bytesRead = dis.read(buff);
            bytesReceived += bytesRead;
            bos.write(buff, 0, bytesRead);
            System.out.printf("Reveiced %d of %d",bytesReceived, fileSize);
        }
        
    }
}
