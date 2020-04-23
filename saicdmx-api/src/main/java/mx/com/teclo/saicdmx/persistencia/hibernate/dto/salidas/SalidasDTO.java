package mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;



@Entity
@Table(name="MOV_DEP_VEHICULO")

public class SalidasDTO implements Serializable{
	

	private static final long serialVersionUID = 313439182656042234L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_MOVIMIENTO", unique = true, nullable = false)
	private Long idMovimiento;
	
	/*@Column(name = "INFRAC_NUM_SALIDA")
	private String numinfrac;*/

	@ManyToOne
	@JoinColumn(name = "INFRAC_NUM", nullable=false) 
	private IngresosDTO numinfraccion;

	@Column(name = "NUM_RESGUARDO")
	private String numResguardo;
	
	@Column(name = "NUM_SERIE")
	private String numSerie;
	
	@Column(name = "FOLIO_DOC")
	private String docSalida;
	
	@Column(name = "FCH_MOVIMIENTO")
	private Date fchSalida;
	
	@Column(name = "EMP_AUTORIZA_MOV")
	private Long idAutoriza;
	
	@Column(name = "ID_DEP_ORIGEN")
	private Long idDep;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "FCH_ULTIMA_MODIFICACION")
	private Date fchUltimaModificacion;
	
	@Column(name = "TPO_MOVIMIENTO")
	private Long movTpo;
	
	@Column(name = "ID_ADJUDICA")
	private Long idAdjudica;
	
	@Column(name = "ID_FASE")
	private Long idFase;
	
	@Column(name = "NUM_CTRL")
	private String numCtrl;
	
	@Column(name = "NUM_PLACA_VEHICULO")
	private String numPlaca;
	
	/*@Column(name = "IMG_SALIDA")
	private Blob imagen;*/

	@Column(name = "ID_DEP_DESTINO")
	private String depDestino;
	
	@Column(name = "ST_MOVIMIENTO")
	private String stMovimiento;
	
	@Column(name = "ACTIVO")
	private Long activo;
	
	@Column(name = "DEP_NOM_ORIGEN")
	private String depNomOrigen;
	
	@Column(name = "DEP_NOM_DESTINO")
	private String depNomDestino;
	
	@Column(name = "OBSERVACIONES")
	private String observaciones;
	
	@Column(name = "NU_ECONOMICO")
	private String noeconomico;
		
	@Column(name = "MEDIOTRANSP")
	private String mediotransp;
	
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getNoeconomico() {
		return noeconomico;
	}

	public void setNoeconomico(String noeconomico) {
		this.noeconomico = noeconomico;
	}

	public String getMediotransp() {
		return mediotransp;
	}

	public void setMediotransp(String mediotransp) {
		this.mediotransp = mediotransp;
	}

	public Long getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Long getMovTpo() {
		return movTpo;
	}

	public void setMovTpo(Long movTpo) {
		this.movTpo = movTpo;
	}

	public String getDepNomOrigen() {
		return depNomOrigen;
	}

	public void setDepNomOrigen(String depNomOrigen) {
		this.depNomOrigen = depNomOrigen;
	}

	public String getDepNomDestino() {
		return depNomDestino;
	}

	public void setDepNomDestino(String depNomDestino) {
		this.depNomDestino = depNomDestino;
	}

	public IngresosDTO getNuminfraccion() {
		return numinfraccion;
	}

	public void setNuminfraccion(IngresosDTO numinfraccion) {
		this.numinfraccion = numinfraccion;
	}
	
//	@OneToMany(mappedBy="salidasPadre")
//	private Set<ImgSalidasDTO> imagenes;
//	
//	public Set<ImgSalidasDTO> getImagenes() {
//		return imagenes;
//	}
//
//	public void setImagenes(Set<ImgSalidasDTO> imagenes) {
//		this.imagenes = imagenes;
//	}



	/*public String getNuminfrac() {
		return numinfrac;
	}

	public void setNuminfrac(String numinfrac) {
		this.numinfrac = numinfrac;
	}*/

	public String getNumResguardo() {
		return numResguardo;
	}

	public void setNumResguardo(String numResguardo) {
		this.numResguardo = numResguardo;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public String getDocSalida() {
		return docSalida;
	}

	public void setDocSalida(String docSalida) {
		this.docSalida = docSalida;
	}

	public Date getFchSalida() {
		return fchSalida;
	}

	public void setFchSalida(Date fchSalida) {
		this.fchSalida = fchSalida;
	}

	public Long getIdAutoriza() {
		return idAutoriza;
	}

	public void setIdAutoriza(Long idAutoriza) {
		this.idAutoriza = idAutoriza;
	}

	public Long getIdDep() {
		return idDep;
	}

	public void setIdDep(Long idDep) {
		this.idDep = idDep;
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

	public Date getFchUltimaModificacion() {
		return fchUltimaModificacion;
	}

	public void setFchUltimaModificacion(Date fchUltimaModificacion) {
		this.fchUltimaModificacion = fchUltimaModificacion;
	}

	public Long getSalidaTpo() {
		return movTpo;
	}

	public void setSalidaTpo(Long movTpo) {
		this.movTpo = movTpo;
	}

	public Long getIdAdjudica() {
		return idAdjudica;
	}

	public void setIdAdjudica(Long idAdjudica) {
		this.idAdjudica = idAdjudica;
	}

	public Long getIdFase() {
		return idFase;
	}

	public void setIdFase(Long idFase) {
		this.idFase = idFase;
	}

	public String getNumCtrl() {
		return numCtrl;
	}

	public void setNumCtrl(String numCtrl) {
		this.numCtrl = numCtrl;
	}

	public String getNumPlaca() {
		return numPlaca;
	}

	public void setNumPlaca(String numPlaca) {
		this.numPlaca = numPlaca;
	}

	/*public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}*/

	public String getDepDestino() {
		return depDestino;
	}

	public void setDepDestino(String depDestino) {
		this.depDestino = depDestino;
	}

	public String getStMovimiento() {
		return stMovimiento;
	}

	public void setStMovimiento(String stMovimiento) {
		this.stMovimiento = stMovimiento;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	

	
	
}
