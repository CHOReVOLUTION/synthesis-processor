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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.api.AdapterGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.AdapterGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.business.AdapterGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.AdapterGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.model.SynthesisProcessorComponentType;
import eu.chorevolution.transformations.generativeapproach.adgenerator.impl.ADGeneratorImpl;
import eu.chorevolution.transformations.generativeapproach.adgenerator.model.Adapter;

@Service
public class AdapterGeneratorImpl implements AdapterGenerator {

	private static final String WAR_EXTENSION = ".war";
	private static final String TAR_GZ_EXTENSION = ".tar.gz";
	
	@Autowired
	private SynthesisProcessorUtils synthesisProcessorUtils;
	
	@Override
	public AdapterGeneratorResponse generateAdapter(AdapterGeneratorRequest adapterGeneratorRequest)
			throws AdapterGeneratorException {

	      try {
	    	  
	    	  Adapter adapter = new ADGeneratorImpl().generateAdapter(adapterGeneratorRequest.getAdapterName(), 
	    			  adapterGeneratorRequest.getAdapterModel(), adapterGeneratorRequest.getWsdl(), adapterGeneratorRequest.getAdGenerationType());
	    	  
	          String fileExtension = (adapterGeneratorRequest.getAdGenerationType() == AdapterGeneratorRequest.AD_GENERATION_TYPE_SRC) ? TAR_GZ_EXTENSION: WAR_EXTENSION ;
	          String location = synthesisProcessorUtils.createArtifactURI(
	                adapterGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.AD,
	                adapterGeneratorRequest.getAdapterName(), fileExtension, adapter.getArtifact());
	          
	          return new AdapterGeneratorResponse(true, adapter.getWsdl(), adapter.getArtifact(), location);

	       } catch (Exception e) {
	          throw new AdapterGeneratorException(e);
	       }
	}
	
	
}
