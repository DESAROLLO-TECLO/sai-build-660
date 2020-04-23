angular.module('siidfApp').controller('consultaSalidasController', function( $location, $scope, salidaVehiculoService,  $routeParams, ModalService) {
	
	
	$scope.param = {};
	$scope.error = true;
	
	busqCombo = function(){
		salidaVehiculoService.comboTipoBusq().success(function(data) {
			$scope.comboTipoBusq = data;
			$scope.param.tipoBusq = $scope.comboTipoBusq[0].codigoString;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		
	};
	
	busqOrden = function(){
		salidaVehiculoService.comboTipoOrden().success(function(data) {
			$scope.comboOrden = data;
			$scope.param.ordenarPor = $scope.comboOrden[0].codigoString;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		
	};
	
	tipoSalida = function(){
		salidaVehiculoService.tipoSalida().success(function(data) {
			$scope.comboTipoSalidas = data;
			$scope.param.tipoBusqSalida = $scope.comboTipoSalidas[0].codigoString;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		
	};
	
	comboAdjudDestino = function(){
		salidaVehiculoService.comboAdjudDestino().success(function(data) {
			$scope.comboAdjudicacionDest = data;
			
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	};
	
	comboFaseCompacta = function(){
		salidaVehiculoService.comboFaseCompacta().success(function(data) {
			$scope.comboFaseCompacto = data;
		
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		
		});
	};
	
	comboDepDestino = function(){
		$scope.param.numDepOrigen = "";
		salidaVehiculoService.comboDepDestino($scope.param.numDepOrigen).success(function(data) {
			$scope.comboDepDestino = data;
			
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	}
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	    	templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	$scope.buscarSalidaVehiculo = function(){
		if($scope.consultaVeh.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
			
		$scope.param.fechaInicio =  ($scope.param.fechaInicio == undefined) ? "" : $scope.param.fechaInicio;
		$scope.param.fechaFin =  ($scope.param.fechaFin == undefined) ? "" : $scope.param.fechaFin;
		$scope.param.valorBusquedaTipo =  ($scope.param.valorBusquedaTipo == undefined) ? "" : $scope.param.valorBusquedaTipo;
		salidaVehiculoService.buscarSalidaVehiculoConsul($scope.param.tipoBusq, 
				 $scope.param.valorCombo, $scope.param.tipoBusqSalida, $scope.param.fechaInicio, 
				$scope.param.fechaFin, $scope.param.valorBusquedaTipo).success(function(data) {
				$scope.salidasVO = data;
				$scope.tipoSalidaBandera = data[0].tipoSalida;

		}).error(function(data){
			$scope.salidasVO = {};
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		};
	};
	
	$scope.accionClean = function(){
		$scope.param.valorCombo = "";
		$scope.salidasVO = {};
		$scope.consultaVeh.$setPristine();
	};
	
	$scope.cleanTable = function(){
		$scope.salidasVO = {};
		if($scope.param.tipoBusq == 'tpoSalida'){
			$scope.param.valorCombo =  "";
				$scope.consultaVeh.$setPristine();
		if($scope.param.tipoBusqSalida == 1){
			comboAdjudDestino();
		}else if($scope.param.tipoBusqSalida == 2){
			comboFaseCompacta();
		}else if($scope.param.tipoBusqSalida == 5){
			comboDepDestino();
		}else if($scope.param.tipoBusqSalida == 4){
			$scope.param.valorBusquedaTipo =  "";
			$scope.consultaVeh.$setPristine();
			
		}
		}
	};


	requiredFields = function(){
		angular.forEach($scope.consultaVeh.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	
	$scope.buscarImgeEvidencias = function(idSalidas, numInfrac, tipoAccion){
		$scope.idSalidasImg = idSalidas;
		$scope.numInfraccion = numInfrac;
		$scope.tipoAccionModal = tipoAccion;
		salidaVehiculoService.mostrarEvidencias(idSalidas, numInfrac, tipoAccion)
		.success(function(data) {
				$scope.error = false;
				$("#modalEvidenciaImg").modal("show");
			
			for(var i = 0 ; i < data.length ;  i++){
				if(data[i].existe){
					data[i].img = "data:image/png;base64,"+data[i].img;
					}else{
					data[i].img = data[i].localPath;
					//	$scope.showImagen(data.localPath);
					}
				}
			$scope.ArrayImg = data;
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalAviso');
		});	
		
	}
	
	
	$scope.showImagen = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalImagen.html',
          controller: 'mensajeModalController',
          	  inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
	};
	
	
	
	$scope.descargarExpediente = function(){
		salidaVehiculoService.bajarExpedientes($scope.idSalidasImg,$scope.numInfraccion, "S")
			.success(function(data, status, headers) {
				$scope.close();
				$scope.error = false;
				var filename = headers('filename');
				var contentType = headers('content-type');
				var zip = new JSZip();
				var img = zip.folder("Expediente-"+filename);
				
				for(var x = 0; x < data.length; x++){
					if(data[x].existe){
						img.file("Expediente-"+filename+x+".png", data[x].img, {base64: true});
						}
				}
				zip.generateAsync({type:"base64"}).then(function(content) {
					$scope.b64toBlob(content, "application/octet-stream", "Expediente-"+filename)
				});
				$scope.error = false;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalAviso');
			});
	};
	
	
	$scope.b64toBlob = function(b64Data, contentType, nameFiles, sliceSize) {
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
		  $scope.descargarArchivoZip(blob, nameFiles+".zip" )
	};
		
	$scope.descargarArchivoZip = function(data, filename){	
			var a = window.document.createElement('a');
			          a.href = window.URL.createObjectURL( data );
			          a.download = filename;
			          document.body.appendChild(a);
			          a.click();
			          document.body.removeChild(a);	
					
				}
	
	$scope.close = function(){
		$scope.ArrayImg = {};
		
	};
	
	
	validarPerfil = function(){
		salidaVehiculoService.validarPerfil().success(function(data) {
				$scope.validarPerfilView = true;
				$scope.validarPerfilMsg = false;
				busqCombo();
				busqOrden();
				tipoSalida();
		}).error(function(data){
			$scope.validarPerfilView = false;
			$scope.validarPerfilMsg = true;
			$scope.perfilValida = data.message;
			
		});
		
	};
	
	validarPerfil();
	
	
	
	
});