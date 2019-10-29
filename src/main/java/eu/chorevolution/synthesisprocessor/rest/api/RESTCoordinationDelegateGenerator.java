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
import eu.chorevolution.synthesisprocessor.rest.business.CoordinationDelegateGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.CoordinationDelegateGeneratorException;

//@Api("Coordination Delegate Generator")
@Path("/coordinationdelegategenerator")
public class RESTCoordinationDelegateGenerator {

	private static Logger logger = LoggerFactory.getLogger(RESTCoordinationDelegateGenerator.class);

	@Autowired
	private CoordinationDelegateGenerator coordinationDelegateGenerator;

	// @ApiOperation(value = "Generates a prosumer Coordination Delegate WSDL",
	// response = CoordinationDelegateGeneratorResponse.class)
	@Path("/generateWSDLCoordinationDelegateProsumer")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateWSDLCoordinationDelegateProsumer(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest) {

		// auth
		if (!SynthesisProcessorAuthenticationProvider.isLoggedIn(coordinationDelegateGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			CoordinationDelegateGeneratorResponse result = coordinationDelegateGenerator
					.generateWSDLCoordinationDelegateProsumer(coordinationDelegateGeneratorRequest);
			return Response.ok(result).build();

		} catch (CoordinationDelegateGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	// @ApiOperation(value = "Generates a client Coordination Delegate WSDL",
	// response = CoordinationDelegateGeneratorResponse.class)
	@Path("/generateWSDLCoordinationDelegateClient")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateWSDLCoordinationDelegateClient(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest) {

		// auth
		if (!SynthesisProcessorAuthenticationProvider.isLoggedIn(coordinationDelegateGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			CoordinationDelegateGeneratorResponse result = coordinationDelegateGenerator
					.generateWSDLCoordinationDelegateClient(coordinationDelegateGeneratorRequest);
			return Response.ok(result).build();

		} catch (CoordinationDelegateGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	// @ApiOperation(value = "Generates a prosumers Coordination Delegate artifact",
	// response = CoordinationDelegateGeneratorResponse.class)
	@Path("/generateCoordinationDelegateProsumer")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateCoordinationDelegateProsumer(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest) {

		// auth
		if (!SynthesisProcessorAuthenticationProvider.isLoggedIn(coordinationDelegateGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			CoordinationDelegateGeneratorResponse result = coordinationDelegateGenerator
					.generateCoordinationDelegateProsumer(coordinationDelegateGeneratorRequest);
			return Response.ok(result).build();

		} catch (CoordinationDelegateGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	// @ApiOperation(value = "Generates a client Coordination Delegate artifact",
	// response = CoordinationDelegateGeneratorResponse.class)
	@Path("/generateCoordinationDelegateClient")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateCoordinationDelegateClient(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest) {

		// auth
		if (!SynthesisProcessorAuthenticationProvider.isLoggedIn(coordinationDelegateGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			CoordinationDelegateGeneratorResponse result = coordinationDelegateGenerator
					.generateCoordinationDelegateClient(coordinationDelegateGeneratorRequest);
			return Response.ok(result).build();

		} catch (CoordinationDelegateGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	// @ApiOperation(value = "Generates a consumer Coordination Delegate artifact",
	// response = CoordinationDelegateGeneratorResponse.class)
	@Path("/generateCoordinationDelegateConsumer")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response generateCoordinationDelegateConsumer(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest) {

		// auth
		if (!SynthesisProcessorAuthenticationProvider.isLoggedIn(coordinationDelegateGeneratorRequest))
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			CoordinationDelegateGeneratorResponse result = coordinationDelegateGenerator
					.generateConsumer(coordinationDelegateGeneratorRequest);
			return Response.ok(result).build();

		} catch (CoordinationDelegateGeneratorException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
