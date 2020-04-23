package mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente;

import java.io.Serializable;

public class VImagenesIngresoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1683541785822535836L;

	private String path;
	
	private String nombreArchivo;
	
	private Integer infracNum;

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

	public Integer getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(Integer infracNum) {
		this.infracNum = infracNum;
	}

}
