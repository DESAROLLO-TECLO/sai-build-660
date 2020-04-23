
angular.module("siidfApp").service("pagosInfraccionService", function($http, config) {
   
	var mapObjects ={listaInfracciones :0 ,idInfraccionDeposito:0 ,botonLiberacion:0, infraccionDepositoVO:0, rowsPerPage:0};
	 
	
	this.setMapObjects = function(object) {
		mapObjects = object;
 	};
	
	this.getMapObjects = function() {
		return mapObjects;
	}
	 
	this.tipoBusquedaInfraccionesPago= function(){
 		return $http.get(config.baseUrl + "/pagos/tipoBusqueda");
	}
	
	this.consultaInfraccionesDeposito= function(infraccionDepositoVO){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/deposito",
		{
			params : {"tipoParametro"	:  infraccionDepositoVO.tipoParametro ,  
					  "valorParametro"	:  infraccionDepositoVO.valorParametro
			}
 		});
	}
	
	this.consultaInfraccionesDepositoDigitaliz= function(tipoParametro, valorParametro, nci, placa){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/deposito",
		{
			params : {"tipoParametro"	:  tipoParametro ,  
					  "valorParametro"	:  valorParametro,
					  "nci"				: nci,
					  "placa" 			: placa
			}
 		});
	}
 
	this.consultaInfraccionesPorPagarService= function(pagoInfraccionVO){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/porpagar",
		{
			params : {"nci"				:  pagoInfraccionVO.nci ,  
					  "numInfraccion"	:  pagoInfraccionVO.infraccion
			}	
 		});
	}
	
	this.consultaInfraccionesPagadasPorDia= function( infraccionDepositoVO ){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/pagadasAlDia",
		{
 			params : {"parametro"	:  infraccionDepositoVO.tipoParametro ,  
				  	  "valor"		:  infraccionDepositoVO.valorParametro,
				  	  "fecha"		:  infraccionDepositoVO.fecha
		}
 		});
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
				nci				: datosPagoVO.infracNumCtrl,
				infracNum 		: datosPagoVO.infracNum,
				importe 		: datosPagoVO.infracTotalPagar,
				transaccionVO	: datosPagoVO.transaccionVO
		}
		 
 		return $http.put(config.baseUrl + "/pagos/infraccion/tarjeta", datosPagoVO );
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
	
	/***** Consulta pago en linea *****/
	this.consultaPagoEnLinea= function(infracNum){
 		return $http.get(config.baseUrl + "/pagos/infraccion/consultaPagoEnLinea",
		{
			params : {"infracNum"	:  infracNum 				
			}
 		});
	}
	
	
});
