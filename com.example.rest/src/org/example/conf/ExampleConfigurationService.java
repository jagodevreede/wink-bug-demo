/*******************************************************************************
 * Copyright (c) 2014 Lopexs.
 * All rights reserved.
 ******************************************************************************/
package org.example.conf;

import org.example.configuration.api.ConfigurationService;

public class ExampleConfigurationService {
    private volatile ConfigurationService m_configuration;

    public void start() {
        // Start with a clean slate
        m_configuration.reset();
       
        m_configuration.configure("org.amdatu.web.rest.wink",
            "wink.rest.path", "/rest"
            );
    }
    
    public void stop() {
        m_configuration.reset();
    }
    
}
