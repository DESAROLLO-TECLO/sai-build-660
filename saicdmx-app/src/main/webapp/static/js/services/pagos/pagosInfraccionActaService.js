
angular.module("siidfApp").service("pagosInfraccionActaService", function($http, config) {
	
	var mapObjects ={ listaInfraccionesActa :0 ,idInfraccionDeposito:0 ,botonLiberacion:0, infraccionDepositoVO:0};
	 
	this.setMapObjects = function(object) {
		mapObjects = object;
 	};
	
	this.getMapObjects = function() {
		return mapObjects;
	}
	
	
	this.tipoBusquedaInfraccionesPago= function(){
 		return $http.get(config.baseUrl + "/pagos/tipoBusqueda");
	}
	
	this.consultaInfraccionesActa= function(infraccionDepositoVO){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/acta",
		{
			params : {"tipoParametro"	:  infraccionDepositoVO.tipoParametro ,  
					  "valorParametro"	:  infraccionDepositoVO.valorParametro
			}
 		});
	}
 
	this.consultaInfraccionesPorPagar= function(pagoInfraccionVO){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/porpagar",
		{
			params : {"nci"				:  pagoInfraccionVO.nci ,  
					  "numInfraccion"	:  pagoInfraccionVO.infraccion
			}	
 		});
	}
	this.consultaInfraccionesPorPagaryPagadasAlDia= function(infraccionPorDia){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/porPagaryPagadasAlDia",
		{
			params : {"nci"				:  infraccionPorDia.nci ,  
					  "numInfraccion"	:  infraccionPorDia.infraccion,
					  "fecha"			:  infraccionPorDia.fecha ,  
					  "valor"			:  infraccionPorDia.valor ,  
					  "parametro"		:  infraccionPorDia.parametro
			}	
 		});
	}
	
	this.pagarInfraccionDocumento= function( datosPagoVO ){
		 
		datosPagoVO = {	
				nci			          : datosPagoVO.infracNumCtrl,
				infracNum 	          : datosPagoVO.infracNum,
				importe 	          : datosPagoVO.infracTotalPagar,
				documentoVO           : datosPagoVO.documentoVO,
 				formaPago             : datosPagoVO.formaPago,
				formaPagoDocumentos   : datosPagoVO.formaPagoDocumentos,
				transaccionVO		  : datosPagoVO.transaccionVO


		}
		
 		return $http.put(config.baseUrl + "/pagos/infraccion/documento", datosPagoVO );
	}
	 
	this.pagarInfraccionEfectivo= function( datosPagoVO ){
		 
		datosPagoVO = {	
				nci			: datosPagoVO.infracNumCtrl,
				infracNum 	: datosPagoVO.infracNum,
				importe 	: datosPagoVO.infracTotalPagar
		}
		
 		return $http.put(config.baseUrl + "/pagos/infraccion/efectivo", datosPagoVO );
	}
	
	this.pagarInfraccionTarjeta= function( datosPagoVO ){
 		datosPagoVO = {	
				
				nci			: datosPagoVO.infracNumCtrl,
				infracNum 	: datosPagoVO.infracNum,
				importe 	: datosPagoVO.infracTotalPagar,
				transaccionVO	: datosPagoVO.transaccionVO
		}
		
 		return $http.put(config.baseUrl + "/pagos/infraccion/tarjeta", datosPagoVO );
	}
	
	this.pagarInfraccionActa = function( datosPagoVO ){
 		datosPagoVO = {	
				
 				nci			          : datosPagoVO.infracNumCtrl,
				infracNum 	          : datosPagoVO.infracNum,
				importe 	          : datosPagoVO.infracTotalPagar,
				documentoVO           : datosPagoVO.documentoVO,
 				formaPago             : datosPagoVO.formaPago,
 				documentoActa		  : datosPagoVO.documentoActa,
				transaccionVO		  : datosPagoVO.transaccionVO

		}
		
 		return $http.put(config.baseUrl + "/pagos/infraccion/acta", datosPagoVO );
	}
});
