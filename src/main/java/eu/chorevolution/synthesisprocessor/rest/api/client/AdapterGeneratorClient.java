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

import eu.chorevolution.synthesisprocessor.rest.api.AdapterGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.AdapterGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.AdapterGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.AdapterGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyValidator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyValidatorException;

public class AdapterGeneratorClient extends SynthesisProcessorWebClientConfiguration
		implements AdapterGenerator {

	public AdapterGeneratorClient(String host) {
		super(host);
	}

	@Override
	public AdapterGeneratorResponse generateAdapter(AdapterGeneratorRequest adapterGeneratorRequest) throws AdapterGeneratorException {

		WebClient client = super.setupClient();
		client.path("generateAdapter");

		try {
			return client.post(adapterGeneratorRequest,
					AdapterGeneratorResponse.class);
		} catch (WebApplicationException e) {
			throw new AdapterGeneratorException(e);
		}
	}

}
