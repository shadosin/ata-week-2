package com.kenzie.subscribeandsave;

import com.kenzie.subscribeandsave.dao.SubscriptionDAO;
import com.kenzie.subscribeandsave.dao.SubscriptionFileStorage;
import com.kenzie.subscribeandsave.resources.AmazonIdentityService;
import com.kenzie.subscribeandsave.resources.AmazonProductService;
import com.kenzie.subscribeandsave.service.SubscriptionService;

import java.io.File;

/**
 * Provides inversion of control for the SNS MLP by instantiating all of the
 * dependencies needed by the SubscriptionDebugUtil and its dependency classes.
 */
public class App {

    /**
     * Returns a product service.
     *
     * @return Product service usable for fetching products by ASIN
     */
    public static AmazonProductService getAmazonProductService() {
        return new AmazonProductService(
                new File(ClassLoader.getSystemResource("catalog.json").getPath()));
    }

    /**
     * Returns an identity service.
     *
     * @return Identity service usable for fetching customers by ID
     */
    public static AmazonIdentityService getAmazonIdentityService() {
        return new AmazonIdentityService(
                new File(ClassLoader.getSystemResource("customers.txt").getPath()));
    }

    /**
     * Gets a subscription DAO.
     *
     * @return A subscription DAO for reading/writing subscriptions
     */
    public static SubscriptionDAO getSubscriptionDAO() {
        return new SubscriptionDAO(getSubscriptionFileStorage());
    }

    /**
     * Gets a subscription file storage manager.
     *
     * @return A subscription file data store
     */
    public static SubscriptionFileStorage getSubscriptionFileStorage() {
        return new SubscriptionFileStorage(new File(ClassLoader.getSystemResource("subscriptions.csv").getPath()));
    }

    /**
     * Gets subscription service.
     *
     * @return the subscription service
     */
    public static SubscriptionService getSubscriptionService() {
        return new SubscriptionService(getAmazonIdentityService(), getSubscriptionDAO(), getAmazonProductService());
    }
}
