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
package eu.chorevolution.synthesisprocessor.rest.business.impl.test.util;

import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessor;
import eu.chorevolution.synthesisprocessor.rest.business.SynthesisProcessorBusinessException;
import eu.chorevolution.synthesisprocessor.rest.business.impl.SynthesisProcessorImpl;

public class SynthesisProcessorImplUtils {
	public static byte[] getArtifactContent(String location) throws SynthesisProcessorBusinessException {
		try {
			SynthesisProcessor synthesisProcessor = new SynthesisProcessorImpl();
			String[] responseSegmentsPath = location.split("/");
			String artifactName = responseSegmentsPath[responseSegmentsPath.length - 1];
			String directory = responseSegmentsPath[responseSegmentsPath.length - 2];
			String choreographyName = responseSegmentsPath[responseSegmentsPath.length - 3];

			return synthesisProcessor.download(choreographyName, directory, artifactName);
		} catch (Exception e) {
			throw new SynthesisProcessorBusinessException("error retrive artifact:", e);
		}

	}
}
