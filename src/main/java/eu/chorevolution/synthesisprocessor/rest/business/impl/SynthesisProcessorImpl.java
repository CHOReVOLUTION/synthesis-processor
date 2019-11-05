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
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessor;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessorBusinessException;
import eu.chorevolution.synthesisprocessor.rest.business.model.SynthesisProcessorComponentType;

@Service
public class SynthesisProcessorImpl implements SynthesisProcessor {

	@Value("#{cfg.ARTIFACT_HOME_REPOSITORY}")
	private String artifactHomeRepository;

	@Autowired
	private SynthesisProcessorUtils synthesisProcessorUtils;

	@Override
	public byte[] download(String choreographyName, String artifactType, String artifactName)
			throws SynthesisProcessorBusinessException {
		try {

			File artifact = new File(artifactHomeRepository + File.separator + choreographyName + File.separator
					+ artifactType + File.separator + artifactName);

			if (!artifact.exists()) {
				throw new SynthesisProcessorBusinessException("artifact not found : " + artifactName);
			}

			return FileUtils.readFileToByteArray(artifact);
		} catch (Exception e) {
			throw new SynthesisProcessorBusinessException("artifact not found : " + artifactName, e);
		}

	}

	@Override
	public String upload(String choreographyName, String artifactType, String artifactName, InputStream artifactContent)
			throws SynthesisProcessorBusinessException {
		try {
			String name = artifactName.replace("." + FilenameUtils.getExtension(artifactName), "");
			String extension = FilenameUtils.getExtension(artifactName);
			byte[] content = IOUtils.toByteArray(artifactContent);
			
			return synthesisProcessorUtils.createArtifactURI(choreographyName, getSynthesisProcessorComponentType(artifactType),
					name, "." + extension, content);
		} catch (Exception e) {
			throw new SynthesisProcessorBusinessException("error upload artifact : " + artifactName, e);
		}
	}
	
	private SynthesisProcessorComponentType getSynthesisProcessorComponentType(String artifactType) {
		switch (artifactType) {
		case "prosumer":
			return SynthesisProcessorComponentType.PROSUMER;
		case "adapter":
			return SynthesisProcessorComponentType.AD;
		case "bindingcomponent":
			return SynthesisProcessorComponentType.BC;
		default:
			return SynthesisProcessorComponentType.PROSUMER;
		}
	}
}
