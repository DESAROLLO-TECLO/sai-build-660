angular.module('siidfApp').controller('ingresoTrasladoVehiculoController', function($scope,$routeParams,  catalogoService, $location,  remisionaDepositoService, ModalService) {
	
	$scope.infracNum = $routeParams.id;
	$scope.infoBusquedaIngreso = {};
	$scope.ingresoInfracVO = {};
	$scope.valorUpdIngreso = {
			codGrua : ""
	};
	
	$scope.file={};
	$scope.files = {};
	$scope.fileObject = {};
	$scope.nombreArchivo = 'Seleccionar evidencias';
 	var inputs = document.querySelectorAll('.inputFileSalidaVeh');
 	
 	
	/*NUEVO*/
	listInventario = function() {	
		catalogoService.listaInventario().success(function(data) {
			$scope.listInventario = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listInventario  = {};
		});
	};
	  $scope.imChanged = function(){
		  $scope.valoresInventario = $scope.ingresoInfracVO.valorInv.listInventario.join('-');
	  }
	
		listMedioTrans = function() {	
			catalogoService.listaMediosTrasp().success(function(data) {
				$scope.listMedios = data;
			}).error(function(data) {
				$scope.listMedios  = {};
			});
		};
		
		$scope.cambioMedioTransporte = function (){
		
			  if($scope.ingresoInfracVO.t_ingr_id != 1){
				  $scope.valorUpdIngreso.codGrua = "";
			  }else{
				  if( $scope.ingresoInfracVO.codGrua == 'SIN GRUA'){
					  $scope.valorUpdIngreso.codGrua = "";
				  }else{
					  $scope.valorUpdIngreso.codGrua =  $scope.ingresoInfracVO.codGrua;
				  }
				  
				  
			  }
		  }
		cargaDato = function(){
			remisionaDepositoService.cargaDatoIngreso($scope.infracNum).success(function(data) {
				$scope.ingresoInfracVO = data;
				$scope.consultarPlaca();
			}).error(function(data){
				$scope.showAviso(data.message, 'templateModalAviso');
			
			});
		};
		listCausa = function() {	
			catalogoService.listaCausaIngresoByTraslado().success(function(data) {
				$scope.listCausa = data[0];
				///$scope.ingresoInfracVO.causa = data[0];
			}).error(function(data) {
				$scope.error = data;
				$scope.listCausa  = {};
			});
		};
		
		
	  /*fin modifcacion*/

	
	$scope.consultarPlaca = 	function() {
		if($scope.ingresoInfracVO.idPlacaOficial != undefined){
			remisionaDepositoService.consultarPlacaOficialById($scope.ingresoInfracVO.idPlacaOficial)
		.success(function(data) {
			 $scope.datosAuxiliar = data;
		}).error(function(data) {
			$scope.showAviso("La placa del oficial no existe", 'templateModalAviso');
		});
		}else{
			$scope.showAviso("La placa del oficial no se encuentra", 'templateModalAviso');
		}
		$scope.viewMessagePlaca = true;
		$scope.viewRegresar = false;
	};
	
	
	$scope.guardarVOIngresoTraslado = function(){
		if($scope.forms.formIngreso.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
		var trasladoIngresoVO = {
				 "infracNum" : $scope.ingresoInfracVO.infracNum,
				 "observaciones" : $scope.ingresoInfracVO.infracOservacion,
				 "medioTrasporte" : $scope.ingresoInfracVO.t_ingr_id,
				 "noEconomico" : $scope.valorUpdIngreso.codGrua,
				 "idDepDestino" : $scope.ingresoInfracVO.idDepDestino,
				 "tipoMovimiento" : "I",
				 "numCtrl" :  $scope.ingresoInfracVO.ingrNumCtrl
		};
		$scope.tamno = ($scope.files.length == undefined || $scope.files.length < 1) ? 0 : $scope.files.length ;
		remisionaDepositoService.guardarVOIngresoTraslado(trasladoIngresoVO, $scope.files, $scope.tamno).success(function(data) {
			$scope.infoBusquedaIngreso = data;
			if(data.mensaje != null){
				remisionaDepositoService.setEstatusMomentoTraslado.estatusMomentoTraslado  = true;
				$scope.showAviso2(data.mensaje, 'templateModalAviso',function(){ locationMover() } );
			}else{
				$scope.showAviso(data.mensaje, 'templateModalError');
			}

		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		
		});
		}
	};


	locationMover = function(){
		$scope.forms.formIngreso.$setPristine();
		$location.path('/salidasVehiculoTraslado');
	}
	
	$scope.regresarBusqMod = function(){
		$location.path('/salidasVehiculoTraslado');
	};
	
	$scope.showAviso2 = function(messageTo, template, action) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/'+template+'.html',
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
	requiredFields = function(){
		angular.forEach($scope.forms.formIngreso.$error, function (field) {
		      	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            	})

		});
	};
	
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	    	templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};

	
	/*IMG VALIDACION*/
	
	/*VALIDACION DE IMG Y SU RESPECTIVA MUESTRA EN INPUT*/
	  
	Array.prototype.forEach.call(inputs, function(input){
		var label = input.nextElementSibling, 
		labelVal = label.innerHTML;
		$scope.flagInputFile = true;
		input.addEventListener('change', function(e){
		$scope.nombreArchivo =  '';
		if (this.files && this.files.length > 1) {
			$scope.flagInputFile = false;
//			$scope.flagInputFileView = $scope.flagInputFile;
			if(this.files.length > 3){
				$scope.showAviso("Se ha excedido el limite de evidencias(3)", 'templateModalError');
				$scope.nombreArchivo = 'Seleccionar evidencias';
				$scope.flagInputFile = true;
//				$scope.flagInputFileView = $scope.flagInputFile;
			}else{
				
				$scope.flagInputFile = false;
//				$scope.flagInputFileView = $scope.flagInputFile;
				$scope.nombreArchivo = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
			}
			
		}else{
			$scope.flagInputFile = false;
//			$scope.flagInputFileView = $scope.flagInputFile;
			$scope.nombreArchivo = e.target.value.split('\\').pop();
			
		}if ($scope.nombreArchivo){
				$scope.nombreArchivo;
			}else{
				$scope.nombreArchivo = 'Seleccionar evidencias';
			}
		});

		// Firefox bug fix
		input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
		input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });

	});
	
//	**************************   INICIO CARGA DE ARCHIVOS   **********************************
	
	$scope.listenerForImg = function (e, file) {
		var reader = new FileReader();
		reader.readAsDataURL(file[0]);  
		reader.onerror = function (error) {
			$scope.files = {};
			
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
	
	listCausa();
	listInventario();
	listMedioTrans();
	cargaDato();
	//$scope.consultarPlaca();
});