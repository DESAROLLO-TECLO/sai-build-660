angular.module('siidfApp').controller('suministroAreasController', function($scope, $window,$location, $filter, controlSuministrosService, catalogoService, ModalService) {
	
	$scope.suministroVO = {tiposuministro : [], regionales : [], areaOperativa : [], oficiales : []};
  
	var dateCurrent = moment();
	var dateAfter   = moment().add(+15, 'm');
	
	$scope.dateTimePickerOptions = {
			format: 'DD/MM/YYYY HH:mm:ss',
			maxDate: dateAfter
	};
	
	/* Modal Aviso */
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
		  
	$scope.altaSuministro = function(suministroVO) {
		if($scope.formAltaSuministro.$invalid || $scope.suministroVO.validFolios){
			requiredFields($scope.formAltaSuministro);
			$scope.showAviso('Formulario Incompleto', 'templateModalAviso');
		}else{
			$scope.suministroVO.txt_fecha = $filter('formatDate')($scope.suministroVO.txt_fecha,'DD/MM/YYYY HH:mm:ss');
			controlSuministrosService.altaSuministro(suministroVO).success(function(data) {
				$scope.showAvisoUpdate(data.mensaje, 'templateModalAviso');
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
			});
		}
		
	}

	listaTipoSum = function() {	
		catalogoService.buscarTipoSuministro().success(function(data) {
			$scope.suministroVO.tiposuministro = data;
			listaRegionales();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
			$scope.suministroVO.tiposuministro  = [];
		});
	};
	
	listaRegionales = function() {	
		catalogoService.buscarRegionales().success(function(data) {
			$scope.suministroVO.regionales = data;
			prepareReset();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
			$scope.suministroVO.regionales  = {};
		});
	};
	
	$scope.actualizarAreaOpe = function() {
		if($scope.suministroVO.lstRegional != null){
			catalogoService.buscarAreaOperativa($scope.suministroVO.lstRegional)
			.success(function(data) {
				$scope.suministroVO.areaOperativa = data;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
				$scope.suministroVO.areaOperativa = [];
			});
		}else{
			$scope.suministroVO.areaOperativa = [];
		}
	}
	
	$scope.actualizarOficial = function() {
		if($scope.suministroVO.lstAreaOpe != null){
			catalogoService.buscarOficiales($scope.suministroVO.lstRegional, $scope.suministroVO.lstAreaOpe)
			.success(function(data) {
				$scope.suministroVO.oficiales = data;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
				$scope.suministroVO.oficiales = [];
			});
		}else{
			$scope.suministroVO.oficiales = [];
		}
	};
	
	$scope.changeFolios = function (){
		$scope.suministroVO.validFolios = false;
		if( parseInt($scope.suministroVO.txt_folio_fin) <= parseInt($scope.suministroVO.txt_folio_ini)){
			$scope.suministroVO.validFolios = true;
		}else{
			$scope.suministroVO.validFolios = false;
		}
	}
	
	requiredFields = function(form){
		angular.forEach(form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	prepareReset = function(){
		$scope.emptyForm = angular.copy($scope.suministroVO);
	}
	
	reset = function(){
		$scope.suministroVO = $scope.emptyForm;
		$scope.formAltaSuministro.$setPristine();
	}
	
	listaTipoSum();
});