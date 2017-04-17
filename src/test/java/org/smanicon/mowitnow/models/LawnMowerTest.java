package org.smanicon.mowitnow.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnMowerTest {
    @Test
    public void should_look_EAST_when_turn_right_and_initial_direction_was_NORTH() {
        LawnMower lawnMower = new LawnMower(Direction.NORTH);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void should_look_SOUTH_when_turn_right_and_initial_direction_was_EAST() {
        LawnMower lawnMower = new LawnMower(Direction.EAST);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void should_look_WEST_when_turn_right_and_initial_direction_was_SOUTH() {
        LawnMower lawnMower = new LawnMower(Direction.SOUTH);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void should_look_NORTH_when_turn_right_and_initial_direction_was_WEST() {
        LawnMower lawnMower = new LawnMower(Direction.WEST);

        lawnMower.turnRight();

        assertThat(lawnMower.getDirection()).isEqualTo(Direction.NORTH);
    }
}
