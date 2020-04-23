angular
		.module('siidfApp')
		.controller(
				'reportesMapaController',
				function($scope, reporteInfraccionesService, $filter,
						ModalService) {
					
					var map = undefined, c1=0, c2=0, c3=0, c4=0, c5=0, c6=0, c7=0, c8=0, numcolor = [];
					$scope.isDisabled = true; 
					$scope.filtroBusqueda = {};
					$scope.data = {model : [], availableOptions : {}};
					
					filtroRadares = function (){
						reporteInfraccionesService.tipoInfracciones().success(
								function(data) {
									
									$scope.data.availableOptions =   filtroTipoInfracciones(data);
									$scope.data.availableOptions  = $filter('filter')($scope.data.availableOptions,{cdTipoInfraccion:'!02'});
								}).error(function(data) {
						});
					}
					
					
				 filtroTipoInfracciones = function (arrayTipoInfraccion){

						angular.forEach(arrayTipoInfraccion, function (value) {
			            	if(value.cdTipoInfraccion == 'x')
			            		value.cdTipoInfraccion= '10';
			            })
			            
			            return arrayTipoInfraccion;
					}
					
					filtroRadares();

					// Datos combo mapa búsqueda ************************************
					$scope.datab = {
						model : '01',
						availableOptions : [ {
							id : '01',
							name : 'Del Día'
						}, {
							id : '02',
							name : 'Por Fecha'
						} ]
					}; 
					
					$scope.actualizarConsulta = function() {
						
						if( $scope.datab.model == '01' && (angular.isDefined($scope.data.model) &&  $scope.data.model.length > 0)){					
							$scope.infraccionesDelDia();
						}
						else{
							
						   if($scope.datab.model == '02' && $scope.filtroBusqueda.fechaInicio != null && $scope.filtroBusqueda.fechaFin != null && 
								   (angular.isDefined($scope.data.model) &&  $scope.data.model.length > 0)){					
							   $scope.actualizarCartodbPorFecha();
						   }
						   else{
								requiredFields();
							}
						}
				
					}
					
					$scope.actualizarCartodb = function() {
						reporteInfraccionesService.upCarto($scope.data.model).success(
								function(data) {
									$scope.information = data;
									overrideJson(false);
								}).error(function(data) {
						});
					}
					
					$scope.actualizarCartodbPorFecha = function() {	
							reporteInfraccionesService.upCartoTipoFecha(
									$scope.data.model,
									$scope.filtroBusqueda.fechaInicio,
									$scope.filtroBusqueda.fechaFin).success(
									function(data) {
										$scope.information = data;
										overrideJson(false); 
									}).error(function(data) {
							});
					}
					
					$scope.infraccionesDelDia = function() {
						var fechadehoy = moment().format('DD/MM/YYYY');
						reporteInfraccionesService.upCartoTipoFecha(
								$scope.data.model,
								fechadehoy,
								fechadehoy).success(
								function(data) {
									$scope.information = data;
									overrideJson(false);
								}).error(function(data) {
						});
				   }

					$scope.loadFirstTime = function (){
						var fechadehoy = moment().format('DD/MM/YYYY');
						reporteInfraccionesService.upCartoTipoFecha(
								$scope.data.model,
								fechadehoy,
								fechadehoy).success(
								function(data) {
									$scope.information = data;
									overrideJson(true);
								}).error(function(data) {
						});
					}
					
					requiredFields = function(){
						angular.forEach($scope.reportesEstadisticasInfracciones.$error, function (field) {
				            	angular.forEach(field, function(errorField){
				            	errorField.$setDirty();
				            	//alert(errorField.$name);
				            	//alert(errorField.$dirty);
				            })
				            $scope.showAviso("Formulario Incompleto");
						});
					};
					
					function getColor(d) {
						return d > c8 ? '#800026'
								: d > c7 ? '#bd0026'
										: d > c6 ? '#e31a1c'
												: d > c5 ? '#fc4e2a'
														: d > c4 ? '#fd8d3c'
																: d > c3 ? '#feb24c'
																		: d > c2 ? '#fed976'
																			: d >= c1 ? '#ffeda0'
																				       : '#ffffcc';
					}
					
					function style(feature) {
						return {
							fillColor : getColor(feature.properties.num_infracciones),
							weight : 2,
							opacity : 1,
							color : 'white',
							dashArray : '3',
							fillOpacity : 0.97
						};
					}

					function popup(feature, layer) {
						var str1 = "<b><center>" + feature.properties.nom_mun + "</center></b>";
						var str2 = "<b>Total: </b>" +  $filter('number')(feature.properties.num_infracciones) + "";
						var str3 = "<ul class='list'><li><a class='plus'>Detalles</a><ul><table>"
						
						
						
						var str4 =  "";
						var tipoRadar = $scope.data.model;
						for(var r = 0; r < tipoRadar.length; r++){
							var codRadar =  tipoRadar[r];
							if(feature.properties[codRadar] > 0){
								str4 = str4 + "<tr><td><b>" + tipoInfraccion(codRadar) + "</b></td> <td align='right'>" +  $filter('number')(feature.properties[codRadar]) + "</td></tr>";
							}
						}
						
						var str5 = "</table></ul></li></ul>";
			
						var res = str1.concat(str2, str3, str4, str5);
						if (feature.properties  && feature.properties.nom_mun) {
							layer.bindPopup(res, {maxWidth: "none"});
						}
					}

					function buildComponent(isFirstTime){
						if(isFirstTime){
							map = L.map('map', {
								minZoom: 0,
								maxZoom: 18,
								zoomSnap: 0,
								zoomDelta: 0.5,
								fullscreenControl: true,
								fullscreenControlOptions: { // optional
								title:"Pantalla completa",
								titleCancel:"Salir pantalla completa"
								}
							});
							

							$('body').on('click', '.list > li a', function() {
								$(this).parent().find('ul').toggle();
								if($(this).hasClass("plus")){
									$(this).removeClass("plus");
									$(this).addClass("minus");
								}else{
									$(this).removeClass("minus");
									$(this).addClass("plus");
								}
							});
						}
						
						L.tileLayer(
										'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
										{
											attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
											maxZoom : 18
										}).addTo(map);
						L.control.scale().addTo(map);
						zoomMap(map);
						L.marker([ 41.66, -4.71 ], {
							draggable : true
						}).addTo(map);
						
						L.geoJson(delegaciones).addTo(map);
						
						L.geoJson(delegaciones, {
							style : style
						}).addTo(map);
						
						geojson = L.geoJson(delegaciones, {
							style : style,
							onEachFeature : popup
						}).addTo(map);
					    
						nombreMapa(map);
					
					}
					
					function overrideJson (isFirstTime){
						//Limpia cantidad de infracciones
						for(var j = 0; j < delegaciones.features.length; j++){		
							delegaciones.features[j].properties.num_infracciones = 0;
							delegaciones.features[j].properties["01"] = 0;
							delegaciones.features[j].properties["03"] = 0;
							delegaciones.features[j].properties["04"] = 0;
							delegaciones.features[j].properties["05"] = 0;
							delegaciones.features[j].properties["06"] = 0;
							delegaciones.features[j].properties["07"] = 0;
							delegaciones.features[j].properties["08"] = 0;
							delegaciones.features[j].properties["09"] = 0;
							delegaciones.features[j].properties["10"] = 0;
						}
						
						//Sobreescribe cantidad de infracciones
						var tipoRadar = $scope.data.model;//['01', '03'];
						for(var r = 0; r < tipoRadar.length; r++){
							var radar = tipoRadar[r];
							var lista = $scope.information[radar];
							
							if(angular.isDefined(lista)){
								if(lista.length > 0){
									
									for(var j = 0; j < delegaciones.features.length; j++){
										var del = delegaciones.features[j].properties.cve_delegacion;
										
										for(var x = 0; x < lista.length; x ++){
											if(del == lista[x].delegacion){
												delegaciones.features[j].properties[radar] = lista[x].cantidad;
												delegaciones.features[j].properties.num_infracciones = parseInt(delegaciones.features[j].properties.num_infracciones) + parseInt(lista[x].cantidad);
											}
										}
									}
									
								}
							}
						}
						arrayColor();
						buildComponent(isFirstTime);
					}
					
					$scope.changeCombo = function(){
						$scope.filtroBusqueda.fechaInicio = '';
						$scope.filtroBusqueda.fechaFin = '';
						$scope.reportesEstadisticasInfracciones.$setPristine();
					}
					
					function nombreMapa(map){
						var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/coy.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.325885, -99.168863], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/gam.png',
							iconSize: [130, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.487287, -99.141752], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/bju.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.377389, -99.18191], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/aob.png',
							iconSize: [130, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.353178, -99.250703], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/azc.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.480106, -99.21018], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/cuj.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.347995, -99.319539], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/cuh.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.411069, -99.171953], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/izc.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.395242, -99.110295], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/izp.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.342083, -99.053216], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/mac.png',
							iconSize: [140, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.23547, -99.312029], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/mih.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.407831, -99.232893], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/mil.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.137384, -99.07814], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/tlh.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.299023, -99.043647], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/tlp.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.190569, -99.242935], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/vca.png',
							iconSize: [110, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.430516, -99.114136], {
								icon: deathIcon
							})
							.addTo(map);
				var deathIcon = L.icon({
							iconUrl: 'static/dist/img/mapa/xoc.png',
							iconSize: [100, 30],
							iconAnchor: [18, 18],
							popupAnchor: [0, -18],
							labelAnchor: [14, 0] // as I want the label to appear 2px past the icon (18 + 2 - 6)
						})
						var noHide = false;
						L.marker([19.257231, -99.102966], {
								icon: deathIcon
							})
							.addTo(map);
						
					}
					
					function tipoInfraccion(data){
						var tipo = undefined;
						
							if(data == '11'){
							tipo = 'Del día';
							}
							if(data == '00'){
							tipo = 'Todos';
							}
							if(data == '01'){
							tipo = 'Gruas';
							}
							if(data == '03'){
							tipo = 'SSP Velocidad';
							}
							if(data == '04'){
							tipo = 'Pie tierra';
							}
							if(data == '05'){
							tipo = 'Inmovilizador';
						    }
							if(data == '06'){
							tipo = 'Parkímetros';
							}
							if(data == '07'){
							tipo = 'Carril confinado';
							}
							if(data == '08'){
							tipo = 'Fotomultas';
						    }
							if(data == '09'){
							tipo = 'Consesionados';
						    }
							if(data == '10'){
							tipo = 'Preimpresa';
						    } 
						
						
						return tipo;
					} 
					
					$scope.habilitaCampoFecha = function (bolean){
					
						return $scope.isDisabled = bolean;
					}
					
					function arrayColor(){
						
						c1=0, c2=0, c3=0, c4=0, c5=0, c6=0, c7=0, c8=0, numcolor = [];
						//Obtiene el total de infracciones
						var tipoRadar = $scope.data.model;//['01', '03'];
						for(var r = 0; r < tipoRadar.length; r++){
							var radar = tipoRadar[r];
							var lista = $scope.information[radar];
							
							if(angular.isDefined(lista)){
								if(lista.length > 0){
									
									for(var j = 0; j < delegaciones.features.length; j++){
										var del = delegaciones.features[j].properties.cve_delegacion;
										
										for(var x = 0; x < lista.length; x ++){
											if(del == lista[x].delegacion){
												delegaciones.features[j].properties[radar] = lista[x].cantidad;
												var suma = parseInt(delegaciones.features[j].properties.num_infracciones) + parseInt(lista[x].cantidad);
												suma = (suma/5);
												numcolor.push(suma);
											}
										}
									}
									
								}
							}
						}
						/*for(var x = 0; x < $scope.information.length; x ++){			
							numcolor.push($scope.information[x].cantidad);
						}*/
						
						numcolor.sort(function(a, b){return b-a});
						
						for (var i=0; i<numcolor.length; i++) {
							if(i==1){
								c8 = numcolor[0] + numcolor[1];
							}
							if(i==3){
								c7 = numcolor[2] + numcolor[3];
							}
							if(i==5){
								c6 = numcolor[4] + numcolor[5];
							}
							if(i==7){
								c5 = numcolor[6] + numcolor[7];
							}
							if(i==9){
								c4 = numcolor[8] + numcolor[9];
							}
							if(i==11){
								c3 = numcolor[10] + numcolor[11];
							}
							if(i==13){
								c2 = numcolor[12] + numcolor[13];
							}
							if(i==15){
								c1 = numcolor[14] + numcolor[15];
							}
						}
						
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
					
					function zoomMap(map){
					    var ZoomViewer = L.Control.extend({
							onAdd: function(){

								var container= L.DomUtil.create('div');
								var gauge = L.DomUtil.create('div');
								container.style.width = '200px';
								container.style.background = 'rgba(255,255,255,0.5)';
								container.style.textAlign = 'left';
								container.appendChild(gauge);

								return container;
							}
						});

						(new ZoomViewer).addTo(map);

						map.setView([19.43, -99.13], 11);
					 }
					
					$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
						$('.selectpicker').selectpicker();
						$('.selectpicker').selectpicker('refresh');
						$('.selectpicker').selectpicker('selectAll');
						$scope.loadFirstTime();
					});
					
					$scope.initController = function() {
						$("#fInicio").datepicker({
							format: 'dd/mm/yyyy',
							//language: 'es',
							todayHighlight : true,
							endDate : '0d',
							autoclose : true
						}).on('changeDate', function (selected) {
				        	var minDate = new Date(selected.date.valueOf());
				        	$('#fFin').datepicker('setStartDate', minDate);
				    	});
						
						$("#fFin").datepicker({           
							format: 'dd/mm/yyyy',
							//language: 'es',
							todayHighlight : true,
							endDate : '0d',
							autoclose : true
						}).on('changeDate', function (selected) {
				        	var maxDate = new Date(selected.date.valueOf());
				        	$('#fInicio').datepicker('setEndDate', maxDate);
				    	});
					}
					
					$scope.initController();				
					
				});
