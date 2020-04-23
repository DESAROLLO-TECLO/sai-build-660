angular.module("siidfApp").service("salidaVehiculoService", function($http, config) {
	/*mapObject para los objetos que permiten cargar la pagina con sus valores anteriores*/
	var mapObjects ={
			opcionSeleccionada: "", 
			ListaOpciones: [], 
			ValorIngresado : "", 
			listaResultados: []
			};
	
	this.setMapObjects = function(object) {
		mapObjects = object;
 	};
	
	this.getMapObjects = function() {
		return mapObjects;
	}
	
	/**/
	 var estatusMomento = {
	                      accionModal : false,
	                      accionRecepcion : false
						}; 
	
	this.setEstatusMomento = function(object) {
		estatusMomento = object;
 	};
	
	this.getEstatusMomento = function() {
		return estatusMomento;
	}
	
	this.filtroBusqGral = function () {
		return $http.get(config.baseUrl + "/filtroBusqGral");
	};
	
	this.buscarSalidaVehiculo = function (parametroBusq, valorBusq) {
		return $http.get(config.baseUrl + "/salidaVehiculoBusqueda",
		{params:{"parametroBusq": parametroBusq, "valorBusq": valorBusq}});
	};
	
	this.busquedaInfoInfrac = function (numInfrac) {
		return $http.get(config.baseUrl + "/busquedaInfoInfrac",
		{params:{"numInfrac": numInfrac}});
	};
	
	this.filtroTipoSalida = function (){
		return $http.get(config.baseUrl + "/filtroTipoSalida");
	};
	
	this.comboAdjudDestino = function(){
		return $http.get(config.baseUrl + "/comboAdjunDestino");
	};
	
	this.comboFaseCompacta = function(){
		return $http.get(config.baseUrl + "/comboFaseCompacta");
	};
	
	this.comboDepDestino = function(depOrigen){
		return $http.get(config.baseUrl + "/comboDepDestino",
				{params:{"depOrigen": depOrigen}});
	};

	this.validaPlaca = function(infracNum){
		return $http.get(config.baseUrl + "/validaPlaca",
				{params:{"infracNum": infracNum}});
	};
	
	
	this.consultarPlacaOficial = function(placaOficial){
		return $http.get(config.baseUrl + "/validaPlacaOficial",
				{params:{"placaOficial": placaOficial}});
	};
	
	this.guardarSalida = function (parametroVO,	 files, tmno){
	
		var json =  angular.toJson(parametroVO);
		var json2 =  angular.toJson(files);
		return $http.get(config.baseUrl + "/guardarSalidaVehiculo",{params:{"parametroVO": json, "files" : json2, "tmno" : tmno }});
//		return $http.get(config.baseUrl + "/guardarSalidaVehiculo",{params:{"parametroVO": json}});
	};
	
	this.validarPerfil = function(){
		return $http.get(config.baseUrl + "/validaUserPerfilSalida");
	};
	
	/*INICIA CODIGO DE CONSULTA BUSQUEDA*/
	
	this.comboTipoBusq = function(){
		return $http.get(config.baseUrl + "/filtroComboTipoBusqueda");
	};
	
	this.comboTipoOrden = function(){
		return $http.get(config.baseUrl + "/filtroComboTipoOrden");
	};
	
	this.tipoSalida = function(){
		return $http.get(config.baseUrl + "/filtroComboTipoSalida");
	};
//	 "ordenarPor" : ordenarPor,
	this.buscarSalidaVehiculoConsul = function(tipoBusq,valorCombo, 
			tipoBusqSalida, fechaInicio, fechaFin, valorBusquedaTipo){
		return $http.get(config.baseUrl + "/buscarSalidaVehiculo",
				{params:{"tipoBusq": tipoBusq, "valorCombo" : valorCombo , 
					"tipoBusqSalida": tipoBusqSalida, "fechaInicio" : fechaInicio, "fechaFin": fechaFin, "valorBusqueda" : valorBusquedaTipo}});
	};
	
	this.mostrarEvidencias = function(idSalidas, numInfrac, tipo){
	return $http.get(config.baseUrl + "/mostrarEvidencia",
			{params:{"idSalidas": idSalidas, "numInfrac": numInfrac, "tipo": tipo}});
	};


	this.bajarExpedientes = function (idSalidas, numInfrac, tipo) {
		return $http({     
	        method: 'GET',
	        url: config.baseUrl + "/descargaEvidencia",
	        params: {"idSalidas":idSalidas,"numInfrac":numInfrac, "tipo" : tipo},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "jpg"
	        },
	        responseType: ''
	    });
	};	
	
	/*INICIA TRASLADO DE VEHICULO */
	
//	this.tipoBusquedaCombo = function(){
//		return $http.get(config.baseUrl + "/tipoBusquedaCombo");
//	};
//	
//	this.depOrgCombo = function(){
//		return $http.get(config.baseUrl + "/depOrgCombo");
//	};
	

//	this.buscarTraslado = function(tipoBusq, datoBusq){
//		return $http.get(config.baseUrl + "/buscarTrasladoEnCurso",
//				{params:{"tipoBusq": tipoBusq, "datoBusq" : datoBusq}});
//	};
//	this.cargaDatoIngreso = function(numInfraccion){
//		return $http.get(config.baseUrl + "/buscarInfoTrasladoVehiculo",
//				{params:{"numInfraccion": numInfraccion}});
//	};
	
//	this.getTiempoTraslado = function(tiempo){
//		return $http.get(config.baseUrl + "/getTiempoTraslado",
//				{params:{"tiempo": tiempo}});
//	};
	
//	this.guardarVOIngresoTraslado = function (parametroVO){
//		var json =  angular.toJson(parametroVO);
//		return $http.get(config.baseUrl + "/guardarVOIngresoTraslado",{params:{"parametroVO": json}});
//	};
	

	
	/*INICIA LA PERSISTENCIA DE DATOS PARA TRASLADO*/
	
//	var mapObjectsTraslado ={
//			opcionSeleccionada: "", 
//			ListaOpciones: [], 
//			ValorIngresado : "", 
//			listaResultados: []
//			};
	
//	this.setMapObjectsTraslado = function(object) {
//		mapObjectsTraslado = object;
// 	};
//	
//	this.getMapObjectsTraslado = function() {
//		return mapObjectsTraslado;
//	}
	
	/**/
//	var estatusMomentoTraslado = {
//	                      accionBusquedaValido : false
//						}; 
//	
//	this.setEstatusMomentoTraslado = function(object) {
//		estatusMomentoTraslado = object;
// 	};
//	
//	this.getEstatusMomentoTraslado = function() {
//		return estatusMomentoTraslado;
//	}
	
	
	/*INICIA CATALOGOS*/
	
	
	
	this.comboCatSalidas = function(){
		return $http.get(config.baseUrl + "/filtroComboCatSalidas");
	};
	this.comboTypeSearchCompacta = function(){
		return $http.get(config.baseUrl + "/filtroComboTypeSearchCompacta");
	};
	this.comboTypeSearchAdjudica = function(){
		return $http.get(config.baseUrl + "/filtroComboTypeSearchAdjudica");
	};
	
	
	this.searchBookCat = function (catVO){
		var json =  angular.toJson(catVO);
		return $http.get(config.baseUrl + "/busquedaCatSalidas",{params:{"catVO": json}});
	};
	
	this.searchCatByIdCat = function(IdCat, typeCat){
		return $http.get(config.baseUrl + "/getCatByIdCat",{params:{"idCat": IdCat, "tipoCat" : typeCat}});
	};
	
	this.comboActiveInactive = function(){
		return $http.get(config.baseUrl + "/filtroComboActiveInactive");
	};
	
	this.updateBookCat = function (catVO, tipoCat){
		var json =  angular.toJson(catVO);
		return $http.get(config.baseUrl + "/updateCatSalidas",{params:{"catVO": json, "tipoCat" : tipoCat}});
	};
	
	this.newBookCat = function (catVO, tipoCat){
		var json =  angular.toJson(catVO);
		return $http.get(config.baseUrl + "/insertCatSalidas",{params:{"catVO": json, "tipoCat" : tipoCat}});
	};
	
	
	
	
	
 });