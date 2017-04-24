package org.smanicon.mowitnow.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LawnMowerTest {

    @Mock
    private Lawn lawn;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(lawn.isCellCanBeMowed(any(Position.class))).thenReturn(true);
    }

    @Test
    public void should_look_EAST_when_turn_right_and_initial_direction_was_NORTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.NORTH, lawn);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void should_look_SOUTH_when_turn_right_and_initial_direction_was_EAST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.EAST, lawn);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void should_look_WEST_when_turn_right_and_initial_direction_was_SOUTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.SOUTH, lawn);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void should_look_NORTH_when_turn_right_and_initial_direction_was_WEST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.WEST, lawn);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void should_look_WEST_when_turn_left_and_initial_direction_was_NORTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.NORTH, lawn);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void should_look_SOUTH_when_turn_left_and_initial_direction_was_WEST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.WEST, lawn);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void should_look_EAST_when_turn_left_and_initial_direction_was_SOUTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.SOUTH, lawn);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void should_look_NORTH_when_turn_left_and_initial_direction_was_EAST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.EAST, lawn);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void should_move_on_top_case_when_direction_is_NORTH() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.NORTH, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 2));
    }

    @Test
    public void should_move_on_bottom_case_when_direction_is_SOUTH() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.SOUTH, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 0));
    }

    @Test
    public void should_move_on_left_case_when_direction_is_WEST() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.WEST, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(0, 1));
    }

    @Test
    public void should_move_on_right_case_when_direction_is_EAST() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.EAST, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(2, 1));
    }

    @Test
    public void should_block_when_try_to_go_on_invalid_cells_WEST_side() {
        when(lawn.isCellCanBeMowed(eq(new Position(0, 1)))).thenReturn(false);
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.WEST, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 1));
    }

    @Test
    public void should_block_when_try_to_go_on_invalid_cells_SOUTH_side() {
        when(lawn.isCellCanBeMowed(eq(new Position(1, 0)))).thenReturn(false);
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.SOUTH, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 1));
    }

    @Test
    public void should_block_when_try_to_go_on_invalid_cells_NORTH_side() {
        when(lawn.isCellCanBeMowed(eq(new Position(1, 2)))).thenReturn(false);
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.NORTH, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 1));
    }

    @Test
    public void should_block_when_try_to_go_on_invalid_cells_EAST_side() {
        when(lawn.isCellCanBeMowed(eq(new Position(2, 1)))).thenReturn(false);
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.EAST, lawn);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 1));
    }
}
