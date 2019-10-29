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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.api.CoordinationDelegateGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.CoordinationDelegateGeneratorResponse;
import eu.chorevolution.synthesisprocessor.rest.business.CoordinationDelegateGenerator;
import eu.chorevolution.synthesisprocessor.rest.business.CoordinationDelegateGeneratorException;
import eu.chorevolution.synthesisprocessor.rest.business.model.SynthesisProcessorComponentType;
import eu.chorevolution.transformations.generativeapproach.cdconsumerpartgenerator.CDConsumerPartGenerator;
import eu.chorevolution.transformations.generativeapproach.cdconsumerpartgenerator.impl.CDConsumerPartGeneratorImpl;
import eu.chorevolution.transformations.generativeapproach.cdgenerator.CDGenerator;
import eu.chorevolution.transformations.generativeapproach.cdgenerator.impl.CDGeneratorImpl;
import eu.chorevolution.transformations.generativeapproach.cdgenerator.model.CD;
import eu.chorevolution.transformations.generativeapproach.cdgenerator.model.ProjectionData;
import eu.chorevolution.transformations.generativeapproach.cdgenerator.model.TaskCorrelationData;
import eu.chorevolution.transformations.generativeapproach.cdgenerator.model.WSDLData;

@Service
public class CoordinationDelegateGeneratorImpl implements CoordinationDelegateGenerator {

	private static final String TAR_GZ_EXTENSION = ".tar.gz";

	@Autowired
	private SynthesisProcessorUtils synthesisProcessorUtils;

	@Override
	public CoordinationDelegateGeneratorResponse generateWSDLCoordinationDelegateProsumer(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest)
			throws CoordinationDelegateGeneratorException {
		try {
			ProjectionData projectionData = new ProjectionData();
			projectionData.setCdName(coordinationDelegateGeneratorRequest.getName());
			projectionData.setParticipantName(coordinationDelegateGeneratorRequest.getProjectedParticipant());
			projectionData.setBpmn(coordinationDelegateGeneratorRequest.getProjectedBpmn2Content());
			projectionData.setTypes(coordinationDelegateGeneratorRequest.getProjectedTypesContent());

			CDGenerator cdGenerator = new CDGeneratorImpl();

			byte[] wsdl = cdGenerator.generateCDWSDL(projectionData);

			return new CoordinationDelegateGeneratorResponse(
					coordinationDelegateGeneratorRequest.getProjectedParticipant(), wsdl);

		} catch (Exception e) {
			throw new CoordinationDelegateGeneratorException(e);
		}

	}

	@Override
	public CoordinationDelegateGeneratorResponse generateWSDLCoordinationDelegateClient(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest)
			throws CoordinationDelegateGeneratorException {

		try {
			ProjectionData projectionData = new ProjectionData();
			projectionData.setCdName(coordinationDelegateGeneratorRequest.getName());
			projectionData.setParticipantName(coordinationDelegateGeneratorRequest.getProjectedParticipant());
			projectionData.setBpmn(coordinationDelegateGeneratorRequest.getProjectedBpmn2Content());
			projectionData.setTypes(coordinationDelegateGeneratorRequest.getProjectedTypesContent());

			List<TaskCorrelationData> taskCorrelationDatas = new ArrayList<TaskCorrelationData>();
			for (Map.Entry<String, String> entry : coordinationDelegateGeneratorRequest.getCorrelations().entrySet()) {
				taskCorrelationDatas.add(new TaskCorrelationData(entry.getKey(), entry.getValue()));
			}

			CDGenerator cdGenerator = new CDGeneratorImpl();

			byte[] wsdl = cdGenerator.generateCDClientWSDL(projectionData, taskCorrelationDatas);

			return new CoordinationDelegateGeneratorResponse(
					coordinationDelegateGeneratorRequest.getProjectedParticipant(), wsdl);
		} catch (Exception e) {
			throw new CoordinationDelegateGeneratorException(e);
		}
	}

	@Override
	public CoordinationDelegateGeneratorResponse generateCoordinationDelegateProsumer(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest)
			throws CoordinationDelegateGeneratorException {

		try {
			ProjectionData projectionData = new ProjectionData();
			projectionData.setCdName(coordinationDelegateGeneratorRequest.getName());
			projectionData.setParticipantName(coordinationDelegateGeneratorRequest.getProjectedParticipant());
			projectionData.setBpmn(coordinationDelegateGeneratorRequest.getProjectedBpmn2Content());
			projectionData.setTypes(coordinationDelegateGeneratorRequest.getProjectedTypesContent());

			List<WSDLData> wsdlDatas = new ArrayList<WSDLData>();
			for (Map.Entry<String, byte[]> entry : coordinationDelegateGeneratorRequest.getWsdlParticipants()
					.entrySet()) {
				wsdlDatas.add(new WSDLData(entry.getKey(), entry.getValue()));
			}

			CDGenerator cdGenerator = new CDGeneratorImpl();

			CD response = cdGenerator.generateCDProsumer(
					coordinationDelegateGeneratorRequest.getProsumerAndClientParticipants(),
					coordinationDelegateGeneratorRequest.getClientParticipants(), projectionData, wsdlDatas);

			String location = synthesisProcessorUtils.createArtifactURI(
					coordinationDelegateGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.CD,
					response.getName(), TAR_GZ_EXTENSION, response.getArtifact());

			return new CoordinationDelegateGeneratorResponse(response.getName(), location, response.getArtifact(),
					response.getProsumerwsdl(), null);
		} catch (Exception e) {
			throw new CoordinationDelegateGeneratorException(e);
		}

	}

	@Override
	public CoordinationDelegateGeneratorResponse generateCoordinationDelegateClient(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest)
			throws CoordinationDelegateGeneratorException {
		try {
			ProjectionData projectionData = new ProjectionData();
			projectionData.setCdName(coordinationDelegateGeneratorRequest.getName());
			projectionData.setParticipantName(coordinationDelegateGeneratorRequest.getProjectedParticipant());
			projectionData.setBpmn(coordinationDelegateGeneratorRequest.getProjectedBpmn2Content());
			projectionData.setTypes(coordinationDelegateGeneratorRequest.getProjectedTypesContent());

			List<WSDLData> wsdlDatas = new ArrayList<WSDLData>();
			for (Map.Entry<String, byte[]> entry : coordinationDelegateGeneratorRequest.getWsdlParticipants()
					.entrySet()) {
				wsdlDatas.add(new WSDLData(entry.getKey(), entry.getValue()));
			}

			List<TaskCorrelationData> taskCorrelationDatas = new ArrayList<TaskCorrelationData>();
			for (Map.Entry<String, String> entry : coordinationDelegateGeneratorRequest.getCorrelations().entrySet()) {
				taskCorrelationDatas.add(new TaskCorrelationData(entry.getKey(), entry.getValue()));
			}

			CDGenerator cdGenerator = new CDGeneratorImpl();

			CD response = cdGenerator.generateCDClient(taskCorrelationDatas, projectionData, wsdlDatas);

			String location = synthesisProcessorUtils.createArtifactURI(
					coordinationDelegateGeneratorRequest.getChoreographyName(), SynthesisProcessorComponentType.CD,
					response.getName(), TAR_GZ_EXTENSION, response.getArtifact());

			return new CoordinationDelegateGeneratorResponse(response.getName(), location, response.getArtifact(), null,
					null);
		} catch (Exception e) {
			throw new CoordinationDelegateGeneratorException(e);
		}
	}

	@Override
	public CoordinationDelegateGeneratorResponse generateConsumer(
			CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest)
			throws CoordinationDelegateGeneratorException {

		try {
			CDConsumerPartGenerator cdGenerator = new CDConsumerPartGeneratorImpl();

			byte[] artifact = cdGenerator.generateCDConsumerPart(coordinationDelegateGeneratorRequest.getName(),
					coordinationDelegateGeneratorRequest.getConsumerWsdlContent());

			return new CoordinationDelegateGeneratorResponse(coordinationDelegateGeneratorRequest.getName(), null, null,
					null, artifact);

		} catch (Exception e) {
			throw new CoordinationDelegateGeneratorException(e);
		}
	}

}
