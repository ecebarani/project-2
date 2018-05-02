myApp.controller("BlogCommentController",function($scope,$rootScope,$location,$http)
		
{
	$scope.blogComment={commentId:0,commentText:'',loginname:'',blogId:0,commentDate:''}
	
	$scope.addComment=function()
	{
		$scope.blogComment.loginname=currentUser.loginname;
		$scope.blogComment.blogId=$rootScope.blogId;
		
		$http.post('http://localhost:8054/ChatMiddleware/addComment',$scope.blogComment)
		.then(function(response)
				{
					$location.path("/blogComment");
				});
	}
	
});