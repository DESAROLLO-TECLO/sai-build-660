angular.module('siidfApp').controller('altaImpugnacionController', function(ModalService,$scope,impugnaService,$location,$filter) {
console.log('Alta Impugnacion Controller');

$scope.datos;
$scope.show = false;

var dateCurrent = moment();
var dateAfter   = moment().add(+5, 'm');

	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY HH:mm:ss',
		maxDate: dateAfter
	};
	
	$scope.abrir =  function() {		
		$location.path('/modifica_InfraccImpugnacion/'+ $scope.datos.impugnacionJuicio);
	}
	
	buscarImpugnacionesPorNumeroJuicio = function(numJuicio) {
		impugnaService.buscarImpugnaciones('numJuicio', numJuicio).success(function(data) {
			$location.path('/modifica_InfraccImpugnacion/'+ data[0].impugnacionId);
		}).error(function(data) {
			$scope.impugna = {};
		});
	};

	$scope.submitForm = function(impugnaVO,estatus) {		
		if ($scope.userForm.$invalid) {
			angular.forEach($scope.userForm.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
					})
			});
			showAviso('Formulario Incompleto');
		}else {
			impugnaVO.fechaRecepcion = $filter('formatDate')($scope.impugna.fechaRecepcion,"DD/MM/YYYY HH:mm:ss");
			impugnaService.saveImpugna(impugnaVO).success(function(data) {
				$scope.datos = data;
				if(data.resultado == -1){
					showError(data.mensaje);
				}else{
					var mensaje =  data.mensaje == 'Impugnacion Registrada Correctamente'
						? 'Impugnación registrada correctamente' : data.mensaje;
					showCorrecto(mensaje);
				}
				$scope.error = false;
			}).error(function(data) {
				$scope.error = data;
			});
		}
    };
    
    
    showCorrecto = function(messageTo) {
    	ModalService.showModal({
    		templateUrl: 'views/templatemodal/templateModalAvisoConfirm.html',
    		controller: 'mensajeModalController',
    		inputs:{ message: messageTo}
    	}).then(function(modal) {
    		modal.element.modal();
    		modal.close.then(function(result) {
    			if(result){
    				buscarImpugnacionesPorNumeroJuicio($scope.datos.impugnacionJuicio);
				}
			});
        });
      };
      
      showError = function(messageTo) {
    	  ModalService.showModal({
    		  templateUrl: 'views/templatemodal/templateModalError.html',
    		  controller: 'mensajeModalController',
    		  inputs:{ message: messageTo}
    	  }).then(function(modal) {
    		  modal.element.modal();
		  });
	  };
	  
	  showAviso = function(messageTo) {
		  ModalService.showModal({
			  templateUrl: 'views/templatemodal/templateModalAviso.html',
			  controller: 'mensajeModalController',
			  inputs:{ message: messageTo}
		  }).then(function(modal) {
			  modal.element.modal();
		  });
	  };	 

});      
        
        
