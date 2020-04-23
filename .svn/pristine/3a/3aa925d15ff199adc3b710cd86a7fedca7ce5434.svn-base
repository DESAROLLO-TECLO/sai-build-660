angular.module('siidfApp').controller('consultaTramiteController', function(
		$scope, $filter, ModalService,utileriaService,atencionCiudadanaService, msCatalogoService, catalogoService,deDetalle,consultaAnteriorVO,$location) {
	
	$scope.viewHelpers = {search : false};
	$scope.valorFueAtendido = 0;
	$scope.valorRequerido = 0;
	$scope.listTramites = {};
	$scope.busquedaAvanzada=false;
	$scope.cantidadRegistrosMostrar=0;
	$scope.bloquearValorBusquedaTodo=true;

	$scope.parametrosBusqueda = {startDate:"", endDate:"", paramBusqueda:"", valorBusqueda:"", valorFueAtendido: "",busquedaAvanzada:false};
	
	tipoFechasBusqueda = function(){
		
		msCatalogoService.busquedaTipoFechasAll().success(function(data){
			$scope.catalogoTipoFechas = data;
			$scope.tipoFecha = data[8];
			$scope.error = false;
		}).error(function(data){
			$scope.catalogoTipoFechas = {}
			$scope.error = data;
		});
	};
	
	tiposDeBusquedaTramites = function(){
		catalogoService.tramitesTipoBusquedaAtencionCiudadana().success(function(data){
			$scope.opcionBusqueda = data;
			$scope.paramBusqueda = data[0].idParametro;
			$scope.error = false;
		}).error(function(data){
			$scope.opcionBusqueda = {};
			$scope.error = data;
		});
	};
	
	$scope.cambioComboParametro = function(op){
		if(op!= 1)
			{
			$scope.valorRequerido = true;
		  $scope.bloquearValorBusquedaTodo=false;
			}
		else
			{
			$scope.valorRequerido = false;
		    $scope.valorBusqueda="";
		    $scope.bloquearValorBusquedaTodo=true;
			}
	};
	
	$scope.busquedaTramitesParametros = function(){
		if($scope.consultaTramite.$invalid){
			requiredFields()
			$scope.viewHelpers.search = true;
		}else{
			validarParametros();	
			{
				atencionCiudadanaService.busquedaTramitesParametros($scope.parametrosBusqueda).success(function(data) {
					/*if(data.length>=20000){
						$scope.showAviso("Su consulta superó los 20,000 registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');	
					}*/
					if(data.length < 1){
						$scope.showAviso("No se encontraron registros.", 'templateModalAviso');
						$scope.mostrarTabla=false;
					}
					else
						{
						if(data.length >= $scope.cantidadRegistrosMostrar){
							$scope.showAviso("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
						}

						$scope.listTramites = data;
						atencionCiudadanaService.parametrosBusquedaAnterior=$scope.parametrosBusqueda;
						var txTipoTramites ="";
						for(var x in $scope.listTramites){
							var listTipoTamites = $scope.listTramites[x].listaTipoTramite;
							for(var y in listTipoTamites){
								if(isNaN(y))
									break;
								if(txTipoTramites!="")
									txTipoTramites = txTipoTramites + ", "
								txTipoTramites = txTipoTramites + listTipoTamites[y].nbTramite;
								$scope.listTramites[x].empId.empNombre= $scope.convertirNombresMayusYminus($scope.listTramites[x].empId.empNombre,false);
								$scope.listTramites[x].empId.empApePaterno= $scope.convertirNombresMayusYminus($scope.listTramites[x].empId.empApePaterno,true);
								$scope.listTramites[x].empId.empApeMaterno= $scope.convertirNombresMayusYminus($scope.listTramites[x].empId.empApeMaterno,true);
								
							}
							$scope.listTramites[x].txTipoTramites = txTipoTramites;
							txTipoTramites="";
							if($scope.listTramites[x].cdTipoPersona == "PF"){
								$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbCiudadano + " " + $scope.listTramites[x].nbCPaterno + " " + $scope.listTramites[x].nbMaterno;
							}else if($scope.listTramites[x].cdTipoPersona == "PM"){
								$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbEmpresa;
							}
						}
						$scope.mostrarTabla=true;
						}
				}).error(function(data){
					$scope.listTramites= {};
					$scope.showAviso(data.message, 'templateModalError');
					$scope.mostrarTabla=false;
				});
			}
			 
	
			
		}
		
	};
	
	$scope.cambioBusquedaAvanzada=function()
	{
		if(!$scope.busquedaAvanzada)
			{
			$scope.avanzadoNombre="";
			$scope.avanzadoAP="";
			$scope.avanzadoAM="";
	        $scope.avanzadoTelefono="";
			$scope.avanzadoCorreo="";
			$scope.avanzadoEmpresa="";
			}
		else
		{
	    $scope.paramBusqueda=1;
	    $scope.cambioComboParametro($scope.paramBusqueda);
       
		}
	}
	
	$scope.limpiarCampos=function()
	{
		$scope.tipoFecha = $scope.catalogoTipoFechas[8];
		$scope.checkTipoBusqudaFecha=true;
		$scope.FHInicio="";
		$scope.FHFin="";
		
		$scope.paramBusqueda = $scope.opcionBusqueda[0].idParametro;
		$scope.cambioComboParametro($scope.paramBusqueda);
		$scope.busquedaAvanzada=false;
		$scope.cambioBusquedaAvanzada();
	}
	
	validarParametros=function()
	{
	$scope.parametrosBusqueda.paramBusqueda=$scope.paramBusqueda;
	$scope.parametrosBusqueda.valorFueAtendido=$scope.valorFueAtendido;
	$scope.parametrosBusqueda.busquedaAvanzada=$scope.busquedaAvanzada;
	if($scope.checkTipoBusqudaFecha==true)
		{
		$scope.parametrosBusqueda.startDate="";
		$scope.parametrosBusqueda.endDate="";
		if($scope.tipoFecha.idTipoFecha!=1)
		{
		$scope.parametrosBusqueda.startDate=$scope.tipoFecha.fechaInicial; 
		$scope.parametrosBusqueda.endDate=$scope.tipoFecha.fechaFin;
		}
		}
	else
		{
		$scope.parametrosBusqueda.startDate=$scope.FHInicio;
		$scope.parametrosBusqueda.endDate=$scope.FHFin;
		}
	
	if(!$scope.busquedaAvanzada)
	{
		$scope.parametrosBusqueda.busquedaNombre="";
		  $scope.parametrosBusqueda.busquedaApellidoP="";
		  $scope.parametrosBusqueda.busquedaApellidoM="";
		  $scope.parametrosBusqueda.busquedaTel="";
		  $scope.parametrosBusqueda.busquedaCorreo="";
		  $scope.parametrosBusqueda.busquedaNomEmpresa="";
	if($scope.paramBusqueda!=1)
		{
		if($scope.paramBusqueda==4)
	    $scope.parametrosBusqueda.valorBusqueda=$scope.convertirNombresMayusYminus($scope.valorBusqueda,true);
		else
		$scope.parametrosBusqueda.valorBusqueda=$scope.valorBusqueda;	
	    
		  
		}
	}
	else
		{
		      $scope.parametrosBusqueda.paramBusqueda=1;
		        $scope.parametrosBusqueda.valorBusqueda="";
		          $scope.parametrosBusqueda.busquedaNombre=$scope.avanzadoNombre?$scope.avanzadoNombre:"";
				  $scope.parametrosBusqueda.busquedaApellidoP=$scope.avanzadoAP?$scope.avanzadoAP:"";
				  $scope.parametrosBusqueda.busquedaApellidoM=$scope.avanzadoAM?$scope.avanzadoAM:"";
				  $scope.parametrosBusqueda.busquedaTel=$scope.avanzadoTelefono?$scope.avanzadoTelefono:"";
				  $scope.parametrosBusqueda.busquedaCorreo=$scope.avanzadoCorreo?$scope.avanzadoCorreo:"";
				  $scope.parametrosBusqueda.busquedaNomEmpresa=$scope.avanzadoEmpresa?$scope.avanzadoEmpresa:"";
		}
	
		
	};
		
		requiredFields = function(){
			angular.forEach($scope.consultaTramite.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
			})
		};
		
		$scope.showAviso = function(messageTo, template) {
		      ModalService.showModal({
		        templateUrl: 'views/templatemodal/' + template + '.html',
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
		
	
		$scope.generarExcelReporte = function(){
			atencionCiudadanaService.reporteConsultaExcel().success(function(data, status, headers) {
				var  filename  = headers('filename');
	         	var contentType = headers('content-type');
	    	 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
	    	 	atencionCiudadanaService.downloadfile(file, filename);
	   	  		$scope.error = false;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
			});
		}
		
		$scope.descargarExpediente = function(idTramite){
			atencionCiudadanaService.expedienteIdTramite(idTramite).success(function(data,status,headers) {
				var  filename  = headers('filename');
	    	 	var file = new Blob([data], {type: 'application/pdf;base64,'});
	    	 	 if(file.size==0){
					 $scope.showAviso("Sin acceso al recurso.",'templateModalError');
	    	 	 }else
	    	 		 {
	    	 	atencionCiudadanaService.downloadfile(file, filename);
	    	 		 }
			}).error(function(data) {
				 $scope.showAviso("Sin acceso al recurso.",'templateModalError');
			});
		}
		

		$scope.descargarExpedienteTramite = function(folio){
			
			atencionCiudadanaService.descargarExpediente(folio, "TODO")
			.success(function(data, status, headers) {
				$scope.error = false;
				var filename = data.nbArchivo;
				var extension = data.nbArchivo.split('.')[1];
				var file = $scope.b64toBlob(data.archivo, "application/pdf");
				if(data.existeEnBD)
					atencionCiudadanaService.downloadfile(file, filename);
				else
					window.open(data.ruraExpediente, 'Download');
				$scope.error = false;
			}).error(function(data) {
				if(data.message){
					$scope.showError(data.message);
				}else{
					$scope.showError(data);
				}
			});
		};
		

		$scope.b64toBlob = function(b64Data, contentType, sliceSize) {
			  contentType = contentType || '';
			  sliceSize = sliceSize || 512;
			  var byteCharacters = atob(b64Data);
			  var byteArrays = [];
			  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
			    var slice = byteCharacters.slice(offset, offset + sliceSize);
			    var byteNumbers = new Array(slice.length);
			    for (var i = 0; i < slice.length; i++) {
			      byteNumbers[i] = slice.charCodeAt(i);
			    }
			    var byteArray = new Uint8Array(byteNumbers);
			    byteArrays.push(byteArray);
			  }
			  var blob = new Blob(byteArrays, {type: contentType});
			  return blob;
		};
		
		$scope.generaReporteTramite = function(idNexTramit){
	           
            atencionCiudadanaService.generaReporteTramite(idNexTramit)
            .success(function(data,status,headers){
                var filename = headers('filename');
                var contentType = headers('content-type');
                var file = new Blob([ data ], {
                    type : 'application/pdf;base64,'
                });
                atencionCiudadanaService.downloadfile(file, filename);
            })
            .error(function(data){
                $scope.showError("Imposible descargar reporte. Inténtelo más tarde.")
            })
        }
		

		$scope.redirigir = function(id_Tramite){
			$location.path('/detalleTramite/'+id_Tramite+'/'+"true");
				
		
		};
		
		$scope.convertirNombresMayusYminus=function(texto,esApellido)
		{
			var textoConvertido;
			if(texto)
				{
			if(!esApellido)
				{
				var arraytTexto=texto.split(' ');
	        	if(arraytTexto.length!=1)
	        		{
	        		for (x=0;x<arraytTexto.length;x++)
	            	{
	        			arraytTexto[x]=arraytTexto[x].charAt(0).toUpperCase() + arraytTexto[x].substring(1, arraytTexto[x].length).toLowerCase();
	        		}
	        		textoConvertido=arraytTexto.join (' ');
	        		}
	        	else
	        		{
	        		textoConvertido=arraytTexto[0].charAt(0).toUpperCase() + arraytTexto[0].substring(1, arraytTexto[0].length).toLowerCase();
	        		}
				}
			else
				{
				var arrayApellido=texto.split(' ');
	        	if(arrayApellido.length!=1)
	        		{
	        		for (x=0;x<arrayApellido.length;x++)
	            	{
	        			if(x+1!=arrayApellido.length)
	        			arrayApellido[x]=arrayApellido[x].toLowerCase();
	        		  else
	        		  arrayApellido[x]=arrayApellido[x].charAt(0).toUpperCase() + arrayApellido[x].substring(1, arrayApellido[x].length).toLowerCase();
	    	        	
	        		}
	        		textoConvertido=arrayApellido.join (' ');
	        		}
	        	else
	        		{
	        		textoConvertido=arrayApellido[0].charAt(0).toUpperCase() + arrayApellido[0].substring(1, arrayApellido[0].length).toLowerCase();
	        		}
				
				}
				}
		
        	
          return textoConvertido;
			
		}
		
	

	
	
	
	tipoFechasBusqueda();
	tiposDeBusquedaTramites();
	
	if(deDetalle)
		{


		atencionCiudadanaService.busquedaTramitesParametros(atencionCiudadanaService.parametrosBusquedaAnterior).success(function(data) {
			if(data.length < 1){
				$scope.showAviso("No se encontraron registros.", 'templateModalAviso');
				$scope.mostrarTabla=false;
			}
			else
				{
				$scope.listTramites = data;
				var txTipoTramites ="";
				for(var x in $scope.listTramites){
					var listTipoTamites = $scope.listTramites[x].listaTipoTramite;
					for(var y in listTipoTamites){
						if(isNaN(y))
							break;
						if(txTipoTramites!="")
							txTipoTramites = txTipoTramites + ", "
						txTipoTramites = txTipoTramites + listTipoTamites[y].nbTramite;
					}
					$scope.listTramites[x].txTipoTramites = txTipoTramites;
					txTipoTramites="";
					if($scope.listTramites[x].cdTipoPersona == "PF"){
						$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbCiudadano + " " + $scope.listTramites[x].nbCPaterno + " " + $scope.listTramites[x].nbMaterno;
					}else if($scope.listTramites[x].cdTipoPersona == "PM"){
						$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbEmpresa;
					}
				}
				$scope.mostrarTabla=true;
				}
		}).error(function(data){
			$scope.listTramites= {};
			$scope.showAviso(data.message, 'templateModalError');
			$scope.mostrarTabla=false;
		});
		}
	
	buscarInformacionTooltipAyuda=function()
	{
		atencionCiudadanaService.informacionTooltip().success(function(data){
        	
            $scope.informacionAyuda=data;
            $scope.cantidadRegistrosMostrar=$scope.informacionAyuda.CANTIDAD_REGISTROS_MOSTRAR_AC;
                
            })
            .error(function(data){
          	  $scope.showAviso("Sin acceso al recurso.",'templateModalError');
            })	
	}
	
	   buscarInformacionTooltipAyuda();
	
	
});