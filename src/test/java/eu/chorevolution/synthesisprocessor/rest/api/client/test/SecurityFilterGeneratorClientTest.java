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
package eu.chorevolution.synthesisprocessor.rest.api.client.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.chorevolution.synthesisprocessor.rest.api.ConnectionAccountType;
import eu.chorevolution.synthesisprocessor.rest.api.SecurityFilterGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.SecurityFilterGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.SecurityFilterGeneratorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.SynthesisProcessorClientUtils;

public class SecurityFilterGeneratorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "SecurityFilterGenerator" + File.separatorChar;

	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;

	private static Logger logger = LoggerFactory.getLogger(SecurityFilterGeneratorClientTest.class);

	private boolean exceptionDetected;
	private SecurityFilterGeneratorClient securityFilterGeneratorClient;

	@Before
	public void setUp() {
		exceptionDetected = false;
		securityFilterGeneratorClient = new SecurityFilterGeneratorClient(Configuration.get(Configuration.APPLICATION_BASE_URI)+"securityfiltergenerator/");
	}

	@Test
	public void generateSecurityFilterProviderWithConnectionAccount() {
		try {

			// generate security filter provider with connection account
			SecurityFilterGeneratorRequest request = new SecurityFilterGeneratorRequest("wp5", "sfPoi", "Master",
					new ArrayList<String>(Arrays.asList("role_one", "role_two")),
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "poi.security")),
					ConnectionAccountType.USERNAME_PASSWORD, "ciao", "ciao", null, SynthesisProcessorEncrypter.getDefaultToken());
			SecurityFilterGeneratorResponse response = securityFilterGeneratorClient.generateSecurityFilterProvider(request);
			
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + response.getName() + ".war"), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));

		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("generate security filter provider with connection account error ", e);
		}

		Assert.assertFalse(exceptionDetected);
	}
	
	@Test
	public void generateSecurityFilterProviderWithoutConnectionAccount() {
		try {
			// generate security filter provider without connection account
			SecurityFilterGeneratorRequest request = new SecurityFilterGeneratorRequest("wp5", "sfParking", "Master",
					new ArrayList<String>(Arrays.asList("role_one", "role_two")),
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "parking.security")), null, null, null, null, SynthesisProcessorEncrypter.getDefaultToken());
			SecurityFilterGeneratorResponse response = securityFilterGeneratorClient.generateSecurityFilterProvider(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + response.getName() + ".war"),
					SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));

		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("generate security filter provider without connection account error ", e);
		}

		Assert.assertFalse(exceptionDetected);
	}
	
	@Test
	public void generateSecurityFilterClient() {
		try {
			// generate security filter client
			SecurityFilterGeneratorRequest request = new SecurityFilterGeneratorRequest("wp5", "sfSTApp", "Master",
					new ArrayList<String>(Arrays.asList("role_one", "role_two")), SynthesisProcessorEncrypter.getDefaultToken());
			SecurityFilterGeneratorResponse response = securityFilterGeneratorClient.generateSecurityFilterClient(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + response.getName() + ".war"),
					SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));

		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("generate security filter client error ", e);
		}

		Assert.assertFalse(exceptionDetected);
	}
	

}
