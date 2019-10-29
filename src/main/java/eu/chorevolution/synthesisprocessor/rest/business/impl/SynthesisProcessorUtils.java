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

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessorBusinessException;
import eu.chorevolution.synthesisprocessor.rest.business.model.SynthesisProcessorComponentType;

@Service
public class SynthesisProcessorUtils {

	private static final String SEPARATOR_TIMESTAMP = "_";

	@Value("#{cfg.ARTIFACT_HOME_REPOSITORY}")
	private String artifactHomeRepository;

	@Value("#{cfg.APPLICATION_BASE_URI}")
	private String applicationBaseURI;

	public String createArtifactURI(String choreographyName,
			SynthesisProcessorComponentType synthesisProcessorComponentType, String artifactName,
			String artifactExtension, byte[] artifactContent) throws SynthesisProcessorBusinessException {
		if (choreographyName == null || choreographyName.equals("")) {
			throw new SynthesisProcessorBusinessException("Choreography name is not specified");
		}

		try {
			String timestamp = getTimestamp();
			String fullArtifactName = artifactName + SEPARATOR_TIMESTAMP + timestamp + artifactExtension;

			File output = new File(artifactHomeRepository + File.separator + choreographyName + File.separator
					+ synthesisProcessorComponentType.getName() + File.separator + fullArtifactName);

			FileUtils.forceMkdir(output.getParentFile());
			FileUtils.writeByteArrayToFile(output, artifactContent);
			String baseUrl = applicationBaseURI;
			return baseUrl + choreographyName + "/" + synthesisProcessorComponentType.getName() + "/" + fullArtifactName;
		} catch (IOException e) {
			throw new SynthesisProcessorBusinessException("Internal Error while create artifact URI", e);
		}
	}

	private static String getTimestamp() {
		return new Date().getTime() + "";
	}

}
