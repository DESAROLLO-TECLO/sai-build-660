angular.module('siidfApp').controller(
		'consultaArchivoOrigenController',
		function($scope,$route,$filter,$location,opcion,busqueda,switchRangoFecha1,periodoFecha1,startDate1,endDate1,estatusProceso1,opcTipoArchivo1,showAlert,ModalService,radarArchivoProcesadosService,catalogoService,
				$location, utileriaService,fmService,$routeParams) {
			
			$scope.opcion=opcion;
//Lista de archivos tipo
//			$scope.opcTipoArchivo = {
//					opcionesDisponibles : [ {
//						id : '1',
//						name : 'Archivo Local Fisico'
//						}, {
//						id : '2',
//							name : 'Archivo Local Moral'
//						}, {
//							id : '3',
//							name : 'Archivo Local Foraneo'
//						}
//						],
//						selectTipo : {
//							id : '1',
//							name : 'Archivo Local Fisico'
//						}
//				
//					};
//			
			//Aviso al consultar
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
				
				
				/* Modal Confirmacion */
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
				
				/* Modal Error */
				$scope.showError = function(messageTo) {
			      ModalService.showModal({
			        templateUrl: 'views/templatemodal/templateModalError.html',
			        controller: 'mensajeModalController',
			        	  inputs:{ message: messageTo}
			      }).then(function(modal) {
			        modal.element.modal();
			      });
				}
				
				
			$scope.validarNum=function(numero){
				
				if(isNaN(numero)){
					
					return 0;
				}else{
				return Math.floor(numero);;
				}
			}
			
			
//			$scope.viewFilters = false;
//			$scope.viewInformation = false;
//			$scope.opcion = 1;
//			$scope.viewAll = true;
//			$scope.dfecha = {
//				tipoFecha : '-1',
//				startDate : '',
//				endDate : ''
//			};
			
//			$scope.historyBusqueda=b	
				if($scope.opcion==1){
					$scope.viewFilters = false;
				    $scope.viewByT = false;
					$scope.viewAll = true;
					
					
				}else
				{
					$scope.viewFilters = true;
				    $scope.viewByT = true;
					$scope.viewAll = false;
				}
		    
//		    $scope.mostrarTabla = false;
			$scope.parametros = {};
		    $scope.archivoVO = [];

		    
		  
		    //Variables temporales para reporte con la busqueda anterior
		    
		    
//		    var $scopeswitchRangoFechaTemp =0; 
//			var periodoFechaTemp =0; 
//			var startDateTemp='';
//			$scope.parametroBusqueda.endDate,
//			var estatusProceso,	
//			opcTipoArchivo
		    
		    //
		    
		    
		    ///
		    
		    
		    var busquedaVO ={};
		  
			var estatusProceso = "";
			var opcTipoArchivo="";
			$scope.parametroBusqueda = {};
			$scope.comboLlenarEstatus = [];
			$scope.switchRangoFecha = 0;
			var consultaAvanzada=false;
		
			var fInicio="";
			var fFinal ="";
			var rangoFechaTemp=0;
			var periodoFechaTemp=0;
			var startDateTemp="";
			var endDateTemp="";
			var estatusProcesoTemp="";
			var opcTipoArchivoTemp="";

			
			
			
			//$scope.opcTipoArchivo.selectTipo.id=0;
		//var tipoArchivo=$scope.opcTipoArchivo.selectTipo.id;
			
			
			$scope.disabledTipoFecha = false;
			$scope.disabledFechas = true;
		//	$scope.parametroBusqueda.selectMultipleOp1='';
	

			$scope.option = {};
			$scope.option.origenProceso = 1;
			$scope.order = 'fecha_COMPLEMENTACIONOrder';

			
		
			
			$scope.getVal = function() {
				if ($scope.opcion == 2) {
					$scope.viewFilters = true;
					$scope.viewByT = true;
					$scope.viewAll = false;
					$scope.viewInformation = false;
					 $scope.mostrarTabla = false;	

				} else {
					$scope.viewFilters = false;
					$scope.viewByT = false;
					$scope.viewAll = true;
					$scope.viewInformation = false;
					 $scope.mostrarTabla = false;	
				}
			}

			
			
			
			
			
			
			filtroTipoEstatus = function(){
				radarArchivoProcesadosService.comboTipoEstatusArchivo().success(function(datos) {
					$scope.comboLlenarEstatus = datos;
					
					
					//$scope.comboLlenarEstatus.push({value:"4", label: "Sin Detecciones"});
					//$scope.comboLlenarEstatus.shift();
					
					for (var esP = 0; esP < $scope.comboLlenarEstatus.length; esP++) {
						var estPro1 = $scope.comboLlenarEstatus[esP].label;
						var estPro2 = $scope.toTitleCase(estPro1);
						$scope.comboLlenarEstatus[esP].label = estPro2;
					}
					$scope.parametroBusqueda.selectMultipleOp1 = [3,5];
					//$scope.parametroBusqueda.estatusproceso = $scope.comboLlenarEstatus[0].value;
				}).error(function(datos) {
					$scope.error = datos;
					$scope.datos = {};
				});
			}
			
			
			
			filtroTipoArchivo = function(){
				radarArchivoProcesadosService.comboTipoArchivoOrigen().success(function(datos) {
					$scope.comboLlenarArchivo = datos;
					
					
					//$scope.comboLlenarEstatus.push({value:"4", label: "Sin Detecciones"});
					//$scope.comboLlenarEstatus.shift();
					
					for (var esP = 0; esP < $scope.comboLlenarArchivo.length; esP++) {
						var estPro1 = $scope.comboLlenarArchivo[esP].label;
						var estPro2 = $scope.toTitleCase(estPro1);
						$scope.comboLlenarArchivo[esP].label = estPro2;
					}
					$scope.parametroBusqueda.selectMultipleOp2 = [3];
					//$scope.parametroBusqueda.estatusproceso = $scope.comboLlenarEstatus[0].value;
				}).error(function(datos) {
					$scope.error = datos;
					$scope.datos = {};
				});
			}
			
			
			
			filtroPeriodoFecha = function(){
				catalogoService.filtroPeriodoFecha(true).success(function(data) {
					$scope.filterPeriodoFecha = data;
					$scope.parametroBusqueda.periodoFecha = $scope.filterPeriodoFecha[5].codigo;
				}).error(function(data){
					$scope.error = data;
					$scope.filterPeriodoFecha = {};
				});
			}
			
			
			
			busquedaHistorica=function(){
				if($scope.opcion==2){
				
				radarArchivoProcesadosService.consultaArchivoOrigen(switchRangoFecha1,periodoFecha1,startDate1,endDate1,estatusProceso1,opcTipoArchivo1).success(function(datos){
					rangoFechaTemp=switchRangoFecha1;
					periodoFechaTemp=periodoFecha1;
					startDateTemp=startDate1;
					endDateTemp=endDate1;
					estatusProcesoTemp=estatusProceso1;
					opcTipoArchivoTemp=opcTipoArchivo1;
					
					if(datos.length < 1){
						$scope.showAviso("No se encontraron registros", 'templateModalAviso');
						$scope.mostrarTabla=false;
					}
					else
						{
					 $scope.archivoVO = datos;
					 for(var y in $scope.archivoVO)
					 {
                      if($scope.archivoVO[y].fh_Ini_Carga)
                    	  {
                    	  $scope.archivoVO[y].fechaStringFiltro=$filter('formatDate')($scope.archivoVO[y].fh_Ini_Carga,'DD/MM/YYYY');
                    	  }
					 }
				     $scope.mostrarTabla = true;	
						}
				
					
				 }
				).error(function(datos){
					
					$scope.mostrarTabla = false;
					showAlert.aviso(datos.message);
					$scope.totalRegistros = 0;
					$scope.archivoVO = [];
				});
				
				
				}
				}
			
			
			$scope.consultaArchivoOrigenTodo=function(todo){
				if($scope.opcion==1){
					
					if(todo==1){
						rangoFechaTemp=0;
						fInicio="";
						fFinal ="";
						consultaAvanzada=false;
						busqueda=false;
						
					}
				
				
				radarArchivoProcesadosService.consultaArchivoOrigenTodo().success(function(datos){
					if(datos.length < 1){
						$scope.showAviso("No se encontraron registros", 'templateModalAviso');
						$scope.mostrarTabla=false;
					}
					else
						{
					 $scope.archivoVO = datos;
					 for(var y in $scope.archivoVO)
					 {
                      if($scope.archivoVO[y].fh_Ini_Carga)
                    	  {
                    	  $scope.archivoVO[y].fechaStringFiltro=$filter('formatDate')($scope.archivoVO[y].fh_Ini_Carga,'DD/MM/YYYY');
                    	  }
					 }
				     $scope.mostrarTabla = true;
						}
				
					
				 }
				).error(function(datos){
					
					$scope.mostrarTabla = false;
					showAlert.aviso(datos.message);
					$scope.totalRegistros = 0;
					$scope.archivoVO = [];
				});
				
				}
			}
			
			
		
			    defaultValues = function(){
				filtroTipoEstatus();
				filtroTipoArchivo();
				filtroPeriodoFecha();
				busquedaHistorica();
				$scope.consultaArchivoOrigenTodo();
				
		   // ComboTipoEstatus();
			
			$scope.parametroBusqueda = {
				startDate : "", 
				endDate : "", 
//				nombrelote : "", 
//				salariomin : 0, 
//				tipoRadar : 0,
				selectMultipleOp1 : "",
				selectMultipleOp2 : ""
			};
		}
		defaultValues();
		
		
		$scope.busquedaRefresh=function(){
			if(estatusProceso1==' '||$scope.opcion==1){
				$scope.consultaArchivoOrigenTodo();
				}else{
			
			
			radarArchivoProcesadosService.consultaArchivoOrigen(
					
					rangoFechaTemp,
					periodoFechaTemp,
					startDateTemp,
					endDateTemp,
					estatusProcesoTemp,
					opcTipoArchivoTemp
					
						
			 ).success(function(datos){
				 if(datos.length < 1){
						$scope.showAviso("No se encontraron registros", 'templateModalAviso');
						$scope.mostrarTabla=false;
					}
				 else
					 {
				 
				 $scope.archivoVO = datos;
				 for(var y in $scope.archivoVO)
				 {
                  if($scope.archivoVO[y].fh_Ini_Carga)
                	  {
                	  $scope.archivoVO[y].fechaStringFiltro=$filter('formatDate')($scope.archivoVO[y].fh_Ini_Carga,'DD/MM/YYYY');
                	  }
				 }
			     $scope.mostrarTabla = true;	
					 }
			     
			
			 }
			).error(function(datos){
				
				$scope.mostrarTabla = false;
				showAlert.aviso(datos.message);
				$scope.totalRegistros = 0;
				$scope.archivoVO = [];
			});
			}
		}
		
		
		
		$scope.consultaArchivo=function(){
			
			if ($scope.form.$invalid) {
				showAlert.requiredFields($scope.form.fInicio);
				showAlert.aviso("Formulario incompleto")
			}else if($scope.switchRangoFecha==1 && ($scope.parametroBusqueda.startDate=="" ||
					$scope.parametroBusqueda.endDate =="") ){
				showAlert.aviso("Favor de ingresar rango de fecha")
			}
			
			else{
			
			
				//showAlert.requiredFields($scope.form.fInicio);
			
		
				
//				$scope.estatusProcesoTitulo = $.grep($scope.comboLlenarEstatus, function (option) {	
//					return option.value == $scope.parametroBusqueda.tipoDeteccion;
//				})[0].label;		
			//$scope.parametroBusqueda.selectMultipleOp1
				estatusProceso = "";
				opcTipoArchivo="";
			   $scope.estatusProcesoTitulo = "";
			   $scope.estatusProcesoTitulo2 = "";
				
//			    estatusProceso = "";
				var estatusProcesoStr = "";
				var estatusProcesoStr2 = "";
				var j = 0;
				var k = 0;
				
				
				$scope.parametroBusqueda.selectMultipleOp1.forEach(function(element) {
					//console.log(element);
					estatusProcesoStr = $.grep($scope.comboLlenarEstatus, function (option) {
						return option.value == element;
					})[0].label;
				
				if((j + 1) == $scope.parametroBusqueda.selectMultipleOp1.length){
						estatusProceso += element;
						$scope.estatusProcesoTitulo += estatusProcesoStr;
					}else{
						estatusProceso += element + ",";
						$scope.estatusProcesoTitulo += estatusProcesoStr + ", ";
					}
					j++;
			});
				
				
				
				$scope.parametroBusqueda.selectMultipleOp2.forEach(function(element2) {
					//console.log(element);
					estatusProcesoStr2 = $.grep($scope.comboLlenarArchivo, function (option) {
						return option.value == element2;
					})[0].label;
				
				if((k + 1) == $scope.parametroBusqueda.selectMultipleOp2.length){
					opcTipoArchivo += element2;
						$scope.estatusProcesoTitulo2 += estatusProcesoStr2;
					}else{
						opcTipoArchivo += element2 + ",";
						$scope.estatusProcesoTitulo2 += estatusProcesoStr2 + ", ";
					}
					k++;
			});
					
				
				
//				if(estatusProceso == "" || estatusProceso == null){
//					estatusProceso = "4";
//				}
				
				
//				$scope.parametroBusqueda.startDate = new Date($scope.parametroBusqueda.startDate);
//				$scope.parametroBusqueda.startDate = moment().format('YYYY-MM-DD');
				
				radarArchivoProcesadosService.consultaArchivoOrigen(
						
						$scope.switchRangoFecha,
						$scope.parametroBusqueda.periodoFecha,
						$scope.parametroBusqueda.startDate,
						$scope.parametroBusqueda.endDate,
						estatusProceso,	
						opcTipoArchivo
						
							
				 ).success(function(datos){
					 if(datos.length < 1){
							$scope.showAviso("No se encontraron registros", 'templateModalAviso');
							$scope.mostrarTabla=false;
						}
					 else
						 {
					 $scope.archivoVO = datos;
					 for(var y in $scope.archivoVO)
					 {
                      if($scope.archivoVO[y].fh_Ini_Carga)
                    	  {
                    	  $scope.archivoVO[y].fechaStringFiltro=$filter('formatDate')($scope.archivoVO[y].fh_Ini_Carga,'DD/MM/YYYY');
                    	  }
					 }
				     $scope.mostrarTabla = true;	
				     busqueda=false;
				     consultaAvanzada=true;
				          }
				     
				     
				     	rangoFechaTemp=$scope.switchRangoFecha;
						periodoFechaTemp=$scope.parametroBusqueda.periodoFecha;
						startDateTemp=$scope.parametroBusqueda.startDate;
						endDateTemp=$scope.parametroBusqueda.endDate;
						estatusProcesoTemp=estatusProceso;
						opcTipoArchivoTemp=opcTipoArchivo;
					
				 }
				).error(function(datos){
					
					$scope.mostrarTabla = false;
					showAlert.aviso(datos.message);
					$scope.totalRegistros = 0;
					$scope.archivoVO = [];
				});
							
			}
		}
		
		
	
		
		$scope.actualizaConfirm =function(idArchivo){
			
			$scope.showConfirmacion("¿Desea Cancelar el Archivo? ", function(){ $scope.cancelaArchivoOrigen(idArchivo) });	
		}
		
		
		$scope.cancelaArchivoOrigen=function(idArchivo){
			radarArchivoProcesadosService.cancelaArchivoOrigen(idArchivo).success(function(datos){
			
			
				
				if(datos==0){
					   $scope.showAviso("El archivo se canceló con exito",function(){$scope.busquedaRefresh()});
					
				}else{
					$scope.showAviso("El archivo esta en proceso, no se puede cancelar");
					
				}
					
				
			}).error(function(datos){
				
				$scope.showError("Hubo un error en la base de datos, espere un momento");
			});
		}
		
		
		
		
		$scope.descargaArchivoOrigen = function(idArchivo) {

			radarArchivoProcesadosService.descargaArchivoOrigen(idArchivo).success(
					function(data, status, headers) {

						var filename = headers('filename');
						var contentType = headers('content-type');
						var file = new Blob([ data ], {
							type : ' text/plain'
						});
						save(file, filename);

					}).error(function(data) {
					
			       $scope.showError(data.message);

			});
		}

		function save(file, fileName) {
			var url = window.URL || window.webkitURL;
			var blobUrl = url.createObjectURL(file);
			var a = document.createElement('a');
			a.href = blobUrl;
			a.target = '_blank';
			a.download = fileName;
			document.body.appendChild(a);
			a.click();
		}
		
		

		
		$scope.activaRangoFecha = function(newValue, oldValuee){
			if(newValue == 1){
				$scope.disabledTipoFecha = true;
				$scope.disabledFechas = false;
				$scope.parametroBusqueda.startDate = '';
				$scope.parametroBusqueda.endDate = '';
			
			}else{
				$scope.disabledTipoFecha = false;
				$scope.disabledFechas = true;
				$scope.parametroBusqueda.startDate = '';
				$scope.parametroBusqueda.endDate = '';
				
			}
		}
		
		
		
		$scope.downloadReporteExcel = function() {
			if(busqueda){
				consultaAvanzada=busqueda;
				
				
				

					rangoFechaTemp=switchRangoFecha1;
					periodoFechaTemp=periodoFecha1;
					startDateTemp=startDate1;
					endDateTemp=endDate1;
					estatusProcesoTemp=estatusProceso1;
					opcTipoArchivoTemp=opcTipoArchivo1;
				
				
				
			}
			
			
			
			radarArchivoProcesadosService.obtenerReportExcelOrigen($scope.opcion,consultaAvanzada,rangoFechaTemp,periodoFechaTemp,startDateTemp,endDateTemp,estatusProcesoTemp,opcTipoArchivoTemp )
			.success(function(data, status, headers) {
				var filename = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				save(file , filename);
				$scope.error = false;
			})
			.error(function(data) {
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
		
		
		
		
		$scope.redirigir = function(id_Archivo,cantidad_Cargada){
			
			if(cantidad_Cargada!=0 && cantidad_Cargada !=null){
			if(busqueda){
			
				$location.path('/consultaArchivoDeteccion/'+id_Archivo+'/'+$scope.opcion+'/'+switchRangoFecha1+'/'+periodoFecha1+'/'+startDate1+'/'+endDate1+'/'+estatusProceso1+'/'+opcTipoArchivo1);
				
			// id_Archivo/:switchRangoFecha/:periodoFecha/:startDate/:endDate/:estatusProceso/:opcTipoArchivo
//			$location.path('/consultaArchivoDeteccion/'+id_Archivo+'/'+$scope.switchRangoFecha+'/'+$scope.parametroBusqueda.periodoFecha+'/'+$scope.parametroBusqueda.startDate+'/'+$scope.parametroBusqueda.endDate+'/'+estatusProceso+'/'+$scope.opcTipoArchivo.selectTipo.id);
//			$location.path('/consultaArchivoOrigen/'+true);
			}else{
				
				
				if(rangoFechaTemp==1){
//					$scope.parametroBusqueda.startDate = new Date($scope.parametroBusqueda.startDate);
//					$scope.parametroBusqueda.endDate = new Date($scope.parametroBusqueda.endDate);
					
					fInicio = startDateTemp.split('/').join('-');
					fFinal = endDateTemp.split('/').join('-');
//					$scope.parametroBusqueda.startDate =moment($scope.parametroBusqueda.startDate).format('DD-MM-YYYY');
//					$scope.parametroBusqueda.endDate =moment($scope.parametroBusqueda.endDate).format('DD-MM-YYYY');
//					$scope.parametroBusqueda.endDate=moment($scope.parametroBusqueda.endDate).format('DD-MM-YYYY');
					
							
		
				}
				
				if(estatusProcesoTemp==""){
					estatusProcesoTemp='NA';
				}
				if(opcTipoArchivoTemp==""){
					opcTipoArchivoTemp='NA';
				}
				if(fInicio=="" || fFinal==""){
					fInicio='NA';
					fFinal='NA';
				}
				
	
				
				$location.path('/consultaArchivoDeteccion/'+id_Archivo+'/'+$scope.opcion+'/'+rangoFechaTemp+'/'+periodoFechaTemp+'/'+fInicio+'/'+fFinal+'/'+estatusProcesoTemp+'/'+opcTipoArchivoTemp);

//				$location.path('/consultaArchivoDeteccion/'+id_Archivo+'/'+switchRangoFecha1+'/'+periodoFecha1+'/'+startDate1+'/'+endDate1+'/'+estatusProceso1+'/'+opcTipoArchivo1);
			}
		}
			else{
				showAlert.aviso("El archivo no tiene detecciones");
				
			}
			}
		
		
		
			
		
		//Convierte Mayusculas
		$scope.toTitleCase = function (str) {
			
			str = str.toLowerCase();
			var pieces = str.split(" ");
		    for(var i = 0; i < pieces.length; i++){

		        
		    	pieces[i] = pieces[i][0].toUpperCase() + pieces[i].substr(1);
		    }
		    return pieces.join(" ");
		};
		
		
		});