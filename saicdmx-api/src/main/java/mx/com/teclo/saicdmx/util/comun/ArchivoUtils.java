package mx.com.teclo.saicdmx.util.comun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchivoUtils {

	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
		}
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		is.close();
		return bytes;
	}
	
	public static boolean comprime(String archivo, String target) {
		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					target));

			FileInputStream fis = new FileInputStream(archivo);
			File file = new File(archivo);
			zos.putNextEntry(new ZipEntry(file.getName()));
			int i;
			do {
				i = fis.read();
				if (i != -1) {
					zos.write(i);
				}
			} while (i != -1);
			zos.closeEntry();
			fis.close();

			zos.close();
		} catch (IOException e) {
			System.out.println("Error al crear archivo ZIP: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
