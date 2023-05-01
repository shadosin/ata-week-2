package com.kenzie.unittesting.kenziemath.texttruncator;

/**
 * Primary method is responsible for returning a truncated version of the <code>String</code>
 * used to constructs the truncator. It can optionally append a suffix to the string (e.g. "...")
 * <p>
 * Example:
 * </p>
 * <pre>
 *     TextTruncator truncator = new TextTruncator(myString);
 *     String truncatedString = truncator.truncateTo(10, "...(more)");
 * </pre>
 */
public class TextTruncator {
    private String originalString;

    /**
     * creates a <code>TextTruncator</code> ready to truncate your text.
     *
     * @param original The <code>String</code> to truncate
     */
    public TextTruncator(String original) {
        this.originalString = original;
    }

    /**
     * Truncates the <code>String</code> to the specified number of characters.
     *
     * @param numChars How many characters from the original <code>String</code> that should be
     *                 returned in the result.
     * @return The truncated <code>String</code>, which will be <code>numChars</code> characters long or shorter.
     */
    public String truncateTo(int numChars) {
        return truncateTo(numChars, "");
    }

    /**
     * If necessary, truncates the <code>String</code> to the specified number of characters,
     * <em>including</em> the <code>suffix</code> <code>String</code>, which gets appended
     * to the end of the truncated version of the original <code>String</code>.
     *
     * @param numChars Maximum number of characters that the returned <code>String</code> should
     *                 return (including <code>suffix</code>).
     * @param suffix   A <code>String</code> to append to the truncated text, e.g. <code>"..."</code>
     * @return The truncated string, with <code>suffix</code> at the end,
     *         if the original <code>String</code> needs truncation.
     */
    public String truncateTo(int numChars, String suffix) {
        String stringToTruncate = "";
        if (this.originalString != null) {
            stringToTruncate = this.originalString;
        }

        String suffixToAdd = "";
        if (null != suffix) {
            suffixToAdd = suffix;
        }

        int maxLength = numChars;

        // If the string is short enough, just leave it alone
        // otherwise, truncate to make room for the suffix itself
        if (maxLength >= stringToTruncate.length()) {
            maxLength = stringToTruncate.length();
        } else {
            maxLength = numChars - suffixToAdd.length();
        }

        String truncatedVersion = stringToTruncate.substring(0, maxLength);
        truncatedVersion = truncatedVersion + suffixToAdd;
        return truncatedVersion;
    }
}
