package com.kenzie.unittesting.kenziemath.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * We're keeping the tests for {@code add()} from the pre-work, and adding new ones for {@code average()}.
 */
public class KenzieMathTest {

    // add()

    @Test
    public void add_singleInteger_returnsTheInteger() {
        // GIVEN
        int[] oneInteger = {42};
        KenzieMath kenzieMath = new KenzieMath();

        // WHEN
        int result = kenzieMath.add(oneInteger);

        // THEN
        assertEquals(42, result, "Expected adding a single int to return the int");
    }

    @Test
    public void add_twoIntegers_returnsTheirSum() {
        // GIVEN
        int[] tuple = {6, 9};
        KenzieMath kenzieMath = new KenzieMath();

        // WHEN
        int result = kenzieMath.add(tuple);

        // THEN
        assertEquals(15, result, String.format(
            "Expected adding two ints (%s) to return their sum (15)",
            Arrays.toString(tuple))
        );
    }

    @Test
    public void add_emptyArray_returnsZero() {
        // GIVEN
        int[] emptyArray = {};
        KenzieMath kenzieMath = new KenzieMath();

        // WHEN
        int result = kenzieMath.add(emptyArray);

        // THEN
        assertEquals(0, result, "Expected empty array to sum to zero");
    }

    @Test
    public void add_nullArray_returnsZero() {
        // GIVEN
        int[] nullArray = null;
        KenzieMath kenzieMath = new KenzieMath();

        // WHEN
        int result = kenzieMath.add(nullArray);

        // THEN
        assertEquals(0, result, "Expected null array to sum to zero");
    }

    @Test
    public void add_sumOutOfBounds_throwsArithmeticException() {
        // GIVEN
        int[] values = {Integer.MAX_VALUE - 5, 3, 3};
        KenzieMath kenzieMath = new KenzieMath();

        // WHEN - attempt to compute the sum
        // PARTICIPANTS: ADD YOUR WHEN CONDITION HERE AND DELETE THE NEXT LINE
        fail("Expected an attempt to compute the sum for add_sumOutOfBounds_throwsArithmeticException");

        // THEN - exception thrown

        // the following syntax is a little fancy, just know that it's
        // asserting that when the second line calls the add()
        // method that we should see an `ArithmeticException` thrown
        assertThrows(ArithmeticException.class,
                     () -> kenzieMath.add(values),
                     "Summing above MAX_VALUE should result in ArithmeticException thrown");
    }

    @Test
    public void add_sumOutofBoundsUnderflow_throwsArithmeticException() {
        // GIVEN
        int[] values = {Integer.MIN_VALUE + 5, -3, -3};
        KenzieMath kenzieMath = new KenzieMath();

        // WHEN - attempt to compute the sum
        // PARTICIPANTS: ADD YOUR WHEN CONDITION HERE AND DELETE THE NEXT LINE
        fail("Expected an attempt to compute the sum for add_sumOutofBoundsUnderflow_throwsArithmeticException");

        // THEN - exception thrown
        assertThrows(ArithmeticException.class,
                     () -> kenzieMath.add(values),
                     "Summing below MIN_VALUE should result in ArithmeticException thrown");
    }

    // average()

    // PARTICIPANTS: ADD YOUR NEW TESTS HERE (and you can delete this line while you're at it)
}
