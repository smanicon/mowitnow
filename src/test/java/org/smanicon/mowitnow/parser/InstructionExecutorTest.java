package org.smanicon.mowitnow.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.smanicon.mowitnow.models.LawnMower;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.smanicon.mowitnow.parser.InstructionSet.*;

public class InstructionExecutorTest {

    @Mock
    private LawnMower lawnMower;

    private InstructionExecutor instructionExecutor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        instructionExecutor = new InstructionExecutor(lawnMower);
    }

    @Test
    public void should_execute_instruction_right_turn_lawnmower() {
        instructionExecutor.execute(Arrays.asList(D));

        verify(lawnMower).turnRight();
    }

    @Test
    public void should_execute_instruction_left_turn_lawnmower() {
        instructionExecutor.execute(Arrays.asList(G));

        verify(lawnMower).turnLeft();
    }

    @Test
    public void should_execute_instruction_move_lawnmower() {
        instructionExecutor.execute(Arrays.asList(A));

        verify(lawnMower).move();
    }

    @Test
    public void should_execute_many_instruction_on_lawnmower() {
        instructionExecutor.execute(Arrays.asList(A, D, A, G));

        InOrder inOrder = Mockito.inOrder(lawnMower);

        inOrder.verify(lawnMower).move();
        inOrder.verify(lawnMower).turnRight();
        inOrder.verify(lawnMower).move();
        inOrder.verify(lawnMower).turnLeft();
    }
}
