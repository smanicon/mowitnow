package org.smanicon.mowitnow.parser;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.smanicon.mowitnow.models.LawnMower;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.smanicon.mowitnow.parser.InstructionSet.*;

public class InstructionExecutorTest {
    @Test
    public void should_execute_instruction_right_turn_lawnmower() {
        LawnMower lawnMower = mock(LawnMower.class);
        InstructionExecutor instructionExecutor = new InstructionExecutor(lawnMower);
        instructionExecutor.execute(Arrays.asList(D));

        verify(lawnMower).turnRight();
    }

    @Test
    public void should_execute_instruction_left_turn_lawnmower() {
        LawnMower lawnMower = mock(LawnMower.class);
        InstructionExecutor instructionExecutor = new InstructionExecutor(lawnMower);
        instructionExecutor.execute(Arrays.asList(G));

        verify(lawnMower).turnLeft();
    }

    @Test
    public void should_execute_instruction_move_lawnmower() {
        LawnMower lawnMower = mock(LawnMower.class);
        InstructionExecutor instructionExecutor = new InstructionExecutor(lawnMower);
        instructionExecutor.execute(Arrays.asList(A));

        verify(lawnMower).move();
    }

    @Test
    public void should_execute_many_instruction_on_lawnmower() {
        LawnMower lawnMower = mock(LawnMower.class);
        InstructionExecutor instructionExecutor = new InstructionExecutor(lawnMower);
        instructionExecutor.execute(Arrays.asList(A, D, A, G));

        InOrder inOrder = Mockito.inOrder(lawnMower);

        inOrder.verify(lawnMower).move();
        inOrder.verify(lawnMower).turnRight();
        inOrder.verify(lawnMower).move();
        inOrder.verify(lawnMower).turnLeft();
    }
}
