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
						'ProgService',
						function($http, $scope, RequestFactory, AuthService,
								$mdDialog, ProgService) {

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

							$scope.userData = [ "", "", {
								"Progress" : 0
							} ];
							$scope.loadUserData = function() {
								ProgService.state(true);
								RequestFactory
										.loadUserData()
										.then(
												function(data) {
													ProgService.state(false);
													if (data.data.successfull) {
														$scope.userData = data.data.data;
														if ($scope.userData[0].Hochschule == "null") {
															$scope.loadHS();
														}
														$scope.questionRb();
													} else {
														alert(data.data.data);
													}
												});
							}

							$scope.totalQuestions = 0;
							$scope.questionRb = function() {
								q = [];

								tmp = [];
								total = 0;
								i = 0;
								z = 0;
								for (b = 0; b < $scope.userData[1].length; b++) {
									total ++;
									tmp.push($scope.userData[1][z]);

									if (i == 2) {
										q.push(tmp);
										i = 0;
										tmp = [];
									} else {
										i++;
									}

									z++;
								}
								q.push(tmp);

								$scope.userData[1] = q;
								$scope.totalQuestions = total;
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

							$scope.doQuestionLike = function(id) {
								ProgService.state(true);
								RequestFactory.doQuestionLike(id).then(
										function(data) {
											ProgService.state(false);
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

							$scope.confirmHS = function() {
								RequestFactory
										.addHStoUser($scope.selectedHS.Name)
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.userData[0] = data.data.data;
														$mdDialog.hide();
													} else {
														alert(data.data.data);

													}
												});
							}

						} ]);
