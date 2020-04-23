package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_REMISION_DETALLE_INFRACCION")
public class VRemDetalleInf {

	@Column(name = "INFRAC_NUM_CTRL", unique = true, nullable = false)
	private String nci;
	@Id
	@Column(name = "INFRAC_NUM", unique = true, nullable = false)
	private String infNum;
	@Column(name = "INFRAC_IMPRESA")
	private String infImp;
	@Column(name = "INFRAC_CON_PLACA")
	private String infConPlaca;
	@Column(name = "INFRAC_PLACA")
	private String placa;
	@Column(name = "INFRAC_LEY_TRANSPORTE")
	private String leyTrans;
	@Column(name = "EMP_ID")
	private Long empId;
	@Column(name = "EMP_NOMBRE_COMPLETO")
	private String empNombreComp;
	@Column(name = "EMP_PLACA")
	private String empPlaca;
	@Column(name = "GRUA_ID")
	private Long gruaId;
	@Column(name = "GRUA_COD")
	private String gruaCod;
	@Column(name = "INFRAC_ARRASTRE")
	private String infArrastre;
	@Column(name = "VEHICULO_TIPO")
	private Long vehTipo;
	@Column(name = "TIPO_INGRESO")
	private Long tipoIngr;

	/**
	 * @return the nci
	 */
	public String getNci() {
		return nci;
	}

	/**
	 * @param nci the nci to set
	 */
	public void setNci(String nci) {
		this.nci = nci;
	}

	/**
	 * @return the infNum
	 */
	public String getInfNum() {
		return infNum;
	}

	/**
	 * @param infNum the infNum to set
	 */
	public void setInfNum(String infNum) {
		this.infNum = infNum;
	}

	/**
	 * @return the infImp
	 */
	public String getInfImp() {
		return infImp;
	}

	/**
	 * @param infImp the infImp to set
	 */
	public void setInfImp(String infImp) {
		this.infImp = infImp;
	}

	/**
	 * @return the infConPlaca
	 */
	public String getInfConPlaca() {
		return infConPlaca;
	}

	/**
	 * @param infConPlaca the infConPlaca to set
	 */
	public void setInfConPlaca(String infConPlaca) {
		this.infConPlaca = infConPlaca;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the leyTrans
	 */
	public String getLeyTrans() {
		return leyTrans;
	}

	/**
	 * @param leyTrans the leyTrans to set
	 */
	public void setLeyTrans(String leyTrans) {
		this.leyTrans = leyTrans;
	}

	/**
	 * @return the empId
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * @return the empNombreComp
	 */
	public String getEmpNombreComp() {
		return empNombreComp;
	}

	/**
	 * @param empNombreComp the empNombreComp to set
	 */
	public void setEmpNombreComp(String empNombreComp) {
		this.empNombreComp = empNombreComp;
	}

	/**
	 * @return the empPlaca
	 */
	public String getEmpPlaca() {
		return empPlaca;
	}

	/**
	 * @param empPlaca the empPlaca to set
	 */
	public void setEmpPlaca(String empPlaca) {
		this.empPlaca = empPlaca;
	}

	/**
	 * @return the gruaId
	 */
	public Long getGruaId() {
		return gruaId;
	}

	/**
	 * @param gruaId the gruaId to set
	 */
	public void setGruaId(Long gruaId) {
		this.gruaId = gruaId;
	}

	/**
	 * @return the gruaCod
	 */
	public String getGruaCod() {
		return gruaCod;
	}

	/**
	 * @param gruaCod the gruaCod to set
	 */
	public void setGruaCod(String gruaCod) {
		this.gruaCod = gruaCod;
	}

	/**
	 * @return the infArrastre
	 */
	public String getInfArrastre() {
		return infArrastre;
	}

	/**
	 * @param infArrastre the infArrastre to set
	 */
	public void setInfArrastre(String infArrastre) {
		this.infArrastre = infArrastre;
	}

	/**
	 * @return the vehTipo
	 */
	public Long getVehTipo() {
		return vehTipo;
	}

	/**
	 * @param vehTipo the vehTipo to set
	 */
	public void setVehTipo(Long vehTipo) {
		this.vehTipo = vehTipo;
	}

	/**
	 * @return the tipoIngr
	 */
	public Long getTipoIngr() {
		return tipoIngr;
	}

	/**
	 * @param tipoIngr the tipoIngr to set
	 */
	public void setTipoIngr(Long tipoIngr) {
		this.tipoIngr = tipoIngr;
	}
}
