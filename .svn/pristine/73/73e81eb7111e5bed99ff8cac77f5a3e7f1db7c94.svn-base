angular.module('siidfApp').controller('cargaExpedienteController', function($scope, $location, $routeParams, $route, expedienteService, catPagoInfraccionService, pagosInfraccionService, jwtService, deviceDetector, loginService, ModalService, storageService) {
	
	/*
	 * César Gómez
	 */

	$scope.infraccionDepositoVO		= {tipoParametro:0};
	$scope.navegador 				= {};
	$scope.infraccionDeposito 		= "";
	$scope.infracAsociadas 			= "";
//	$scope.catalogoExp 				= false;
	$scope.catalogoExp = [];
	$scope.expedienteVO				= {};
	
	$scope.tipoArchivo  = {};
	$scope.tipoCatalogo = "";
	
	
	
	//Se agrega para visualizar el numero de infraccion en vista y no afectar este controler
	$scope.infracNumM = $routeParams.id;
	$scope.viewFlag = $routeParams.flagView;
	
	$scope.limpiarVista = function(){
		$scope.catalogoExp = ($scope.infraccionDepositoVO.tipoParametro == undefined || $scope.infraccionDepositoVO.valorParametro == undefined) ? false : 0;
	}
		
//	OBTENER EL PERFIL CAJERO PARA VALIDAR
	initController = function() {
		
		$('.select2').select2({
			minimumResultsForSearch: -1
		});
		
		var placa = jwtService.getPlacaUsuario(storageService.getToken());
		
		catPagoInfraccionService.obtenerPerfilCajero( placa ).success(function(data) {
			$scope.isCajero = data;
			$scope.messagePerfil = "¡Cuidado! No puedes realizar esta operación, verifica tu perfil.";
			if ( angular.equals(deviceDetector.browser ,"chrome" )){
				$scope.navegador ="chrome";
				$scope.mensajeNavegador = "¡Cuidado! El módulo pago requiere de navegador Firefox o Internet Explorer.";
			}else if ( angular.equals(deviceDetector.browser,"ms-edge" )){
				$scope.navegador ="ms-edge";
				$scope.mensajeNavegador = "¡Cuidado! El módulo pago requiere de navegador Firefox o Internet Explorer.";
			} else {
			
				tiposBusquedaInfraccionesPago();
				
			}
			
			tiposBusquedaInfraccionesPago();
 		}).error(function(data) {
			$scope.isCajero = data;
		});
    }
	
//	CONSULTA EL CATALOGO DE TIPOS DE BUSQUEDA DE INFRACCIONES
	tiposBusquedaInfraccionesPago = function() {
		catPagoInfraccionService.tipoBusquedaInfracciones().success(function(data) {
			$scope.catTiposBusqueda = data;
			$scope.infraccionDepositoVO.tipoParametro = $scope.catTiposBusqueda[0].codigoString;
			$('#select2-catBusqueda-container').text($scope.catTiposBusqueda[0].descripcion);
  			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.catTiposBusqueda = {};
		});
	}
	
		
 // CONSULTA LA INFRACCION POR LA QUE ENTRO A DEPOSITO
	$scope.consultaInfraccionesDeposito = function(infraccionDepositoVO) {
		if($scope.infraccionesDeposito.$invalid) {
			angular.forEach($scope.infraccionesDeposito.$error, function (field) {
	           	angular.forEach(field, function(errorField){
	           		errorField.$setDirty();
	           	})
			});
		} else {			
			expedienteService.consultaInfraccionesDeposito(infraccionDepositoVO.tipoParametro, infraccionDepositoVO.valorParametro).success(function(data) {
				$scope.obtenerExpediente(data);
			}).error(function(data) {
				$scope.showAviso(data.message);
			});
		}
	}    
	
	/*Se obtiene las capturas que estan registradas y las que no estan*/
	$scope.obtenerExpediente = function(data){
		
		$scope.infraccionDeposito = data[3];
		$scope.infracAsociadas = data[2];
		$scope.nci = data[1];
		$scope.placa = data[0];
		
		expedienteService.buscarCatalogoExpedientes($scope.infraccionDeposito).success(function(data, status, headers) {

			$scope.catalogoExp = data;
			$scope.error = false;
			
		}).error(function(result) {
			$scope.showError(result.message);
			$scope.catalogoExp = {};
		});
	};

	$scope.onChange = function (e, file) {
		
		var data 		 = file[0];
		$scope.tipoArchivo  = data.type;
		$scope.tipoCatalogo = e.target.id;
		
		$scope.nombreArchivoCargado = data.name;
		
		if($scope.tipoCatalogo == "TODO" && $scope.tipoArchivo != "application/pdf") {
			$scope.showAviso("Solo se permiten archivos con formato PDF para el Expediente Completo");
		} else {
			
			switch($scope.tipoArchivo) {
				case "application/pdf":
				case "image/png":
				case "image/jpg":
				case "image/jpeg":
					$scope.showConfirmacion("Se cargará el archivo del documento " +$scope.nombreArchivoCargado+ " al expediente", function(){
						$scope.getBase64(data, $scope.tipoCatalogo, $scope.tipoArchivo)});
					break;
				default:
					$scope.showAviso("Solo se permiten archivos con formato PNG, JPG|JPEG o PDF");
					break;
			}
		}
	};
	
	/* Funcion de Buscar catalogo*/
	$scope.cargarExpediente = function(data){
		
		$scope.expedienteVO.infracNum = $scope.infraccionDeposito;
		$scope.expedienteVO.tipoCatalogo = data.tipo;
		$scope.expedienteVO.archivo = data.base64;
		$scope.expedienteVO.tipoArchivo = data.tipoArchivo;
		
		expedienteService.capturarExpedientes($scope.expedienteVO)
			.success(function(data) {
				$scope.error = false;
				if(data){
					$scope.showAviso("Archivo del documento cargado "+$scope.nombreArchivoCargado+" correctamente al expediente", function(){
//						$route.reload();
						$scope.consultaInfraccionesDeposito($scope.infraccionDepositoVO);
					});
				}
			}).error(function(data) {
				//$scope.error = data.message;
				if(data.message){
//					$scope.showError(data.message);
					$location.path('/pagoDigitalizacion');
					$scope.showAviso("Tipo de Archivo no Admitido");
				}else{
					$scope.showError(data);
				}
		});	
	};
	
	$scope.eliminarExpediente = function($event, object){
		$event.preventDefault();
		var algo = $scope.file;
		$scope.showConfirmacion("Se eliminará el archivo del documento "+object+" del Expediente", function(){
			expedienteService.borrarExpedientes($scope.infraccionDeposito, object)
			.success(function(data) {
				$scope.error = false;
				if(data){
					$scope.showAviso("Archivo del documento "+object+" eliminado correctamente", function(){
//						$route.reload();
						$scope.consultaInfraccionesDeposito($scope.infraccionDepositoVO);
					});
				}
			}).error(function(data) {
				if(data.message){
					$scope.showError(data.message);
				}else{
					$scope.showError(data);
				}
			});	
		});
	};
	
	$scope.verExpediente = function($event, object){
		$event.preventDefault();
		expedienteService.mostrarExpedientes($scope.infraccionDeposito, object)
		.success(function(data) {
			$scope.error = false;
			if(data.existeEnBD){
				$scope.showImagen("data:image/png;base64,"+data.bdPath);
				//$scope.showAviso("esta en base", function(){});
			}else{
				$scope.showImagen(data.localPath);
				//$scope.showAviso("esta en local", function(){});
			}
		}).error(function(data) {
			if(data.message){
				$scope.showError(data.message);
			}else{
				$scope.showError(data);
			}
		});	
	};
	
	$scope.descargarExpediente = function($event, object){
		$event.preventDefault();
		expedienteService.bajarExpedientes($scope.infraccionDeposito, object)
			.success(function(data, status, headers) {
				$scope.error = false;
//				var filename = headers('filename');
//				var contentType = headers('content-type');
				
				var filename = data.nombreArchivo;
				var extension = data.nombreArchivo.split('.')[1];
				
				if(data.existeEnBD){
					
					var file;
					
					if(extension == "pdf") {
						file = $scope.b64toBlob(data.bdPath, "application/pdf");
					} else {
						file = $scope.b64toBlob(data.bdPath, "image/jpg");
					}
					
					$scope.save(data.existeEnBD, file, filename);
					
				}else{
					$scope.save(data.existeEnBD, data.localPath, filename);
				}
				
				$scope.error = false;
				
			}).error(function(data) {
				if(data.message){
					$scope.showError(data.message);
				}else{
					$scope.showError(data);
				}
			});
	};
	
	$scope.consultaInfrac=function(){
		$location.path('/pagoInfraccion/'+$scope.infraccionDepositoVO.tipoParametro+'/'+$scope.infraccionDeposito+'/'+$scope.nci+'/'+$scope.placa);
	}	
	
	/* NOTIFICACIONES MODAL */
	$scope.showAviso = function(messageTo, action) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalAviso.html',
          controller: 'mensajeModalController',
          inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
          modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
        });
	};
	
	$scope.showError = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalError.html',
          controller: 'mensajeModalController',
          	  inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
	};
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	    });
	};
	
	$scope.showImagen = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalImagen.html',
          controller: 'mensajeModalController',
          	  inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
	};
	/* FIN NOTIFICACIONES MODAL */
	$scope.getBase64 = function(file, kind, tipoArchivo) {
	
	   var reader = new FileReader();
	   reader.readAsDataURL(file);
	   reader.onload = function () {
		 
		 var base64 = reader.result.split(',')[1];
		   
	     $scope.cargarExpediente({
	    	 base64:base64,
	    	 tipo:kind,
	    	 tipoArchivo: tipoArchivo
	     	});
	   };
	   reader.onerror = function (error) {
		   $scope.cargarExpediente(error);
	   };
	};
		
	$scope.b64toBlob = function(b64Data, contentType, sliceSize) {
		  contentType = contentType || '';
		  sliceSize = sliceSize || 512;

		  var byteCharacters = atob(b64Data);
		  var byteArrays = [];

		  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
		    var slice = byteCharacters.slice(offset, offset + sliceSize);

		    var byteNumbers = new Array(slice.length);
		    for (var i = 0; i < slice.length; i++) {
		      byteNumbers[i] = slice.charCodeAt(i);
		    }

		    var byteArray = new Uint8Array(byteNumbers);

		    byteArrays.push(byteArray);
		  }

		  var blob = new Blob(byteArrays, {type: contentType});
		  return blob;
	};
		
	$scope.save = function(isURL, file, fileName) {
			var url = window.URL || window.webkitURL;
			var a = document.createElement('a');
			if(!isURL){
				a.href = file;
			}else{ 
				a.href = url.createObjectURL(file);
			}
			a.target = '_blank';
			a.download = fileName;
			document.body.appendChild(a);
			a.click();	
	};
	
	$scope.regresar = function(){
		if($scope.viewFlag == 1){
			$location.path('/pagoInfraccion');
		}else{
			$location.path('/pagoInfraccionActa');
		}
		
	}
	
    initController();
});
