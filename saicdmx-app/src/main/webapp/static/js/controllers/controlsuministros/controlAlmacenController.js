angular.module('siidfApp').controller('controlAlmacenController', function($scope, $filter, $window,$location, controlSuministrosService, catalogoService, ModalService) {
	
	$scope.almacenVO   = {};

	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/'+template+'.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
           
      });
	};
	
	$scope.showAvisoUpdate = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		$window.location.reload();
	        	}
	        }); 
	        
	      });
		};
	

		  
	$scope.altaAlmacen = function(almacenVO) {
		if($scope.formAltaAlmacen.$invalid || $scope.almacenVO.validFolios){
			requiredFields($scope.formAltaAlmacen);
			$scope.showAviso('Formulario Incompleto', 'templateModalAviso');
		}else{
			$scope.almacenVO.txt_fecha = $filter('formatDate')($scope.almacenVO.txt_fecha,'DD/MM/YYYY HH:mm:ss');
			controlSuministrosService.altaAlmacen(almacenVO).success(function(data) {
				
				if(data.resultado == "-1"){
					$scope.showAviso(data.mensaje, 'templateModalError');
				}else{
					$scope.showAvisoUpdate(data.mensaje, 'templateModalAviso');
									
				}		
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
			});
		}
	}
	
	$scope.changeFolios = function (){
		$scope.almacenVO.validFolios = false;
		if(parseInt( $scope.almacenVO.txt_al_folio) <= parseInt($scope.almacenVO.txt_del_folio)){
			$scope.almacenVO.validFolios = true;
		}else{
			$scope.almacenVO.validFolios = false;
		}
		
	}
	
	listaTipoRSum = function() {	
		catalogoService.buscarTipoSuministro().success(function(data) {
			$scope.almacenVO.tiposuministro = data;
			prepareReset();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	requiredFields = function(form){
		angular.forEach(form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	prepareReset = function(){
		$scope.emptyForm = angular.copy($scope.almacenVO);
	}
	
	reset = function(){
		$scope.almacenVO = $scope.emptyForm;
		$scope.formAltaAlmacen.$setPristine();
	}
	
	listaTipoRSum();
	
});