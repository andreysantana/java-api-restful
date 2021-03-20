package br.com.jaxrs.texoit.utils;

import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
	@Override
	protected void configure() {
		bind(JustInTimeServiceResolver.class).to(JustInTimeInjectionResolver.class);
	}
}
