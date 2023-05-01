package com.kenzie.subscribeandsave.dao;


import com.kenzie.subscribeandsave.App;
import com.kenzie.subscribeandsave.types.Subscription;
import com.kenzie.subscribeandsave.util.SubscriptionRestorer;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class SubscriptionFileStorageTest {

    private static final String ASIN = "B01BMDAVIY";
    private static final String CUSTOMER_ID = "amzn1.account.AEZI3A063427738YROOFT8WCXKDE";

    private SubscriptionFileStorage classUnderTest;

    /**
     * The entry point, which results in calls to all test methods.
     *
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        SubscriptionFileStorageTest tester = new SubscriptionFileStorageTest();

        // clean up subscriptions before/after running runAllTests(), for when tests are invoked via main(),
        // rather than through a build
        tester.restoreSubscriptions();
        tester.runAllTests();
        tester.restoreSubscriptions();
    }

    @Test
    public void runAllTests() {
        classUnderTest = App.getSubscriptionFileStorage();
        boolean pass = true;

        pass = writeSubscription_newSubscription_SubscriptionReturnedWithId();

        if (!pass) {
            String errorMessage = "\n/!\\ /!\\ /!\\ The SubscriptionFileStorage tests failed. Test aborted. /!\\ /!\\ /!\\";
            System.out.println(errorMessage);
            fail(errorMessage);
        } else {
            System.out.println("The SubscriptionFileStorage tests passed!");
        }
    }

    public boolean writeSubscription_newSubscription_SubscriptionReturnedWithId() {
        // GIVEN - a new subscription to save
        Subscription newSubscription = Subscription.builder()
                                                   .withAsin(ASIN)
                                                   .withCustomerId(CUSTOMER_ID)
                                                   .withFrequency(1)
                                                   .build();

        // WHEN - create a new subscription
        Subscription result = classUnderTest.writeSubscription(newSubscription);

        // THEN a subscription should be returned and the id field should be populated
        if (result == null) {
            System.out.println("   FAIL: Writing subscription should return the subscription.");
            return false;
        }
        if (StringUtils.isBlank(result.getId())) {
            System.out.println("   FAIL: Writing subscription should return a subscription with an id field.");
            return false;
        }

        System.out.println("  PASS: Creating a new subscription succeeded.");
        return true;
    }

    @BeforeEach
    @AfterEach
    public void restoreSubscriptions() {
        SubscriptionRestorer.restoreSubscriptions();
    }
}
