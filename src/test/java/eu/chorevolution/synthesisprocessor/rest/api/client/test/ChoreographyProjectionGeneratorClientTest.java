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

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.ChoreographyProjectionGeneratorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;

public class ChoreographyProjectionGeneratorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "ChoreographyProjectionGenerator"
			+ File.separatorChar;

	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;
	
	private static final String BPMN2_FILE_EXTENSION = ".bpmn2";
	
	private static Logger logger = LoggerFactory.getLogger(ChoreographyProjectionGeneratorClientTest.class);

	private boolean exceptionDetected;
	private ChoreographyProjectionGeneratorClient client;

	@Before
	public void setUp() {
		exceptionDetected = false;
		client = new ChoreographyProjectionGeneratorClient(
				Configuration.get(Configuration.APPLICATION_BASE_URI)+"choreographyprojectiongenerator/");
	}

	@Test
	public void generateChoreographyProjection() {
		try {
			ChoreographyProjectionGeneratorRequest request = new ChoreographyProjectionGeneratorRequest();
			request.setBpmn2Content(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "wp5" + BPMN2_FILE_EXTENSION)));
			request.setParticipant("Trip Planner");
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());

			ChoreographyProjectionGeneratorResponse response = client.generateChoreographyProjection(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "wp5_tripplanner" + BPMN2_FILE_EXTENSION), response.getBpmn2Content());
		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("generate choreography projection error ", e);
		}
		
		Assert.assertFalse(exceptionDetected);
	}

}
