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
package eu.chorevolution.synthesisprocessor.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorAuthenticationProvider;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGeneratorException;

//@Api("Binding Component Generator")
@Path("/bindingComponentGenerator")
public class RESTBindingComponentGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(RESTBindingComponentGenerator.class);
	
	@Autowired
	private BindingComponentGenerator bindingComponentGenerator; 
	
	//@ApiOperation(value = "Generates a Binding Component artifact",
	//	    response = BindingComponentGeneratorResponse.class)
	@Path("/generateBindingComponent")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateBindingComponent(
			BindingComponentGeneratorRequest bindingComponentGeneratorRequest){

		//auth
		if(!SynthesisProcessorAuthenticationProvider.isLoggedIn(bindingComponentGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();
		try {
			BindingComponentGeneratorResponse result = bindingComponentGenerator.generateBindingComponent(bindingComponentGeneratorRequest);
			return Response.ok(result).build();

		} catch (BindingComponentGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Path("/generateGidlFromWSDL")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateGIDLFromWSDL(
			GidlFromWSDLRequest gidlFromWSDLRequest){

		//auth
		if(!SynthesisProcessorAuthenticationProvider.isLoggedIn(gidlFromWSDLRequest))
			return Response.status(Status.UNAUTHORIZED).build();
		
		try {
			GidlFromWSDLResponse result = bindingComponentGenerator.generateGIDLFromWSDL(gidlFromWSDLRequest);
			return Response.ok(result).build();

		} catch (BindingComponentGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
