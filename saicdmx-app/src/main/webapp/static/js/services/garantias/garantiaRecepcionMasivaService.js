angular.module("siidfApp").service("garantiaRecepcionMasivaService",function($http, config){
	
	this.buscarTipoFechas=function(){
		return $http.get(config.baseUrl + "/tipoFechasForGarantiasMasivas");
	};
	
	this.buscarGarantiasRecepcionMasiva = function (valor, op, idTipoFecha) {
		return $http.get(config.baseUrl + "/garantiasRecibirMasiva", 
				{params:{
					"valor": valor,
					"op": op,
					"idTipoFecha": idTipoFecha}});
	};
	
	
	this.buscarGarantiaMasivaFech = function(placaOficial, op, startDate, endDate){
		return $http.get(config.baseUrl + "/garantiaMasivaFech",
				{
					params:{
						"placaOficial": placaOficial,
						"op": op,
						"startDate": startDate,
						"endDate": endDate
					}
				});
		
	};
	
	this.GuardarGarantiasRecepcionIdMasiva = function(garantiaIdMasivasVO){
		return $http.post(config.baseUrl + "/recibirProcesarMasivas", garantiaIdMasivasVO);
	}

	
	this.aplicaConsultaPromesas= function(){
		return $http.get(config.baseUrl + "/aplicaConsultaPromesas");
	};
	
	this.buscarCatalogoDocGarantias=function(){
		return $http.get(config.baseUrl + "/catalogoDocGarantias");
	};

});