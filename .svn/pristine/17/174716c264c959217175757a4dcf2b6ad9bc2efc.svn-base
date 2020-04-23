angular.module('siidfApp').controller('adminUsuariosModificaClaveController', function($scope, $filter, $rootScope, $location, administracionService, ModalService, loginService, logOutService) {
	$scope.objeto = {
			contra: "",
			nuevo : "",
			repetir : ""	
	}
	$scope.guardado=false;
	
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
	
	$scope.guardar = function()
	{
		
		if($scope.objeto.contra == "" || $scope.objeto.nuevo == "" || $scope.objeto.repetir == "")
		{
			if ($scope.formChgPass.$invalid) {
		           angular.forEach($scope.formChgPass.$error, function (field) {
		             angular.forEach(field, function(errorField){
		           	 errorField.$setDirty();
		             })
		           });
		           $scope.showAviso("Formulario Incompleto", function(){});
		           return;
		     }
			
		}
		else
		{
			if($scope.objeto.nuevo != $scope.objeto.repetir)
			{
				$scope.showAviso("La contraseña nueva no coincide", function(){});
				$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
				$scope.formChgPass.$setPristine();
			}
			else
			{
				if($scope.objeto.contra == $scope.objeto.nuevo)
				{
					$scope.showAviso("La contraseña nueva no puede ser igual a la actual", function(){});
					$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
					$scope.formChgPass.$setPristine();
				}
				else
				{
					$scope.cambiarClave();
				}
			}
		}
	};
	
	$scope.cambiarClave = function(){
		administracionService.cambiarClave($scope.objeto.contra, $scope.objeto.nuevo, $scope.objeto.repetir)
		.success(function(data) {
			if(data[0] == 0){
				$scope.showAviso("El registro se actualizó correctamente", function(){});
				$scope.guardado=true;
			}
			else
			{
				$scope.showAviso(data[1], function(){});
				
			}
			$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
			$scope.formChgPass.$setPristine();
			
			$("#modalDynamic").modal("hide");
			$('.modal-backdrop').remove();
			$rootScope.estatusPassword =1;
		}).error(function(data) {
			if(data.message){
				$scope.showAviso(data.message, function(){});
			}else{
				$scope.showAviso(data, function(){});
			}
			$scope.objeto.contra = "";  $scope.objeto.nuevo = "";  $scope.objeto.repetir = "";
			$scope.formChgPass.$setPristine();
		});	
	};
	
	$scope.logout = function(){
		loginService.logout();
		logOutService.StopTimer();
		$location.path('/login');
		$('.modal-backdrop').remove();

	}
	
	
	$scope.tituloSug="<b>Sugerencia de contraseña</b></br>";
	$scope.tamanio="<div style='width: 70%; min-width:310px; text-align: justify;'> - Al menos 8 caracteres </br>";
	$scope.mayusculas="- Al menos una letra mayúscula </br>";
	$scope.minuscula="- Al menos una letra minúscula </br>";
	$scope.digitos="- Al menos un dígito </br>";
	$scope.caracEpecials="- Al menos un caracter del conjunto:<br/>" +
	"<center>!&nbsp#&nbsp$&nbsp%&nbsp&&nbsp(&nbsp)&nbsp*&nbsp+&nbsp,&nbsp-&nbsp.&nbsp/&nbsp:&nbsp;&nbsp" +
	"<&nbsp=&nbsp>&nbsp?&nbsp@&nbsp[&nbsp\\&nbsp]&nbsp_&nbsp|</center>";
	$scope.tituloEjemplo="<br/><center><b>Ejemplo de contraseñas:</b></center>";
	$scope.pasInvalid="Contraseña no valida: </br><center> teclo1+- </center>";
	$scope.passValid="Contraseña valida: </br> <center>Teclo3Logistica@</center> </div>";
});