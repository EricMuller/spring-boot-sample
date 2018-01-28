package com.emu.apps.qcm.services.repositories.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConfigurationProperties(prefix = "spring.jpa.properties.hibernate.jdbc")
@EnableJpaRepositories(
        basePackages = "com.emu.apps.qcm.services.repositories",
        repositoryBaseClass = SimpleJpaBulkRepositoryImpl.class

)
public class RepositoryConfiguration {
    private int batchSize;

    public int getBatchSize() {
        return batchSize;
    }

}

