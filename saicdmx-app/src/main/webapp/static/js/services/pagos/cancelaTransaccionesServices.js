angular.module("siidfApp").service("cancelaTransaccionesServices", function($http, config) {
	
	this.consultaTransaccionesCancelacion=function(){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesCancelacion");
	}
	
	this.consultaTransaccionesByParametro=function(tipoBusqueda,valorBuscado){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesCancelacionByParametro",
										
				{
					params : {"tipoBusqueda" : tipoBusqueda,
								"parametro" : valorBuscado }	
		 		});
	}
	
	this.cancelacionTransacciones=function(transaccionVO){
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
				voucher : transaccionVO.voucher,
				cajaId : transaccionVO.cajaId
		}
		
		return $http.put(config.baseUrl + "/pagos/cancelacionTransaccion",transaccionVO);
	}
	
});