/**
 * 
 */

w2sTaxApp
		.controller(
				"w2sTaxController",
				function($scope, $http) {

					/* $scope.message = "Hello AngularJS"; */

					$http(
							{
								method : 'GET',
								url : 'http://51.16.29.162/v1_0/OOID/AOID/workerTaxStatement/1610081328019745104574700013351'
							}).then(function(response) {
								
								$scope.messages = response.data.workerTaxStatement.statementSections[0].sectionLabelName;
								
								
					})

				})