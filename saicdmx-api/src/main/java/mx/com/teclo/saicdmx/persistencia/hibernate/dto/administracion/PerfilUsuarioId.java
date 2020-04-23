package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;
 
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

 
@Embeddable
public class PerfilUsuarioId implements java.io.Serializable {

	private BigDecimal perfilId;
	private long usuId;
	private long idAplicacion;

	public PerfilUsuarioId() {
	}

	public PerfilUsuarioId(BigDecimal perfilId, long usuId, long idAplicacion) {
		this.perfilId = perfilId;
		this.usuId = usuId;
		this.idAplicacion = idAplicacion;
	}

	@Column(name = "PERFIL_ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getPerfilId() {
		return this.perfilId;
	}

	public void setPerfilId(BigDecimal perfilId) {
		this.perfilId = perfilId;
	}

	@Column(name = "USU_ID", nullable = false, precision = 11, scale = 0)
	public long getUsuId() {
		return this.usuId;
	}

	public void setUsuId(long usuId) {
		this.usuId = usuId;
	}

	@Column(name = "ID_APLICACION", nullable = false, precision = 11, scale = 0)
	public long getIdAplicacion() {
		return this.idAplicacion;
	}

	public void setIdAplicacion(long idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	 
}
