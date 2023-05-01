#Class: KenzieMath

Implement these test cases as JUnit unit tests in `KenzieMathTest`:

##Minimum cases

**average_ofSingleInteger_isThatInteger**
* **Description**: Averaging a single integer returns the single value
* GIVEN
    * Single integer array
* WHEN
    1. Compute the average
* THEN
    * The average is (the double form of) the single value

**average_ofSeveralIntegers_isCorrect**
* **Description**: Averaging an array of small integers returns the correct average
* GIVEN
    * Array of several integers (with easy to compute average)
* WHEN
    1. Compute the average
* THEN
    * The average is correct

**average_ofNullArray_throwsIllegalArgumentException**
* **Description**: Attempting to average a `null` array throws an exception
* GIVEN
    * Null array
* WHEN
    1. Attempt to compute the average
* THEN
    * Exception thrown

**average_ofPositiveAndNegativeIntegers_isCorrect**
* **Description**: Averaging a mix of positive and negative integers returns the correct average
* GIVEN
    * Array of mix of positive and negative integers (with easy to compute average)
* WHEN
    1. Compute the average
* THEN
    * The average is correct

**average_ofIntegersIncludingZeroes_isCorrect**
* **Description**: Averaging an array of integers that includes several zeroes returns correct average
* GIVEN
    * Array of ins including zeroes
* WHEN
    1. Compute the average
* THEN
    * The average is correct

**average_ofEmptyArray_throwsIllegalArgumentException**
* **Description**: Attempting to average an empty array (zero elements) throws an exception
* GIVEN
    * Empty array
* WHEN
    1. Attempt to compute the average
* THEN
    * Exception thrown

**average_ofLargeNumbersThatOverflow_throwsArithmeticException**
* **Description**: Attempting to average an array that contains large numbers whose sum exceeds `Integer.MAX_VALUE`
                   results in an exception
* GIVEN
    * Array of large numbers that sum to greater than `Integer.MAX_VALUE`
* WHEN
    1. Attempt to compute the sum
* THEN
    * Exception thrown
