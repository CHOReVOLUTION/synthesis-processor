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

import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.GidlFromWSDLRequest;
import eu.chorevolution.synthesisprocessor.rest.api.GidlFromWSDLResponse;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGeneratorException;

public class BindingComponentGeneratorClient extends SynthesisProcessorWebClientConfiguration
		implements BindingComponentGenerator {

	public BindingComponentGeneratorClient(String host) {
		super(host);
	}

	@Override
	public BindingComponentGeneratorResponse generateBindingComponent(
			BindingComponentGeneratorRequest bindingComponentGeneratorRequest)
			throws BindingComponentGeneratorException {

		WebClient client = super.setupClient();
		client.path("generateBindingComponent");

		try {
			return client.post(bindingComponentGeneratorRequest, BindingComponentGeneratorResponse.class);
		} catch (WebApplicationException e) {
			throw new BindingComponentGeneratorException(e);
		}
	}

	@Override
	public GidlFromWSDLResponse generateGIDLFromWSDL(GidlFromWSDLRequest gidlFromWSDLRequest)
			throws BindingComponentGeneratorException {
		WebClient client = super.setupClient();
		client.path("generateGidlFromWSDL");

		try {
			return client.post(gidlFromWSDLRequest, GidlFromWSDLResponse.class);
		} catch (WebApplicationException e) {
			throw new BindingComponentGeneratorException(e);
		}
	}

}
