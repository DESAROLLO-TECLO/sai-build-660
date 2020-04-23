angular.module("siidfApp").service("atencionCiudadanaService", function ($http, config) {	
	
	parametrosBusquedaAnterior={};
	
	this.getTiposDeBusquedaTramites = function(){
		return $http.get(config.baseUrl + "/atencionCiudadana/tramitesTipoBusqueda");
	};
	
	this.busquedaTramitesParametros = function(){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaTramitesDefault");
	};
	
	this.busquedaTramitesDefault = function(){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaTramitesDefault");
	};

	//Combo Tipo de Tramite
	this.comboTipoTramite = function(){
		
		return $http.get(config.baseUrl + "/atencionCiudadana/catTipoTramite");	
	};
	
	/// Combo Tipo de Documento
	this.comboTipoDocumento = function(){
		
		return $http.get(config.baseUrl + "/atencionCiudadana/catTipoDocumento");	
	};
	
	/// Combo medio de solicitud
	this.comboOrigenSolicitud = function(){
		
		return $http.get(config.baseUrl + "/atencionCiudadana//catOrigenSolicitud");	
	};
	//alta de tramite
	this.altaTramite = function(altaTramiteVO){//R
		return $http.post(config.baseUrl + "/atencionCiudadana/altaTramite", altaTramiteVO);
	}
	


	this.generaReporteTramite = function(idTramite){
	
		return $http({
			method : 'GET',
			url : config.baseUrl + "/atencionCiudadana/reporteAltaTramite",
			params:{
				"idTramite" : idTramite,
				},
			dataType: "json",
	    	header :{ "Content-type": "application/json",
	    			"Accept"    : "pdf"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.vistaPrevia = function(altaTramiteVO){
		
		//return $http.post(config.baseUrl + "/atencionCiudadana/vistaPrevia", altaTramiteVO);
		return $http({
			method : 'POST',
			url : config.baseUrl + "/atencionCiudadana/vistaPrevia",
			data:altaTramiteVO,
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



	this.busquedaTramitesParametros = function(parametrosBusqueda){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaTramitesParametros",
		{
			params:{
				"fhInicio": parametrosBusqueda.startDate,
				"fhFin": parametrosBusqueda.endDate,
				"paramBusqueda": parametrosBusqueda.paramBusqueda,
				"valor": parametrosBusqueda.valorBusqueda,
				"opcionAtendido": parametrosBusqueda.valorFueAtendido,
				"avanzadoNombre":parametrosBusqueda.busquedaNombre,
				"avanzadoAP": parametrosBusqueda.busquedaApellidoP,
				"avanzadoAM": parametrosBusqueda.busquedaApellidoM,
				"avanzadoTel":parametrosBusqueda.busquedaTel,
				"avanzadoCorreo":parametrosBusqueda.busquedaCorreo,
				"avanzadoEmpresa":parametrosBusqueda.busquedaNomEmpresa,
				"busquedaAvanzada":parametrosBusqueda.busquedaAvanzada


						 }
		});
	};
	
	this.reporteConsultaExcel = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/atencionCiudadana/tramitesExcel",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.expedienteIdTramite = function(idTramite){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/atencionCiudadana/descargarExpediente",
	        params: {"idTramite":idTramite},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}

	this.cargarExpediente = function(expedienteTramiteVO){
		return $http.post(config.baseUrl + "/atencionCiudadana/cargarExpedienteTramite", expedienteTramiteVO);
	};
	
	this.consultadaDefualtModificacion = function(op){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaDefaultModificacion", {
			params:{
				"opcion" : op
			}
		});
	}
	
	this.busquedaTramitesAModificar = function(fhInicio, fhFin, paramBusqueda, valor, atendido){
		return $http.get(config.baseUrl + "/atencionCiudadana/busquedaTramitesAModificar",
		{
			params:{
				"fhInicio": fhInicio,
				"fhFin": fhFin,
				"paramBusqueda": paramBusqueda,
				"valor": valor,
				"opcionAtendido": atendido
			}
		});
	};
	
	this.busquedaTramitePorFolio = function(folio, paramBusqueda){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaTramiteDetalle",
		{
			params:{
				"folio": folio,
				"paramBusqueda": paramBusqueda
			}
		});
	};
	
	this.consultaInfraccion = function(placa, infraccion){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaInfraccion",
		{
			params:{
				"placa": placa,
				"infraccion": infraccion
			}
		});
	};
	
	this.camposRequeridos = function(){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaCamposRequeridos");
	};
	
	this.modificaTramite = function(motificaTramiteVO){
		return $http.post(config.baseUrl + "/atencionCiudadana/modificaACTramite", motificaTramiteVO);
	};
	
	this.descargarExpediente = function(folioTramite, tipoExp){
		return $http({     
	        method: 'POST',
	        url: config.baseUrl + "/atencionCiudadana/descargarExpedienteTramite",
	        params: {"folioTramite":folioTramite, "tipoExp":tipoExp},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "pdf"
	        },
	        responseType: ''
	    });
	};
	
	this.remplazaExpediente = function(expedienteTramiteVO){
		return $http.post(config.baseUrl + "/atencionCiudadana/remplazarExpedienteTramite", expedienteTramiteVO);
	};
	
	this.informacionTooltip=function()
	{
		return $http.get(config.baseUrl + "/atencionCiudadana/informacionTooltipAyuda");
	};
	
	this.consultaSeguimiento = function(
			tipoBusqueda, valor, idTipoTramite, 
			stSegTramite, tipoFecha, fhInicio, fhFin){
		return $http.get(config.baseUrl + "/atencionCiudadana/consultaSeguimiento",
		{
			params:{
				"tipoBusqueda": tipoBusqueda,
				"valor": valor,
				"idTipoTramite": idTipoTramite,
				"stSegTramite": stSegTramite,
				"tipoFecha": tipoFecha,
				"fhInicio": fhInicio,
				"fhFin": fhFin
			}
		});
	};
	
	this.consultaInfraccionesTramite = function(
			placaVehicular,
			infracNum,
			listInfracciones){
		return $http.get(config.baseUrl + "/atencionCiudadana/getInfCambioDePersona",
		{
			params:{
				"placaVehicular"	: placaVehicular,
				"infracNum"			: infracNum,
				"listInfracciones"	: listInfracciones
			}
		});
	};
	
	this.realizarCambioDePersona = function(
			idAcTramite, txComentario){
		return $http.get(config.baseUrl + "/atencionCiudadana/realizarCambioDePersona",
		{
			params:{
				"idAcTramite" : idAcTramite, 
				"txComentario" : txComentario
			}
		});
	};
	
	this.descargarExcelSeguimeinto = function(
			tipoBusqueda, valor, idTipoTramite, 
			stSegTramite, tipoFecha, fhInicio, fhFin){
		return $http.get(config.baseUrl + "/atencionCiudadana/seguimeintoTramitesTPExcel",
		{
			params:{
				"tipoBusqueda": tipoBusqueda,
				"valor": valor,
				"idTipoTramite": idTipoTramite,
				"stSegTramite": stSegTramite,
				"tipoFecha": tipoFecha,
				"fhInicio": fhInicio,
				"fhFin": fhFin
			},
			dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
		});
	};
	
	this.descargaExcelInfraccionesTramite = function(
			placaVehicular,
			infracNum,
			listInfracciones,
			idAcTramite){
		return $http.get(config.baseUrl + "/atencionCiudadana/seguimeintoTramitesTPInfExcel",
		{
			params:{
				"placaVehicular"	: placaVehicular,
				"infracNum"			: infracNum,
				"listInfracciones"	: listInfracciones,
				"idAcTramite" : idAcTramite
			},
			dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
		});
	};
});
