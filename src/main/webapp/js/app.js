angular.module('years', ['ngRoute', 'ngResource'])

    .controller('MainController', function ($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;
    })

    .controller('LikeController', function ($scope, $routeParams, $resource) {
        $scope.posts = [];

        var postResource = $resource('webapi/likes', {}, {
            //'id' is not a parameter of resource itself, because we are going to use query() as well
            get: {method:'GET', url:'webapi/likes/:id', params:{id:'@id'}},
            delete: {method:'DELETE', url:'webapi/likes/:id', params:{id:'@id'}},
            like: {method:'PUT', url:'webapi/likes/like/:id', params:{id:'@id'}},
            unlike: {method:'PUT', url:'webapi/likes/unlike/:id', params:{id:'@id'}},
            add: {method:'POST', url:'webapi/likes/add/:text', params:{text:'@text'}}
        });

        $scope.refreshAllPosts = function () {
            $scope.posts = postResource.query();
        };

        $scope.refreshAllPosts();

        $scope.updateSinglePost = function(id) {
            var updatedPost = postResource.get({id:id});
            updatedPost.$promise.then(function(data){
                for(var i = 0; $scope.posts[i]; i++) {
                    if($scope.posts[i].id == id){
                        $scope.posts[i] = data;
                    }
                }
            })
        };

        $scope.getPostById = function(id){
            for(var i = 0; $scope.posts[i]; i++){
                var post = $scope.posts[i];
                if(post.id == id){
                    return post;
                }
            }
        };

        $scope.likePost = function (id) {
            var answer = postResource.like({id: id});
            answer.$promise.then(function(data){
                $scope.updateSinglePost(id);
            });
        };
        $scope.unlikePost = function (id) {
            var answer = postResource.unlike({id: id});
            answer.$promise.then(function(data){
                $scope.updateSinglePost(id);
            });
        };

        $scope.addNewPost = function() {
            var text = $scope.newPostText;
            var answer = postResource.add({text:text});
            answer.$promise.then(function(data){
                $scope.refreshAllPosts();
                $scope.newPostText = '';
            });
        };

        $scope.deletePost = function(id) {
            var answer = postResource.delete({id:id});
            answer.$promise.then(function(data){
                $scope.refreshAllPosts();
            });
        };

    })

    .controller('FortuneController', function ($scope, $routeParams, $resource) {
        $scope.receivedAdvice = '';
        $scope.receivedAdviceToPerson = '';

        var randomAdvice = $resource('webapi/advice');
        var adviceToPerson = $resource('webapi/advice/:name/:birthdate', {name: '@name', birthdate: '@birthdate'});

        $scope.getTextData = function (data) {
            var text = '';
            for (var i = 0; data[i]; i++) {
                text = text + data[i];
            }
            return text;
        };

        $scope.getRandomAdvice = function () {
            var answer = randomAdvice.get();
            answer.$promise.then(function (data) {
                $scope.receivedAdvice = $scope.getTextData(data);
            });
        };

        $scope.getAdviceToPerson = function () {
            var name = $scope.name;
            var birthdate = $scope.birthdate;
            if (!name || name == '' || !birthdate || birthdate == '') {
                return '';
            }
            var answer = adviceToPerson.get({name: name, birthdate: birthdate});
            answer.$promise.then(function (data) {
                $scope.receivedAdviceToPerson = $scope.getTextData(data);
            });
        };


    })

    .config(function ($routeProvider, $locationProvider) {
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