angular.module('siidfApp').controller('nuevoDocumentoController', function($scope, $filter, $timeout, parteInformativoService, ModalService) {
	$scope.nDocumento = {};
	$scope.addInfracciones = []; 
	
	$scope.faltanDocumentos = true;
	$scope.faltanDocumentosMensaje = false;
	$scope.faltanInfracciones = false;
	
	$scope.viewOficialData = false;
	$scope.viewMessagePlaca = false;
	
	$scope.sdocife = false;
	$scope.starcirc = false;
	$scope.sdoclic = false;
	$scope.sdocverif = false;
	$scope.sdocotros =  false;
	
	$scope.validplaca = false;  //Valida si la placa va vacía
	$scope.validinfra = false;  //Valida si la infraccion va vacía
	$scope.optionsAdd = -1; //ng-model del selectmultiple su valor es la posicion del elemento
	$scope.numInfraccion = '';//ng-model de infraccion agregará al multiselect
	$scope.strPlaca = '' //ng-model de placa que se agregara al multiselect

	$scope.buscarEmpleadoPorPlaca = function(placa) {
		if(placa != undefined){
			parteInformativoService.buscarEmpleadoPorPlaca(placa).success(function(data) {
				$scope.viewOficialData = true;
				
				//SET de datos del oficial.
				$scope.oficialAgrupamiento = data.agrupamiento.agrupacionNombre;
				$scope.oficialSector = data.sector.secNombre;
				$scope.oficialNombre = data.empNombre + ' ' + data.empApePaterno + ' ' + data.empApeMaterno;
				
				//SET color y mensaje.
				$scope.validColor = "#444";
				$scope.validMensaje = "La placa oficial es válida";
			}).error(function(data) {
				$scope.viewOficialData = false;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa oficial no es válida";
			});
		}else{
			$scope.viewOficialData = false;
			$scope.validColor = "red";
			$scope.validMensaje = "La placa oficial no es válida";
		}
		
		$scope.viewMessagePlaca = true;
	}
	
	$scope.agregarInfracciones = function(){
		var flag = true;
		if(!$scope.numInfraccion){
			flag = false;
			$scope.validinfra = true; 
		}else{
			$scope.validinfra = false; 
		}
		if(!$scope.strPlaca){
			flag = false;
			$scope.validplaca = true;
		}else{
			$scope.validplaca = false;
		}
		
		if(flag){
			$scope.addInfracciones.push({"infracNum" : $scope.numInfraccion.toUpperCase() , "infracPlaca" : $scope.strPlaca.toUpperCase()});
			$scope.numInfraccion = '';
			$scope.strPlaca = '';
			$scope.validinfra = false;
			$scope.validplaca = false;
			$scope.faltanInfracciones = false;
		}
		
		
	}
	
	$scope.eliminarInfraccionSeleccionada = function(){
		if($scope.optionsAdd > -1){
			$scope.addInfracciones.splice($scope.optionsAdd, 1);
			$scope.optionsAdd = -1;
		}
		
		if($scope.addInfracciones[0] == undefined){
			$scope.faltanInfracciones = true;
		}
	}
	
	$scope.eliminarInfracciones = function(){
		$scope.addInfracciones = [];
		$scope.optionsAdd = -1;
		$scope.faltanInfracciones = true;
	}
	
	$scope.changeValue= function(){
		$scope.faltanDocumentos = !($scope.sdocife || $scope.starcirc || $scope.sdoclic || $scope.sdocverif || $scope.sdocotros);
		$scope.faltanDocumentosMensaje = $scope.faltanDocumentos;
	}
	
	$scope.changePlaca = function(){
		$scope.viewOficialData = false;
		$scope.viewMessagePlaca = true;
		$scope.validColor = "red";
		$scope.validMensaje = "La placa oficial no es válida";
	}
	
	$scope.crearDocumento = function(documentoVO,addInfracciones){
		var viewmodal = true;
		if($scope.form.$invalid){
			requiredFields();

			if($scope.addInfracciones[0] == undefined){
				$scope.faltanInfracciones = true;
			}
			
			if(!$scope.viewOficialData){
				$scope.viewMessagePlaca = true;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa oficial no es válida";
			}
			
		}else if(!$scope.viewOficialData){
			$scope.viewMessagePlaca = true;
			$scope.validColor = "red";
			$scope.validMensaje = "La placa oficial no es válida";
		}else if($scope.addInfracciones[0] == undefined){
			$scope.faltanInfracciones = true;
		}else{
			viewmodal = false;
			if($scope.sdocife){documentoVO.docIfe = 'S';}
			if($scope.starcirc){documentoVO.docTarjCirc = 'S';}
			if($scope.sdoclic){documentoVO.docLicencia ='S';}
			if($scope.sdocverif){documentoVO.docVerific = 'S';}
			if($scope.sdocotros){documentoVO.docOtros = 'S';}
			documentoVO.fecha = $filter('formatDate')($scope.nDocumento.fechaDate,'DD/MM/YYYY HH:mm:ss'); 
			
			parteInformativoService.crearDocumento(documentoVO, addInfracciones).success(function(data) {
				$scope.documentoNuevo = data;
				
				if($scope.documentoNuevo.resultado == '-1'){
					$scope.showAviso($scope.documentoNuevo.mensaje, 'templateModalError');
				}else{
					$scope.showAviso("Registro: " + $scope.documentoNuevo.id + " Resultado: " + $scope.documentoNuevo.mensaje, 'templateModalAviso');
					reset();
				}
				
			}).error(function(data) {
				$scope.documentoNuevo = {};
				$scope.showAviso(data.message, 'templateModalError');
			});
			
			$scope.faltanInfracciones = false;
		}
		
		if(viewmodal){
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}
	}
	
	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/' + template + '.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      	}).then(function(modal) {
      		modal.element.modal();
      });
	};
	
	prepareReset = function(){
		$scope.emptyForm = angular.copy($scope.nDocumento);
	}
	
	reset = function(){
		$scope.nDocumento = {};
		$scope.addInfracciones = [];
		$scope.validColor = "#444";
		$scope.viewOficialData = false;
		$scope.viewMessagePlaca = false;
		$scope.sdocife = false;
		$scope.starcirc = false;
		$scope.sdoclic = false;
		$scope.sdocverif = false;
		$scope.sdocotros = false;
		$timeout(function() {
			$scope.form.$setPristine();
		}, true);
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            	//alert(errorField.$name);
            })
		});
	}
	
	$scope.formatDateF = {
			format: 'DD/MM/YYYY HH:mm:ss'
//			maxDate: dateAfter
	};
	
	prepareReset();
});
