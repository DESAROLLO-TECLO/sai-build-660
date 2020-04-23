package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class AdminCajasNuevoVO {
	
	private String pOperacion;
	private Long pDepId;
	private Long pCajaId;
	private String pCajaCod;
	private Long pModificadoPor;
	private String pCdAplicacion;
	private Long pResultado;
	private String pMensaje;
	
	private AdminCajasNuevoSPVO adminCajasNuevoSPVO;

	public String getpOperacion() {
		return pOperacion;
	}

	public void setpOperacion(String pOperacion) {
		this.pOperacion = pOperacion;
	}

	public Long getpDepId() {
		return pDepId;
	}

	public void setpDepId(Long pDepId) {
		this.pDepId = pDepId;
	}

	public Long getpCajaId() {
		return pCajaId;
	}

	public void setpCajaId(Long pCajaId) {
		this.pCajaId = pCajaId;
	}

	public String getpCajaCod() {
		return pCajaCod;
	}

	public void setpCajaCod(String pCajaCod) {
		this.pCajaCod = pCajaCod;
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

	public AdminCajasNuevoSPVO getAdminCajasNuevoSPVO() {
		return adminCajasNuevoSPVO;
	}

	public void setAdminCajasNuevoSPVO(AdminCajasNuevoSPVO adminCajasNuevoSPVO) {
		this.adminCajasNuevoSPVO = adminCajasNuevoSPVO;
	}
	
	public void generaParametrosParaSP(){
		adminCajasNuevoSPVO = new AdminCajasNuevoSPVO(
			pOperacion,
			pDepId,
			pCajaId,
			pCajaCod,
			pModificadoPor,
			pCdAplicacion,
			pResultado,
			pMensaje); 
	}
}

