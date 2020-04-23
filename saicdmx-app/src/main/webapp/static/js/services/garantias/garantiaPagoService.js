angular.module("siidfApp").service("garantiaPagoService", function ($http, config) {
	
	this.obtenerPerfilCajero = function () {
		return $http.get(config.baseUrl + "/verificarPerfilCaja");
	};
	
	this.cargarParametros = function () {
		return $http.get(config.baseUrl + "/cargarParamGarantiasPorPagar");
	};
	
	this.buscarDetalleGarantia = function(gVO){
		return $http.get(config.baseUrl + "/buscarDetalleGarantiaPorPagar",{params:{"param": gVO.infracNum}});
	};
	
	this.buscarGarantias = function (bVO) {
		return $http.get(config.baseUrl + "/buscarGarantiasPorPagar",{params:{"param": bVO.valor,"tipo": bVO.tipo.codigoString}});
	};
	
	this.obtenerTokenMit= function(){
 		return $http.get(config.baseUrl + "/tokenMit",{responseType:'text'});
	}
	
	this.pagarInfraccionTarjeta= function(transaccionVO, garantiaDetalleVO ){
 		datosPagoVO = {	
				nci				: garantiaDetalleVO.t_nci,
				infracNum 		: garantiaDetalleVO.t_infraccion,
				importe 		: garantiaDetalleVO.t_total,
				transaccionVO	: transaccionVO
		}
		 
 		return $http.put(config.baseUrl + "/pagarGarantiaTarjeta", datosPagoVO );
	}
	
	this.pagarInfraccionDocumento= function(transaccionVO, garantiaDetalleVO, documentoVO){
		datosPagoVO = {	
				nci			          : garantiaDetalleVO.t_nci,
				infracNum 	          : garantiaDetalleVO.t_infraccion,
				importe 	          : garantiaDetalleVO.t_total,
				documentoVO           : documentoVO,
				formaPago		      : "0",
				formaPagoDocumentos   : "DOCUMENTO",
				transaccionVO		  : transaccionVO
		}
		
 		return $http.put(config.baseUrl + "/pagarGarantiaDocumento", datosPagoVO );
	}
	
	this.descargarPDF = function(infracNum,tipovoucher){
		return $http.get(config.baseUrl + "/generarReporteVaucher",{params:{"infracNum": infracNum,"tipoReporte": tipovoucher}});
	}
});