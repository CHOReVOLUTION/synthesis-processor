/*
* Copyright 2015 The CHOReVOLUTION project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package eu.chorevolution.synthesisprocessor.rest.api.client.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	private static String PROPERTIES_FILE = "configuration.properties";

	private static Configuration INSTANCE = new Configuration();

	private final Properties properties = new Properties();
	
	public static final String ARTIFACT_HOME_REPOSITORY = "ARTIFACT_HOME_REPOSITORY";
	public static final String APPLICATION_BASE_URI = "APPLICATION_BASE_URI";
	public static final String BC_GENERATOR_IMPL = "BC_GENERATOR_IMPL";
	public static final String SF_GENERATOR_IMPL = "SF_GENERATOR_IMPL";

	private Properties getProperties() {
		return properties;
	}

	public static String get(String key) {
		if (key == null)
			throw new IllegalArgumentException();
		String value = INSTANCE.getProperties().getProperty(key);
		if (value != null)
			value.trim();
		return value;

	}

	public static void set(String key, String value) {
		if (key == null)
			throw new IllegalArgumentException();
		INSTANCE.getProperties().setProperty(key, value);
	}

	private Configuration() {

		try {
			final ClassLoader loader = this.getClass().getClassLoader();
			final InputStream propFile = loader.getResourceAsStream(PROPERTIES_FILE);
			if (propFile != null) {
				properties.load(propFile);
				propFile.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
