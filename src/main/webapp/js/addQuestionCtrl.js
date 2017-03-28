angular.module('ExPrep').controller(
		'AddQuestionCtrl',
		[
				'$http',
				'$scope',
				'RequestFactory',
				'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

					$scope.startup = function() {
						$scope.checkAuth();
						$scope.getHochschulen();
						$scope.getModule();
						$scope.getDozenten();

					}

					$scope.selected = {
							"Titel": "Bitte Fragentitel Eingeben",					
							"Hochschule": "Bitte Hochschule auswählen",
							"Modul":"Bitte Modul auswählen",
							"Dozent":"Bitte Dozenten auswählen",
							"Beschreibung":"Bitte Beschreibung einfügen",
								"Datum": "Datum"
					}
					
					$scope.hochschulen;
					$scope.dozenten;
					$scope.module;

					$scope.getHochschulen = function() {
						RequestFactory.getHochschulen().success(function(data) {
							if (data.successfull) {
								$scope.hochschulen = data.data;
							} else {
								alert(data.data);
								// $scope.$apply;
							}
						});
					}
					
					$scope.addFrage = function(){
						RequestFactory.addFrage($scope.selected).success(function(data) {
							if (data) {
								window.location.href = "./danke.html";
							} else {
								alert("Error");
								// $scope.$apply;
							}
						});
					}
					
					$scope.getDozenten = function() {
						RequestFactory.getDozenten().success(function(data) {
							if (data.successfull) {
								$scope.dozenten = data.data;
							} else {
								alert(data.data);
								// $scope.$apply;
							}
						});
					}
					
					$scope.getModule = function() {
						RequestFactory.getModule().success(function(data) {
							if (data.successfull) {
								$scope.module = data.data;
							} else {
								alert(data.data);
								// $scope.$apply;
							}
						});
					}

					$scope.checkAuth = function() {
						token = AuthService.getUserToken();
						if (AuthService.isAuthenticated && token) {

							RequestFactory.checkAuthToken().success(
									function(data) {
										if (data.successfull) {

										} else {
											alert(data.token);
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
					
					$scope.dozentenName = function(index){
						return $scope.dozenten[index].Nachname + ", " + $scope.dozenten[index].Vorname;
					}
					

				} ]);
