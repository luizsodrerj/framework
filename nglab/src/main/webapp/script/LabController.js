
	angular.module("nglab").controller(
		"LabController", function($scope, $http, appConfig) {
			
			baseUrl = appConfig.contextRoot;
			$scope.contato	= newContato();
			$scope.contatos = []; 
			
//			$scope.addContato = function () {
//				var url = baseUrl + '/rest/controller/create';
//				
//				$http.post(url,$scope.contato)
//					 .success(function(data, status, headers, config) {
//						//$scope.contatos = data; 
//					 }).error(function(data, status, headers, config) {
//						 alert("failure");
//					 });			
//				$scope.contato = newContato();
//				listAll($http, $scope);
//			}
			
			$scope.listContatos = function () {
				var url = baseUrl + '/rest/list';
				
				listAll($http, $scope, url);
			}
	});

	function listAll($http, $scope, url) {
		$http.get(url)
	      .success(function(data, status, headers, config) {
	            $scope.contatos = data; 
	        })
	      .error(function(data, status, headers, config) {
	    	  alert("failure");
	      });			
	}
	
	function newContato() {
		var contato = {
			nome: '',
			tel: ''
		}
		return contato;
	}
	
	
	
	