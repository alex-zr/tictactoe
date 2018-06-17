var app = angular.module('ticTacToe', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/game/:id', {
            templateUrl: 'templates/games-page.html',
            controller: 'gameController'
        }).
        when('/player/panel', {
            templateUrl: 'templates/game-panel.html',
            controller: 'newGameController'
        }).
        otherwise({
            redirectTo: '/player/panel'
        });
}]);
