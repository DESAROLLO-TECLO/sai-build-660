angular.module("siidfApp").service("fmService", function ($http, config) {
	
	this.buscarLoteEnProceso = function () {
		return $http.get(config.baseUrl + "/buscaLoteEnCurso",{});
	};
	
	this.consultaDeteccionesSP = function (tipoDeteccion, tipoFecha, multipleTipoArchivo) {//tipoDeteccion,tipoRadar,tipoFecha,origenPlaca
		return $http.get(config.baseUrl + "/consultaDeteccionesSP", {
			params : {
				"tipoDeteccion" : tipoDeteccion,
				"tipoFecha" : tipoFecha,
				"multipleTipoArchivo" : multipleTipoArchivo
			}});
	}
	
	this.consultaDeteccionesSPDetalleMes = function (idTipoFotocivica,idTipoArchivo,tipoConsulta,tipoFecha,tipoDetConsulta,mesConsulta,anioConsulta) {
		return $http.get(config.baseUrl + "/consultaDeteccionesSPDetalle", {
			params : {
				"idTipoFotocivica" : idTipoFotocivica,
				"idTipoArchivo" : idTipoArchivo,
				"tipoConsulta" : tipoConsulta,
				"tipoFecha" : tipoFecha,
				"tipoDetConsulta" : tipoDetConsulta,
				"mesConsulta" : mesConsulta,
				"anioConsulta" : anioConsulta
			}});
	}
	
	this.consultaDeteccionesSPDetalleDia = function (idTipoFotocivica,idTipoArchivo,tipoConsulta,tipoFecha,tipoDetConsulta,mesConsulta,anioConsulta) {
		return $http.get(config.baseUrl + "/consultaDeteccionesSPDetalle", {
			params : {
				"idTipoFotocivica" : idTipoFotocivica,
				"idTipoArchivo" : idTipoArchivo,
				"tipoConsulta" : tipoConsulta,
				"tipoFecha" : tipoFecha,
				"tipoDetConsulta" : tipoDetConsulta,
				"mesConsulta" : mesConsulta,
				"anioConsulta" : anioConsulta
			}});
	}
	
	this.consultaDeteccionesDetalleDia = function (tipoDeteccion,tipoRadar,tipoFecha,origenPlaca,fechaInicio,fechaFin) {
		return $http.get(config.baseUrl + "/consultaDeteccionesSPDetalleHistoricasDetalleDia", {
			params : {
				"TipoDeteccion":tipoDeteccion,
                "TipoRadar":tipoRadar,
                "tipoFecha":tipoFecha,
                "origenPlaca":origenPlaca,
                "fechaInicio":fechaInicio,
                "fechaFin":fechaFin
			}});
	}
	
	
	this.consultaDeteccionesSPDetalleHistoricas = function (tipoDeteccion,tipoRadar,tipoFecha,origenPlaca) {
		return $http.get(config.baseUrl + "/consultaDeteccionesSPDetalleHistoricas", {
			params : {
				"TipoDeteccion":tipoDeteccion,
                "TipoRadar":tipoRadar,
                "tipoFecha":tipoFecha,
                "origenPlaca":origenPlaca
			}});
	}
	this.validarDeteccionesParaLote = function(fechaInicio, fechaFin, tipoPersona, nombrePersona, tipoDeteccion, nombreDeteccion, origenPlaca){
		return $http.get(config.baseUrl + "/validarDeteccionesParaLoteFM", {
			params:{"fechaInicio"	  : fechaInicio, 
					"fechaFin"   	  : fechaFin, 
					"tipoPersona"  	  : tipoPersona, 
					"nombrePersona"	  : nombrePersona,
					"tipoDeteccion"	  : tipoDeteccion, 
					"nombreDeteccion" : nombreDeteccion,
					"origenPlaca"	  : origenPlaca
			}
		});
	}
	
	this.crearLote = function(fechaEmision, nombreLote, salario, fInicio, fFin, tipoPersona, tipoDeteccion, origenPlaca, stLCaptura, stVCP){
		return $http.get(config.baseUrl + "/crearLoteFM",{
			params:{
				"fechaEmision": fechaEmision, 
				"nombreLote": nombreLote, 
				"salarioMinimo": salario, 
				"fechaInicio" : fInicio,
				"fechaFin": fFin, 
				"tipoPersona" : tipoPersona,
				"tipoDeteccion" : tipoDeteccion,
				"origenPlaca": origenPlaca,
				"stLCaptura" : stLCaptura,
				"stVCP" : stVCP
			}
		});
	}
	
	this.consultaLotesCreados = function (){
		return $http.get(config.baseUrl + "/consultaLotesCreadosFM",{});
	};
	
	this.generarReporteDeteccionesPorLote = function(fechaInicio, fechaFin, tipoDeteccion, tipoPersona, nombrePersona, nombreDeteccion, origenPlaca){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteDeteccionesPorLoteFM",
	        params: {
	        	"fechaInicio": fechaInicio, 
				"fechaFin": fechaFin, 
				"tipoDeteccion": tipoDeteccion, 
				"tipoPersona" : tipoPersona,
				"nombrePersona": nombrePersona, 
				"nombreDeteccion" : nombreDeteccion,
				"origenPlaca" : origenPlaca
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	/*UTILITIES*/
	this.downloadfile = function(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
	}
	
	this.realizarComplementacion = function (idLote){
		return $http.get(config.baseUrl + "/realizarComplementacionFM",{
			params:{"idLote": idLote}});
	}
	
	this.buscarArchivoaCancelar = function(lote){
		return $http.get(config.baseUrl + "/buscaFmLoteACancelar",
				{params:{"lote": lote}});
	}
	
	this.cancelaFmLote = function(lote, motivo){
		return $http.get(config.baseUrl + "/cancelacionFmLote",
				{params:{"lote": lote, "motivo": motivo}});
	}
	
	this.buscarDeteccionesFmCPValidas = function(lote){
		return $http.get(config.baseUrl + "/buscarDeteccionesFmValidas",
				{params:{"lote": lote}});
	}
	
	this.buscarDeteccionesFmCPInvalidas = function(lote){
		return $http.get(config.baseUrl + "/buscarDeteccionesFmInvalidas",
				{params:{"lote": lote}});
	}
	
	this.buscarAllDeteccionesFm = function(lote){
		return $http.get(config.baseUrl + "/buscarAllDeteccionesFm",
				{params:{"lote": lote}});
	}
	
	this.codigoPostalReasignado = function(lote){
		return $http.get(config.baseUrl + "/actualizaLoteCP",
				{params:{"lote": lote}});
	}
	this.recomplementaCodigoPostal2 = function(ids,newcp){
		return $http.get(config.baseUrl + "/complementacionDeCP",
				{params:{"ids": ids, "newcp":newcp}});
	}
	
	this.recomplementaCodigoPostal = function(lote){ 
		return $http.get(config.baseUrl + "/recomplementaCP",
				{params:{"lote": lote}});
	}

	this.loteFmCambiaCpDesh = function(idCP, idLote){
		return $http.get(config.baseUrl + "/deshabilitaFmCambioCP",
				{params : {"idCP": idCP, "idLote": idLote }});
	}
	
	this.loteFmCambiaCpHabil = function(idCP, CP, idLote){
		return $http.get(config.baseUrl + "/habilitaFmCambioCP",
				{params : {"idCP": idCP, "cp": CP, "idLote": idLote }});
	}

/*
 *metodos para funcionalidad fmCancelacionDetecciones
 */
 
	this.TiposFechaDetecciones = function (){
		return $http.get(config.baseUrl + "/TiposFechaDetecciones",{});
	};
	
	this.consultaDeteccionesParaCancelar = function (fechaInicio,fechaFin,tipoDeteccion,tipoRadar,origenPlaca) {
		return $http.get(config.baseUrl + "/consultaDeteccionesParaCancelar", {
			params : {
					"fechaInicio":fechaInicio,
					"fechaFin":fechaFin,
					"TipoDeteccion":tipoDeteccion,
					"TipoRadar":tipoRadar,
					"OrigenPlaca":origenPlaca
			}});
	}
	
	this.consultaDetalles = function (fechaInicio,fechaFin,tipoDeteccion,tipoRadar,origenPlaca) {
		return $http.get(config.baseUrl + "/consultaDetalles", {
			params : {
					"fechaInicio":fechaInicio,
					"fechaFin":fechaFin,
					"TipoDeteccion":tipoDeteccion,
					"TipoRadar":tipoRadar,
					"OrigenPlaca":origenPlaca
			}});
	}
	
	this.descargarExcel = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelDetalleDetecciones",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.CancelarDeteccionesFM = function (fechaInicio,fechaFin,tipoDeteccion,tipoRadar,origenPlaca,motivo) {
		return $http.get(config.baseUrl + "/CancelarDeteccionesFM", {
			params : {
					"fechaInicio":fechaInicio,
					"fechaFin":fechaFin,
					"TipoDeteccion":tipoDeteccion,
					"TipoRadar":tipoRadar,
					"OrigenPlaca":origenPlaca,
					"motivo":motivo
			}});
	}
	
	/* final de metodo para la funcionalidad fmCancelacionDetecciones */
	
	/* Inicia funcionalidad de Estadisticas Detecciones*/
	this.consultaDetEstadistica = function (switchRangoFecha, periodoFecha, fechaInicio, fechaFin, tipoDeteccion, tipoRadar, origenPlaca, estatusproceso, nombreRadar, nombreDeteccion, opcion) {
		return $http.get(config.baseUrl + "/estadisticasDetecciones", {
			params : {
				"switchRangoFecha"	: switchRangoFecha, 
				"periodoFecha"		: periodoFecha,
				"fechaInicio"		: fechaInicio,
				"fechaFin"			: fechaFin, 
				"tipoDeteccion"		: tipoDeteccion, 
				"tipoRadar"			: tipoRadar, 
				"origenPlaca"		: origenPlaca, 
				"estatusproceso"	: estatusproceso, 
				"nombreRadar"		: nombreRadar, 
				"nombreDeteccion"	: nombreDeteccion,
				"opcion"			: opcion
			}
		});
	}
	/* Final funcionalidad de Estadisticas Detecciones*/
	
	this.generarReporteDeteccionesEstadisticas = function (opcion) {
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteDeteccionesEstadisticas",
	        params: {
	        	"opcion" : opcion
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.consultaDetDisponibles = function (){
		return $http.get(config.baseUrl + "/consultaDetDisponiblesFC",{});
	};
	
	this.consultaNombreSugLote = function (fechaInicio, fechaFin, tipoPersona,	tipoDeteccion){
		return $http.get(config.baseUrl + "/consultaNombreSugLoteFC", {
			params : {
				"fechaInicio"	: fechaInicio, 
				"fechaFin"		: fechaFin, 
				"tipoPersona"	: tipoPersona, 
				"tipoDeteccion"	: tipoDeteccion
			}
		});
	};
	
	this.consultaDetecciones = function (tipoDeteccion,multipleTipoArchivo,fechaInicio,fechaFin,tipoBusqueda,valorBusqueda, consultaProcesables) {
		return $http.get(config.baseUrl + "/consultaDetecciones", {
			params : {
				"tipoDeteccion" : tipoDeteccion,
				"multipleTipoArchivo" : multipleTipoArchivo,
				"fechaInicio" : fechaInicio,
				"fechaFin" : fechaFin,
				"tipoBusqueda" : tipoBusqueda,
				"valorBusqueda" : valorBusqueda,
				"consultaProcesables" : consultaProcesables
			}});
	}
	
	this.generaRepConsultaDeteccionesFC = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generaRepConsultaDetecciones",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.crearLoteValidaciones = function(idLote){
		return $http.get(config.baseUrl + "/crearLoteValidaciones",{
			params : {
				"idLote" : idLote
			}
		});
	}
});