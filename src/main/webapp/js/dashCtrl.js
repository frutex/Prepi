angular
		.module('ExPrep')
		.controller(
				'DashCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						'$mdDialog',
						function($http, $scope, RequestFactory, AuthService, $mdDialog) {

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
														if($scope.userData[0].Hochschule == "null"){
															$scope.showHSDialog();
														}
													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

							$scope.doQuestionLike = function(id) {
								RequestFactory.doQuestionLike(id).then(
										function(data) {
											if (data.data.successfull) {
												$scope.loadUserData();
											} else {
												alert(data.data.data);

											}
										});
							}
							
							$scope.showHSDialog = function() {
							    $mdDialog.show({
							      contentElement: '#hsDialog',
							      parent: angular.element(document.body),
							      clickOutsideToClose: true
							    });
							  };

						} ]);
