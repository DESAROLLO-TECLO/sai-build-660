package mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MOV_DEP_IMAGENES")
public class ImgSalidasDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7822226445962047452L;
	
		  
	  @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "IMG_MOV_ID", unique = true, nullable = false)
		private Long imgMovId;
	  
		@Column(name = "INFRAC_NUM", unique = true, nullable = false)
		private String infracNum;
		
		@Column(name = "INFRAC_NUM_CTRL")
		private String infracNumCtrl;
		
		@Column(name = "NOMBRE_ARCHIVO")
		private String nombreArchivo;
		
		@Column(name = "ARCHIVO")
		private Blob archivo;
		
		@Column(name = "TIPO")
		private String tipo;
		
		@Column(name = "ACTIVO")
		private Integer activo;
		
		@Column(name = "CREADO_POR")
		private Long creadoPor ;
		
		@Column(name = "FECHA_CREACION")
		private Date fechaCreacion;
		
		@Column(name = "MODIFICADO_POR")
		private Long modificadoPor;
		
		@Column(name = "ULTIMA_MODIFICACION")
		private Date ultimaModificacion;

		public Long getImgMovId() {
			return imgMovId;
		}

		public void setImgMovId(Long imgMovId) {
			this.imgMovId = imgMovId;
		}

		public String getInfracNum() {
			return infracNum;
		}

		public void setInfracNum(String infracNum) {
			this.infracNum = infracNum;
		}

		public String getInfracNumCtrl() {
			return infracNumCtrl;
		}

		public void setInfracNumCtrl(String infracNumCtrl) {
			this.infracNumCtrl = infracNumCtrl;
		}

		public String getNombreArchivo() {
			return nombreArchivo;
		}

		public void setNombreArchivo(String nombreArchivo) {
			this.nombreArchivo = nombreArchivo;
		}

		public Blob getArchivo() {
			return archivo;
		}

		public void setArchivo(Blob archivo) {
			this.archivo = archivo;
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

		public Long getCreadoPor() {
			return creadoPor;
		}

		public void setCreadoPor(Long creadoPor) {
			this.creadoPor = creadoPor;
		}

		public Date getFechaCreacion() {
			return fechaCreacion;
		}

		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}

		public Long getModificadoPor() {
			return modificadoPor;
		}

		public void setModificadoPor(Long modificadoPor) {
			this.modificadoPor = modificadoPor;
		}

		public Date getUltimaModificacion() {
			return ultimaModificacion;
		}

		public void setUltimaModificacion(Date ultimaModificacion) {
			this.ultimaModificacion = ultimaModificacion;
		}
		
		

//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "IMG_MOV_ID", unique = true, nullable = false)
//	private Long idImgSalida;
//	
//	@Column(name =  "INFRAC_NUM")
//	private String infracNum;
//	
//	@Column(name =  "INFRAC_NUM_CTRL")
//	private String infracNumCtrl;
//	
//	@Column(name =  "NOMBRE_ARCHIVO")
//	private String nombreArchivo;
//	
//	@Column(name =  "ARCHIVO")
//	private Blob archivo;
//	
//	@Column(name =  "TIPO")
//	private String tipo;
//	
//	@Column(name =  "ACTIVO")
//	private Long activo;
//	
//	@Column(name =  "CREADO_POR")
//	private Long creadoPor;
//	
//	@Column(name =  "FECHA_CREACION")
//	private Date fechaCreacion;
//	
//	@Column(name =  "MODIFICADO_POR")
//	private Date modificadoPor;
//	
//	@Column(name =  "ULTIMA_MODIFICACION")
//	private Date ultimaModificacion;
//
//	public Long getIdImgSalida() {
//		return idImgSalida;
//	}
//
//	public void setIdImgSalida(Long idImgSalida) {
//		this.idImgSalida = idImgSalida;
//	}
//
//	public String getInfracNum() {
//		return infracNum;
//	}
//
//	public void setInfracNum(String infracNum) {
//		this.infracNum = infracNum;
//	}
//
//	public String getInfracNumCtrl() {
//		return infracNumCtrl;
//	}
//
//	public void setInfracNumCtrl(String infracNumCtrl) {
//		this.infracNumCtrl = infracNumCtrl;
//	}
//
//	public String getNombreArchivo() {
//		return nombreArchivo;
//	}
//
//	public void setNombreArchivo(String nombreArchivo) {
//		this.nombreArchivo = nombreArchivo;
//	}
//
//	public Blob getArchivo() {
//		return archivo;
//	}
//
//	public void setArchivo(Blob archivo) {
//		this.archivo = archivo;
//	}
//
//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}
//
//	public Long getActivo() {
//		return activo;
//	}
//
//	public void setActivo(Long activo) {
//		this.activo = activo;
//	}
//
//	public Long getCreadoPor() {
//		return creadoPor;
//	}
//
//	public void setCreadoPor(Long creadoPor) {
//		this.creadoPor = creadoPor;
//	}
//
//	public Date getFechaCreacion() {
//		return fechaCreacion;
//	}
//
//	public void setFechaCreacion(Date fechaCreacion) {
//		this.fechaCreacion = fechaCreacion;
//	}
//
//	public Date getModificadoPor() {
//		return modificadoPor;
//	}
//
//	public void setModificadoPor(Date modificadoPor) {
//		this.modificadoPor = modificadoPor;
//	}
//
//	public Date getUltimaModificacion() {
//		return ultimaModificacion;
//	}
//
//	public void setUltimaModificacion(Date ultimaModificacion) {
//		this.ultimaModificacion = ultimaModificacion;
//	}
//	
	
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name="ID_SALIDA", nullable=false)
//	private SalidasDTO salidasPadre;

	
	


	

}
