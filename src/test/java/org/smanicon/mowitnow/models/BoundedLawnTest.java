package org.smanicon.mowitnow.models;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BoundedLawnTest {

    @Test
    public void should_return_true_when_position_inside_the_bounded_lawn() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(2, 2);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isTrue();
    }

    @Test
    public void should_return_true_when_X_position_is_zero() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(0, 2);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isTrue();
    }

    @Test
    public void should_return_true_when_Y_position_is_zero() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(2, 0);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isTrue();
    }

    @Test
    public void should_return_false_when_X_position_is_negative() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(-1, 2);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isFalse();
    }

    @Test
    public void should_return_false_when_Y_position_is_negative() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(2, -1);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isFalse();
    }

    @Test
    public void should_return_true_when_X_position_is_same_of_lawn_width() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(5, 2);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isTrue();
    }

    @Test
    public void should_return_true_when_Y_position_is_same_of_lawn_height() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(2, 5);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isTrue();
    }

    @Test
    public void should_return_false_when_X_position_is_greater_than_lawn_width() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(2, 6);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isFalse();
    }

    @Test
    public void should_return_false_when_Y_position_is_greater_than_lawn_height() {
        BoundedLawn lawn = new BoundedLawn(5, 5);
        Position position = new Position(2, 6);
        Assertions.assertThat(lawn.isCellCanBeMowed(position)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_Lawn_width_is_negative() {
        new BoundedLawn(-1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_Lawn_height_is_negative() {
        new BoundedLawn(5, -5);
    }
}