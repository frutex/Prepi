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
								$scope.getHochschulen();
								$scope.getDozenten();
								$scope.getModule();
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

							$scope.selected = {
								"Hochschule" : "",
								"Dozent" : "",
								"Modul" : "",
							}
							$scope.hochschulen = "";
							$scope.module = "";
							$scope.dozenten = "";
							$scope.keywords = "";
							$scope.fragen;

							$scope.doSearch = function() {
								ProgService.state(true);
								RequestFactory.doSearch(
										$scope.selected.Modul.Name,
										$scope.selected.Hochschule.Name,
										$scope.selected.Dozent.Vollname,
										$scope.keywords).then(function(data) {
									ProgService.state(false);
									if (data.data.successfull) {
										$scope.fragen = data.data.data;
										$scope.questionRb();
									} else {
										alert(data.data.data);

									}
								});
							}

							$scope.questionRb = function() {
								q = [];

								tmp = [];
								total = 0;
								i = 0;
								z = 0;
								for (b = 0; b < $scope.fragen.length; b++) {
									total++;
									tmp.push($scope.fragen[z]);

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
								$scope.fragen = q;
							}

							$scope.getParam = function() {
								ProgService.state(true);
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
														ProgService.state(false);
														if (data.data.successfull) {
															$scope.fragen = data.data.data;
															$scope.questionRb();
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
														$scope
																.addLike(data.data.data.FragenID);
													} else {
														alert(data.data.data);

													}
												});
							}

							$scope.addLike = function(id) {
								for (i = 0; i < $scope.fragen.length; i++) {
									var collection = $scope.fragen[i];

									for (z = 0; z < collection.length; z++) {
										if (collection[z].FragenID == id) {
											collection[z].isLikeable = false;
											collection[z].Likes = parseInt($scope.fragen[idx].Likes) + 1;
										}
									}
								}
								return -1;
							}

							$scope.getHochschulen = function() {
								RequestFactory
										.getHochschulen()
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.hochschulen = data.data.data;

													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

							$scope.getDozenten = function() {
								RequestFactory
										.getDozenten()
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.dozenten = data.data.data;
													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

							$scope.getModule = function() {
								RequestFactory.getModule().then(function(data) {
									if (data.data.successfull) {
										$scope.module = data.data.data;
									} else {
										alert(data.data.data);
										// $scope.$apply;
									}
								});
							}

							$scope.loadHSDozenten = function() {
								RequestFactory
										.getDozentenForHochschule(
												$scope.selected.Hochschule.Name)
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.dozenten = data.data.data;

													} else {
														alert(data.data.data);
														// $scope.$apply;
													}
												});
							}

							$scope.loadDModule = function() {
								if ($scope.selected.Dozent) {
									RequestFactory
											.getModulForDozent(
													$scope.selected.Dozent)
											.then(
													function(data) {
														if (data.data.successfull) {
															$scope.module = data.data.data;

														} else {
															alert(data.data.data);
															// $scope.$apply;
														}
													});
								}
							}

							// Querys

							$scope.searchTextH = "";
							$scope.searchTextD = "";
							$scope.searchTextM = "";

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

							$scope.queryD = function(query) {
								res = [];
								for (i = 0; i < $scope.dozenten.length; i++) {
									dName = $scope.dozenten[i].Vollname
											.toLowerCase().trim();

									if (dName.contains(query.toLowerCase())) {
										res[res.length] = $scope.dozenten[i];
									}

								}
								return res;

							}

							$scope.queryM = function(query) {
								res = [];
								for (i = 0; i < $scope.module.length; i++) {
									mName = $scope.module[i].Name.toLowerCase()
											.trim();

									if (mName.contains(query.toLowerCase())) {
										res[res.length] = $scope.module[i];
									}

								}
								return res;

							}

						} ]);
