myApp.controller("JobController",function($scope,$rootScope,$http,$location)
		{
	$scope.job={jobId:0,jobDesignation:'',company:'',salary:0,location:'',jobDesc:'',lastDateApply:''}
	
	$scope.allJobData;
	
	$scope.publishJob=function()
	{
		console.log('Adding job Information');
		console.log($scope.job);
		$http.post('http://localhost:8054/ChatMiddleware/addJob',$scope.job)
		.then(function(response)
				{
			
				});
	};
	
	function listJobs()
	{
		$http.get('http://localhost:8054/ChatMiddleware/listJobs')
		.then(function(response)
				{
			$scope.allJobData=response.data;
				});
	
	}
	
});