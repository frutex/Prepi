angular
		.module('ExPrep')
		.controller(
				'ProfileCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						'$mdDialog',
						'ProgService',
						function($http, $scope, RequestFactory, AuthService,
								$mdDialog, ProgService) {

							$scope.startup = function() {
								$scope.checkAuth();
								$scope.loadHS();
								$scope.loadUserData();
							}

							$scope.checkAuth = function() {
								token = AuthService.getUserToken();
								if (AuthService.isAuthenticated && token) {

									RequestFactory
											.checkAuthToken()
											.then(
													function(data) {
														if (data.data.successfull) {

														} else {
															alert(data.data.data);
															AuthService
																	.logout();
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

							$scope.hochschulen
							$scope.loadHS = function() {
								RequestFactory
										.getHochschulen()
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.hochschulen = data.data.data;
														$scope.showHSDialog();
													} else {
														alert(data.data.data);
													}
												});

							}

							$scope.userData;
							$scope.loadUserData = function() {
								ProgService.state(true);
								RequestFactory
										.loadUserData()
										.then(
												function(data) {
													ProgService.state(false);
													if (data.data.successfull) {
														$scope.userData = data.data.data;
													} else {
														alert(data.data.data);
													}
												});
							}

							$scope.editable = false;

							$scope.data = {

								"Name" : "",
								"Vorname" : "",
								"Email" : "",
								"EmailWied" : "",
								"Pass" : "",
								"PassWied" : "",
								"Hochschule" : ""
							}

							$scope.toggleEdit = function() {
								$scope.editable = !$scope.editable;
								if ($scope.editable) {
									$scope.data.Name = $scope.userData[0].Name;
									$scope.data.Vorname = $scope.userData[0].Vorname;
									$scope.data.Email = $scope.userData[0].Email;
									$scope.data.EmailWied = $scope.userData[0].Email;
									$scope.data.Hochschule = $scope.userData[0].Hochschule;
								}
							}

							$scope.searchTextH;
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

							$scope.checkmail = function() {
								if ($scope.data.Email.trim() == $scope.data.EmailWied
										.trim()) {
									return true;
								} else {
									return false;
								}
							}

							$scope.checkpass = function() {
								if ($scope.data.Pass.trim() == $scope.data.PassWied
										.trim()) {
									return true;
								} else {
									return false;
								}
							}
							
							$scope.checkModification = function() {
								modified = false;
								if ($scope.data.Vorname != $scope.userData[0].Vorname) {
									modified = true;
								}
								if ($scope.data.Name != $scope.userData[0].Name) {
									modified = true;
								}
								if ($scope.data.Email != $scope.userData[0].Email) {
									modified = true;
								}
								if ($scope.data.Hochschule != $scope.userData[0].Hochschule) {
									modified = true;
								}

								return modified;
							}

							$scope.submit = function() {
								ProgService.state(true);
								if ($scope.checkModification) {
									if (!$scope.checkmail()) {
										alert("Fehler! Die angegebenen Emailadressen stimmen nicht überein.")
									} else {
										if (!$scope.checkpass()) {
											alert("Fehler! Die angegebenen Passwörter stimmen nicht überein.")
										} else {
											// Email und Passwörter stimmen
											// überein
											RequestFactory
													.modifyAccount($scope.data)
													.then(
															function(data) {
																ProgService.state(false);
																if (data.data.successfull) {
																	$scope.loadUserData();
																	alert("Änderungen erfolgreich!")
																	$scope.toggleEdit();
																	
																} else {
																	alert(data.data.data);
																}
															});

										}
									}
								}
							}
						} ]);
