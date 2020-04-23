package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;

@Entity
@Table(name = "PAGOS_IMAGENES")
public class ExpedienteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5576489050291267102L;
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IMG_EXPEDIENTE_ID", unique = true, nullable = false)
	private Long img_expediente_id;
	@Column(name = "INFRAC_NUM", unique = true, nullable = false)
	private String infrac_Num;
	@Column(name = "INFRAC_NUM_CTRL")
	private String infrac_num_ctrl;
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombre_archivo;
	@Column(name = "ARCHIVO")
	private Blob Archivo;
	@Column(name = "TIPO")
	private String tipo;
	@Column(name = "ACTIVO")
	private Integer activo;
	@Column(name = "CREADO_POR")
	private Long creado_por ;
	@Column(name = "FECHA_CREACION")
	private Date fecha_creacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificado_por;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultima_modificacion;
	
	public Long getImg_expediente_id() {
		return img_expediente_id;
	}
	public void setImg_expediente_id(Long img_expediente_id) {
		this.img_expediente_id = img_expediente_id;
	}
	public String getInfrac_Num() {
		return infrac_Num;
	}
	public void setInfrac_Num(String infrac_Num) {
		this.infrac_Num = infrac_Num;
	}
	public String getInfrac_num_ctrl() {
		return infrac_num_ctrl;
	}
	public void setInfrac_num_ctrl(String infrac_num_ctrl) {
		this.infrac_num_ctrl = infrac_num_ctrl;
	}
	public String getNombre_archivo() {
		return nombre_archivo;
	}
	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}
	public Blob getArchivo() {
		return Archivo;
	}
	public void setArchivo(Blob archivo) {
		Archivo = archivo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
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
