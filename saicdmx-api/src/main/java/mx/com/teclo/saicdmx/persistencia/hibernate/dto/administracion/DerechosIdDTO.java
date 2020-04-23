package mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DerechosIdDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5386174687966082213L;

	@Basic(optional = false)
	@Column(name = "USU_ID", nullable = false, precision = 11, scale = 0)
	private Long usuId;

	@Basic(optional = false)
	@Column(name = "MOD_ID", nullable = false, precision = 11, scale = 0)
	private Long modId;

	@Basic(optional = false)
	@Column(name = "OPER_ID", nullable = false, precision = 11, scale = 0)
	private Long operId;

	public DerechosIdDTO() {
	}

	public DerechosIdDTO(Long usuId, Long modId, Long operId) {
		this.usuId = usuId;
		this.modId = modId;
		this.operId = operId;
	}

	public Long getUsuId() {
		return usuId;
	}

	public void setUsuId(Long usuId) {
		this.usuId = usuId;
	}

	public Long getModId() {
		return modId;
	}

	public void setModId(Long modId) {
		this.modId = modId;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

}
