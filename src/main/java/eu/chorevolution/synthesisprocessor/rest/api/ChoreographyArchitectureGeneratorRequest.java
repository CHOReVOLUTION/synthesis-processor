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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import eu.chorevolution.synthesisprocessor.rest.business.model.ChoreographyArchitectureComponentData;

@XmlRootElement
public class ChoreographyArchitectureGeneratorRequest implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639108L;

	private String accessToken;
	
	private byte[] bpmn2Content;
	private List<ChoreographyArchitectureComponentData> clientParticipants;
	private List<ChoreographyArchitectureComponentData> prosumerParticipants;
	private List<ChoreographyArchitectureComponentData> providerParticipants;

	public ChoreographyArchitectureGeneratorRequest() {
		super();
		clientParticipants = new ArrayList<ChoreographyArchitectureComponentData>();
		prosumerParticipants = new ArrayList<ChoreographyArchitectureComponentData>();
		providerParticipants = new ArrayList<ChoreographyArchitectureComponentData>();
	}

	public ChoreographyArchitectureGeneratorRequest(byte[] bpmn2Content,
			List<ChoreographyArchitectureComponentData> clientParticipants,
			List<ChoreographyArchitectureComponentData> prosumerParticipants,
			List<ChoreographyArchitectureComponentData> providerParticipants, String accessToken) {
		super();
		this.bpmn2Content = bpmn2Content;
		this.clientParticipants = clientParticipants;
		this.prosumerParticipants = prosumerParticipants;
		this.providerParticipants = providerParticipants;
		this.setAccessToken(accessToken);
	}

	public byte[] getBpmn2Content() {
		return bpmn2Content;
	}

	public void setBpmn2Content(final byte[] bpmn2Content) {
		this.bpmn2Content = bpmn2Content;
	}

	public List<ChoreographyArchitectureComponentData> getClientParticipants() {
		return clientParticipants;
	}

	public void setClientParticipants(List<ChoreographyArchitectureComponentData> clientParticipants) {
		this.clientParticipants = clientParticipants;
	}

	public List<ChoreographyArchitectureComponentData> getProsumerParticipants() {
		return prosumerParticipants;
	}

	public void setProsumerParticipants(List<ChoreographyArchitectureComponentData> prosumerParticipants) {
		this.prosumerParticipants = prosumerParticipants;
	}

	public List<ChoreographyArchitectureComponentData> getProviderParticipants() {
		return providerParticipants;
	}

	public void setProviderParticipants(List<ChoreographyArchitectureComponentData> providerParticipants) {
		this.providerParticipants = providerParticipants;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
