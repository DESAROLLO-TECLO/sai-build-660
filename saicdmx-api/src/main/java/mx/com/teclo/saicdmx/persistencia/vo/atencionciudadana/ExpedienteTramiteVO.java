package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.io.Serializable;
import java.util.Date;

public class ExpedienteTramiteVO implements Serializable {

	private static final long serialVersionUID = -7644677695613421929L;
	
	private String folioTramite;
	private String nbArchivo;
	private byte[] archivo;
	private String tipoExp;
	private String ruraExpediente;
	private boolean existeEnBD;
	
	public String getFolioTramite() {
		return folioTramite;
	}
	public void setFolioTramite(String folioTramite) {
		this.folioTramite = folioTramite;
	}
	public String getNbArchivo() {
		return nbArchivo;
	}
	public void setNbArchivo(String nbArchivo) {
		this.nbArchivo = nbArchivo;
	}
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	public String getTipoExp() {
		return tipoExp;
	}
	public void setTipoExp(String tipoExp) {
		this.tipoExp = tipoExp;
	}
	public boolean getExisteEnBD() {
		return existeEnBD;
	}
	public void setExisteEnBD(boolean existeEnBD) {
		this.existeEnBD = existeEnBD;
	}
	public String getRuraExpediente() {
		return ruraExpediente;
	}
	public void setRuraExpediente(String ruraExpediente) {
		this.ruraExpediente = ruraExpediente;
	}
}
