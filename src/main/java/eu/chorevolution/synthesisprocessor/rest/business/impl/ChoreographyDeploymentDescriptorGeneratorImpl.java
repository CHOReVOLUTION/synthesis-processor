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

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyDeploymentDescriptorGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyDeploymentDescriptorGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyDeploymentDescriptorGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyDeploymentDescriptorGeneratorException;
import eu.chorevolution.transformations.generativeapproach.choreographyspecificationgenerator.ChoreographySpecificationGenerator;
import eu.chorevolution.transformations.generativeapproach.choreographyspecificationgenerator.ChoreographySpecificationGeneratorRequest;
import eu.chorevolution.transformations.generativeapproach.choreographyspecificationgenerator.ChoreographySpecificationGeneratorResponse;

@Service
public class ChoreographyDeploymentDescriptorGeneratorImpl implements ChoreographyDeploymentDescriptorGenerator {

	@Override
	public ChoreographyDeploymentDescriptorGeneratorResponse generateChoreographyDeploymentDescriptor(
			ChoreographyDeploymentDescriptorGeneratorRequest choreographyDeploymentDescriptorGeneratorRequest)
			throws ChoreographyDeploymentDescriptorGeneratorException {
		try {

			ChoreographySpecificationGeneratorRequest request = new ChoreographySpecificationGeneratorRequest(
					choreographyDeploymentDescriptorGeneratorRequest.getChoreographyArchitectureContent());

			ChoreographySpecificationGeneratorResponse response = new ChoreographySpecificationGenerator()
					.generate(request);

			return new ChoreographyDeploymentDescriptorGeneratorResponse(response.getChoreographySpecification());

		} catch (Exception e) {
			throw new ChoreographyDeploymentDescriptorGeneratorException(e);
		} 
	}
}
