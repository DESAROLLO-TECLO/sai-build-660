package mx.com.teclo.saicdmx.negocio.service.semovi;

import java.io.File;
import java.io.IOException;

public interface ArchivoService {
	
	byte[] getBytesFromFile(File file) throws IOException;

	boolean comprime(String archivo, String target);
	
	void eliminarArchivosCarpetaAnteriores(String path);
	
	Boolean creaDirectoriosSemovi(String anio);

}
