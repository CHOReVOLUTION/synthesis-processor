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

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessor;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessorBusinessException;

public class SynthesisProcessorClient extends SynthesisProcessorWebClientConfiguration
		implements SynthesisProcessor {

	public SynthesisProcessorClient(String host) {
		super(host);
	}
	
	@Override
	public byte[] download(String choreographyName, String artifactType, String artifactName)
			throws SynthesisProcessorBusinessException {
		WebClient client = super.setupClient();
		client.path(choreographyName).path(artifactType).path(artifactName);
		try {
			Response s = client.get();
		
			if (s.getEntity() instanceof InputStream){
				try {
					return IOUtils.toByteArray((InputStream)s.getEntity());
				} catch (IOException e) {
					throw new SynthesisProcessorBusinessException (e);
				}
			}
		}catch (WebApplicationException e) {
			throw new SynthesisProcessorBusinessException(e);
		}
		
		return null;
	}
	
	@Override
	public String upload(String choreographyName, String artifactType, String artifactName, InputStream artifactContent)
			throws SynthesisProcessorBusinessException {
		WebClient client = super.setupClient();
		client.path(choreographyName).path(artifactType).path(artifactName);
		try {
			return client.post(artifactContent, String.class);
		} catch (WebApplicationException e) {
			throw new SynthesisProcessorBusinessException(e);
		}

	}



	

}
