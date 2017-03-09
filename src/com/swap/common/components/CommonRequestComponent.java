package com.swap.common.components;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
public class CommonRequestComponent {

	private Locale locale = Locale.getDefault();
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
