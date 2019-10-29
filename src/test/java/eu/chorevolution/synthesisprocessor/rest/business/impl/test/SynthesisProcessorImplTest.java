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
package eu.chorevolution.synthesisprocessor.rest.business.impl.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessor;
import eu.chorevolution.synthesisprocessor.rest.business.impl.SynthesisProcessorImpl;
import eu.chorevolution.synthesisprocessor.rest.business.impl.test.util.SynthesisProcessorImplUtils;

public class SynthesisProcessorImplTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "SynthesisProcessor" + File.separatorChar;

	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;

	private static Logger logger = LoggerFactory.getLogger(SynthesisProcessorImplTest.class);

	private boolean exceptionDetected;
	private SynthesisProcessor synthesisProcessor;
	
	@Before
	public void setUp() {
		exceptionDetected = false;
		synthesisProcessor = new SynthesisProcessorImpl();
	}

	@Test
	public void upload() {
		try {
			//String location = synthesisProcessor.upload("wp5", "prosumer", "consumercdTouristAgent.war", FileUtils.openInputStream(new File(TEST_RESOURCES + "consumercdTouristAgent.war")));
			String location = synthesisProcessor.upload("wp5", "prosumer", "consumercdTouristAgent.war", FileUtils.openInputStream(new File(TEST_RESOURCES + "consumercdTouristAgent.war")));
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES +"consumercdTouristAgent.war"), SynthesisProcessorImplUtils.getArtifactContent(location));
		} catch (Exception e) {
			exceptionDetected = true;
			logger.error("error upload arifact", e);
		}
		
		Assert.assertFalse(exceptionDetected);
	}
	

}
