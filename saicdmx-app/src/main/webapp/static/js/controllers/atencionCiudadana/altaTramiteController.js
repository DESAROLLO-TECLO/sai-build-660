angular.module('siidfApp').controller( 
    'altaTramiteController',
    function($scope,$window,$compile,showAlert, $filter,$timeout, atencionCiudadanaService, infraccionService, catalogoService, infraccionAllDataService, ModalService) {

        $scope.msgAviso = ""; 
        $scope.msgError = "";
        $scope.tipoTramite= "";
        $scope.tipoDocumento="";
        $scope.idNexTramit="";
	    var dateAfter = moment().add(+15, 'm');
	    $scope.nuevoTramiteVO = {};
	    $scope.DG = {};
        $scope.DG.empleado = {};
        $scope.DG.empleado.valido = false;
        $scope.DG.empleado.placaColor = "black";
        $scope.DV = {};
        $scope.DD = {};
        $scope.DC = {};
        $scope.DV.catalogoVehiculosModelo={};
        $scope.DV.sectorPublico = 'N';
        $scope.DV.origen ='N';
        $scope.DV.cuentaConPlaca ='S';
        $scope.DV.placaDisabled = false;
        $scope.DV.showSelectGarantiaCatDoc = false;	
        $scope.camposRequeridos=false;
        $scope.tipoPersona = false;
        var fileURL="";
        var tamañoDeCorreo=1;
        var longitud=0
        $scope.arregloAuxilarCorreos=[];
        $scope.tipoPersonaFotocivicas=true;
        $scope.errorLongitud=false;
        $scope.errorTipoDeFolio=false;
        var tamañoDeInfrccion=1;
        var longitudInfraccion=0
        $scope.arregloAuxilarInfracciones=[];
        $scope.bloquearDesconocido=true;
		$scope.bloquearFisicaYMoral=true;

  
          $scope.clasesCss={
        		    estiloCorreo:false,
        		  }
        
          $scope.clasesCss2={
        		    estiloInfraccion:false
        		  }

        $scope.refrescarpag = function(){
    		$window.location.reload();
    	}
        
        $scope.dateTimePickerOptions = {
                format: 'DD/MM/YYYY HH:mm:ss',
                maxDate: dateAfter
              
        };
        
        $scope.showAviso = function(messageTo) {
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
        
      
                    
        $scope.resetForm = function(){
            $timeout(function() {
                $scope.altaTramite.$setPristine();
//					$scope.altaInfraccion.DGFecha.$setPristine();
            });
            $scope.DG = angular.copy($scope.originalDG);
            $scope.UBI = angular.copy($scope.originalUBI);
            $scope.DV = angular.copy($scope.originalDV);
            $scope.DC = angular.copy($scope.originalDC);
            $scope.FI = angular.copy($scope.orginalFI);
            $scope.DD.hechos="";
            $scope.DD.docOtro="";
            filtroTipoTramite();
            filtroTipoDocumento ();
            filtroOrigenSolicitud();
            $scope.tipoPersona = false;
            fileURL="";
       	    buscarCamposRequeridos();
       	 $scope.clasesCss.estiloCorreo=false;
       	$scope.clasesCss2.estiloInfraccion=false;
       	 $scope.arregloAuxilarCorreos=[];
       	$scope.arregloAuxilarInfracciones=[];
       	
			document.getElementById("correos1").innerHTML =""; 
			for(var z=2;z<=longitud;z++)
			{
				 var borradiv = document.getElementById("correos"+z);
				 var br = document.getElementById("br"+z);
				 borradiv.parentNode.removeChild(borradiv);
				 br.parentNode.removeChild(br);
			}
			
			document.getElementById("infrac1").innerHTML =""; 
			for(var z=2;z<=longitudInfraccion;z++)
			{
				 var borradiv = document.getElementById("infrac"+z);
				 var br = document.getElementById("bi"+z);
				 borradiv.parentNode.removeChild(borradiv);
				 br.parentNode.removeChild(br);
			}
			
			
		 tamañoDeCorreo=1;
	     longitud=0;
	     tamañoDeInfraccion=1;
	     longitudInfraccion=0;
	     $scope.tipoPersonaFotocivicas=true;
	     $scope.errorLongitud=false;
	     $scope.errorTipoDeFolio=false;
	     $scope.bloquearDesconocido=true;
		 $scope.bloquearFisicaYMoral=true;
        
            
        }

        filtroTipoTramite = function(){
        	atencionCiudadanaService.comboTipoTramite().success(function(datos) {
                $scope.comboTipoTramite = datos;
                $scope.DG.tipoTramite = [2];
                $scope.DG.nbTramite= [$scope.comboTipoTramite[1].nbTramite];
                var dateCurrent = moment();
                $scope.DG.fecha=dateCurrent;

        }).error(function(datos) {
                $scope.error = datos;
            $scope.datos = {};
            });
        }
        
        filtroOrigenSolicitud = function(){
        	atencionCiudadanaService.comboOrigenSolicitud().success(function(datos) {
                $scope.comboOrigenSolicitud = datos;
                $scope.DG.medioSolicitud =1;
        }).error(function(datos) {
                $scope.error = datos;
            $scope.datos = {};
            });
        }
        
        filtroTipoDocumento = function(){
        	atencionCiudadanaService.comboTipoDocumento().success(function(datos) {
                $scope.comboTipoDocumento = datos;
                $scope.DD.tipoDocumento = [2];
                $scope.DD.nbDocumento= [$scope.comboTipoDocumento[1].nbDocumento];
        }).error(function(datos) {
                $scope.error = datos;
            $scope.datos = {};
            });
        }


        $scope.getVal=function(){
                if($scope.DD.tipoDocumento.includes(99) ){
                    $scope.listaCamposRequeridos.nbDocOtro=true;
              
                }else{

                    $scope.listaCamposRequeridos.nbDocOtro=false; 
                    $scope.DD.docOtro="";
                }
                var listaDocumentos=$scope.DD.tipoDocumento;
                $scope.DD.nbDocumento= [];
    	    	for(var x in listaDocumentos )
    	    		{
    	    		if(!isNaN(x)){
    	    		
    	    		for(var y in  $scope.comboTipoDocumento )
    	    			{
    	    			if(listaDocumentos[x]== $scope.comboTipoDocumento[y].idDocumento)
    	    				{
    	    				 $scope.DD.nbDocumento[x]=$scope.comboTipoDocumento[y].nbDocumento;
    	    				}
    	    			}
    	    		}
    	    		}
                

            }
                    
        /*******************************************************************
         * ********* BringAllData(AD)  <Iyeccion de Service Infracciones  **
         * *****************************************************************/
        $scope.bringAllData = function(){
            infraccionAllDataService.createAllData()
            .success(function(data){
                $scope.infraccionAllData = data;
                $scope.error = false;
                //buscarDistritoFederal();
                fillDataSelect();
            })
            .error(function(data){
                $scope.infraccionAllData = {};
                $scope.error = data;
            })
        }
        
            fillDataSelect = function(){
          
            
            $scope.DG.CatalogoTipoUnidad = $scope.infraccionAllData.tipoUnidadCatalogo;
            $scope.originalDG = $scope.DG;
        
            $scope.DV.catalogoTiposVehiculo = $scope.infraccionAllData.vehiculoTipoCatalogo;
            $scope.DV.catalogoVehiculosMarcas = $scope.infraccionAllData.vehiculoMarcaCatalogo;
            $scope.DV.catalogoVehiculosColor = $scope.infraccionAllData.vehiculoColorCatalogo;
            $scope.DC.catalogoEstados = $scope.infraccionAllData.estadoCatalogo;
           
            $scope.originalDV = $scope.DV;
            $scope.originalDC = $scope.DC;
            filtroTipoTramite();
            filtroTipoDocumento ();
            filtroOrigenSolicitud();

          
            $scope.resetForm();
        }
   
        buscarPlacaPorCodigo = function() {
                catalogoService.empleadoLogeado()
                    .success(function(data){
                    	
                    data.empNombre= $scope.convertirNombresMayusYminus(data.empNombre,false);
                    data.empApePaterno= $scope.convertirNombresMayusYminus(data.empApePaterno,true);
                    data.empApeMaterno= $scope.convertirNombresMayusYminus(data.empApeMaterno,true);

                        $scope.DG.empleado = data;
                        $scope.DG.empleado.valido=true;
                        $scope.DG.empleado.placaColor = "green";
                        $scope.error = false;
                    }).error(function(data){
                        $scope.showError("Por favor, ingrese un oficial válido.");
                        $scope.error = data;
                        $scope.DG.empleado = {};
                        $scope.DG.empleado.empPlaca ="";
                        $scope.DG.empleado.valido=false;
                        $scope.DG.empleado.placaColor = "red";
                    });

        };
        
        $scope.typePlacaDeEmpleado = function (){
        
	        $scope.DG.empleado.placaColor = "red";
	        $scope.DG.empleado.valido = false;
	        $scope.DG.empleado.empNombre = "";
	        $scope.DG.empleado.empApePaterno= "";
	        $scope.DG.empleado.empApeMaterno = "";
	    };
	    
	    $scope.buscarEmpleadoPorPlaca = function(empPlaca) {
	        if(empPlaca != null || empPlaca != "")
	            catalogoService.empleadoPorPlaca(empPlaca)
	                .success(function(data){
	                	data.empNombre= $scope.convertirNombresMayusYminus(data.empNombre,false);
	                    data.empApePaterno= $scope.convertirNombresMayusYminus(data.empApePaterno,true);
	                    data.empApeMaterno= $scope.convertirNombresMayusYminus(data.empApeMaterno,true);
	                    $scope.DG.empleado= data;
	                    $scope.DG.empleado.valido = true;
	                    $scope.DG.empleado.placaColor = "green";
	                    $scope.error = false;
	                    $scope.DG.empleado.empPlaca = empPlaca;
	                }).error(function(data){
                        $scope.showError("Por favor, ingrese un oficial válido.");
	                    $scope.error = data;
	                    $scope.DG.empleado = {};
	                    $scope.DG.empleado.valido  = false;
	                    $scope.DG.empleado.placaColor  = "red";
	                });
	    };
        
    
        
        $scope.actualizarVehiculosModeloPorMarca = function() {
        	$scope.DV.vehiculoModelo = null;
        	$scope.DV.MarcaOtro = null;
        	$scope.DV.ModeloOtro = null;
        	
        	if($scope.DV.vehiculoMarca)
        		{
            catalogoService.buscarVehiculosModeloPorMarca($scope.DV.vehiculoMarca.vMarId).success(function(data) {
                $scope.DV.catalogoVehiculosModelo = data;
                $scope.error = false;
                if($scope.DV.vehiculoMarca.vMarNombre=="OTRO")
             $scope.listaCamposRequeridos.nbMarcaOtro=true;
                
        		else
        	 $scope.listaCamposRequeridos.nbMarcaOtro=false;
             $scope.listaCamposRequeridos.nbModeloOtro=false;
                
            }).error(function(data) {
                $scope.error = data;
                $scope.DV.catalogoVehiculosModelo = {};
            });
        		}
        };
          
    	$scope.changeModeloVehiculo = function(idModelo){
    		$scope.DV.ModeloOtro = null;
    		if(idModelo.vModNombre=="OTRO")
    		     $scope.listaCamposRequeridos.nbModeloOtro=true;
    		else
    			 $scope.listaCamposRequeridos.nbModeloOtro=false;
    	};
        
    	
    	$scope.changeTipoPersona = function(){
    		if(!$scope.tipoPersona){
    			$scope.DD.tipoDocumento=[4];
    			$scope.listaCamposRequeridos.nbDocOtro=false
    			$scope.DD.docOtro="";
    			$scope.getVal();
    			$scope.listaCamposRequeridos.nbCPaterno=false;
    			$scope.listaCamposRequeridos.nbMaterno=false;
    			$scope.listaCamposRequeridos.nbCiudadano=false;
    			$scope.listaCamposRequeridos.nbEmpresa=true;
    			showAlert.aviso("Para persona moral, es necesario recibir el acta constitutiva.", null);
    			
    		}else{
    			$scope.DD.tipoDocumento=[2];
    			$scope.listaCamposRequeridos.nbDocOtro=false
    			$scope.DD.docOtro="";
    			$scope.getVal();
    			$scope.listaCamposRequeridos.nbCPaterno=true;
    			$scope.listaCamposRequeridos.nbMaterno=true;
    			$scope.listaCamposRequeridos.nbCiudadano=true;
    			$scope.listaCamposRequeridos.nbEmpresa=false;
    			
    		}
    	};
       
        
        
        
        
        $scope.actualizarDelegacionPorEstado = function() {
        	$scope.DC.delegacion=null;
        	if($scope.DC.direccionEstado)
        		{
            catalogoService.delegacionPorEstado($scope.DC.direccionEstado.edoId)
                .success(function(data){
                    $scope.DC.catalogoDelegaciones = data;
                    //$scope.DC.delegacion = $scope.DC.catalogoDelegaciones[0];
                    $scope.error = false;
                }).error(function(data){
                    $scope.DC.catalogoDelegaciones = {};
                    $scope.error = true;
                });
        		}
        };
      
         /******************************************************************
         * ****************     Combos actualizaciones   *******************
         * *****************************************************************/
        
        

        $scope.validateForm = function(){
            error = false;
            $scope.incompleteDG = {};
            if($scope.DG.fecha == null || $scope.DG.fecha == ""){
                error=true;
                $scope.incompleteDG.fecha = true;
            }
             
            if(error){
                $scope.showAviso("Formulario incompleto.");
            }
        }
        
        $scope.DoNuevoTramiteVO = function(){
            if ($scope.altaTramite.$invalid) {
                  
                angular.forEach($scope.altaTramite.$error, function (field) {
                  angular.forEach(field, function(errorField){
                      errorField.$setDirty();
                  })
                });
                $scope.showAviso("Formulario incompleto.");
            }else{
             if($scope.tipoPersona && !$scope.DD.tipoDocumento.includes(4) ) {
            	$scope.showAviso("Para persona moral, es necesario recibir el acta constitutiva.");
             }
             else if (!$scope.DG.empleado.valido){
                    $scope.showError("Por favor, ingrese un oficial válido.");
                  
                }
             else if($scope.DG.tipoTramite.includes(6)&& $scope.arregloAuxilarInfracciones.length<1)
            	 {
            	  $scope.showError("Es necesario ingresar al menos 1 folio de infracción para el cambio de tipo de persona.");
            	 }
             else {
                	guardarDatos();
                    atencionCiudadanaService.altaTramite($scope.nuevoTramiteVO)
                    .success(function(data){
                        $scope.viewModal=data;
                        $scope.viewModal.idNexTramit=$scope.viewModal[0];
                        if(data[1] =="true"){
                        $("#myModalConfirmacion").modal()
                         
                        }
                      
                    }).error(function(data){
                    $scope.showError(data.message);
                    })
                }
            }
            
        }


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
                $scope.showError("Imposible descargar reporte, inténtelo más tarde.")
            })
        }
       
     
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
       
   
       $scope.save = function(isURL, file, fileName) {
           var url = window.URL || window.webkitURL;
           var a = document.createElement('a');
           if(!isURL){
               a.href = file;
           }else{ 
               a.href = url.createObjectURL(file);
           }
           a.target = '_blank';
           a.download = fileName;
           document.body.appendChild(a);
           a.click();	
       };
       





        	//Convierte Mayusculas
		$scope.toTitleCase = function (str) {
			
			str = str.toLowerCase();
			var pieces = str.split(" ");
		    for(var i = 0; i < pieces.length; i++){

		        
		    	pieces[i] = pieces[i][0].toUpperCase() + pieces[i].substr(1);
		    }
		    return pieces.join(" ");
		};
		
		$scope.changeTipoTramite = function(){
			if($scope.DG.tipoTramite.includes(6)&& !$scope.DG.tipoTramite.includes(2))
			{
			var auxiliar=$scope.DG.tipoTramite.slice()
	        auxiliar.push(2)
		    $scope.DG.tipoTramite=auxiliar.sort((unNumero, otroNumero) => unNumero - otroNumero);
			}
			var listTipos =  $scope.DG.tipoTramite;
			$scope.listaCamposRequeridos.txCCorreo = false;
			$scope.tipoPersonaFotocivicas=true;
			$scope.listaCamposRequeridos.folioInfrac=false;
	    	for(var x in listTipos){
	    		if(!isNaN(x)){
	    			if(listTipos[x]==5){
	    				$scope.listaCamposRequeridos.txCCorreo = true;
	    			}
	    			
	    		     if(listTipos[x]==6)
	    				{
		    				$scope.tipoPersonaFotocivicas=false;
		    				$scope.listaCamposRequeridos.folioInfrac=true;
	    					
	    				}
	    		    
	    		    	 
	    		}
	    	}
	    	if(!$scope.DG.tipoTramite.includes(6))
	    			
	    			{
	    		
	    		     $scope.bloquearDesconocido=true;
					 $scope.bloquearFisicaYMoral=true;
					 $scope.arregloAuxilarInfracciones=[];
		             $scope.DV.origenPlaca=undefined;
		             $scope.DV.tipoPersona=undefined;
		             $scope.DV.nuevoEstadoTipoPersona=undefined;
		             
		            document.getElementById("infrac1").innerHTML =""; 
					for(var z=2;z<=longitudInfraccion;z++)
					{
						 var borradiv = document.getElementById("infrac"+z);
						 var br = document.getElementById("bi"+z);
						 borradiv.parentNode.removeChild(borradiv);
						 br.parentNode.removeChild(br);
					}
		        	$scope.clasesCss2.estiloInfraccion=false;
		        	tamañoDeInfraccion=1;
		   	        longitudInfraccion=0;
	    		
	    			}
	    	 $scope.DG.nbTramite= [];
	    	for(var x in listTipos )
	    		{
	    		if(!isNaN(x)){
	    		
	    		for(var y in $scope.comboTipoTramite )
	    			{
	    			if(listTipos[x]==$scope.comboTipoTramite[y].idTramite)
	    				{
	    				$scope.DG.nbTramite[x]=$scope.comboTipoTramite[y].nbTramite;
	    				}
	    			}
	    		}
	    		}
	    		
	    	
	    	
	    	
		};
		
		
		
		
		
		$scope.vistaPrevia=function()
		{
			 if ($scope.altaTramite.$invalid) {
                 
	                angular.forEach($scope.altaTramite.$error, function (field) {
	                  angular.forEach(field, function(errorField){
	                      errorField.$setDirty();
	                  })
	                });
	                $scope.showAviso("Formulario incompleto.");
	               
	            }else{
	            	if($scope.tipoPersona && !$scope.DD.tipoDocumento.includes(4) ) {
	                	$scope.showAviso("Para persona moral, es necesario recibir el acta constitutiva.");
	                 }
	            	else if (!$scope.DG.empleado.valido){
	                     $scope.showError("Por favor, ingrese un oficial válido.");
	                 }
	            	else if($scope.DG.tipoTramite.includes(6)&& $scope.arregloAuxilarInfracciones.length<1)
	            	 {
	            	  $scope.showError("Es necesario ingresar al menos 1 folio de infracción para el cambio de tipo de persona.");
	            	 }else{
	                $("#myModal").modal()
	            	guardarDatos();
	            	  atencionCiudadanaService.vistaPrevia($scope.nuevoTramiteVO)
	                  .success(function(data,status,headers){
	                	
	                      var filename = headers('filename');
	                      var contentType = headers('content-type');
	                      var file = new Blob([ data ], {
	                          type : 'application/pdf;base64,'
	                      });
	                      fileURL = URL.createObjectURL(file);
	                      var blob_iframe = document.querySelector('#blob-src-test');
	                      blob_iframe.src =fileURL ;
	                     // window.open(fileURL);
	                      
	                      /*probando
	                    
	                      file= new Blob([file,file], {
	                          type : 'application/pdf;base64,'
	                      });
	                      var filrURL2=URL.createObjectURL(file);
	                      window.open(filrURL2);*/
	                      
	                  })
	                  .error(function(data){
	                	  
	                      $scope.showError("Imposible visualizar el reporte, inténtelo más tarde.")
	                  })
	            
	            }
	            }
			
		}
		
		guardarDatos=function()
		{
			  /// Recorrido de opcion multiple       
            var  tipoTramite = "";
            var tipoDoc="";
            $scope.tipoTramiteTitulo = "";
            $scope.tipoDocTitulo = "";
             
             var tipoTramiteStr = "";
             var tipoDocStr = "";
             var j = 0;
             var k = 0;

            
             $scope.DG.tipoTramite.forEach(function(element) {
                 //console.log(element);
                 tipoTramiteStr = $.grep($scope.comboTipoTramite, function (option) {
                     return option.idTramite == element;
                 })[0].nbTramite;
             
             if((j + 1) == $scope.DG.tipoTramite.length){
                     tipoTramite += element;
                     $scope.tipoTramiteTitulo += tipoTramiteStr;
                 }else{
                     tipoTramite += element + ",";
                     $scope.tipoTramiteTitulo += tipoTramiteStr + ", ";
                 }
                 j++;
         });
         

         //Recorf opc tipoDocumrnto
         
         $scope.DD.tipoDocumento.forEach(function(element) {
             //console.log(element);
             tipoDocStr = $.grep($scope.comboTipoDocumento, function (option) {
                 return option.idDocumento == element;
             })[0].nbDocumento;
         
         if((k + 1) == $scope.DD.tipoDocumento.length){
             tipoDoc += element;
             $scope.tipoDocTitulo += tipoDocStr;
             }else{
                 tipoDoc += element + ",";
                 $scope.tipoDocTitulo += tipoDocStr + ", ";
             }
             k++;
     });

              

             //DATOS DEL CIUDADANO
             $scope.nuevoTramiteVO.idacTramite=null;
             $scope.nuevoTramiteVO.idTramite = tipoTramite;
             $scope.nuevoTramiteVO.cTramite=$scope.tipoTramiteTitulo;
             $scope.nuevoTramiteVO.fhAlta = $filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY HH:mm:ss');
             $scope.nuevoTramiteVO.nbCiudadano = $scope.DC.nombre?$scope.DC.nombre:null; 
             $scope.nuevoTramiteVO.nbEmpresa=$scope.DC.emp?$scope.DC.emp:null;
             $scope.nuevoTramiteVO.nbPaterno =$scope.DC.apellidoPaterno?$scope.DC.apellidoPaterno:null;
             $scope.nuevoTramiteVO.nbMaterno = $scope.DC.apellidoMaterno?$scope.DC.apellidoMaterno:null;
             $scope.nuevoTramiteVO.nuTelefono = $scope.DC.numTelefono?$scope.DC.numTelefono:null;
             $scope.nuevoTramiteVO.txCorreo=$scope.DC.correo?$scope.DC.correo:null;
             $scope.nuevoTramiteVO.txCalle = $scope.DC.calle?$scope.DC.calle:null;
             $scope.nuevoTramiteVO.txColonia=$scope.DC.colonia?$scope.DC.colonia:null;
             $scope.nuevoTramiteVO.nuExt = $scope.DC.numeroExterior?$scope.DC.numeroExterior:null;
             $scope.nuevoTramiteVO.nuInt = $scope.DC.numeroInterior?$scope.DC.numeroInterior:null;
             $scope.nuevoTramiteVO.nuCp=null;
             
             if($scope.DC.direccionEstado)
             	{
             	$scope.nuevoTramiteVO.idEdo = $scope.DC.direccionEstado.edoId;
                 $scope.nuevoTramiteVO.cEdo=   $scope.DC.direccionEstado.edoNombre;
             	}
				else
					{
					$scope.nuevoTramiteVO.idEdo= null;
                  $scope.nuevoTramiteVO.cEdo=null;
					}
             
            
             
             if($scope.DC.delegacion)
             	{
             $scope.nuevoTramiteVO.idDelegacion = $scope.DC.delegacion.delId.delId;
             $scope.nuevoTramiteVO.cDelegacion= $scope.DC.delegacion.delNombre;
             	}
             else
             	{
             $scope.nuevoTramiteVO.idDelegacion=null;
             $scope.nuevoTramiteVO.cDelegacion=null;
             	}
           
   

             $scope.nuevoTramiteVO.cdPlaca = $scope.DV.placa?$scope.DV.placa:null;
             
             $scope.nuevoTramiteVO.tipoVehiculo = $scope.DV.tipoVehiculo?$scope.DV.tipoVehiculo.vTipoId:null;
             $scope.nuevoTramiteVO.cVehiculo= $scope.DV.tipoVehiculo?$scope.DV.tipoVehiculo.vTipoNombre:null;
             
             $scope.nuevoTramiteVO.idMarca = $scope.DV.vehiculoMarca ? $scope.DV.vehiculoMarca.vMarId : null;
             $scope.nuevoTramiteVO.cMarca = $scope.DV.vehiculoMarca ? $scope.DV.vehiculoMarca.vMarNombre :null;
             
             $scope.nuevoTramiteVO.idModelo = $scope.DV.vehiculoModelo  ? $scope.DV.vehiculoModelo.vModId.vModId : null;
             $scope.nuevoTramiteVO.cModelo=   $scope.DV.vehiculoModelo ? $scope.DV.vehiculoModelo.vModNombre :null;
             
             $scope.nuevoTramiteVO.idColor = $scope.DV.vehiculoColor ? $scope.DV.vehiculoColor.vColorId : null;
             $scope.nuevoTramiteVO.cColor=$scope.DV.vehiculoColor ? $scope.DV.vehiculoColor.vColorNombre: null;;
             
             $scope.nuevoTramiteVO.txHechos=$scope.DD.hechos?$scope.DD.hechos:null;
             $scope.nuevoTramiteVO.txCc =$scope.arregloAuxilarCorreos.length>0?$scope.arregloAuxilarCorreos.toString():null;
             $scope.nuevoTramiteVO.idDocumento = tipoDoc;
             $scope.nuevoTramiteVO.cDocumento=$scope.tipoDocTitulo;
             $scope.nuevoTramiteVO.lbExpediente=null;
             $scope.nuevoTramiteVO.stAtendido=0;
             $scope.nuevoTramiteVO.stExpediente=0;
             $scope.nuevoTramiteVO.nbMarcaOtro=$scope.DV.MarcaOtro?$scope.DV.MarcaOtro:null;
             $scope.nuevoTramiteVO.nbModeloOtro=$scope.DV.ModeloOtro?$scope.DV.ModeloOtro:null;
             $scope.nuevoTramiteVO.nbDocOtro=$scope.DD.docOtro?$scope.DD.docOtro:null;
             $scope.nuevoTramiteVO.idEmp = $scope.DG.empleado.empId;
             $scope.nuevoTramiteVO.cEmp=null;
             $scope.nuevoTramiteVO.nbOficial=$scope.DG.empleado.empNombre+' '+$scope.DG.empleado.empApePaterno+' '+$scope.DG.empleado.empApeMaterno;
             $scope.nuevoTramiteVO.idUsrCreacion=null;
             $scope.nuevoTramiteVO.idUsrModifica=null;
             $scope.nuevoTramiteVO.modificadoPor = 1;
             $scope.nuevoTramiteVO.fhCreacion=$filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY HH:mm:ss');
             $scope.nuevoTramiteVO.fhModificacion=$filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY HH:mm:ss');
             $scope.nuevoTramiteVO.cdTipoPersona=$scope.tipoPersona?"PM":"PF";
             
             //Datos de Cambio de tipo de persona
             $scope.nuevoTramiteVO.foliosDeInfraccion=$scope.arregloAuxilarInfracciones.length>0?$scope.arregloAuxilarInfracciones.toString():null; 
             $scope.nuevoTramiteVO.nuevoOrigenTipo=$scope.DV.origenPlaca?$scope.DV.origenPlaca:null;
             $scope.nuevoTramiteVO.nuevoTipo=$scope.DV.tipoPersona?$scope.DV.tipoPersona:null;
             $scope.nuevoTramiteVO.nuevoIdEstadoTipo=$scope.DV.nuevoEstadoTipoPersona? $scope.DV.nuevoEstadoTipoPersona:null;
             //medio de solicitud
             $scope.nuevoTramiteVO.idMedioSolicitud=$scope.DG.medioSolicitud?$scope.DG.medioSolicitud:99;
            	 
            	 
                
            	 
             
			
		}
		
		buscarCamposRequeridos=function()
		{
			atencionCiudadanaService.camposRequeridos().success(function(data){
            	
               $scope.listaCamposRequeridos=data;
                
            })
            .error(function(data){
          	  $scope.showAviso("Sin acceso al recurso.",'templateModalError');
            })
		}
		
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
		
		buscarInformacionTooltipAyuda=function()
		{
			atencionCiudadanaService.informacionTooltip().success(function(data){
            	
	            $scope.informacionAyuda=data;
	    		$scope.tituloSug=$scope.informacionAyuda.TITULO_SUG_TOOLTIP;
	   			$scope.descripcion=$scope.informacionAyuda.DESCRIPCION_TOOLTIP;
	   			$scope.descripcion2=$scope.informacionAyuda.DESCRIPCION_2_TOOLTIP;
	   			$scope.formato=$scope.informacionAyuda.FORMATO_TOOLTIP;
	   			$scope.tituloEjemplo=$scope.informacionAyuda.TITULO_EJEMPLO_TOOLTIP;
	   			$scope.ejemplo=$scope.informacionAyuda.EJEMPLO_TOOLTIP;
	                
	            })
	            .error(function(data){
	          	  $scope.showAviso("Sin acceso al recurso.",'templateModalError');
	            })	
		}
		
		
	
		
		
		 $scope.bringAllData();
		 buscarPlacaPorCodigo();
		 buscarCamposRequeridos();
		 buscarInformacionTooltipAyuda();
		 

			
			/*$scope.validarcc=function()
			{
				var cuanto=0;
				$scope.errorCorreoCopia=false;
				
				if($scope.DV.cc)
					{
				$scope.DV.cc=$scope.DV.cc.split(",");
				 $scope.clasesCss.estiloCorreo=true;
				longitud=$scope.DV.cc.length;
				//document.getElementById("correos1").innerHTML = $scope.DV.cc[0]; 
				if(longitud>tamañoDeCorreo)
					{
					tamañoDeCorreo=longitud;
					var aumento=true;
					var disminuyo=false;
					}
				else if(longitud<tamañoDeCorreo)
					{
					
				    cuanto=tamañoDeCorreo-longitud;
					tamañoDeCorreo=longitud;
					var disminuyo=true;
				    var aumento=false;
				   
					}
				else
					{
					var disminuyo=false;
				    var aumento=false;
					}
				
				var y=longitud-1;
				if(aumento)
					{
						  var newDiv = document.createElement("div");
						  newDiv.setAttribute("id", "correos"+(y+1));
						  newDiv.setAttribute("ng-click","borrarDiv($event)");
						  newDiv.classList.add("estiloCorreo");
						  var newContent = document.createTextNode(""); 
						  newDiv.appendChild(newContent); //añade texto al div creado.
						  
						  var saltoLinea = document.createElement("br");
						  saltoLinea.setAttribute("id", "br"+(y+1)); 
						  var currentSalto = document.getElementById("br"+(y));   
						  currentSalto.insertAdjacentElement("afterend", newDiv);
						  $compile(newDiv)($scope);
						  newDiv.insertAdjacentElement("afterend", saltoLinea);
						}
				else if(disminuyo)
					{
					for (var x=cuanto;x>0;x--)
						{
					  var borradiv = document.getElementById("correos"+(longitud+x));
					  var br = document.getElementById("br"+(longitud+x));
					  borradiv.parentNode.removeChild(borradiv);
					  br.parentNode.removeChild(br);
					    }

					}
				
				
				
				for(var z=1;z<=longitud;z++)
				{
		      document.getElementById("correos"+z).innerHTML = $scope.DV.cc[z-1]; 
				}
					
				
				
				for(var x in $scope.DV.cc){
		    		if(!isNaN(x)){
		    			if($scope.DV.cc[x].includes('@'))
							$scope.errorCorreoCopia=false;
		    			else
							$scope.errorCorreoCopia=true;
		    				
		    			}
		    		}

			    	}    
				else
					{
					$scope.clasesCss.estiloCorreo=false;
					document.getElementById("correos1").innerHTML =""; 
					for(var z=2;z<=longitud;z++)
					{
			      document.getElementById("correos"+z).innerHTML =""; 
			      document.getElementById("correos"+z).classList.remove("estiloCorreo");
					}
					
					}
				
			};
			
			$scope.borrarDiv=function(id)
			{
				var arregloAuxiliar;
				var posicionArreglo=id.target.id.substr(7,8)
				if(longitud==1)
					{
					arregloAuxiliar=$scope.DV.cc.slice();
					arregloAuxiliar.shift();
					$scope.DV.cc=arregloAuxiliar.slice();
			    	$scope.clasesCss.estiloCorreo=false;
			    	var probando=document.getElementById("correos1");
					document.getElementById("correos1").innerHTML ="";
					longitud=0;
					tamañoDeCorreo=1;
					$scope.errorCorreoCopia=false;
					}
				else if (longitud>1)
					{
					 arregloAuxiliar=$scope.DV.cc.slice();
					 arregloAuxiliar.splice(posicionArreglo-1, 1);
					 $scope.DV.cc=arregloAuxiliar.slice();
					 var borradiv = document.getElementById("correos"+(longitud));
					 var br = document.getElementById("br"+(longitud));
					 borradiv.parentNode.removeChild(borradiv);
					 br.parentNode.removeChild(br);
					 longitud=$scope.DV.cc.length;
						tamañoDeCorreo=longitud;
						for(var z=1;z<=longitud;z++)
						{
				        document.getElementById("correos"+z).innerHTML = $scope.DV.cc[z-1]; 
						}
						
					}
				
				
				
			};*/
		 
		   $scope.validarcc=function()
			{
				
				
				var aumento=false;
				
				if($scope.DV.cc)
					{

					if(longitud<5)
						{
					$scope.clasesCss.estiloCorreo=true;
					$scope.arregloAuxilarCorreos.push($scope.DV.cc);
					$scope.DV.cc=undefined;
					longitud=$scope.arregloAuxilarCorreos.length;
					if(longitud>tamañoDeCorreo)
					{
					tamañoDeCorreo=longitud;
					aumento=true;
					}
				else
					{
				     aumento=false;
					}
				
				var y=longitud-1;
				if(aumento)
					{
						  var newDiv = document.createElement("div");
						  newDiv.setAttribute("id", "correos"+(y+1));
						  newDiv.setAttribute("ng-click","borrarDiv($event)");
						  newDiv.classList.add("estiloCorreo");
						  var newContent = document.createTextNode(""); 
						  newDiv.appendChild(newContent); //añade texto al div creado.
						  
						  var saltoLinea = document.createElement("br");
						  saltoLinea.setAttribute("id", "br"+(y+1)); 
						  var currentSalto = document.getElementById("br"+(y));   
						  currentSalto.insertAdjacentElement("afterend", newDiv);
						  $compile(newDiv)($scope);
						  newDiv.insertAdjacentElement("afterend", saltoLinea);
						}		
				
				for(var z=1;z<=longitud;z++)
				{
		      document.getElementById("correos"+z).innerHTML = $scope.arregloAuxilarCorreos[z-1]; 
				}
						}
					else if(longitud>=5)
						{
						 $scope.showError("Has alcanzado el límite de correos.");
						 $scope.DV.cc=undefined;
						}
					
			    	}    

				
			};
			
			$scope.borrarDiv=function(id)
			{
				var arregloAuxiliar;
				var posicionArreglo=id.target.id.substr(7,8)
				if(longitud==1)
					{
					arregloAuxiliar=$scope.arregloAuxilarCorreos.slice();
					arregloAuxiliar.shift();
					$scope.arregloAuxilarCorreos=arregloAuxiliar.slice();
			    	$scope.clasesCss.estiloCorreo=false;
					document.getElementById("correos1").innerHTML ="";
					longitud=0;
					tamañoDeCorreo=1;
					
					}
				else if (longitud>1)
					{
					 arregloAuxiliar=$scope.arregloAuxilarCorreos.slice();
					 arregloAuxiliar.splice(posicionArreglo-1, 1);
					 $scope.arregloAuxilarCorreos=arregloAuxiliar.slice();
					 var borradiv = document.getElementById("correos"+(longitud));
					 var br = document.getElementById("br"+(longitud));
					 borradiv.parentNode.removeChild(borradiv);
					 br.parentNode.removeChild(br);
					 longitud=$scope.arregloAuxilarCorreos.length;
						tamañoDeCorreo=longitud;
						for(var z=1;z<=longitud;z++)
						{
				        document.getElementById("correos"+z).innerHTML = $scope.arregloAuxilarCorreos[z-1]; 
						}
						
					}
				
				
				
			};
			
			
			
             
			
			 $scope.validarFolios=function()
				{
				 var aumento2=false;
				 $scope.errorLongitud=false;
			     $scope.errorTipoDeFolio=false;
				 
					if($scope.DV.listaInfracciones)
						{
                          if($scope.DV.listaInfracciones.length!=11)
                        	  {
                        	  $scope.errorLongitud=true;
                        	  }
                          else if($scope.DV.listaInfracciones.substring(0,2)!="03" && $scope.DV.listaInfracciones.substring(0,2)!="08")
                        	  {
                        	  $scope.errorTipoDeFolio=true;
                        	  }
                          else if(!$scope.DV.placa)
                        	  {
                        	  $scope.showError("Ingrese la placa vehicular.");
                        	  }
                          else if(longitudInfraccion<10)
                        	  {
                        	  atencionCiudadanaService.consultaInfraccion($scope.DV.placa,$scope.DV.listaInfracciones)
                              .success(function(data){
                             var encontroInfraccion=data;
                             if(encontroInfraccion=="SI")
                            	 {
                            	 $scope.clasesCss2.estiloInfraccion=true;
           						$scope.arregloAuxilarInfracciones.push($scope.DV.listaInfracciones);
           						$scope.DV.listaInfracciones=undefined;
           						longitudInfraccion=$scope.arregloAuxilarInfracciones.length;
           						if(longitudInfraccion>tamañoDeInfraccion)
           						{
           						tamañoDeInfraccion=longitudInfraccion;
           						aumento2=true;
           						}
           					else
           						{
           					     aumento2=false;
           						}
           					
           					var y=longitudInfraccion-1;
           					if(aumento2)
           						{
           							  var newDiv = document.createElement("div");
           							  newDiv.setAttribute("id", "infrac"+(y+1));
           							  newDiv.setAttribute("ng-click","borrarDivInfrac($event)");
           							  newDiv.classList.add("estiloInfraccion");
           							  var newContent = document.createTextNode(""); 
           							  newDiv.appendChild(newContent); //añade texto al div creado.
           							  
           							  var saltoLinea = document.createElement("br");
           							  saltoLinea.setAttribute("id", "bi"+(y+1)); 
           							  var currentSalto = document.getElementById("bi"+(y));   
           							  currentSalto.insertAdjacentElement("afterend", newDiv);
           							  $compile(newDiv)($scope);
           							  newDiv.insertAdjacentElement("afterend", saltoLinea);
           							}		
           					
           					for(var z=1;z<=longitudInfraccion;z++)
           					{
           			      document.getElementById("infrac"+z).innerHTML = $scope.arregloAuxilarInfracciones[z-1]; 
           					}
                            	 }
                             else
                            	 {
                            	 $scope.showError("No se encontró la infracción.");
                            	 }

                              }).error(function(data){
                              $scope.showError(data.message);
                              })
                              
                              
      						
      							
                       }
                          else if(longitudInfraccion>=10)
							{
							 $scope.showError("Has alcanzado el límite de infracciones.");
							 $scope.DV.listaInfracciones=undefined;
							}
					
						
				    	}    

					
				};
				
				
				
				
				
				
				
				$scope.borrarDivInfrac=function(id)
				{
					var arregloAuxiliar;
					var posicionArreglo=id.target.id.substr(6,7)
					if(longitudInfraccion==1)
						{
						arregloAuxiliar=$scope.arregloAuxilarInfracciones.slice();
						arregloAuxiliar.shift();
						$scope.arregloAuxilarInfracciones=arregloAuxiliar.slice();
				    	$scope.clasesCss2.estiloInfraccion=false;
						document.getElementById("infrac1").innerHTML ="";
						longitudInfraccion=0;
						tamañoDeInfraccion=1;
						
						}
					else if (longitudInfraccion>1)
						{
						 arregloAuxiliar=$scope.arregloAuxilarInfracciones.slice();
						 arregloAuxiliar.splice(posicionArreglo-1, 1);
						 $scope.arregloAuxilarInfracciones=arregloAuxiliar.slice();
						 var borradiv = document.getElementById("infrac"+(longitudInfraccion));
						 var br = document.getElementById("bi"+(longitudInfraccion));
						 borradiv.parentNode.removeChild(borradiv);
						 br.parentNode.removeChild(br);
						 longitudInfraccion=$scope.arregloAuxilarInfracciones.length;
						 tamañoDeInfraccion=longitudInfraccion;
							for(var z=1;z<=longitudInfraccion;z++)
							{
					        document.getElementById("infrac"+z).innerHTML = $scope.arregloAuxilarInfracciones[z-1]; 
							}
							
						}
					
					
					
				};
				
				$scope.validarOrigenPlaca=function()
				{
					$scope.bloquearDesconocido=true;
					$scope.bloquearFisicaYMoral=true;
					if($scope.DV.origenPlaca==0)
						{
						$scope.bloquearFisicaYMoral=false;
						$scope.DV.tipoPersona="";
						$scope.DV.nuevoEstadoTipoPersona=9;
						}
					else
						{
						$scope.bloquearDesconocido=false;
						$scope.DV.tipoPersona="";
						$scope.bloquearFisicaYMoral=false;
						$scope.DV.nuevoEstadoTipoPersona="";
						}
				}
				
					
			
			

    });



 



