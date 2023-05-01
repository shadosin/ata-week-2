package com.kenzie.debugging.wordanalyzer;

import com.kenzie.test.infrastructure.reflect.ClassQuery;
import com.kenzie.test.infrastructure.reflect.ConstructorQuery;
import com.kenzie.test.infrastructure.reflect.MethodInvoker;
import com.kenzie.test.infrastructure.reflect.MethodQuery;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WordAnalyzerTest {

    private static final String BASE_PACKAGE = "com.kenzie.debugging.wordanalyzer";

    /**
     * The entry point, which results in calls to all test methods.
     *
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        WordAnalyzerTest tester = new WordAnalyzerTest();
        tester.runAllTests();
    }

    /**
     * A convenience method that prints whether the first repeated character is the expected one.
     *
     * @param analyzed The string that will be analyzed.
     * @param expected The character expected as the first repeated character.
     * @return True if the expected character was the result, false otherwise.
     */
    private static boolean expectFirstRepeatToBe(String analyzed, char expected) {
        WordAnalyzer analyzer = new WordAnalyzer(analyzed);
        char actual = analyzer.firstRepeatedCharacter();
        if (actual == expected) {
            System.out.println(String.format("PASS: firstRepeatedCharacter for \"%s\" is '%c'.", analyzed, actual));
            return true;
        } else {
            System.out.println(String.format("FAIL: expected firstRepeatedCharacter for \"%s\" to be '%c', got '%c'!",
                    analyzed, expected, actual));
            return false;
        }
    }

    /**
     * A convenience method that prints whether the previous repeated character is the expected one.
     *
     * @param analyzed The string that will be analyzed.
     * @param index The index of the character that should be equal to the previous.
     * @return True if the expected character was the result, false otherwise.
     */
    private static boolean expectRepeatsPreviousCharacterToBe(String analyzed, int index, boolean expected) {
        WordAnalyzer analyzer = new WordAnalyzer(analyzed);
        boolean repeatsPrevious = analyzer.repeatsPreviousCharacter(index);
        if (repeatsPrevious == expected) {
            System.out.println(String.format("PASS: repeatsPreviousCharacter for \"%s\" at index %d is %s.", analyzed, index, repeatsPrevious));
            return true;
        } else {
            System.out.println(String.format("FAIL: expected repeatsPreviousCharacter for \"%s\" at index %d to be %s, got %s!",
                    analyzed, index, expected, repeatsPrevious));
            return false;
        }
    }

    /**
     * A convenience method that prints whether the first multiple character is the expected one.
     *
     * @param analyzed The string that will be analyzed.
     * @param expected The character expected as the first multiple character.
     * @return True if the expected character was the result, false otherwise.
     */
    private static boolean expectFirstMultipleToBe(String analyzed, char expected) {
        WordAnalyzer analyzer = new WordAnalyzer(analyzed);
        char actual = analyzer.firstMultipleCharacter();
        if (actual == expected) {
            System.out.println(String.format("PASS: firstMultipleCharacter for \"%s\" is '%c'.", analyzed, actual));
            return true;
        } else {
            System.out.println(String.format("FAIL: expected firstMultipleCharacter for \"%s\" to be '%c', got '%c'!",
                    analyzed, expected, actual));
            return false;
        }
    }

    /**
     * A convenience method that prints whether the number of repeated groups is the expected one.
     *
     * @param analyzed The string that will be analyzed.
     * @param expected The number of repeated groups expected.
     * @return True if the expected number was the result, false otherwise.
     */
    private static boolean expectRepeatCountToBe(String analyzed, int expected) {
        WordAnalyzer analyzer = new WordAnalyzer(analyzed);
        int actual = analyzer.countRepeatedCharacters();
        if (actual == expected) {
            System.out.println(String.format("PASS: countRepeatedCharacters for \"%s\" is %d.", analyzed, actual));
            return true;
        } else {
            System.out.println(String.format("FAIL: expected countRepeatedCharacters for \"%s\" to be %d, got %d!",
                    analyzed, expected, actual));
            return false;
        }
    }

    /**
     * Calls the test methods, keeping track of whether each WordAnalyzer method passes all of its tests or not.
     * Prints a summary of results. Note that some tests may not run if earlier tests fail.
     * <p>
     * The {@code @Test} annotation here marks this as a unit test, so that JUnit runs the tests when the Brazil package
     * is built. We'll cover this in the Unit Testing lesson.
     */
    @Test
    public void runAllTests() {
        char noneFound = 0;

        System.out.println("\nTesting repeatsPreviousCharacter()...");
        boolean pass = expectRepeatsPreviousCharacterToBe("test", 0, false);
        pass = expectRepeatsPreviousCharacterToBe("aabbcdaaaadcbb", 4, false) && pass;
        pass = expectRepeatsPreviousCharacterToBe("aabbcdaaaadcbb", 1, true) && pass;
        if (!pass) {
            String errorMessage = "\nThe repeatsPreviousCharacter() method failed. Test aborted.";
            fail(errorMessage);
        }

        System.out.println("\nTesting firstRepeatedCharacter()...");
        pass = expectFirstRepeatToBe("aa", 'a');
        pass = expectFirstRepeatToBe("roommate", 'o') && pass;
        pass = expectFirstRepeatToBe("aardvark", 'a') && pass;
        pass = expectFirstRepeatToBe("mate", noneFound) && pass;
        pass = expectFirstRepeatToBe("test", noneFound) && pass;
        if (!pass) {
            String errorMessage = "\nThe firstRepeatedCharacter() method failed. Test aborted.";
            fail(errorMessage);
        }

        System.out.println("\nTesting firstMultipleCharacter()...");
        pass = expectFirstMultipleToBe("aa", 'a');
        pass = expectFirstMultipleToBe("mississippi", 'i') && pass;
        pass = expectFirstMultipleToBe("mate", noneFound) && pass;
        pass = expectFirstMultipleToBe("test", 't') && pass;
        if (!pass) {
            String errorMessage = "\nThe firstMultipleCharacter() method failed. Test aborted.";
            fail(errorMessage);
        }

        System.out.println("\nTesting countRepeatedCharacters()...");
        pass = expectRepeatCountToBe("mississippiii", 4);
        pass = expectRepeatCountToBe("test", 0) && pass;
        pass = expectRepeatCountToBe("aabbcdaaaadcbb", 4) && pass;
        if (!pass) {
            String errorMessage = "\nThe countRepeatedCharacters() method failed. Test aborted.";
            fail(errorMessage);
        }
    }
    @Test
    public void wordAnalyzer_findFunction_hasNotBeenAltered() {
        // The Class Exists
        Class<?> wordAnalyzerClass = assertDoesNotThrow( () -> ClassQuery.inContainingPackage(BASE_PACKAGE)
                .withExactSimpleName("WordAnalyzer")
                .findClassOrFail(), "The WordAnalyzer class must exist before this test will pass.");

        Constructor constructor = assertDoesNotThrow( () -> ConstructorQuery.inClass(wordAnalyzerClass)
                .withExactArgTypes(Arrays.asList(String.class))
                .findConstructor(), "The WordAnalyzer Constructor with a single word parameter was not found. Please double check your WordAnalyzer Constructor is configured correctly.");

        Method findMethod = assertDoesNotThrow( () -> MethodQuery.inType(wordAnalyzerClass)
                .withExactName("findWrapper")
                .findMethodOrFail(), "The method find was not found in the WordAnalyzer Class");

        Object wordAnalyzerFirstLetterClassObject = null;
        Object wordAnalyzerLastLetterClassObject = null;

        try {
            wordAnalyzerFirstLetterClassObject = constructor.newInstance("kenzieacademy");
            wordAnalyzerLastLetterClassObject = constructor.newInstance("kenzieacademy");
        } catch (Exception e) {
            try {
                throw new InstantiationException("Cannot instantiate wordAnalyzerClassObject.");
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }

        String letterToTest1 = "k";
        String letterToTest2 = "y";

        char letterToTest1Char = letterToTest1.charAt(0);
        char letterToTest2Char = letterToTest2.charAt(0);

        int startIndex = 0;

        Object foundStartCharacter = MethodInvoker.invokeInstanceMethodWithReturnValue(wordAnalyzerFirstLetterClassObject, findMethod, letterToTest1Char, startIndex);

        Object foundEndCharacter = MethodInvoker.invokeInstanceMethodWithReturnValue(wordAnalyzerLastLetterClassObject, findMethod, letterToTest2Char, startIndex);

        assertEquals(foundStartCharacter.toString(), "0");
        assertEquals(foundEndCharacter.toString(), "12");
    }

    @Test
    public void wordAnalyzer_repeatsPreviousCharacterFunction_hasNotBeenAltered() {
        // The Class Exists
        Class<?> wordAnalyzerClass = assertDoesNotThrow( () -> ClassQuery.inContainingPackage(BASE_PACKAGE)
                .withExactSimpleName("WordAnalyzer")
                .findClassOrFail(), "The WordAnalyzer class must exist before this test will pass.");

        Constructor constructor = assertDoesNotThrow( () -> ConstructorQuery.inClass(wordAnalyzerClass)
                .withExactArgTypes(Arrays.asList(String.class))
                .findConstructor(), "The WordAnalyzer Constructor with a single word parameter was not found. Please double check your WordAnalyzer Constructor is configured correctly.");

        Method repeatsPreviousCharacterMethod = assertDoesNotThrow( () -> MethodQuery.inType(wordAnalyzerClass)
                .withExactName("repeatsPreviousCharacterWrapper")
                .findMethodOrFail(), "The method repeatsPreviousCharacter was not found in the WordAnalyzer Class");

        Object wordAnalyzerClassObject = null;

        try {
            wordAnalyzerClassObject = constructor.newInstance("aaa");
        } catch (Exception e) {
            try {
                throw new InstantiationException("Cannot instantiate wordAnalyzerClassObject.");
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }

        int index = 1;

        Object repeatsPreviousValue = MethodInvoker.invokeInstanceMethodWithReturnValue(wordAnalyzerClassObject, repeatsPreviousCharacterMethod, index);

        assertEquals(repeatsPreviousValue.toString(), "true");
    }
}
