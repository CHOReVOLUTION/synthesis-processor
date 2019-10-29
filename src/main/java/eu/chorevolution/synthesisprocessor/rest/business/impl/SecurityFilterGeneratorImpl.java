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
package eu.chorevolution.synthesisprocessor.rest.business.impl;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.api.ConnectionAccountType;
import eu.chorevolution.synthesisprocessor.rest.api.SecurityFilterGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.SecurityFilterGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.SecurityFilterGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.SecurityFilterGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.model.SynthesisProcessorComponentType;
import eu.chorevolution.transformations.sfgenerator.ConnectionAccount;
import eu.chorevolution.transformations.sfgenerator.LoginPasswordConnectionAccount;
import eu.chorevolution.transformations.sfgenerator.impl.SFGeneratorImpl;
import eu.chorevolution.transformations.sfgenerator.model.SF;

@Service
public class SecurityFilterGeneratorImpl implements SecurityFilterGenerator {
	
	private static final String WAR_EXTENSION = ".war";
	
	@Value("#{cfg.SF_GENERATOR_IMPL}")
	private int sfGeneratorImpl;
	
	@Autowired
	private SynthesisProcessorUtils synthesisProcessorUtils;
	
	@Override
	public SecurityFilterGeneratorResponse generateSecurityFilterProvider(
			SecurityFilterGeneratorRequest securityFilterGeneratorRequest) throws SecurityFilterGeneratorException {
	
		switch (sfGeneratorImpl) {
			case 1:
				return generateSecurityFilterProvider_1(securityFilterGeneratorRequest);
			case 2:
				return generateSecurityFilterProvider_2(securityFilterGeneratorRequest);
			default:
				throw new SecurityFilterGeneratorException ("Error to find an implementation of Securify Filter Generator");
		} 
	}
	
	@Override
	public SecurityFilterGeneratorResponse generateSecurityFilterClient(
			SecurityFilterGeneratorRequest securityFilterGeneratorRequest) throws SecurityFilterGeneratorException {
		
		switch (sfGeneratorImpl) {
			case 1:
				return generateSecurityFilterClient_1(securityFilterGeneratorRequest);
			case 2:
				return generateSecurityFilterClient_2(securityFilterGeneratorRequest);
			default:
				throw new SecurityFilterGeneratorException ("Error to find an implementation of Securify Filter Generator");
		} 
		
	}
	
	public SecurityFilterGeneratorResponse generateSecurityFilterProvider_1(
			SecurityFilterGeneratorRequest securityFilterGeneratorRequest) throws SecurityFilterGeneratorException {
		try {
			byte [] securityFilter = getArtifact(securityFilterGeneratorRequest.getChoreographyName(),
					securityFilterGeneratorRequest.getName(),WAR_EXTENSION);
			
			String location = synthesisProcessorUtils.createArtifactURI(
					securityFilterGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.SF,
					securityFilterGeneratorRequest.getName(), WAR_EXTENSION, securityFilter);
			
			return new SecurityFilterGeneratorResponse(securityFilterGeneratorRequest.getName(), location);
			
		} catch (Exception e) {
			throw new SecurityFilterGeneratorException(e);
		}
		
	}
	
	public SecurityFilterGeneratorResponse generateSecurityFilterClient_1(
			SecurityFilterGeneratorRequest securityFilterGeneratorRequest) throws SecurityFilterGeneratorException {
		try {
			byte [] securityFilter = getArtifact(securityFilterGeneratorRequest.getChoreographyName(),
					securityFilterGeneratorRequest.getName(),WAR_EXTENSION);
			
			String location = synthesisProcessorUtils.createArtifactURI(
					securityFilterGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.SF,
					securityFilterGeneratorRequest.getName(), WAR_EXTENSION, securityFilter);
			
			return new SecurityFilterGeneratorResponse(securityFilterGeneratorRequest.getName(), location);
			
		} catch (Exception e) {
			throw new SecurityFilterGeneratorException(e);
		}
	}
	

	
	public SecurityFilterGeneratorResponse generateSecurityFilterProvider_2(
			SecurityFilterGeneratorRequest securityFilterGeneratorRequest) throws SecurityFilterGeneratorException {
		try {
			ConnectionAccount connectionAccount = null;

			if (securityFilterGeneratorRequest.getConnectionAccountType() != null && securityFilterGeneratorRequest
					.getConnectionAccountType() == ConnectionAccountType.USERNAME_PASSWORD) {
				connectionAccount = new LoginPasswordConnectionAccount();
				((LoginPasswordConnectionAccount) connectionAccount)
						.setLogin(securityFilterGeneratorRequest.getUsernameConnectionAccount());
				((LoginPasswordConnectionAccount) connectionAccount)
						.setPassword(securityFilterGeneratorRequest.getPasswordConnectionAccount());
			}

			SF response = new SFGeneratorImpl().generateSecurityFilter(securityFilterGeneratorRequest.getName(), "",
					securityFilterGeneratorRequest.getServiceInventoryDomain(),
					securityFilterGeneratorRequest.getSecurityDescriptionContent(),
					securityFilterGeneratorRequest.getSecurityRoles(), connectionAccount);

			String location = synthesisProcessorUtils.createArtifactURI(
					securityFilterGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.SF,
					response.getName(), WAR_EXTENSION, response.getWar());

			return new SecurityFilterGeneratorResponse(response.getName(), location);

		} catch (Exception e) {
			throw new SecurityFilterGeneratorException(e);
		}
	}
	
	public SecurityFilterGeneratorResponse generateSecurityFilterClient_2(
			SecurityFilterGeneratorRequest securityFilterGeneratorRequest) throws SecurityFilterGeneratorException {
		try {
			
			SF response = null;
			
			//standard auth security filter
			if(securityFilterGeneratorRequest.getCustomAuthJar() == null) {
				response = new SFGeneratorImpl().generateSecurityFilter(securityFilterGeneratorRequest.getName(), "",
						securityFilterGeneratorRequest.getServiceInventoryDomain(),
						securityFilterGeneratorRequest.getSecurityRoles());
			}
			else {//custom jar security filter
				response = new SFGeneratorImpl().generateSecurityFilter(securityFilterGeneratorRequest.getName(), "",
						securityFilterGeneratorRequest.getServiceInventoryDomain(),
						securityFilterGeneratorRequest.getSecurityRoles(),
						securityFilterGeneratorRequest.getCustomAuthJar());
			}
			
			String location = synthesisProcessorUtils.createArtifactURI(
					securityFilterGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.SF,
					response.getName(), WAR_EXTENSION, response.getWar());

			return new SecurityFilterGeneratorResponse(response.getName(), location);

		} catch (Exception e) {
			throw new SecurityFilterGeneratorException(e);
		}

	}
	
	private byte[] getArtifact(String choreographyName, String artifactName, String artifactExtension) throws BindingComponentGeneratorException {
		try {
			final ClassLoader loader = this.getClass().getClassLoader();
			final InputStream resource = loader
					.getResourceAsStream("sf/" +choreographyName+"/"+ artifactName + "/" + artifactName + artifactExtension);
			if (resource != null) {
				byte[] resourceContent = IOUtils.toByteArray(resource);
				resource.close();
				return resourceContent;
			} else {
				throw new BindingComponentGeneratorException("artifact not found: "+ artifactName + artifactExtension);
			}
		} catch (Exception e) {
			throw new BindingComponentGeneratorException("artifact not found", e);
		}
	}

}
