package org.example.configuration.impl;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.example.configuration.api.ConfigurationService;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationAdmin;

public class Activator extends DependencyActivatorBase {
    @Override
    public synchronized void init(BundleContext context, DependencyManager manager) throws Exception {
        manager.add(createComponent()
            .setInterface(ConfigurationService.class.getName(), null)
            .setImplementation(ConfigurationServiceImpl.class)
            .add(createServiceDependency().setService(ConfigurationAdmin.class).setRequired(true))
            );
    }

    @Override
    public synchronized void destroy(BundleContext context, DependencyManager manager) throws Exception {
    }
}