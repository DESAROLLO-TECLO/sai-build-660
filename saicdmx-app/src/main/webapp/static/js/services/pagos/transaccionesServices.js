angular.module("siidfApp").service("transaccionesServices", function($http, config) {
	
	this.consultaTrnsacciones= function(){
 		return $http.get(config.baseUrl + "/pagos/consultaTodasTransacciones");
	}

	this.consultaTransaccionesByInfraccion=function(valorBuscado){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesInfraccion",
				{
					params : {"valorBuscado" : valorBuscado}	
		 		});
	}
	
	
	this.consultaTransaccionesByNumReferencia=function(valorBuscado){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesReferencia",
				{
					params : {"valorBuscado" : valorBuscado}	
		 		});
	}
	
	this.consultaTransaccionesFechas=function(fechaInicio,fechaFin,tipoBusqueda){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesFechas",
				{
					params : {"fechaInicio":fechaInicio,
						"fechaFin":fechaFin,"tipoBusqueda":tipoBusqueda}	
		 		});
	}
	
	this.consultaTrnsaccionesCentroPagos=function(tipoBusqueda,valorBusqueda,fechaTransaccion){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesCentroPagos",
				{
					params : {"tipoBusqueda":tipoBusqueda,
							  "valorBusqueda":valorBusqueda,
							  "fechaTransaccion":fechaTransaccion}
		 		});
	}
	
	this.validacionManual=function(transaccionVO){
		transaccionVO={
				tranId : transaccionVO.tranId,
				numOperacion : transaccionVO.numOperacion,
				tranReferencia: transaccionVO.tranReferencia,
				infracNum : transaccionVO.infracNum,
				tranImporte : transaccionVO.tranImporte,
				banNombre : transaccionVO.banNombre,
				tranTarjeta : transaccionVO.tranTarjeta,
				tranNombre : transaccionVO.tranNombre,
				tranRespuesta : transaccionVO.tranRespuesta,
				tranNumAutoriza : transaccionVO.tranNumAutoriza,
				tranFecha : transaccionVO.tranFecha,
				fechaValidacion : transaccionVO.fechaValidacion,
				voucher : transaccionVO.voucher
		}
		return $http.put(config.baseUrl + "/pagos/validacionManual",transaccionVO);
	}
});