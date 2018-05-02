myApp.controller("BlogController",function($rootScope,$scope,$location,$cookiesStore,$http)
{
	$scope.blog={'blogId':0,'blogName':'','blogContent':'','createDate':'','likes':'','loginname':''}
	
	$scope.blogdata;
	
	$scope.addBlog=function($scope)
	{
		console.log('Adding Blog Information');
		$http.post('http://localhost:8054/ChatMiddleware/addBlog',$scope.blog)
		.then(function(response)
				{
			$location.path("/blog");
				});
	};
	
	function listBlog()
	{
		console.log('List Blog Method');
		$http.get('http://localhost:8054/ChatMiddleware/showAllApprovedBlogs')
			.then(function(response)
					{
				console.log(response.data);
				$scope.blogdata=response.data;
					});
	}
	
	function listAllBlogs()
	{
		console.log('list All Blog Method');
		$http.get('http://localhost:8054/ChatMiddleware/showAllBlogs')
			.then(function(response)
		
					
					
					
		.then(function(response)
				{
					console.log('Blog Rejected');
				});
			
	$scope.deleteBlog=function(blogId)
	{
		console.log('i am in Deleting the Blog');
		$http.get('http://localhost:8054/ChatMiddleware/deleteBlog/'+blogId)
		.then(function(response)
				{
			console.log('Blog Detected');
				});
	}
	
	function listAllBlogs()
	{
		console.log('List All Blog Method');
		$http.get('http://localhost:8054/ChatMiddleware/showAllBlogs')
		.then(function(response)
				{
			console.log(response.data);
			$scope.allBlogdata=response.data;
				});
	}
	
	$scope.listBlogComment=function(blogId)
	{
		console.log('Blog Comments Displaying');
		$http.get('http://localhost:8054/ChatMiddleware/listAllBlogComments/'+blogId)
		.then(function(response)
				{
			$rootScope.blogcommentdata=response.data;
			$rootScope.blogId=
			console.log($rootScope.blogcommentdata);
			$location.path("/blogcomment");
				});
		
	}
