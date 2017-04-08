angular
		.module('ExPrep')
		.controller(
				'ExploreCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						function($http, $scope, RequestFactory, AuthService) {

							$scope.startup = function() {
								$scope.checkAuth();
								$scope.getParam();
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

							
							$scope.hochschule = "";
							$scope.modul = "";
							$scope.dozent = "";
							$scope.keywords = "";
							
							$scope.doSearch = function() {
								RequestFactory.doSearch(null, null, null,
										$scope.keywords).then(function(data) {
									if (data.data.successfull) {
										alert("YAY!")
									} else {
										alert(data.data.data);

									}
								});
							}
							
							$scope.getParam = function(){
								var keywords = loc.substring(
										loc.lastIndexOf("=") + 1, loc.length);
								$scope.keywords = keywords;
								// doSearch()
							}

						} ]);
