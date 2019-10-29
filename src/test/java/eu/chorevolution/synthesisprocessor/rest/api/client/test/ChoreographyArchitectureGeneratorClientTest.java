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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyArchitectureGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyArchitectureGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.ChoreographyArchitectureGeneratorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;
import eu.chorevolution.synthesisprocessor.rest.business.model.ChoreographyArchitectureComponentData;
import eu.chorevolution.synthesisprocessor.rest.business.model.ChoreographyArchitectureComponentDependencyData;

public class ChoreographyArchitectureGeneratorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "ChoreographyArchitectureGenerator"
			+ File.separatorChar;
	
	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;

	private static final String BPMN2_FILE_EXTENSION = ".bpmn2";
	private static final String CHOREOARCH_FILE_EXTENSION = ".chorarch";
	private static final String DEPLOYABLESERVICE_TAR_GZ_EXTENSION = ".tar.gz";
	private static final String DEPLOYABLESERVICE_WAR_EXTENSION = ".war";

	private static Logger logger = LoggerFactory.getLogger(ChoreographyArchitectureGeneratorClientTest.class);

	private boolean exceptionDetected;
	private ChoreographyArchitectureGeneratorClient client;

	@Before
	public void setUp() {
		exceptionDetected = false;
		client = new ChoreographyArchitectureGeneratorClient(
				Configuration.get(Configuration.APPLICATION_BASE_URI)+"choreographyarchitecturegenerator/");
	}

	@Test
	public void generateChoreographyArchitecture() {
		try {
			List<ChoreographyArchitectureComponentData> clientParticipants = new ArrayList<ChoreographyArchitectureComponentData>() {
				{
					add(new ChoreographyArchitectureComponentData("STApp", "cdSTApp",
							"http://localhost:8080/cdSTApp" + DEPLOYABLESERVICE_TAR_GZ_EXTENSION, null,
							new ChoreographyArchitectureComponentDependencyData("sfSTApp",
									"http://localhost:8080/sfSTApp" + DEPLOYABLESERVICE_WAR_EXTENSION),
							new ChoreographyArchitectureComponentDependencyData("bcSTApp",
									"http://localhost:8080/bcSTApp" + DEPLOYABLESERVICE_WAR_EXTENSION),
							new ChoreographyArchitectureComponentDependencyData("adSTApp",
									"http://localhost:8080/adSTApp" + DEPLOYABLESERVICE_WAR_EXTENSION)));
				}
			};
			List<ChoreographyArchitectureComponentData> prosumerParticipants = new ArrayList<ChoreographyArchitectureComponentData>() {
				{
					add(new ChoreographyArchitectureComponentData("Tourist Agent", "cdTouristAgent",
							"http://localhost:8080/cdTouristAgent" + DEPLOYABLESERVICE_TAR_GZ_EXTENSION,
							new ChoreographyArchitectureComponentDependencyData("TouristAgent",
									"http://localhost:8080/consumercdTouristAgent" + DEPLOYABLESERVICE_WAR_EXTENSION),
							null, null, null));
					add(new ChoreographyArchitectureComponentData("Trip Planner", "cdTripPlanner",
							"http://localhost:8080/cdTripPlanner" + DEPLOYABLESERVICE_TAR_GZ_EXTENSION,
							new ChoreographyArchitectureComponentDependencyData("TripPlanner",
									"http://localhost:8080/consumercdTripPlanner" + DEPLOYABLESERVICE_WAR_EXTENSION),
							null, null, null));
				}
			};
			List<ChoreographyArchitectureComponentData> providerParticipants = new ArrayList<ChoreographyArchitectureComponentData>() {
				{
					add(new ChoreographyArchitectureComponentData("Poi", "Poi", "http://93.62.202.242/poi/poi/", null,
							new ChoreographyArchitectureComponentDependencyData("sfPoi",
									"http://localhost:8080/sfPoi" + DEPLOYABLESERVICE_WAR_EXTENSION),
							null, null));
					add(new ChoreographyArchitectureComponentData("Traffic Information", "TrafficInformation",
							"http://93.62.202.242/trafficinformation/trafficinformation/", null,
							new ChoreographyArchitectureComponentDependencyData("sfTrafficInformation",
									"http://localhost:8080/sfTrafficInformation" + DEPLOYABLESERVICE_WAR_EXTENSION),
							null, new ChoreographyArchitectureComponentDependencyData("adTrafficInformation",
									"http://localhost:8080/adTrafficInformation" + DEPLOYABLESERVICE_WAR_EXTENSION)));
					add(new ChoreographyArchitectureComponentData("Journey Planner", "JourneyPlanner",
							"http://93.62.202.242/journeyplanner/journeyplanner/", null,
							new ChoreographyArchitectureComponentDependencyData("sfJourneyPlanner",
									"http://localhost:8080/sfJourneyPlanner" + DEPLOYABLESERVICE_WAR_EXTENSION),
							null, null));
					add(new ChoreographyArchitectureComponentData("OSM Parking", "OSMParking",
							"http://93.62.202.242/osmparking/osmparking/", null,
							new ChoreographyArchitectureComponentDependencyData("sfOSMParking",
									"http://localhost:8080/sfOSMParking" + DEPLOYABLESERVICE_WAR_EXTENSION),
							null, null));
					add(new ChoreographyArchitectureComponentData("Public Transportation", "PublicTransportation",
							"http://93.62.202.242/publictransportation/publictransportation/", null, null, null, null));
					add(new ChoreographyArchitectureComponentData("Weather", "Weather",
							"http://93.62.202.242/weather/weather/", null, null,
							new ChoreographyArchitectureComponentDependencyData("bcWeather",
									"http://localhost:8080/bcWeather" + DEPLOYABLESERVICE_WAR_EXTENSION),
							new ChoreographyArchitectureComponentDependencyData("adWeather",
									"http://localhost:8080/adWeather" + DEPLOYABLESERVICE_WAR_EXTENSION)));
					add(new ChoreographyArchitectureComponentData("News", "News", "http://93.62.202.242/news/news/",
							null, null, null, null));
				}
			};

			ChoreographyArchitectureGeneratorRequest request = new ChoreographyArchitectureGeneratorRequest();
			request.setBpmn2Content(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "wp5"+BPMN2_FILE_EXTENSION)));
			request.setClientParticipants(clientParticipants);
			request.setProsumerParticipants(prosumerParticipants);
			request.setProviderParticipants(providerParticipants);
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());

			
			ChoreographyArchitectureGeneratorResponse response = client.generateChoreographyArchitecture(request);

			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "wp5" + CHOREOARCH_FILE_EXTENSION),
					response.getChoreographyArchitectureContent());

		} catch (Exception e) {
			logger.error("generate choreography architecture error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}

}
