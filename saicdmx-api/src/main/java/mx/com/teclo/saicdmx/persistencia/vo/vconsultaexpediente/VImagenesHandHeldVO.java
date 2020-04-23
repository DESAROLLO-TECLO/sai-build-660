package mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente;

import java.io.Serializable;

public class VImagenesHandHeldVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347543790568411270L;
	
	private String path;
	
	private String nombreArchivo;
	
	private Integer infrac_num;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getInfrac_num() {
		return infrac_num;
	}

	public void setInfrac_num(Integer infrac_num) {
		this.infrac_num = infrac_num;
	}
	
}
