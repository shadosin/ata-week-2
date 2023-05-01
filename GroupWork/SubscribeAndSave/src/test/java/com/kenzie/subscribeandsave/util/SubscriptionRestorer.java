package com.kenzie.subscribeandsave.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Restores subscriptions to the original state between/after tests.
 */
public class SubscriptionRestorer {
    /**
     * Restores the subscriptions data to the same state before/after every test.
     */
    public static void restoreSubscriptions() {
        try {
            Path source = Path.of(ClassLoader.getSystemResource("subscriptions.csv.restore").toURI());
            Path dest = Path.of(ClassLoader.getSystemResource("subscriptions.csv").toURI());
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (URISyntaxException | IOException e) {
            System.out.println(
                    String.format("Error restoring subscriptions data file '%s' from original '%s': %s",
                            "subscription.csv",
                            "subscription.restore.csv",
                            e)
            );
        }
    }
}
