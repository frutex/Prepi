angular
		.module('ExPrep')
		.controller(
				'AddQuestionCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						'ProgService',
						function($http, $scope, RequestFactory, AuthService, ProgService) {

							$scope.checkAuth = function() {
								token = AuthService.getUserToken();
								if (AuthService.isAuthenticated && token) {

									RequestFactory.checkAuthToken().then(
											function(data) {
												if (data.data.successfull) {

												} else {
													alert(data.data.data);
													AuthService.logout();
													window.location.href = "./login.html";
												}
											});

								} else {
									var loc = window.location.pathname;
									var dir = loc.substring(loc
											.lastIndexOf('/'), loc.length);
									if (dir != "/login.html") {
										if (dir != "/") {
											window.location.href = "./login.html";
										}
									}
								}
							}
							
							$scope.today = new Date();
							$scope.hochschulen = [];
							$scope.dozenten;
							$scope.module;

							$scope.startup = function() {
								ProgService.state(true);
								$scope.checkAuth();
								$scope.getHochschulen();
								$scope.getModule();
								$scope.getDozenten();
							}

							$scope.selected = {
								"Titel" : "",
								"Hochschule" : "",
								"Modul" : "",
								"Dozent" : "",
								"Beschreibung" : "",
								"Datum" : $scope.today.getFullYear()
							}

							$scope.setDate = function() {
								$scope.selected.Datum = $scope.today
										.getFullYear();
							}

							$scope.checkAll = function() {
								if ($scope.selected.Titel != ""
										&& $scope.selected.Hochschule != ""
										&& $scope.selected.Modul != ""
										&& $scope.selected.Dozent != ""
										&& $scope.selected.Beschreibung != "") {
									$scope.doSymbolCheck();
									return true;
								} else {
									alert("Leider wurden nicht alle Felder angemessen ausgefüllt, bitte überprüfe deine Angaben.")
									return false;
								}
							}

							$scope.doSymbolCheck = function() {
								$scope.selected.Beschreibung.replace("&",
										" und ").replace("?", ".");
							}

							$scope.getHochschulen = function() {
								RequestFactory
										.getHochschulen()
										.then(
												function(data) {
													ProgService.state(false);
													if (data.data.successfull) {
														$scope.hochschulen = data.data.data;
														
													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

							$scope.addFrage = function() {
								ProgService.state(true);
								if ($scope.checkAll()) {
									RequestFactory
											.addFrage($scope.selected)
											.then(
													function(data) {
														ProgService.state(false);
														if (data.data.successfull) {
															window.location.href = "./danke.html";
														} else {
															alert(data.data.data);
															// $scope.$apply;
														}
														
													});
								} else {
									ProgService.state(false);
								}
							}

							$scope.getDozenten = function() {
								RequestFactory
										.getDozenten()
										.then(
												function(data) {
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
								ProgService.state(true);
								if ($scope.selected.Dozent) {
									RequestFactory
											.getModulForDozent(
													$scope.selected.Dozent)
											.then(
													function(data) {
														ProgService.state(false);
														if (data.data.successfull) {
															$scope.module = data.data.data;
															
														} else {
															alert(data.data.data);
															// $scope.$apply;
														}
													});
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
								ProgService.state(true);
								RequestFactory
										.getDozentenForHochschule(
												$scope.selected.Hochschule.Name)
										.then(
												function(data) {
													ProgService.state(false);
													if (data.data.successfull) {
														$scope.dozenten = data.data.data;
														
													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

							// Querys

							$scope.searchTextH = "";
							$scope.searchTextD = "";
							$scope.searchTextM = "";

							$scope.queryHS = function(query) {
								res = [];
								for (i = 0; i < $scope.hochschulen.length; i++) {
									hName = $scope.hochschulen[i].Name
											.toLowerCase();

									if (hName.contains(query.toLowerCase())) {
										res[res.length] = $scope.hochschulen[i];
									}

								}
								return res;

							}

							$scope.queryD = function(query) {
								res = [];
								for (i = 0; i < $scope.dozenten.length; i++) {
									dName = $scope.dozenten[i].Vollname
											.toLowerCase().trim();

									if (dName.contains(query.toLowerCase())) {
										res[res.length] = $scope.dozenten[i];
									}

								}
								return res;

							}

							$scope.queryM = function(query) {
								res = [];
								for (i = 0; i < $scope.module.length; i++) {
									mName = $scope.module[i].Name.toLowerCase()
											.trim();

									if (mName.contains(query.toLowerCase())) {
										res[res.length] = $scope.module[i];
									}

								}
								return res;

							}
						} ]);
