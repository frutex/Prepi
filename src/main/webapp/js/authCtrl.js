angular
		.module('ExPrep')
		.controller(
				'AuthCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						function($http, $scope, RequestFactory, AuthService) {

							$scope.authenticated = false;
							$scope.checkAuth = function() {
								token = AuthService.getUserToken();
								if (token) {
									$scope.authenticated = true;

									RequestFactory
											.checkAuthToken()
											.then(
													function(data) {
														if (data.data.successfull) {
															var loc = window.location.pathname;

															if (loc
																	.contains("login")) {
																window.location.href = "./dashboard.html";
															}
															if (loc == "/prepa/") {
																window.location.href = "./dashboard.html";
															}
														} else {
															$scope.authenticated = false;
															AuthService.logout();
															alert(data.data.data);
															var loc = window.location.pathname;

															if (!loc.contains("login") && loc != "/prepa/") {
																window.location.href = "./login.html";
															}
														}
													});

								} else {
									$scope.authenticated = false;
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

							$scope.loginData = {
								username : "",
								password : ""
							}

							$scope.login = function() {
								RequestFactory
										.login($scope.loginData.username,
												$scope.loginData.password)
										.then(
												function(data) {
													if (data.data.successfull) {
														AuthService
																.login(data.data);
														$scope.checkAuth();
														window.location.href = "./dashboard.html";
													} else {
														alert(data.data.data);
													}
												})
							}

							$scope.logout = function() {
								AuthService.logout();
								$scope.authenticated = false;
								// $scope.$apply();
							}

							$scope.showDiv = "dashboard";
							$scope.isShown = function(div) {
								if (div == $scope.showDiv) {
									return true;
								} else {
									return false;
								}
							}

						} ]);
