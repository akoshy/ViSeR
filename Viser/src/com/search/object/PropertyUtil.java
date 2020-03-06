package com.search.object;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	static Properties propertyMap = new Properties();

	public static Properties getPropertyMap() throws IOException {
		return formPropertyMap();
	}

	public static Properties formPropertyMap() throws IOException {
		if (propertyMap.size() == 0) {
			propertyMap.load(PropertyUtil.class.getResourceAsStream("/resources/appConfig.properties"));
		}
		return propertyMap;
	}
}
