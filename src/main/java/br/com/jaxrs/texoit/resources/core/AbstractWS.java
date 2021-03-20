package br.com.jaxrs.texoit.resources.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.research.ws.wadl.Application;

public abstract class AbstractWS extends Application {
	protected Gson gSon;
	
	public Gson getGson() {
		if (gSon == null) {
			gSon = new GsonBuilder()
					.serializeNulls()
					.create();
		}
		
		return gSon;
	}
	
	public String convertToJson(Object obj) {
		gSon = getGson();
		String json = gSon.toJson(obj);
		return json;
	}
}
