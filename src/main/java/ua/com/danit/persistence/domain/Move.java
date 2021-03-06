package ua.com.danit.persistence.domain;

import ua.com.danit.enums.Piece;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    Game game;

    @Column(name = "board_row", nullable = false)
    int boardRow;

    @Column(name = "board_column", nullable = false)
    int boardColumn;

    @Enumerated(EnumType.STRING)
    Piece pieceCode;

    @Column(name = "created", nullable = false)
    Date created;
}
