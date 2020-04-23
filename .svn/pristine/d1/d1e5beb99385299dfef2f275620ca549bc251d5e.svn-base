angular.module('siidfApp').controller('consultaBloqueohhController',function($scope, $filter, consultaBloqueohhService, ModalService,utileriaService) {
	
	$scope.tiposBloquehohh = {};
	$scope.tiposEstatus = {};
	$scope.order='fechaBloqueoOrder';
	$scope.bloqueohhVO ={
			estatusBloqueo :"",
			tipoBloqueo :"",
			placaOficial : "",
			numeroSeriehh : "",
			fechaInicio : "",
			fechaFin : ""	
	};
	
	$scope.listaBloqueohhVO ={};

	/* Modal Aviso */
	$scope.showAviso = function(messageTo) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/templateModalAviso.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	

	/* Modal Error */
	$scope.showError = function(messageTo) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/templateModalError.html',
        controller: 'mensajeModalController',
        	  inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	

	 obtenerTipoBloqueo = function() {
		 consultaBloqueohhService.obtenerTipoBloqueo().success(function(data) {
			$scope.tiposBloquehohh = data;
			$scope.bloqueohhVO.tipoBloqueo = $scope.tiposBloquehohh[0].codigo;
  			$scope.error = false;
 		}).error(function(data) {
 			$scope.tiposBloquehohh = {};
		});
	}
	
	 obtenerTipoEstatus = function() {
		 consultaBloqueohhService.obtenerTipoEstatus().success(function(data) {
				$scope.tiposEstatus = data;
				$scope.bloqueohhVO.estatusBloqueo = $scope.tiposEstatus[0].codigo;
  				$scope.error = false;
	 		}).error(function(data) {
 				$scope.tiposEstatus = {};
			});
		}
	 
	 obtenerReporte = function() {
		 consultaBloqueohhService.obtenerReporte().success(function(data) {
				$scope.tiposEstatus = data;
				$scope.bloqueohhVO.estatusBloqueo = $scope.tiposEstatus[0].codigo;
				$scope.error = false;
	 		}).error(function(data) {
	 			$scope.showAviso(data.message);
				$scope.tiposEstatus = {};
			});
		}
	 
	 $scope.consultaHandHeld = function(bloqueohhVO) {
		 if($scope.busq.$invalid){
				requiredFields();
			}else{
		 consultaBloqueohhService.consultaInformacionHh(  bloqueohhVO ).success(function(data) {
			    var listDates= ['fechaBloqueo','fechaDesbloqueo'];
			    for (var i = 0; i < data.length; i++) {							
			    	data[i].fechaBloqueo    = $filter('date')(data[i].fechaBloqueo,'dd/MM/yyyy HH:mm:ss'); 
			    	data[i].fechaDesbloqueo = $filter('date')(data[i].fechaDesbloqueo,'dd/MM/yyyy HH:mm:ss'); 
			    }
			    data = utileriaService.orderData(listDates,[],[],data);	
			    $scope.listaBloqueohhVO = data;
   				$scope.error = false;
	 		}).error(function(data) {
	 			$scope.showAviso(data.message);
				$scope.listaBloqueohhVO = {};
			});
		 }
		}
	 
	 requiredFields = function(){
			angular.forEach($scope.busq.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
	            $scope.showAviso("Formulario Incompleto");
			});
	};
	 validarCamposVacios = function(objeto){

		 angular.forEach(objeto, function(value, key){
			 objeto[key] = (value != "") ? value : "null";
 		    });
		 return  objeto;
	 }

	 $scope.downloadReporte = function() {
		 consultaBloqueohhService.obtenerReporteExcel().success(function(data, status, headers) {
		  var  filename  = headers('filename');
          var contentType = headers('content-type');
     	  var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
     	  save (file , filename);
    	  $scope.error = false;
	}).error(function(data) {
		$scope.error = data;
		$scope.listaBloqueohhVO = {};
	});
		 
	 }
	 
	function save(file, fileName) {
		 var url = window.URL || window.webkitURL;
    	 var blobUrl = url.createObjectURL(file);
    	 var a         = document.createElement('a');
    	 a.href        = blobUrl; 
		 a.target      = '_blank';
		 a.download    = fileName;
		 document.body.appendChild(a);
		 a.click();   
		}
 
	obtenerTipoEstatus();
	obtenerTipoBloqueo();
	
});