angular.module("siidfApp").service("catalogoService", function($http, config) {

	var mapObjects ={catalogoWeb: {}, opcion: {}, listaCatalago : [], listaOpciones: []};
	//var mapSources = {articulos : []};
	
	this.setMapObjects = function(object) {
		mapObjects = object;
 	};
	
	this.getMapObjects = function() {
		return mapObjects;
	}
	
	/*
	this.setMapSources = function(object) {
		mapSources = object;
 	};
	
	this.getMapSources = function() {
		return mapSources;
	}*/
	
	this.buscarTodosLosEstados = function(tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/estados");
	};

	this.buscarEstadoPorCodigo = function(codigo) {
		return $http.get(config.baseUrl + "/estados", {
			params : {
				"codigo" : codigo
			}
		});
	};
	
	this.buscarSectoresPorEstado = function(estadoId){
		return $http.get(config.baseUrl + "/sector", {
			params : {
				"estadoId" : estadoId 
			}
		});
	};
	
	this.buscarUnidadTerritorialPorSector = function(sectorId){
		return $http.get(config.baseUrl + "/unidadTerritorial", {
			params : {
				"sectorId" : sectorId
			}
		});
	};
		
	
	this.buscarCatalogoResponsableVehiculoActivo = function(){
		return $http.get(config.baseUrl + "/catalogoResponsableVehiculo");
	};
	
	this.buscarTipoArrastreCatalogo = function(){
		return $http.get(config.baseUrl + "/tipoArrastreCatalogo");
	};
	
	this.buscarTipoUnidadCatalogo = function(){
		return $http.get(config.baseUrl + "/tipoUnidadCatalogo");
	};
	
	this.buscarGruaPorCodigo = function(codigo){
		return $http.get(config.baseUrl + "/grua", {
			params : {
				"codigo" : codigo
			}
		});
	};
	
	this.empleadoPorPlaca = function(placa){
		return $http.get(config.baseUrl + "/empleado", {
			params : {
				"placa" : placa
			}
		});
	};
	
	this.	empleadoLogeado = function(){
		return $http.get(config.baseUrl + "/empleadoLogeado", {
		});
	};
	

	
	this.buscarConDireccion = function(){
		return $http.get(config.baseUrl + "/conDireccion");
	};
	
	this.buscarObserveQue = function(){
		return $http.get(config.baseUrl + "/observeQue");
	};
	
	this.delegacionPorEstado = function(estado){
		return $http.get(config.baseUrl + "/delegaciones", {
			params : {
				"estadoId" : estado
			}
		});
	};
	
	this.buscarTiposVehiculo = function(){
		return $http.get(config.baseUrl + "/catalogoTipoVehiculo");
	};
	
	this.buscarVehiculosMarcas = function(){
		return $http.get(config.baseUrl + "/marcasVehiculos");
	};
	
	this.buscarVehiculosModeloPorMarca = function(marca){
		return $http.get(config.baseUrl + "/modelosVehiculos", {
			params : {
				"marca" : marca
			}
		});
	};
	
	this.buscarVehiculosColor = function(){
		return $http.get(config.baseUrl + "/colorVehiculo");
	};
	
	this.buscarEstadosTodos = function(){
		return $http.get(config.baseUrl + "/estadosTodos");
	};
	
	this.buscarTipoLicencias = function(){
		return $http.get(config.baseUrl + "/tipoLicencia");
	};
	
	this.buscarGarantiasDocumentos = function(){
		return $http.get(config.baseUrl + "/garantiasCatDocumentos");
	};
	
	this.buscarArticulosActivos = function(fecha){
		return $http.get(config.baseUrl + "/articulosPorFecha", {
			params:{
				"fecha" : fecha
			}
		});
	};
	
	this.buscarFraccionesActivasPorArticulo = function(fecha, articulo){
		return $http.get(config.baseUrl + "/fraccionPorFechaYArtNum", {
			params:{
				"fecha" : fecha,
				"articuloNumero" : articulo
			}
		});
	};
	
	this.buscarParrafoIncisoPorFraccion = function(fecha, articulo, fraccion){
		return $http.get(config.baseUrl + "/incisoParrafoPorFechaArtNumYFraccion", {
			params:{
				"fecha" : fecha,
				"articuloNumero" : articulo,
				"fraccion" : fraccion
			}
		});
	};
	
	this.buscarSancionesPorFraccion = function(articulo){
		return $http.get(config.baseUrl + "/sancionArticulo", {
			params:{
				"articuloId" : articulo
			}
		});
	};
	
	this.buscaEmpleadoParaModDeposito = function(placa, pwd){
		return $http.get(config.baseUrl + "/empleadoParaModificarDeposito", {
			params : {
				"placa" : placa,
				"pwd" : pwd
			}
		});
	};
	
	/* Control de suministros */
	this.buscarTipoSuministro = function() {
		return $http.get(config.baseUrl + "/tipoSuministro");
	};
	
	this.buscarRegionales = function() {
		return $http.get(config.baseUrl + "/regionales");
	};
	
	this.buscarAreaOperativa = function(reg_id){
		return $http.get(config.baseUrl + "/areaOperativa", {
			params : {
				"reg_id" : reg_id 
			}
	});
	};
	
	this.buscarOficiales = function(reg_id,upc_id){
		return $http.get(config.baseUrl + "/oficiales", {
			params : {
				"reg_id" : reg_id,
				"upc_id" : upc_id
			}
	});
	};
	
	this.buscarAuxiliar = function(reg_id,upc_id){
		return $http.get(config.baseUrl + "/auxiliares", {
			params : {
				"reg_id" : reg_id,
				"upc_id" : upc_id
			}
	});
	};
	
	this.buscarRadares = function (option){
		return $http.get(config.baseUrl + "/fotomulta/radares", {
			params : {
				"option" : option
			}
		});
	}
	
	this.buscarTipoResultado = function(){
		return $http.get(config.baseUrl + "/tipoResultadoSinCajaHistorico");
	};
	
	this.buscarTiposFechaConsultaLotes = function(){
		return $http.get(config.baseUrl + "/fotomulta/tiposfechaconsultalotes");
	};
	
	this.buscarEstatusProcesoLotes = function(){
		return $http.get(config.baseUrl + "/estatusProcesoLotes");
	};
	
	this.buscarTipoBusqueda = function(){
		return $http.get(config.baseUrl + "/tipoBusquedaSinCajaActual");
	};
	
	this.buscatiposReporteWeb = function(){
		return $http.get(config.baseUrl + "/tiposReporteWeb");
	};
	
	this.filtroLiberacionVehiculo = function(){
		return $http.get(config.baseUrl + "/filtroLiberacionVehiculo");
	}
	
	this.buscarAniosSalarioMinimo = function(){
		return $http.get(config.baseUrl + "/fotomulta/aniossalariominimo");
	}
	
	this.buscarTipoCajas = function(){
		return $http.get(config.baseUrl + "/tipoCajasHistorico");
	};
	
	this.buscarProcesados = function (){
		return $http.get(config.baseUrl + "/fotomulta/procesados");
	};
	
	this.buscarEstatus = function (){
		return $http.get(config.baseUrl + "/fotomulta/estatus");
	};

	/* Remisiones a Deposito */
	this.OpcionesConInfaccion = function() {
		return $http.get(config.baseUrl + "/opcionesConInfraccion");
	};
	
	/* Lista de operativo  */
	this.listaOperativo = function() {
		return $http.get(config.baseUrl + "/listaOperativa");
	};
	
	/* Lista de Medios de trasporte   */
	this.listaMediosTrasp = function() {
		return $http.get(config.baseUrl + "/listaMediosTransp");
	};
	
	/* Lista No. de Grua   */
	this.listaNoGruas = function() {
		return $http.get(config.baseUrl + "/listaNoGruas");
	};
	
	/* Lista de causa de ingreso  */
	this.listaCausaIngreso = function() {
		return $http.get(config.baseUrl + "/listaCausaIngreso");
	};
	
	/* Lista de causa de ingreso por traslado  -- cambios relacionados al mov entre depositos*/
	
	this.listaCausaIngresoByTraslado = function() {
		return $http.get(config.baseUrl + "/listaCausaIngresoByTraslado");
	};
	
	/* Lista de causa de ingreso  */
	this.listaCausaIngresoSin = function() {
		return $http.get(config.baseUrl + "/listaCausaIngresoSin");
	};
	
	/* Lista de Deposito */
	this.listaDeposito = function(dep_id) {
		return $http.get(config.baseUrl + "/listaDeposito", {
			params : {
				"dep_id" : dep_id
			}
	});
	};
	
	/* Lista de causa de ingreso  */
	this.listaDepositoOrigen = function() {
		return $http.get(config.baseUrl + "/listaDepositoOrigen");
	};
	
	/* Lista de causa de ingreso  */
	this.listaInventario = function() {
		return $http.get(config.baseUrl + "/listaInventario");
	};
	
	
	/* Consulta de remisiones */
	this.OpcionesConsultaRemision = function() {
		return $http.get(config.baseUrl + "/opcionesConsultaRemi");
	};
	
	this.buscaTipoConsulta = function(){
		return $http.get(config.baseUrl + "/tipoBusquedaCaja");
	};
	
	this.buscarCatalogosWeb = function() {
		return $http.get(config.baseUrl + "/catalogosWeb");
	};
	
	this.buscarCatalogosWebOpcionesPorCatalogo = function(catalogoId) {
		return $http.get(config.baseUrl + "/catalogosWebOpciones/" + catalogoId);
	};
	
	this.buscarConcesionarias = function() {
		return $http.get(config.baseUrl + "/concesionarias");
	};
	
	this.buscarGruaStatus = function() {
		return $http.get(config.baseUrl + "/gruaStatus");
	};
	
	this.buscarRegionesPorEstado = function(estadoId) {
		return $http.get(config.baseUrl + "/regiones/" + estadoId);
	};
	
	this.buscarSectores = function() {
		return $http.get(config.baseUrl + "/sectorTodos");
	};
	
	this.buscarDelegacionPorEstado = function(estadoId) {
		return $http.get(config.baseUrl + "/delegacionesTodos/" + estadoId);
	};
	
	this.buscarRegionDepositoPorEstado = function(estadoId) {
		return $http.get(config.baseUrl + "/regiones/" + estadoId);
	};
	
	this.buscarRegionesDF = function() {
		return $http.get(config.baseUrl + "/regionesDepositoDF");
	};
	
	this.buscarZonas = function() {
		return $http.get(config.baseUrl + "/zonas");
	};
	
	this.buscarVehiculosMarcasTodos = function(){
		return $http.get(config.baseUrl + "/marcasVehiculosTodos");
	};
	
	this.buscarSubTipos = function(){
		return $http.get(config.baseUrl + "/subTiposVehiculo");
	};
	
	this.buscarArticulos = function(){
		return $http.get(config.baseUrl + "/articulosTodos");
	};
	
	this.buscarDatosCrud = function(nombreServicio) {
		return $http.get(config.baseUrl + nombreServicio);
	};
	
	this.enviarCrud = function(nombreServicio, crud) {
		return $http.post(config.baseUrl + nombreServicio, crud);
	};
	
	this.buscarArticulosInfraccionesFinanzasPorArticulo = function(articuloId) {
		return $http.get(config.baseUrl + "/articulosInfraccionesFinanzas/" + articuloId);
	};
	
	this.buscarArticulosInfraccionesFinanzasPorId = function(id) {
		return $http.get(config.baseUrl + "/articuloInfraccionesFinanzas/" + id);
	};
	
	this.actualizarArticulosInfraccionesFinanzas = function(articuloVO) {
		return $http.post(config.baseUrl + "/articuloInfraccionesFinanzas", articuloVO);
	};
	
	this.buscarProgramas = function() {
		return $http.get(config.baseUrl + "/programasActivos");
	};
	
	this.buscarCategorias = function() {
		return $http.get(config.baseUrl + "/categoriasActivas");
	};
	
	this.buscarCausales = function() {
		return $http.get(config.baseUrl + "/causalesActivos");
	};
	
	this.buscarArticuloPorId = function(articuloId) {
		return $http.get(config.baseUrl + "/articulos/" + articuloId);
	};
	
	this.generarExcel = function(nombreServicio) {
		return $http({
	        method: 'GET',
	        url: config.baseUrl + nombreServicio,
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
	this.buscaTipoConsulta = function(){
		return $http.get(config.baseUrl + "/tipoBusquedaCaja");
	};
	
	this.buscaCausasIngreso = function(){
		return $http.get(config.baseUrl + "/causasIngreso");
	}
	
	this.buscaTipoIngresos = function(){
		return $http.get(config.baseUrl + "/ingresos/tipos");
	}
	
	this.filtroEmbargos = function(){
		return $http.get(config.baseUrl + "/filtroEmbargos");
	}
	
	this.filtroOrigenPlaca = function(tipo){
		return $http.get(config.baseUrl + "/filtroOrigenPlaca", {
			params : {
				"tipoFiltro" : tipo 
			}
		});
	}
	
	this.registrosPorPagina = function(){
		return $http.get(config.baseUrl + "/registrosPorPagina");
	}
	
	this.filtroTipoDetecciones = function (option){
		return $http.get(config.baseUrl + "/fm/tiposDetecciones", {
			params : {
				"option" : option
			}
		});
	}
	
	this.buscarRadaresByMarca = function (option, idMarca){
		return $http.get(config.baseUrl + "/fm/tiposRadaresByMarca", {
			params : {
				"option" : option,
				"idMarca" : idMarca
			}
		});
	}
	this.filtroPeriodoFecha = function (option){
		return $http.get(config.baseUrl + "/fm/periodoFecha", {
			params : {
				"option" : option
			}
		});
	}
	
	this.buscaTipoDescuento = function (){
		return $http.get(config.baseUrl + "/buscaTipoDescuento");
	}
	
	this.filtroTipoDeteccionesFC = function (option){
		return $http.get(config.baseUrl + "/fc/tiposDetecciones", {
			params : {
				"option" : option
			}
		});
	}
	
	this.filtroTipoArchivo = function (origenPlaca){
		return $http.get(config.baseUrl + "/fc/tipoArchivo",{
			params : {
				"origenPlaca" : origenPlaca
			}
		});
	}
	
	this.comboTipoDeteccionesFC = function (opcion){
		return $http.get(config.baseUrl + "/fc/tiposDeteccionesOpcion", {
			params : {
				"opcion" : opcion
			}
		});
	}
	
	this.filtroTipoArchivoTodo = function (opcion){
		return $http.get(config.baseUrl + "/fc/tipoArchivoTodo",{
			params : {
				"opcion" : opcion
			}
		});
	}
	
	this.filtroOrigenPlacaFC = function(tipo){
		return $http.get(config.baseUrl + "/filtroOrigenPlacaFC", {
			params : {
				"tipoFiltro" : tipo 
			}
		});
	}
	
	this.filtroTiposBusquedaDetFC = function(tipoProcesable){
		return $http.get(config.baseUrl + "/filtroTiposBusquedaDetFC",{
			params : {
				"tipoProcesable" : tipoProcesable
			}
		});
	}
	
	this.valoresParametrosPorNbConfig = function(nbConfig){
		return $http.get(config.baseUrl + "/getValoresParametrosPorNbConfig", {
			params : {
				"nbConfig" : nbConfig 
			}
		});
	}
	
	this.tramitesTipoBusquedaAtencionCiudadana = function(){
		return $http.get(config.baseUrl + "/tramitesTipoBusquedaAC");
	};
	
	//Tipos de Tramite
	this.consultaTipoTramite = function(opcion){
		return $http.get(config.baseUrl + "/ac/filtroTipoTramite",{
			params : {
				"opcion" : opcion
			}
		});
	}
	
	this.consultaEstatusTramite = function(opcion){
		return $http.get(config.baseUrl + "/ac/filtroEstatusTramite",{
			params : {
				"opcion" : opcion
			}
		});
	}
	
	this.consultaParametrosBusquedaAC = function(){
		return $http.get(config.baseUrl + "/ac/filtroParametrosBusquedaAC");
	}
});
