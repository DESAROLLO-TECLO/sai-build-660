package mx.com.teclo.saicdmx.negocio.service.caja;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaConTieneOperacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteActVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteHistVO;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("cajaReporteExcel")
public class CajaReporteExcel {

	private ByteArrayOutputStream byteArrayOutputStream = null;

	public ByteArrayOutputStream generarReporteSinCajaInfraccion(List<VCajaSinCorteHistVO> listaRegistroVO,
			String nombreReporte) {

		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setNombreReporte(nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		//propiedadesReporte.setFechaI("10/10/2016");
		//propiedadesReporte.setFechaF("10/10/2016");

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();

		List<String> titulos = new ArrayList<String>();
		titulos.add("NO. DE CAJA");
		titulos.add("TIPO DE CAJA");
		titulos.add("USUARIO");
		titulos.add("PLACA USUARIO");
		titulos.add("TIPO USUARIO");
		titulos.add("OPERACIONES");
		titulos.add("FECHA");

		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for (VCajaSinCorteHistVO record : listaRegistroVO) {
			listaContenido1 = new ArrayList<String>();

			listaContenido1.add(record.getCajaCod());
			listaContenido1.add(record.getPerfilNombre());
			listaContenido1.add(record.getEmpNombreComp());
			listaContenido1.add(record.getEmpPlaca());
			listaContenido1.add(record.getEmpTipo());
			if (nombreReporte.equals("Caja Sin Corte Historico Total Infracción"))
				listaContenido1.add(record.getTotalCount());
			else {
				listaContenido1.add(
						"Infracción: " + record.getInfracNum() + " Monto Pagado: $" + record.getPagoTotal() + ".00");
			}
			listaContenido1.add(record.getFecha());

			hoja1.add(listaContenido1);
		}
		hojas.add(hoja1);

		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}

	public ByteArrayOutputStream generarReporteSinCajaInfraccionAct(List<VCajaSinCorteActVO> listaRegistroVO,
			String nombreReporte) {

		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setNombreReporte(nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		//propiedadesReporte.setFechaI("10/10/2016");
		//propiedadesReporte.setFechaF("10/10/2016");

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();

		List<String> titulos = new ArrayList<String>();
		titulos.add("NO. DE CAJA");
		titulos.add("TIPO DE CAJA");
		titulos.add("USUARIO");
		titulos.add("PLACA USUARIO");
		titulos.add("TIPO USUARIO");
		titulos.add("OPERACIONES");
		titulos.add("FECHA");

		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for (VCajaSinCorteActVO record : listaRegistroVO) {
			listaContenido1 = new ArrayList<String>();

			listaContenido1.add(record.getCajaCod());
			listaContenido1.add(record.getPerfilNombre());
			listaContenido1.add(record.getEmpNombreComp());
			listaContenido1.add(record.getEmpPlaca());
			listaContenido1.add(record.getEmpTipo());
			if (nombreReporte.equals("Caja Sin Corte Actual Total Infracción"))
				listaContenido1.add(record.getTotalCount());
			else {
				listaContenido1.add(
						"Infracción: " + record.getInfracNum() + " Monto Pagado: $" + record.getPagoTotal() + ".00");
			}
			listaContenido1.add(record.getFecha());

			hoja1.add(listaContenido1);
		}
		hojas.add(hoja1);

		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}

	public ByteArrayOutputStream generarReporteCajasAdmin(List<VCajaConsultaConTieneOperacionesVO> listaRegistroVO,
			String nombreReporte) {

		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setNombreReporte(nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		/*propiedadesReporte.setFechaI("10/10/2016");
		propiedadesReporte.setFechaF("10/10/2016");*/

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();

		List<String> titulos = new ArrayList<String>();
		titulos.add("CAJA");
		titulos.add("USUARIO");
		titulos.add("PLACA USUARIO");
		titulos.add("TIPO USUARIO");
		titulos.add("DEPÓSITO");
		titulos.add("ESTATUS CAJA");

		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for (VCajaConsultaConTieneOperacionesVO record : listaRegistroVO) {
			listaContenido1 = new ArrayList<String>();

			listaContenido1.add(record.getCajaCod());
			listaContenido1.add(record.getEmpNombre());
			listaContenido1.add(record.getEmpPlaca());
			listaContenido1.add(record.getEmpTipo());
			listaContenido1.add(record.getDeposito());
			listaContenido1.add(record.getCajaEstatus());
			
			hoja1.add(listaContenido1);
		}
		hojas.add(hoja1);

		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}
}
