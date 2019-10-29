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

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorAuthenticationProvider;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessor;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessorBusinessException;
import eu.chorevolution.synthesisprocessor.rest.business.impl.SynthesisProcessorImpl;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

/**
 * REST operations for acting on artifacts generated by the synthesis processor.
 */
@Path("/")
//@Api("/")
public class RESTSynthesisProcessor {

	private static Logger logger = LoggerFactory.getLogger(RESTSynthesisProcessor.class);

	@Autowired
	private SynthesisProcessor synthesisProcessor;

	/**
	 * Get an generated artifact
	 * 
	 * @param choreographyName
	 *            name of the choreography
	 * @param artifactType
	 *            timestamp of the generated artifact
	 * @param artifactName
	 *            name of the generated artifact
	 * @return
	 */
	//@ApiOperation(value = "Retrives a specific generated artifact", response = byte[].class)
	@Path("{choreographyName}/{artifactType}/{artifactName}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response downloadArtifact(@PathParam("choreographyName") String choreographyName,
			@PathParam("artifactType") String artifactType, @PathParam("artifactName") String artifactName) {

		
		
		try {
			byte[] artifactContent = synthesisProcessor.download(choreographyName, artifactType, artifactName);
			String header = "attachment; filename = " + artifactName;
			return Response.ok(artifactContent).header("content-disposition", header).build();
		} catch (SynthesisProcessorBusinessException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	//@ApiOperation(value = "Upload a specific artifact", response = String.class)
	@Path("{choreographyName}/{artifactType}/{artifactName}")
	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response upload(@PathParam("choreographyName") String choreographyName,
			@PathParam("artifactType") String artifactType, @PathParam("artifactName") String artifactName,
			InputStream artifactContent) {

		
		try {
			String location = synthesisProcessor.upload(choreographyName, artifactType, artifactName, artifactContent);
			return Response.ok(location).build();

		} catch (SynthesisProcessorBusinessException e) {
			logger.error("error", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
