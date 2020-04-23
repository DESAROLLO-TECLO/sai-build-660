angular.module("siidfApp").service("lineaCapturaService", function ($http, config) {	
	
	/*COMIENZA REASIGNACION*/
	this.buscarInfraccionesRadarByFolio = function (folio, fechareasignacion) {
		return $http.get(config.baseUrl + "/buscarInfraccionRadarByFolio", 
				{params:{"folio": folio, "fechareasignacion": fechareasignacion}});
	};
	
	this.buscarDetalleReasignacionesByInfraccion = function (folio) {
		return $http.get(config.baseUrl + "/buscarDetalleReasignacionesByInfraccion", 
				{params:{"folio": folio}});
	};
	
	this.generarPDFPago = function(reasignacionVO){
		var json =  angular.toJson(reasignacionVO);
		 return $http({     
		        method: 'GET',
		        url: config.baseUrl + "/generarReportePagoFinanzas",
		        params: {"reasignacionVO": reasignacionVO},
		        dataType: "json",
		        header :{ "Content-type": "application/json",
		        			"Accept"    : "pdf"
		        		},
		        responseType: 'arraybuffer'
		    });
	};
	
	this.reasignarLineaCaptura = function (infraccionRadarVO, descuento){
		var json =  angular.toJson(infraccionRadarVO);
		return $http({
	          method: 'POST',
	          url: config.baseUrl + "/reasignarLineaDeCaptura",
	          params: {infraccionRadarVO : json, descuento : descuento},
	          dataType: "json",
	          contentType: {'Accept' : 'application/json'},
	          responseType: 'application/json'
		});
	}
	
	/*COMIENZA CONSULTA*/
	this.buscarInfraccionesReasignacionHistorico = function (fInicio, fFin, noInfraccion, placaOficial, placaVehiculo) {
		return $http.get(config.baseUrl + "/buscarInfraccionesReasignacionHistorico", 
				{params:{"fechaInicio": fInicio,
						"fechaFin": fFin,
						"noInfraccion": noInfraccion,
						"placaOficial": placaOficial,
						"placaVehiculo": placaVehiculo}});
	};
	
	this.buscarInfraccionesReasignacionEstadistica = function (fInicio, fFin, placaOficial, nombreOficial){
		return $http.get(config.baseUrl + "/buscarInfraccionesReasignacionEstadistica", 
				{params:{"fechaInicio": fInicio,
						"fechaFin": fFin,
						"placaOficial": placaOficial,
						"nombreOficial": nombreOficial}});
	}
	
	this.reporteHistoricosExcel = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/reporteHistoricos",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.reporteEstadisticaExcel = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/reporteEstadistica",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.buscarDetalleReasignacionesByOficial = function(placaOficial, fInicio, fFin){
		return $http.get(config.baseUrl + "/buscarDetalleReasignacionesByOficial", 
				{params:{"placaOficial": placaOficial,
						 "fechaInicio": fInicio,
						 "fechaFin": fFin,}});
	}
	
	this.reporteEstadisticaPorPersona = function(){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/reporteEstadisticaPorPersona",
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
});