package mx.com.teclo.saicdmx.persistencia.vo.pagos;

public class TodoExpedienteVO {
	private String TIPO;
	private String DESCRIPCION;
	private Boolean REGISTRADO;
	
	public String getTIPO() {
		return TIPO;
	}
	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}
	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}
	public Boolean getREGISTRADO() {
		return REGISTRADO;
	}
	public void setREGISTRADO(Boolean rEGISTRADO) {
		REGISTRADO = rEGISTRADO;
	}
	
}
