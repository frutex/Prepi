angular
		.module('ExPrep')
		.controller(
				'DankeCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						function($http, $scope, RequestFactory, AuthService) {

							$scope.startup = function() {
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
															alert(data.data.token);
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
													if (data.data.successfull) {
														$scope.userData = data.data.data;
													} else {
														$scope.userData = data.data;
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

						} ]);
