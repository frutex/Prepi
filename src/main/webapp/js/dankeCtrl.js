angular
		.module('ExPrep')
		.controller(
				'DankeCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						'ProgService',
						function($http, $scope, RequestFactory, AuthService, ProgService) {

							$scope.startup = function() {
								ProgService.state(true);
								$scope.checkAuth();
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

							$scope.userData;
							$scope.loadUserData = function() {
								RequestFactory
										.loadUserData()
										.then(
												function(data) {
													ProgService.state(false);
													if (data.data.successfull) {
														$scope.userData = data.data.data;
													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

						} ]);
