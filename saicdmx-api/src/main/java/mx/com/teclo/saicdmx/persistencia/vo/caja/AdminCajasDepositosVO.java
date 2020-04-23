package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class AdminCajasDepositosVO {
	
	private String pOperacion;
	private Long pCajaId;
	private Long pCajaEmpId;
	private Long pDepId;
	private Long pModificadoPor;
	private String pCdAplicacion;
	private Long pResultado;
	private String pMensaje;
	
	private AdminCajasDepositosSPVO adminCajasDepositosSPVO;
	
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
	public Long getpDepId() {
		return pDepId;
	}
	public void setpDepId(Long pDepId) {
		this.pDepId = pDepId;
	}
	public Long getpModificadoPor() {
		return pModificadoPor;
	}
	public void setpModificadoPor(Long pModificadoPor) {
		this.pModificadoPor = pModificadoPor;
	}
	public String getpCdAplicacion() {
		return pCdAplicacion;
	}
	public void setpCdAplicacion(String pCdAplicacion) {
		this.pCdAplicacion = pCdAplicacion;
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
	public AdminCajasDepositosSPVO getAdminCajasDepositosSPVO() {
		return adminCajasDepositosSPVO;
	}
	public void setAdminCajasDepositosSPVO(AdminCajasDepositosSPVO adminCajasDepositosSPVO) {
		this.adminCajasDepositosSPVO = adminCajasDepositosSPVO;
	}

	public void generaParametrosParaSP(){
		adminCajasDepositosSPVO = new AdminCajasDepositosSPVO(
				pOperacion,
				pCajaId,
				pCajaEmpId,
				pDepId,
				pModificadoPor,
				pCdAplicacion,
				pResultado,
				pMensaje);
	}
}
