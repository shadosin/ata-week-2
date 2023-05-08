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


        // THEN - exception thrown
        assertThrows(ArithmeticException.class,
                     () -> kenzieMath.add(values),
                     "Summing below MIN_VALUE should result in ArithmeticException thrown");
    }

    // average()

@Test
public void average_ofSingleInteger_isThatInteger(){
    //Given
    int[] oneInteger = {42};
    KenzieMath kenzieMath = new KenzieMath();

    //When
    double result = kenzieMath.average(oneInteger);

    //then

    assertEquals(42, result, "Expected average a single int to return the int");
}
@Test
    public void average_ofSeveralIntegers_isCorrect(){
        //Given
        int[] values = {7,9};
        KenzieMath kenzieMath = new KenzieMath();
        //When
        double result = kenzieMath.average(values);
        //Then
    assertEquals(8, result, "Expected 8 average for 7 & 9");
    }
@Test
    public void average_ofNullArray_throwsIllegalArgumentException(){
        //Given
    int[] nullArray = null;
    KenzieMath kenzieMath = new KenzieMath();
    //When

    //then
    assertThrows(IllegalArgumentException.class,
            () -> kenzieMath.average(nullArray),
                "Expected IllegalArgumentException when attempting to average null array"
    );
}
@Test
    public void average_ofPositiveAndNegativeIntegers_isCorrect(){
        //Given
    int[] mixed = {-2, 6};
    KenzieMath kenzieMath = new KenzieMath();
    //When
    double result = kenzieMath.average(mixed);
    //then
    assertEquals(2, result, "Average of negative and positive numbers should be like subtraction and than division");
}
@Test
    public void average_OfIntegersIncludingZeroes_isCorrect(){
        //Given
        int [] zeroArray = {0, 0, 16, 16};
        KenzieMath kenzieMath = new KenzieMath();
        //When
        double result = kenzieMath.average(zeroArray);
        //Then
    assertEquals(8, result, "Averaging an array of integers that includes several zeroes returns the correct average");
}
@Test
    public void average_ofEmptyArray_throwsIllegalArgumentException(){
    //Given
    int[] emptyArray = {};
    KenzieMath kenzieMath = new KenzieMath();
    //When

    //Then
      assertThrows (IllegalArgumentException.class,
            () -> kenzieMath.average(emptyArray),
    "Expected illegal Argument thrown when attempting to average empty array");
}
@Test
    public void average_ofLargeNumbersThatOverflow_throwsArithmeticException(){
        //Given
    int [] maxArray = {Integer.MAX_VALUE-5, 3, 3};
    KenzieMath kenzieMath = new KenzieMath();
    //When

    //Then
    assertThrows(ArithmeticException.class,
            () -> kenzieMath.average(maxArray),
            "Expected Arithmetic Exception thrown when attempting to average above max value");
}
}
