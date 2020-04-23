package mx.com.teclo.saicdmx.persistencia.vo.administracion;

public class PerfilUsuarioVO {

	private Long perfilId;
	private Long usuId;
	private Long IdAplicacion;

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public Long getUsuId() {
		return usuId;
	}

	public void setUsuId(Long usuId) {
		this.usuId = usuId;
	}

	public Long getIdAplicacion() {
		return IdAplicacion;
	}

	public void setIdAplicacion(Long cdAplicacion) {
		this.IdAplicacion = cdAplicacion;
	}

}
