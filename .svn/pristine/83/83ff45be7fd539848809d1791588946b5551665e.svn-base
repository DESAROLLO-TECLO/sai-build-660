package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;

@Embeddable
public class DetallePagosPrimarykey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="PAG_ID")
	private String pagId;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "CAJA_ID", referencedColumnName = "CAJA_ID")
	private CajaDTO cajaDTO;
	
	@Column(name = "PAG_RENGLON")
	private Long pagRenglon;

	
	
	public CajaDTO getCajaDTO() {
		return cajaDTO;
	}

	public void setCajaDTO(CajaDTO cajaDTO) {
		this.cajaDTO = cajaDTO;
	}

	public String getPagId() {
		return pagId;
	}

	public void setPagId(String pagId) {
		this.pagId = pagId;
	}

	public Long getPagRenglon() {
		return pagRenglon;
	}

	public void setPagRenglon(Long pagRenglon) {
		this.pagRenglon = pagRenglon;
	}

	

}
