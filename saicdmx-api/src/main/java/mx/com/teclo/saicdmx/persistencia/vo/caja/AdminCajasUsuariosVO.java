package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class AdminCajasUsuariosVO {
	

	private String pOperacion;
	private Long pCajaid;
	private Long pCajaEmpId;
	private Long pCajaEmpPerfilId;
	private Long pEmpId;
	private Long pEmpCajaId;
	private Long pEmpPerfilId;
	private String pEmpPuedeCobrar;
	private Long pModificadoPor;
	private Long pResultado;
	private String pMensaje;
	
	private AdminCajasUsuariosSPVO adminCajasUsuariosSPVO;
	
	
	public String getpOperacion() {
		return pOperacion;
	}
	public void setpOperacion(String pOperacion) {
		this.pOperacion = pOperacion;
	}
	public Long getpCajaid() {
		return pCajaid;
	}
	public void setpCajaid(Long pCajaid) {
		this.pCajaid = pCajaid;
	}
	public Long getpCajaEmpId() {
		return pCajaEmpId;
	}
	public void setpCajaEmpId(Long pCajaEmpId) {
		this.pCajaEmpId = pCajaEmpId;
	}
	public Long getpCajaEmpPerfilId() {
		return pCajaEmpPerfilId;
	}
	public void setpCajaEmpPerfilId(Long pCajaEmpPerfilId) {
		this.pCajaEmpPerfilId = pCajaEmpPerfilId;
	}
	public Long getpEmpId() {
		return pEmpId;
	}
	public void setpEmpId(Long pEmpId) {
		this.pEmpId = pEmpId;
	}
	public Long getpEmpCajaId() {
		return pEmpCajaId;
	}
	public void setpEmpCajaId(Long pEmpCajaId) {
		this.pEmpCajaId = pEmpCajaId;
	}
	public Long getpEmpPerfilId() {
		return pEmpPerfilId;
	}
	public void setpEmpPerfilId(Long pEmpPerfilId) {
		this.pEmpPerfilId = pEmpPerfilId;
	}
	public String getpEmpPuedeCobrar() {
		return pEmpPuedeCobrar;
	}
	public void setpEmpPuedeCobrar(String pEmpPuedeCobrar) {
		this.pEmpPuedeCobrar = pEmpPuedeCobrar;
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
	public AdminCajasUsuariosSPVO getAdminCajasUsuariosSPVO() {
		return adminCajasUsuariosSPVO;
	}
	public void setAdminCajasUsuariosSPVO(AdminCajasUsuariosSPVO adminCajasUsuariosSPVO) {
		this.adminCajasUsuariosSPVO = adminCajasUsuariosSPVO;
	}
	
	public void generaParametrosParaSP(){
		adminCajasUsuariosSPVO = new AdminCajasUsuariosSPVO(
				pOperacion,
				pCajaid,
				pCajaEmpId,
				pCajaEmpPerfilId,
				pEmpId,
				pEmpCajaId,
				pEmpPerfilId,
				pEmpPuedeCobrar,
				pModificadoPor,
				pResultado,
				pMensaje);
	}
}
