angular.module("siidfApp").service("fotoMultaService", function ($http, config) {	
	
	this.filtroTiposFecha = function () {
		return $http.get(config.baseUrl + "/filtroTiposFecha");
	};

	this.buscarDeteccionesPreValidadas = function () {
		return $http.get(config.baseUrl + "/buscarDeteccionesPreValidadas");
	};
	
	this.buscarDetecciones = function(tipoFecha, origenPlaca){
		return $http.get(config.baseUrl + "/buscarDetecciones", 
				{params:{"tipoFecha": tipoFecha, "origenPlaca": origenPlaca}});
	}
	
	this.buscarDeteccionesPorFechaYRadar = function (tipoBusqueda, codigoRadar, origenPlaca, tipoDeteccion){
		return $http.get(config.baseUrl + "/buscarDeteccionesPorTipoFechaYRadar", 
				{params:{"tipoFecha": tipoBusqueda, "codigoRadar": codigoRadar, "origenPlaca" : origenPlaca,
					"tipoDeteccion" : tipoDeteccion}});
	}
	
	this.buscarDeteccionesPorMes = function (fechaInicio, origenPlaca, tipoFecha, radar){
		return $http.get(config.baseUrl + "/buscarDeteccionesPorMes", 
				{params:{"fechaInicio": fechaInicio, "origenPlaca" : origenPlaca, "tipoFecha": tipoFecha,
					"codigoRadar" : radar}});
	}
	
	this.consultaDeteccionesFotomulta = function (tipoFecha, fechaInicio, fechaFin, estatus, procesado, radarTipo, radarNombre, origenPlaca){
		return $http.get(config.baseUrl + "/consultaDeteccionesFotomulta", 
					{params:{"tipoFecha": tipoFecha, "fechaInicio": fechaInicio,
							"fechaFin": fechaFin, "estatus": estatus,
							"procesado": procesado, "radarTipo": radarTipo,
							"radarNombre": radarNombre, "origenPlaca" : origenPlaca}});
	}
	
	this.generarReporteDetecciones = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteDetecciones",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.validarDeteccionesParaLote = function(fechaInicio, fechaFin, radarTipo, nombreRadar, origenPlaca){
		return $http.get(config.baseUrl + "/validarDeteccionesParaLote", 
				{params:{"fechaInicio": fechaInicio, "fechaFin": fechaFin, 
					"tipoRadar": radarTipo, "nombreRadar" : nombreRadar,
					"origenPlaca" : origenPlaca}});
	}
	
	this.generarReporteDeteccionesPorLote = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteDeteccionesPorLote",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.crearLote = function(fechaEmision, nombreLote, salario, fInicio, fFin, tipoRadar, origenPlaca){
		return $http.get(config.baseUrl + "/crearLote", 
				{params:{"fechaEmision": fechaEmision, "nombreLote": nombreLote, 
					"salarioMinimo": salario, "fechaInicio" : fInicio,
					"fechaFin": fFin, "tipoRadar" : tipoRadar, "origenPlaca": origenPlaca}});
	}
	
	this.realizarComplementacion = function (lote){
		return $http.get(config.baseUrl + "/realizarComplementacion", 
				{params:{"loteId": lote}});
	}
	
	this.consultaLotesFotomulta = function(fechaInicio, fechaFin, tipoRadar, estatusProceso, tipoFecha, origenPlaca){
		return $http.get(config.baseUrl + "/consultaLotesFotomulta", 
				{params:{"fechaInicio": fechaInicio, "fechaFin": fechaFin,
						"tipoRadar": tipoRadar, "estatusProceso": estatusProceso,
						"tipoFecha": tipoFecha, "origenPlaca": origenPlaca}});
	}
	
	this.cancelarArchivo = function(lote, motivo){
		return $http.get(config.baseUrl + "/cancelacionArchivoFotomulta",
				{params:{"lote": lote, "motivo": motivo}});
	}
	
	this.buscarArchivoaCancelar = function(lote){
		return $http.get(config.baseUrl + "/buscaArchivoACancelar",
				{params:{"lote": lote}});
	}
	
	this.notificarLiberacionArchivo = function(lote){
		return $http.get(config.baseUrl + "/notificarLiberacion",
				{params:{"lote": lote, "tipoProcesows": 1}});
	}
	
	this.notificarAsignacionArchivo = function(lote, nombreLote){
		return $http.get(config.baseUrl + "/notificarAsignacion",
				{params:{"lote": lote, "tipoProcesows": 2, "nombreLote": nombreLote}});
	}
	
	this.buscaReporteEstadistica = function(fInicio, fFin, canceladas, tipoReporte){
		return $http.get(config.baseUrl + "/buscaReporteEstadistica",
				{params:{"fInicio": fInicio, "fFin": fFin, "canceladas": canceladas,
					"tipoReporte":tipoReporte}});
	}
	
	this.buscaDeteccionesRechazadasGeneral = function(fInicio, fFin, canceladas){
		return $http.get(config.baseUrl + "/buscaDeteccionesRechazadasGeneral",
				{params:{"fInicio": fInicio, "fFin": fFin, "canceladas": canceladas}});
	}
	
	this.buscaDeteccionesRechazadasPrevalidador = function(prevalidador, fInicio, fFin, canceladas, nombre){
		return $http.get(config.baseUrl + "/buscaDeteccionesRechazadasPrevalidador",
				{params:{"prevalidador": prevalidador, "fInicio": fInicio, "fFin": fFin, "canceladas": canceladas,
					"persona": nombre}});
	}
	
	this.buscaDeteccionesRechazadasParaReporteGeneralSSP = function(fInicio, fFin){
		return $http.get(config.baseUrl + "/buscaDeteccionesRechazadasParaReporteGeneralSSP",
				{params:{"fInicio": fInicio, "fFin": fFin}});
	}
	
	this.buscaDeteccionesRechazadasPorPersonaSSP = function(placa, fInicio, fFin, nombre){
		return $http.get(config.baseUrl + "/buscaDeteccionesRechazadasPorPersonaSSP",
				{params:{"placa":placa, "fInicio": fInicio, "fFin": fFin,
						"persona": nombre}});
	}
	
	this.generarReporteRendimiento = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteRendimiento",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.generarReporteDeteccionesRechazadas = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteDeteccionesRechazadas",
	        params: {},
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
	
	this.buscarDeteccionesPorRangoTiempo = function () {
		return $http.get(config.baseUrl + "/buscarDeteccionesPorRangoTiempo");
	};
	
	this.buscarAceptadasRechazadas = function () {
		return $http.get(config.baseUrl + "/buscarAceptadasRechazadas");
	};
	
	this.getLiberacionesInfracciones = function () {
		return $http.get(config.baseUrl + "/buscarLiberacionesInfracciones");
	};
	
	this.buscarUsuariosClasificacion = function () {
		return $http.get(config.baseUrl + "/buscarUsuariosClasificacion");
	};
	
	/***** Marcado de detecciones ****/
	
	this.busquedaDetecCancel = function (fecha,tipoRadar,origenPlaca) {
		return $http.get(config.baseUrl + "/busquedaDetecPrevalCancel",
		{params:{"fecha":fecha,"tipoRadar":tipoRadar,"origenPlaca":origenPlaca}});
	};
	
	this.obtenerFechasCombo = function () {
		return $http.get(config.baseUrl + "/obtenerFechasCombo");
	};
	
	this.cancelarDetecciones = function (tipoRadar,origenPlaca,motivo) {
		return $http.get(config.baseUrl + "/cancelarDetecciones",
				{params:{"tipoRadar":tipoRadar,"origenPlaca":origenPlaca,"motivoCancelacion":motivo}});
	};
	
	this.obtenerListaDetecciones = function (tipoLista) {
		return $http.get(config.baseUrl+"/"+tipoLista);
	};

	this.descargarExcel = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelMarcado",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	/*********/
	/***** Consulta detecciones Canceladas****/
	
	this.getCatMotCancelacion = function () {
		return $http.get(config.baseUrl + "/obtenerCatMotCancelacion");
	};

	this.getFechasCancelacion = function () {
		return $http.get(config.baseUrl + "/obtenerFechasCancelacion");
	};

	this.getDeteccionesCanceladas = function (parametrosVO) {		
		var json =   angular.toJson(parametrosVO);
		return $http.post(config.baseUrl + "/obtenerDeteccionesCanceladas",json);
	};
	
	
	this.descargarExcelCanceladas = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelCanceladas",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	
	
});