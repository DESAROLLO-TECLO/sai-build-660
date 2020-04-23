angular.module('siidfApp').controller('validacionCertificadoController',function($scope, radaresProcesoService, certificadoService, ModalService,showAlert, $filter, $location, $controller, growl) {
	$scope.mostrarEmpleado = false;
	
	$scope.rfc;
	$scope.inputRFC = false;
	$scope.textoRFC = false;
	$scope.mostrarCargaArchivos = false;
	$scope.cargaArchivos = false;
	$scope.detalleArchivos = false;
	$scope.idUploadCertificado = false;
	$scope.idUpdateCertificado=false;
	$scope.key = false;
	$scope.show=false;	
	$scope.files = [];
	$scope.certificado ={ checkvalidacion : false,
						  pwdValidaCertCarga : "",
						  pwdValidaCertCargado : ""};
	$scope.msg =[];
	
	$scope.mostrarErrorPlaca = false;
	$scope.errorPlaca = "";
	$scope.mostrarErrorEmpleado = false;
	$scope.errorEmpleado = "";
	$scope.mostrarErrorCertificado = false;
	$scope.errorCertificado = "";
	
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	$scope.showAvisoFinal = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
//	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
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
	
	validaLongitudRFC = function (rfc){
		if(rfc.length > 12 ){
    		$scope.inputRFC = false;
    		$scope.textoRFC = true;
    	}else if(rfc.length < 12 ){
    		$scope.inputRFC = true;
    		$scope.textoRFC = false;
    	}
	}
	
	validaElementosCargaArchivo =  function(operaciones) {
		if(operaciones == "NO"){
			$scope.mostrarCargaArchivos = true;
			$scope.idValidarCertificado= false;
			$scope.cargaArchivos= true;
			$scope.key =  true;
			$scope.detalleArchivos= false;
		}else{
			$scope.mostrarCargaArchivos = false;
			$scope.cargaArchivos= false;
			$scope.key = false;
		}
	}
	
	muestraComponentesCertCargado = function (){
		$scope.divValidaCertCargado = true;
		$scope.key =  true;	
		$scope.pwdValidaCertCargadoShow = true;
	}
	
	validarCertificadoExistente =  function (placa){
		certificadoService.validaExisteCert(placa).success(function(data) {
			if(data.respuesta){
				var certValidoHasta = new Date(data.certificado.certValidoHasta);
				var certValidoDesde = new Date(data.certificado.certValidoDesde);
				
				$scope.certNombre = data.certificado.certNombre;
				$scope.certImage = true;
				
				$scope.keyNombre = data.certificado.keyNombre;
				$scope.keyImage = true;
				
				$scope.certValidoDesde=data.certificado.certValidoDesde;
				$scope.certValidoHasta=data.certificado.certValidoHasta;
				$scope.certEmitidoPor=data.certificado.certEmitidoPor;
				$scope.certEmitidoPara=data.certificado.certEmitidoPara;
				
				$scope.cargaArchivos = false;
				$scope.detalleArchivos = true;
				$scope.idUploadCertificado = false;
				$scope.idUpdateCertificado=true;
				
				$scope.certValido = data.certificado.validado;
				
				if($scope.certValido == 0){
					//$scope.showAviso("El oficial no cuenta con un certificado válido, favor de actualizarlo");
					$scope.mostrarErrorEmpleado = true;
					$scope.errorEmpleado = "El oficial no cuenta con un certificado válido, favor de actualizarlo";
					growl.error($scope.errorEmpleado);
				}else{
					muestraComponentesCertCargado();
					$scope.idValidarCertificado = true;
				}
   		 }else{
   			 $scope.cargaArchivos=true;
   			 $scope.detalleArchivos= false;
   			 $scope.idUploadCertificado=true;
   		 }
		}).error(function(data) {
			$scope.error = data;
			
		});
   	}
	

	$scope.buscarUsuarios = function() {
		$scope.mostrarErrorEmpleado = false;
		$scope.errorEmpleado = "";
		$scope.mostrarErrorCertificado = false;
		$scope.errorCertificado = "";
		$scope.mostrarEmpleado = false;
		$scope.usuariosVO = "";
		if($scope.parametroBusqueda.placa == undefined || $scope.parametroBusqueda.placa == ''){
			//showAlert.requiredFields($scope.formVCertSAT);
			$scope.mostrarErrorEmpleado = true;
			$scope.errorEmpleado = "La placa del ofician es requerida";
			growl.error($scope.errorEmpleado);
		}else{
			var placa = $scope.parametroBusqueda.placa;
			
			radaresProcesoService.vigenciaUsuario(placa).success(function(data){
				if(data == true){
					certificadoService.buscarUsuarios('emp_placa','3',placa).success(function(data) {
						$scope.usuariosVO = data;
						$scope.mostrarEmpleado = true;
						
						validaLongitudRFC(data[0].emp_rfc);
				    	if($scope.textoRFC == true){
							validaElementosCargaArchivo(data[0].tiene_operaciones); 
							validarCertificadoExistente(data[0].emp_placa);
				    	}else{
				    		//$scope.showAviso("El oficial no cuenta con un RFC registrado válido");
				    		$scope.mostrarErrorEmpleado = true;
				    		$scope.errorEmpleado = "El oficial no cuenta con un RFC registrado válido";
				    		growl.error($scope.errorEmpleado);
				    		$scope.mostrarEmpleado = false;
				    		$scope.usuariosVO = "";
				    	}
					}).error(function(data) {
						$scope.usuariosVO = "";
						$scope.error = data;
					});
				}else{
					//$scope.showAviso("El oficial con placa '"+placa+"' no existe");
					$scope.mostrarErrorEmpleado = true;
		    		$scope.errorEmpleado = "El oficial con placa '"+placa+"' no existe";
		    		growl.error($scope.errorEmpleado);
				}
			});
		}
	}
	
	validarCertificadoLLave =  function (placa,pwd) {
		radaresProcesoService.validaPassCert(placa,pwd).success(function(data) {
			var respuesta = data.respuesta;
			var message = data.message;
			
			if(respuesta == true){
				radaresProcesoService.actualizaCertificadoValido(
						$scope.radarArchivoVO.radarArchivoId,
						$scope.usuariosVO[0].emp_id,
						placa,
						$scope.usuariosVO[0].emp_rfc,
						pwd
				).success(function(data) {
					$('.modal').hide();
					$('.modal-backdrop').remove();
					//$scope.showAvisoFinal(message);
					$scope.showConfirmacion(message, function(){ $scope.updateFront() });
				}).error(function(data) {
					$scope.error = data;
				});
			}else{
				//$scope.showAviso(message);
				$scope.mostrarErrorCertificado = true;
	    		$scope.errorCertificado = message;
	    		growl.error($scope.errorCertificado);
			}
		}).error(function(data) {
			$scope.usuariosVO = "";
			$scope.error = data;
		});
	}
	
	$scope.validarCertificado = function(placa,pwd) {
		var password =	pwd;
		var placaOficial = placa;
	 	if(password == null | password == '' ){
//	 		$scope.form.pwdValidaCertCargado.$invalid =true;
//	 		$scope.form.pwdValidaCertCargado.$pristine = false;
	 		$scope.mostrarErrorCertificado = true;
    		$scope.errorCertificado = "La Contraseña de Validación es requerida";
    		growl.error($scope.errorCertificado);
	 	}else{
			  validarCertificadoLLave(placaOficial, password);
	 	}
	}
	
	defaultValues = function(){
		$scope.parametroBusqueda = {};
		$scope.tipoRadardisabled = true;
	}
	
	defaultValues();
});