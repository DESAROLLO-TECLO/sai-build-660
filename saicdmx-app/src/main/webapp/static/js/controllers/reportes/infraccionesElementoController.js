angular.module("siidfApp").controller("infraccionesElementoController", function($scope, ModalService,growl,infraccionesElementoService,ReporteDinamicosServices) {   
	 $scope.oficialesOriginal = [{nombreLista : "Resultados"},{nombreLista : "*Seleccione", listado : []}];
		$scope.oficiales = angular.copy($scope.oficialesOriginal);
		$scope.empleadoVO="";
		
		$scope.parametroBusquedaVO ={fechaInicio:"",fechaFin:"",placas:[]};
		var tamaño =0 ;
		var cont=0;
		var guardar=0;

		/*Metodo para obtener Id de empleados
		 * param placa
		 * return id*/
		$scope.BuscarEmpleados = function (Placas){
			tamaño=0;		
		if(Placas.length>0){   ///if principal 
				PlacasOficial = Placas.split(" ");  
				PlacasOficial2 = [];
				var placa="";
				//$scope.empleadoVO="";
				tamaño = PlacasOficial.length  -1 ;
				
				if($scope.oficiales[1].listado.length>0){
					var valores = $scope.oficiales[1].listado.length;
					
					for(y=0;y<= tamaño;y++){//valores a buscar 								
						for(lista = 0;lista<valores;lista++){
							if(PlacasOficial[y] === $scope.oficiales[1].listado[lista].placa){	
								guardar=1;
								continue;
							}
						}
						if(guardar === 0){
						    PlacasOficial2[cont]=PlacasOficial[y];
						    cont+=1;						    
						}else{
							guardar =0;
						}
					  }
					
					tamaño = PlacasOficial2.length-1 ;
					for( x = 0; x<=tamaño ;x++){
						if(x === tamaño){
							placa=placa+"'"+ PlacasOficial2[x]+  "'";
						}else{
							placa = placa + "'"+PlacasOficial2[x]+"',";
						}
					}
					
				}else{
					tamaño = PlacasOficial.length -1;
					for( x = 0; x<=tamaño ;x++){
						if(x === tamaño){
							placa=placa+"'"+ PlacasOficial[x]+  "'";
						}else{
							placa = placa + "'"+PlacasOficial[x]+"',";
						}
					}	
				}
			
			if(placa.length>0){	
			ReporteDinamicosServices.consultaEmpleados(placa).success(function(data){
					$scope.oficiales[0] = angular.copy($scope.oficialesOriginal[0]);
					$scope.oficiales[0].listado=data;
					$scope.flag=true;
					}).error(function(data){
						showAviso(data.message);
						$scope.oficiales = angular.copy($scope.oficialesOriginal);
					});
			}else {
				 //mostrar mensaje que hay oficiales agregados 
				growl.warning('Placas Agregadas', {ttl:3000});
			 }
		  }else{
			    growl.warning(' Escriba una o más placas a consultar ', {ttl:3000});
		  }
		};
		
		
		/*Funcion para traer las infracciones por fechas y placas de los oficiales */
		$scope.consultaInfraccionesEmpleado = function (parametrosVO){
			if ($scope.form.$invalid) {
	            angular.forEach($scope.form.$error, function (field) {
	              angular.forEach(field, function(errorField){
	            	  errorField.$setDirty();
	              })
	            });
	         //   showAviso('Formulario incompleto');
			}else{
				var idOficial="";
				var placas = "" ;				
				tamaño = $scope.oficiales[1].listado.length;
				
				for(x=0;x<tamaño;x++){
				   if(x+1 == tamaño ){
					   idOficial = idOficial + $scope.oficiales[1].listado[x].id;
					   placas = placas + $scope.oficiales[1].listado[x].placa;
				   }else{
					   idOficial = idOficial + $scope.oficiales[1].listado[x].id + ",";
					   placas = placas + $scope.oficiales[1].listado[x].placa + ",";
				   }	
				}
				
				var objectParametros={
						idPlacasOficiales: idOficial,
						fechaInicio:parametrosVO.fechaInicio==undefined ? "" : parametrosVO.fechaInicio ,
						fehaFin:parametrosVO.fechaFin==undefined ? "" : parametrosVO.fechaFin,
						placasOfiales:placas
				};
				$scope.ResultadoConsulta = [];
				infraccionesElementoService.consultaInfraccionesEmpleado(objectParametros).success(function(data){
					$scope.mensaje ="";
					 if(data.length===5000){
				    	  $scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
				    	  		   " de lo contrario modifique los parámetros de búsqueda.";
				      }
					$scope.ResultadoConsulta = data;
				}).error(function(data){
					showAviso(data.message);
					$scope.ResultadoConsulta=[];
				});
			}   
		};

		/*Metodos para descargar Excel */
		$scope.descargarExcelEmpleado = function(){
			infraccionesElementoService.descargarExcelEmpleado("infraccionesporElemento.xls").success(function(data, status, headers) {			
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

		testAviso = function() {
			growl.info('',{title:'Alerta de aviso'});
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

});