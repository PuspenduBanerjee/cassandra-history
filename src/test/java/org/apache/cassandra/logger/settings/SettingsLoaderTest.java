package org.apache.cassandra.logger.settings;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SettingsLoaderTest {
    
    @Test
    public void loadValidSettings() throws IOException {
        Settings settings = SettingsLoader.load("org/apache/cassandra/logger/settings/ValidSettings.properties");
        assertThat(settings.getLogKeyspace(), is("test_logger"));
        assertThat(settings.getLogColumnFamily(), is("test_log"));
    }

    @Test
    public void useDefaultLogColumnFamilyIfNotProvidedInSettingsFile() throws IOException {
        Settings settings = SettingsLoader.load("org/apache/cassandra/logger/settings/SettingsWithoutLogColumnFamily.properties");
        assertThat(settings.getLogKeyspace(), is("test_logger"));
        assertThat(settings.getLogColumnFamily(), is("log"));
    }

    @Test
    public void useDefaultLogKeyspaceIfNotProvidedInSettingsFile() throws IOException {
        Settings settings = SettingsLoader.load("org/apache/cassandra/logger/settings/SettingsWithoutLogKeyspace.properties");
        assertThat(settings.getLogKeyspace(), is("logger"));
        assertThat(settings.getLogColumnFamily(), is("test_log"));
    }
    
    @Test(expected = IOException.class)
    public void failIfNoSettingsFileFound() throws IOException {
        SettingsLoader.load("Nonexistent.properties");
    }
}