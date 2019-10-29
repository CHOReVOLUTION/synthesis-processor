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

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import eu.chorevolution.synthesisprocessor.rest.business.AccessControl;
import eu.chorevolution.synthesisprocessor.rest.business.AccessControlException;


//@Api("Synthesis Processor Login")
@Path("/login")
public class RESTSynthesisProcessorAccessControl {
	
	private static Logger logger = LoggerFactory.getLogger(RESTSynthesisProcessorAccessControl.class);

	@Autowired
	private AccessControl accessControl; 
	
	
	//@ApiOperation(value = "Log into the Synthesis Processor",
	//	    response = SynthesisProcessorLoginResponse.class)
	@Path("/executeLogin")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response executeLogin(SynthesisProcessorLoginRequest splr){
		
		try {
			SynthesisProcessorLoginResponse result = accessControl.executeLogin(splr);
			return Response.ok(result).build();

		} catch (AccessControlException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
