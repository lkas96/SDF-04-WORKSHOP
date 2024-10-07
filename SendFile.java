import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendFile {
    
    public void upload(Socket sock, File toUpload) throws IOException {
        String fileName = toUpload.getName();
        long fileSize = toUpload.length();

        //Open the file for reading
        FileInputStream is = new FileInputStream(toUpload);
        BufferedInputStream bis = new BufferedInputStream(is);

        //Open a connection to the server
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        //Write the file name
        dos.writeUTF(fileName);

        //Write the filesize
        dos.writeLong(fileSize);

        //Create a 4k buffer
        byte[] buff = new byte[4 * 1024];
        int bytesRead = 0;
        int sentBytes = 0;

        while ((bytesRead = bis.read(buff)) > 0) {
            dos.write(buff, 0, bytesRead);
            sentBytes += bytesRead;
            System.out.printf("> send %d of %d\n", sentBytes, fileSize );
        }

        dos.flush();
        os.flush();
    }
}
