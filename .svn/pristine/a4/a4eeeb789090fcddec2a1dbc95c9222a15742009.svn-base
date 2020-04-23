angular.module("siidfApp").service("cajaService",function($http, config) {

	this.buscaHistoricoTotalInfracciones = function(paramsVO) {
		return $http.post(config.baseUrl
				+ "/historicoTotalInfracciones", paramsVO);
	}

	this.buscaHistoricoDetalleInfracciones = function(paramsVO) {
		return $http.post(config.baseUrl
				+ "/historicoDetalleInfracciones", paramsVO);
	}

	this.buscaHistoricoDetalleInfraccion = function(caja, emp,
			fecha) {
		return $http.get(config.baseUrl
				+ "/historicoDetalleInfraccion", {
			params : {
				'caja' : caja,
				'emp' : emp,
				'fecha' : fecha
			}
		});
	}

	this.obtenerReporteExcel = function() {
		return $http({
			method : 'GET',
			url : config.baseUrl + "/reporteCorteSinCajaHistTot",
			params : {},
			dataType : "json",
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	}
	
	this.buscaActualTotalInfracciones = function(paramsVO) {
		parametroBusqueda = paramsVO.tipoBusqueda == "PERFIL_ID" ? paramsVO.perfilId : paramsVO.parametroBusqueda ==  null ? "" : paramsVO.parametroBusqueda;
		return $http.get(config.baseUrl
				+ "/actualTotalInfracciones", {
			params : {
				"tipoBusqueda" : paramsVO.tipoBusqueda,
				"parametroBusqueda" : parametroBusqueda
			}
		});
	}
	
	this.buscaActualDetalleInfraccion = function(caja, emp){
		return $http.get(config.baseUrl
				+ "/actualDetalleInfraccion", {
			params : {
				'caja' : caja,
				'emp' : emp
			}
		});
	}
	
	this.buscaActualDetalleInfracciones = function(paramsVO) {
		parametroBusqueda = paramsVO.tipoBusqueda == "PERFIL_ID" ? paramsVO.perfilId : paramsVO.parametroBusqueda ==  null ? "" : paramsVO.parametroBusqueda;
		return $http.get(config.baseUrl
				+ "/actualDetalleInfracciones", {
			params : {
				"tipoBusqueda" : paramsVO.tipoBusqueda,
				"parametroBusqueda" : parametroBusqueda
			}
		});
	}
	
	this.consultaCajaUsuarios = function(param, tipoBusqueda){
		return $http.get(config.baseUrl + "/consultaCajaUsuarios", {
			params : {
				"param" : param,
				"tipoBusqueda" : tipoBusqueda
			}
		});
	}
	
	this.preparaCorte = function(cajaId, tipoCorte, fechaCorte) {
		return $http.get(config.baseUrl
				+ "/ProcInformeCorte", {
			params : {
				"cajaId" : cajaId,
				"tipoCorte" : tipoCorte,
				"fechaCorte" : fechaCorte
			}
		});
	}
	
	this.guardaCorte = function(paramsVO) {
		return $http.post(config.baseUrl
				+ "/ProcGuardarCorte", paramsVO);
	}
	
	this.impresionCorteEfectuado = function(paramsVO){
		return $http({
			method : 'POST',
			url : config.baseUrl + "/ImpresionCorteEfecutado",
			data: paramsVO,
			 dataType: "json",
		        header :{ "Content-type": "application/json",
		        			"Accept"    : "pdf"
		        		},
		        responseType: 'arraybuffer'
		    } );
	}
	
	this.imprimirPartidas = function(cajaId, numCaja, fecha){
		return $http({
			method : 'GET',
			url : config.baseUrl + "/ImpresionPartidasCorteEfecutado",
			params: {'cajaId' : cajaId, 'numCaja' : numCaja, "fecha" : fecha},
			dataType: "json",
		    header :{	"Content-type" : "application/json",
		        		"Accept"    : "pdf"
		        	},
		    responseType: 'arraybuffer'
		    });
	}
	
	this.imprimirPartidastTar = function(cajaId, numCaja, fecha, cajaCod){
		return $http({
			method : 'GET',
			url : config.baseUrl + "/ImpresionPartidasTarCorteEfecutado",
			params: {'cajaId' : cajaId, 'numCaja' : numCaja, "fecha" : fecha, "cajaCod" : cajaCod},
			dataType: "json",
		    header :{	"Content-type" : "application/json",
		        		"Accept"    : "pdf"
		        	},
		    responseType: 'arraybuffer'
		    });
	}
	
	this.imprimirInfracciones = function(cajaId, numCaja, fecha){
		return $http({
			method : 'GET',
			url : config.baseUrl + "/ImpresionInfraccionesCorteEfecutado",
			params: {'cajaId' : cajaId, 'numCaja' : numCaja, "fecha" : fecha},
			dataType: "json",
		    header :{	"Content-type" : "application/json",
		        		"Accept"    : "pdf"
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
	
	this.consultaCajaFechaPorParam = function(param, tipoBusqueda) {
		return $http.get(config.baseUrl
				+ "/ConsultaCajaFechaPorParam", {
			params : {
				"param" : param,
				"tipoBusqueda" : tipoBusqueda
			}
		});
	}
	
	this.impresionCorteConsulta = function(paramsVO){
		return $http({
			method : 'POST',
			url : config.baseUrl + "/ImpresionCorteConsulta",
				
				data : paramsVO,
				dataType: "json",
		        header :{ "Content-type": "application/json",
		        			"Accept"    : "pdf"
		        		},
		        responseType: 'arraybuffer'
		    });
	}
	
	this.modificaCajaCod = function(paramsVO) {
		return $http.post(config.baseUrl
				+ "/ModificaCajaCod", paramsVO);
	}
	
	this.consultaCajaFechaPorParam = function(param, tipoBusqueda) {
		return $http.get(config.baseUrl
				+ "/ConsultaCajaFechaPorParam", {
			params : {
				"param" : param,
				"tipoBusqueda" : tipoBusqueda
			}
		});
	}
	
	this.usuarioEnCaja = function(placaUsuario){
		return $http.get(config.baseUrl + "/ConsutlaUsuariosCaja", {params: {"placaUsuario": placaUsuario}})
	}
	
	this.guardaUsuarioEnCaja = function(paramsVO){
		return $http.post(config.baseUrl + "/adminSPCajasUsuarios", paramsVO);
	}
	
	this.guardaDepositoEnCaja = function(paramsVO){
		return $http.post(config.baseUrl + "/adminSPCajasDepositos", paramsVO);
	}
	
	this.toggleCaja = function(paramsVO){
		return $http.post(config.baseUrl + "/adminSPCajasModif", paramsVO);
	}
	
	this.obtenerReporteAdminExcel = function() {
		return $http({
			method : 'GET',
			url : config.baseUrl + "/administracionController/reporteAdminCajas",
			params : {},
			dataType : "json",
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	}
});