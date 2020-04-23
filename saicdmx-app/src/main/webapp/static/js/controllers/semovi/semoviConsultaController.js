angular.module('siidfApp').controller('semoviConsultaController', function($scope, semoviService,ModalService,utileriaService) {
	console.log('Controller Semovi Consulta Archivos');
	$scope.parametros = {};
	$scope.mostrarTabla= false;
	$scope.order='fechaArchivoOrder';
	buscaCatTipoArchivos = function() {
		
		semoviService.buscaCatTipoArchivos().success(function(data) {
			$scope.tipoArchivo = data.listaInformacion;
			$scope.tipoArchivo.splice(0,0,{"catTipoArchivoId":0,"descripcion":"Todos","activo":true,"codigo":0,"tipo":"TODOS","cradoPor":-2,"fechaCracion":1465431174000}); 
			$scope.parametros.selected = data.listaInformacion[0];
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	validaParametros = function(fechaInicio,fechaFin){
	    if(fechaInicio == 'null' && fechaFin != 'null'){
	    	$scope.myForm.fechaInicio.$invalid = true;
			$scope.myForm.fechaInicio.$pristine = false;
			return false;
		}else{		
			if(fechaInicio != 'null' && fechaFin == 'null'){
				$scope.myForm.fechaFin.$invalid = true;
				$scope.myForm.fechaFin.$pristine = false;
				return false;
			}else{
				return true;
			}	
		}	
	}

	$scope.consultar = function(parametros) {
		
	   var tipoBusqueda = $scope.parametros.selected.codigo;
	   var fechaInicio = $scope.parametros.fechaInicio;
	   var fechaFin = $scope.parametros.fechaFin;
        fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/', '-') : 'null';
		fechaFin =	fechaFin ? fechaFin.replace('/', '-').replace('/', '-') : 'null';
	    
	   if(validaParametros(fechaInicio,fechaFin)){
		     semoviService.consultar(tipoBusqueda,fechaInicio,fechaFin).success(function(data) {
			    $scope.mostrarTabla= true;
				$scope.totalRegistros = data.length;
				data = utileriaService.orderData(['fechaArchivo'],[],[],data);
				$scope.consultaSemovi = data;
				$scope.error = false;
			}).error(function(data) {
				 $scope.mostrarTabla= false;
				 showAviso(data.message);
				$scope.totalRegistros = 0;
			});
		}else{
		   showAviso("Formulario Incompleto");
		}
	  }
	
	$scope.fechaArchivo = function(fechaArchivo){		
		var fecha = fechaArchivo.split('/').join('-');
		return fecha;
		}
	
	$scope.descargaExcel = function(nombreArchivo,codigo,fechaArchivo) {
		semoviService.descargaExcel(nombreArchivo,codigo,fechaArchivo).success(function(data, status, headers) {
			if(data.byteLength == 0) {
				showAviso("No se encontro el archivo a descargar");
			}else{
				var  filename  = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				save (file , filename);
			}
		}).error(function(data) {
			showAviso(data.message);
			$scope.mostrarTabla= false;
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
	
	buscaCatTipoArchivos();
});