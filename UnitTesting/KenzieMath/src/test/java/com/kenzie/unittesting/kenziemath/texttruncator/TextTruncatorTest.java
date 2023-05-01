package com.kenzie.unittesting.kenziemath.texttruncator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test the <code>TextTruncator</code> behavior.
 */
public class TextTruncatorTest {
    @Test
    public void truncateTo_stringLongerThanNumChars_shortensToFirstNumChars() {
        // GIVEN
        String original = "One two three four five";
        TextTruncator truncator = new TextTruncator(original);

        // WHEN
        String truncatedString = truncator.truncateTo(7);

        // THEN
        Assertions.assertEquals("One two", truncatedString,
                                "Expected to truncate to the first 7 characters");

    }

    /* ADD YOUR TESTS HERE */

}
