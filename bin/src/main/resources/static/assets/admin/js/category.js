let pathCategory = "http://localhost:8080/rest";

app.controller("ctrl-category", function($scope, $http){
	$scope.form = {};
	$scope.items = {};
	
	$scope.load_all = function(){
        var url = `${pathCategory}/category`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(errors => {
            console.log("Error", errors)
        });
    }
    
    $scope.edit = function(categoryId){
		var url = `${pathCategory}/category/${categoryId}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch(errors => {
			console.log("Error", errors);
		});
	}
	
	$scope.update = function(){
        var category = angular.copy($scope.form);
        var url = `${pathCategory}/category/${$scope.form.categoryId}`;
        $http.put(url, category).then(resp => {
            var index = $scope.items.findIndex(category => category.categoryId == $scope.form.categoryId);
            $scope.items[index] = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${pathCategory}/category`;
        $http.post(url, item).then(resp => {
            $scope.items.push(item);
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    $scope.delete = function(categoryId){
        var url = `${pathCategory}/category/${categoryId}`;
        $http.delete(url).then(resp => {
            // tìm ra phần tử tại vị trí sẽ xóa.
            var index = $scope.items.findIndex(item => item.categoryId == categoryId);
            $scope.items.splice(index, 1); // tại vị trí đó và xóa 1 phần tử
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
	
	$scope.reset = function(){
		$scope.form = {};
	}
    
    $scope.load_all();
	$scope.reset();
    
});
