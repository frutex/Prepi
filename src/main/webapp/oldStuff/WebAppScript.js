var EasyCMApp = angular.module("EasyCMApp", [ 'ui.bootstrap', 'chart.js' ]);

EasyCMApp.factory('EasyCMScriptService', [
		'$http',
		'$filter',
		function($http, $filter) {
			return {

				addM : function(newUserCreds) {
					return $http.post(
							"/EasyCM/easyCM?c=addM&name=" + newUserCreds.Name
									+ "&vorname=" + newUserCreds.Vorname
									+ "&adresse=" + newUserCreds.Adresse
									+ "&telnr=" + newUserCreds.TelNr).success(
							function(data) {
								return data;
							})
				},
				addK : function(newUserCreds) {
					return $http.post(
							"/EasyCM/easyCM?c=addK&name=" + newUserCreds.Name
									+ "&vorname=" + newUserCreds.Vorname
									+ "&adresse=" + newUserCreds.Adresse
									+ "&telnr=" + newUserCreds.TelNr
									+ "&unternehmen="
									+ newUserCreds.Unternehmen).success(
							function(data) {
								return data;
							})
				},
				addV : function(newContractCreds) {
					return $http.post(
							"/EasyCM/easyCM?c=addV&name="
									+ newContractCreds.Name + "&besch="
									+ newContractCreds.Beschreibung + "&kid="
									+ newContractCreds.KundenID + "&mid="
									+ newContractCreds.MitarbeiterID
									+ "&vdatum=" + newContractCreds.StartD
									+ "&vablauf=" + newContractCreds.EndD
									+ "&wert=" + newContractCreds.Wert
									+ "&branche=" + newContractCreds.Branche)
							.success(function(data) {
								return data;
							})
				},
				searchM : function(tosearch) {
					return $http.post(
							"/EasyCM/easyCM?c=searchM&toSearch=" + tosearch)
							.success(function(data) {
								return data;
							})
				},
				searchK : function(tosearch) {
					return $http.post(
							"/EasyCM/easyCM?c=searchK&toSearch=" + tosearch)
							.success(function(data) {
								return data;
							})
				},
				searchV : function(tosearch) {
					return $http.post(
							"/EasyCM/easyCM?c=searchV&toSearch=" + tosearch)
							.success(function(data) {
								return data;
							})
				},
				getAllKunden : function() {
					return $http.post("/EasyCM/easyCM?c=getAllK").success(
							function(data) {
								return data;
							})
				},
				getAllM : function(tosearch) {
					return $http.post("/EasyCM/easyCM?c=getAllM").success(
							function(data) {
								return data;
							})
				},
				getAllV : function() {
					return $http.post("/EasyCM/easyCM?c=getAllV").success(
							function(data) {
								return data;
							})
				},
				deleteK : function(id) {
					return $http.post("/EasyCM/easyCM?c=deleteK&id=" + id)
							.success(function(data) {
								return data;
							})
				},
				deleteV : function(id) {
					return $http.post("/EasyCM/easyCM?c=deleteV&id=" + id)
							.success(function(data) {
								return data;
							})
				},
				deleteM : function(id) {
					return $http.post("/EasyCM/easyCM?c=deleteM&id=" + id)
							.success(function(data) {
								return data;
							})
				},

				updateK : function(updatedUserCreds) {
					return $http.post(
							"/EasyCM/easyCM?c=updateK&name="
									+ updatedUserCreds.Name + "&vorname="
									+ updatedUserCreds.Vorname + "&adresse="
									+ updatedUserCreds.Adresse + "&telnr="
									+ updatedUserCreds.TelNr + "&unternehmen="
									+ updatedUserCreds.Unternehmen + "&id="
									+ updatedUserCreds.ID).success(
							function(data) {
								return data;
							})
				},

				updateM : function(updatedUserCreds) {
					return $http.post(
							"/EasyCM/easyCM?c=updateM&name="
									+ updatedUserCreds.Name + "&vorname="
									+ updatedUserCreds.Vorname + "&adresse="
									+ updatedUserCreds.Adresse + "&telnr="
									+ updatedUserCreds.TelNr + "&id="
									+ updatedUserCreds.ID).success(
							function(data) {
								return data;
							})
				},
				
				updateV : function(updatedContractCreds) {
					return $http.post(
							"/EasyCM/easyCM?c=updateV&name="
									+ updatedContractCreds.Name + "&mid="
									+ updatedContractCreds.MitarbeiterID + "&kid="
									+ updatedContractCreds.KundeID + "&datum="
									+ updatedContractCreds.Vertragsdatum + "&ablauf="
									+ updatedContractCreds.Ablaufdatum + "&beschreibung="
									+ updatedContractCreds.Beschreibung + "&id="
									+ updatedContractCreds.ID).success(
							function(data) {
								return data;
							})
				},

			};
		} ]);
// ///////////////////////////// controller
EasyCMApp
		.controller(
				'EasyCMScriptCtrl',
				[
						'$http',
						'$scope',
						'EasyCMScriptService',
						function($http, $scope, EasyCMScriptService) {

							$scope.loadProgressbar = {
								Steps : 0,
								Current : 0,
								Value : 0,
								Shown : false
							};

							$scope.progressbarStart = function(val) {
								$scope.loadProgressbar.Steps = val + 1;
								$scope.loadProgressbar.Current = 1;
								$scope.loadProgressbar.Value = $scope.loadProgressbar.Current
										/ $scope.loadProgressbar.Steps * 100;
								$scope.loadProgressbar.Shown = true;
							};

							$scope.progressbarOFF = function() {
								$scope.loadProgressbar.Shown = false;
							};

							$scope.progressbarNext = function() {
								$scope.loadProgressbar.Current = $scope.loadProgressbar.Current + 1;
								$scope.loadProgressbar.Value = $scope.loadProgressbar.Current
										/ $scope.loadProgressbar.Steps * 100;
								if ($scope.loadProgressbar.Current == $scope.loadProgressbar.Steps) {
									$scope.progressbarOFF();
								}

							};

							$scope.newUserCreds = {
								Name : 'Name',
								Vorname : 'Vorname',
								Adresse : 'Adresse',
								TelNr : 'Telefonnummer',
								Unternehmen : 'Unternehmen'
							};

							$scope.newContractCreds = {
								Name : 'Vertragsname...',
								Beschreibung : 'Beschreibung..',
								Kunde : 'Bitte Wählen..',
								KundenID : '',
								Mitarbeiter : 'Bitte Wählen...',
								MitarbeiterID : '',
								StartD : 'YYYY-MM-DD',
								EndD : 'YYYY-MM-DD',
								Wert : '',
								Branche : 'Bitte Wählen.'
							};

							$scope.BranchenListe = [ Automobilindustrie = {
								Name : 'Automobilindustrie'
							}, Energieversorgung = {
								Name : 'Energieversorgung'
							}, Information = {
								Name : 'Information'
							}, Landwirtschaft = {
								Name : 'Landwirtschaft'
							}, ÖffentlicheVerwaltung = {
								Name : 'Öffentliche Verwaltung'
							}, Verkehr = {
								Name : 'Verkehr'
							}, Wasserversorgung = {
								Name : 'Wasserversorgung'
							} ];

							$scope.activeTabs = [ true, false, false ];

							$scope.setActiveTab = function(ind) {
								$scope.activeTabs[0] = false;
								$scope.activeTabs[1] = false;
								$scope.activeTabs[2] = false;
								$scope.activeTabs[ind] = true;
							}

							$scope.resetNewContractCreds = function() {
								$scope.newContractCreds.Name = 'Vertragsname...';
								$scope.newContractCreds.Beschreibung = 'Beschreibung..';
								$scope.newContractCreds.Kunde = 'Bitte Wählen..';
								$scope.newContractCreds.KundenID = '';
								$scope.newContractCreds.Mitarbeiter = 'Bitte Wählen...';
								$scope.newContractCreds.MitarbeiterID = '';
								$scope.newContractCreds.StartD = 'YYYY-MM-DD';
								$scope.newContractCreds.EndD = 'YYYY-MM-DD';
								$scope.newContractCreds.Wert = '';
							};

							$scope.searchResultCollapser = true;
							$scope.searchResultCollapserKunde = true;
							$scope.successfullyAddedCollapser = true;
							$scope.successfullyAddedContractCollapser = true;

							$scope.searchEntry = {
								Entry : 'Suche...'
							};

							$scope.testfunction = function() {
								var a = 1;
								a++;
								$scope.newUserCreds.NeuName = $scope.newUserCreds.Name
							};

							$scope.searched = {
								Type : '',
								Entry : ''
							};

							$scope.searchResults;
							$scope.searchM = function() {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.searchM($scope.searchEntry.Entry)
										.success(
												function(data) {
													$scope.searchResults = data;
													$scope.searchResultCollapser = false;
													$scope.searchResultCollapserKunde = true;
													$scope.searched.Entry = $scope.searchEntry.Entry;
													$scope.searched.Type = 'Mitarbeiter';
													$scope.progressbarNext();
												})
							};
							$scope.searchMID = function(id) {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.searchM(id)
										.success(
												function(data) {
													$scope.searched.Entry = "mit der ID " + id;
													$scope.searched.Type = 'Mitarbeiter';
													$scope.searchResults = data;
													$scope.searchResultCollapser = false;
													$scope.searchResultCollapserKunde = true;
													$scope.progressbarNext();
												})
							};
							$scope.searchK = function() {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.searchK($scope.searchEntry.Entry)
										.success(
												function(data) {
													$scope.searched.Type = 'Kunden';
													$scope.searched.Entry = $scope.searchEntry.Entry;
													$scope.searchResults = data;
													$scope.searchResultCollapser = true;
													$scope.searchResultCollapserKunde = false;
													$scope.progressbarNext();
												})
							};
							$scope.searchKID = function(id) {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.searchK(id)
										.success(
												function(data) {
													$scope.searched.Type = 'Kunden';
													$scope.searched.Entry = "mit der ID " + id;
													$scope.searchResults = data;
													$scope.searchResultCollapser = true;
													$scope.searchResultCollapserKunde = false;
													$scope.progressbarNext();
												})
							};

							$scope.searchResultContractCollapser = true;
							$scope.searchResultsC;
							$scope.searchV = function() {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.searchV($scope.searchEntry.Entry)
										.success(
												function(data) {
													$scope.searched.Type = 'Vertrag';
													$scope.searched.Entry = $scope.searchEntry.Entry;
													$scope.searchResultsC = data;
													$scope.searchResultContractCollapser = false;
													$scope.progressbarNext();
												})
							};
							$scope.searchVID = function(id) {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.searchV(id)
										.success(
												function(data) {
													$scope.searched.Type = 'Vertrag';
													$scope.searched.Entry = "mit der ID " + id;
													$scope.searchResultsC = data;
													$scope.searchResultContractCollapser = false;
													$scope.progressbarNext();
												})
							};

							$scope.lastAddedType;
							$scope.newUserAddedData;

							$scope.addM = function() {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.addM($scope.newUserCreds)
										.success(
												function(data) {
													$scope.newUserAddedData = data;
													$scope.lastAddedType = "Mitarbeiter";
													$scope.successfullyAddedCollapser = false;
													// Reset newUserCreds
													$scope.newUserCreds.Name = 'Name';
													$scope.newUserCreds.Vorname = 'Vorname';
													$scope.newUserCreds.Adresse = 'Adresse';
													$scope.newUserCreds.TelNr = 'Telefonnummer';
													$scope.progressbarNext();
													$scope.getAllM();
												})
							};
							$scope.addK = function() {
								$scope.progressbarStart(1);
								EasyCMScriptService
										.addK($scope.newUserCreds)
										.success(
												function(data) {
													$scope.newUserAddedData = data;
													$scope.lastAddedType = "Kunde";
													$scope.successfullyAddedCollapser = false;
													// Reset newUserCreds
													$scope.newUserCreds.Name = 'Name';
													$scope.newUserCreds.Vorname = 'Vorname';
													$scope.newUserCreds.Adresse = 'Adresse';
													$scope.newUserCreds.TelNr = 'Telefonnummer';
													$scope.progressbarNext();
													$scope.getAllKunden();
												})
							};

							$scope.allKunden;
							$scope.getAllKunden = function() {
								EasyCMScriptService.getAllKunden().success(
										function(data) {
											$scope.allKunden = data;
										})
							};

							$scope.allMitarbeiter;
							$scope.getAllM = function() {
								EasyCMScriptService.getAllM().success(
										function(data) {
											$scope.allMitarbeiter = data;
										})
							};

							$scope.allVertrag;
							$scope.getAllV = function() {
								EasyCMScriptService.getAllV().success(
										function(data) {
											$scope.allVertrag = data;
										})
							};

							$scope.getAllData = function() {
								$scope.progressbarStart(3);
								EasyCMScriptService.getAllV().success(
										function(data) {
											$scope.allVertrag = data;
											$scope.progressbarNext();
											$scope.createChartDataContract(-1);
										});
								EasyCMScriptService.getAllKunden().success(
										function(data) {
											$scope.allKunden = data;
											$scope.progressbarNext();
										});
								EasyCMScriptService.getAllM().success(
										function(data) {
											$scope.allMitarbeiter = data;
											$scope.progressbarNext();
										});
							};

							$scope.newContractID;
							$scope.newContract = function() {
								$scope.progressbarStart(1)
								EasyCMScriptService
										.addV($scope.newContractCreds)
										.success(
												function(data) {
													$scope
															.resetNewContractCreds();
													$scope.newContractID = data.ID;
													$scope.successfullyAddedContractCollapser = false;
													$scope.progressbarNext();
													$scope.getAllData();
												})
							};

							$scope.setContractKunde = function(index) {
								$scope.newContractCreds.Kunde = $scope.allKunden[index].Vorname
										+ ", " + $scope.allKunden[index].Name;
								$scope.newContractCreds.KundenID = $scope.allKunden[index].ID
							};

							$scope.setContractMitarbeiter = function(index) {
								$scope.newContractCreds.Mitarbeiter = $scope.allMitarbeiter[index].Vorname
										+ ", "
										+ $scope.allMitarbeiter[index].Name;
								$scope.newContractCreds.MitarbeiterID = $scope.allMitarbeiter[index].ID
							};

							$scope.getBranchenIndex = function(branche) {
								for (z = 0; z < $scope.BranchenListe.length; z++) {
									if ($scope.BranchenListe[z].Name == branche) {
										return z;
									}
								}
								return -1;
							};

							$scope.chartDataContract = {
								Names : [],
								Data : [],
								Depth : ""

							};

							$scope.resetChartDataContract = function() {
								$scope.chartDataContract.Names = [];
								$scope.chartDataContract.Data = [];
							};

							$scope.subcategory = false;
							$scope.createChartDataContract = function(index) {
								$scope.progressbarStart(1);
								$scope.indx = index;

								if ($scope.indx == -1) {
									$scope.chartDataContract.Depth = "";
									$scope.subcategory = false;
									$scope.resetChartDataContract();
									for (z = 0; z < $scope.BranchenListe.length; z++) {
										$scope.chartDataContract.Names[z] = $scope.BranchenListe[z].Name;
										$scope.chartDataContract.Data[z] = 0;
									}
									for (i = 0; i < $scope.allVertrag.length; i++) {
										tmp = $scope
												.getBranchenIndex($scope.allVertrag[i].Branche);
										if (tmp != -1) {
											$scope.chartDataContract.Data[tmp] = $scope.chartDataContract.Data[tmp]
													+ parseInt($scope.allVertrag[i].Wert);
										}
									}
									$scope.progressbarNext();
								} else {
									if (!$scope.subcategory) {
										$scope.subcategory = true;
										$scope.branche = $scope.chartDataContract.Names[$scope.indx];
										$scope.chartDataContract.Depth = $scope.branche;
										$scope.resetChartDataContract();
										for (i = 0; i < $scope.allVertrag.length; i++) {
											if ($scope.allVertrag[i].Branche == $scope.branche) {
												$scope.chartDataContract.Names[$scope.chartDataContract.Names.length] = $scope.allVertrag[i].Name;
												$scope.chartDataContract.Data[$scope.chartDataContract.Data.length] = $scope.allVertrag[i].Wert;

											}
										}
										$scope.progressbarNext();
									} else {
										$scope.name = $scope.chartDataContract.Names[$scope.indx];
										for (i = 0; i < $scope.allVertrag.length; i++) {
											var ix = i;
											if ($scope.allVertrag[ix].Name == $scope.name) {
												$scope
														.searchVID($scope.allVertrag[ix].ID);
											}
										}
									}
								}

							};

							$scope.showContractDepth = function() {
								if ($scope.chartDataContract.Depth != "") {
									return true;
								}
								return false;
							};

							$scope.getContractIndex = function(label) {
								for (i = 0; i < $scope.chartDataContract.Names.length; i++) {
									if ($scope.chartDataContract.Names[i] == label) {
										return i;
									}
								}
								return -1;
							};

							$scope.contractClick = function(points, evt) {
								var label = points[0].label;
								var index = $scope.getContractIndex(label);
								if (index != -1) {
									$scope.createChartDataContract(index);
								} else {
									alert("Internal Error. Could not Parse Contract index for Label: "
											+ label
											+ ". Please contact your Administrator for further help.")

								}
							};

							$scope.chartDataMitarbeiter = {
								btnField : 'Eingrenzen auf...',
								ID : [],
								Data : [ [] ],
								Names : [],
								Depth : ""
							};

							$scope.resetChartDataMitarbeiter = function() {
								$scope.chartDataMitarbeiter.ID = [];
								$scope.chartDataMitarbeiter.Names = [];
								$scope.chartDataMitarbeiter.Data = [ [] ];
							};

							$scope.subMitarbeiter = false;
							$scope.createChartDataMitarbeiter = function(index) {
								$scope.progressbarStart(1);
								$scope.index = index;

								if ($scope.index == -1) {
									$scope.chartDataMitarbeiter.Depth = "";
									$scope.subMitarbeiter = false;
									$scope.chartDataMitarbeiter.btnField = 'Eingrenzen auf...'
									$scope.resetChartDataMitarbeiter();
									for (z = 0; z < $scope.allMitarbeiter.length; z++) {
										$scope.chartDataMitarbeiter.ID[z] = $scope.allMitarbeiter[z].ID;
										$scope.chartDataMitarbeiter.Names[z] = $scope.allMitarbeiter[z].Name
												+ " , "
												+ $scope.allMitarbeiter[z].Vorname
										$scope.chartDataMitarbeiter.Data[0][z] = 0;
									}
									for (i = 0; i < $scope.allVertrag.length; i++) {
										tmp = $scope.allVertrag[i].MitarbeiterID;
										$scope.chartDataMitarbeiter.Data[0][parseInt(tmp) - 1] = $scope.chartDataMitarbeiter.Data[0][parseInt(tmp) - 1]
												+ parseInt($scope.allVertrag[i].Wert);
									}
									$scope.progressbarNext();
								} else {
									if (!$scope.subMitarbeiter) {
										$scope.subMitarbeiter = true;
										$scope.chartDataMitarbeiter.btnField = 'Vertrag suchen ...'
										$scope.ID = $scope.chartDataMitarbeiter.ID[$scope.index];
										$scope.chartDataMitarbeiter.Depth = $scope
												.getMName($scope.ID);
										$scope.resetChartDataMitarbeiter();
										for (i = 0; i < $scope.allVertrag.length; i++) {
											if ($scope.allVertrag[i].MitarbeiterID == $scope.ID) {
												$scope.chartDataMitarbeiter.Names[$scope.chartDataMitarbeiter.Names.length] = $scope.allVertrag[i].Name;
												$scope.chartDataMitarbeiter.Data[0][$scope.chartDataMitarbeiter.Data[0].length] = $scope.allVertrag[i].Wert;
												$scope.chartDataMitarbeiter.ID[$scope.chartDataMitarbeiter.ID.length] = $scope.allVertrag[i].ID;
											}
										}
										$scope.progressbarNext();
									} else {
										$scope
												.searchVID($scope.chartDataMitarbeiter.ID[$scope.index]);
									}
								}

							};

							$scope.showMitarbeiterDepth = function() {
								if ($scope.chartDataMitarbeiter.Depth != "") {
									return true;
								}
								return false;
							};
							
							$scope.getMitarbeiterIndex = function(name) {
								for (i = 0; i < $scope.chartDataMitarbeiter.Names.length; i++) {
									if ($scope.chartDataMitarbeiter.Names[i] == name) {
										return i;
									}
								}
								return -1;
							};

							$scope.mitarbeiterClick = function(points, evt) {
								var label = points[0].label;
								var index = $scope.getMitarbeiterIndex(label);
								if (index != -1) {
									$scope.createChartDataMitarbeiter(index);
								} else {
									alert("Internal Error. Could not Parse Employee index for Label: "
											+ label
											+ ". Please contact your Administrator for further help.")

								}
							};
							

							$scope.chartDataKunden = {
								btnField : 'Eingrenzen auf...',
								Angezeigt : 'Kunden, Unternehmen:',
								ID : [],
								Data : [ [] ],
								Names : [],
								Depth : {
									Firma : {
										Name : "",
										Index : -1
									},
									Mitarbeiter : {
										Name : "",
										Index : -1
									}
								},
								ShowMitarbeiter : true,
								Last : {
									index : -1,
									subKunden : 0
								}
							};

							$scope.showChartKDepthFirma = function() {
								if ($scope.chartDataKunden.Depth.Firma.Index != -1) {
									return true;
								}
								return false;
							};

							$scope.showChartKDepthMitarbeiter = function() {
								if ($scope.chartDataKunden.Depth.Mitarbeiter.Index != -1) {
									return true;
								}
								return false;
							};

							$scope.resetChartDataKunden = function() {
								$scope.chartDataKunden.ID = [];
								$scope.chartDataKunden.Names = [];
								$scope.chartDataKunden.Data = [ [] ];
							};

							$scope.subKunden = 0;
							$scope.createChartDataKunden = function(index) {
								$scope.chartDataKunden.Last.index = index;
								$scope.chartDataKunden.Last.subKunden = $scope.subKunden;
								$scope.progressbarStart(1);
								$scope.index = index;

								// Grundform
								if ($scope.index == -1) {
									$scope.subKunden = 0;
									$scope.chartDataKunden.Depth = {
										Firma : {
											Name : "",
											Index : -1
										},
										Mitarbeiter : {
											Name : "",
											Index : -1
										}
									};
									$scope.chartDataKunden.btnField = 'Eingrenzen auf...';
									$scope.chartDataKunden.Angezeigt = 'Kunden, Unternehmen:';
									$scope.resetChartDataKunden();
									// Unternehmen füllen
									for (z = 0; z < $scope.allKunden.length; z++) {
										cont = false;
										for (y = 0; y < $scope.chartDataKunden.Names.length; y++) {
											if ($scope.chartDataKunden.Names[y] == $scope.allKunden[z].Unternehmen) {
												cont = true;
												y = $scope.chartDataKunden.Names.length;
											}
										}
										if (!cont) {
											$scope.chartDataKunden.Names[$scope.chartDataKunden.Names.length] = $scope.allKunden[z].Unternehmen;
											$scope.chartDataKunden.Data[0][$scope.chartDataKunden.Data[0].length] = 0;

										}
									}
									// Vertragswerte eintragen
									for (a = 0; a < $scope.allVertrag.length; a++) {
										$scope.kID = $scope.allVertrag[a].KundeID;
										unt = "";
										for (b = 0; b < $scope.allKunden.length; b++) {
											if ($scope.allKunden[b].ID == $scope.kID) {
												unt = $scope.allKunden[b].Unternehmen;
												b = $scope.allKunden.length;
											}
										}
										if (unt != "") {
											// get Index of Unternehen in
											// chartDataKunden
											$scope.indexTmp = -1;
											for (c = 0; c < $scope.chartDataKunden.Names.length; c++) {
												if ($scope.chartDataKunden.Names[c] == unt) {
													$scope.indexTmp = c;
													c = $scope.chartDataKunden.Names.length;
												}
											}
											if ($scope.indexTmp != -1) {
												$scope.chartDataKunden.Data[0][$scope.indexTmp] = $scope.chartDataKunden.Data[0][$scope.indexTmp]
														+ parseInt($scope.allVertrag[a].Wert);
											}
										}
									}

									$scope.progressbarNext();

									// Einschränkungen
								} else {
									// Einschränkung auf Unternehemn
									if ($scope.subKunden == 0) {
										// UNternehmen Herausfinden
										$scope.subKunden = 1;

										$scope.untName = $scope.chartDataKunden.Names[$scope.index];
										$scope.chartDataKunden.Angezeigt = 'Kunden, Mitarbeiter von '
												+ $scope.untName + ":";
										$scope.resetChartDataKunden();

										// Depth eintragen
										$scope.chartDataKunden.Depth.Firma.Index = $scope.index;
										$scope.chartDataKunden.Depth.Firma.Name = $scope.untName;
										$scope.chartDataKunden.btnField = 'Einschränken auf ...';

										// Überprüfe ob Mitarbeiter oder Kunden
										// angezeigt werden sollen
										if ($scope.chartDataKunden.ShowMitarbeiter == true) {
											// Zeige Einzelne Mitarbeiter an
											for (d = 0; d < $scope.allKunden.length; d++) {
												if ($scope.allKunden[d].Unternehmen == $scope.untName) {
													$scope.ix = $scope.chartDataKunden.Names.length;
													$scope.chartDataKunden.Names[$scope.chartDataKunden.Names.length] = $scope
															.getKName($scope.allKunden[d].ID);
													$scope.chartDataKunden.ID[$scope.chartDataKunden.ID.length] = $scope.allKunden[d].ID;
													$scope.chartDataKunden.Data[0][$scope.ix] = 0;
													// find alle Verträge des
													// Kundes
													for (e = 0; e < $scope.allVertrag.length; e++) {
														if ($scope.allVertrag[e].KundeID == $scope.allKunden[d].ID) {
															$scope.chartDataKunden.Data[0][$scope.ix] = $scope.chartDataKunden.Data[0][$scope.ix]
																	+ parseInt($scope.allVertrag[e].Wert);
														}
													}

												}
											}
										} else {

											// Setze subKunden auf 2 damit als
											// nächstes Vertragssuche
											// durchgeführt wird
											$scope.subKunden = 2;
											$scope.chartDataKunden.btnField = 'Suche ...'

											for (d = 0; d < $scope.allVertrag.length; d++) {
												if ($scope
														.getKUnternehmen($scope.allVertrag[d].KundeID) == $scope.untName) {
													$scope.chartDataKunden.Names[$scope.chartDataKunden.Names.length] = $scope.allVertrag[d].Name;
													$scope.chartDataKunden.Data[0][$scope.chartDataKunden.Data[0].length] = $scope.allVertrag[d].Wert;
													$scope.chartDataKunden.ID[$scope.chartDataKunden.ID.length] = $scope.allVertrag[d].ID;

												}
											}

										}

										$scope.progressbarNext();

										// Einschränkung auf Kunden
									} else if ($scope.subKunden == 1) {
										// Verträge Des Mitarbeiters
										// Heraussuchen
										$scope.subKunden = 2;
										$scope.chartDataKunden.btnField = 'Suche ...'
										$scope.ID = $scope.chartDataKunden.ID[$scope.index];
										$scope.chartDataKunden.Angezeigt = 'Verträge mit: '
												+ $scope.getKName($scope.ID);
										$scope.resetChartDataKunden();

										// Setze Depth für Mitarbeiter
										$scope.chartDataKunden.Depth.Mitarbeiter.Index = $scope.index;
										$scope.chartDataKunden.Depth.Mitarbeiter.Name = $scope
												.getKName($scope.ID);

										for (h = 0; h < $scope.allVertrag.length; h++) {
											if ($scope.allVertrag[h].KundeID == $scope.ID) {
												$scope.chartDataKunden.Names[$scope.chartDataKunden.Names.length] = $scope.allVertrag[h].Name;
												$scope.chartDataKunden.Data[0][$scope.chartDataKunden.Data[0].length] = $scope.allVertrag[h].Wert;
												$scope.chartDataKunden.ID[$scope.chartDataKunden.ID.length] = $scope.allVertrag[h].ID;
											}
										}
										$scope.progressbarNext();
									} else {
										$scope
												.searchVID($scope.chartDataKunden.ID[$scope.index]);
									}
								}

							};
							
							$scope.getKundenIndex = function(name) {
								for (i = 0; i < $scope.chartDataKunden.Names.length; i++) {
									if ($scope.chartDataKunden.Names[i] == name) {
										return i;
									}
								}
								return -1;
							};

							$scope.kundenClick = function(points, evt) {
								var label = points[0].label;
								var index = $scope.getKundenIndex(label);
								if (index != -1) {
									$scope.createChartDataKunden(index);
								} else {
									alert("Internal Error. Could not Parse Employee index for Label: "
											+ label
											+ ". Please contact your Administrator for further help.")

								}
							};

							$scope.depthSucheUnternehmen = function() {
								$scope.lastIndex = $scope.chartDataKunden.Depth.Firma.Index;
								$scope.lastSubTmp = 0;
								$scope.createChartDataKunden(-1);
								$scope.chartDataKunden.Last.index = $scope.lastIndex;
								$scope.chartDataKunden.Last.subKunden = $scope.lastSubTmp;
								$scope.lastChartKunden();
							};

							$scope.lastChartKunden = function() {
								$scope.subKunden = $scope.chartDataKunden.Last.subKunden;
								$scope
										.createChartDataKunden($scope.chartDataKunden.Last.index);
							};

							$scope.showMoVButtons = function() {
								if ($scope.subKunden == 0) {
									return false;
								} else if ($scope.subKunden == 2
										&& $scope.successButtons.Mitarbeiter == "btn btn-success") {
									return false;
								}
								return true;
							};

							$scope.successButtons = {
								Mitarbeiter : "btn btn-success",
								Vertrag : "btn btn-default"

							};

							$scope.toggleMoV = function() {
								if ($scope.chartDataKunden.ShowMitarbeiter == true) {
									$scope.chartDataKunden.ShowMitarbeiter = false;
									$scope.successButtons = {
										Mitarbeiter : "btn btn-default",
										Vertrag : "btn btn-success"
									};
									$scope.lastIndex = $scope.chartDataKunden.Last.index;
									$scope.lastSubTmp = $scope.chartDataKunden.Last.subKunden;
									$scope.createChartDataKunden(-1);
									$scope.chartDataKunden.Last.index = $scope.lastIndex;
									$scope.chartDataKunden.Last.subKunden = $scope.lastSubTmp;
									$scope.lastChartKunden();
									return;
								} else {
									$scope.chartDataKunden.ShowMitarbeiter = true;
									$scope.successButtons = {
										Mitarbeiter : "btn btn-success",
										Vertrag : "btn btn-default"
									};
									$scope.lastIndex = $scope.chartDataKunden.Last.index;
									$scope.lastSubTmp = $scope.chartDataKunden.Last.subKunden;
									$scope.createChartDataKunden(-1);
									$scope.chartDataKunden.Last.index = $scope.lastIndex;
									$scope.chartDataKunden.Last.subKunden = $scope.lastSubTmp;
									$scope.lastChartKunden();
									return;
								}
							};

							$scope.getKUnternehmen = function(kid) {
								for (v = 0; v <= $scope.allKunden.length; v++) {
									if ($scope.allKunden[v].ID == kid) {
										return $scope.allKunden[v].Unternehmen;
									}
								}
								return 'Fehler';

							};

							$scope.getKName = function(id) {
								$scope.idTmp = id;
								for (w = 0; w < $scope.allKunden.length; w++) {
									$scope.idex = w;
									if ($scope.allKunden[$scope.idex].ID == $scope.idTmp) {
										return $scope.allKunden[$scope.idex].Name
												+ ", "
												+ $scope.allKunden[$scope.idex].Vorname;
									}
								}
							};

							$scope.getMName = function(id) {
								$scope.itmp = id;
								for (i = 0; i < $scope.allMitarbeiter.length; i++) {
									$scope.indext = i;
									if ($scope.allMitarbeiter[i].ID == $scope.itmp) {
										return $scope.allMitarbeiter[$scope.indext].Name
												+ ", "
												+ $scope.allMitarbeiter[$scope.indext].Vorname;
									}
								}
							};

							$scope.isKunde = function(type) {
								if (type == 'Kunde') {
									return true;
								}
								return false;
							};

							$scope.deleted = {
								Type : "",
								ID : -1,
								Collapsed : true,
								Erfolgreich : "",
								Alert : ""
							};

							$scope.deleteK = function(id) {
								$scope.progressbarStart(1);
								$scope.deleted.ID = id;
								EasyCMScriptService
										.deleteK(id)
										.success(
												function(data) {
													$scope.progressbarNext();
													$scope.deleted.Type = "Kunden";
													$scope.deleted.Collapsed = false;
													if (data) {
														$scope.deleted.Erfolgreich = "erfolgreich gelöscht.";
														$scope.deleted.Alert = "alert alert-success";

													} else {
														$scope.deleted.Erfolgreich = "konnte nicht gelöscht werden.";
														$scope.deleted.Alert = "alert alert-danger";
													}
												})
							};

							$scope.deleteM = function(id) {
								$scope.progressbarStart(1);
								$scope.deleted.ID = id;
								EasyCMScriptService
										.deleteM(id)
										.success(
												function(data) {
													$scope.progressbarNext();
													$scope.deleted.Type = "Mitarbeiter";
													$scope.deleted.Collapsed = false;
													if (data) {
														$scope.deleted.Erfolgreich = "erfolgreich gelöscht.";
														$scope.deleted.Alert = "alert alert-success";

													} else {
														$scope.deleted.Erfolgreich = "konnte nicht gelöscht werden.";
														$scope.deleted.Alert = "alert alert-danger";
													}
												})
							};
							$scope.deleteV = function(id) {
								$scope.progressbarStart(1);
								$scope.deleted.ID = id;
								EasyCMScriptService
										.deleteV(id)
										.success(
												function(data) {
													$scope.progressbarNext();
													$scope.deleted.Type = "Vertrag";
													$scope.deleted.Collapsed = false;
													if (data) {
														$scope.deleted.Erfolgreich = "erfolgreich gelöscht.";
														$scope.deleted.Alert = "alert alert-success";

													} else {
														$scope.deleted.Erfolgreich = "konnte nicht gelöscht werden.";
														$scope.deleted.Alert = "alert alert-danger";
													}
												})
							};

							$scope.lastUpdatedType = "";
							$scope.userUpdatedData;
							$scope.successfullyUpdatedCollapser = true;

							$scope.updateK = function(index) {
								$scope.progressbarStart(1);
								$scope.disableEditableKunde();
								EasyCMScriptService
										.updateK($scope.searchResults[index])
										.success(
												function(data) {
													$scope.lastUpdatedType = "Kunde";
													$scope.userUpdatedData = data;
													$scope.successfullyUpdatedCollapser = false;
													$scope.progressbarNext();
												})
							};

							$scope.updateM = function(index) {
								$scope.progressbarStart(1);
								$scope.disableEditableKunde();
								EasyCMScriptService
										.updateM($scope.searchResults[index])
										.success(
												function(data) {
													$scope.lastUpdatedType = "Mitarbeiter";
													$scope.userUpdatedData = data;
													$scope.successfullyUpdatedCollapser = false;
													$scope.progressbarNext();
												})
							};

							$scope.editKunde = -1;

							$scope.editableKunde = function(index) {
								$scope.editKunde = index;
							};
							$scope.disableEditableKunde = function() {
								$scope.editKunde = -1;
							};

							$scope.isEditableKunde = function(index) {
								if (index == $scope.editKunde) {
									return true;
								} else {
									return false;
								}
							};
							
							$scope.lastUpdatedVertrag = -1;
							$scope.successfullyUpdatedContractCollapser = true;
							$scope.contractNames = {
									Kunde : "",
									Mitarbeiter: "",
							};
							
							$scope.updateV = function(index) {
								var idx = index;
								$scope.progressbarStart(1);
								$scope.disableEditableVertrag();
								var tmp = $scope.searchResultsC[idx]
								EasyCMScriptService.updateV(tmp).success(
												function(data) {
													$scope.lastUpdatedVertrag = data.ID
													$scope.successfullyUpdatedContractCollapser = false;
													$scope.progressbarNext();
												})
							};
							
							$scope.editVertrag = -1;

							$scope.editableVertrag = function(index) {
								$scope.editVertrag = index;
								$scope.contractNames.Kunde = $scope.getKName($scope.searchResultsC[index].KundeID);
								$scope.contractNames.Mitarbeiter = $scope.getMName($scope.searchResultsC[index].MitarbeiterID);
							};
							
							$scope.setContractUpdateKunde = function(index){
								$scope.searchResultsC[$scope.editVertrag].KundeID = $scope.allKunden[index].ID;
								$scope.editableVertrag($scope.editVertrag);
							};
							
							$scope.setContractUpdateMitarbeiter = function(index){
								$scope.searchResultsC[$scope.editVertrag].MitarbeiterID = $scope.allMitarbeiter[index].ID;
								$scope.editableVertrag($scope.editVertrag);
							};
							
							
							
							$scope.disableEditableVertrag = function() {
								$scope.editVertrag = -1;
							};

							$scope.isEditableVertrag = function(index) {
								if (index == $scope.editVertrag) {
									return true;
								} else {
									return false;
								}
							};

							$scope.gotResults = function() {
								if (!$scope.searchResults) {
									return false;
								}
								return true;
							};
							
							$scope.gotResultsC = function() {
								if (!$scope.searchResultsC) {
									return false;
								}
								return true;
							};

							$scope.sortAlphabetically = function() {

							};

							$scope.numberSorted = 0;
							$scope.increasing = false;
							$scope.newData;
							$scope.sortNumber = function() {
								$scope.notsorted = true;
								$scope.tmp;
								if (!$scope.increasing) {
									while ($scope.notsorted) {
										$scope.notsorted = false;
										for (i = 1; i < $scope.chartDataContract.Data.length; i++) {
											if ($scope.chartDataContract.Data[i] < $scope.chartDataContract.Data[i - 1]) {
												$scope.tmp = $scope.chartDataContract.Data[i - 1];
												$scope.chartDataContract.Data[i - 1] = $scope.chartDataContract.Data[i];
												$scope.chartDataContract.Data[i] = $scope.tmp;
												$scope.tmp = $scope.chartDataContract.Names[i - 1];
												$scope.chartDataContract.Names[i - 1] = $scope.chartDataContract.Names[i];
												$scope.chartDataContract.Names[i] = $scope.tmp;
												$scope.notsorted = true;
											}
										}
									}
									$scope.increasing = true;
									$scope.numberSorted = 1;
								} else {
									while ($scope.notsorted) {
										$scope.notsorted = false;
										for (b = 1; b < $scope.chartDataContract.Data.length; b++) {
											if ($scope.chartDataContract.Data[b] > $scope.chartDataContract.Data[b - 1]) {
												$scope.tmp = $scope.chartDataContract.Data[b - 1];
												$scope.chartDataContract.Data[b - 1] = $scope.chartDataContract.Data[b];
												$scope.chartDataContract.Data[b] = $scope.tmp;
												$scope.tmp = $scope.chartDataContract.Names[b - 1];
												$scope.chartDataContract.Names[b - 1] = $scope.chartDataContract.Names[b];
												$scope.chartDataContract.Names[b] = $scope.tmp;
												$scope.notsorted = true;
											}
										}
									}
									$scope.increasing = false;
									$scope.numberSorted = 2;
								}
							};

						} ]);
