angular.module("siidfApp").service("reporteService", function($http, config,$timeout) {
	
	this.getReportesLista = function () {
		return $http.get(config.baseUrl + "/reportes/getReportesLista");
	};
	
	this.getDataPefilesReportes = function () {
		return $http.get(config.baseUrl + "/reportes/getDataPefilesReportes");
	};
	
	this.getReporte = function (idReporte) {
		return $http.get(config.baseUrl + "/reportes/getReporte",{
					params:{'idReporte' : idReporte}
		}
		);
	};
	
	this.persisteConfigReportePerf = function (data) {
		var voObject = {
				perfiles: data.perfiles,
				reportes : data.reportes,
				interseccion : data.interseccion
		};
		return $http.post(config.baseUrl + "/reportes/persisteConfigReportePerf",voObject);
	};
	
	this.methodConsultaDinamica = function (parametros) {
		return $http.post(config.baseUrl + "/consulta/reporte",parametros);
	};
	
	this.methodDescargaExcel = function (parametros) {
		return $http({
			method : 'POST',
			url : config.baseUrl + "/consulta/excel",
			dataType : "json",
			data : parametros,
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	};
	
	this.save = function(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		//a.click();
		$timeout(function() {
			a.click();
		},100);
	};
	
	/**M�todo que reemplaza carcateres espaciales
	 */
	this.getCleanedString = function (cadena){
			//Lo queremos devolver limpio en minusculas
		   cadena = cadena.toLowerCase();
		   //Definimos los caracteres que queremos eliminar
		   var specialChars = "!@#$^&%*()+=-[]\/{}|:<>?,."; 
		    // Los eliminamos todos
		   for (var i = 0; i < specialChars.length; i++) {
		       cadena= cadena.replace(new RegExp("\\" + specialChars[i], 'gi'), '');
		   }
		   // Quitamos espacios y los sustituimos por _ porque nos gusta mas asi
		   cadena = cadena.replace(/ /g,"");//reemplazar espacios
		   cadena = cadena.replace(/_/g,"");//reemplazar guiones bajos y medios
		   cadena = cadena.replace(/-/g,"");//reemplazar guiones bajos y medios
		   // Quitamos acentos y "�". Fijate en que va sin comillas el primer parametro
		   cadena = cadena.replace(/�/gi,"a");
		   cadena = cadena.replace(/�/gi,"e");
		   cadena = cadena.replace(/�/gi,"i");
		   cadena = cadena.replace(/�/gi,"o");
		   cadena = cadena.replace(/�/gi,"u");
		   cadena = cadena.replace(/�/gi,"n");
		   return cadena;//retornar cadena limpia
	};
	
	/*M�todo para normalizar las cabeceras
	 * esto se hizo para que el ordenamiento
	 * dinamico funcione sin problemas
	 * */
	this.normalizeHeaders = function (listaHeaders){
		 var obj = {};
		 var headReturn = [];
		 var strHead= '';
		 	if(listaHeaders.length > 0){
		      for(var i=0; i < listaHeaders.length; i++){
		        strHead = this.getCleanedString(listaHeaders[i]);
		        obj = {id:strHead,name:listaHeaders[i]};
		        headReturn.push(obj);
		      }
		      //console.log(headReturn);
		    }
		 return headReturn;
	};
	
	this.convertMapKeyAndValue = function(values, keys){
		listReturn = [];
		var object = null;
		for(var x=0; x < values.length; x++){
			object = {};
			for(var y=0; y < values[x].length; y++){
				object[keys[y].id]=values[x][y];
			}
			listReturn.push(object);
		}
		return listReturn;
	};
	
	this.consultaPrevia = function (idParametro, valuesParams){
		return $http.get(config.baseUrl + "/consulta/consultaPrevia",{
				params:{'idParametro' : idParametro,'valores':valuesParams}
			}
		);
	};
	
 });
