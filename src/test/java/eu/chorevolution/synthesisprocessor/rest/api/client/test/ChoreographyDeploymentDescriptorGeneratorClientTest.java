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

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyDeploymentDescriptorGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyDeploymentDescriptorGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.ChoreographyDeploymentDescriptorGeneratorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;

public class ChoreographyDeploymentDescriptorGeneratorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "ChoreographyDeploymentDescriptorGenerator"
			+ File.separatorChar;

	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;
	private static final String CHOREOARCH_FILE_EXTENSION = ".chorarch";
	private static final String CHOREOGRAPHY_DEPLOYMENT_DESCRIPTION_FILE_EXTENSION = ".xml";

	private static Logger logger = LoggerFactory.getLogger(ChoreographyDeploymentDescriptorGeneratorClientTest.class);

	private boolean exceptionDetected;
	private ChoreographyDeploymentDescriptorGeneratorClient client;

	@Before
	public void setUp() {
		exceptionDetected = false;
		client = new ChoreographyDeploymentDescriptorGeneratorClient(
				Configuration.get(Configuration.APPLICATION_BASE_URI)+"choreographydeploymentdescriptorgenerator/");
	}

	@Test
	public void getChoreographyDeploymentDescriptorContent() {
		try {
			ChoreographyDeploymentDescriptorGeneratorRequest request = new ChoreographyDeploymentDescriptorGeneratorRequest();
			request.setChoreographyArchitectureContent(
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "wp5" + CHOREOARCH_FILE_EXTENSION)));
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());

			
			ChoreographyDeploymentDescriptorGeneratorResponse response = client
					.generateChoreographyDeploymentDescriptor(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "wp5"+ CHOREOGRAPHY_DEPLOYMENT_DESCRIPTION_FILE_EXTENSION),
					response.getChoreographyDeploymentDescriptorContent());
		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("generate choreography deployment description error ", e);
		}
		
		Assert.assertFalse(exceptionDetected);
	}

}
