package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class AdminCajasModifVO {

	private String pOperacion;
	private Long pCajaId;
	private Long pCajaEmpId;
	private Long pModificadoPor;
	private Long pResultado;
	private String pMensaje;
	private String cdAplicacion;
	private String stCaja;
	private Long cajaDepId;
	private String cajaCod;
	private AdminCajasModifSPVO adminCajasModifSPVO;
	
	public String getpOperacion() {
		return pOperacion;
	}
	public void setpOperacion(String pOperacion) {
		this.pOperacion = pOperacion;
	}
	public Long getpCajaId() {
		return pCajaId;
	}
	public void setpCajaId(Long pCajaId) {
		this.pCajaId = pCajaId;
	}
	public Long getpCajaEmpId() {
		return pCajaEmpId;
	}
	public void setpCajaEmpId(Long pCajaEmpId) {
		this.pCajaEmpId = pCajaEmpId;
	}
	public Long getpModificadoPor() {
		return pModificadoPor;
	}
	public void setpModificadoPor(Long pModificadoPor) {
		this.pModificadoPor = pModificadoPor;
	}
	public Long getpResultado() {
		return pResultado;
	}
	public void setpResultado(Long pResultado) {
		this.pResultado = pResultado;
	}
	public String getpMensaje() {
		return pMensaje;
	}
	public void setpMensaje(String pMensaje) {
		this.pMensaje = pMensaje;
	}
	public AdminCajasModifSPVO getAdminCajasModifSPVO() {
		return adminCajasModifSPVO;
	}
	public void setAdminCajasModifSPVO(AdminCajasModifSPVO adminCajasModifSPVO) {
		this.adminCajasModifSPVO = adminCajasModifSPVO;
	}
	public String getCdAplicacion() {
		return cdAplicacion;
	}
	public void setCdAplicacion(String cdAplicacion) {
		this.cdAplicacion = cdAplicacion;
	}
	public String getStCaja() {
		return stCaja;
	}
	public void setStCaja(String stCaja) {
		this.stCaja = stCaja;
	}
	public Long getCajaDepId() {
		return cajaDepId;
	}
	public void setCajaDepId(Long cajaDepId) {
		this.cajaDepId = cajaDepId;
	}
	public String getCajaCod() {
		return cajaCod;
	}
	public void setCajaCod(String cajaCod) {
		this.cajaCod = cajaCod;
	}
	public void generaParametrosParaSP(){
		adminCajasModifSPVO = new AdminCajasModifSPVO(
				pOperacion,
				pCajaId,
				pCajaEmpId,
				pModificadoPor,
				cdAplicacion, pResultado,
				pMensaje);
	}
}
