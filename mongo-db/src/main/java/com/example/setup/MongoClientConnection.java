package com.example.setup;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author rahul
 */
public class MongoClientConnection {

    private static final String APPLICATION_PROPERTIES = "application.properties";

    private static final String URI = "spring.data.mongodb.uri";

    private static final String ADMIN = "admin";

    private static final String PING = "ping";

    private static Properties loadProperties() {
        Properties config = new Properties();
        try (InputStream inputStream = MongoClientConnection.class
                .getClassLoader()
                .getResourceAsStream(APPLICATION_PROPERTIES)) {
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void main(String[] args) {
        String connectionString = loadProperties()
                .getProperty(URI);

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase(ADMIN);
                database.runCommand(new Document(PING, 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}

