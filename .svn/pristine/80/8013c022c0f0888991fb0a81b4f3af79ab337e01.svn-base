package mx.com.teclo.saicdmx.util.comun;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RadarUtils {
	
	public byte[] getBytesArchivoFotomulta(Long radarArchivoId, String nombreArchivo, String rutaZip, List<String> lista) {
		
		//List<String> lista = new ArrayList<String>();
		String rutaArchivo = rutaZip + nombreArchivo + ".txt";
		String rutaArchivoZip = rutaZip + nombreArchivo
				+ ".zip";
		File zip;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
			for (String registro : lista) {
				bw.write(registro);
				bw.newLine();
			}
			bw.close();

			ArchivoUtils.comprime(rutaArchivo, rutaArchivoZip);
			zip = new File(rutaArchivoZip);
			return ArchivoUtils.getBytesFromFile(zip);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
