app.controller('newGameController', ['$rootScope', '$scope', '$http', '$location',

    function (rootScope, scope, http, location) {

        rootScope.gameId = null;
        scope.newGameData = null;

        scope.newGameOptions = {
            availablePieces: [
                {name: 'X'},
                {name: 'O'}
            ],
            selectedPiece: {name: 'O'}
        };

        scope.createNewGame = function () {

            var name = window.prompt("Enter game name", "game");

            http.get("/game/create/" + name, {
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            }).success(function (data) {
                rootScope.gameId = data.id;
                location.path('/game/' + rootScope.gameId);
            }).error(function () {
                location.path('/player/panel');
            });
        }
    }
]);