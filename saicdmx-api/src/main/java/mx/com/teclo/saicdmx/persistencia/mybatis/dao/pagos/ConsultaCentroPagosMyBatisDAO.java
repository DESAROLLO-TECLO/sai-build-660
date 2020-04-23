package mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import beans.BeanTransaccion;
import beans.administrativos.BeanConsultas;
import beans.administrativos.BeanConsultasTransaccion;
import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaCentroPagosService;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.RespuestaCentroPagosVO;
import operativas.Administrativas;
  
public class ConsultaCentroPagosMyBatisDAO {

     private static final String PAGO_APROBADO = "APPROVED";
     private ConsultaCentroPagosService consultaCentroPagosService;

 
//    protected List<RespuestaCentroPagosVO> obtenerRegistroCentroPagos(String fecha) {
//        List<RespuestaCentroPagosVO> listaPagos = new ArrayList<RespuestaCentroPagosVO>();
//        RespuestaCentroPagosVO transaccion = null;
//        BeanConsultasTransaccion bean[] = new BeanConsultasTransaccion[0];
//        Administrativas administrativas = new Administrativas();
//        BeanConsultas beanConsultas = new BeanConsultas();
//        BeanTransaccion beanTransaccion = new BeanTransaccion();
//        beanTransaccion.setBeanReceptor(consultaCentroPagosService.getBeanReceptor());
//        beanTransaccion.getBeanReceptor().setReference("");
//
//        beanConsultas.setDate(fecha);
//        beanTransaccion.setBeanConsultas(beanConsultas);
//        administrativas.Consultas(beanTransaccion);
//        if (beanTransaccion.getBeanRespuesta().getCdError() == null) {
//            bean = beanTransaccion.getBeanRespuesta().getBeanConsultasTransaccion();
//        }
//        if (bean.length > 0) {
//            for (int contador = 0; contador < bean.length; contador++) {
//                transaccion = new RespuestaCentroPagosVO();
//                transaccion.setImporte(bean[contador].getNu_importe());
//                transaccion.setNumeroAutorizacion(bean[contador].getNu_auth());
//                transaccion.setOperacionNumero(bean[contador].getNu_operacion());
//                transaccion.setOperacionRespuesta(bean[contador].getNb_response());
//                transaccion.setOperacionFecha(bean[contador].getFh_bank());
//                transaccion.setOperacionFechaHora(bean[contador].getFh_registro());
//                transaccion.setReferencia(bean[contador].getNb_referencia());
//                transaccion.setTarjetaNombreUsuario(bean[contador].getCc_nombre());
//                transaccion.setTarjetaNumero(bean[contador].getCc_num());
//                transaccion.setPagoAprobado(
//                        transaccion.getOperacionRespuesta().toUpperCase().
//                        equals(PAGO_APROBADO));
//                listaPagos.add(transaccion);
//            }
//
//        }
//
//        return listaPagos;
//    }
// 
//    public List<BeanConsultasTransaccion> obtenerReferenciasPagosHandheld(String fecha) {
//        List<BeanConsultasTransaccion> listaPagos = new LinkedList<BeanConsultasTransaccion>();
//        BeanConsultasTransaccion bean[] = new BeanConsultasTransaccion[0];
//        Administrativas administrativas = new Administrativas();
//        BeanConsultas beanConsultas = new BeanConsultas();
//        BeanTransaccion beanTransaccion = new BeanTransaccion();
//        beanTransaccion.setBeanReceptor(consultaCentroPagosService.getBeanReceptor());
//        beanTransaccion.getBeanReceptor().setReference("");
//        beanConsultas.setDate(fecha);
//        beanTransaccion.setBeanConsultas(beanConsultas);
//        administrativas.Consultas(beanTransaccion);
//        if (beanTransaccion.getBeanRespuesta().getCdError() == null) {
//            bean = beanTransaccion.getBeanRespuesta().getBeanConsultasTransaccion();
//        }
//        if (bean.length > 0) {
//            for (int contador = 0; contador < bean.length; contador++) {
//                if (bean[contador].getNb_response().toUpperCase().equals(PAGO_APROBADO)) {
//                    listaPagos.add(bean[contador]);
//                }
//            }
//        }
//
//        return listaPagos;
//    }  

//    protected List<String> obtenerTodasReferenciasEnCurso() {
//        List<String> listaReferencias = new LinkedList<String>();
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT nci FROM ctro_pagos_valida ")
//                .append("WHERE estatus_proceso=0");
//
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                listaReferencias.add(rs.getString("nci"));
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaReferencias;
//    }

//    protected boolean existeNCIEnCtroPagosValida(String nci) {
//
//        boolean existe = true;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT count(*) as existe FROM ctro_pagos_valida ")
//                .append("WHERE nci=?");
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, nci);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                existe = rs.getBoolean("existe");
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return existe;
//    }
/*
    protected int getEstatusRegistroCtroPagosValida(String nci) {

        int estatus = 0;
        Connection conexion = null;
        PreparedStatement pstBusqueda = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT ESTATUS_PROCESO FROM ctro_pagos_valida ")
                .append("WHERE nci=?");
        try {

            conexion = utileriaBD.Conectar();
            pstBusqueda = conexion.prepareStatement(query.toString());
            pstBusqueda.setString(1, nci);
            rs = pstBusqueda.executeQuery();
            while (rs.next()) {
                estatus = rs.getInt("ESTATUS_PROCESO");
            }
        } catch (Exception exception) {
            System.out.println("ERROR EN LA CLASE ["
                    + ConsultaCentroPagosMyBatisDAO.class
                    + "]:"
                    + exception.getMessage());
            exception.printStackTrace();
        } finally {
            utileriaBD.cerrarResultStatement(rs);
            utileriaBD.cerrarPreparedStatement(pstBusqueda);
            utileriaBD.cerrarConexion(conexion);
        }
        return estatus;
    }*/

//    protected EmpleadoBean getEmpleadoInfoFoliosInfracciones(String folioInfraccion) {
//
//        EmpleadoBean empleado = null;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT e.emp_id, emp_placa, ")
//                .append("emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno emp_nombre ")
//                .append("FROM folios_infracciones f JOIN empleados e ON e.emp_id = f.emp_id ")
//                .append("WHERE folio_tipo = 'I' AND infrac_folio = ?");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, folioInfraccion.substring(1, 10));
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                empleado = new EmpleadoBean();
//                empleado.setEmpleadoId(rs.getString("emp_id"));
//                empleado.setEmpleadoNombreCompleto(rs.getString("emp_nombre"));
//                empleado.setEmpleadoPlaca(rs.getString("emp_placa"));
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return empleado;
//    }
//
//    protected String getCtroPagosId(String nci) {
//        String id = "0";
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT CTRO_PAGOS_VALIDA_ID FROM ctro_pagos_valida ")
//                .append("WHERE nci=?");
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, nci);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                id = rs.getString("CTRO_PAGOS_VALIDA_ID");
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return id;
//    }

//    protected EmpleadoBean getEmpleadoPorNci(String nci) {
//
//        EmpleadoBean empleado = null;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT e.emp_id, emp_placa, ")
//                .append("emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno emp_nombre ")
//                .append("FROM infracciones i join empleados e ")
//                .append("ON e.emp_id = i.emp_id ")
//                .append("WHERE infrac_num_ctrl=?");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, nci);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                empleado = new EmpleadoBean();
//                empleado.setEmpleadoId(rs.getString("emp_id"));
//                empleado.setEmpleadoNombreCompleto(rs.getString("emp_nombre"));
//                empleado.setEmpleadoPlaca(rs.getString("emp_placa"));
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return empleado;
//    }
/*
    protected List<PagosCentroPagosValidaBean> getCtroPagosValidaTotalesPorFechas(String fechaInicial,
            String fechaFinal) {
        List<PagosCentroPagosValidaBean> listaCtroPagos =
                new LinkedList<PagosCentroPagosValidaBean>();
        PagosCentroPagosValidaBean pago;
        Connection conexion = null;
        PreparedStatement pstBusqueda = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT estatus_proceso,pago_origen, pago_fecha,")
                .append("cantidad ")
                .append("FROM V_CTRO_PAGOS_REP_TOTALES ")
                .append("WHERE pago_fecha BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
                .append("AND TO_DATE (?,'dd/mm/yyyy') ")
                .append("ORDER BY pago_fecha,estatus_proceso");

        try {
            conexion = utileriaBD.Conectar();
            pstBusqueda = conexion.prepareStatement(query.toString());
            pstBusqueda.setString(1, fechaInicial);
            pstBusqueda.setString(2, fechaFinal);
            rs = pstBusqueda.executeQuery();
            while (rs.next()) {
                pago = new PagosCentroPagosValidaBean();
                pago.setEstatusProceso(rs.getInt("estatus_proceso"));
                pago.setPagoOrigen(rs.getString("pago_origen"));
                pago.setPagoFecha(rs.getString("pago_fecha"));
                pago.setPagoCantidad(rs.getString("cantidad"));
                listaCtroPagos.add(pago);

            }
        } catch (Exception exception) {
            System.out.println("ERROR EN LA CLASE ["
                    + ConsultaCentroPagosMyBatisDAO.class
                    + "]:"
                    + exception.getMessage());
            exception.printStackTrace();
        } finally {
            utileriaBD.cerrarResultStatement(rs);
            utileriaBD.cerrarPreparedStatement(pstBusqueda);
            utileriaBD.cerrarConexion(conexion);
        }

        return listaCtroPagos;

    }

    protected String getCtroPagosTotalPorFechaOrigenEstatus(String fecha,
            String pagoOrigen, int estatus) {
        String total = "0";
        PagosCentroPagosValidaBean pago;
        Connection conexion = null;
        PreparedStatement pstBusqueda = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT cantidad ")
                .append("FROM V_CTRO_PAGOS_REP_TOTALES ")
                .append("WHERE pago_fecha=TO_DATE (?,'dd/mm/yyyy') ")
                .append("AND pago_origen=? ")
                .append("AND estatus_proceso=? ");

        try {
            conexion = utileriaBD.Conectar();
            pstBusqueda = conexion.prepareStatement(query.toString());
            pstBusqueda.setString(1, fecha);
            pstBusqueda.setString(2, pagoOrigen);
            pstBusqueda.setInt(3, estatus);
            rs = pstBusqueda.executeQuery();
            while (rs.next()) {
                total = rs.getString("cantidad");
            }
        } catch (Exception exception) {
            System.out.println("ERROR EN LA CLASE ["
                    + ConsultaCentroPagosMyBatisDAO.class
                    + "]:"
                    + exception.getMessage());
            exception.printStackTrace();
        } finally {
            utileriaBD.cerrarResultStatement(rs);
            utileriaBD.cerrarPreparedStatement(pstBusqueda);
            utileriaBD.cerrarConexion(conexion);
        }

        return total;

    }

    protected String getCtroPagosTotalPorFechaOrigenEstatus(String fechaInicial,
            String fechaFinal, String pagoOrigen, int estatus) {
        String total = "0";
        PagosCentroPagosValidaBean pago;
        Connection conexion = null;
        PreparedStatement pstBusqueda = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT nvl(sum(cantidad),0) cantidad ")
                .append("FROM V_CTRO_PAGOS_REP_TOTALES ")
                .append("WHERE pago_fecha ")
                .append("BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
                .append("AND TO_DATE (?,'dd/mm/yyyy') ")
                .append("AND pago_origen=? ")
                .append("AND estatus_proceso=? ");

        try {
            conexion = utileriaBD.Conectar();
            pstBusqueda = conexion.prepareStatement(query.toString());
            pstBusqueda.setString(1, fechaInicial);
            pstBusqueda.setString(2, fechaFinal);
            pstBusqueda.setString(3, pagoOrigen);
            pstBusqueda.setInt(4, estatus);
            rs = pstBusqueda.executeQuery();
            while (rs.next()) {
                total = rs.getString("cantidad");
            }
        } catch (Exception exception) {
            System.out.println("ERROR EN LA CLASE ["
                    + ConsultaCentroPagosMyBatisDAO.class
                    + "]:"
                    + exception.getMessage());
            exception.printStackTrace();
        } finally {
            utileriaBD.cerrarResultStatement(rs);
            utileriaBD.cerrarPreparedStatement(pstBusqueda);
            utileriaBD.cerrarConexion(conexion);
        }

        return total;

    }

    protected String getCtroPagosTotalPagosPorFecha(String fechaInicial,
            String fechaFinal) {
        String total = "0";
        PagosCentroPagosValidaBean pago;
        Connection conexion = null;
        PreparedStatement pstBusqueda = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT nvl(sum(cantidad),0) cantidad ")
                .append("FROM V_CTRO_PAGOS_REP_TOTALES ")
                .append("WHERE pago_fecha ")
                .append("BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
                .append("AND TO_DATE (?,'dd/mm/yyyy') ");

        try {
            conexion = utileriaBD.Conectar();
            pstBusqueda = conexion.prepareStatement(query.toString());
            pstBusqueda.setString(1, fechaInicial);
            pstBusqueda.setString(2, fechaFinal);
            rs = pstBusqueda.executeQuery();
            while (rs.next()) {
                total = rs.getString("cantidad");
            }
        } catch (Exception exception) {
            System.out.println("ERROR EN LA CLASE ["
                    + ConsultaCentroPagosMyBatisDAO.class
                    + "]:"
                    + exception.getMessage());
            exception.printStackTrace();
        } finally {
            utileriaBD.cerrarResultStatement(rs);
            utileriaBD.cerrarPreparedStatement(pstBusqueda);
            utileriaBD.cerrarConexion(conexion);
        }

        return total;

    }

    protected String getCtroPagosTotalPagosPorFecha(String fechaInicial) {
        String total = "0";
        PagosCentroPagosValidaBean pago;
        Connection conexion = null;
        PreparedStatement pstBusqueda = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT nvl(sum(cantidad),0) cantidad ")
                .append("FROM V_CTRO_PAGOS_REP_TOTALES ")
                .append("WHERE pago_fecha=TO_DATE (?,'dd/mm/yyyy') ");

        try {
            conexion = utileriaBD.Conectar();
            pstBusqueda = conexion.prepareStatement(query.toString());
            pstBusqueda.setString(1, fechaInicial);
            rs = pstBusqueda.executeQuery();
            while (rs.next()) {
                total = rs.getString("cantidad");
            }
        } catch (Exception exception) {
            System.out.println("ERROR EN LA CLASE ["
                    + ConsultaCentroPagosMyBatisDAO.class
                    + "]:"
                    + exception.getMessage());
            exception.printStackTrace();
        } finally {
            utileriaBD.cerrarResultStatement(rs);
            utileriaBD.cerrarPreparedStatement(pstBusqueda);
            utileriaBD.cerrarConexion(conexion);
        }

        return total;
**/
    }
////2
//    protected List<PagosCentroPagosValidaDTO> getAllCtroPagosValidaInCorrectosPorPeriodo(String fechaInicial,
//            String fechaFinal) {
//        List<PagosCentroPagosValidaDTO> listaCtroPagos =
//                new LinkedList<PagosCentroPagosValidaDTO>();
//        PagosCentroPagosValidaDTO pago;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT nci,nvl (infrac_num,' ') infrac_num, ")
//                .append("nvl(EMP_PLACA,' ') EMP_PLACA, nvl(EMP_NOMBRE,' ') EMP_NOMBRE,")
//                .append("EXISTE_INFRACCION,EXISTE_PAGO,EXISTE_DETALLE_PAGO,")
//                .append("EXISTE_DETALLE_CARGO,EXISTE_TRANSACCION,EXISTE_TRANSAC_INFRAC,")
//                .append("ESTATUS_PROCESO,TO_CHAR(FECHA_CREACION,'dd/mm/yyyy') FECHA_CREACION,")
//                .append("TO_CHAR(ULTIMA_MODIFICACION,'dd/mm/yyyy') ULTIMA_MODIFICACION,")
//                .append("to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') fecha_pago_ctro_pagos ")
//                .append("FROM CTRO_PAGOS_VALIDA where estatus_proceso=0")
//                .append("AND fecha_pago_ctro_pagos BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND TO_DATE (?,'dd/mm/yyyy') ")
//                .append("ORDER BY fecha_pago_ctro_pagos");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, fechaInicial);
//            pstBusqueda.setString(2, fechaFinal);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("nci"));
//                pago.setEmpNombre(rs.getString("EMP_NOMBRE"));
//                pago.setEmpPlaca(rs.getString("EMP_PLACA"));
//                pago.setExisteInfraccion(rs.getBoolean("EXISTE_INFRACCION"));
//                pago.setExistePago(rs.getBoolean("existe_pago"));
//                pago.setExisteDetallePagos(rs.getBoolean("existe_detalle_pago"));
//
//                pago.setExisteDetalleCargos(rs.getBoolean("EXISTE_DETALLE_CARGO"));
//                pago.setExistePagoTransaccion(rs.getBoolean("EXISTE_TRANSACCION"));
//                pago.setExisteInfracTransaccion(rs.getBoolean("EXISTE_TRANSAC_INFRAC"));
//                pago.setEstatusProceso(rs.getInt("ESTATUS_PROCESO"));
//                pago.setFechaCreacion(rs.getString("FECHA_CREACION"));
//                pago.setFechaModificacion(rs.getString("ULTIMA_MODIFICACION"));
//                pago.setFechaPagoCtroPagos(rs.getString("fecha_pago_ctro_pagos"));
//                listaCtroPagos.add(pago);
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaCtroPagos;
//
//    }
////3
//    protected List<PagosCentroPagosValidaDTO> getAllCtroPagosValidaInCorrectosPorFechaTipoPago(String fechaInicial,
//            String tipoPago) {
//        List<PagosCentroPagosValidaDTO> listaCtroPagos =
//                new LinkedList<PagosCentroPagosValidaDTO>();
//        PagosCentroPagosValidaDTO pago;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT nci,nvl (infrac_num,' ') infrac_num, ")
//                .append("nvl(EMP_PLACA,' ') EMP_PLACA, nvl(EMP_NOMBRE,' ') EMP_NOMBRE,")
//                .append("EXISTE_INFRACCION,EXISTE_PAGO,EXISTE_DETALLE_PAGO,")
//                .append("EXISTE_DETALLE_CARGO,EXISTE_TRANSACCION,EXISTE_TRANSAC_INFRAC,")
//                .append("ESTATUS_PROCESO,TO_CHAR(FECHA_CREACION,'dd/mm/yyyy') FECHA_CREACION,")
//                .append("TO_CHAR(ULTIMA_MODIFICACION,'dd/mm/yyyy') ULTIMA_MODIFICACION,")
//                .append("to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') fecha_pago_ctro_pagos ")
//                .append("FROM CTRO_PAGOS_VALIDA where estatus_proceso=0")
//                .append("AND fecha_pago_ctro_pagos=TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND pago_origen=? ")
//                .append("ORDER BY fecha_pago_ctro_pagos");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, fechaInicial);
//            pstBusqueda.setString(2, tipoPago);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("nci"));
//                pago.setEmpNombre(rs.getString("EMP_NOMBRE"));
//                pago.setEmpPlaca(rs.getString("EMP_PLACA"));
//                pago.setExisteInfraccion(rs.getBoolean("EXISTE_INFRACCION"));
//                pago.setExistePago(rs.getBoolean("existe_pago"));
//                pago.setExisteDetallePagos(rs.getBoolean("existe_detalle_pago"));
//                pago.setExisteDetalleCargos(rs.getBoolean("EXISTE_DETALLE_CARGO"));
//                pago.setExistePagoTransaccion(rs.getBoolean("EXISTE_TRANSACCION"));
//                pago.setExisteInfracTransaccion(rs.getBoolean("EXISTE_TRANSAC_INFRAC"));
//                pago.setEstatusProceso(rs.getInt("ESTATUS_PROCESO"));
//                pago.setFechaCreacion(rs.getString("FECHA_CREACION"));
//                pago.setFechaModificacion(rs.getString("ULTIMA_MODIFICACION"));
//                pago.setFechaPagoCtroPagos(rs.getString("fecha_pago_ctro_pagos"));
//                listaCtroPagos.add(pago);
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaCtroPagos;
//
//    }
////1
//    protected List<PagosCentroPagosValidaDTO> getAllCtroPagosValidaInCorrectosPorPeriodoTipo(String fechaInicial,
//            String fechaFinal, String tipoPago) {
//        List<PagosCentroPagosValidaDTO> listaCtroPagos =
//                new LinkedList<PagosCentroPagosValidaDTO>();
//        PagosCentroPagosValidaDTO pago;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT nci,nvl (infrac_num,' ') infrac_num, ")
//                .append("nvl(EMP_PLACA,' ') EMP_PLACA, nvl(EMP_NOMBRE,' ') EMP_NOMBRE,")
//                .append("EXISTE_INFRACCION,EXISTE_PAGO,EXISTE_DETALLE_PAGO,")
//                .append("EXISTE_DETALLE_CARGO,EXISTE_TRANSACCION,EXISTE_TRANSAC_INFRAC,")
//                .append("ESTATUS_PROCESO,TO_CHAR(FECHA_CREACION,'dd/mm/yyyy') FECHA_CREACION,")
//                .append("TO_CHAR(ULTIMA_MODIFICACION,'dd/mm/yyyy') ULTIMA_MODIFICACION,")
//                .append("to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') fecha_pago_ctro_pagos ")
//                .append("FROM CTRO_PAGOS_VALIDA where estatus_proceso=0")
//                .append("AND fecha_pago_ctro_pagos BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND pago_origen=? ")
//                .append("ORDER BY fecha_pago_ctro_pagos,pago_origen");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, fechaInicial);
//            pstBusqueda.setString(2, fechaFinal);
//            pstBusqueda.setString(3, tipoPago);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("nci"));
//                pago.setEmpNombre(rs.getString("EMP_NOMBRE"));
//                pago.setEmpPlaca(rs.getString("EMP_PLACA"));
//                pago.setExisteInfraccion(rs.getBoolean("EXISTE_INFRACCION"));
//                pago.setExistePago(rs.getBoolean("existe_pago"));
//                pago.setExisteDetallePagos(rs.getBoolean("existe_detalle_pago"));
//                pago.setExisteDetalleCargos(rs.getBoolean("EXISTE_DETALLE_CARGO"));
//                pago.setExistePagoTransaccion(rs.getBoolean("EXISTE_TRANSACCION"));
//                pago.setExisteInfracTransaccion(rs.getBoolean("EXISTE_TRANSAC_INFRAC"));
//                pago.setEstatusProceso(rs.getInt("ESTATUS_PROCESO"));
//                pago.setFechaCreacion(rs.getString("FECHA_CREACION"));
//                pago.setFechaModificacion(rs.getString("ULTIMA_MODIFICACION"));
//                pago.setFechaPagoCtroPagos(rs.getString("fecha_pago_ctro_pagos"));
//                listaCtroPagos.add(pago);
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaCtroPagos;
//
//    }
////5
//    protected List<PagosCentroPagosValidaDTO> getAllCtroPagosValidaCorrectosPorPeriodo(String fechaInicial,
//            String fechaFinal) {
//        List<PagosCentroPagosValidaDTO> listaCtroPagos =
//                new LinkedList<PagosCentroPagosValidaDTO>();
//        PagosCentroPagosValidaDTO pago;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT nci,infrac_num, EMP_PLACA,EMP_NOMBRE,")
//                .append("to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') fecha_pago_ctro_pagos ")
//                .append("FROM CTRO_PAGOS_VALIDA where estatus_proceso=1")
//                .append("AND fecha_pago_ctro_pagos BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND TO_DATE (?,'dd/mm/yyyy') ")
//                .append("ORDER BY fecha_pago_ctro_pagos");;
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, fechaInicial);
//            pstBusqueda.setString(2, fechaFinal);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("nci"));
//                pago.setEmpNombre(rs.getString("EMP_NOMBRE"));
//                pago.setEmpPlaca(rs.getString("EMP_PLACA"));
//                pago.setFechaPagoCtroPagos(rs.getString("fecha_pago_ctro_pagos"));
//                listaCtroPagos.add(pago);
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaCtroPagos;
//
//    }
////4
//    protected List<PagosCentroPagosValidaDTO> getAllCtroPagosValidaCorrectosPorPeriodoTipoPago(String fechaInicial,
//            String fechaFinal, String tipoPago) {
//        List<PagosCentroPagosValidaDTO> listaCtroPagos =
//                new LinkedList<PagosCentroPagosValidaDTO>();
//        PagosCentroPagosValidaDTO pago;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT nci,infrac_num, EMP_PLACA,EMP_NOMBRE,")
//                .append("to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') fecha_pago_ctro_pagos ")
//                .append("FROM CTRO_PAGOS_VALIDA where estatus_proceso=1")
//                .append("AND fecha_pago_ctro_pagos BETWEEN TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND pago_origen=? ")
//                .append("ORDER BY fecha_pago_ctro_pagos,pago_origen");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, fechaInicial);
//            pstBusqueda.setString(2, fechaFinal);
//            pstBusqueda.setString(3, tipoPago);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("nci"));
//                pago.setEmpNombre(rs.getString("EMP_NOMBRE"));
//                pago.setEmpPlaca(rs.getString("EMP_PLACA"));
//                pago.setFechaPagoCtroPagos(rs.getString("fecha_pago_ctro_pagos"));
//                listaCtroPagos.add(pago);
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaCtroPagos;
//
//    }
////6
//    protected List<PagosCentroPagosValidaDTO> getAllCtroPagosValidaCorrectosPorFechaTipoPago(String fechaInicial,
//            String tipoPago) {
//        List<PagosCentroPagosValidaDTO> listaCtroPagos =
//                new LinkedList<PagosCentroPagosValidaDTO>();
//        PagosCentroPagosValidaDTO pago;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT nci,infrac_num, EMP_PLACA,EMP_NOMBRE,")
//                .append("to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') fecha_pago_ctro_pagos ")
//                .append("FROM CTRO_PAGOS_VALIDA where estatus_proceso=1")
//                .append("AND fecha_pago_ctro_pagos =TO_DATE (?,'dd/mm/yyyy') ")
//                .append("AND pago_origen=? ")
//                .append("ORDER BY fecha_pago_ctro_pagos,pago_origen");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, fechaInicial);
//            pstBusqueda.setString(2, tipoPago);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("nci"));
//                pago.setEmpNombre(rs.getString("EMP_NOMBRE"));
//                pago.setEmpPlaca(rs.getString("EMP_PLACA"));
//                pago.setFechaPagoCtroPagos(rs.getString("fecha_pago_ctro_pagos"));
//                listaCtroPagos.add(pago);
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//
//        return listaCtroPagos;
//
//    }

//    protected PagosCentroPagosValidaDTO getPagoDetalleTransaccion(String nci) {
//
//        PagosCentroPagosValidaDTO pago = null;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append(" SELECT caja_id,infrac_num, infrac_num_ctrl,pag_id,existe_pago,existe_detalle_pago, ")
//                .append("existe_pag_transaccion,existe_infrac_transaccion ")
//                .append("FROM V_PAGOS_DETALLE_TRANSAC ")
//                .append("WHERE infrac_num_ctrl = ?");
//
//        try {
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, nci);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                pago = new PagosCentroPagosValidaDTO();
//                pago.setCajaId(rs.getString("caja_id"));
//                pago.setInfracNum(rs.getString("infrac_num"));
//                pago.setNci(rs.getString("infrac_num_ctrl"));
//                pago.setPagoId(rs.getString("pag_id"));
//                pago.setExistePago(rs.getBoolean("existe_pago"));
//                pago.setExisteDetallePagos(rs.getBoolean("existe_detalle_pago"));
//                pago.setExistePagoTransaccion(rs.getBoolean("existe_pag_transaccion"));
//                pago.setExisteInfracTransaccion(rs.getBoolean("existe_infrac_transaccion"));
//
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return pago;
//    }

//    protected boolean existeDetalleCargos(String pagId, String cajaId) {
//
//        boolean existe = true;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT count(*) as existe FROM detalle_cargos ")
//                .append("WHERE pag_id=? AND caja_id=?");
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, pagId);
//            pstBusqueda.setString(2, cajaId);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                existe = rs.getBoolean("existe");
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return existe;
//    }
//
//    protected boolean existeDetallePagos(String pagId, String cajaId) {
//
//        boolean existe = true;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT count(*) as existe FROM detalle_pago ")
//                .append("WHERE pag_id=? AND caja_id=?");
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, pagId);
//            pstBusqueda.setString(2, cajaId);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                existe = rs.getBoolean("existe");
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return existe;
//    }

//    protected boolean existeInfraccion(String nci) {
//
//        boolean existe = true;
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT count(*) as existe FROM infracciones ")
//                .append("WHERE infrac_num_ctrl=?");
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            pstBusqueda.setString(1, nci);
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                existe = rs.getBoolean("existe");
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return existe;
//    }

//    protected String getCtroPagosUltimaConsulta() {
//        String fecha = "";
//        Connection conexion = null;
//        PreparedStatement pstBusqueda = null;
//        ResultSet rs = null;
//        StringBuffer query = new StringBuffer();
//        query.append("SELECT to_char(fecha_ultima_consulta,'dd/mm/yyyy HH24:MI') fecha")
//                .append(" FROM CTRO_PAGOS_ULTIMA_CONSULTA ");
//        try {
//
//            conexion = utileriaBD.Conectar();
//            pstBusqueda = conexion.prepareStatement(query.toString());
//            rs = pstBusqueda.executeQuery();
//            while (rs.next()) {
//                fecha = rs.getString("fecha");
//            }
//        } catch (Exception exception) {
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class
//                    + "]:"
//                    + exception.getMessage());
//            exception.printStackTrace();
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarPreparedStatement(pstBusqueda);
//            utileriaBD.cerrarConexion(conexion);
//        }
//        return fecha;
//    }

//    protected BeanReceptor getBeanReceptor() {
//
//        BeanReceptor parametros = null;
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        String query = "SELECT * FROM "
//                + "V_CONSULTA_PARAMETROS_TARJETA "
//                + "WHERE PARAM_STATUS=1";
//        try {
//            con = utileriaBD.Conectar();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                parametros = new BeanReceptor();
//                parametros.setCompany(rs.getString("param_bs_company"));
//                parametros.setBranch(rs.getString("param_bs_branch"));
//                parametros.setCountry(rs.getString("param_bs_country"));
//                parametros.setUser(rs.getString("param_bs_user"));
//                parametros.setPassword(rs.getString("param_bs_pwd"));
//                parametros.setOperationType(rs.getString("param_tx_operationtype"));
//                parametros.setURL(rs.getString("param_URL"));
//                parametros.setUsrTransaction(rs.getString("param_usr_transaction"));
//
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            System.out.println("ERROR EN LA CLASE ["
//                    + ConsultaCentroPagosMyBatisDAO.class + "]:"
//                    + exception.getMessage());
//        } finally {
//            utileriaBD.cerrarResultStatement(rs);
//            utileriaBD.cerrarStatement(stmt);
//            utileriaBD.cerrarConexion(con);
//        }
//        return parametros;
//    }
    
//    protected Utileriabd getUtileriaBD() {
//        return utileriaBD;
//    }
//
//    protected void setUtileriaBD(Utileriabd utileriaBD) {
//        this.utileriaBD = utileriaBD;
//    }

//}
