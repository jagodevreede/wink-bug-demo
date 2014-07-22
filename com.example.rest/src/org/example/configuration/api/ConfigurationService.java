package org.example.configuration.api;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface ConfigurationService {
    
    void configure(String pid, String... props);
    /** Removes any old factories */
    void reset();
}
