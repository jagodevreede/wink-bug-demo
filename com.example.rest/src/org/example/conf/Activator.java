/*******************************************************************************
 * Copyright (c) 2014 Lopexs.
 * All rights reserved.
 ******************************************************************************/
package org.example.conf;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.example.configuration.api.ConfigurationService;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;

public class Activator extends DependencyActivatorBase {
    @Override
    public synchronized void init(BundleContext context, DependencyManager manager) throws Exception {
        manager.add(createComponent()
            .setInterface(Object.class.getName(), null)
            .setImplementation(ExampleConfigurationService.class)
            .add(createServiceDependency().setService(ConfigurationService.class).setRequired(true))
            .add(createServiceDependency().setService(LogService.class).setRequired(false))
            );
    }

    @Override
    public synchronized void destroy(BundleContext context, DependencyManager manager) throws Exception {
    }
}