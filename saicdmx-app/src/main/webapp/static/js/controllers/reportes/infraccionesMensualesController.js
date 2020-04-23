angular.module("siidfApp").controller("infraccionesMensualesController", function($scope, ModalService,infraccionesTotalesServices) {
	$scope.parametroBusquedaVO={depositoVO:"",anioVO:"",mesVO:""};
	$scope.listaDepositos=[];
	$scope.listaAnios=[];
	$scope.ResultadoConsulta=[];
	
	$scope.listaMes=[{id:1,nombre:"Enero"},{id:2,nombre:"Febrero"},{id:3,nombre:"Marzo"},{id:4,nombre:"Abril"},{id:5,nombre:"Mayo"},{id:6,nombre:"Junio"},
			         {id:7,nombre:"Julio"},{id:8,nombre:"Agosto"},{id:9,nombre:"Septiembre"},{id:10,nombre:"Octubre"},{id:11,nombre:"Noviembre"},{id:12,nombre:"Diciembre"}];
	
	
	/*Funciones que se inician al cargar la pagina */
	consultaDepositos = function (){
		$scope.listaDepositos=[];
		infraccionesTotalesServices.Depositos().success(function(data){
				$scope.listaDepositos=data;
				$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.listaDepositos = [];
		});
	};
	
	consultaAnios = function (){
		$scope.listaAnios=[];
		infraccionesTotalesServices.AniosInfracciones().success(function(data){
				$scope.listaAnios=data;
				$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			showAviso(data.message);
			$scope.listaAnios=[];
		});
	};
	
	/*Funciones para consultar infracciones y generar reporte excel*/
	$scope.consultaInfraccionesMensuales = function(parametroVO){
		if ($scope.form.$invalid) {
            angular.forEach($scope.form.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
	   }else{
		   $scope.ResultadoConsulta=[];
		infraccionesTotalesServices.consultaInfraccionesMensuales(parametroVO).success(function(data){
			$scope.mensaje ="";
			if(data.length===5000){
		    	  $scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
		    	  		   " de lo contrario modifique los parámetros de búsqueda.";
		      }
			$scope.ResultadoConsulta=data;
		}).error(function(data){
			$scope.error = data;
			showAviso(data.message);
			$scope.ResultadoConsulta=[];
		});
	   }
	};
	
	/* Metodo para generar Excel */
	$scope.descargaExcel = function(){///////////////////////////////////////////////
		infraccionesTotalesServices.descargarExcel("TotalInfracionesMensuales.xls").success(function(data, status, headers) {			
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save (file , filename);
			
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

	 showAviso = function(messageTo) {
	        ModalService.showModal({
	          templateUrl: 'views/templatemodal/templateModalAviso.html',
	          controller: 'mensajeModalController',
	          inputs:{ message: messageTo}
	        }).then(function(modal) {
	          modal.element.modal();
	        });
	    };
	/*Funciones al cargar la pagina llena el select de Depositos,año y mes */
	consultaDepositos();
	consultaAnios();
});