/**
 * 
 */
var w2sTaxApp = angular.module("w2sTaxApp", []);

w2sTaxApp.controller("taxSmtController", function($scope, $http) {

	$scope.submitForm = function(form) {
		
		$http(
				{
					method : 'GET',
					url : 'http://localhost:8080/w2s/rest/taxs',
					params: {aoid:form.aoid , ooid:form.ooid }
				}).then(function(res) {
					
					$scope.docList = res.data.docList;
					var docList = res.data.docList;
					
		})
/*
		$http.get('http://localhost:8090/w2s/taxs', aoid, ooid)
				.success(function(data) {
					alert("fine");
				}).error(function(data) {
					alert('fail');
				})*/

		// use URI to get deductions
		/*
		 * $.ajax({ url : "http://localhost:8090/w2s/taxs", method : "GET",
		 * beforeSend : function(xhr) { xhr.setRequestHeader("Content-Type",
		 * "application/json"); }, success : function(data) {
		 *  }, error : function(jqXHR) {
		 *  } })
		 */

	}

})