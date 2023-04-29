package com.ethnicthv.webprojectcnpmrestful;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected @NotNull String getDatabaseName() {
        return "EthnicTHV";
    }

    @Bean
    @Override
    public @NotNull MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://root:1234@ethnicthv.1rac17z.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }
}
