
angular.module("siidfApp").service("centroPagosService", function($http, config) {
   
	this.ultimaConsultaCentroPagos= function(){
 		return $http.get(config.baseUrl + "/centroPagos/ultimaConsulta");
	}
	
	this.consultaTotalesPorFecha= function(fechaInicio, fechaFin){
		 
 		return $http.get(config.baseUrl + "/centroPagos/pagos/totales",
		{
			params : {"fechaInicio"	:  fechaInicio ,  
					  "fechaFin"	:  fechaFin
			}
 		});
	}
	
	this.totalesCentroPagosRangoFecha= function(fechaInicio, fechaFin){
		 
 		return $http.get(config.baseUrl + "/centroPagos/pagos/totales/fechas",
		{
			params : {"fechaInicio"	:  fechaInicio ,  
					  "fechaFin"	:  fechaFin
			}
 		});
	}
	
	
	this.consultaCentroPagosCompleto= function(fechaInicio, fechaFin ,tipoPago){
		 
 		return $http.get(config.baseUrl + "/centroPagos/pagos/completos",
		{
			params : {"fechaInicio"	:  fechaInicio ,  
					  "fechaFin"	:  fechaFin,
					  "tipoPago"	:  tipoPago
			}
 		});
	}
	
	this.consultaCentroPagosIncompleto= function(fechaInicio, fechaFin ,tipoPago){
		 
 		return $http.get(config.baseUrl + "/centroPagos/pagos/incompletos",
		{
			params : {"fechaInicio"	:  fechaInicio ,  
					  "fechaFin"	:  fechaFin,
					  "tipoPago"	:  tipoPago
			}
 		});
	}
});
