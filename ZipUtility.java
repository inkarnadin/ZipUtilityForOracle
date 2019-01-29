create or replace and compile java source named "ZipUtility" as
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.Reader;
import java.nio.charset.Charset;

import java.sql.*;
import oracle.jdbc.*;

public class ZipUtility
{
  public static Blob zip(Clob clob) throws IOException, SQLException
  {   
    Reader reader = clob.getCharacterStream();
    char[] charBuffer = new char[8 * 1024];
    StringBuilder builder = new StringBuilder();
    int numCharsRead;
    while ((numCharsRead = reader.read(charBuffer, 0, charBuffer.length)) != -1) 
        builder.append(charBuffer, 0, numCharsRead);
    ByteArrayInputStream inByteArray = new ByteArrayInputStream(builder.toString().getBytes(Charset.forName("UTF-8")));

    ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();

    ZipOutputStream zipStream = new ZipOutputStream(outByteArray);
    ZipEntry zipEntry = new ZipEntry("entry.xml");
    zipStream.putNextEntry(zipEntry);

    byte[] buffer = new byte[1024];
    int lenght;
    while ((lenght = inByteArray.read(buffer)) > 0)
          zipStream.write(buffer, 0, lenght);

    inByteArray.close();
    outByteArray.close();
    zipStream.close();
 
    Blob result = oracle.sql.BLOB.createTemporary((OracleConnection) new OracleDriver().defaultConnection(), true, oracle.sql.BLOB.DURATION_SESSION);
    result.setBytes(1, outByteArray.toByteArray()); 
    
    return result;
  }

  public static Blob unzip(Blob blob) throws IOException, SQLException
  { 
    int length = (int) blob.length();  
    byte[] data = blob.getBytes(1, length);
    blob.free();    
     
    byte[] buffer = new byte[1024];
    ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
    
    ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(data));
    ZipEntry zipEntry = null;

    while ((zipEntry = zipStream.getNextEntry()) != null)
    {
        int len;
        while ((len = zipStream.read(buffer)) > 0)
           outByteArray.write(buffer, 0, len);

        outByteArray.close();
    }
    zipStream.close();

    Blob result = oracle.sql.BLOB.createTemporary((OracleConnection) new OracleDriver().defaultConnection(), true, oracle.sql.BLOB.DURATION_SESSION);
    result.setBytes(1, outByteArray.toByteArray()); 
        
    return result;
  }
}



