/*
The MIT License (MIT)

Copyright (c) 2013 Marlon Meuters <marlon.meuters@cluster-one.eu>

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class JDESCrypt
{
  public static void encode(byte[] bytes, OutputStream out, String pass) throws Exception
  {
    Cipher c = Cipher.getInstance("DES");
    Key k = new SecretKeySpec( pass.getBytes(), "DES");
    c.init(Cipher.ENCRYPT_MODE, k);

    OutputStream cos = new CipherOutputStream(out, c);
    cos.write(bytes);
    cos.close();
  }

  public static byte[] decode(InputStream is, String pass) throws Exception
  {
    Cipher c = Cipher.getInstance("DES");
    Key k = new SecretKeySpec(pass.getBytes(), "DES");
    c.init(Cipher.DECRYPT_MODE, k);

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    CipherInputStream cis = new CipherInputStream(is, c);

    for (int b; (b = cis.read()) != -1; b++) {
      bos.write(b);
    }

    cis.close();
    return bos.toByteArray();
  }
}