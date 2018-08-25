package ua.com.danit.persistence.repository;

import ua.com.danit.enums.Piece;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {

    List<Move> findByGame(Game game);

    List<Move> findByGameAndPieceCode(Game game, Piece pieceCode);
}
