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

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.api.GidlFromWSDLRequest;
import eu.chorevolution.synthesisprocessor.rest.api.GidlFromWSDLResponse;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.BindingComponentGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.model.SynthesisProcessorComponentType;
import eu.chorevolution.transformations.generativeapproach.bcgenerator.BCGenerator;
import eu.chorevolution.transformations.generativeapproach.bcgenerator.BCProtocolType;
import eu.chorevolution.transformations.generativeapproach.bcgenerator.impl.BCGeneratorImpl;
import eu.chorevolution.transformations.generativeapproach.bcgenerator.model.BC;
import eu.chorevolution.transformations.generativeapproach.gidlgenerator.GIDLGenerator;
import eu.chorevolution.transformations.generativeapproach.gidlgenerator.GIDLGeneratorException;
import eu.chorevolution.transformations.generativeapproach.gidlgenerator.GIDLGeneratorRequest;

@Service
public class BindingComponentGeneratorImpl implements BindingComponentGenerator {

	private static final String WAR_EXTENSION = ".war";
	private static final String TAR_GZ_EXTENSION = ".tar.gz";
	private static final String WSDL_EXTENSION = ".wsdl";

	@Value("#{cfg.BC_GENERATOR_IMPL}")
	private int bcGeneratorImpl;

	@Autowired
	private SynthesisProcessorUtils synthesisProcessorUtils;

	@Override
	public BindingComponentGeneratorResponse generateBindingComponent(
			BindingComponentGeneratorRequest bindingComponentGeneratorRequest)
			throws BindingComponentGeneratorException {

		switch (bcGeneratorImpl) {
		case 1:
			return getBindingComponentFromFileSystem(bindingComponentGeneratorRequest);
		case 3:
			return generateBindingComponentInternal(bindingComponentGeneratorRequest);
		default:
			throw new BindingComponentGeneratorException(
					"Error to find an implementation of Binding Component Generator");
		}

	}

	@Override
	public GidlFromWSDLResponse generateGIDLFromWSDL(GidlFromWSDLRequest gidlFromWSDLRequest)
			throws BindingComponentGeneratorException {
		try {
			GidlFromWSDLResponse gidlFromWSDLResponse = new GidlFromWSDLResponse();
			gidlFromWSDLResponse.setChoreographyName(gidlFromWSDLRequest.getChoreographyName());
			GIDLGenerator gidlGenerator = new GIDLGenerator();
			GIDLGeneratorRequest gidlGeneratorRequest = new GIDLGeneratorRequest();
			gidlGeneratorRequest.setWsdlContent(gidlFromWSDLRequest.getWsdl());
			gidlGeneratorRequest.setName(gidlFromWSDLRequest.getPtName());
			byte[] clientCDGIDL = gidlGenerator.generate(gidlGeneratorRequest).getGidlContent();
			gidlFromWSDLResponse.setGidl(clientCDGIDL);
			return gidlFromWSDLResponse;
		} catch (GIDLGeneratorException e) {
			throw new BindingComponentGeneratorException(e);
		}

	}

	private BindingComponentGeneratorResponse getBindingComponentFromFileSystem(
			BindingComponentGeneratorRequest bindingComponentGeneratorRequest)
			throws BindingComponentGeneratorException {
		try {
			byte[] bindingComponent = getArtifact(bindingComponentGeneratorRequest.getChoreographyName(),
					bindingComponentGeneratorRequest.getBindingComponentName(), WAR_EXTENSION);
			byte[] bindingWSDL = getArtifact(bindingComponentGeneratorRequest.getChoreographyName(),
					bindingComponentGeneratorRequest.getBindingComponentName(), WSDL_EXTENSION);

			String location = synthesisProcessorUtils.createArtifactURI(
					bindingComponentGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.BC,
					bindingComponentGeneratorRequest.getBindingComponentName(), WAR_EXTENSION, bindingComponent);
			return new BindingComponentGeneratorResponse(null, bindingWSDL, location);

		} catch (Exception e) {
			throw new BindingComponentGeneratorException(e);
		}
	}

	private BindingComponentGeneratorResponse generateBindingComponentInternal(
			BindingComponentGeneratorRequest bindingComponentGeneratorRequest)
			throws BindingComponentGeneratorException {
		try {
			BCProtocolType protocolType;
			switch (bindingComponentGeneratorRequest.getBindingComponentProtocolType()) {
			case SOAP:
				protocolType = BCProtocolType.SOAP;
				break;
			case REST:
				protocolType = BCProtocolType.REST;
				break;
			default:
				protocolType = BCProtocolType.SOAP;
				break;
			}

			BCGenerator bcGenerator = new BCGeneratorImpl();

			BC response = bcGenerator.generateBC(bindingComponentGeneratorRequest.getBindingComponentName(),
					bindingComponentGeneratorRequest.getInterfaceDescriptionContent(), protocolType, bindingComponentGeneratorRequest.getBcGenerationType());
			String fileExtension = (bindingComponentGeneratorRequest.getBcGenerationType() == BindingComponentGeneratorRequest.BC_GENERATION_TYPE_SRC) ? TAR_GZ_EXTENSION: WAR_EXTENSION ;
			String location = synthesisProcessorUtils.createArtifactURI(
					bindingComponentGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.BC,
					bindingComponentGeneratorRequest.getBindingComponentName(), fileExtension, response.getArtifact());

			return new BindingComponentGeneratorResponse(response.getArtifact(), response.getWsdl(), location);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BindingComponentGeneratorException(e);
		}
	}

	private byte[] getArtifact(String choreographyName, String artifactName, String artifactExtension)
			throws BindingComponentGeneratorException {
		try {
			final ClassLoader loader = this.getClass().getClassLoader();
			final InputStream resource = loader.getResourceAsStream(
					"bc/" + choreographyName + "/" + artifactName + "/" + artifactName + artifactExtension);
			if (resource != null) {
				byte[] resourceContent = IOUtils.toByteArray(resource);
				resource.close();
				return resourceContent;
			} else {
				throw new BindingComponentGeneratorException("artifact not found: " + artifactName + artifactExtension);
			}
		} catch (Exception e) {
			throw new BindingComponentGeneratorException("artifact not found", e);
		}
	}

}
