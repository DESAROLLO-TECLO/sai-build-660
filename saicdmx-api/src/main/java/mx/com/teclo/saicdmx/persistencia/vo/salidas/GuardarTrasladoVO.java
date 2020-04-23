package mx.com.teclo.saicdmx.persistencia.vo.salidas;

public class GuardarTrasladoVO {
	
	 private String  infracNum;
	 private String  observaciones;
	 private String medioTrasporte;
	 private String noEconomico;
	 private String idDepDestino;
	 private Long creadoPor;
	 private String tipoMovimiento;
		private String numCtrl;
	private String mensaje;
	private String resultadoPrincipal;
	private String resultadoExtra;

	
	
	public String getNumCtrl() {
		return numCtrl;
	}
	public void setNumCtrl(String numCtrl) {
		this.numCtrl = numCtrl;
	}
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getMedioTrasporte() {
		return medioTrasporte;
	}
	public void setMedioTrasporte(String medioTrasporte) {
		this.medioTrasporte = medioTrasporte;
	}
	public String getNoEconomico() {
		return noEconomico;
	}
	public void setNoEconomico(String noEconomico) {
		this.noEconomico = noEconomico;
	}
	public String getIdDepDestino() {
		return idDepDestino;
	}
	public void setIdDepDestino(String idDepDestino) {
		this.idDepDestino = idDepDestino;
	}
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getResultadoPrincipal() {
		return resultadoPrincipal;
	}
	public void setResultadoPrincipal(String resultadoPrincipal) {
		this.resultadoPrincipal = resultadoPrincipal;
	}
	public String getResultadoExtra() {
		return resultadoExtra;
	}
	public void setResultadoExtra(String resultadoExtra) {
		this.resultadoExtra = resultadoExtra;
	}
	
	

}
