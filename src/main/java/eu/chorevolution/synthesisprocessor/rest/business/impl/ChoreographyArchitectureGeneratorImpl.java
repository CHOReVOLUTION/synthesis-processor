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

import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyArchitectureGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyArchitectureGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyArchitectureGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyArchitectureGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.model.ChoreographyArchitectureComponentData;
import eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.model.AdapterComponentData;
import eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.model.BindingComponentData;
import eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.model.ComponentData;
import eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.model.ConsumerComponentData;
import eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.model.SecurityComponentData;

@Service
public class ChoreographyArchitectureGeneratorImpl implements ChoreographyArchitectureGenerator {

	@Override
	public ChoreographyArchitectureGeneratorResponse generateChoreographyArchitecture(
			ChoreographyArchitectureGeneratorRequest choreographyArchitectureGeneratorRequest)
			throws ChoreographyArchitectureGeneratorException {
		try {

			eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.ChoreographyArchitectureGeneratorRequest request = new eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.ChoreographyArchitectureGeneratorRequest();
			request.setBpmn2Content(choreographyArchitectureGeneratorRequest.getBpmn2Content());

			for (ChoreographyArchitectureComponentData choreographyArchitectureComponentData : choreographyArchitectureGeneratorRequest
					.getClientParticipants()) {
				ComponentData componentData = new ComponentData(
						choreographyArchitectureComponentData.getParticipantName(),
						choreographyArchitectureComponentData.getName(),
						choreographyArchitectureComponentData.getLocation());
				request.getClientParticipants().add(componentData);
				
				if (choreographyArchitectureComponentData.getBindingComponentData() != null) {
					BindingComponentData bindingComponentData = new BindingComponentData(
							choreographyArchitectureComponentData.getBindingComponentData().getName(),
							choreographyArchitectureComponentData.getBindingComponentData().getLocation());
					componentData.setBindingComponentData(bindingComponentData);
				}

				if (choreographyArchitectureComponentData.getConsumerComponentData() != null) {
					ConsumerComponentData consumerComponentData = new ConsumerComponentData(
							choreographyArchitectureComponentData.getConsumerComponentData().getName(),
							choreographyArchitectureComponentData.getConsumerComponentData().getLocation());
					componentData.setConsumerComponentData(consumerComponentData);
				
				}

				if (choreographyArchitectureComponentData.getSecurityComponentData() != null) {
					SecurityComponentData securityComponentData = new SecurityComponentData(
							choreographyArchitectureComponentData.getSecurityComponentData().getName(),
							choreographyArchitectureComponentData.getSecurityComponentData().getLocation());
					componentData.setSecurityComponentData(securityComponentData);
				}
				
				if (choreographyArchitectureComponentData.getAdapterComponentData() != null) {
					AdapterComponentData adapterComponentData = new AdapterComponentData(
							choreographyArchitectureComponentData.getAdapterComponentData().getName(),
							choreographyArchitectureComponentData.getAdapterComponentData().getLocation());
					componentData.setAdapterComponentData(adapterComponentData);
				}
			}

			for (ChoreographyArchitectureComponentData choreographyArchitectureComponentData : choreographyArchitectureGeneratorRequest
					.getProsumerParticipants()) {
				ComponentData componentData = new ComponentData(
						choreographyArchitectureComponentData.getParticipantName(),
						choreographyArchitectureComponentData.getName(),
						choreographyArchitectureComponentData.getLocation());
				request.getProsumerParticipants().add(componentData);
				
				if (choreographyArchitectureComponentData.getBindingComponentData() != null) {
					BindingComponentData bindingComponentData = new BindingComponentData(
							choreographyArchitectureComponentData.getBindingComponentData().getName(),
							choreographyArchitectureComponentData.getBindingComponentData().getLocation());
					componentData.setBindingComponentData(bindingComponentData);
				}

				if (choreographyArchitectureComponentData.getConsumerComponentData() != null) {
					ConsumerComponentData consumerComponentData = new ConsumerComponentData(
							choreographyArchitectureComponentData.getConsumerComponentData().getName(),
							choreographyArchitectureComponentData.getConsumerComponentData().getLocation());
					componentData.setConsumerComponentData(consumerComponentData);
				
				}

				if (choreographyArchitectureComponentData.getSecurityComponentData() != null) {
					SecurityComponentData securityComponentData = new SecurityComponentData(
							choreographyArchitectureComponentData.getSecurityComponentData().getName(),
							choreographyArchitectureComponentData.getSecurityComponentData().getLocation());
					componentData.setSecurityComponentData(securityComponentData);
				
				}
				
				if (choreographyArchitectureComponentData.getAdapterComponentData() != null) {
					AdapterComponentData adapterComponentData = new AdapterComponentData(
							choreographyArchitectureComponentData.getAdapterComponentData().getName(),
							choreographyArchitectureComponentData.getAdapterComponentData().getLocation());
					componentData.setAdapterComponentData(adapterComponentData);
				
				}
			}
			
			for (ChoreographyArchitectureComponentData choreographyArchitectureComponentData : choreographyArchitectureGeneratorRequest
					.getProviderParticipants()) {
				ComponentData componentData = new ComponentData(
						choreographyArchitectureComponentData.getParticipantName(),
						choreographyArchitectureComponentData.getName(),
						choreographyArchitectureComponentData.getLocation());
				request.getProviderParticipants().add(componentData);
				
				if (choreographyArchitectureComponentData.getBindingComponentData() != null) {
					BindingComponentData bindingComponentData = new BindingComponentData(
							choreographyArchitectureComponentData.getBindingComponentData().getName(),
							choreographyArchitectureComponentData.getBindingComponentData().getLocation());
					componentData.setBindingComponentData(bindingComponentData);
				}

				if (choreographyArchitectureComponentData.getConsumerComponentData() != null) {
					ConsumerComponentData consumerComponentData = new ConsumerComponentData(
							choreographyArchitectureComponentData.getConsumerComponentData().getName(),
							choreographyArchitectureComponentData.getConsumerComponentData().getLocation());
					componentData.setConsumerComponentData(consumerComponentData);
				
				}

				if (choreographyArchitectureComponentData.getSecurityComponentData() != null) {
					SecurityComponentData securityComponentData = new SecurityComponentData(
							choreographyArchitectureComponentData.getSecurityComponentData().getName(),
							choreographyArchitectureComponentData.getSecurityComponentData().getLocation());
					componentData.setSecurityComponentData(securityComponentData);
				
				}
				
				if (choreographyArchitectureComponentData.getAdapterComponentData() != null) {
					AdapterComponentData adapterComponentData = new AdapterComponentData(
							choreographyArchitectureComponentData.getAdapterComponentData().getName(),
							choreographyArchitectureComponentData.getAdapterComponentData().getLocation());
					componentData.setAdapterComponentData(adapterComponentData);
				
				}
			}
			
			eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.ChoreographyArchitectureGeneratorResponse response = new eu.chorevolution.transformations.generativeapproach.choreographyarchitecturegenerator.ChoreographyArchitectureGenerator().generate(request);

			return new ChoreographyArchitectureGeneratorResponse(response.getChoreographyArchitecture());
			
		} catch (Exception e) {
			throw new ChoreographyArchitectureGeneratorException(e);
		}
	}
}
