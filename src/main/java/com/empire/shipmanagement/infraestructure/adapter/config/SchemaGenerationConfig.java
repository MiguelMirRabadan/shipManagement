package com.empire.shipmanagement.infraestructure.adapter.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import java.io.File;

@Configuration
@Log4j2
public class SchemaGenerationConfig {

    private static final String SCRIPT_PATH = "src/main/resources/sql/db/schema.sql";

    @PostConstruct
    public void deleteExistingScript() {
        File scriptFile = new File(SCRIPT_PATH);
        if (scriptFile.exists()) {
            if (scriptFile.delete()) {
                log.debug("Deleted existing script file: " + SCRIPT_PATH);
            } else {
                log.error("Failed to delete existing script file: " + SCRIPT_PATH);
            }
        }
    }
}
