var app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http){	

	// Quản lý sản phẩm được yêu thích
	var $like = $scope.like = {
	//	items: [],
		change(id){
			$http.get(`/rest/favourite/${id}`).then(resp => {
				$scope.favourite = resp.data;
				if($scope.favourite.isLiked){
					// Đang ở trạng thái like. User unlike => cập nhật isLike = 0
					$http.put(`/rest/favourite/${id}`).then(resp =>{
						console.log("Success", resp);
						location.href = "/product/detail/" + id  ;
					}).catch(error => {
						console.log("Error", error);
					})
				}else{
					// Đang ở trạng thái unlike. User like => cập nhật isLike = 1
					$http.put(`/rest/favourite/${id}`).then(resp =>{
						console.log("Success", resp);
						location.href = "/product/detail/" + id  ;
					}).catch(error => {
						console.log("Error", error);
					})
				}
				
			})
		},
		// Thêm sản phẩm được like
	//	add(id){
			// check id có hay chưa
	//		var item = this.items.find(item => item.productId == id);
			// Nếu tồn tại thì unlike
	//		if(item){
	//			this.items.splice(item, 1);
	//			this.saveToLocalStorage();
	//			$http.delete(`/rest/favourite/${id}`).then(resp => {
	//				alert("Unlike")
	//			}).catch(error => {
	//				alert("Unlike Error")
	//			})
	//		}else{
				// ko tồn tại thì đi gọi API để tìm sp
	//			$http.get(`/web/rest/product/${id}`).then(resp => {
	//				resp.data.qty = 1;
					// thêm một phần tử vào cuối một mảng
	//				this.items.push(resp.data);
	//				this.saveToLocalStorage();
					// insert 1 record vào Favourite
	//				$scope.favourite = {
	//					get account(){
	//						return {username: $auth.user.username};
	//					},
	//					get product(){
	//						return {productId: id};
	//					},
	//				};
	//				$http.post("/rest/favourite", $scope.favourite).then(resp => {
	//					alert("You liked product id: " + id);
	//				}).catch(error => {
	//					alert("Insert failed id: " + id);
	//				})
	//			})
	//		}
	//	},
		
		// Tổng số lượng mặt hàng được thích
	//	get count(){
	//		return this.items
	//		.map(item => item.qty)
	//		.reduce((total, qty) => total += qty, 0);
	//	},
		
		// cập nhật Local Storage
	//	saveToLocalStorage(){
	//		var json = JSON.stringify(angular.copy(this.items));
	//		localStorage.setItem("favourite", json);
	//	},
		
		// Đọc giỏ hàng từ local storage
	//	loadFromLocalStorage(){
	//		var json = localStorage.getItem("favourite");
	//		this.items = json ? JSON.parse(json) : [];
	//	}
	};
	
	// tải toàn bộ local vào bộ nhớ
	//$like.loadFromLocalStorage();
	
	//==============================================
	
	// Quản lý giỏ hàng
	var $cart = $scope.cart = {
		items: [],
		
		// Thêm sản phẩm vào giỏ 
		add(id){
			var item = this.items.find(item => item.productId == id);         // tìm id, xem có tồn tại chưa
			if(item){  // đã tồn tại thì ++
				if(item.qty < item.productQuantity){
					// Kiểm tra số lượng sản phẩm trong giỏ hàng với số lượng sản phẩm trong kho
					item.qty++;
					this.saveToLocalStorage();
				}else{
					alert("The number of products has exceeded the quantity in stock!");
				}
			}else{     // chưa tồn tại, gọi API tìm sp
				$http.get(`/web/rest/product/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();                                // save vào localstorage
				})
			}
		},
		
		// Xóa sản phẩm khỏi giỏ hàng
		remove(id){
			var index = this.items.findIndex(item => item.productId == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		
		// Xóa sạch các mặt hàng trong giỏ 
		clear(){
			this.items = [];
			this.saveToLocalStorage();
		},
		
		// Tính thành tiền của 1 sản phẩm
		amt_of(item){
			return item.productPrice * item.qty;
		},
		
		// Tính tổng số lượng các mặt hàng trong giỏ
		get count(){
			return this.items.map(item => item.qty).reduce((total, qty) => total += qty, 0);
		},
		
		// Tổng thành tiền các mặt hàng trong giỏ
		get amount(){
			return this.items.map(item => this.amt_of(item)).reduce((total, amt) => total += amt, 0);
		},
		
		// Lưu giỏ hàng vào local storage
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		
		// Đọc giỏ hàng từ local storage
		loadFromLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}
	
	// tải lại toàn bộ localStorage lưu vào bộ nhớ
	$cart.loadFromLocalStorage();

	// Thêm sự kiện tăng giảm số lượng
	$scope.increaseQuantity = function(item) {
		// Kiểm tra số lượng trong kho
		if (item.qty < item.productQuantity) {
			// Nếu số lượng của item chưa vượt quá số lượng trong kho
			// thì tăng số lượng của item lên 1
			item.qty += 1;
		}else{
			alert("The number of products has exceeded the quantity in stock!");
		}
		$scope.cart.saveToLocalStorage();
	};

	$scope.decreaseQuantity = function(item) {
		if (item.qty > 1) {
			item.qty -= 1;
			$scope.cart.saveToLocalStorage();
		}
	};
	
	/*	Đặt hàng
	 * 	Phần 1: thông tin user nhập vào
	 * 	account: lấy username bằng Jquery trên giao diện về
	 * 	get orderDetails(){} : lấy toàn bộ mặt hàng gửi lên server
	 * 	purchase(){} : thực hiện gửi lên server
	 */
	$scope.order = {
		address: "",
		get account(){ 
			return {username: $auth.user.username}
		},
		createdDate: new Date(),
		get orderDetails(){
			return $cart.items.map(item => {
				return {
					product: {productId: item.productId},
					price: item.productPrice,
					quantity: item.qty
				}
			});
		},
		
		purchase(){
			var order = angular.copy(this);
			// thực hiện đặt hàng, orders này là giá trị truyền vào JsonNode orderData bên Controller
			$http.post("/rest/order", order).then(resp => {
				alert("Thank you for your purchase!");
				$cart.clear();                                                 // xóa giỏ hàng
				location.href = "/order/detail/" + resp.data.orderId  ;		   // Chuyển sang trang chi tiết đơn hàng
			}).catch(error => {
				alert("Error processing transaction!");
				console.log(error)
			})
		}
	} 
	
})