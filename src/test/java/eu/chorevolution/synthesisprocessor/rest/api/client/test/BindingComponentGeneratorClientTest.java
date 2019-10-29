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

import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.api.client.BindingComponentGeneratorClient;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.Configuration;
import eu.chorevolution.synthesisprocessor.rest.api.client.test.util.SynthesisProcessorClientUtils;
import eu.chorevolution.synthesisprocessor.rest.business.model.BindingComponentProtocolType;
import eu.chorevolution.synthesisprocessor.rest.business.model.InterfaceDescriptionType;

public class BindingComponentGeneratorClientTest {
	private static final String TEST_RESOURCES = "." + File.separatorChar + "src" + File.separatorChar + "test"
			+ File.separatorChar + "resources" + File.separatorChar + "BindingComponentGenerator"
			+ File.separatorChar;
	
	private static final String OUTPUT_TEST_RESOURCES = TEST_RESOURCES + "output" + File.separatorChar;

	private static final String GIDL_EXTENSION = ".gidl";
	private static final String WAR_EXTENSION = ".war";
	private static final String WSDL_EXTENSION = ".wsdl";

	private static Logger logger = LoggerFactory.getLogger(BindingComponentGeneratorClientTest.class);

	private boolean exceptionDetected;
	private BindingComponentGeneratorClient bindingComponentGeneratorClient;
	
	@Before
	public void setUp() {
		exceptionDetected = false;
		bindingComponentGeneratorClient = new BindingComponentGeneratorClient(
				Configuration.get(Configuration.APPLICATION_BASE_URI)+"bindingComponentGenerator/");
	}

	@Test
	public void generateWP5WeatherBindingComponent() {
		try {
			
			BindingComponentGeneratorRequest request = new BindingComponentGeneratorRequest();
			request.setInterfaceDescriptionContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "weather_wp5"+GIDL_EXTENSION)));
			request.setInterfaceDescriptionType(InterfaceDescriptionType.GIDL);
			request.setChoreographyName("wp5");
			request.setBindingComponentName("bcWeather");
			request.setBindingComponentProtocolType(BindingComponentProtocolType.SOAP);
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());
			BindingComponentGeneratorResponse response = bindingComponentGeneratorClient.generateBindingComponent(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "weather_wp5" + WSDL_EXTENSION), response.getWsdlContent());
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bcWeather_wp5" + WAR_EXTENSION), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));
			

		} catch (Exception e) {
			logger.error("generate WP5 wheather binding component error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}
	
	@Test
	public void generateWP4BridgeBindingComponent() {
		try {
			BindingComponentGeneratorRequest request = new BindingComponentGeneratorRequest();
			request.setInterfaceDescriptionContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "dts-bridge"+GIDL_EXTENSION)));
			request.setInterfaceDescriptionType(InterfaceDescriptionType.GIDL);
			request.setChoreographyName("wp4");
			request.setBindingComponentName("bcDTS-BRIDGE");
			request.setBindingComponentProtocolType(BindingComponentProtocolType.SOAP);
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());
			BindingComponentGeneratorResponse response = bindingComponentGeneratorClient.generateBindingComponent(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bridge" + WSDL_EXTENSION), response.getWsdlContent());
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bcBridge" + WAR_EXTENSION), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));			
		} catch (Exception e) {
			logger.error("generate WP4 bridge binding component error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}
	
	@Test
	public void generateWP4WeatherBindingComponent() {
		try {
			BindingComponentGeneratorRequest request = new BindingComponentGeneratorRequest();              
			request.setInterfaceDescriptionContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "weather_wp4"+GIDL_EXTENSION)));
			request.setInterfaceDescriptionType(InterfaceDescriptionType.GIDL);
			request.setChoreographyName("wp4");
			request.setBindingComponentName("bcDTS-WHATHER");
			request.setBindingComponentProtocolType(BindingComponentProtocolType.SOAP);
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());
			BindingComponentGeneratorResponse response = bindingComponentGeneratorClient.generateBindingComponent(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "weather_wp4" + WSDL_EXTENSION), response.getWsdlContent());
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bcWeather_wp4" + WAR_EXTENSION), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));			
		} catch (Exception e) {
			logger.error("generate WP4 weather binding component error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}
	
	@Test
	public void generateWP4AccidentsBindingComponent() {
		try {
			BindingComponentGeneratorRequest request = new BindingComponentGeneratorRequest();
			request.setInterfaceDescriptionContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "dts-accidents"+GIDL_EXTENSION)));
			request.setInterfaceDescriptionType(InterfaceDescriptionType.GIDL);
			request.setChoreographyName("wp4");
			request.setBindingComponentName("bcDTS-ACCIDENTS");
			request.setBindingComponentProtocolType(BindingComponentProtocolType.SOAP);
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());
			BindingComponentGeneratorResponse response = bindingComponentGeneratorClient.generateBindingComponent(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "accidents" + WSDL_EXTENSION), response.getWsdlContent());
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bcAccidents" + WAR_EXTENSION), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));			
		} catch (Exception e) {
			logger.error("generate WP4 accidents binding component error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}

	@Test
	public void generateWP4CongestionBindingComponent() {
		try {
			BindingComponentGeneratorRequest request = new BindingComponentGeneratorRequest();
			request.setInterfaceDescriptionContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "dts-congestion"+GIDL_EXTENSION)));
			request.setInterfaceDescriptionType(InterfaceDescriptionType.GIDL);
			request.setChoreographyName("wp4");
			request.setBindingComponentName("bcDTS-CONGESTION");
			request.setBindingComponentProtocolType(BindingComponentProtocolType.SOAP);
			request.setAccessToken(SynthesisProcessorEncrypter.getDefaultToken());
			BindingComponentGeneratorResponse response = bindingComponentGeneratorClient.generateBindingComponent(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "congestion" + WSDL_EXTENSION), response.getWsdlContent());
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bcCongestion" + WAR_EXTENSION), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));			
		} catch (Exception e) {
			logger.error("generate WP4 congestion binding component error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}

	/*
	@Test
	public void generateSTAppBindingComponent() {
		try {
			
			BindingComponentGeneratorRequest request = new BindingComponentGeneratorRequest();
			request.setInterfaceDescriptionContent(FileUtils.readFileToByteArray(new File(TEST_RESOURCES + "cdstapp"+WSDL_EXTENSION)));
			request.setInterfaceDescriptionType(InterfaceDescriptionType.WSDL);
			request.setChoreographyName("wp5");
			request.setBindingComponentName("bcSTApp");
			request.setBindingComponentProtocolType(BindingComponentProtocolType.REST);
			BindingComponentGeneratorResponse response = bindingComponentGeneratorClient.generateBindingComponent(request);
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "stapp" + WSDL_EXTENSION), response.getWsdlContent());
			FileUtils.writeByteArrayToFile(new File(OUTPUT_TEST_RESOURCES + "bcSTApp" + WAR_EXTENSION), SynthesisProcessorClientUtils.getArtifactContent(response.getLocation()));
	
		} catch (Exception e) {
			logger.error("generate stapp binding component error ", e);
			exceptionDetected = true;
		}
		
		Assert.assertFalse(exceptionDetected);
	}
	*/

}
