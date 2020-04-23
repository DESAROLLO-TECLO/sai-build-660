package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;

@Embeddable
public class PagosPrimaryKeyDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="PAG_ID")
	private String pagId;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "CAJA_ID", referencedColumnName = "CAJA_ID", insertable = false)
	private CajaDTO cajaId;

	

	public CajaDTO getCajaId() {
		return cajaId;
	}

	public void setCajaId(CajaDTO cajaId) {
		this.cajaId = cajaId;
	}

	public String getPagId() {
		return pagId;
	}

	public void setPagId(String pagId) {
		this.pagId = pagId;
	}
}
