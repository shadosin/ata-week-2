package com.kenzie.unittesting.kenziemath.addition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KenzieAdditionTest {
    @Test
    public void add_singleInteger_returnsTheInteger() {
        // GIVEN
        int[] oneInteger = {42};
        KenzieAddition kenzieAddition = new KenzieAddition();

        // WHEN
        int result = kenzieAddition.add(oneInteger);

        // THEN
        Assertions.assertEquals(42, result, "Expected adding a single int to return the int");
    }

    @Test
    public void add_twoIntegers_returnsTheirSum() {
        // GIVEN
        int[] tuple = {6, 9};
        KenzieAddition kenzieAddition = new KenzieAddition();

        // WHEN
        int result = kenzieAddition.add(tuple);

        // THEN
        Assertions.assertEquals(15, result,
                                "Expected adding two ints (6,9) to return their sum (15)");
    }

    @Test
    public void add_emptyArray_returnsZero() {
        // GIVEN
        int[] emptyArray = {};
        KenzieAddition kenzieAddition = new KenzieAddition();

        // WHEN
        int result = kenzieAddition.add(emptyArray);

        // THEN
        Assertions.assertEquals(0, result, "Expected empty array to sum to zero");
    }
    

}
