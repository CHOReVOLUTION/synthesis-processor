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

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyProjectionGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyProjectionGeneratorException;
import eu.chorevolution.transformations.generativeapproach.bpmn2choreographyprojector.Bpmn2ChoreographyProjector;
import eu.chorevolution.transformations.generativeapproach.bpmn2choreographyprojector.Bpmn2ChoreographyProjectorRequest;
import eu.chorevolution.transformations.generativeapproach.bpmn2choreographyprojector.Bpmn2ChoreographyProjectorResponse;

@Service
public class ChoreographyProjectionGeneratorImpl implements ChoreographyProjectionGenerator {

	@Override
	public ChoreographyProjectionGeneratorResponse generateChoreographyProjection(
			ChoreographyProjectionGeneratorRequest choreographyProjectionGeneratorRequest)
			throws ChoreographyProjectionGeneratorException {
		try {

			Bpmn2ChoreographyProjectorRequest request = new Bpmn2ChoreographyProjectorRequest(
					choreographyProjectionGeneratorRequest.getBpmn2Content(),
					choreographyProjectionGeneratorRequest.getParticipant());

			Bpmn2ChoreographyProjector bpmn2ChoreographyProjector = new Bpmn2ChoreographyProjector();
			Bpmn2ChoreographyProjectorResponse response = bpmn2ChoreographyProjector.project(request);
			return new ChoreographyProjectionGeneratorResponse(response.getBpmn2Content());

		} catch (Exception e) {
			throw new ChoreographyProjectionGeneratorException(e);
		}
	}
}
