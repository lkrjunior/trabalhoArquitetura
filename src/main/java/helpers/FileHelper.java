package helpers;

import java.io.*;

public class FileHelper {

    public static String GenerateNameFile()
    {
        return java.util.UUID.randomUUID().toString();
    }

    public static InputStream ConvertByteArrayFileToFileInputStream(byte[] file)
    {
        InputStream input = new ByteArrayInputStream(file);
        return input;
    }

    public static byte[] ConvertInputStreamtoByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }
}
