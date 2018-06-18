app.controller('gamesController', ['$scope', '$http', '$location', '$routeParams',
    function (scope, http, location) {

        scope.playerGames = [];

        http.get('/game/player/list').success(function (data) {
            scope.playerGames = data;
        }).error(function () {
            location.path('/player/panel');
        });

        scope.loadGame = function (id) {
            console.log(id);
            location.path('/game/' + id);
        }

    }]);