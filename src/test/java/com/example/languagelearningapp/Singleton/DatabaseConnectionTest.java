package com.example.languagelearningapp.Singleton;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void databaseObjectShouldNotBeNull() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        assertNotNull(databaseConnection);
    }

    @Test
    void shouldReturnTheSameInstance() {
        DatabaseConnection databaseConnection1 = DatabaseConnection.getInstance();
        DatabaseConnection databaseConnection2 = DatabaseConnection.getInstance();
        assertEquals(databaseConnection1, databaseConnection2);
    }

    @Test
    void constructorShouldBePrivate() {
        try {
            Constructor<DatabaseConnection> constructor = DatabaseConnection.class.getDeclaredConstructor();

            assertThrows(IllegalAccessException.class, constructor::newInstance, "Constructor is accessible");
        } catch (NoSuchMethodException e) {
            fail("Constructor not found");
        }
    }
}