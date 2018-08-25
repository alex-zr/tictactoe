package ua.com.danit.controller;

import ua.com.danit.enums.Piece;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.Move;
import ua.com.danit.persistence.domain.dto.CreateMoveDTO;
import ua.com.danit.persistence.domain.dto.MoveDTO;
import ua.com.danit.service.GameService;
import ua.com.danit.service.MoveService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log
@RestController
@RequestMapping("/move")
@AllArgsConstructor
public class MoveController {

    private final MoveService moveService;
    private final GameService gameService;
    private final HttpSession httpSession;

    @PostMapping(value = "/create")
    public Move createMove(@RequestBody CreateMoveDTO createMoveDTO) {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        log.info("move to insert:" + createMoveDTO.getBoardColumn() + createMoveDTO.getBoardRow());


        Move move = moveService.createMove(gameService.getGame(gameId), Piece.valueOf(createMoveDTO.getPieceCode()), createMoveDTO);
        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game, Piece.valueOf(createMoveDTO.getPieceCode())));

        return move;
    }

    @GetMapping(value = "/list")
    public List<MoveDTO> getMovesInGame() {

        Long gameId = (Long) httpSession.getAttribute("gameId");

        return moveService.getMovesInGame(gameService.getGame(gameId));
    }

}
