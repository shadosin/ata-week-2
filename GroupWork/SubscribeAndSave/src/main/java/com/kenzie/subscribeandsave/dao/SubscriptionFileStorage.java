package com.kenzie.subscribeandsave.dao;

import com.kenzie.subscribeandsave.types.Subscription;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

/**
 * Subscription data store that is file based.
 */
public class SubscriptionFileStorage {

    private File subscriptionsFile;

    /**
     * Creates a {@code SubscriptionFileStorage} using the specified file for reading/writing subscriptions.
     *
     * @param subscriptionsFile The subscription {@code File} to use
     */
    public SubscriptionFileStorage(File subscriptionsFile) {
        this.subscriptionsFile = subscriptionsFile;
    }

    /**
     * Creates a new subscription.
     * <p>
     * Throws {@code StorageException} if the subscription already exists or if an input/output error occurs.
     *
     * @param subscription the subscription to store
     * @return The subscription that was written
     */
    public Subscription writeSubscription(Subscription subscription) {
        Subscription existingSubscription = getSubscription(subscription.getCustomerId(), subscription.getAsin());

        if (existingSubscription != null) {
            throw new StorageException(
                    String.format("Subscription already exists: %s. The update API is coming soon! " +
                            "Please cut a ticket for this to be done manually.", existingSubscription));
        }

        String id = UUID.randomUUID().toString();
        subscription.setId(id);


        StringBuilder sb = new StringBuilder();
        sb.append(subscription.getId()).append(",");
        sb.append(subscription.getCustomerId()).append(",");
        sb.append(subscription.getAsin()).append(",");
        sb.append(subscription.getFrequency());
        sb.append("\n");

        try {
            FileUtils.writeStringToFile(subscriptionsFile, sb.toString(), Charset.defaultCharset(), true);
        } catch (IOException e) {
            throw new StorageException("Unable to save subscription.", e);
        }

        return subscription;
    }

    /**
     * Updates an existing subscription.
     * <p>
     * Throws {@code IllegalArgumentException} if the {@code Subscription} is null, missing an ID or if no
     * subscription is found for that ID.
     * <p>
     * Throws {@code StorageException} if an error occurs trying to write the updated record.
     *
     * @param subscriptionId The {@code Subscription} to update (must already have a subscription ID)
     * @return the {@code Subscription} if writing succeeded
     */
    public Subscription getSubscriptionById(String subscriptionId) {
        String[] lines = readSubscriptionsFile();
        for (String subscriptionLine : lines) {
            String[] subscriptionData = subscriptionLine.split(",");
            String id = subscriptionData[0].trim();
            if (subscriptionId.equals(id)) {
                Subscription subscription = Subscription.builder()
                        .withSubscriptionId(id)
                        .withAsin(subscriptionData[1])
                        .withCustomerId(subscriptionData[2])
                        .withFrequency(Integer.parseInt(subscriptionData[3]))
                        .build();
                return subscription;
            }
        }

        return null;
    }

    private Subscription getSubscription(String customerId, String asin) {
        String[] lines = readSubscriptionsFile();
        for (String subscriptionLine : lines) {
            String[] subscriptionData = subscriptionLine.split(",");

            String currentCustomerId = subscriptionData[1].trim();
            String currentAsin = subscriptionData[2].trim();

            if (customerId.equals(currentCustomerId) && asin.equals(currentAsin)) {
                Subscription subscription = Subscription.builder()
                        .withSubscriptionId(subscriptionData[0].trim())
                        .withAsin(currentAsin)
                        .withCustomerId(currentCustomerId)
                        .withFrequency(Integer.parseInt(subscriptionData[3].trim()))
                        .build();
                return subscription;
            }
        }

        return null;
    }

    private String[] readSubscriptionsFile() {
        try {
            List<String> lines = FileUtils.readLines(subscriptionsFile, Charset.defaultCharset());
            return lines.toArray(new String[lines.size()]);
        } catch (IOException e) {
            throw new StorageException("Unable to access subscription data.", e);
        }
    }
}
