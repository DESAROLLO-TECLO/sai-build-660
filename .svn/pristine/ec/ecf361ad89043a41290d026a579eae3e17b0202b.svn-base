package mx.com.teclo.saicdmx.negocio.service.semovi;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Service
public class ArchivoServiceImpl implements ArchivoService{

	
	@Autowired
	ParametroService parametros;
	
	@SuppressWarnings("resource")
	public byte[] getBytesFromFile(File file) throws IOException {
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

	public boolean comprime(String archivo, String target) {
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
	
	public  void eliminarArchivosCarpetaAnteriores(String path) {
		RutinasTiempoImpl rutinasTiempo = new RutinasTiempoImpl();
		
		File listaArchivos = new File(path);

		String[] arrayArchivos = listaArchivos.list();

		for (String nombre : arrayArchivos) {
			File archivo = new File(path + "/" + nombre);
			Date fechaArchivo = rutinasTiempo
					.obtenrFechaModificacionArchivo(archivo);
			Date fechaSinHoras = rutinasTiempo.getFechaActual();

			if (fechaArchivo.getTime() < fechaSinHoras.getTime()) {
				archivo.delete();
			}
		}
	}
	
	public Boolean creaDirectoriosSemovi(String anio) {
		String pathEntrada = parametros.getRutaSemovi() + "ENTRADA/" + anio + "/";
		String pathSalida = parametros.getRutaSemovi() + "SALIDA/" + anio + "/";

		// Crea directorios para archivos de semovi si es necesario
		File f = new File(pathEntrada);
		if (!f.exists()) {
			if (!f.mkdirs()) {
				// No se pudo crear, regresamos error
				return false;
			}
		}
		// Crea directorio para archivos de semovi si es necesario
		f = new File(pathSalida);
		if (!f.exists()) {
			if (!f.mkdirs()) {
				// No se pudo crear, regresamos error
				return false;
			}
		}

		return true;
	}
}
