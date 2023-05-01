package com.kenzie.subscribeandsave;

import com.kenzie.subscribeandsave.resources.ATAUserHandler;
import com.kenzie.subscribeandsave.service.SubscriptionService;
import com.kenzie.subscribeandsave.types.Subscription;

/**
 * This utility tool was created to help debug the SubscriptionService. You can exercise its methods.
 */
public class SubscriptionDebugUtil {

    private static final String SUBSCRIBE_COMMAND = "SUBSCRIBE";
    private static final String QUERY_COMMAND = "QUERY";
    private static final String QUIT_COMMAND = "QUIT";

    private SubscriptionService service;
    private ATAUserHandler inputHandler;

    /**
     * Construct a new SubscriptionDebugUtil.
     *
     * @param service      SubscriptionService to make requests against
     * @param inputHandler ATAUserHandler to get user input for requests
     */
    public SubscriptionDebugUtil(SubscriptionService service, ATAUserHandler inputHandler) {
        this.service = service;
        this.inputHandler = inputHandler;
    }

    /**
     * Main method to run debug requests on this SubscriptionDebugUtil.
     *
     * @param args Java boilerplate args, unused
     */
    public static void main(String[] args) {
        SubscriptionDebugUtil util = new SubscriptionDebugUtil(App.getSubscriptionService(), new ATAUserHandler());

        util.printUsage();
        String command = util.inputHandler.getString("\nWhat would you like to do? Please type a command.");
        while (!command.equalsIgnoreCase(QUIT_COMMAND)) {
            util.debug(command);
            command = util.inputHandler.getString("What would you like to do? Please type a command.");
        }

        System.out.println("Thank you.");
    }

    /**
     * Depending on command input, exercise one of the subscription service's methods.
     *
     * @param command A known command, otherwise user is informed the command is unknown
     */
    private void debug(String command) {
        if (command.equalsIgnoreCase(SUBSCRIBE_COMMAND)) {
            debugSubscribe();
        } else if (command.equalsIgnoreCase(QUERY_COMMAND)) {
            debugGet();
        } else {
            System.out.println("Unknown command. Please try again.");
        }
    }

    private void debugGet() {
        String subscriptionId = inputHandler.getString("Please provide the subscription Id: ");

        Subscription subscription = service.getSubscription(subscriptionId);
        System.out.println("\nSubscription found: " + subscription);
    }

    private void debugSubscribe() {
        String customerId = inputHandler.getString("Please provide the customer Id: ");
        String asin = inputHandler.getString("Please provide the asin: ");
        int frequency = inputHandler.getInteger("Please provide the frequency (every 1 - 6 months): ");
        Subscription subscription = service.subscribe(customerId, asin, frequency);
        System.out.println("Subscription created: " + subscription);
    }

    private void printUsage() {
        System.out.println("Welcome to the SNS debug util. You can create and look up subscriptions here.");
        System.out.println(String.format("Enter %s to create a subscription.", SUBSCRIBE_COMMAND));
        System.out.println(String.format("Enter %s to look up a subscriptions by its id.", QUERY_COMMAND));
        System.out.println(String.format("Enter %s to exit the debug util.", QUIT_COMMAND));
    }
}
