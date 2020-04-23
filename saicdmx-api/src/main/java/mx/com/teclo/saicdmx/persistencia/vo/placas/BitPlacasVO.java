package mx.com.teclo.saicdmx.persistencia.vo.placas;

import java.util.Date;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.BitPlacasDTO;

public class BitPlacasVO {
	private Integer idBitValidaMatri;
	private String cdValorOriginal;
	private String cdValorFinal;
	private String idRegistro;
	private String nbColumna;
	private String nbTabla;
	private Date fechaCreacion;
	private Integer creadoPor;
	private BitPlacasDTO bitPlacasDTO;

	public Integer getIdBitValidaMatri() {
		return idBitValidaMatri;
	}

	public void setIdBitValidaMatri(Integer idBitValidaMatri) {
		this.idBitValidaMatri = idBitValidaMatri;
	}

	public String getCdValorOriginal() {
		return cdValorOriginal;
	}

	public void setCdValorOriginal(String cdValorOriginal) {
		this.cdValorOriginal = cdValorOriginal;
	}

	public String getCdValorFinal() {
		return cdValorFinal;
	}

	public void setCdValorFinal(String cdValorFinal) {
		this.cdValorFinal = cdValorFinal;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNbColumna() {
		return nbColumna;
	}

	public void setNbColumna(String nbColumna) {
		this.nbColumna = nbColumna;
	}

	public String getNbTabla() {
		return nbTabla;
	}

	public void setNbTabla(String nbTabla) {
		this.nbTabla = nbTabla;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Integer creadoPor) {
		this.creadoPor = creadoPor;
	}

	public BitPlacasDTO getBitPlacasDTO() {
		bitPlacasDTO=new BitPlacasDTO();
		bitPlacasDTO.setIdBitValidaMatri(idBitValidaMatri);
		bitPlacasDTO.setCdValorOriginal(cdValorOriginal);
		bitPlacasDTO.setCdValorFinal(cdValorFinal);
		bitPlacasDTO.setIdRegistro(idRegistro);
		bitPlacasDTO.setNbColumna(nbColumna==null?"":nbColumna.toUpperCase());
		bitPlacasDTO.setNbTabla(nbTabla==null?"":nbTabla.toUpperCase());
		bitPlacasDTO.setCreadoPor(creadoPor);
		return bitPlacasDTO;
	}

	
}
