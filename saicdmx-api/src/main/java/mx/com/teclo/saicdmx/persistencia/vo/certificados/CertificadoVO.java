package mx.com.teclo.saicdmx.persistencia.vo.certificados;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CertificadoVO {
	
	private Long  certId;
 	private byte[] certArchivo;
 	private byte[] keyArchivo;

 	private EmpleadoVO empleadoVO;
 	private String  certNombre;
 	private String 	keyNombre;
 	private String 	certEmitidoPara;
 	private String 	certEmitidoPor;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="America/Mexico_City")
 	private Date 	certValidoDesde;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="America/Mexico_City")
	private Date 	certValidoHasta;
 	private String  certEncoder;
 	private String  numSerie;
	private String  emisorDN;
 	private String  propietarioDN;
	private String  propietarioRfc;
 	private Integer estatusCertificado;
 	private Integer validado;
    private long numDias;
 	 
 	
 	
	public Long getCertId() {
		return certId;
	}
	public void setCertId(Long certId) {
		this.certId = certId;
	}
	public String getEmisorDN() {
		return emisorDN;
	}
	public void setEmisorDN(String emisorDN) {
		this.emisorDN = emisorDN;
	}
	public String getPropietarioDN() {
		return propietarioDN;
	}
	public void setPropietarioDN(String propietarioDN) {
		this.propietarioDN = propietarioDN;
	}
	public String getCertNombre() {
		return certNombre;
	}
	public void setCertNombre(String certNombre) {
		this.certNombre = certNombre;
	}
	public String getKeyNombre() {
		return keyNombre;
	}
	public void setKeyNombre(String keyNombre) {
		this.keyNombre = keyNombre;
	}
	public String getCertEmitidoPara() {
		return certEmitidoPara;
	}
	public void setCertEmitidoPara(String certEmitidoPara) {
		this.certEmitidoPara = certEmitidoPara;
	}
	public String getCertEmitidoPor() {
		return certEmitidoPor;
	}
	public void setCertEmitidoPor(String certEmitidoPor) {
		this.certEmitidoPor = certEmitidoPor;
	}
	public Date getCertValidoDesde() {
		return certValidoDesde;
	}
	public void setCertValidoDesde(Date certValidoDesde) {
		this.certValidoDesde = certValidoDesde;
	}
	public Date getCertValidoHasta() {
		return certValidoHasta;
	}
	public void setCertValidoHasta(Date certValidoHasta) {
		this.certValidoHasta = certValidoHasta;
	}
	public String getCertEncoder() {
		return certEncoder;
	}
	public void setCertEncoder(String certEncoder) {
		this.certEncoder = certEncoder;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public EmpleadoVO getEmpleadoVO() {
		return empleadoVO;
	}
	public void setEmpleadoVO(EmpleadoVO empleadoVO) {
		this.empleadoVO = empleadoVO;
	}
	public byte[] getCertArchivo() {
		return certArchivo;
	}
	public void setCertArchivo(byte[] certArchivo) {
		this.certArchivo = certArchivo;
	}
	public byte[] getKeyArchivo() {
		return keyArchivo;
	}
	public void setKeyArchivo(byte[] keyArchivo) {
		this.keyArchivo = keyArchivo;
	}
	
	public String getPropietarioRfc() {
		return propietarioRfc;
	}
	public void setPropietarioRfc(String propietarioRfc) {
		this.propietarioRfc = propietarioRfc;
	}
	public Integer getEstatusCertificado() {
		return estatusCertificado;
	}
	public void setEstatusCertificado(Integer estatusCertificado) {
		this.estatusCertificado = estatusCertificado;
	}
	public Integer getValidado() {
		return validado;
	}
	public void setValidado(Integer validado) {
		this.validado = validado;
	}
	public long getNumDias() {
		return numDias;
	}
	public void setNumDias(long numDias) {
		this.numDias = numDias;
	}
 
}
