angular.module('years', ['ngRoute'])

    .controller('MainController', function($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;
    })

    .controller('LikeController', function($scope, $routeParams) {
        $scope.name = "BookController";
        $scope.params = $routeParams;
    })

    .controller('FortuneController', function($scope, $routeParams) {
        $scope.name = "ChapterController";
        $scope.params = $routeParams;
    })

    .config(function($routeProvider, $locationProvider) {
        $routeProvider
            .when('/Like', {
                templateUrl: 'like/main.html',
                controller: 'LikeController'
            })
            .when('/Fortune', {
                templateUrl: 'fortune/main.html',
                controller: 'FortuneController'
            });

    });