package com.example.languagelearningapp.Singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void databaseObjectShouldNotBeNull() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        assertNotNull(databaseConnection);
    }
}