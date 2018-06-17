package com.pragmatic.play.persistence.repository;

import com.pragmatic.play.enums.Piece;
import com.pragmatic.play.persistence.domain.Game;
import com.pragmatic.play.persistence.domain.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {

    List<Move> findByGame(Game game);

    List<Move> findByGameAndPieceCode(Game game, Piece pieceCode);
}
