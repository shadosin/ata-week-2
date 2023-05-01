package com.kenzie.unittesting.kenziemath.math;

/**
 * A utility providing math operations (for now, adding integers and averaging integers).
 * <p>
 * We're keeping the test for {@code add()} from the reading, and adding new ones for {@code average()}.
 */
public class KenzieMath {

    /**
     * Computes the sum of the integers in the array argument, throwing an
     * <code>ArithmeticException</code> if the sum overflows or
     * underflows MAX_INTEGER/MIN_INTEGER.
     *
     * @param integers The array of ints for which to compute the sum
     * @return The sum of the integers in <code>integers</code>.
     * @see ArithmeticException
     */
    public int add(final int[] integers) {
        if (null == integers) {
            return 0;
        }

        Long longSum = 0L;
        for (int x : integers) {
            longSum = longSum + x;

            // throw ArithmeticException on overflow/underflow of running sum
            if (longSum > Integer.MAX_VALUE) {
                throw new ArithmeticException("Sum overflowed int");
            } else if (longSum < Integer.MIN_VALUE) {
                throw new ArithmeticException("Sum underflowed int");
            }
        }

        return longSum.intValue();
    }

    /**
     * Computes average of the integers in the provided array.
     * <p>
     * Throws an {@code IllegalArgumentException} if the array is empty or null.
     * <p>
     * Throws an {@code ArithmeticException} if while computing the average, we overflow trying to sum the
     * elements of {@code integers}.
     *
     * @param integers The non-null/non-empty integers to compute the average of
     * @return the resulting average (as a double)
     */
    public double average(final int[] integers) {
        if (null == integers || integers.length == 0) {
            throw new IllegalArgumentException("Cannot compute the average of an empty/null array of integers");
        }

        final int intSum = add(integers);
        return (double) intSum / integers.length;
    }
}
