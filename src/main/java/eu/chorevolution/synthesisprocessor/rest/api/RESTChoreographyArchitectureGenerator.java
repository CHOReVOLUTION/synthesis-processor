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
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyArchitectureGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.ChoreographyArchitectureGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.impl.ChoreographyArchitectureGeneratorImpl;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

//@Api("Choreography Architecture Generator")
@Path("/choreographyarchitecturegenerator")
public class RESTChoreographyArchitectureGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(RESTChoreographyArchitectureGenerator.class);
	
	@Autowired
	private ChoreographyArchitectureGenerator choreographyArchitectureGenerator; 
	
	//@ApiOperation(value = "Generates a Choreography Architecture",
	//	    response = ChoreographyArchitectureGeneratorResponse.class)
	@Path("/generateChoreographyArchitecture")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateChoreographyArchitecture(
			ChoreographyArchitectureGeneratorRequest choreographyArchitectureGeneratorRequest){

		//auth
		if(!SynthesisProcessorAuthenticationProvider.isLoggedIn(choreographyArchitectureGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();
		
		try {
			ChoreographyArchitectureGeneratorResponse result = choreographyArchitectureGenerator.generateChoreographyArchitecture(choreographyArchitectureGeneratorRequest);
			return Response.ok(result).build();

		} catch (ChoreographyArchitectureGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
