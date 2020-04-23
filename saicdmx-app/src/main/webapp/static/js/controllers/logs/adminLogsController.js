angular.module('siidfApp').controller('adminLogsController', function($location,$scope,logsService,ModalService) {

	consultaActivos = function() {
		logsService.consultaActivos().success(function(data) {	
			evaluaEstatus(data);			
			$scope.logsActivos = data;
		}).error(function(data) {
			$scope.error = data;
			$scope.logsActivos = "";
		});
	}
	
	$scope.modificarLog = function (id) {
		$location.path('/administracionLogs/administracionModificaLogs/'+id);
	}
	
		
	evaluaEstatus = function (data) {

		angular.forEach(data, function(value, key){

			if (value.logEstatus == "A"){	
				value.estado1 = "Habilitado";
				value.estado2 = "Desabilitar";				
			}else
				
			if((value.logEstatus == "D")){	
				value.estado1 = "Desactivado";
				value.estado2 = "Habilitar";
			}
	    });	
	}
	
	$scope.cambiaEstatus = function (estatus,id){
		  if (estatus == "D") {
			   showConfirmacion('¿Desea habilitar la consulta del log?',id,estatus);
		    } else 
		   if (estatus == "A") {
				showConfirmacion('¿Desea deshabilitar la consulta del log?',id,estatus);
		    }
	}
	
	 showAviso = function(messageTo) {
	        ModalService.showModal({
	          templateUrl: 'views/templatemodal/templateModalAviso.html',
	          controller: 'mensajeModalController',
	          inputs:{ message: messageTo}
	        }).then(function(modal) {
	          modal.element.modal();
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
	      
	 showConfirmacion = function(messageTo,id,estatus){
	            ModalService.showModal({
	            templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	              controller: 'mensajeModalController',
	                  inputs:{ message: messageTo}
	          }).then(function(modal) {
	              modal.element.modal();
	              modal.close.then(function(result) {
	                  if(result){
	                	  
                       logsService.cambiarEstatus(id,estatus).success(function(data) {	
                    	   consultaActivos();
	              		}).error(function(data) {
	              			
	              		});
	                	  
	                       
	                  }                    
	              }); 
	          });
	      };
	      
	      $scope.showNuevoLog = function() {
				$scope.log = {};
				ModalService.showModal({
					templateUrl: 'views/templatemodal/templateModalCrearLog.html',
		        	controller: 'modalCrearLogController',
				}).then(function(modal) {
			        modal.element.modal();
				});
	      }
	      
	      showAvisoWAction = function(messageTo) {
		      ModalService.showModal({
		        templateUrl: 'views/templatemodal/templateModalAviso.html',
		        controller: 'mensajeModalController',
		        inputs:{ message: messageTo}
		      	}).then(function(modal) {
		      		modal.element.modal();
		      		modal.close.then(function(result) {
		      			if(result){
		      				consultaActivos();
		      			}
		      		 });  
		      });
		};
	
	
	consultaActivos();
	
});