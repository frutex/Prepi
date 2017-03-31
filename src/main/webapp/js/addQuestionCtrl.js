angular.module('ExPrep').controller(
		'AddQuestionCtrl',
		[
				'$http',
				'$scope',
				'RequestFactory',
				'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

					
					$scope.today = new Date();
					$scope.hochschulen = [];
					$scope.dozenten;
					$scope.module;
					
					$scope.startup = function() {
						$scope.checkAuth();
						$scope.getHochschulen();
						$scope.getModule();
						$scope.getDozenten();
						var i = 0;
					}

					$scope.selected = {
						"Titel" : "Bitte Fragentitel Eingeben",
						"Hochschule" : "",
						"Modul" : "",
						"Dozent" : "",
						"Beschreibung" : "Bitte Beschreibung einfügen",
						"Datum" : $scope.today.getFullYear()
					}

				
					
					$scope.setDate = function(){
						$scope.selected.Datum = $scope.today.getFullYear(); 
					}

					$scope.getHochschulen = function() {
						RequestFactory.getHochschulen().then(function(data) {
							if (data.data.successfull) {
								$scope.hochschulen = data.data.data;
							} else {
								alert(data.data.data);
								// $scope.$apply;
							}
						});
					}

					$scope.addFrage = function() {
						RequestFactory.addFrage($scope.selected).then(
								function(data) {
									if (data.data) {
										window.location.href = "./danke.html";
									} else {
										alert("Error");
										// $scope.$apply;
									}
								});
					}

					$scope.getDozenten = function() {
						RequestFactory.getDozenten().then(function(data) {
							if (data.data.successfull) {
								$scope.dozenten = data.data.data;
							} else {
								alert(data.data.data);
								// $scope.$apply;
							}
						});
					}

					$scope.getModule = function() {
						RequestFactory.getModule().then(function(data) {
							if (data.data.successfull) {
								$scope.module = data.data.data;
							} else {
								alert(data.data.data);
								// $scope.$apply;
							}
						});
					}
					
					$scope.loadDModule = function() {
						RequestFactory.getModulForDozent($scope.selected.Dozent).then(function(data) {
							if (data.data.successfull) {
								$scope.module = data.data.data;
							} else {
								alert(data.data.data);
								// $scope.$apply;
							}
						});
					}

					$scope.checkAuth = function() {
						token = AuthService.getUserToken();
						if (AuthService.isAuthenticated && token) {

							RequestFactory.checkAuthToken().then(
									function(data) {
										if (data.data.successfull) {

										} else {
											alert(data.data.token);
											// $scope.$apply;
										}
									});

						} else {
							var loc = window.location.pathname;
							var dir = loc.substring(loc.lastIndexOf('/'),
									loc.length);
							if (dir != "/login.html") {
								if (dir != "/") {
									window.location.href = "./login.html";
								}
							}
						}
					}

					$scope.dozentenName = function(index) {
						return $scope.dozenten[index].Nachname + ", "
								+ $scope.dozenten[index].Vorname;
					}

					$scope.dozentenNameObj = function(dz) {
						return dz.Nachname + ", " + dz.Vorname;
					}

					$scope.loadHSDozenten = function() {
						RequestFactory.getDozentenForHochschule(
								$scope.selected.Hochschule.Name).then(
								function(data) {
									if (data.data.successfull) {
										$scope.dozenten = data.data.data;
									} else {
										alert(data.data.data);
										// $scope.$apply;
									}
								});
					}

					// _----------------------------------


					$scope.searchTextH = "";
					$scope.searchTextD = "";
					$scope.searchTextM = "";

					$scope.queryHS = function(query) {
						res = [];
						for (i = 0; i < $scope.hochschulen.length; i++) {
							hName = $scope.hochschulen[i].Name.toLowerCase();

							if (hName.contains(query.toLowerCase())) {
								res[res.length] = $scope.hochschulen[i];
							}

						}
						return res;

					}
					
					$scope.queryD = function(query) {
						res = [];
						for (i = 0; i < $scope.dozenten.length; i++) {
							dName = $scope.dozentenNameObj($scope.dozenten[i]).toLowerCase().trim();
								
							if (dName.contains(query.toLowerCase())) {
								res[res.length] = $scope.dozenten[i];
							}

						}
						return res;

					}
					
					$scope.queryM = function(query) {
						res = [];
						for (i = 0; i < $scope.module.length; i++) {
							mName = $scope.module[1].Name.toLowerCase().trim();
								
							if (mName.contains(query.toLowerCase())) {
								res[res.length] = $scope.module[i];
							}

						}
						return res;

					}
				} ]);
