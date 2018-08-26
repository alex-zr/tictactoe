package ua.com.danit.persistence.domain;

import lombok.Builder;
import ua.com.danit.enums.GameStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.util.Date;

@Data
@Entity
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    GameStatus gameStatus;

    @Column(name = "created", nullable = true)
    Date created;
}
