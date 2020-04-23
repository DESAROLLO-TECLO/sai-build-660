package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class TipoGruaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 936714295029787969L;
	private String idTipoGrua;	
	private String codigoTipoGrua;	
	private String nombreTipoGrua;	
	private String statusTipoGrua;	
	private TipoIngresoVO tipoIngreso;
	/**
	 * @return the idTipoGrua
	 */
	public String getIdTipoGrua() {
		return idTipoGrua;
	}
	/**
	 * @param idTipoGrua the idTipoGrua to set
	 */
	public void setIdTipoGrua(String idTipoGrua) {
		this.idTipoGrua = idTipoGrua;
	}
	/**
	 * @return the codigoTipoGrua
	 */
	public String getCodigoTipoGrua() {
		return codigoTipoGrua;
	}
	/**
	 * @param codigoTipoGrua the codigoTipoGrua to set
	 */
	public void setCodigoTipoGrua(String codigoTipoGrua) {
		this.codigoTipoGrua = codigoTipoGrua;
	}
	/**
	 * @return the nombreTipoGrua
	 */
	public String getNombreTipoGrua() {
		return nombreTipoGrua;
	}
	/**
	 * @param nombreTipoGrua the nombreTipoGrua to set
	 */
	public void setNombreTipoGrua(String nombreTipoGrua) {
		this.nombreTipoGrua = nombreTipoGrua;
	}
	/**
	 * @return the statusTipoGrua
	 */
	public String getStatusTipoGrua() {
		return statusTipoGrua;
	}
	/**
	 * @param statusTipoGrua the statusTipoGrua to set
	 */
	public void setStatusTipoGrua(String statusTipoGrua) {
		this.statusTipoGrua = statusTipoGrua;
	}
	/**
	 * @return the tipoIngreso
	 */
	public TipoIngresoVO getTipoIngreso() {
		return tipoIngreso;
	}
	/**
	 * @param tipoIngreso the tipoIngreso to set
	 */
	public void setTipoIngreso(TipoIngresoVO tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoGruaVO [idTipoGrua=" + idTipoGrua + ", codigoTipoGrua=" + codigoTipoGrua + ", nombreTipoGrua="
				+ nombreTipoGrua + ", statusTipoGrua=" + statusTipoGrua + ", tipoIngreso=" + tipoIngreso + "]";
	}
	
}
