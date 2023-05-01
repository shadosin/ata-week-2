package com.kenzie.subscribeandsave.dao;

import com.kenzie.subscribeandsave.App;
import com.kenzie.subscribeandsave.types.Subscription;
import com.kenzie.subscribeandsave.util.SubscriptionRestorer;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class SubscriptionDAOTest {

    private static final String ASIN = "B01BMDAVIY";
    private static final String CUSTOMER_ID = "amzn1.account.AEZI3A06339413S37ZHKJQUEGLC4";
    private static final String SUBSCRIPTION_ID = "81a9792e-9b4c-4090-aac8-28e733ac2f54";

    private SubscriptionDAO classUnderTest;

    /**
     * The entry point, which results in calls to all test methods.
     *
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        SubscriptionDAOTest tester = new SubscriptionDAOTest();

        // clean up subscriptions before/after running runAllTests(), for when tests are invoked via main(),
        // rather than through a build
        tester.restoreSubscriptions();
        tester.runAllTests();
        tester.restoreSubscriptions();
    }

    @Test
    public void runAllTests() {
        classUnderTest = new SubscriptionDAO(App.getSubscriptionFileStorage());
        boolean pass = true;

        pass = createSubscription_newSubscription_subscriptionReturned();
        pass = getSubscription_existingSubscription_subscriptionReturned() && pass;
        pass = getSubscription_subscriptionDoesNotExist_nullReturned() && pass;

        if (!pass) {
            String errorMessage = "\n/!\\ /!\\ /!\\ The SubscriptionDAOTest tests failed. Test aborted. /!\\ /!\\ /!\\";
            System.out.println(errorMessage);
            fail(errorMessage);
        } else {
            System.out.println("The SubscriptionDAOTest tests passed!");
        }
    }

    public boolean getSubscription_existingSubscription_subscriptionReturned() {
        // GIVEN - a valid subscriptionId
        String subscriptionId = SUBSCRIPTION_ID;

        // WHEN - get the corresponding subscription
        Subscription result = classUnderTest.getSubscription(subscriptionId);

        // THEN - a subscription object is returned, with a matching id
        if (result == null) {
            System.out.println("   FAIL: Getting a subscription for a valid id should return the subscription.");
            return false;
        }
        if (!subscriptionId.equals(result.getId())) {
            System.out.println("   FAIL: Subscription returned when getting subscription by id has mismatching id " +
                                   "value");
            return false;
        }

        System.out.println("  PASS: Getting a subscription for a valid id succeeded.");
        return true;
    }

    public boolean getSubscription_subscriptionDoesNotExist_nullReturned() {
        // GIVEN - an invalid subscriptionId
        String subscriptionId = "123456789";

        // WHEN - get the corresponding subscription
        Subscription result = classUnderTest.getSubscription(subscriptionId);

        // THEN - a subscription object is not returned, null is
        if (result != null) {
            System.out.println("   FAIL: Getting a non existent subscription should reutrn null.");
            return false;
        }

        System.out.println("  PASS: Getting a subscription for an invalid id worked as expected.");
        return true;
    }

    public boolean createSubscription_newSubscription_subscriptionReturned() {
        // GIVEN - a customerId to make a subscription for, asin to subscribe to, and the frequency to receive
        // subscription
        String customerId = CUSTOMER_ID;
        String asin = ASIN;
        int frequency = 1;

        // WHEN - create a new subscription
        Subscription result = classUnderTest.createSubscription(customerId, asin, frequency);

        // THEN a subscription should be returned and the id field should be populated
        if (result == null) {
            System.out.println("   FAIL: Creating subscription should return the subscription.");
            return false;
        }
        if (StringUtils.isBlank(result.getId())) {
            System.out.println("   FAIL: Creating subscription should return a subscription with a populated id " +
                                   "field.");
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
