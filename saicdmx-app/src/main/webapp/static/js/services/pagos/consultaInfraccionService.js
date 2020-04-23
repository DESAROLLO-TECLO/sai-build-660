angular.module("siidfApp").service("consultaInfraccionService", function($http, config) {
   
	this.tipoBusquedaInfraccionesPago= function(){
 		return $http.get(config.baseUrl + "/pagos/tipoBusqueda");
	}
 
	this.consultaInfraccionesPagadas = function( infraccionDepositoVO ){
		 
 		return $http.get(config.baseUrl + "/pagos/infracciones/pagados",
		{
 			params : {"parametro"	:  infraccionDepositoVO.tipoParametro ,  
				  	  "valor"		:  infraccionDepositoVO.valorParametro
		}
 		});
	}
 

	this.downloadVoucher = function( infracNum, tipoReporte){

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
	
	this.actualizarApiCartoDB = function( ){
		var sql_statement="SELECT%20*%20FROM%20cartodb_query";
//		
//		console.log($http.get(config.cartoDbUrl + sql_statement +'&api_key='+config.apiKey))

//		 return $http({     
//		        method: 'GET',
//		        url: config.cartoDbUrl ,
//		       
// 		        headers: {
//		            'Content-Type': 'application/json; charset=UTF-8'
//		        }
// 		    });
		 var liga=config.cartoDbUrl+ '?q='+ sql_statement +'&api_key='+config.apiKey;
		 return $http({     
		        method: 'GET',
		        url: liga ,
		       
		        headers: {
		            'Content-Type': 'application/json; charset=UTF-8',
		            'Access-Control-Allow-Headers' : 'X-Requested-With, X-Prototype-Version, X-Auth-Token',
 		        }
		    });
//		 console.log(url);
//		 return $http.get(url);

		 
//		 return $http.jsonp(config.cartoDbUrl+ '?q='+ sql_statement +'&api_key='+config.apiKey);
//		
//		console.log(config.cartoDbUrl + sql_statement +'&api_key='+config.apiKey)
//		console.log($http.get(config.cartoDbUrl + sql_statement +'&api_key='+config.apiKey))
//		return $http.get(config.cartoDbUrl + sql_statement +'&api_key='+config.apiKey );
		  
	};
	
	
	 
});
