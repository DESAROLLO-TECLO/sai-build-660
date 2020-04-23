package mx.com.teclo.saicdmx.negocio.service.caja;

import java.io.ByteArrayOutputStream;
//import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.pdf.garantia.GarantiasRecibe;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.cajas.CajaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.FNRenglonesCortePartidasVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.FnRenglonesCorteInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.FnRenglonesCortePartidasTarVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.ProcInformeCorteSPVO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CajaReporteServiceImpl implements CajaReporteService {
	
	private static final Logger logger = Logger
			.getLogger(GarantiasRecibe.class);
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private CajaMyBatisDAO cajaMyBatisDAO;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ByteArrayOutputStream impresionCorte(String rutaTotalArchivo, ProcInformeCorteSPVO procInformeCorteSPVO) throws FileNotFoundException{
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		//File img = new File(context.getRealPath("/WEB-INF/imagenes/logo_SSP_bn.png"));
        //InputStream imagen = new FileInputStream(img);
        Map parametros = new HashMap();
        //parametros.put("hu", new HashMap<String,String>());
        parametros.put("cantB1000", String.valueOf(procInformeCorteSPVO.getP_EFE_B1000()));
        parametros.put("cantB500", String.valueOf(procInformeCorteSPVO.getP_EFE_B500()));
        parametros.put("cantB200", String.valueOf(procInformeCorteSPVO.getP_EFE_B200()));
        parametros.put("cantB100", String.valueOf(procInformeCorteSPVO.getP_EFE_B100()));
        parametros.put("cantB50", String.valueOf(procInformeCorteSPVO.getP_EFE_B50()));
        parametros.put("cantB20", String.valueOf(procInformeCorteSPVO.getP_EFE_B20()));
        parametros.put("cantM20", String.valueOf(procInformeCorteSPVO.getP_EFE_M20()));
        parametros.put("cantM10", String.valueOf(procInformeCorteSPVO.getP_EFE_M10()));
        parametros.put("cantM5", String.valueOf(procInformeCorteSPVO.getP_EFE_M5()));
        parametros.put("cantM2", String.valueOf(procInformeCorteSPVO.getP_EFE_M2()));
        parametros.put("cantM1", String.valueOf(procInformeCorteSPVO.getP_EFE_M1()));
        parametros.put("cantM_5", String.valueOf(procInformeCorteSPVO.getP_EFE_MC50()));
        parametros.put("cantM_2", String.valueOf(procInformeCorteSPVO.getP_EFE_MC20()));
        parametros.put("cantM_1", String.valueOf(procInformeCorteSPVO.getP_EFE_MC10()));
        parametros.put("cantM_05", String.valueOf(procInformeCorteSPVO.getP_EFE_MC5()));
        parametros.put("impB1000", String.valueOf(procInformeCorteSPVO.getP_EFE_B1000() * 1000));
        parametros.put("impB500", String.valueOf(procInformeCorteSPVO.getP_EFE_B500() * 500));
        parametros.put("impB200", String.valueOf(procInformeCorteSPVO.getP_EFE_B200() * 200));
        parametros.put("impB100", String.valueOf(procInformeCorteSPVO.getP_EFE_B100() * 100));
        parametros.put("impB50", String.valueOf(procInformeCorteSPVO.getP_EFE_B50() * 50));
        parametros.put("impB20", String.valueOf(procInformeCorteSPVO.getP_EFE_B20() * 20));
        parametros.put("impM20", String.valueOf(procInformeCorteSPVO.getP_EFE_M20() * 20));
        parametros.put("impM10", String.valueOf(procInformeCorteSPVO.getP_EFE_M10() * 10));
        parametros.put("impM5", String.valueOf(procInformeCorteSPVO.getP_EFE_M5() * 5));
        parametros.put("impM2", String.valueOf(procInformeCorteSPVO.getP_EFE_M2() * 2));
        parametros.put("impM1", String.valueOf(procInformeCorteSPVO.getP_EFE_M1() * 1));
        parametros.put("impM_5", String.valueOf((float) procInformeCorteSPVO.getP_EFE_MC50() * 0.5F));
        parametros.put("impM_2", String.valueOf((float) procInformeCorteSPVO.getP_EFE_MC20() * 0.2F));
        parametros.put("impM_1", String.valueOf((float) procInformeCorteSPVO.getP_EFE_MC10() * 0.1F));
        parametros.put("impM_05", String.valueOf((float) procInformeCorteSPVO.getP_EFE_MC50() * 0.05F));
        parametros.put("impDocTD", "0");
        parametros.put("impDocTC", "0");
        parametros.put("impDocSubtotal", procInformeCorteSPVO.getP_total_otros().toString());
        parametros.put("impRecTD", procInformeCorteSPVO.getP_total_cheques().toString());
        parametros.put("impRecEfec", procInformeCorteSPVO.getP_total_efectivo().toString());
        parametros.put("impTotalConc", "0");
        parametros.put("impRecTC", procInformeCorteSPVO.getP_total_tar_credito().toString());
        parametros.put("resTotConc", "0");
        parametros.put("resPartIni", procInformeCorteSPVO.getP_partida_inicial().toString().substring(3, procInformeCorteSPVO.getP_partida_inicial().toString().length()));
        parametros.put("resPartFin", procInformeCorteSPVO.getP_partida_final().toString().substring(3, procInformeCorteSPVO.getP_partida_final().toString().length()));
        parametros.put("resOper", procInformeCorteSPVO.getP_num_operaciones().toString());
        parametros.put("resTotRec", procInformeCorteSPVO.getP_total_corte().toString());
        parametros.put("resTotCorr", "0");
        parametros.put("resTotAjus", "0");
        parametros.put("resSobr", "0");
        parametros.put("cantBsubtotal", String.valueOf(procInformeCorteSPVO.getP_EFE_B1000() + procInformeCorteSPVO.getP_EFE_B500() + procInformeCorteSPVO.getP_EFE_B200() + procInformeCorteSPVO.getP_EFE_B100() + procInformeCorteSPVO.getP_EFE_B50() + procInformeCorteSPVO.getP_EFE_B20()));
        parametros.put("impBsubtotal", String.valueOf(procInformeCorteSPVO.getP_EFE_B1000() * 1000 + procInformeCorteSPVO.getP_EFE_B500() * 500 + procInformeCorteSPVO.getP_EFE_B200() * 200 + procInformeCorteSPVO.getP_EFE_B100() * 100 + procInformeCorteSPVO.getP_EFE_B50() * 50 + procInformeCorteSPVO.getP_EFE_B20() * 20));
        parametros.put("cantMsubtotal", String.valueOf(procInformeCorteSPVO.getP_EFE_M20() + procInformeCorteSPVO.getP_EFE_M10() + procInformeCorteSPVO.getP_EFE_M5() + procInformeCorteSPVO.getP_EFE_M2() + procInformeCorteSPVO.getP_EFE_M1() + procInformeCorteSPVO.getP_EFE_MC50() + procInformeCorteSPVO.getP_EFE_MC20() + procInformeCorteSPVO.getP_EFE_MC10() + procInformeCorteSPVO.getP_EFE_MC5()));
        parametros.put("impMsubtotal", String.valueOf((float) (procInformeCorteSPVO.getP_EFE_M20() * 20 + procInformeCorteSPVO.getP_EFE_M10() * 10 + procInformeCorteSPVO.getP_EFE_M5() * 5 + procInformeCorteSPVO.getP_EFE_M2() * 2 + procInformeCorteSPVO.getP_EFE_M1() * 1) + (float) procInformeCorteSPVO.getP_EFE_MC50() * 0.5F + (float) procInformeCorteSPVO.getP_EFE_MC20() * 0.2F + (float) procInformeCorteSPVO.getP_EFE_MC10() * 0.1F + (float) procInformeCorteSPVO.getP_EFE_MC5() * 0.05F));
        parametros.put("corteArea", "PAGOS INFRACCIONES");
        
        parametros.put("corteCaja", procInformeCorteSPVO.getCaja_cod());
        parametros.put("folioinicial", procInformeCorteSPVO.getP_RECIBO_FOL_INI());
        parametros.put("foliofinal", procInformeCorteSPVO.getP_RECIBO_FOL_FIN());
        parametros.put("totalfolios", String.valueOf(procInformeCorteSPVO.getP_RECIBO_TOTAL()));
        parametros.put("utilizados", String.valueOf(procInformeCorteSPVO.getP_RECIBO_UTILIZADOS()));
        parametros.put("cancelados", String.valueOf(procInformeCorteSPVO.getP_RECIBO_CANCELADOS()));
        parametros.put("inutilizados", String.valueOf(procInformeCorteSPVO.getP_RECIBO_INUTILIZADOS()));
        parametros.put("faltantes", String.valueOf(procInformeCorteSPVO.getP_RECIBO_FALTANTES()));
        if(procInformeCorteSPVO.getPTXT_FECHA_CORTE()!= null)
        	parametros.put("fecha", remplazaMes(procInformeCorteSPVO.getPTXT_FECHA_CORTE()));
        try {
	    	reporte.write(JasperRunManager.runReportToPdf(rutaTotalArchivo, parametros,new JREmptyDataSource()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	    return reporte;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ByteArrayOutputStream impresionRenglonesCortePartida(String cajaId, String numCaja, String fecha){
		FNRenglonesCortePartidasVO fNRenglonesCortePartidasVO = new FNRenglonesCortePartidasVO();
		fNRenglonesCortePartidasVO.setCajaId(cajaId);
		fNRenglonesCortePartidasVO.setNumCaja(numCaja);
		List<FNRenglonesCortePartidasVO> listFNRenglonesCortePartidasVO = cajaMyBatisDAO.renglonesCortePartida(fNRenglonesCortePartidasVO);
		String rutaArchivo;
		
		JRBeanCollectionDataSource DS = new JRBeanCollectionDataSource(listFNRenglonesCortePartidasVO);
		Map parametros = new HashMap();
		parametros.put("recordDataSource", DS);
		
		if(fecha.equals("")){
			rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/new_add_partida_corte.jasper");
		}else{
			parametros.put("fecha", remplazaMes(fecha));
			rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/new_add_partida_corte_consulta.jasper");
		}
		
		
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		try {
			reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ByteArrayOutputStream impresionRenglonesCorteInfraccion(String cajaId, String numCaja, String fecha){
		FnRenglonesCorteInfraccionVO fnRenglonesCorteInfraccionVO = new FnRenglonesCorteInfraccionVO();
		fnRenglonesCorteInfraccionVO.setCajaId(cajaId);
		fnRenglonesCorteInfraccionVO.setNumCaja(numCaja);
		List<FnRenglonesCorteInfraccionVO> listfnRenglonesCorteInfraccionVO = cajaMyBatisDAO.renglonesCorteInfraccion(fnRenglonesCorteInfraccionVO);
		String rutaArchivo;
		
		JRBeanCollectionDataSource DS = new JRBeanCollectionDataSource(listfnRenglonesCorteInfraccionVO);
		Map parametros = new HashMap();
		parametros.put("recordDataSource", DS);
		
		if(fecha.equals("")){
			rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/new_add_infracciones_corte.jasper");
		}else{
			parametros.put("fecha", remplazaMes(fecha));
			rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/new_add_infracciones_corte_consulta.jasper");
		}
		
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		try {
			reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ByteArrayOutputStream impresionRenglonesCortePartidaTarjeta(String cajaId, String numCaja, String fecha, String cajaCod){
		FnRenglonesCortePartidasTarVO fnRenglonesCortePartidasTarVO = new FnRenglonesCortePartidasTarVO();
		fnRenglonesCortePartidasTarVO.setCajaId(cajaId);
		fnRenglonesCortePartidasTarVO.setNumCaja(numCaja);
		List<FnRenglonesCortePartidasTarVO> listFnRenglonesCortePartidasTarVO = cajaMyBatisDAO.renglonesCortePartidaTarjeta(fnRenglonesCortePartidasTarVO);
		String rutaArchivo;
		
		JRBeanCollectionDataSource DS = new JRBeanCollectionDataSource(listFnRenglonesCortePartidasTarVO);
		Map parametros = new HashMap();
		parametros.put("recordDataSource", DS);
		parametros.put("CAJACOD", cajaCod);
		
		if(fecha.equals("")){
			rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/new_add_totalizacion_corte.jasper");
		}else{
			parametros.put("fecha", remplazaMes(fecha));
			rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/new_add_totalizacion_corte_consulta.jasper");
		}
		
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		try {
			reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;
	}
	
	private String remplazaMes(String fecha){
		String mes = fecha.substring(3,5);
		
		if(mes.equalsIgnoreCase("01"))
			mes = "ENERO";
		if(mes.equalsIgnoreCase("02"))
			mes = "FEBRERO";
		if(mes.equalsIgnoreCase("03"))
			mes = "MARZO";
		if(mes.equalsIgnoreCase("04"))
			mes = "ABRIL";
		if(mes.equalsIgnoreCase("05"))
			mes = "MAYO";
		if(mes.equalsIgnoreCase("06"))
			mes = "JUNIO";
		if(mes.equalsIgnoreCase("07"))
			mes = "JULIO";
		if(mes.equalsIgnoreCase("08"))
			mes = "AGOSTO";
		if(mes.equalsIgnoreCase("09"))
			mes = "SEPTIEMBRE";
		if(mes.equalsIgnoreCase("10"))
			mes = "OCTUBRE";
		if(mes.equalsIgnoreCase("11"))
			mes = "NOVIEMBRE";
		if(mes.equalsIgnoreCase("12"))
			mes = "DICIEMBRE";
		
		return fecha.substring(0,3)+mes+fecha.substring(5,fecha.length());
	}
}
