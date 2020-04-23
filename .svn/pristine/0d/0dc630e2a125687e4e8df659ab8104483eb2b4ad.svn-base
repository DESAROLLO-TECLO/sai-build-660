angular.module('siidfApp').controller('reasignacionLCMasivaController', function($scope, $timeout, $filter, $route, lineaCapturaMasivaService, ModalService, catalogoService) {
		var d = moment().format("DD/MM/YYYY"); 
		$scope.reasignadoVO={
				file: null,
				fEmision: d,
				cantidadPendientes:0
		}
		$scope.showPendiente = false;
		$scope.resultadoVO = false;
		
		$scope.opciones={
			reasignar:null,
			cancelar:null
		}
		
		/*MODAL*/
		$scope.showAviso = function(messageTo, action) {
			ModalService.showModal({
				templateUrl: 'views/templatemodal/templateModalAviso.html',
				controller: 'mensajeModalController',
				inputs:{ message: messageTo}
			}).then(function(modal) {
				modal.element.modal();
				modal.close.then(function(){
					action();
				});
			});
	    };
		
	    buscaTipoDescuento = function(){
			catalogoService.buscaTipoDescuento(true).success(function(data) {
				$scope.tipoDescuento = data;
				$scope.reasignadoVO.tipoDescuento = $scope.tipoDescuento[0].codigo;
			}).error(function(data){
				$scope.error = data;
				$scope.tipoDescuento = {};
			});
		}
	    
	    $scope.validarArchivoPendiente = function(){
	    	//Consumo de Servicio
			lineaCapturaMasivaService.validarReasignacionesPendientes().success(function(data){
				if(data.respuesta == 0){
					if(data.code == 1){
						$scope.showPendiente = true;
					} else {
						$scope.showPendiente = true;
						$("#reasignar").attr('disabled', 'disabled');
						$("#cancelar").attr('disabled', 'disabled');
						$timeout(function(){
							$route.reload();
						    },10000);
					}
					$scope.reasignadoVO.cantidadPendientes = data.cantidad;
				}else{
					
				}
			}).error(function(data) {
				
			});
	    };
	    
		$scope.cargarArchivoReasignado = function(){
			if($scope.formReasigMasivo.$invalid){
    			requiredFields();
    		}else{
    			//Consumo de Servicio
    			lineaCapturaMasivaService.cargarFolioInfracciones(
    				$scope.reasignadoVO.file, 
    				$scope.reasignadoVO.fEmision, 
    				$scope.reasignadoVO.tipoDescuento
    			).success(function(data){
    				if(data.respuesta){
    					if(data.status == 100){
    						var tmp = "";
        					for(var a = 0; a < data.incidencia.length; a++)
        					{
        						tmp+= data.incidencia[a] + "\r\n";
        					}
        					formarArchivo("Incidencias", tmp);
        					$scope.showAviso(data.message);
    					}
    					$route.reload();
    				}else{
    					if(data.status == 500){
	    					var tmp = "";
	    					for(var a = 0; a < data.incidencia.length; a++)
	    					{
	    						tmp+= data.incidencia[a] + "\r\n";
	    					}
	    					formarArchivo("Incidencias", tmp);
    					}
    					$scope.showAviso(data.message);
    				}
    			}).error(function(data) {
    				$scope.showAviso("No se pudo cargar el archivo");
    			});
    		}
		}
		
		$scope.getFileReasigna = function(e){
			$scope.$apply(function () {
	        	$scope.reasignadoVO.file = e.files[0];
	        	if($scope.formReasigMasivo.$invalid){
	    			requiredFields();
	    		}else{
	    			
	    		}
	        });
		}
		
		requiredFields = function(){
			angular.forEach($scope.formReasigMasivo.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
			});
		}
		
		formarArchivo = function(nombre, archivo){
			var textFile = null, 
			makeTextFile = function(archivo){
				var data = new Blob([archivo], {type: 'text/plain'});
				if (textFile !== null) {
					window.URL.revokeObjectURL(textFile);
				}

				textFile = window.URL.createObjectURL(data);

				return textFile;
			};
			
			if (navigator.appVersion.toString().indexOf('.NET') > 0){
				var data = new Blob([archivo], {type: 'text/plain'});
				window.navigator.msSaveBlob(data, nombre);
			}
		    else
			{
				var url = window.URL || window.webkitURL || window.msURL;
				var a         = document.createElement('a');
				a.href        = makeTextFile(archivo); 
				a.download    = nombre;
				a.target      = '_blank';
				document.body.appendChild(a);
				a.click();
			}
		}
		
		$scope.reasignarArchivo = function(){
			//Consumo de Service
			lineaCapturaMasivaService.reasignarLote().success(function(data){
				if(data.respuesta){
					if(data.respuesta2 != ""){
						$scope.showAviso("Se han reasignado los folios de manera exitosa.\n Algunos no se han reasignado, por ser cancelados o inexistendes.", 
							function(){
								$scope.showPendiente = false;
								genArchivoInfCan(data.respuesta2);
							});
					}else if(data.respuesta2 == ""){
						$scope.showAviso("Se han reasignado los folios de manera exitosa", function(){
							$scope.showPendiente = false;
						});
					}
				}else{
					$scope.showAviso("No se ha podido realizar la acción", function(){
						$route.reload();
					});
				}
			}).error(function(data) {
				$scope.showAviso(data.message, function(){
					$route.reload();
				});
			});
		}
		
		$scope.cancelarArchivo = function(){
			//Consumo de Service
			lineaCapturaMasivaService.cancelarLote().success(function(data){
				if(data.respuesta){
					$scope.showPendiente = false;
					$scope.showAviso(data.message);
				}else{
					
				}
			}).error(function(data) {
				$showAviso("No ha sido posible cancelar el archivo, por favor inténtelo más tarde");
			});
			
		}
		
		$scope.nuevaReasignacion = function(){
			//Consumo de Service
			$scope.showAviso("Se reasignaron de manera exitosa");
		}
		
		$scope.compara_fechas = function(){
			//alert(d + " - " + e.value);
			fecha  = $scope.reasignadoVO.fEmision;
			fecha2 = d;
			// 27/02/2018
			//var xDay	= fecha.substring(0, 2);
			//var xMonth	= fecha.substring(1, 2);
			//var xYear	= fecha.substring(2, 4);
			
			//var yDay	= fecha2.substring(0, 2);
			//var yMonth	= fecha2.substring(3, 2);
			//var yYear	= fecha2.substring(6, 4);
			
			var fechaS = fecha.split("/");
			var fecha2S = fecha2.split("/");
			
			var xDay	= fechaS[0];
			var xMonth	= fechaS[1];
			var xYear	= fechaS[2];
			
			var yDay	= fecha2S[0];
			var yMonth	= fecha2S[1];
			var yYear	= fecha2S[2];
			
			var fechaPasada = false;
			
			if(xYear < yYear){
				fechaPasada = true;
			}else{
				if(xYear == yYear){
					if(xMonth < yMonth){
						fechaPasada = true;
					}else{
						if(xMonth == yMonth){
							if(xDay < yDay){
								fechaPasada = true;
							}
						}
					}
				}
			}
			
			//txtbFecha = document.getElementById("calfield1");
			if(fechaPasada == true){
				$scope.showConfirmacion("La fecha seleccionada es anterior a la fecha actual \n¿Desea conservar esta fecha?", function(){ corrigeFecha()});
			}
		}
		$scope.showConfirmacion = function(messageTo, action){
			ModalService.showModal({
				templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
				controller: 'mensajeModalController',
				inputs:{ message: messageTo}
			}).then(function(modal) {
				modal.element.modal();
				modal.close.then(function(result) {
					if(!result){
						action();
					}
				});
			});
		};
		
		function corrigeFecha(){
			$scope.reasignadoVO.fEmision = d;
			$("#fEmision").datepicker("setDate", d);
		}
		
		function genArchivoInfCan(contenidoDeArchivo) {
			var data = "";
			var contenidoDeArchivoS = contenidoDeArchivo.split(",");
			
			for (x=0 ; x < contenidoDeArchivoS.length; x++){
				if((x + 1) == contenidoDeArchivoS.length){
					data += contenidoDeArchivoS[x];
				}else{
					data += contenidoDeArchivoS[x] + "\r\n";
				}
			}
			
			var filename = "Infracciones Canceladas.txt";
			var type = "text/plain;charset=utf-8";
			var a = document.createElement("a"), file = new Blob([data], {type: type});
			
			if (window.navigator.msSaveOrOpenBlob) // IE10+
				window.navigator.msSaveOrOpenBlob(file, filename);
			else { // Otros
				var url = URL.createObjectURL(file);
				a.href = url;
				a.download = filename;
				document.body.appendChild(a);
				a.click();
				setTimeout(function() {
					document.body.removeChild(a);
					window.URL.revokeObjectURL(url);
				}, 0);
			}
		}
		buscaTipoDescuento();
});
