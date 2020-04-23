angular.module('siidfApp').controller('semoviArchivosController', function($scope, $filter, semoviService,ModalService) {
	console.log('Controller Semovi Carga Archivos');
	
	$scope.files = [];
	$scope.opc1 = function() {
		$scope.opcion1 = true;
		$scope.opcion2 = false;
		$scope.form2.$setPristine();
		$scope.show= false;
	}
	$scope.opc2 = function name() {
		$scope.opcion2 = true;
		$scope.opcion1 = false;
		$scope.form1.$setPristine();
		
	}

	
	obtenerArchivosActivos = function() {
		semoviService.obtenerArchivosActivos().success(function(data) {
			$scope.selected = data.listaInformacion[0];
			$scope.archivosActivos = data.listaInformacion;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.archivosActivos = {};
		});
	}
	
	
	$scope.submitForm = function(fechaArchivo) {
   		if ($scope.form1.$valid ) {
			var diaHoy = new Date();
			diaHoy = new Date(diaHoy.getFullYear(), diaHoy.getMonth(), diaHoy
					.getDate());
			var arrayFechaSeleccionada = fechaArchivo.toString().split("/");
			var fechaSeleccionada = new Date(arrayFechaSeleccionada[2],
					arrayFechaSeleccionada[1] - 1, arrayFechaSeleccionada[0]);

			if (fechaSeleccionada.getTime() < diaHoy.getTime()) {
		
				 semoviService.validaArchivoExiste(fechaArchivo,1).success(function(data) {	     
					 var value =  data["respuesta"];
					 if(value){
						 showConfirmacion("El archivo ya se generó previamente \n ¿Desea continuar?",fechaArchivo);					    
					 }else{ 
						  generaArchivoPuntoInfraccion(fechaArchivo.replace('/','-').replace('/','-'));
 			        }
				 }).error(function(data) {		
						showError(JSON.stringify(data));
				 });
			}else{
				$scope.mensaje = "Tiene que seleccionar una fecha menor al día de hoy";
				$scope.form1.fechaArchivo.$invalid = true;
				$scope.form1.fechaArchivo.$pristine = false; }
			}else{
			$scope.mensaje = "La fecha de emisión es requerida";
			$scope.form1.fechaArchivo.$invalid = true;
			$scope.form1.fechaArchivo.$pristine = false;	
		    }
    	}
	
	generaArchivoPuntoInfraccion =  function name(fechaArchivo) {
		semoviService.generaArchivoPuntoInfraccion(fechaArchivo).success(function(data) {	     		
			 $scope.resultado2 = data;						 
          	if($scope.resultado2.accion == 1){
          		descargaExcel($scope.resultado2.nombreArchvo,1,fechaArchivo);
    		} else{
    			showError("No se pudo descargar el expediente");
    		}
          	limpiarModel();
              
		 }).error(function(data) {
			 
			 $scope.error = data;
 
		 });

	}
	
	descargaExcel = function(nombreArchivo,codigo,fechaArchivo) {
		semoviService.descargaExcel(nombreArchivo,codigo,fechaArchivo).success(function(data, status, headers) {
			
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save (file , filename);
			$scope.error = false;
			
	}).error(function(data) {
		$scope.error = data;
		
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

	
	$scope.getFile = function (e) {
		$scope.files = [];
        $scope.$apply(function () {
        	$scope.files.push(e.files[0]);
        	if(!e.files[0]){
        		$scope.inputFileDefined = null;       		
        		$scope.form2.inputFileName.$invalid=true;
        		$scope.form2.inputFileName.$dirty=true;
       		 	$scope.mensaje  = ($scope.files.length > 0 && $scope.files[0] != undefined ? "" : "El archivo .xls es requerido");
        		
        	}else{
        		$scope.inputFileDefined = e.files[0].name;
        	}
        });
   
    };
    
    
    limpiarModel = function(){
    $scope.fechaPuntosCanceladas = "";
    $scope.fechaArchivo = "";
    $scope.inputFileDefined = ""; 	
    $scope.form2.fechaPuntosCanceladas.$pristine = true;
    $scope.form2.$setPristine();
    $scope.files = [];
    $scope.form2.fechaPuntosCanceladas.$pristine = true;
    $scope.form1.fechaArchivo.$pristine = true;
   	
    angular.forEach(
			    angular.element("input[type='file']"),
			    function(inputElem) {
			      angular.element(inputElem).val(null);
			    });
    }
    
	requiredFields = function(){
		angular.forEach($scope.form2.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
          });
		 $scope.mensaje  = ($scope.files.length > 0 && $scope.files[0] != undefined ? "" : "El archivo .xls es requerido");
		 
	};
	
	$scope.validaExiteArchvioPuntosCanceladas = function(fechaArchivo,tipoArchivo) {
		$scope.resultado;
		if($scope.form2.$invalid){
			requiredFields();
			showAviso("Formulario Incompleto");
			}else{
				if($scope.files[0].size >= 4194304){
					 showAviso("El tamaño del archivo ha sobrepasado el limite"); 
					 limpiarModel();
					 return false; 
				}else{
			  semoviService.validaArchivoExiste(fechaArchivo,tipoArchivo).success(function(data) {	     					
				  var value =  data["respuesta"];
				  if(value){
					   	showConfirmacionCargaArchivo("El archivo ya se generó previamente \n ¿Desea continuar?",$scope.files[0],fechaArchivo,tipoArchivo);	
					}else{
						cargarArchivosSemovi($scope.files[0],fechaArchivo,tipoArchivo);
					}   
					    
				 }).error(function(data) {		
						$scope.error = JSON.stringify(data);
				 });
			 	}	
			}
		}

	
	cargarArchivosSemovi = function(file,fechaArchivo,tipoArchivo) {
		 semoviService.cargarArchivosSemovi(file,fechaArchivo,tipoArchivo).success(function(data) {	     
		 limpiarModel();
		 $scope.resultado = JSON.stringify(data);
		 showAviso(data["message"]);
		 }).error(function(data) {		
			    
			
		 });
		
	}
	
	$scope.showAviso = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalAviso.html',
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
      
      $scope.showError = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalError.html',
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

      showConfirmacionCargaArchivo = function(messageTo,file,fechaArchivo,tipoArchivo){
          ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
            controller: 'mensajeModalController',
                inputs:{ message: messageTo}
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                if(result){
                	 cargarArchivosSemovi(file,fechaArchivo,tipoArchivo);
                }                     
            }); 
        });
    };

      showConfirmacion = function(messageTo,fechaArchivo){
          ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
            controller: 'mensajeModalController',
                inputs:{ message: messageTo}
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                if(result){
					generaArchivoPuntoInfraccion(fechaArchivo.replace('/','-').replace('/','-'));

                }                     
            }); 
        });
    };

	
	obtenerArchivosActivos();
	
});