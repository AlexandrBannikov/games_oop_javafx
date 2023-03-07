package ru.job4j.chess;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.D4, Cell.E3);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board!");
    }

    @Test
    public void whenOccupiedCellException()
        throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.add(new BishopBlack(Cell.D4));
            logic.add(new PawnWhite(Cell.F2));
            logic.move(Cell.D4, Cell.G1);
        });
        assertThat(exception.getMessage()).isEqualTo("There are other figures on steps");
    }

    @Test
    public void whenImpossibleMoveException()
    throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.add(new BishopBlack(Cell.D4));
            logic.move(Cell.D4, Cell.A4);
        });
        assertThat(exception
                .getMessage())
                .isEqualTo(String
                        .format("Could not way by diagonal from %s to %s", Cell.D4, Cell.A4));
    }
}