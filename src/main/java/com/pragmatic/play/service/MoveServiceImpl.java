package com.pragmatic.play.service;

import com.pragmatic.play.enums.GameStatus;
import com.pragmatic.play.enums.Piece;
import com.pragmatic.play.persistence.domain.Game;
import com.pragmatic.play.persistence.domain.Move;
import com.pragmatic.play.persistence.domain.Position;
import com.pragmatic.play.persistence.domain.dto.CreateMoveDTO;
import com.pragmatic.play.persistence.domain.dto.MoveDTO;
import com.pragmatic.play.persistence.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoveServiceImpl implements MoveService {

    private final MoveRepository moveRepository;
    private final GameService gameService;

    @Autowired
    public MoveServiceImpl(MoveRepository moveRepository, GameService gameService) {
        this.moveRepository = moveRepository;
        this.gameService = gameService;
    }

    @Override
    public Move createMove(Game game, Piece piece, CreateMoveDTO createMoveDTO) {
        Move move = new Move();
        move.setBoardColumn(createMoveDTO.getBoardColumn());
        move.setBoardRow(createMoveDTO.getBoardRow());
        move.setCreated(new Date());
        move.setPieceCode(piece);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    @Override
    public GameStatus checkCurrentGameStatus(Game game, Piece piece) {
        if (gameService.isWinner(getMovePositionsForPeace(game, piece))) {
            return piece == Piece.X ? GameStatus.X_WON : GameStatus.O_WON;
        } else if (gameService.isBoardFull(getTakenMovePositionsInGame(game))) {
            return GameStatus.DRAW;
        } else {
            return GameStatus.IN_PROGRESS;
        }
    }

    @Override
    public List<MoveDTO> getMovesInGame(Game game) {
        return moveRepository.findByGame(game).stream()
                .map(this::createMoveDto)
                .collect(Collectors.toList());
    }

    private MoveDTO createMoveDto(Move move) {
        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setBoardColumn(move.getBoardColumn());
        moveDTO.setBoardRow(move.getBoardRow());
        moveDTO.setCreated(move.getCreated());
        moveDTO.setGameStatus(move.getGame().getGameStatus());
        moveDTO.setPieceCode(move.getPieceCode());

        return moveDTO;
    }

    @Override
    public List<Position> getTakenMovePositionsInGame(Game game) {
        return moveRepository.findByGame(game).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Position> getMovePositionsForPeace(Game game, Piece piece) {
        return moveRepository.findByGameAndPieceCode(game, piece).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }
}