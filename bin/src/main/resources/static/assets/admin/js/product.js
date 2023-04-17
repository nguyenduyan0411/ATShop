let pathProduct = "http://localhost:8080/rest";

app.controller("ctrl-product", function($scope, $http){
	$scope.form = {};
	$scope.items = [];
	$scope.categories = []; // container value of categoryId
	
	// load categoryId into select box
	$http.get("/rest/category").then(resp => {
		$scope.categories = resp.data;
	});
	
	
	$scope.load_all = function(){
        var url = `${pathProduct}/product`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(errors => {
            console.log("Error", errors)
        });
    }
    
    $scope.edit = function(id){
		var url = `${pathProduct}/product/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch(errors => {
			console.log("Error", errors);
		});
	}
	
	$scope.update = function(){
        var item = angular.copy($scope.form);
        var url = `${pathProduct}/product/${$scope.form.productId}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.productId == $scope.form.productId);
            $scope.items[index] = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${pathProduct}/product`;
        $http.post(url, item).then(resp => {
            $scope.items.push(item);
            $scope.reset();
            $scope.load_all();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    $scope.delete = function(productId){
        var url = `${pathProduct}/product/${productId}`;
        $http.delete(url).then(resp => {
            // tìm ra phần tử tại vị trí sẽ xóa.
            var index = $scope.items.findIndex(item => item.productId == productId);
            $scope.items.splice(index, 1); // tại vị trí đó và xóa 1 phần tử
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    
    // Upload img
    $scope.imageChanged = function(files){
		// tạo 1 đối tượng FormData
		var data = new FormData();
		// lấy file đã chọn bỏ vào FormData
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		}).then(resp => {
			$scope.form.productImage = resp.data.name; 
		}).catch(error => {
			alert("Error upload image");
			console.log("Error", error);
		})
	}
    
    $scope.reset = function(){
		$scope.form = {
			productImage: 'cloud-upload.jpg',
		};
	}

    $scope.load_all();
    $scope.reset();
    
    // Pagination
    $scope.currentPage = 1; // Khởi tạo trang hiện tại là trang 1
    $scope.itemsPerPage = 5; // Thiết lập số sản phẩm hiển thị trên mỗi trang
	
	 // Lấy tổng số trang
    $scope.totalPages = function() {
  		var totalPages = 0;
  		for (var i = 0; i < $scope.items.length; i += $scope.itemsPerPage) {
    		totalPages++;
  		}
  		return totalPages;

	};
	
	// begin <=> currentPage
	$scope.prev = function(){
		if($scope.currentPage > 0){
			$scope.currentPage -= itemsPerPage;
		}
	}
	$scope.setPage = function(pageNum) {
        $scope.currentPage = pageNum;
    };
    
    
    // Lấy các sản phẩm của trang hiện tại
    $scope.getCurrentItems = function() {
        var startIndex = ($scope.currentPage - 1) * $scope.itemsPerPage;
        var endIndex = startIndex + $scope.itemsPerPage;
        return $scope.items.slice(startIndex, endIndex);
    };
	
	$scope.setPageFirst = function() {
  		$scope.currentPage = 1;
	};

	$scope.setPageLast = function() {
  		$scope.currentPage = $scope.totalPages();
	};

});