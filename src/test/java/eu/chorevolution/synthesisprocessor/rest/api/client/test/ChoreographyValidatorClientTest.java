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

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.ChoreographyValidatorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;

public class ChoreographyValidatorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "ChoreographyValidator"
			+ File.separatorChar;

	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;
	
	private static final String BPMN2_FILE_EXTENSION = ".bpmn2";
	private static final String XSD_FILE_EXTENSION = ".xsd";
	private static final String TXT_FILE_EXTENSION = ".txt";
	
	private static Logger logger = LoggerFactory.getLogger(ChoreographyValidatorClientTest.class);

	private boolean exceptionDetected;
	private ChoreographyValidatorClient client;

	@Before
	public void setUp() {
		exceptionDetected = false;
		client = new ChoreographyValidatorClient(
				Configuration.get(Configuration.APPLICATION_BASE_URI)+"choreographyvalidator/");
	}

	@Test
	public void validateChoreography() {
		try {
			ChoreographyValidatorRequest request = new ChoreographyValidatorRequest();
			request.setBpmn2Content(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "wp5" + BPMN2_FILE_EXTENSION)));
			request.setTypesContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "types" + XSD_FILE_EXTENSION)));		
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());

			ChoreographyValidatorResponse response = client.validateChoreography(request);
			if (!response.isChoreographyValidated()){
				FileUtils.writeLines(new File(OUTPUT_TEST_RESOURCES + "errors" + TXT_FILE_EXTENSION), response.getErrors());
				exceptionDetected = true;
				logger.error("validate choreography error: see the " + OUTPUT_TEST_RESOURCES);
			}
		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("validate choreography error ", e);
		}
		
		Assert.assertFalse(exceptionDetected);
	}

}
