angular
		.module('ExPrep')
		.controller(
				'ExploreCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						'ProgService',
						function($http, $scope, RequestFactory, AuthService,
								ProgService) {

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

							$scope.hochschule = "";
							$scope.modul = "";
							$scope.dozent = "";
							$scope.keywords = "";
							$scope.fragen;

							$scope.doSearch = function() {
								ProgService.state(true);
								RequestFactory.doSearch($scope.hochschule,
										$scope.modul, $scope.dozent,
										$scope.keywords).then(function(data) {
									ProgService.state(false);
									if (data.data.successfull) {
										$scope.fragen = data.data.data;
										alert("YAY!");
									} else {
										alert(data.data.data);

									}
								});
							}

							$scope.getParam = function() {

								var loc = window.location.search;
								var keywords = "";
								keywords = loc.substring(
										loc.lastIndexOf("=") + 1, loc.length);
								if (keywords != ""
										&& loc.substring(0,
												loc.lastIndexOf("=") + 1)
												.contains("keyword")) {
									$scope.keywords = keywords;
									$scope.doSearch();
								} else {
									RequestFactory
											.getAllQuestions()
											.then(
													function(data) {
														if (data.data.successfull) {
															$scope.fragen = data.data.data;
														} else {
															alert(data.data.data);

														}
													});
								}

							}

							$scope.doQuestionLike = function(id) {
								RequestFactory
										.doQuestionLike(id)
										.then(
												function(data) {
													if (data.data.successfull) {
														idx = $scope
																.getIndex(data.data.data.FragenID);
														$scope.fragen[idx].isLikeable = false;
														$scope.fragen[idx].Likes = parseInt($scope.fragen[idx].Likes) + 1;
													} else {
														alert(data.data.data);

													}
												});
							}

							$scope.getIndex = function(id) {
								for (i = 0; i < $scope.fragen.length; i++) {
									if ($scope.fragen[i].FragenID == id) {
										return i;
									}
								}
								return -1;
							}

						} ]);
