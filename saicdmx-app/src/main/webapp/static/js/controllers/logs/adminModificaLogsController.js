angular.module('siidfApp').controller('adminModificaLogsController', function($scope,logsService,ModalService,$routeParams) {
	
	consultaPorId = function(id) {
    	logsService.consultaPorId(id).success(function(data) {	
			$scope.consultaId = data;	
			consultaPerfilesActivos(data.logId);
			consultaPerfilesNoActivos(data.logId);
			
		}).error(function(data) {
			
			$scope.consultaId= {};
		});
	}
	
	consultaPerfilesActivos = function(id) {
    	logsService.getPerfilesActivosPorLog(id).success(function(data) {	
			
			$scope.activos = data;
			
		}).error(function(data) {
			
			$scope.activos= {};
		});
	}
	consultaPerfilesNoActivos = function(id) {
    	logsService.getPerfilesNoActivosPorLog(id).success(function(data) {	
			
			$scope.noActivos = data;
		
		}).error(function(data) {
			
			$scope.noActivos= {};
		});
	}
	
	$scope.agregar = function (id) {
	
		
	  var logVO = ({logId :$scope.consultaId.logId,
				   perfilId : id,
				      });
	  
	
	  logsService.agregarPerfil(logVO).success(function(data) {	
		  
		  consultaPerfilesActivos($scope.consultaId.logId);
		  consultaPerfilesNoActivos($scope.consultaId.logId);

		}).error(function(data) {
		
		});
	  
	  
	 
	}	
	
	$scope.eliminar = function (id) {
		
		  var logVO = ({logId :$scope.consultaId.logId,
			   perfilId : id,
			      });
 

	logsService.eliminarPerfil(logVO).success(function(data) {	

 		consultaPerfilesActivos($scope.consultaId.logId);
 		consultaPerfilesNoActivos($scope.consultaId.logId);

	    }).error(function(data) {
	
	});
 
 
	}	
	
	
	$scope.modificarLog = function (log) {
			
		if ($scope.userForm.$invalid) {
			
			angular.forEach($scope.userForm.$error, function (field) {
	            angular.forEach(field, function(errorField){
	            errorField.$setDirty();
	            })
	           });
			
			showAviso('Formulario Incompleto');
	}else{

		
		 var logVO = ({
			   logId :log.logId,
			   rutaArchivo:log.rutaArchivo,
			   logTipoArchivos : log.tipoExtensiones,
			   logNombre : log.logNombre,
			   logDescripcion : log.logDescripcion,
			   
			      });


	logsService.actualizar(logVO).success(function(data) {	
	
       if (data.resultadoPrincipal == "-1"){
			showError(data.mensaje);
		}else{
			showAviso(data.mensaje); 
			consultaPorId($routeParams.id);
		}
		
		

	    }).error(function(data) {
	
	    });
	
	}

	}	
	

	$scope.doTheBack = function() {
		  window.history.back();
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
      
     showError = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalError.html',
          controller: 'mensajeModalController',
              inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
      };
      
      $scope.showConfirmacion = function(messageTo){
          ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
            controller: 'mensajeModalController',
                inputs:{ message: messageTo}
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                if(result){
                     
                }                      
            }); 
        });
    };
	
	
	consultaPorId($routeParams.id);
	
});