package mx.com.teclo.saicdmx.persistencia.vo.administracion;

public class AdminUsuarioClaveVO {
	private Long usuario_id;
	private String vieja_contra;
	private String nueva_contra;
	private String repetir_contra;
	private Integer resultado;
	private String mensaje;
	
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getVieja_contra() {
		return vieja_contra;
	}
	public void setVieja_contra(String vieja_contra) {
		this.vieja_contra = vieja_contra;
	}
	public String getNueva_contra() {
		return nueva_contra;
	}
	public void setNueva_contra(String nueva_contra) {
		this.nueva_contra = nueva_contra;
	}
	public String getRepetir_contra() {
		return repetir_contra;
	}
	public void setRepetir_contra(String repetir_contra) {
		this.repetir_contra = repetir_contra;
	}
	public Integer getResultado() {
		return resultado;
	}
	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}