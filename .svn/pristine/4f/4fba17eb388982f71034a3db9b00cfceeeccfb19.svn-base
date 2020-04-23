package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudBancoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConceptoPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDenominacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEntidadPagosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoPagos {
	ArrayList<BitacoraCambiosVO> compararDenominacion(CrudDenominacionVO newCrudDenominacionVO, CrudDenominacionVO oldCrudDenominacionVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararConceptodePago(CrudConceptoPagoVO newCrudConceptoPagoVO, CrudConceptoPagoVO oldCrudConceptoPagoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararEntidad(CrudEntidadPagosVO newCrudEntidadPagosVO, CrudEntidadPagosVO oldCrudEntidadPagosVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararBanco(CrudBancoVO newCrudBancoVO, CrudBancoVO oldCrudBancoVO, String oldEstatus);
}
