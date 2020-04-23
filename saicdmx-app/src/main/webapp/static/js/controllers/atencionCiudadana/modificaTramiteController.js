angular.module('siidfApp').controller('modificaTramiteController', function(
		$scope,$compile, $filter, $route, $window, showAlert, atencionCiudadanaService, msCatalogoService, catalogoService, growl, tramiteATCiudadana, $location, ModalService) {

	$scope.ACTramiteVO ={};
	$scope.ACTramiteVistaPrevia = {};
	$scope.tramiteVO = tramiteATCiudadana.data;
	var _defaultDate = moment($scope.tramiteVO.fhAltaString,'DD/MM/YYYY HH:mm:ss');
	$scope.tipoPersona = false;
	$scope.lineaCaptura50 = false;
	$scope.marcaOtroRequerido = false;
	$scope.modeloOtroRequerido = false;
	$scope.documentoOtroRequerido = false;
	var actaConstitutivaRequerida = false;
	//$scope.actaConstitutiva = false;
	
	$scope.listNbTramite = [];
	$scope.listNbDocumento = [];
	var tpPersona = "PF";
	var tamañoDeCorreo=1;
    var longitud=0;
    $scope.arregloAuxilarCorreos=[];
    $scope.tipoPersonaFotocivicas=true;
    $scope.errorLongitud=false;
    $scope.errorTipoDeFolio=false;
    var tamañoDeInfrccion=1;
    var longitudInfraccion=0
    $scope.arregloAuxilarInfracciones=[];
    var arregloOriginaldeInfracciones=[];
    var elementosAgregados=[];
    var elementosBorrados=[];
    $scope.bloquearDesconocido=true;
	$scope.bloquearFisicaYMoral=true;

	
	 $scope.clasesCss={
    		    estiloCorreo:false
    		  }
	$scope.clasesCss2={
		    estiloInfraccion:false
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

    $scope.showError = function(messageTo) {
        ModalService.showModal({
            templateUrl: 'views/templatemodal/templateModalError.html',
            controller: 'mensajeModalController',
            inputs:{ message: messageTo}
        }).then(function(modal) {
            modal.element.modal();
        });
      };
    
	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY HH:mm:ss',
		defaultDate : _defaultDate
	};
	
	filtarDocumentoPorId = function(op){
		if(op == 4)
			return op;
	};
	
	
	$scope.changeTipoPersona = function(op){
		if(op==false){
			$scope.DTDocumento=[2];
			$scope.documentoOtroRequerido = false;
			$scope.DTDocumentoOtro = null;
			$scope.changeDocumento($scope.DTDocumento);
			tpPersona = "PF";
			actaConstitutivaRequerida = false;
		}else{
			$scope.DTDocumento=[4];
			$scope.documentoOtroRequerido = false;
			$scope.DTDocumentoOtro = null;
			$scope.changeDocumento($scope.DTDocumento);
			tpPersona = "PM";
				showAlert.aviso("Para persona moral, es necesario recibir el acta constitutiva.", null);
				actaConstitutivaRequerida = true;
			
		}
	};
	
	$scope.changeTipoTramite = function(){
		if($scope.tipoTramite.includes(6)&&!$scope.tipoTramite.includes(2))
		{
		var auxiliar=$scope.tipoTramite.slice()
        auxiliar.push(2)
	    $scope.tipoTramite=auxiliar.sort((unNumero, otroNumero) => unNumero - otroNumero);
		}
		var listTipos =  $scope.tipoTramite;
		$scope.lineaCaptura50 = false;
		$scope.tipoPersonaFotocivicas=true;
		$scope.listaCamposRequeridos.folioInfrac=false;
		$scope.listNbTramite= [];
    	for(var x in listTipos){
    		if(!isNaN(x)){
    			if(listTipos[x]==5){
    				$scope.lineaCaptura50 = true;
    			}
    			else if(listTipos[x]==6)
				{
    				$scope.tipoPersonaFotocivicas=false;
    				$scope.listaCamposRequeridos.folioInfrac=true;
					
				}
    			
    			for(var y in $scope.comboTipoTramite ){
    				if(!isNaN(y)){
	    				if(listTipos[x]==$scope.comboTipoTramite[y].idTramite){
	    					$scope.listNbTramite.push($scope.comboTipoTramite[y].nbTramite);
	    					break;
	    				}
    				}
    			}
    		}
    	}
    	if(!$scope.tipoTramite.includes(6) && $scope.consultaTramitesData[0].nbstSegTramite!='Atendido' )
			
		{
	
	     $scope.bloquearDesconocido=true;
		 $scope.bloquearFisicaYMoral=true;
		 elementosBorrados.push($scope.arregloAuxilarInfracciones.slice());
		 $scope.arregloAuxilarInfracciones=[];
         $scope.origenPlaca=undefined;
         $scope.idtipoPersona=undefined;
         $scope.nuevoEstadoTipoPersona=undefined;
         
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
    	
	};
	
	$scope.changeModeloVehiculo = function(idModelo){
		$scope.DVModeloOtro = null;
		var listModelos =  $scope.catalogoVehiculosModelo;
		var nombreModelo;
    	for(var x in listModelos){
    		if(!isNaN(x)){
    			if(listModelos[x].vModId.vModId==idModelo){
    				nombreModelo=listModelos[x].vModNombre;
    			}
    		}
    	}
		if(nombreModelo=="OTRO")
			$scope.modeloOtroRequerido = true;
		else
			$scope.modeloOtroRequerido = false;
	};
	
	$scope.changeDocumento = function(DTDocumento){
		var listDocumentos = DTDocumento;
		$scope.documentoOtroRequerido = false;
		$scope.listNbDocumento = [];
    	for(var x in listDocumentos){
    		if(!isNaN(x)){
    			if(listDocumentos[x]==99){
    				$scope.documentoOtroRequerido = true;
    				$scope.DTDocumentoOtro = null;
    			}
    			for(var y in $scope.comboTipoDocumento){
    				if(!isNaN(y)){
    					if(listDocumentos[x] == $scope.comboTipoDocumento[y].idDocumento){
    						$scope.listNbDocumento.push($scope.comboTipoDocumento[y].nbDocumento);
    						break;
    					}
    				}
    			}
    		}
    	}
    	if($scope.documentoOtroRequerido == false)
    		$scope.DTDocumentoOtro = null;
	};
	
	llenarCatalogoEstados = function(){
		catalogoService.buscarEstadosTodos().success(function(data){
			$scope.catalogoEstados = data;
			$scope.error = false;
		}).error(function(data){
			$scope.catalogoEstados = {};
			$scope.error = data;
		});
	};
	
	$scope.actualizarDelegacionPorEstado = function() {
		$scope.DCDelegacion=null;
		if($scope.DCEstado)
		{
        catalogoService.delegacionPorEstado($scope.DCEstado).success(function(data){
                $scope.catalogoDelegaciones = data;
                $scope.error = false;
            }).error(function(data){
                $scope.catalogoDelegaciones = {};
                $scope.error = true;
            });
		}
    };
    
    llenarCatalogoTipoVehiculo =  function(){
    	catalogoService.buscarTiposVehiculo().success(function(data){
    		$scope.catalogoTiposVehiculo = data;
    		$scope.error = false;
    	}).error(function(data){
    		$scope.catalogoTiposVehiculo = {};
    		$scope.error = data;
    	});
    };
    
    llenarCatalogoVehiculosMarcas = function(){
    	catalogoService.buscarVehiculosMarcas().success(function(data){
    		$scope.catalogoVehiculosMarcas = data;
    		$scope.error = false;
    	}).error(function(data){
    		$scope.catalogoVehiculosMarcas = {};
    		$scope.error = data;
    	});
    };
    
    $scope.actualizarVehiculosModeloPorMarca = function() {
    	$scope.DVModelo = null;
    	$scope.DVMarcaOtro = null;
    	$scope.DVModeloOtro = null;
    	if($scope.DVMarca)
		{
        catalogoService.buscarVehiculosModeloPorMarca($scope.DVMarca).success(function(data) {
            $scope.catalogoVehiculosModelo = data;
            $scope.error = false;
        	var listMarcas =  $scope.catalogoVehiculosMarcas;
    		var nombreMarca;
        	for(var x in listMarcas){
        		if(!isNaN(x)){
        			if(listMarcas[x].vMarId==$scope.DVMarca){
        				nombreMarca=listMarcas[x].vMarNombre;
        			}
        		}
        	}
            if(nombreMarca=="OTRO")
    			$scope.marcaOtroRequerido = true;
    		else
    			$scope.marcaOtroRequerido = false;
                $scope.modeloOtroRequerido = false;
        }).error(function(data) {
            $scope.error = data;
            $scope.catalogoVehiculosModelo = {};
        });
        
		}
    };
    
    llenarCatalogoVehiculosColor = function(){
    	catalogoService.buscarVehiculosColor().success(function(data){
    		$scope.catalogoVehiculosColor = data;
    		$scope.error = false;
    	}).error(function(data){
    		$scope.catalogoVehiculosColor = {};
    		$scope.error = data;
    	});
    };
    
    filtroTipoTramite = function(){
    	atencionCiudadanaService.comboTipoTramite().success(function(datos) {
            $scope.comboTipoTramite = datos;
    }).error(function(datos) {
            $scope.error = datos;
        $scope.datos = {};
        });
    };
    
    filtroOrigenSolicitud = function(){
    	atencionCiudadanaService.comboOrigenSolicitud().success(function(datos) {
            $scope.comboOrigenSolicitud = datos;
    }).error(function(datos) {
            $scope.error = datos;
        $scope.datos = {};
        });
    }
    
    filtroTipoDocumento = function(){
    	atencionCiudadanaService.comboTipoDocumento().success(function(datos) {
            $scope.comboTipoDocumento = datos;
    	}).error(function(datos) {
            $scope.error = datos;
            $scope.datos = {};
        });
    };
    
    
    $scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY HH:mm:ss',
		defaultDate : _defaultDate
	};
    
    $scope.typePlacaDeEmpleado = function (){
        $scope.empleado.Color = "red";
        $scope.empleado.valido = false;
        $scope.empleado.empNombre ="";
        $scope.empleado.empApePaterno ="";
        $scope.empleado.empApeMaterno = "";
    };
    
    $scope.buscarEmpleadoPorPlaca = function(empPlaca) {
        if(empPlaca != null || empPlaca != "")
            catalogoService.empleadoPorPlaca(empPlaca)
                .success(function(data){
                	data.empNombre= $scope.convertirNombresMayusYminus(data.empNombre,false);
                    data.empApePaterno= $scope.convertirNombresMayusYminus(data.empApePaterno,true);
                    data.empApeMaterno= $scope.convertirNombresMayusYminus(data.empApeMaterno,true);
                    $scope.empleado = data;
                    $scope.empleado.valido = true;
                    $scope.modificaTramite.DGEmpleado.$valid = true;
                    $scope.empleado.Color = "green";
                    $scope.error = false;
                    $scope.empPlaca = empPlaca;
                }).error(function(data){
                    $scope.error = data;
                    $scope.empleado = {};
                    $scope.modificaTramite.DGEmpleado.$valid = false;
                    $scope.empleado.valido = false;
                    $scope.empleado.Color = "red";
                });
    };
    
    llenarDatosTramite = function(){
    	var lTipoTramites =  $scope.tramiteVO.listaTipoTramite;
    	var arrayTipoTramites = [];
    	
    	var lTipoDocumentos =  $scope.tramiteVO.listaTipoDocumento;
    	var arrayTipoDocumentos = [];
    	
    	for(var x in lTipoTramites){
    		if(!isNaN(x)){
    			if(lTipoTramites[x].idTramite==5){
    				$scope.lineaCaptura50 = true;
    			}
    			if(lTipoTramites[x].idTramite==6)
    				{
    				$scope.tipoPersonaFotocivicas=false;
    				}
    			arrayTipoTramites[x] = lTipoTramites[x].idTramite;
    			$scope.listNbTramite.push(lTipoTramites[x].nbTramite);
    		}
    	}
    	
    	for(var x in lTipoDocumentos){
    		if(!isNaN(x)){
    			if(lTipoDocumentos[x].idDocumento==99){
    				$scope.documentoOtroRequerido = true;
    			}
    			arrayTipoDocumentos[x] = lTipoDocumentos[x].idDocumento;
    			$scope.listNbDocumento.push(lTipoDocumentos[x].nbDocumento);
    		}
    	}
    	
    	$scope.folioTramite = $scope.tramiteVO.idACTramite;
    	$scope.tipoTramite = arrayTipoTramites;
    	$scope.DTDocumento = arrayTipoDocumentos;
    	$scope.medioSolicitud=$scope.tramiteVO.idMedioSolicitud;
    	$scope.buscarEmpleadoPorPlaca($scope.tramiteVO.empId.empPlaca);
    	$scope.DCPaterno = $scope.tramiteVO.nbCPaterno;
    	$scope.DCMaterno = $scope.tramiteVO.nbMaterno;
    	$scope.DCNombre = $scope.tramiteVO.nbCiudadano;
    	
    	$scope.dcEmpresa = $scope.tramiteVO.nbEmpresa;
    	if($scope.tramiteVO.cdTipoPersona == "PM"){
    		$scope.tipoPersona = true;
    		tpPersona = "PM";
    		var acta = $scope.DTDocumento.filter(filtarDocumentoPorId);
			if(acta.length==0){
				actaConstitutivaRequerida = true;
			}
    	}else{
    		$scope.tipoPersona = false;
    		actaConstitutivaRequerida = false;
    		tpPersona = "PF";
    	}

    	
    	$scope.dcTelefono = $scope.tramiteVO.nuCTelefono;
    	$scope.dcCorreo = $scope.tramiteVO.txCCorreo;
    	
    	$scope.DVPlaca = $scope.tramiteVO.cdCPlaca;
    	$scope.DVtipoVehiculo = $scope.tramiteVO.idTipoVehiculo;
    	
    	if($scope.tramiteVO.idMarca!=null){
	    	$scope.DVMarca = $scope.tramiteVO.idMarca;
	    	 
    		var listMarcas =  $scope.catalogoVehiculosMarcas;
    		var nombreMarca;
        	for(var x in listMarcas){
        		if(!isNaN(x)){
        			if(listMarcas[x].vMarId==$scope.DVMarca){
        				nombreMarca=listMarcas[x].vMarNombre;
        			}
        		}
        	}
    	if(nombreMarca=="OTRO"){
			$scope.marcaOtroRequerido = true;
			$scope.DVMarcaOtro = $scope.tramiteVO.nbMarcaOtro;
    	}
		else
			$scope.marcaOtroRequerido = false;
    	
	    	 catalogoService.buscarVehiculosModeloPorMarca($scope.tramiteVO.idMarca).success(function(data) {
	             $scope.catalogoVehiculosModelo = data;
	         	if($scope.tramiteVO.idModelo!=null){
	        		$scope.DVModelo = $scope.tramiteVO.idModelo;
	        		var listModelos =  $scope.catalogoVehiculosModelo;
	        		var nombreModelo;
	            	for(var x in listModelos){
	            		if(!isNaN(x)){
	            			if(listModelos[x].vModId.vModId==$scope.DVModelo){
	            				nombreModelo=listModelos[x].vModNombre;
	            			}
	            		}
	            	}
	    	    	if(nombreModelo=="OTRO"){
	    				$scope.modeloOtroRequerido = true;
	    				$scope.DVModeloOtro = $scope.tramiteVO.nbModelOtro;
	    	    	}
	    			else
	    				$scope.modeloOtroRequerido = false;
	        	}
	             $scope.error = false;
	         }).error(function(data) {
	             $scope.error = data;
	             $scope.catalogoVehiculosModelo = {};
	         });
    	}
    	
    
    	
    	$scope.vehiculoColor = $scope.tramiteVO.idColor;
    	
    	$scope.DCCalle = $scope.tramiteVO.txCCalle;
    	$scope.DCColonia = $scope.tramiteVO.txCColonia;
    	$scope.DCNumExt = $scope.tramiteVO.nuCExt;
    	$scope.DCNumInt = $scope.tramiteVO.nuCInt;
    	
    	$scope.DCEstado = $scope.tramiteVO.idCEDO;
    	if($scope.tramiteVO.idCEDO!=null){
	    	catalogoService.delegacionPorEstado($scope.tramiteVO.idCEDO)
	        	.success(function(data){
	        	$scope.catalogoDelegaciones = data;
	            $scope.error = false;
	        }).error(function(data){
	            $scope.catalogoDelegaciones = {};
	            $scope.error = true;
	        });
    	}
    	
    	$scope.DCDelegacion = $scope.tramiteVO.idCDelegacion;
    	
    	$scope.DTDocumentoOtro = $scope.tramiteVO.nbDocOtro;
    	$scope.DTConCopia = $scope.tramiteVO.txCC;
    	if($scope.DTConCopia)
		{	
		$scope.clasesCss.estiloCorreo=true;
		
		$scope.arregloAuxilarCorreos=$scope.DTConCopia.split(",");
		$scope.DTConCopia=undefined
		tamañoDeCorreo=$scope.arregloAuxilarCorreos.length;
	    longitud=$scope.arregloAuxilarCorreos.length;
		
		if($scope.arregloAuxilarCorreos.length>1)
			{
			for (var x=2;x<=$scope.arregloAuxilarCorreos.length;x++)
			{
			
			 var newDiv = document.createElement("div");
			  newDiv.setAttribute("id", "correos"+(x));
			  newDiv.setAttribute("ng-click","borrarDiv($event)");
			  newDiv.classList.add("estiloCorreo");
			  var newContent = document.createTextNode(""); 
			  newDiv.appendChild(newContent); //añade texto al div creado.
			  
			  var saltoLinea = document.createElement("br");
			  saltoLinea.setAttribute("id", "br"+(x)); 
			  
			  var currentSalto = document.getElementById("br"+(x-1));   
			  currentSalto.insertAdjacentElement("afterend", newDiv);
			  $compile(newDiv)($scope);
			  newDiv.insertAdjacentElement("afterend", saltoLinea);
			
			}
			}
		
		for(var z=1;z<=$scope.arregloAuxilarCorreos.length;z++)
		{
      document.getElementById("correos"+z).innerHTML = $scope.arregloAuxilarCorreos[z-1]; 
		}
			
		
		
		}
    	$scope.DTHechos = $scope.tramiteVO.txHechos;
    	$scope.listaInfracciones=$scope.tramiteVO.txInfraccion;
    	
    	
    	if($scope.listaInfracciones)
  		{
  		$scope.arregloAuxilarInfracciones=$scope.listaInfracciones.split(",");
  		arregloOriginaldeInfracciones=$scope.listaInfracciones.split(",");
  		$scope.listaInfracciones=undefined;
  		$scope.clasesCss2.estiloInfraccion=true;
  		tamañoDeInfraccion=$scope.arregloAuxilarInfracciones.length;
	    longitudInfraccion=$scope.arregloAuxilarInfracciones.length;
  		if($scope.arregloAuxilarInfracciones.length>1)
  			{
  			for (var x=2;x<=$scope.arregloAuxilarInfracciones.length;x++)
				{
  				if($scope.consultaTramitesData[0].nbstSegTramite=='Atendido')
				  {
  				  var newDiv = document.createElement("div");
				  newDiv.setAttribute("id", "infracc"+(x));
				  newDiv.classList.add("estiloInfraccion2");
					  
				  var newContent = document.createTextNode(""); 
				  newDiv.appendChild(newContent); //añade texto al div creado.
				  
				  var saltoLinea = document.createElement("br");
				  saltoLinea.setAttribute("id", "bi"+(x)); 
				  
				  var currentSalto = document.getElementById("bi"+(x-1));   
				  currentSalto.insertAdjacentElement("afterend", newDiv);
				  $compile(newDiv)($scope);
				  newDiv.insertAdjacentElement("afterend", saltoLinea);
				  }else
					  {
					  $scope.listaCamposRequeridos.folioInfrac=true;
					  var newDiv = document.createElement("div");
					  newDiv.setAttribute("id", "infrac"+(x));
					  newDiv.setAttribute("ng-click","borrarDivInfrac($event)");
					  newDiv.classList.add("estiloInfraccion");
						  
					  var newContent = document.createTextNode(""); 
					  newDiv.appendChild(newContent); //añade texto al div creado.
					  
					  var saltoLinea = document.createElement("br");
					  saltoLinea.setAttribute("id", "bi"+(x)); 
					  
					  var currentSalto = document.getElementById("bi"+(x-1));   
					  currentSalto.insertAdjacentElement("afterend", newDiv);
					  $compile(newDiv)($scope);
					  newDiv.insertAdjacentElement("afterend", saltoLinea);
					  
					  
	  			
	  			}
	  			}
  			
  		
	  		
	  		
					  }
  		if($scope.consultaTramitesData[0].nbstSegTramite=='Atendido')
		  {
			for(var z=1;z<=$scope.arregloAuxilarInfracciones.length;z++)
			{
	      document.getElementById("infracc"+z).innerHTML = $scope.arregloAuxilarInfracciones[z-1]; 
			}
		  }
		else
			{
			for(var z=1;z<=$scope.arregloAuxilarInfracciones.length;z++)
			{
	      document.getElementById("infrac"+z).innerHTML = $scope.arregloAuxilarInfracciones[z-1]; 
			}
			}
  			
  			
  		}
      
    	$scope.origenPlaca=$scope.tramiteVO.cdNuevoOrigenPlaca;
    	validarOrigenPlaca2();
    	$scope.idtipoPersona=$scope.tramiteVO.idNuevoTipoPersona;
    	$scope.nuevoEstadoTipoPersona=$scope.tramiteVO.idNuevoEdo;
    	
    };
    
    $scope.guardarTramite = function(){
    	if ($scope.modificaTramite.$invalid) {
            angular.forEach($scope.modificaTramite.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
            showAlert.aviso("Formulario incompleto.", null);
        }else{
        	
        	if (!$scope.empleado.valido){
        		showAlert.error("Por favor, seleccione un oficial válido.");
        	}else{
        		
            	if(actaConstitutivaRequerida && !$scope.DTDocumento.includes(4)){
            		showAlert.error("Es necesario marcar el acta constitutiva como documento por recibir.");
            	}
            	else if($scope.tipoTramite.includes(6)&& $scope.arregloAuxilarInfracciones.length<1)
           	    {
           	       $scope.showError("Es necesario ingresar al menos 1 folio de infracción para el cambio de tipo de persona.");
           	     }
            	else{
			    	$scope.ACTramiteVO.fhAltaString = $filter('formatDate')($scope.fechaTramite,'DD/MM/YYYY HH:mm:ss')+"";
			    	$scope.ACTramiteVO.idACTramite = $scope.folioTramite?$scope.folioTramite:null;
			    	$scope.ACTramiteVO.tipoTramite = $scope.tipoTramite.toString();
			    	$scope.ACTramiteVO.empId = $scope.empleado?$scope.empleado:null;
			    	$scope.ACTramiteVO.cdCPlaca = $scope.DVPlaca?$scope.DVPlaca:null;
			    	$scope.ACTramiteVO.cdTipoPersona = tpPersona?tpPersona:null;
			    	
			    	$scope.ACTramiteVO.idTipoVehiculo = $scope.DVtipoVehiculo?$scope.DVtipoVehiculo:null;
			    	
			    	$scope.ACTramiteVO.idMarca = $scope.DVMarca?$scope.DVMarca:null;
			    	if($scope.marcaOtroRequerido)
			    		$scope.ACTramiteVO.nbMarcaOtro = $scope.DVMarcaOtro?$scope.DVMarcaOtro:null;
			    	
			    	$scope.ACTramiteVO.idModelo = $scope.DVModelo?$scope.DVModelo:null;
			    	if($scope.modeloOtroRequerido)
			    		$scope.ACTramiteVO.nbModelOtro = $scope.DVModeloOtro?$scope.DVModeloOtro:null;
			    	
			    	$scope.ACTramiteVO.idColor = $scope.vehiculoColor?$scope.vehiculoColor:null;
			    	
			    	$scope.ACTramiteVO.nbCPaterno = $scope.DCPaterno?$scope.DCPaterno:null;
			    	$scope.ACTramiteVO.nbMaterno = $scope.DCMaterno?$scope.DCMaterno:null;
			    	$scope.ACTramiteVO.nbCiudadano = $scope.DCNombre?$scope.DCNombre:null;
			    	
			    	$scope.ACTramiteVO.nuCTelefono = $scope.dcTelefono?$scope.dcTelefono:null;
			    	$scope.ACTramiteVO.nbEmpresa = $scope.dcEmpresa?$scope.dcEmpresa:null;
			    	
			    	$scope.ACTramiteVO.txCCorreo = $scope.dcCorreo?$scope.dcCorreo:null;
			    	
			    	$scope.ACTramiteVO.txCCalle = $scope.DCCalle?$scope.DCCalle:null;
			    	$scope.ACTramiteVO.txCColonia = $scope.DCColonia?$scope.DCColonia:null;
			    	$scope.ACTramiteVO.nuCExt = $scope.DCNumExt?$scope.DCNumExt:null;
			    	$scope.ACTramiteVO.nuCInt = $scope.DCNumInt?$scope.DCNumInt:null;
			    	
			    	$scope.ACTramiteVO.idCEDO = $scope.DCEstado?$scope.DCEstado:null;
			    	$scope.ACTramiteVO.idCDelegacion = $scope.DCDelegacion?$scope.DCDelegacion:null;
			    	
			    	$scope.ACTramiteVO.idTipoDoc = $scope.DTDocumento.toString();
			    	if($scope.documentoOtroRequerido)
			    		$scope.ACTramiteVO.nbDocOtro = $scope.DTDocumentoOtro?$scope.DTDocumentoOtro:null;
			    	
			    	$scope.ACTramiteVO.txHechos = $scope.DTHechos?$scope.DTHechos:null;
			    	$scope.ACTramiteVO.txCC = $scope.arregloAuxilarCorreos.length>0?$scope.arregloAuxilarCorreos.toString():null;
			    	$scope.ACTramiteVO.motivoCambio = $scope.DTMCambio?$scope.DTMCambio:null;
			    	
			    	  //Datos de Cambio de tipo de persona
		             $scope.ACTramiteVO.txInfraccion=$scope.arregloAuxilarInfracciones.length>0?$scope.arregloAuxilarInfracciones.toString():null; 
		             $scope.ACTramiteVO.cdNuevoOrigenPlaca=$scope.origenPlaca;
		             $scope.ACTramiteVO.idNuevoTipoPersona=$scope.idtipoPersona?$scope.idtipoPersona:null;
		             $scope.ACTramiteVO.idNuevoEdo=$scope.nuevoEstadoTipoPersona? $scope.nuevoEstadoTipoPersona:null;
		             $scope.ACTramiteVO.txInfraccionAgregadasModi=elementosAgregados.length>0?elementosAgregados.toString():null;
		             $scope.ACTramiteVO.txInfraccionEliminadasModi=elementosBorrados.length>0?elementosBorrados.toString():null;
		             $scope.ACTramiteVO.totalDeInfraccionesModificacion=$scope.arregloAuxilarInfracciones.length>0?$scope.arregloAuxilarInfracciones.length:0; 
		             $scope.ACTramiteVO.idTramiteDetalle=$scope.consultaTramitesData[0].idTramiteDetalle;
		             //medio de solicitud
		             $scope.ACTramiteVO.idMedioSolicitud=$scope.medioSolicitud?$scope.medioSolicitud:99;
			    	
			    	showAlert.confirmacion("Se modificará la información del trámite.", guardarModificacionTramite, $scope.ACTramiteVO);
		        }
        	}
        }
    };
    
    guardarModificacionTramite = function(ACTramiteVO){
    	atencionCiudadanaService.modificaTramite(ACTramiteVO).success(function(data){
    		$scope.Modal = data;
    		$scope.Modal.listNbTramite = $scope.listNbTramite;
    		$scope.Modal.mensaje = "Detalle de modificación.";
    		$scope.error = false;
    		$scope.regresarConsultaModifica($scope.Modal);
    	}).error(function(data){
    		showAlert.error("Ocurrió un imprevisto.");
    		$scope.error = data;
    	});
    };
    
    $scope.regresarConsultaModifica= function(tramiteVO){
    	 ModalService.showModal({
    		 templateUrl: 'views/templatemodal/templateModalACAvisoModificacion.html',
    		 controller: 'mensajeModalControllerACAviso',
    		 inputs:{ Modal: tramiteVO}
    	 }).then(function(modal) {
    		 modal.element.modal();
    		 modal.close.then(function(result) {
    			 if(result){
    				 $location.path('/modificaTramiteBusqueda/true');
    			 }
    		});
    	 });
    };
    
    
    
    $scope.regresar= function(){
    	window.history.back();
    };
    
    
    filtarTipoTramitePoId = function(op){
    	for(var x in $scope.tipoTramite){
    		if(!isNaN(x)){
    			if($scope.tipoTramite[x] == op.idTramite){
    				return op;
    				break;
    			}
    		}
    	}
    };
    
    filtarEdoPoId = function(op){
    	if(op!=undefined)
    		if($scope.DCEstado == op.edoId){
    			return op;
    		}
    	return null;
    };
    
    filtarDelegacionPoId = function(op){
    	if(op!=undefined)
    		if($scope.DCDelegacion == op.delId.delId){
    			return op; 
    		}
    	return null;
    };
    
    filtarTipoVPoId = function(op){
		if($scope.DVtipoVehiculo == op.vTipoId ){
			return op; 
		}
    };
    
    filtarMarcaPoId = function(op){
		if($scope.DVMarca == op.vMarId){
			return op; 
		}
    };
    
    filtarModeloPoId = function(op){
		if($scope.DVModelo == op.vModId.vModId){
			return op; 
		}
    };
    
    filtarColorPoId = function(op){
		if($scope.vehiculoColor == op.vColorId){
			return op; 
		}
    };
    
    filtarDocumentoPoId = function(op){
		for(var x in $scope.DTDocumento){
    		if(!isNaN(x)){
    			if($scope.DTDocumento[x] == op.idDocumento){
    				return op;
    				break;
    			}
    		}
    	}
    };
    
    $scope.vistaPrevia=function() {
    	if ($scope.modificaTramite.$invalid) {
            angular.forEach($scope.modificaTramite.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
            showAlert.aviso("Formulario incompleto.", null);
        }else{
        	
        	if (!$scope.empleado.valido){
        		showAlert.error("Por favor, seleccione un oficial válido.");
        	}else{
        		
            	if(actaConstitutivaRequerida && !$scope.DTDocumento.includes(4)){
            		showAlert.error("Es necesario marcar el acta constitutiva como documento por recibir.");
            	}
            	else if($scope.tipoTramite.includes(6)&& $scope.arregloAuxilarInfracciones.length<1)
           	    {
           	       $scope.showError("Es necesario ingresar al menos 1 folio de infracción para el cambio de tipo de persona.");
           	     }
            	else{
	            	$scope.ACTramiteVistaPrevia.fhAlta = $filter('formatDate')($scope.fechaTramite,'DD/MM/YYYY HH:mm:ss');
			    	$scope.ACTramiteVistaPrevia.idacTramite = $scope.folioTramite;
			    	
			    	var filtroTipoTramite = $scope.comboTipoTramite.filter(filtarTipoTramitePoId);
			    	var cTipoTramite ="";
			    	for(var x in  filtroTipoTramite){
			    		if(!isNaN(x)){
			    			cTipoTramite = cTipoTramite + (x>0 ? ", ": "") + filtroTipoTramite[x].nbTramite;
			    		}
			    	}
			    	
			    	$scope.ACTramiteVistaPrevia.cTramite = cTipoTramite?cTipoTramite:null;
			    	$scope.ACTramiteVistaPrevia.nbPaterno = $scope.DCPaterno?$scope.DCPaterno:null;
			    	$scope.ACTramiteVistaPrevia.nbMaterno = $scope.DCMaterno?$scope.DCMaterno:null;
			    	$scope.ACTramiteVistaPrevia.nbCiudadano = $scope.DCNombre?$scope.DCNombre:null;
			    	$scope.ACTramiteVistaPrevia.nbEmpresa = $scope.dcEmpresa? $scope.dcEmpresa:null;
			    	$scope.ACTramiteVistaPrevia.nuTelefono = $scope.dcTelefono?$scope.dcTelefono:null;
			    	$scope.ACTramiteVistaPrevia.txCorreo = $scope.dcCorreo?$scope.dcCorreo:null;
			    	$scope.ACTramiteVistaPrevia.txCalle = $scope.DCCalle? $scope.DCCalle:null;
			    	$scope.ACTramiteVistaPrevia.txColonia = $scope.DCColonia? $scope.DCColonia:null;
			    	$scope.ACTramiteVistaPrevia.nuExt = $scope.DCNumExt? $scope.DCNumExt:null;
			    	$scope.ACTramiteVistaPrevia.nuInt = $scope.DCNumInt?$scope.DCNumInt:null;
			    	
			    	var filtroEdo = $scope.catalogoEstados.filter(filtarEdoPoId);
			    	if($scope.catalogoDelegaciones!=undefined){
			    		var filtroDel = $scope.catalogoDelegaciones.filter(filtarDelegacionPoId);
			    		$scope.ACTramiteVistaPrevia.cDelegacion = filtroDel.length>1?filtroDel[0].delNombre:null;
			    	}
			    	
			    	
			    	var filtroTipoV = $scope.catalogoTiposVehiculo.filter(filtarTipoVPoId);
			    	var filtroMarca = $scope.catalogoVehiculosMarcas.filter(filtarMarcaPoId);
			    	if($scope.catalogoVehiculosModelo!= undefined){
			    		var filtroModelo = $scope.catalogoVehiculosModelo.filter(filtarModeloPoId);
			    		$scope.ACTramiteVistaPrevia.cModelo = filtroModelo[0].vModNombre?filtroModelo[0].vModNombre:null;
			    	}
			    	var filtroColor = $scope.catalogoVehiculosColor.filter(filtarColorPoId);
			    	
			    	$scope.ACTramiteVistaPrevia.cEstado = filtroEdo.length > 0 ? filtroEdo[0].edoNombre : null;		    	
			    	
			    	
			    	$scope.ACTramiteVistaPrevia.cdPlaca = $scope.DVPlaca?$scope.DVPlaca:null;
			    	$scope.ACTramiteVistaPrevia.cVehiculo = filtroTipoV[0].vTipoNombre?filtroTipoV[0].vTipoNombre:null;
			    	$scope.ACTramiteVistaPrevia.cMarca = filtroMarca[0].vMarNombre?filtroMarca[0].vMarNombre:null;
			    	
			    	$scope.ACTramiteVistaPrevia.cColor = filtroColor[0].vColorNombre?filtroColor[0].vColorNombre:null;
			    	
			    	if($scope.marcaOtroRequerido)
			    	$scope.ACTramiteVistaPrevia.nbMarcaOtro = $scope.DVMarcaOtro? $scope.DVMarcaOtro:null;
			    	if($scope.modeloOtroRequerido)
			    	$scope.ACTramiteVistaPrevia.nbModeloOtro = $scope.DVModeloOtro?$scope.DVModeloOtro:null;
			    	
			    	$scope.ACTramiteVistaPrevia.nbOficial = $scope.empleado.empNombre+' '+$scope.empleado.empApePaterno+' '+$scope.empleado.empApeMaterno;
			    	$scope.ACTramiteVistaPrevia.cdCPlaca = $scope.DVPlaca?$scope.DVPlaca:null;
			    	
			    	var filtroDocumento = $scope.comboTipoDocumento.filter(filtarDocumentoPoId);
			    	
			    	var cDocumento ="";
			    	for(var x in  filtroDocumento){
			    		if(!isNaN(x)){
			    			cDocumento = cDocumento + (x>0 ? ", ": "") + filtroDocumento[x].nbDocumento;
			    		}
			    	}
			    	$scope.ACTramiteVistaPrevia.cDocumento = cDocumento;
			    	if($scope.documentoOtroRequerido)
			    		$scope.ACTramiteVistaPrevia.nbDocOtro = $scope.DTDocumentoOtro?$scope.DTDocumentoOtro:null;
			    	
			    	$scope.ACTramiteVistaPrevia.txHechos = $scope.DTHechos?$scope.DTHechos:null;
			    	$scope.ACTramiteVistaPrevia.txCc = $scope.arregloAuxilarCorreos.length>0?$scope.arregloAuxilarCorreos.toString():null;
			    	$scope.ACTramiteVistaPrevia.motivoCambio = $scope.DTMCambio?$scope.DTMCambio:null;
			    	
		            //Datos de Cambio de tipo de persona
		             $scope.ACTramiteVistaPrevia.foliosDeInfraccion=$scope.arregloAuxilarInfracciones.length>0?$scope.arregloAuxilarInfracciones.toString():null; 
		             $scope.ACTramiteVistaPrevia.nuevoOrigenTipo=$scope.origenPlaca;
		             $scope.ACTramiteVistaPrevia.nuevoTipo=$scope.idtipoPersona?$scope.idtipoPersona:null;
		             $scope.ACTramiteVistaPrevia.nuevoIdEstadoTipo=$scope.nuevoEstadoTipoPersona? $scope.nuevoEstadoTipoPersona:null;
		             //medio de solicitud
		             $scope.ACTramiteVistaPrevia.idMedioSolicitud=$scope.medioSolicitud?$scope.medioSolicitud:99;
			    	
			    	
			    	atencionCiudadanaService.vistaPrevia($scope.ACTramiteVistaPrevia).success(function(data,status,headers){
			    		$("#myModal").modal()
			    		var filename = headers('filename');
	                    var contentType = headers('content-type');
	                    var file = new Blob([ data ], {
	                        type : 'application/pdf;base64,'
	                    });
	                    fileURL = URL.createObjectURL(file);
	                    var blob_iframe = document.querySelector('#blob-src-test');
	                    blob_iframe.src =fileURL ;
	//                    window.open(fileURL);
	                })
	                .error(function(data){
	                    showAlert.error("Imposible visualizar el reporte, inténtelo más tarde.");
	                   
	                });
	            }
        	}
        }
	};
	
	$scope.generaReporteTramite = function(idTramite){
        atencionCiudadanaService.generaReporteTramite(idTramite)
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
    };
	
	buscarCamposRequeridos=function(){
		atencionCiudadanaService.camposRequeridos().success(function(data){
			$scope.listaCamposRequeridos=data;
        }).error(function(data){
        	$scope.showError("Sin acceso al recurso.")
        });
	};
	
	$scope.letraCapitalAP = function(texto){
		$scope.DCPaterno = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
	};

	$scope.letraCapitalAM = function(texto){
		$scope.DCMaterno = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
	};

	$scope.letraCapitalNB = function(texto){
		$scope.DCNombre = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
	};
	
	$scope.letraCapital = function(texto){
		if(texto != null || texto != undefined){
			var t = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
			return t;
		}
		return "";
	};
	
	$scope.refrescarpag = function(){
		$window.location.reload();
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
	
	$scope.validarcc=function()
	{
	
		
		var aumento=false;
		
		if($scope.DTConCopia)
			{
			
			
			if(longitud<5)
			{
				$scope.clasesCss.estiloCorreo=true;
				$scope.arregloAuxilarCorreos.push($scope.DTConCopia);
				$scope.DTConCopia=undefined;
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
	      document.getElementById("correos"+z).innerHTML =$scope.arregloAuxilarCorreos[z-1]; 
			}
			}
			else if(longitud>=5)
			{
			 $scope.showError("Has alcanzado el límite de correos.");
			 $scope.DTConCopia=undefined;
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
	    	var probando=document.getElementById("correos1");
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
		 
			if($scope.listaInfracciones)
				{
               if($scope.listaInfracciones.length!=11)
             	  {
             	  $scope.errorLongitud=true;
             	  }
               else if($scope.listaInfracciones.substring(0,2)!="03" && $scope.listaInfracciones.substring(0,2)!="08")
             	  {
             	  $scope.errorTipoDeFolio=true;
             	  }
               else if(!$scope.DVPlaca)
             	  {
             	  $scope.showError("Ingrese la placa vehicular.");
             	  }
               else if(longitudInfraccion<10)
             	  {
             	  atencionCiudadanaService.consultaInfraccion($scope.DVPlaca,$scope.listaInfracciones)
                   .success(function(data){
                  var encontroInfraccion=data;
                  if(encontroInfraccion=="SI")
                 	 {
                 	 $scope.clasesCss2.estiloInfraccion=true;
						$scope.arregloAuxilarInfracciones.push($scope.listaInfracciones);
						 elementosAgregados.push($scope.listaInfracciones);						
						$scope.listaInfracciones=undefined;
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
					 $scope.listaInfracciones=undefined;
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
				elementosBorrados.push($scope.arregloAuxilarInfracciones[0]);
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
				 elementosBorrados.push(arregloAuxiliar.splice(posicionArreglo-1, 1));
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
			if($scope.origenPlaca==0)
				{
				$scope.bloquearFisicaYMoral=false;
				$scope.idtipoPersona="";
				$scope.nuevoEstadoTipoPersona=9;
				}
			else
				{
				$scope.bloquearDesconocido=false;
				$scope.idtipoPersona="";
				$scope.bloquearFisicaYMoral=false;
				}
		}
		
	       validarOrigenPlaca2=function()
		{
			$scope.bloquearDesconocido=true;
			$scope.bloquearFisicaYMoral=true;
			if($scope.origenPlaca==0)
				{
				$scope.bloquearFisicaYMoral=false;
				}
			else
				{
				$scope.bloquearDesconocido=false;
				$scope.bloquearFisicaYMoral=false;
				}
		}
	       
	       comprobarEstatusExpediente=function()
	       {
	    	   atencionCiudadanaService.consultaSeguimiento(
	   				1,
	   				$scope.tramiteVO.idACTramite, 
	   				99, 
	   				99, 
	   				99,//tipoFecha 
	   				null,//fhInicio 
	   				null //fhFin
	   			).success(function(data) {
	   				$scope.consultaTramitesData = data;
	   				llenarDatosTramite();
	   			}).error(function(datos) {
	   				$scope.error = data;
	   				$scope.consultaTramitesData = {};
	   				$scope.viewpanelConsultaTramites = false;
	   				$scope.showAviso(data.message, "templateModalAviso");
	   			});
	    	   
	       };
	
	
	
	buscarCamposRequeridos();
    llenarCatalogoEstados();
    llenarCatalogoTipoVehiculo();
    llenarCatalogoVehiculosMarcas();
    llenarCatalogoVehiculosColor();
    filtroTipoTramite();
    filtroOrigenSolicitud();
    filtroTipoDocumento();
    comprobarEstatusExpediente();   
    buscarInformacionTooltipAyuda();
    
    
});