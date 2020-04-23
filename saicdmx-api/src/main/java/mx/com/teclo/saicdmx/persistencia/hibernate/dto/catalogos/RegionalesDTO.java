package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGIONALES")
public class RegionalesDTO implements Serializable {
	
	private static final long serialVersionUID = -936077548484000666L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "REG_ID", unique = true, nullable = false)
	private Long reg_id;
	
	@Column(name = "REG_NOMBRE")
	private String reg_nombre;
	
	public Long getReg_id() {
		return reg_id;
	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_nombre() {
		return reg_nombre;
	}
	public void setReg_nombre(String reg_nombre) {
		this.reg_nombre = reg_nombre;
	}
	
	
}
