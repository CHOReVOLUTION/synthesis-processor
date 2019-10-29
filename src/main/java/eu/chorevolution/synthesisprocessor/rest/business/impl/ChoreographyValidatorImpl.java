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

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyValidator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyValidatorException;
import eu.chorevolution.validations.bpmn2choreographyvalidator.Bpmn2ChoreographyValidator;
import eu.chorevolution.validations.bpmn2choreographyvalidator.Bpmn2ChoreographyValidatorRequest;
import eu.chorevolution.validations.bpmn2choreographyvalidator.Bpmn2ChoreographyValidatorResponse;

@Service
public class ChoreographyValidatorImpl implements ChoreographyValidator {

	@Override
	public ChoreographyValidatorResponse validateChoreography(
			ChoreographyValidatorRequest choreographyValidationRequest) throws ChoreographyValidatorException {
		try {
			Bpmn2ChoreographyValidatorRequest bpmn2ChoreographyValidatorRequest = new Bpmn2ChoreographyValidatorRequest();
			bpmn2ChoreographyValidatorRequest.setBpmn2Content(choreographyValidationRequest.getBpmn2Content());
			bpmn2ChoreographyValidatorRequest.setBpmn2XSD(choreographyValidationRequest.getTypesContent());

			Bpmn2ChoreographyValidatorResponse response = new Bpmn2ChoreographyValidator()
					.validate(bpmn2ChoreographyValidatorRequest);

			return new ChoreographyValidatorResponse(response.isValid(), response.getErrors());
		} catch (Exception e) {
			throw new ChoreographyValidatorException(e);
		}
	}
}
