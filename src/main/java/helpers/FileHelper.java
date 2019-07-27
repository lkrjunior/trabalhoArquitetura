package helpers;

import java.io.*;

public class FileHelper {

    private FileHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateNameFile()
    {
        return java.util.UUID.randomUUID().toString();
    }

    public static InputStream convertByteArrayFileToFileInputStream(byte[] file)
    {
        return new ByteArrayInputStream(file);
    }

    public static byte[] convertInputStreamtoByteArray(InputStream in) throws IOException
    {
        if (in == null)
        {
            throw new IOException("InputStream is null");
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }
}
