package ua.com.danit.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    int boardRow;
    int boardColumn;
}
