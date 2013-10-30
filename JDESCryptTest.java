package rp.copy.jcrypt;

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
import java.io.ByteArrayInputStream;
import org.apache.commons.codec.binary.Base64;

public class JDESCryptTest 
{
	public static void main( String[] args ) throws Exception
	{
		if (args.length >= 2) {

			String command 	= args[0];
			String pass		= args[1];
			String payload 	= null;

			if (args.length >= 3)
				payload = args[2];

			switch (args[0]) {
				case "-encode": {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					JDESCrypt.encode(payload.getBytes(), out, pass);

					byte[] bytes = Base64.encodeBase64(out.toByteArray());
					for (int  i = 0; i < bytes.length; i++) {
						System.out.print(bytes[i]);
					}
					System.out.println();
				}
				break;

				case "-decode": {
					System.out.println(payload);
					ByteArrayInputStream in = new ByteArrayInputStream(Base64.decodeBase64(payload.getBytes()));
					byte[] decoded = JDESCrypt.decode(in, pass);
					System.out.println(new String(decoded));
				}
				break;

				case "-test": {
					payload = "123";
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					JDESCrypt.encode(payload.getBytes(), out, pass);

					byte[] bytes = Base64.encodeBase64(out.toByteArray());
					for (int  i = 0; i < bytes.length; i++) {
						System.out.print(bytes[i]);
					}
					System.out.println();

					ByteArrayInputStream in = new ByteArrayInputStream(Base64.decodeBase64(bytes));
					byte[] decoded = JDESCrypt.decode(in, pass);
					System.out.println(new String(decoded));
				}
				break;

				default:
				break;	
			}
		} else {
			usage();
		}
	}

	private static void usage() {
		System.out.println("There was an error using this tool.");
	}
}