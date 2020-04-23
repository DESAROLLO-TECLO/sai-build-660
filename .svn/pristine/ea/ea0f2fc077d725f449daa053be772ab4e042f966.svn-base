angular.module("siidfApp").service("remisionaDepositoService", function ($http, config) {	

	var depositoUsuario = 0;
	
	this.setDeposito = function(depo) {
		depositoUsuario = depo;
 	};
	
	this.getDeposito = function() {
		return depositoUsuario;
	}
	
	this.buscarConInfraccion = function(opcion,valor){
		return $http.get(config.baseUrl + "/buscarInfraccion", {
			params : {
				"opcion" : opcion,
				"valor" : valor
			}
	});
		
	};
	
	//buscar upd para registrar ingreso
	this.buscarUpdPorId = function (id) {
		//return auxiliarVO;
		return $http.get(config.baseUrl + "/updIngresoDeposito/" + id);
	};
	
	
	// Alta de ingreso de infraccion
	this.altaIngresoInfraccion = function (infraccionCompletaVO) {
		return $http.post(config.baseUrl + "/registroConInfraccion", infraccionCompletaVO);
	};
	
	
	this.buscarOficialPorPlaca = function(infrac_placa_empl){
		return $http.get(config.baseUrl + "/consultarOficialPlaca", {
			params : {
				"infrac_placa_empl" : infrac_placa_empl
			}
	});
	};

	// Imprimir Recibo Resguardo
	this.ImprimirResguardoPDF = function(infracNum){
	        return $http({
		          	method: 'GET',
		          	url: config.baseUrl + "/imprimirResguardo",
			        params: {"infraccionNum": infracNum},
			        header :{ "Content-type": "application/json",
	        			"Accept"    : "pdf"
		        	},
			        responseType: 'arraybuffer'
		      });
	    
	};
	
	// Imprimir Recibo Arrastre
	this.ImprimirArrastrePDF = function(infracNum){
	        return $http({
		          	method: 'GET',
		          	url: config.baseUrl + "/imprimirArrastre",
		          	params: {"infraccionNum": infracNum},
			        header :{ "Content-type": "application/json",
			        			"Accept"    : "pdf"
			        },
			        responseType: 'arraybuffer'
		      });
	    
	};
	
	this.valorArticuloSancion = function(infrac_num){
		return $http.get(config.baseUrl + "/articuloSancion", {
			params : {
				"infrac_num" : infrac_num
			}
		});
	};
	
	this.valorVehiculo = function(infrac_num){
		return $http.get(config.baseUrl + "/vehiculo", {
			params : {
				"infrac_num" : infrac_num
			}
		});
	};
	
	this.valorSecAgrupArrasEmp = function(infrac_num){
		return $http.get(config.baseUrl + "/SecAgrupArrasEmp", {
			params : {
				"infrac_num" : infrac_num
			}
	});
	};
	
	this.valorGruaConce = function(infrac_num){
		return $http.get(config.baseUrl + "/gruaConce", {
			params : {
				"infrac_num" : infrac_num
			}
		});
	};
	
	
	// Alta de ingreso Sin  infraccion
	this.altaIngresoSinInfraccion = function (sinInfraccionCompletaVO) {
		return $http.post(config.baseUrl + "/registroSinInfraccion", sinInfraccionCompletaVO);
	};
	
//	// Alta de ingreso Sin  infraccion
//	this.altaIngresoSinInfraccionANS = function (InfraccionCompletaVO) {
//		return $http.post(config.baseUrl + "/registroSinInfraccionANS", InfraccionCompletaVO);
//	};
	
	// buscar remisiones
	this.buscarRemisiones = function(opcion,valor){
		return $http.get(config.baseUrl + "/buscarRemisiones", {
			params : {
				"opcion" : opcion,
				"valor" : valor				
			}
	});
	};
	
	this.mostarDatosPorNCI = function (infrac_num_ctrl) {
		return $http.get(config.baseUrl + "/mostarDatosNCI",  {
			params : {
				"infrac_num_ctrl" : infrac_num_ctrl,
		
			}
	   });
	};
	
	//buscar upd para registrar ingreso
	this.buscarInfraporNCI = function (infrac_num_ctrl) {
		return $http.get(config.baseUrl + "/infracIngresoNCI",  {
			params : {
				"infrac_num_ctrl" : infrac_num_ctrl,
		
			}
	   });
	};
	
	
	
	
	/*INICIA TRASLADO DE VEHICULO */
	
	this.tipoBusquedaCombo = function(){
		return $http.get(config.baseUrl + "/tipoBusquedaCombo");
	};
	
	this.depOrgCombo = function(){
		return $http.get(config.baseUrl + "/depOrgCombo");
	};
	
//	this.buscarTraslado = function(tipoBusq, depOrig, depDesti, datoBusq){
//		return $http.get(config.baseUrl + "/buscarTrasladoEnCurso",
//				{params:{"tipoBusq": tipoBusq, "depOrig" : depOrig , 
//					"depDesti": depDesti, "datoBusq" : datoBusq}});
//	};
	this.buscarTraslado = function(tipoBusq, datoBusq){
		return $http.get(config.baseUrl + "/buscarTrasladoEnCurso",
				{params:{"tipoBusq": tipoBusq, "datoBusq" : datoBusq}});
	};
	this.cargaDatoIngreso = function(numInfraccion){
		return $http.get(config.baseUrl + "/buscarInfoTrasladoVehiculo",
				{params:{"numInfraccion": numInfraccion}});
	};
	
//	this.getTiempoTraslado = function(tiempo){
//		return $http.get(config.baseUrl + "/getTiempoTraslado",
//				{params:{"tiempo": tiempo}});
//	};
//	this.guardarVOIngresoTraslado = function (parametroVO){
//		var json =  angular.toJson(parametroVO);
////		return $http.get(config.baseUrl + "/guardarSalidaVehiculo",{params:{"parametroVO": json, "contenido" : contenido, "nombreImg" : nombreImg, "tipoImg" : tipoImg }});
//		return $http.get(config.baseUrl + "/guardarVOIngresoTraslado",{params:{"parametroVO": json}});
//	};
	
	this.guardarVOIngresoTraslado = function (parametroVO,	 files, tmno){
		
		var json =  angular.toJson(parametroVO);
		var json2 =  angular.toJson(files);
		return $http.get(config.baseUrl + "/guardarVOIngresoTraslado",{params:{"parametroVO": json, "files" : json2, "tmno" : tmno}});

	};
	
	this.mostrarEvidencias = function(idSalidas){
		return $http.get(config.baseUrl + "/mostrarEvidencia",
				{params:{"idSalidas": idSalidas}});
	}
	
	
	this.bajarExpedientes = function (idSalidas) {
		return $http({     
	        method: 'GET',
	        url: config.baseUrl + "/descargaEvidencia",
	        params: {"idSalidas":idSalidas},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "jpg"
	        },
	        responseType: ''
	    });
	};	
	
	/*INICIA LA PERSISTENCIA DE DATOS PARA TRASLADO*/
	
	var mapObjectsTraslado ={
			opcionSeleccionada: "", 
			ListaOpciones: [], 
			ValorIngresado : "", 
			listaResultados: []
			};
	
	this.setMapObjectsTraslado = function(object) {
		mapObjectsTraslado = object;
 	};
	
	this.getMapObjectsTraslado = function() {
		return mapObjectsTraslado;
	}
	
	/**/
	var estatusMomentoTraslado = {
	                      accionBusquedaValido : false
						}; 
	
	this.setEstatusMomentoTraslado = function(object) {
		estatusMomentoTraslado = object;
 	};
	
	this.getEstatusMomentoTraslado = function() {
		return estatusMomentoTraslado;
	}
	
	this.consultarPlacaOficialById = function(infrac_placa_empl){
		return $http.get(config.baseUrl + "/getInfracPlaca",
				{params:{"infrac_placa_empl": infrac_placa_empl}});
	}
	
	

});