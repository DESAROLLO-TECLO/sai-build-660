angular.module("siidfApp").service("garantiaSinProcesarService", function ($http, config) {	
	
	this.buscarGarantias = function (valor) {
		return $http.get(config.baseUrl + "/garantias", 
				{params:{"valor": valor}});
	};
//Inicia garantias recepcion
	
	this.validarUsuario = function (){
		return $http.get(config.baseUrl + "/verificarPerfilCaja");
	};
	
	this.buscarGarantiasRecepcion = function (valor, op) {
		return $http.get(config.baseUrl + "/garantiasRecibir", 
				{params:{
					"valor": valor,
					"op": op}});
	};
	
	
	this.buscarGarantiasRecepcionId = function (garantiaId) {
		return $http.get(config.baseUrl + "/garantiaInfo", 
				{params:{"garantiaId": garantiaId}});
	};
	
	this.GuardarGarantiasRecepcionId = function (garantiaId,recibir,observacion) {
				return $http.get(config.baseUrl + "/recibirProcesar",{params:{"garantiaId": garantiaId, "recibir":recibir, "observaciones": observacion}});
	
	};
	
	this.buscarPDF = function(garantiaID){
		
		
		 return $http({     
		        method: 'GET',
		        url: config.baseUrl + "/generarReporteGarantiaRecepcion",
		        params: {"garantiaID": garantiaID},
		        dataType: "json",
		        header :{ "Content-type": "application/json",
		        			"Accept"    : "pdf"
		        },
		        responseType: 'arraybuffer'
		    });
	};
	//termina garantia RECEPCION
	/*iNICIA GARANTIA ENTREGA*/
	this.buscarPorOpcionEntrega = function(dato,opcionBusqueda){
			return $http.get(config.baseUrl + "/buscarPorOpcionGarantiaEntrega",
				{params:{"dato": dato,"opcionBusqueda": opcionBusqueda}});
	
	};
	
	this.guardarGarantiaEntregaId = function(garantiaId,observacion){
	
		return $http.get(config.baseUrl + "/guardarGarantiasEntrega",
			{params:{"garantiaId": garantiaId,"observacion": observacion}});

};

this.buscarPDFEntrega = function(garantiaID){
	
	
	 return $http({     
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteGarantiaEntrega",
	        params: {"garantiaID": garantiaID},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "pdf"
	        },
	        responseType: 'arraybuffer'
	    });
};

/*FINALIZA GARANTIA ENTREGA*/
/*Inicia garantia Consulta*/
this.buscarPorOpcionParametroA = function(opcionParametro, parametro){
	

	return $http.get(config.baseUrl + "/consultaDeGarantias",
			{params:{"estatusParametro":opcionParametro, "txtParametro":parametro}});	
};

this.bucarCantGaranLote = function(idLote){
	return $http.get(config.baseUrl + "/consultaListaGarantiaLotes",{
		params:{"idlote": idLote}
	});
};

this.buscarPorOpcionParametroB = function(opcionTipoFecha,opcionTipoProceso,fechaInicio, fechaFin){
	

	return $http.get(config.baseUrl + "/consultaDeGarantiasOp",
			{params:{"tipoFecha": opcionTipoFecha,"tipoProceso": opcionTipoProceso, "fechaInicio":fechaInicio, "fechaFin":fechaFin}});	
};


this.buscarTipoProceso = function(){
	
	return $http.get(config.baseUrl + "/buscarEstatusProceso");
	
};

this.descargarVaucher = function(infracNum,tipoReporte){

	 return $http({     
	        method: 'GET',
	        url: config.baseUrl + "/generarReporteVaucher",
	        params: {"infracNum": infracNum, "tipoReporte":tipoReporte},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "pdf"
	        },
	        responseType: 'arraybuffer'
	    });
	
};



this.buscarReporteGral = function(fechaInicio,fechaFin){

	return $http.get(config.baseUrl + "/consultarReporteGeneral",
			{params:{"fechaInicio":fechaInicio, "fechaFin":fechaFin}});	
};

this.descargaDeExcel = function(fechaInicio,fechaFin){

	
	 return $http({     
	        method: 'GET',
	        url: config.baseUrl + "/recuperarArchivo",
	        params: {"fechaInicio": fechaInicio, "fechaFin":fechaFin},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
		
	};

	this.generarExcel = function(empPlaca,op) {
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/garantias/generarExcel",
	        params: {
	        	"empPlaca":empPlaca,
	        	"op":op
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
	
	this.generarExcelEntrega = function(dato,opcionBusqueda){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/garantias/generarExcelEntrega",
	        params: {
	        	"dato":dato,
	        	"opcionBusqueda":opcionBusqueda
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
	this.generarExcelMasiva = function(
			reportePlacaOficial,
			reporteConPromesaPago,
			reporteTipoFecha,
			reporteFechaIni,
			reporteFechaFin
			){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/garantias/generarExcelMasiva",
	        params: {
	        	"reportePlacaOficial":reportePlacaOficial,
	        	"reporteConPromesaPago":reporteConPromesaPago,
	        	"reporteTipoFecha":reporteTipoFecha,
				"reporteFechaIni":reporteFechaIni,
				"reporteFechaFin":reporteFechaFin
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
	this.generarExcelConsultaA = function(opcionParametro, parametro){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/garantias/generarExcelConsultaA",
	        params: {
	        	"estatusParametro":opcionParametro, 
	        	"txtParametro":parametro
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
	this.generarExcelConsultaB = function(opcionTipoFecha, opcionTipoProceso, fechaInicio, fechaFin){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/garantias/generarExcelConsultaB",
	        params: {
	        	"tipoFecha": opcionTipoFecha, 
	        	"tipoProceso": opcionTipoProceso, 
	        	"fechaInicio":fechaInicio, 
	        	"fechaFin":fechaFin
	        },
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
this.buscarPDFMasiva = function(idGarantiaInd, totalGarMasiva, iDLote){
		
		
		return $http({     
			   method: 'GET',
			   url: config.baseUrl + "/generarReporteGarantiaRecepcionMasiva",
			   params: {
				   "idGarantiaInd":idGarantiaInd,
				   "totalGarMasiva":totalGarMasiva,
				   "iDLote": iDLote
},
			   dataType: "json",
			   header :{ "Content-type": "application/json",
						   "Accept"    : "pdf"
			   },
			   responseType: 'arraybuffer'
		   });
   };

});