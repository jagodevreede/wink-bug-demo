package org.example.configuration.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.example.configuration.api.ConfigurationService;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public class ConfigurationServiceImpl implements ConfigurationService {
    private volatile ConfigurationAdmin m_configurationAdmin;
    private final List<Configuration> m_configurations = new ArrayList<>();
    
    /** This is called when the bundle is stopped, we need to clear any configurations left. */
    public void stop() {
        reset();
    }
    
    @Override
    public void configure(String pid, String... props) {
        Properties properties = properties((Object[]) props);
        Configuration config;
        try {
            config = getConfiguration(pid);
            Dictionary<String, Object> dict = propertiesToDictionary(properties);
            config.update(dict);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties properties(Object... values) {
        Properties props = new Properties();
        for (int i = 0; i < values.length; i += 2) {
            if (values[i + 1] != null) {
                props.put(values[i], values[i + 1]);
            }
        }
        return props;
    }
    
    private Configuration getConfiguration(String pid) throws IOException {
        return m_configurationAdmin.getConfiguration(pid, null);
    }

    private Dictionary<String, Object> propertiesToDictionary(Properties properties) {
        Dictionary<String, Object> dict = new Hashtable<>();
        for(Object key : properties.keySet()) {
            dict.put((String)key, properties.get(key));
        }
        return dict;
    }

    @Override
    public void reset() {
        for (Configuration config : m_configurations) {
            try {
                config.delete();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        m_configurations.clear();
    }
}
