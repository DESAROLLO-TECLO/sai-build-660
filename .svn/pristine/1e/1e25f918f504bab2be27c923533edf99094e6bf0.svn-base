package mx.com.teclo.saicdmx.negocio.service.remisionadeposito;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InventarioDepositosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ArticuloSancionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ConInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.IngresoInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OficialIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.SecAgrupArrasEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.VehiculoTipoColorMarcaVO;

public interface RemisionaDepositoService {

	public List<ConInfraccionVO> buscarConInfraccion(String opcion, String valor);

	public IngresoInfraccionVO buscarUpdPorId(String t_nci);

	public IngresoInfraccionVO altaIngresoInfraccion(IngresoInfraccionVO ingresoInfracVO) throws ParseException;
	
	public IngresoInfraccionVO altaIngresoAdmin(IngresoInfraccionVO ingresoInfracVO); 

	public OficialIngresoVO buscarOficialPorPlaca(String infrac_placa_empl);

	public ByteArrayOutputStream generaReporteResguardo(String infraccionNum, String rutaArchivo)throws FileNotFoundException;

	public ByteArrayOutputStream generaReporteArrastre(String infracNum, String rutaArchivo)throws FileNotFoundException;

	public ArticuloSancionVO valorArticuloSancion(String infrac_num);

	public VehiculoTipoColorMarcaVO valorVehiculo(String infrac_num);

	public SecAgrupArrasEmpVO valorSecAgrupArrasEmp(String infrac_num);

	public List<ConInfraccionVO> buscarRemisiones(String opcion, String valor, Long depId);

	public IngresoInfraccionVO mostarDatosPorNCI(String infrac_num_ctrl);

	public IngresoInfraccionVO altaIngresoEmbargo(IngresoInfraccionVO ingresoInfracVO) throws ParseException;

	public IngresoInfraccionVO buscarPorNCI(String infrac_num_ctrl);

	public GruaConceVO valorGruaConce(String infrac_num);

	public OficialIngresoVO buscarOficialPorPlacaId(String infrac_placa_empl);

	

}
