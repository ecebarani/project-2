myApp.controller("UserController",function($scope,$rootScope,$http,$location)
{
	$scope.User={loginname:'',password:'',userName:'',emailId:'',mobileNo:'',address:'',role:''};
	
	$scope.register=function()
	{
		console.log('Entered into the Register function');
		$scope.User.role='ROLE_USER';
		$http.post('http://localhost:8054/ChatMiddleware/registerUser',$scope.User)
		.then(function(response)
				{
			        console.log('Registeration completed');
			        console.log(response.statusText);
			        $location.path("/login");
				});
		
	}
	
	$scope.login=function()
	{
		console.log('Entered into the Login function');
		$http.post('http://localhost:8054/ChatMiddleware/checkLogin',$scope.User)
		.then(function(response)
				{
			        $scope.User=response.data;
			        $rootScope.currentUser=response.data;
			        console.log($rootScope.currentUser);
			        $CookieStore.put('userDetails',response.data);
			        $location.path("/UserHome");
				});
		
	}
	$rootScope.logout=function()
	{
		console.log('Enter into the Logout Function');
		delete $rootScope.currentUser;
		$cookieStore
	}
