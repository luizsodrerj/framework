
	angular.module("nglab", []);
	angular.module("nglab").controller(
		"LabController", function($scope, $http) {
			
			$scope.contato	= newContato();
			$scope.contatos = []; 
			
			
			$scope.testSession = function () {
				$http({
					method: 'POST',
					url: '/labweb/rest/controller/session'
				}).success(function(data, status, headers, config) {
		        }).error(function(data, status, headers, config) {
		        	alert("failure");
			    });			
			}
			
			$scope.addContato = function () {
				$http.post('/labweb/rest/controller/create',$scope.contato)
					 .success(function(data, status, headers, config) {
						//$scope.contatos = data; 
					 }).error(function(data, status, headers, config) {
						 alert("failure");
					 });			
				$scope.contato = newContato();
				listAll($http, $scope);
			}
			
			$scope.listContatos = function () {
				$http.get("/labweb/rest/controller/contatos")
			      .success(function(data, status, headers, config) {
			            $scope.contatos = data; 
			        })
			      .error(function(data, status, headers, config) {
			    	  alert("failure");
			      });			
			}
			
	});

	function listAll($http, $scope) {
		$http.get("/labweb/rest/controller/contatos")
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
	
	
	
	