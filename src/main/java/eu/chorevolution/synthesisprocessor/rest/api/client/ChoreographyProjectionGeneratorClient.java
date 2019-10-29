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
package eu.chorevolution.synthesisprocessor.rest.api.client;

import javax.ws.rs.WebApplicationException;

import org.apache.cxf.jaxrs.client.WebClient;

import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyProjectionGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyProjectionGeneratorException;

public class ChoreographyProjectionGeneratorClient extends SynthesisProcessorWebClientConfiguration implements ChoreographyProjectionGenerator {
	public ChoreographyProjectionGeneratorClient(String host) {
		super(host);
	}

	@Override
	public ChoreographyProjectionGeneratorResponse generateChoreographyProjection(
			ChoreographyProjectionGeneratorRequest choreographyProjectionGeneratorRequest)
			throws ChoreographyProjectionGeneratorException {

		WebClient client = super.setupClient();
		client.path("generateChoreographyProjection");

		try {
			return client.post(choreographyProjectionGeneratorRequest,
					ChoreographyProjectionGeneratorResponse.class);
		} catch (WebApplicationException e) {
			throw new ChoreographyProjectionGeneratorException(e);
		}
		
		
	}

	

}
