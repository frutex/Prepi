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
						function($http, $scope, RequestFactory, AuthService,
								$mdDialog) {

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
													if (data.data.successfull) {
														$scope.userData = data.data.data;
														if ($scope.userData[0].Hochschule == "null") {
															$scope.loadHS();
														}
													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
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

							$scope.selectedHS;
							$scope.searchTextH = "";

							$scope.showHSDialog = function() {
								$mdDialog.show({
									contentElement : '#hsDialog',
									parent : angular.element(document.body),
									clickOutsideToClose : true
								});
							};

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
							
							$scope.confirmHS = function(){
								RequestFactory.addHStoUser($scope.selectedHS.Name).then(
										function(data) {
											if (data.data.successfull) {
												$scope.loadUserData();
												$mdDialog.close({
													contentElement : '#hsDialog',
													parent : angular.element(document.body),
													clickOutsideToClose : true
												});
											} else {
												alert(data.data.data);

											}
										});
							}

						} ]);
