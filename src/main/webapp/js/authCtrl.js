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
								if (AuthService.isAuthenticated && token) {
									$scope.authenticated = true;

									RequestFactory
											.checkAuthToken()
											.success(
													function(data) {
														if (data.successfull) {

														} else {
															$scope.authenticated = false;
															alert(data.token);
															// $scope.$apply;
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
										.success(
												function(data) {
													if (data.successfull) {
														AuthService.login(data);
														$scope.checkAuth();
														window.location.href = "./dashboard.html";
													} else {
														alert(data.token);
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
