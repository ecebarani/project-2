var myApp=angular.module("myApp",['ngRoute','ngCookies']);

myApp.config(function($routeProvider)
{
	alert("Route Provider");
	$routeProvider.when("/",{templateUrl:"/index.html"})
					.when("/login",{templateUrl:"c_user/Login.html"})
					.when("/register",{templateUrl:"c_user/Register.html"})
					.when("/aboutUs",{templateUrl:"template/AboutUs.html"})
					.when("/contactUs",{templateUrl:"template/ContactUs.html"})
					.when("/UserHome",{templateUrl:"c_user/UserHome.html"})
					.when("/blog",{templateUrl:"c_blog/Blog.html"})
					.when("/showblog",{templateUrl:"c_blog/AllApprovedBlogs.html"})
					.when("/allblog",{templateUrl:"c_blog/ShowAllBlogs.html"})
					.when("/myblog",{templateUrl:"c_blog/MyBlogs.html"})
					.when("/blogcomment",{templateUrl:"c_blog/BlogComment.html"})
					.when("/adminblog",{templateUrl:"c_blog/AdminBlog.html"})
					.when("/job",{templateUrl:"c_job/Job.html"})
					.when("/jobdetail",{templateUrl:"c_job/JobDetail.html"})
					.when("/logout",{templateUrl:"c_user/Logout.html"})
					.when("/updateProfile",{templateUrl:"c_user/ProfilePicture.html"})
					.when("/showFriend",{templateUrl:"c_friend/ShowFriend.html"})
					.when("/showSuggestedFriend",{templateUrl:"c_friend/ShowSuggestedFriend.html"})
					.when("/showPendingFriend",{templateUrl:"c_friend/ShowPendingFriendList.html"})
				
});

myApp.run(function($rootScope,$cookieStore)
{
	console.log('I am in Run function');	
	console.log($rootScope.currentUser);	
	if($rootScope.currentUser==undefined)
		{
		    $rootScope.currentUser=$cookieStore.get('userDetails');
		}	
});