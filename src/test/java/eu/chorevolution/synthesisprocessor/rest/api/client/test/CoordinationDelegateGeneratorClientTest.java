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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.chorevolution.synthesisprocessor.rest.api.CoordinationDelegateGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.CoordinationDelegateGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.CoordinationDelegateGeneratorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.SynthesisProcessorClientUtils;

public class CoordinationDelegateGeneratorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "CoordinationDelegateGenerator"
			+ File.separatorChar;

	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;
	private static final String TAR_GZ_EXTENSION = ".tar.gz";
	private static final String WSDL_EXTENSION = ".wsdl";

	private static Logger logger = LoggerFactory.getLogger(CoordinationDelegateGeneratorClientTest.class);

	private boolean exceptionDetected;
	private CoordinationDelegateGeneratorClient client;

	@Before
	public void setUp() {
		exceptionDetected = false;
		client = new CoordinationDelegateGeneratorClient(
				Configuration.get(Configuration.APPLICATION_BASE_URI) + "coordinationdelegategenerator/");
	}

	@Test
	public void generateWSDLCoordinationDelegateProsumer() {
		try {

			CoordinationDelegateGeneratorRequest request = new CoordinationDelegateGeneratorRequest("wp5",
					"cdTouristAgent", "Tourist Agent",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "touristagent.bpmn2")),
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "types.xsd")), null, SynthesisProcessorEncrypter.getDefaultToken());
			CoordinationDelegateGeneratorResponse response = client.generateWSDLCoordinationDelegateProsumer(request);

			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "cdTouristAgent" + WSDL_EXTENSION),
					response.getWsdlContent());

		} catch (Exception e) {
			logger.error("generate wsdl coordination delegate prosumer error ", e);
			exceptionDetected = true;
		}

		Assert.assertFalse(exceptionDetected);
	}

	@Test
	public void generateWSDLCoordinationDelegateClient() {
		try {
			Map<String, String> correlationDatas = new HashMap<String, String>();

			CoordinationDelegateGeneratorRequest request = new CoordinationDelegateGeneratorRequest("wp5", "cdSTApp",
					"STApp", FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdstapp.bpmn2")),
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "types.xsd")), correlationDatas, SynthesisProcessorEncrypter.getDefaultToken());

			CoordinationDelegateGeneratorResponse response = client.generateWSDLCoordinationDelegateClient(request);

			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "cdSTApp" + WSDL_EXTENSION),
					response.getWsdlContent());
		} catch (Exception e) {
			logger.error("generate wsdl coordination delegate client error ", e);
			exceptionDetected = true;
		}

		Assert.assertFalse(exceptionDetected);
	}

	@Test
	public void generateCoordinationDelegateProsumer() {
		try {

			Map<String, byte[]> wsdlContents = new HashMap<String, byte[]>();
			wsdlContents.put("STApp", FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdstapp.wsdl")));
			wsdlContents.put("Trip Planner",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdtripplanner.wsdl")));
			wsdlContents.put("News", FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "news.wsdl")));
			wsdlContents.put("Poi", FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "poi.wsdl")));
			wsdlContents.put("Tourist Agent",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdtouristagent.wsdl")));

			List<String> prosumerAndClientParticipantName = new ArrayList<String>();
			prosumerAndClientParticipantName.add("Tourist Agent");
			prosumerAndClientParticipantName.add("Trip Planner");
			prosumerAndClientParticipantName.add("STApp");

			List<String> clientParticipantName = new ArrayList<String>();
			clientParticipantName.add("STApp");

			CoordinationDelegateGeneratorRequest request = new CoordinationDelegateGeneratorRequest("wp5",
					"cdTouristAgent", prosumerAndClientParticipantName, clientParticipantName, "Tourist Agent",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "touristagent.bpmn2")),
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "types.xsd")), null, wsdlContents, SynthesisProcessorEncrypter.getDefaultToken());
			CoordinationDelegateGeneratorResponse response = client.generateCoordinationDelegateProsumer(request);

			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "cdTouristAgent" + TAR_GZ_EXTENSION),
					SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));

		} catch (Exception e) {
			logger.error("generate generate coordination delegate prosumer error ", e);
			exceptionDetected = true;
		}

		Assert.assertFalse(exceptionDetected);
	}

	@Test
	public void generateCoordinationDelegateClient() {
		try {
			Map<String, String> correlationDatas = new HashMap<String, String>();

			Map<String, byte[]> wsdlContents = new HashMap<String, byte[]>();
			wsdlContents.put("STApp", FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdstapp.wsdl")));
			wsdlContents.put("Tourist Agent",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdtouristagent.wsdl")));

			List<String> prosumerAndClientParticipantName = new ArrayList<String>();
			prosumerAndClientParticipantName.add("Tourist Agent");
			prosumerAndClientParticipantName.add("Trip Planner");
			prosumerAndClientParticipantName.add("STApp");

			CoordinationDelegateGeneratorRequest request = new CoordinationDelegateGeneratorRequest("wp5", "cdSTApp", "STApp",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdstapp.bpmn2")),
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "types.xsd")), correlationDatas,
					wsdlContents, SynthesisProcessorEncrypter.getDefaultToken());

			CoordinationDelegateGeneratorResponse response = client.generateCoordinationDelegateClient(request);

			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "cdSTApp" + TAR_GZ_EXTENSION),
					SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));

		} catch (Exception e) {
			logger.error("generate coordination delegate client error ", e);
			exceptionDetected = true;
		}

		Assert.assertFalse(exceptionDetected);
	}

	@Test
	public void generateConsumer() {
		try {
			CoordinationDelegateGeneratorRequest request = new CoordinationDelegateGeneratorRequest("wp5",
					"TouristAgent",
					FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "consumer_touristagent.wsdl")), SynthesisProcessorEncrypter.getDefaultToken());

			CoordinationDelegateGeneratorResponse response = client.generateConsumer(request);

			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "TouristAgent" + TAR_GZ_EXTENSION),
					response.getConsumerContent());

		} catch (Exception e) {
			logger.error("generate consume error ", e);
			exceptionDetected = true;
		}

		Assert.assertFalse(exceptionDetected);
	}

}
