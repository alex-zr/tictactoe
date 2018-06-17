app.controller('gameController', ['$rootScope', '$routeParams', '$scope', '$http', '$location',
    function (rootScope, routeParams, scope, http, location) {
        var gameStatus;
        var currentPiece = 'X';
        getInitialData();

        function getInitialData() {

            http.get('/game/' + routeParams.id).success(function (data) {
                scope.gameProperties = data;
                gameStatus = scope.gameProperties.gameStatus;
                getMoveHistoryAndRefresh();
            }).error(function (data, status, headers, config) {
                scope.errorMessage = "Failed do load game properties";
            });
        }

        function getMoveHistoryAndRefresh() {
            scope.movesInGame = [];

            return http.get('/move/list').success(function (data) {
                scope.movesInGame = data;
                scope.playerMoves = [];

                //paint the board with positions from the retrieved moves
                angular.forEach(scope.movesInGame, function (move) {
                    scope.rows[move.boardRow - 1][move.boardColumn - 1].letter = move.pieceCode;
                });
            }).error(function (data, status, headers, config) {
                scope.errorMessage = "Failed to load moves in game"
            });
        }

        function checkIfBoardCellAvailable(boardRow, boardColumn) {

            for (var i = 0; i < scope.movesInGame.length; i++) {
                var move = scope.movesInGame[i];
                if (move.boardColumn == boardColumn && move.boardRow == boardRow) {
                    return false;
                }
            }
            return true;
        }

        scope.rows = [
            [
                {'id': '11', 'letter': '', 'class': 'box'},
                {'id': '12', 'letter': '', 'class': 'box'},
                {'id': '13', 'letter': '', 'class': 'box'}
            ],
            [
                {'id': '21', 'letter': '', 'class': 'box'},
                {'id': '22', 'letter': '', 'class': 'box'},
                {'id': '23', 'letter': '', 'class': 'box'}
            ],
            [
                {'id': '31', 'letter': '', 'class': 'box'},
                {'id': '32', 'letter': '', 'class': 'box'},
                {'id': '33', 'letter': '', 'class': 'box'}
            ]
        ];

        angular.forEach(scope.rows, function (row) {
            row[0].letter = row[1].letter = row[2].letter = '';
            row[0].class = row[1].class = row[2].class = 'box';
        });

        scope.markPlayerMove = function (column) {

            var boardRow = parseInt(column.id.charAt(0));
            var boardColumn = parseInt(column.id.charAt(1));
            var params = {'boardRow': boardRow, 'boardColumn': boardColumn, 'pieceCode': currentPiece};

            if (checkIfBoardCellAvailable(boardRow, boardColumn)) {

                http.post("/move/create", params, {
                    headers: {
                        'Content-Type': 'application/json; charset=UTF-8'
                    }
                }).success(function () {
                    getMoveHistoryAndRefresh().success(function () {

                        var gameStatus = scope.movesInGame[scope.movesInGame.length - 1].gameStatus;
                        if (gameStatus !== 'IN_PROGRESS') {
                            alert(gameStatus);
                            location.path('/player/panel');
                        }
                        currentPiece = currentPiece === 'X' ? 'O' : 'X';
                    });

                }).error(function (data, status, headers, config) {
                    scope.errorMessage = "Can't send the move"
                });

            }
        };
    }
]);