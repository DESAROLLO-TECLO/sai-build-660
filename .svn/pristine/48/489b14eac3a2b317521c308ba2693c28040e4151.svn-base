package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REGIONALES_UPC")
public class AreaOperativaDTO  implements Serializable{
	
	private static final long serialVersionUID = -936077548484000666L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "UPC_ID", unique = true, nullable = false)
	private Long upc_id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="REG_ID", referencedColumnName="REG_ID", insertable=false, updatable=false)
	private RegionalesDTO regionalDTO;
	
	@Column(name = "UPC_COD")
	private String upc_cod;
	
	@Column(name = "UPC_NOMBRE")
	private String upc_nombre;
	@Column(name = "UPC_STATUS")
	private String upc_status;
	
	@Column(name = "CREADO_POR")
	private Long creado_por;
	@Column(name = "FECHA_CREACION")
	private Date fecha_creacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificado_por;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultima_modificacion;
	public Long getUpc_id() {
		return upc_id;
	}
	public void setUpc_id(Long upc_id) {
		this.upc_id = upc_id;
	}
	public RegionalesDTO getRegionalDTO() {
		return regionalDTO;
	}
	public void setRegionalDTO(RegionalesDTO regionalDTO) {
		this.regionalDTO = regionalDTO;
	}
	public String getUpc_cod() {
		return upc_cod;
	}
	public void setUpc_cod(String upc_cod) {
		this.upc_cod = upc_cod;
	}
	public String getUpc_nombre() {
		return upc_nombre;
	}
	public void setUpc_nombre(String upc_nombre) {
		this.upc_nombre = upc_nombre;
	}
	public String getUpc_status() {
		return upc_status;
	}
	public void setUpc_status(String upc_status) {
		this.upc_status = upc_status;
	}
	public Long getCreado_por() {
		return creado_por;
	}
	public void setCreado_por(Long creado_por) {
		this.creado_por = creado_por;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Long getModificado_por() {
		return modificado_por;
	}
	public void setModificado_por(Long modificado_por) {
		this.modificado_por = modificado_por;
	}
	public Date getUltima_modificacion() {
		return ultima_modificacion;
	}
	public void setUltima_modificacion(Date ultima_modificacion) {
		this.ultima_modificacion = ultima_modificacion;
	}
	
}
