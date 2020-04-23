angular.module('siidfApp').controller('consultaCertificadoControlller', function($location,$scope,ModalService,certificadoService,$location,$route,utileriaService) {

	//console.log('Controller Consulta Certificado SAT');
	var paramBusqueda = null;
	var tipoBusqueda = null;
	var comboValidado = null;
	var fechaInicio = null;
	var fechaFin = null;
	$scope.mostrarTabla=false;
	$scope.formCertificado = {};
	$scope.actualizarCertificado= function (placa) {
		$location.path('/cargaCertificadoSAT/'+ placa);
	}
	$scope.order='certValidoDesdeOrder';


	$scope.descargarCertificados=function(certificado, certificadoNombre,  llave, nombreLlave ) {	
		
	 	generaArchivo(certificadoNombre, certificado ,"application/x-x509-ca-cert");
	  	generaArchivo(nombreLlave, llave ,"application/octet-stream");
	}
	
	$scope.cargarUsuarioDeshabilitado = function(index){
		if($scope.certificadosVO != null){
		$scope.placaUsuarioActual = $scope.certificadosVO[index].empleadoVO.empPlaca;		
		}
	};
	
	
	
	iniciaCombos = function (estado) {
		
		 $scope.opc = {
	    	     availableOptions: [
	                {id: '', name: 'Todos'},
	    	       {id: 'empCod', name: 'Placa/Usuario'},
	    	       {id: 'empApePaterno', name: 'Apellido Paterno'},
	    	       {id: 'empNombre', name: 'Nombre'}
	    	      
	    	     ],
	    	     tipoBusqueda : {id: '', name: 'Todos'} //This sets the default value of the select in the ui
	    	     };
	    
	    $scope.opc2 = {
	   	     availableOptions: [
	   	       {id: '3', name: 'Todos'},
	   	       {id: '1', name: 'Validado'},
	   	       {id: '0', name: 'No Validado'}
	   	     ],
	   	  comboValidado: {id: '3', name: 'Todos'} //This sets the default value of the select in the ui
	   	     };
	    
	    $scope.fechaInicio = "";
	    $scope.fechaFin = "";
	    obtenerCamposFormulario(estado);	
	}
	
	
	
	$scope.consultaCertificadosPorVencer=function () {
		
		
		comboValidado = $scope.opc2.comboValidado.id;
		tipoBusqueda = $scope.opc.tipoBusqueda.id;
		fechaInicio = $scope.fechaInicio;
		fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/','-') :  null ;
		fechaFin = $scope.fechaFin;
		fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/','-') :  null ;
		paramBusqueda =  $scope.paramBusqueda;
		var btnBuscar = true;
		
		if (validaParametros(btnBuscar)) {

			certificadoService.buscarPorFechas(tipoBusqueda,
												comboValidado,
												paramBusqueda,
												fechaInicio,
												fechaFin).success(function(data) {	
				
				imagenCertificadoValidado(data[0].validado);				
				var listDates= ['certValidoDesde','certValidoHasta'];
				var listData = diasVencimiento(data);
				listData = utileriaService.orderData(listDates,[],[],listData);	
				$scope.certificadosVO = listData;	

				$scope.mostrarTabla=true;
				certificadoService.setListaCertificadosConsulta($scope.certificadosVO);
			
				
			}).error(function(data) {
				$scope.mostrarTabla=false;
				 showAviso(data.message);
				
				
				
			});  
		}
	}
	
     consultaCertificadosPorVencer=function () {

		comboValidado = $scope.opc2.comboValidado.id;
		tipoBusqueda = $scope.opc.tipoBusqueda.id;
		fechaInicio = $scope.fechaInicio;
		fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/','-') :  null ;
		fechaFin = $scope.fechaFin;
		fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/','-') :  null ;
		paramBusqueda =  $scope.paramBusqueda;
		var btnBuscar = false;
		
		if (validaParametros(btnBuscar)) {

			certificadoService.buscarPorFechas(tipoBusqueda,
												comboValidado,
												paramBusqueda,
												fechaInicio,
												fechaFin).success(function(data) {	

				imagenCertificadoValidado(data[0].validado);				
				var listDates= ['certValidoDesde','certValidoHasta'];
				var listData = diasVencimiento(data);
				listData = utileriaService.orderData(listDates,[],[],listData);	
				$scope.certificadosVO = listData;	
								
				$scope.mostrarTabla=true;
				
			}).error(function(data) {
				$scope.mostrarTabla=false;
				
				 showAviso(data.message);
				
			});  
		}
	}
	
	
     
     
     
     
	
	 diasVencimiento = function(data){
 
		 
		 $scope.listaCertificados =  data;

		 angular.forEach($scope.listaCertificados, function(item){
			 
			 var certValidoHasta = item.certValidoHasta;
				var certValidoDesde = item.certValidoDesde;
				var dias =	restaFechas(new Date().toLocaleDateString(),certValidoHasta);
                 item.numDias = dias;  
         });
		
		 
		 return $scope.listaCertificados;
		
		
	 
	}
	
	  restaFechas=function(fechaActual, fechaRestar)
	 {
	 	var aFecha1 = fechaActual.split('/'); 
	 	var aFecha2 = fechaRestar.split('/'); 
	 	var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]); 
	 	var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]); 
	 	var dif = fFecha2 - fFecha1;
	 	var dias = Math.floor(dif / (1000 * 60 * 60 * 24)); 
	  	return dias;
	 }
	 
	 imagenCertificadoValidado =function  (validado){
			if (validado == 1){
				$scope.EstatusVal = true;
			}
				if (validado == 0){
				$scope.EstatusInval = true;
				}
			}
	
	 validaParametros=function(btnBuscar) {

		if (tipoBusqueda == 'empApePaterno' || tipoBusqueda == 'empNombre') {
			paramBusqueda = paramBusqueda ? paramBusqueda.toUpperCase() : null;

		}

		if (paramBusqueda == '' ||  paramBusqueda== null){
	 
	 		tipoBusqueda = "";
			paramBusqueda = "";
		}

		
		
		if (fechaInicio == null && fechaFin == null) {

			fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/','-') :  "null" ;
			fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/', '-') :  "null" ;
			paramBusqueda = paramBusqueda ? paramBusqueda.toUpperCase() :"null" ;
	 		fechaFin = sumaDias(moment(), 30);
	 		fechaFin=moment(fechaFin).format("DD/MM/YYYY");
	 		if(btnBuscar){
	 			fechaFin = "null";
	 		}
	 			
	 
			return true;

		} else if(fechaInicio != null){
			fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/', '-') : 'null';
			fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/', '-')	: "null" ;
			paramBusqueda = paramBusqueda ? paramBusqueda.toUpperCase()	: "null" ;
			return true;
		}
		 else if(fechaFin != null){
				fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/', '-') : "null" ;
				fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/', '-')	: "null" ;
				paramBusqueda = paramBusqueda ? paramBusqueda.toUpperCase()	: "null" ;
				return true;
		}else if ((fechaInicio != null && fechaFin != null) || paramBusqueda) {
	 			fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/', '-') : "null" ;
				fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/', '-')	: "null" ;
				paramBusqueda = paramBusqueda ? paramBusqueda.toUpperCase()	: "null" ;
			
				return true;
			} else {

				return false;
			}

		
	}
	
	obtenerCamposFormulario=function (estado) {
		
		comboValidado = $scope.opc2.comboValidado.id;
		tipoBusqueda = $scope.opc.tipoBusqueda.id;
		fechaInicio = $scope.fechaInicio;
		fechaInicio = fechaInicio ? fechaInicio.replace('/', '-').replace('/','-') :  null ;
		fechaFin = $scope.fechaFin;
		fechaFin = fechaFin ? fechaFin.replace('/', '-').replace('/','-') :  null ;
		paramBusqueda =  $scope.paramBusqueda;

		
		if(estado){
		consultaCertificadosPorVencer();
		}
	}
	
	 sumaDias=function(fecha, dias) {
		fecha= fecha.add(dias, 'days');
	 	return fecha;
	}
	 
	 function generaArchivo(nombre, archivo, extension) {
		 
		 	var blob = new base64toBlob(archivo, extension);
			 var url = window.URL || window.webkitURL;
			 var blobUrl = url.createObjectURL(blob);
			 var a         = document.createElement('a');
			 a.href        = blobUrl; 
			 a.target      = '_blank';
			 a.download    = nombre;
			 document.body.appendChild(a);
			 a.click();

		}

		function base64toBlob(base64Data, contentType) {
		    contentType = contentType || '';
		    var sliceSize = 1024;
		    var byteCharacters = atob(base64Data);
		    var bytesLength = byteCharacters.length;
		    var slicesCount = Math.ceil(bytesLength / sliceSize);
		    var byteArrays = new Array(slicesCount);

		    for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
		        var begin = sliceIndex * sliceSize;
		        var end = Math.min(begin + sliceSize, bytesLength);

		        var bytes = new Array(end - begin);
		        for (var offset = begin, i = 0 ; offset < end; ++i, ++offset) {
		            bytes[i] = byteCharacters[offset].charCodeAt(0);
		        }
		        byteArrays[sliceIndex] = new Uint8Array(bytes);
		    }

		    var blob = new Blob(byteArrays, {type: contentType});
		    return blob;
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
		   
			      
		$scope.$on('$locationChangeSuccess', function(event, current, previous) {

			          $scope.proximoController = $route.current.$$route.controller;
			          if( $scope.proximoController == 'cargaCertificadoController'  ){
			        	 
			        	  certificadoService.setListaCertificadosConsulta($scope.certificadosVO);
			          }
			 		   else{   

			 			 $scope.certificadosListaVO ={};            
				 		 certificadoService.setListaCertificadosConsulta($scope.certificadosListaVO);
			 			
			 		   }
			   
			      });
		
		 verificaLista = function() {
			   if (certificadoService.getListaCertificadosConsulta().length  > 0) {
				   
				   iniciaCombos(false);
				   $scope.certificadosVO = certificadoService.getListaCertificadosConsulta();
				   $scope.mostrarTabla=true;
				   
			   }else{
				   
				   iniciaCombos(true); 
			   }
				 
			   
		   };    
		   
		   
		   $scope.setPrestine = function(){
			   if ($scope.opc.tipoBusqueda.name == "Todos"){
				   $scope.paramBusqueda = "";
					$scope.formCertificado.$setPristine();
			   }
		   };

		   verificaLista();
	

});

