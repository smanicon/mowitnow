package org.smanicon.mowitnow.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnMowerTest {
    @Test
    public void should_look_EAST_when_turn_right_and_initial_direction_was_NORTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.NORTH);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void should_look_SOUTH_when_turn_right_and_initial_direction_was_EAST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.EAST);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void should_look_WEST_when_turn_right_and_initial_direction_was_SOUTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.SOUTH);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void should_look_NORTH_when_turn_right_and_initial_direction_was_WEST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.WEST);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void should_look_WEST_when_turn_left_and_initial_direction_was_NORTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.NORTH);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void should_look_SOUTH_when_turn_left_and_initial_direction_was_WEST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.WEST);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void should_look_EAST_when_turn_left_and_initial_direction_was_SOUTH() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.SOUTH);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void should_look_NORTH_when_turn_left_and_initial_direction_was_EAST() {
        LawnMower lawnMower = new LawnMower(new Position(0, 0), Direction.EAST);

        lawnMower.turnLeft();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void should_move_on_top_case_when_direction_is_NORTH() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.NORTH);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 2));
    }

    @Test
    public void should_move_on_bottom_case_when_direction_is_SOUTH() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.SOUTH);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 0));
    }

    @Test
    public void should_move_on_left_case_when_direction_is_WEST() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.WEST);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(0, 1));
    }

    @Test
    public void should_move_on_right_case_when_direction_is_EAST() {
        LawnMower lawnMower = new LawnMower(new Position(1, 1), Direction.EAST);

        lawnMower.move();

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(2, 1));
    }

}
