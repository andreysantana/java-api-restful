package br.com.jaxrs.texoit.utils;

import java.lang.reflect.Type;
import java.util.List;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;

@Service
public class JustInTimeServiceResolver implements JustInTimeInjectionResolver {

    @Inject
    private ServiceLocator serviceLocator;

    @Override
    public boolean justInTimeResolution(Injectee injectee) {
        final Type requiredType = injectee.getRequiredType();

        if (injectee.getRequiredQualifiers().isEmpty() && requiredType instanceof Class) {
            final Class<?> requiredClass = (Class<?>) requiredType;
            if (requiredClass.getName().startsWith("br.com.jaxrs.texoit")) {
                final List<ActiveDescriptor<?>> descriptors = ServiceLocatorUtilities.addClasses(serviceLocator, requiredClass);

                return !descriptors.isEmpty();
            }
        }
        return false;
    }
}
