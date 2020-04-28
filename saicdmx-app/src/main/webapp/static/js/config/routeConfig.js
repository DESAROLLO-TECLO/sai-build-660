angular.module('siidfApp').config(function($routeProvider, $locationProvider) {
	
	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/login", {
		templateUrl : "login.html",
		controller: "loginController"
	});
	
	$routeProvider.when("/error", {
		templateUrl : "views/error.html",
	});
	
	$routeProvider.when("/index",{
		templateUrl : "views/index.html",
	});
	
	$routeProvider.when("/accesoNegado", {
		templateUrl : "views/accesoNegado.html",
	});
	
	
	/*INICIA CONTROL DE SUMINISTROS */
	
	$routeProvider.when("/controlAlmacen", {
		templateUrl : "views/controlsuministros/controlalmacen.html",
		controller: "controlAlmacenController"
	});
	
	$routeProvider.when("/suministroAreas", {
		templateUrl : "views/controlsuministros/suministroareas.html",
		controller: "suministroAreasController"
	});
	
	$routeProvider.when("/devoluciones", {
		templateUrl : "views/controlsuministros/devoluciones.html",
		controller: "devolucionesController"
	});
	
	$routeProvider.when("/catalogoAreas", {
		templateUrl : "views/controlsuministros/catalogoareas.html",
		controller: "catalogoAreasController"
	});
	
	$routeProvider.when("/modificarAuxiliar/:id", {
		templateUrl : "views/controlsuministros/modificarauxiliar.html",
		controller: "modificarAuxiliarController",
		resolve: {
			auxiliar: function (controlSuministrosService, $route) {
				return controlSuministrosService.buscarAuxiliarPorId($route.current.params.id);
			}
        }
    });
	
	$routeProvider.when("/altaAuxiliar/:id/:id2", {
		templateUrl : "views/controlsuministros/altaauxiliar.html",
		controller: "altaAuxiliarController",
		resolve: {
			buscaraux: function (controlSuministrosService, $route) {
				return controlSuministrosService.buscarAuxiliarAreaRegion($route.current.params.id,$route.current.params.id2);
			}
        }
    });
	
	/*INICIA PARTE INFORMATIVO*/
	
	/*DOCUMENTOS*/
	$routeProvider.when("/busquedaDocumentos", {
		templateUrl : "views/parteInformativo/busquedaDocumentos.html",
		controller: "busquedaDocumentosController"
	});
	
	$routeProvider.when("/nuevoDocumento",{
		templateUrl : "views/parteInformativo/nuevoDocumento.html",
		controller: "nuevoDocumentoController"
	});
	
	$routeProvider.when("/busquedaDocumentos/modificacionDocumentos/:id", {
		templateUrl : "views/parteInformativo/modificacionDocumentos.html",
		controller: "modificacionDocumentosController",
		resolve: {
			documento: function (parteInformativoService, $route) {
				return parteInformativoService.buscarDocumentoPorId($route.current.params.id);
			}
        }
    });
	
	/*BOLETAS*/
	$routeProvider.when("/busquedaBoletas", {
		templateUrl : "views/parteInformativo/busquedaBoletas.html",
		controller: "busquedaBoletasController"
	});
	
	$routeProvider.when("/nuevaBoleta",{
		templateUrl : "views/parteInformativo/nuevaBoleta.html",
		controller: "nuevaBoletaController"
	});
	
	$routeProvider.when("/busquedaBoletas/modificacionBoletas/:id", {
		templateUrl : "views/parteInformativo/modificacionBoletas.html",
		controller: "modificacionBoletasController",
		resolve: {
			boleta: function (parteInformativoService, $route) {
				return parteInformativoService.buscarBoletaPorId($route.current.params.id);
			}
        }
    });
	
	/*TERMINA PARTE INFORMATIVO*/
	
	/************INFRACCIONES************/
	
	$routeProvider.when("/altaInfraccion",{
		templateUrl : "views/infraccion/altaInfraccion.html",
		//controller : "altaInfraccionController"
		controller : "altaInfraccionControllerOptimizado"
	});
	
	$routeProvider.when("/modificaInfracciones",{
		templateUrl : "views/infraccion/modificaInfraccionesBusqueda.html",
		controller : "modificaInfraccionesBusquedaController"
	});
	
	$routeProvider.when("/modificaEnDepositoInfracciones",{
		templateUrl : "views/infraccion/modificaEnDepositoBusqueda.html",
		controller : "modificaEnDepositoBusquedaController"
	});
	
	$routeProvider.when("/marcadoInfracciones",{
		templateUrl : "views/infraccion/marcadoInfraccionesBusqueda.html",
		controller : "marcadoInfraccionesBusquedaController"
	});
	
	$routeProvider.when("/liberaPreimpresas",{
		templateUrl : "views/infraccion/liberaPreimpresaInfraccion.html",
		controller : "liberaPreimpresaInfraccionController"
	});
	
	$routeProvider.when("/consultaInfraccionDetalle/:id",{
		templateUrl : "views/infraccion/busquedaDetalleInfraccion.html",
		controller : "busquedaDetalleInfraccionController",
		resolve: {
			infraccion : function(infraccionService, $route){
				return infraccionService.buscarInfraccionDetallada($route.current.params.id);
			}
		}
	});
	
	

	
	$routeProvider.when("/busquedaInfracciones", { //MENU_ID : 10
		templateUrl : "views/infraccion/busquedaInfraccion.html",
		controller: "busquedaInfraccionController", 
		resolve : {
				validaExpediente : function(){return false;}
        }
	});
	
	$routeProvider.when("/validaExpediente", { //MENU_ID : 14
		templateUrl : "views/infraccion/busquedaInfraccion.html",
		controller: "busquedaInfraccionController", 
		resolve : {
				validaExpediente : function(){return true;}
        }
	});
	
	$routeProvider.when("/consultaExpediente/:id/:validaExpediente",{
		templateUrl : "views/infraccion/expedienteInfraccion.html",
		controller : "expedienteInfraccionController",
		resolve: {
			infraccion : function(infraccionService, $route){			
				return infraccionService.buscarInfraccionDetallada($route.current.params.id);;
			},
			validaExpediente : function($route){ 
				return $route.current.params.validaExpediente;
			}
		}
	});
	
	$routeProvider.when("/modificaInfraccion/:id",{
		templateUrl : "views/infraccion/modificaInfraccion.html",
		controller : "modificaInfraccionController",
		resolve: {
			infraccion : function(infraccionService, $route){
				return infraccionService.buscarInfraccionDetallada($route.current.params.id);
			}
		}
	});
	
	$routeProvider.when("/modificaEnDepositoInfraccion/:id",{
		templateUrl : "views/infraccion/modificaEnDeposito.html",
		controller : "modificaEnDepositoController",
		resolve: {
			infraccion : function(infraccionService, $route){
				return infraccionService.buscarInfraccionDetallada($route.current.params.id);
			}
		}
	});
	
	$routeProvider.when("/marcadoInfraccion/:id",{
		templateUrl : "views/infraccion/marcadoInfraccion.html",
		controller : "marcadoInfraccionController",
		resolve: {
			infraccion : function(infraccionService, $route){
				return infraccionService.buscarInfraccionDetallada($route.current.params.id);
			}
		}
	});
	
	$routeProvider.when("/infraccion_foto/:nombre", {
		templateUrl : "views/infraccion/infraccion_foto.html",
		controller : "infraccion_foto",
		resolve: {
			foto : function(expedienteInfraccionService, $route){
				return expedienteInfraccionService.consultaFotoPorNombre($route.current.params.nombre);
			}
		}
	});
	
	/************INFRACCIONES************/
	
	/************CAJAS SIN CORTES************/
	
	$routeProvider.when("/cajaSinCorteHistorico",{
		templateUrl : "views/cajasincorte/consulta_historico.html",
		controller : "consultaHistoricoController"
	});
	
	$routeProvider.when("/cajaSinCorteActual",{
		templateUrl : "views/cajasincorte/consulta_actual.html",
		controller : "consultaActualController"
	});
	
	/************CORTE DE CAJA************/
	
	$routeProvider.when("/corteCajaNuevo",{
		templateUrl : "views/cortedecaja/corteCajaNuevo.html",
		controller : "corteCajaNuevoController"
	});
	
	$routeProvider.when("/corteCajaConsulta",{
		templateUrl : "views/cortedecaja/corteCajaConsulta.html",
		controller : "corteCajaConsultaController"
	});
	
	
	/************IMPUGNACION************/
	$routeProvider.when("/consultaImpugnacion", {
		templateUrl : "views/impugnacion/consulta_impugnacion.html",
		controller: "impugnaController"
	}); 
		
	$routeProvider.when("/detalleImpugnacion/:id", {
		templateUrl : "views/impugnacion/detalle_impugnacion.html",
		controller: "detalle_impugnacionController"
		
	}); 
	
	$routeProvider.when("/modificaImpugnacion/:id", {
		templateUrl : "views/impugnacion/modifica_impugnacion.html",
		controller: "modifica_ImpugnacionController"
       	
      }); 
	
	$routeProvider.when("/modifica_InfraccImpugnacion/:id", {
		templateUrl : "views/impugnacion/modifica_infraccion_impugnacion.html",
		controller: "modifica_InfraccImpugnacionController"
		
	}); 
	 
	$routeProvider.when("/altaImpugnacion", {
		templateUrl : "views/impugnacion/nueva_impugnacion.html",
		controller: "altaImpugnacionController"
		
	}); 
	
	$routeProvider.when("/consultaCanceladas", {
		templateUrl : "views/impugnacion/consulta_canceladas.html",
		controller: "canceladasImpugnaController"
	}); 
	
	$routeProvider.when("/detalleCanceladas/:infraccion", {
		templateUrl : "views/impugnacion/detalle_consulta_canceladas.html",
		controller: "canceladas_DetalleImpugnaController"
		
	});
	
	$routeProvider.when("/consultaExpedienteImp/:id/:validaExpediente",{
		templateUrl : "views/impugnacion/expedienteImpugnacion.html",
		controller : "expedienteImpugnacionController",
		resolve: {
			infraccion : function($route){	
				return $route.current.params.id
				},
			validaExpediente : function($route){ 
				return $route.current.params.validaExpediente;
			}
		}
	});
	
	

      /************************/	
     /*** SEMOVI ***/
	
	$routeProvider.when("/generaCargaArchivo",{
		templateUrl : "views/semovi/generaCargaArchivo.html",
		controller : "semoviArchivosController"
	});
	
	$routeProvider.when("/consultaArchivos",{
		templateUrl : "views/semovi/semoviConsulta.html",
		controller : "semoviConsultaController"
	});
	
	/***************************************************/

	  /************************/	
    /*** AUTOS ROBADOS ***/
	
	$routeProvider.when("/vehRobadosConsulta",{
		templateUrl : "views/autosRobados/vehRobadosConsulta.html",
		controller : "vehRobadosConsultaController"
	});
	
	$routeProvider.when("/vehRobadosAlta",{
		templateUrl : "views/autosRobados/vehRobadosAlta.html",
		controller : "vehRobadosAltaController"
	});
	
	$routeProvider.when("/detalleRoboVehiculo/:id/:opcion",{
		templateUrl : "views/autosRobados/detalleRoboVehiculo.html",
		controller : "detalleVehiculoRobadoController",
		resolve: {
			idRobo : function($route){	
				return $route.current.params.id
				},
			opcion : function($route){ 
				return $route.current.params.opcion;
			}
		}
	});
	
    /***** LOGS *****/	

	$routeProvider.when("/administracionLogs",{
		templateUrl : "views/logs/administracionLogs.html",
		controller : "adminLogsController"
	});
	
	$routeProvider.when("/administracionLogs/administracionModificaLogs/:id",{
		templateUrl : "views/logs/administracionModificaLogs.html",
		controller : "adminModificaLogsController"
	});
	
	$routeProvider.when("/consultaLogs",{
		templateUrl : "views/logs/consultaLogs.html",
		controller : "consultaLogsController"
	});
	
	/**** CERTIFICADOS SAT ***/
	
	$routeProvider.when("/nuevoCertificadoSAT",{
		templateUrl : "views/certificados/nuevoCertificado.html",
		controller : "altaCertificadoControlller",
		resolve: {
				busqueda : function($route){
					return null
				}
		}
	        
	});
	
	$routeProvider.when("/nuevoCertificadoSAT/:busqueda", {
		templateUrl : "views/certificados/nuevoCertificado.html",
		controller : "altaCertificadoControlller",
		resolve: {
			busqueda : function($route){
				return $route.current.params.busqueda
			}
        }
    });
	
	$routeProvider.when("/consultaCertificadoSAT",{
		templateUrl : "views/certificados/consultaCertificado.html",
		controller : "consultaCertificadoControlller"
			
	});
	
	$routeProvider.when("/cargaCertificadoSAT/:id",{
		templateUrl : "views/certificados/cargaCertificado.html",
		controller : "cargaCertificadoController"
	});
	
//	**ADMINISTRACION DE PLACAS**//
	
	
	$routeProvider.when("/administrarPlacas",{
		templateUrl : "views/placas/administrarPlacas.html",
		controller : "administracionPlacasController"
	});
	
	$routeProvider.when("/consultarPlacas",{
		templateUrl : "views/placas/consultaPlacas.html",
		controller : "administracionPlacasController"
	});
	
	
		
	//--**Admionistracion Placas controller***-----------------------------///
	
	
	
	
	
	
	/******	ADMINISTRACIÓN ***/
	
	$routeProvider.when("/adminUsuarios",{
		templateUrl : "views/administracion/adminUsuariosConsulta.html",
		controller : "adminUsuariosConsultaController"
	});
	
	$routeProvider.when("/adminUsuarios/adminUsuariosModifica/:id",{
		templateUrl : "views/administracion/adminUsuariosModifica.html",
		controller : "adminUsuariosModificaController"
	});
		
	$routeProvider.when("/adminUsuarios/adminUsuariosAlta",{
		templateUrl : "views/administracion/adminUsuariosAlta.html",
		controller : "adminUsuariosAltaController"
	});
	
	$routeProvider.when("/adminUsuarios/adminUsuariosModificaRegion/:id",{
		templateUrl : "views/administracion/adminUsuariosModificaRegion.html",
		controller : "adminUsuariosModificaRegionController"
	});
	$routeProvider.when("/adminUsuarios/adminUsuariosModificaCaja/:id",{
		templateUrl : "views/administracion/adminUsuariosModificaCaja.html",
		controller : "adminUsuariosModificaCajaController"
	});
	$routeProvider.when("/adminUsuarios/adminUsuariosModificaDeposito/:id",{
		templateUrl : "views/administracion/adminUsuariosModificaDeposito.html",
		controller : "adminUsuariosModificaDepositoController"
	});
	
	$routeProvider.when("/soporteOperacion",{
		templateUrl : "views/administracion/soporteOperacion.html",
		controller : "soporteOperacionController"
	});

	$routeProvider.when("/adminPerfilesWeb",{
		templateUrl : "views/administracion/adminPerfilesWeb.html",
		controller : "perfilesWebController"
	});
	
	$routeProvider.when("/adminCajaRecaudacion",{
		templateUrl : "views/administracion/cajaRecaudacion.html",
		controller : "cajaRecaudacionController"
	});
	
	$routeProvider.when("/adminUsuariosModificaClave",{
		templateUrl : "views/administracion/adminUsuariosModificaClave.html",
		controller : "adminUsuariosModificaClaveController"
	});
	

	/***Administración de reporte***/
	$routeProvider.when("/reportes",{
		templateUrl : "views/administracion/adminReporte.html",
		controller : "admiReporteController"
	});
	
	
     /****** REPORTES ******/
	
	$routeProvider.when("/reporteConsulta",{
		templateUrl : "views/reportes/reporteConsulta.html",
		controller : "reporteConsultaController"
	});
	

	/**** ESTADISTICAS **/
	
//	Dashboard Reportes Foto-Multa
					
	$routeProvider.when("/reportesFotoMulta",{
		templateUrl : "views/estadisticas/reportesFotoMulta.html",
		controller : "reportesFotoMultaController"
	});
	
//	Dashboard Reportes Infracciones
	$routeProvider.when("/reportesInfracciones",{
		templateUrl : "views/estadisticas/reportesInfracciones.html",
		controller : "reportesInfraccionesController"
	});
//	Dashboard Reportes Infracciones Mapa
	$routeProvider.when("/mapaInfracciones",{
		templateUrl : "views/estadisticas/reportes.html",
		controller : "reportesMapaController"
	});
	/*******************RUTAS BLOQUEHO DE HAND HELD****************/

	$routeProvider.when("/consultaHandHeld", {
		templateUrl : "views/bloqueohh/consultaBloqueohh.html",
		controller: "consultaBloqueohhController"
	});
	
	$routeProvider.when("/desbloqueoHandHeld", {
		templateUrl : "views/bloqueohh/bloqueohh.html",
		controller: "bloqueohhController"
	});
	/*****************FIN RUTAS BLOQUEHO DE HAND HELD****************/

	/**************************RUTAS PAGOS***************************/

	$routeProvider.when("/pagoDigitalizacion", {
		templateUrl : "views/pagos/digitalizacionInfraccion.html",
		controller: "cargaExpedienteController"
	});
 
	$routeProvider.when("/pagoInfraccion", {
		templateUrl : "views/pagos/pagoInfraccion.html",
		controller: "pagosInfraccionController"
	});
	
	$routeProvider.when("/consultaInfraccion", {
		templateUrl : "views/pagos/consultaInfraccion.html",
		controller: "consultaInfraccionController"
	});
	
	$routeProvider.when("/pagoInfraccionActa", {
		templateUrl : "views/pagos/pagoInfraccionActa.html",
		controller: "pagosInfraccionActaController"
	});
	
	$routeProvider.when("/centroDePagos", {
		templateUrl : "views/pagos/centroPagos.html",
		controller: "centroPagosController"
	});
	
			/**************************RUTA EXPEDIENTE***************************/
	
	$routeProvider.when("/cargaExpediente/:id/:flagView", {
		templateUrl : "views/pagos/nuevoExpediente.html",
		controller: "cargaExpedienteController"
	});
	
	/**************************FIN RUTA EXPEDIENTE***************************/
	
/**************************RUTA TRANSACCIONES***************************/
	
	$routeProvider.when("/consultaTransacciones", {
		templateUrl : "views/pagos/consultaTransacciones.html",
		controller: "transaccionesController"
	});
	
	$routeProvider.when("/consultaTransaccionesForCancelacion", {
		templateUrl : "views/pagos/cancelacionTransacciones.html",
		controller: "cancelaTransaccionesController"
	});
	
	$routeProvider.when("/consultaTransaccionesCanceladas", {
		templateUrl : "views/pagos/consultaTransaccionesCanceladas.html",
		controller: "transaccionesCanceladasController"
	});
	
	
	/**************************FIN RUTA TRANSACCIONES***************************/
	 
	/***************************FIN RUTAS PAGOS***********************/

	
	/**********************INICIA GARANTIAS*****************************/

	
/**********************INICIA GARANTIAS*****************************/

	$routeProvider.when("/pagoInfraccion", {
		templateUrl : "views/pagos/pagoInfraccion.html",
		controller: "pagosInfraccionController"
	});
	
	 
	/***************************FIN RUTAS PAGOS***********************/

	
/**********************INICIA GARANTIAS*****************************/

	$routeProvider.when("/garantiaSinProcesar",{
		templateUrl : "views/garantia/garantiaSinProcesar.html",
		controller: "garantiaSinProcesarController"
	});
	
	$routeProvider.when("/garantiaRecepcion",{
		templateUrl : "views/garantia/garantiaRecepcion.html",
		controller: "garantiaRecepcionController"
	});
	
	$routeProvider.when("/garantiaPago",{
		templateUrl : "views/garantia/garantiaPago.html",
		controller: "garantiaPagoController"
	});

	$routeProvider.when("/garantiaEntrega",{
		templateUrl : "views/garantia/garantiaEntrega.html",
		controller: "garantiaEntregaController",
		resolve: {
			infraccion : function($route){
				return null
			}
        }
	});
	
	$routeProvider.when("/garantiaEntrega/:infraccion", {
		templateUrl : "views/garantia/garantiaEntrega.html",
		controller: "garantiaEntregaController",
		resolve: {
			infraccion : function($route){
				return $route.current.params.infraccion
			}
        }
    });
	
	$routeProvider.when("/garantiaConsulta",{
		templateUrl : "views/garantia/garantiaConsulta.html",
		controller: "garantiaConsultaController"
	});
	
	$routeProvider.when("/garantiaReporte",{
		templateUrl : "views/garantia/garantiaReporte.html",
		controller: "garantiaReporteController"
	});
	
	$routeProvider.when("/garantiaRecepcionMasiva",{
		templateUrl : "views/garantia/garantiaRecepcionMasiva.html",
		controller: "garantiaRecepcionMasivaController"
	});
	
	/**********************TERMINA GARANTIAS*****************************/
	
	/******************** Radares ***********************/
	$routeProvider.when("/complementarRadares", {
		templateUrl : "views/radares/complementarRadares.html",
		controller: "complementarRadaresController",
		resolve: {
			validaArchivoProceso: function (radaresService, $route, $location) {
				radaresService.buscaArchivoEnProceso().success(function(data){
					if(data){
						$location.path("/complementarRadares/complementacionRadaresProceso");
					}				
				}).error(function(data){
					$scope.error = data;
					$scope.sucess = false;
					$scope.MostrarTabla= false;
				});;
				
			}
        }
	});
	
	$routeProvider.when("/complementarRadares/complementacionRadaresProceso", {
		templateUrl : "views/radares/complementacionRadaresProceso.html",
		controller: "complementacionRadaresProcesoController",
		resolve: {
			isLoteFM : function($route){
				return null
			}
        }
	});
	
	$routeProvider.when("/radarArchivoProcesados", {
		templateUrl : "views/radares/radarArchivosProcesados.html",
		controller: "radarArchivosComplementadosController",
		resolve: {
			origen : function($route){
				return 0;
			}
		}
	});
	
	$routeProvider.when("/radarCatalogos", {
		templateUrl : "views/radares/radarCatalogos.html",
		controller: "radarCatalogosController"
	});
	
	
	/******************** Dispositivos Fijos ***********************/
	/*INICIA DISPOSITIVOS FIJOS*/
	$routeProvider.when("/crearLotesFM", {
		templateUrl : "views/fm/crearLotes.html",
		controller: "creaLotesFMController"
	});
	
	$routeProvider.when("/complementaDispFijos", {
//		templateUrl : "views/fm/complementacionFMDispFijos.html",
//		controller: "complementarFMController",
		templateUrl : "views/radares/complementacionRadaresProceso.html",
		controller: "complementacionRadaresProcesoController",
		resolve: {
			isLoteFM : function($route){
				return true
			}
	    }
	});
	
	$routeProvider.when("/complementaDispFijos:isLoteFM", {
		templateUrl : "views/radares/complementacionRadaresProceso.html",
		controller: "complementacionRadaresProcesoController",
		resolve: {
			isLoteFM : function($route){
				return $route.current.params.isLoteFM
			}
	    }
	});
	
	$routeProvider.when("/fmConsultaDetecciones", {
		templateUrl : "views/fm/fmConsultaDetecciones.html",
		controller: "consultaDeteccionesFMController"
	});
	
	$routeProvider.when("/fmConsultaDeteccionesSP", {
		templateUrl : "views/fm/fmConsultaDeteccionesSP.html",
		controller: "consultaDeteccionesSPFMController"
	});
	
	$routeProvider.when("/fmCancelacionDetecciones", {
		templateUrl : "views/fm/fmCancelacionDetecciones.html",
		controller: "fmCancelacionDeteccionController"
	});
	
	$routeProvider.when("/consultaLotesFMDispFijos", {
//		templateUrl : "views/fm/consultaLotesFMDispFijos.html",
//		controller: "consultaLotesFMController"
		templateUrl : "views/radares/radarArchivosProcesados.html",
		controller: "radarArchivosComplementadosController",
		resolve: {
			origen : function($route){
				return 1;
			}
		}
	});
	
	$routeProvider.when("/estadisticasDetecciones", {
		templateUrl : "views/fm/estadisticasDeteccionesFMDispFijos.html",
		controller: "estadisticasDeteccionesFMController"
	});
	
	
	$routeProvider.when("/consultaArchivoOrigen", {
		templateUrl : "views/fm/consultaArchivoOrigen.html",
		controller: "consultaArchivoOrigenController",
				resolve: {
				opcion : function($route){
						return 1;
					},
				busqueda : function($route){
					return false;
				}
				,
				switchRangoFecha1: function($route){			
					return 0 ;
				},
				periodoFecha1: function($route){			
					return 0;
				},
				startDate1: function($route){			
					return "";
				},
				endDate1: function($route){			
					return "";
				},
				estatusProceso1: function($route){			
					return "";
				},
				opcTipoArchivo1: function($route){			
					return 0;
				}
	        }
	});
	
	
	$routeProvider.when("/consultaArchivoOrigen/:opcion/:busqueda/:switchRangoFecha/:periodoFecha/:startDate/:endDate/:estatusProceso/:opcTipoArchivo",{
		templateUrl : "views/fm/consultaArchivoOrigen.html",
		controller: "consultaArchivoOrigenController",
				resolve: {
					opcion : function($route){
						return $route.current.params.opcion;
						},
				
				busqueda : function($route){
					return $route.current.params.busqueda;
					}
	
					,
					switchRangoFecha1: function($route){			
						return $route.current.params.switchRangoFecha;
					},
					periodoFecha1: function($route){			
						return $route.current.params.periodoFecha;
					},
					startDate1: function($route){			
						return $route.current.params.startDate;
					},
					endDate1: function($route){			
						return $route.current.params.endDate;
					},
					estatusProceso1: function($route){			
						return $route.current.params.estatusProceso;
					},
					opcTipoArchivo1: function($route){			
						return $route.current.params.opcTipoArchivo;
					}
	
	        }
	});
	
	
	
	$routeProvider.when("/consultaArchivoDeteccion/:id_Archivo/:opcion/:switchRangoFecha/:periodoFecha/:startDate/:endDate/:estatusProceso/:opcTipoArchivo",{
		templateUrl : "views/fm/consultaAchivoDeteccion.html",
		controller : "consultaArchivoDeteccionController",
		resolve: {
			deteccion : function(radarArchivoProcesadosService, $route){			
				return radarArchivoProcesadosService.buscarArchivoDeteccion($route.current.params.id_Archivo);
			},
			opcion: function($route){			
				return $route.current.params.opcion;
			},
			switchRangoFecha1: function($route){			
				return $route.current.params.switchRangoFecha;
			},
			periodoFecha1: function($route){			
				return $route.current.params.periodoFecha;
			},
			startDate1: function($route){			
				return $route.current.params.startDate;
			},
			endDate1: function($route){			
				return $route.current.params.endDate;
			},
			estatusProceso1: function($route){			
				return $route.current.params.estatusProceso;
			},
			opcTipoArchivo1: function($route){			
				return $route.current.params.opcTipoArchivo;
			}
			
			
		}
	});
	
	
	
	
	
	/*TERMINA DISPOSITIVOS FIJOS*/
	
	
	
	/*  INICIA REMISION A DEPOSITO  */
	
	$routeProvider.when("/conInfraccion", {
		templateUrl : "views/remisionadeposito/coninfraccion.html",
		controller: "conInfraccionController"		
	});
	
	$routeProvider.when("/updIngresoDeposito/:id", {
		templateUrl : "views/remisionadeposito/updingresodeposito.html",
		controller: "updIngresoDepositoController",
		resolve: {
			valoresupd: function (remisionaDepositoService, $route) {
				return remisionaDepositoService.buscarUpdPorId($route.current.params.id);
			}
        }
    });

	$routeProvider.when("/sinInfraccion", {
		templateUrl : "views/remisionadeposito/sininfraccion.html",
		controller: "sinInfraccionController"
	});
	
	$routeProvider.when("/consultaRemision", {
		templateUrl : "views/remisionadeposito/consultaremision.html",
		controller: "consultaRemisionController"
	});
	
	$routeProvider.when("/salidasVehiculoTraslado", {
		templateUrl : "views/remisionadeposito/trasladoEnCurso.html",
		controller: "trasladoEnCursoController"
    });
	
	$routeProvider.when("/ingresoTrasladoVehiculo/:id", {
		templateUrl : "views/remisionadeposito/ingresoTrasladoVehiculo.html",
		controller: "ingresoTrasladoVehiculoController"
    });	

	/*INICIA LINEA DE CAPTURA*/
	
	
	$routeProvider.when("/reasignacionLC", {
		templateUrl : "views/lineadecaptura/reasignacionLC.html",
		controller: "reasignacionLCController"
	});
	
	$routeProvider.when("/consultaLC", {
		templateUrl : "views/lineadecaptura/consultaLC.html",
		controller: "consultaLCController"
	});
	
	$routeProvider.when("/reasignacionLCMasiva", {
		templateUrl : "views/lineadecaptura/reasignacionLCMasiva.html",
		controller: "reasignacionLCMasivaController"
	});
	
	$routeProvider.when("/consultaLCMasiva", {
		templateUrl : "views/lineadecaptura/consultaLCMasiva.html",
		controller: "consultaLCMasivaController"
	});

	/*TERMINA LINEA DE CAPTURA*/
	
	
	/*INICIA FOTOMULTAS*/
	$routeProvider.when("/deteccionesSinProcesar", {
		templateUrl : "views/fotomulta/deteccionesSinProcesar.html",
		controller: "deteccionesSinProcesarController"
	});
	
	$routeProvider.when("/consultaDetecciones", {
		templateUrl : "views/fotomulta/consultaDetecciones.html",
		controller: "consultaDeteccionesController"
	});
	
	$routeProvider.when("/crearLotes", {
		templateUrl : "views/fotomulta/crearLotes.html",
		controller: "crearLotesController"
	});
	
	$routeProvider.when("/consultaLotes", {
		templateUrl : "views/fotomulta/consultaLotes.html",
		controller: "consultaLotesController"
	});
	

	$routeProvider.when("/estadisticas", {
		templateUrl : "views/fotomulta/estadisticas.html",
		controller: "estadisticasController"
	});
	
	$routeProvider.when("/marcadoDetecciones", {
		templateUrl : "views/fotomulta/marcadoDetecciones.html",
		controller: "marcadoDeteccionesController"
	});
	
	$routeProvider.when("/consultaFotomultasCancel", {
		templateUrl : "views/fotomulta/consultaCanceladas.html",
		controller: "deteccionesCanceladasController"
	});
	
	
	
	/*TERMINA FOTOMULTAS*/
	
	$routeProvider.when("/catalogos", {
		templateUrl : "views/catalogos/adminCatalogos.html",
		controller: "crudController"
	});
	
	$routeProvider.when("/catalogos/articulosInfraccionesFinanzas/:id", {
		templateUrl : "views/catalogos/crudHistoricoArticulo.html",
		controller: "articuloHistoricoController",
		resolve: {
			busquedaHistorico: function (catalogoService, $route) {
				return catalogoService.buscarArticulosInfraccionesFinanzasPorArticulo($route.current.params.id);
			},
			artCons: function($route) {
				return $route.current.params.id;
			}
        }
    });
	
	$routeProvider.when("/historicoDetalle/:id", {
		templateUrl : "views/catalogos/historicoDetalle.html",
		controller: "detalleHistoricoController",
		resolve: {
			busquedaDetalleHistorico: function (catalogoService, $route) {
				return catalogoService.buscarArticulosInfraccionesFinanzasPorId($route.current.params.id);
			}
        }
    });
	
	$routeProvider.when("/catalogos/modificaHistorico/:id", {
		templateUrl : "views/catalogos/modificaHistorico.html",
		controller: "detalleHistoricoController",
		resolve: {
			busquedaDetalleHistorico: function (catalogoService, $route) {
				return catalogoService.buscarArticulosInfraccionesFinanzasPorId($route.current.params.id);
			}
        }
    });	
	
	$routeProvider.when("/catalogos/altaArticulo", {
		templateUrl : "views/catalogos/altaArticulo.html",
		controller: "altaArticuloController"
    });
	
	$routeProvider.when("/catalogos/altaHistoricoArticulo/:id", {
		templateUrl : "views/catalogos/altaHistoricoArticulo.html",
		controller: "altaHistoricoArticuloController",
		resolve: {
			artCons: function($route) {
				return $route.current.params.id;
			}
        }
    });
	
	$routeProvider.when("/catalogos/articuloDetalle/:id", {
		templateUrl : "views/catalogos/articuloDetalle.html",
		controller: "detalleArticuloController",
		resolve: {
			busquedaDetalleArticulo: function (catalogoService, $route) {
				return catalogoService.buscarArticuloPorId($route.current.params.id);
			}
        }
    });
	
	/*INICIA LIBERACION VEHICULO*/
	$routeProvider.when("/liberacionVehiculo", {
		templateUrl : "views/liberacionvehiculo/liberacionVehiculo.html",
		controller: "liberacionVehiculoController"
	});
	
	$routeProvider.when("/liberacionVehiculoModificacion/:infracNum/:paginaOrigen", {
		templateUrl : "views/liberacionvehiculo/liberacionVehiculoModificacion.html",
		controller: "liberacionVehiculoModificacionController",
		resolve: {
			vehiculo: function (liberacionVehiculoService, $route) {
				return liberacionVehiculoService.buscarDetalleDeposito($route.current.params.infracNum);
			}
        }
    });
	/*TERMINA LIBERACION VEHICULO*/
	
	$routeProvider.when("/operacionExt", {
		templateUrl : "views/administracion/adminOperacionExt.html",
		controller: "operacionExtController"
	});
	
	$routeProvider.when("/localizacionGPS", {
		templateUrl : "views/administracion/localizacionGPS.html"
	});

/*INICIA SALIDAS*/
	
	/*Sub modulo salida de vehiculos consulta*/
	$routeProvider.when("/salidasVehiculoBusqueda", {
		templateUrl : "views/salidas/salidaVehiculoBusqueda.html",
		controller: "salidaBusquedaController"
	});
	
	$routeProvider.when("/altaSalidaVehiculo/:id", {
		templateUrl : "views/salidas/altaSalida.html",
		controller: "altaSalidaVehiculoController"
    });	
	
	$routeProvider.when("/salidasVehiculoConsulta", {
		templateUrl : "views/salidas/consultaSalidas.html",
		controller: "consultaSalidasController"
    });
	
	
	$routeProvider.when("/salidaVehiculoCatalogo", {
		templateUrl : "views/salidas/catalogoSalidas.html",
		controller: "catalogoSalidasController"
    });

	
//	Componentes Web
	$routeProvider.when("/componentesWeb",{
		templateUrl : "views/administracion/resources/pluginsWeb.html",
		controller : "pluginsWebController"
	});
	
// Reporteador Dinamico
//	Componentes Web
	$routeProvider.when("/ConsultaReportes",{
		templateUrl : "views/reportes/consultaReportes.html",
		controller : "consultaReportesController"
			
	});
	
	/**
	 * EMPIEZA EL LOS PATH DEL REQUERIMIENTO DE REPORTES
	 * */
	$routeProvider.when("/asignacionReportes",{
		templateUrl : "views/reportes/asignarReportes.html",
		controller : "asignarReportesController",
		resolve :{
			dataRepPerfiles: function (reporteService) {
				return reporteService.getDataPefilesReportes();
			}
		}
	});	
	
	$routeProvider.when("/dinamicReporte",{
		templateUrl : "views/reportes/dinamicReporte.html",
		controller : "dinamicReporteController"
	});
	
	$routeProvider.when("/formBusqueda/:idReporte", {
		templateUrl : "views/reportes/formBusqueda.html",
		controller: "formBusquedaController",
		resolve: {
			reporte: function (reporteService, $route) {
				return reporteService.getReporte($route.current.params.idReporte);
			}
        }
    });
	
	$routeProvider.when("/infraccionesEmpleados/:idReporte", {
		templateUrl : "views/reportes/infraccionesEmpleados.html",
		controller: "InfraccionesEmpleadosController",
		resolve: {
			reporte: function (reporteService, $route) {
				reporteService.getReporte($route.current.params.idReporte);
			}
        }
    });
	
	/**
	 * TERMINA EL LOS PATH DEL REQUERIMIENTO DE REPORTES
	 * */

	/*$routeProvider.when("/asignacionReportes",{
		templateUrl : "views/reportes/asignarReportes.html",
		controller : "asignarReportesController",
		resolve :{
			dataRepPerfiles: function (reporteService) {
				return reporteService.getDataPefilesReportes();
			}
		}
	});	
	
	$routeProvider.when("/dinamicReporte",{
		templateUrl : "views/reportes/dinamicReporte.html",
		controller : "dinamicReporteController"
	});	*/
	
	//INICIA ATENCION  CIUDADANA
	$routeProvider.when("/altaTramite", {
		templateUrl : "views/atencionCiudadana/altaTramite.html",
		controller: "altaTramiteController"
	});
	
	$routeProvider.when("/consultaTramite", {
		templateUrl : "views/atencionCiudadana/consultaTramite.html",
		controller: "consultaTramiteController",
		resolve:
			{
			deDetalle:function($route){
				return false
			},
			consultaAnteriorVO:function($route){
				return consultaAnteriorVO={}
			}
	    }
		
	});
	
	$routeProvider.when("/consultaTramite/:deDetalle/:busquedaAnterior", {
		templateUrl : "views/atencionCiudadana/consultaTramite.html",
		controller: "consultaTramiteController",
		resolve:
			{
			deDetalle:function($route){
				return $route.current.params.deDetalle
			},
			
			consultaAnteriorVO:function($route){
				return $route.current.params.busquedaAnterior
			}
	    }
	});
	
	

	$routeProvider.when("/modificaTramiteBusqueda", {
		templateUrl : "views/atencionCiudadana/modificaTramiteBusqueda.html",
		controller: "modificaTramiteBusquedaController",
		resolve: {
			opcion : function($route){
				return null;
			}
        }
	});
	
	$routeProvider.when("/modificaTramiteBusqueda/:opcion", {
		templateUrl : "views/atencionCiudadana/modificaTramiteBusqueda.html",
		controller: "modificaTramiteBusquedaController",
		resolve: {
			opcion : function($route){
				return $route.current.params.opcion
			}
        }
	});
	
	$routeProvider.when("/modificaTramite/:folio", {
		templateUrl : "views/atencionCiudadana/modificaTramite.html",
		controller: "modificaTramiteController",
		resolve: {
			tramiteATCiudadana : function(atencionCiudadanaService, $route){
				return atencionCiudadanaService.busquedaTramitePorFolio($route.current.params.folio, 2);
			}
		}
	});
	
	$routeProvider.when("/detalleTramite/:folio/:busquedaAnterior", {
		templateUrl : "views/atencionCiudadana/detalleTramite.html",
		controller: "detalleTramiteController",
		resolve: {
			tramiteATCiudadana : function(atencionCiudadanaService, $route){
				return atencionCiudadanaService.busquedaTramitePorFolio($route.current.params.folio, 2);
			},
			busquedaAnteriorVO : function($route){
				return $route.current.params.busquedaAnterior;
			}
		}
	});
	
	$routeProvider.when("/seguimientoTramite", {
		templateUrl : "views/atencionCiudadana/seguimientoTramite.html",
		controller: "seguimientoTramiteController"
	});

	/**
	 * EMPIEZA EVALUACIONES
	 * */
	$routeProvider.when("/evaluaciones", {
        templateUrl: "views/evaluaciones/evaluaciones.html",
        controller: "evaluacionesController"
    });

    $routeProvider.when("/usuariosEvaluacion/:idEvaluacion", {
        templateUrl: "views/evaluaciones/evaluacionesUsuarios.html",
        controller: "evaluacionesUsuariosController",
        resolve: {
            idEvaluacion: function($route) { return $route.current.params.idEvaluacion; }
        }
    });

	$routeProvider.otherwise({redirectTo: "/index"});
});