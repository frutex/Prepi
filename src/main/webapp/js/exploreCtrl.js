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
							$scope.fragen;

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

							$scope.getParam = function() {
								var loc = window.location.search;
								var keywords = loc.substring(loc
										.lastIndexOf("=") + 1, loc.length);
								if (keywords == "") {
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
								} else {

									$scope.keywords = keywords;
									// doSearch()
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
														$scope.fragen[idx].Likes = $scope.fragen[idx].Likes +1;
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
