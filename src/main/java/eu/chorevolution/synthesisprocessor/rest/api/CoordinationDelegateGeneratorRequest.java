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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CoordinationDelegateGeneratorRequest implements java.io.Serializable{
	private static final long serialVersionUID = 9043942153918639106L;
	private String choreographyName;
	
	private String accessToken;
	
	private String name;
	private List<String> prosumerAndClientParticipants;
	private List<String> clientParticipants;
	private Map<String, byte[]> wsdlParticipants;
	
	private String projectedParticipant;
	private byte[] projectedBpmn2Content;
	private byte[] projectedTypesContent;
	private Map<String,String> correlations;
	
	private byte[] consumerWsdlContent;
	
	public CoordinationDelegateGeneratorRequest() {
    	super();
    	this.prosumerAndClientParticipants = new ArrayList<String>();
    	this.clientParticipants = new ArrayList<String>();
    	this.wsdlParticipants = new HashMap<String, byte[]>();
    	correlations = new HashMap<String, String>();
    	
    }
	
	/*
	 * used to generate WSDL for prosumers and client participant
	 */
	public CoordinationDelegateGeneratorRequest(String choreographyName, String name, String projectedParticipant, byte[] projectedBpmn2Content, byte[] projectedTypesContent, Map<String,String> correlations, String accessToken) {
    	super();
    	this.prosumerAndClientParticipants = new ArrayList<String>();
    	this.clientParticipants = new ArrayList<String>();
    	this.choreographyName = choreographyName;
    	this.name = name;
    	this.projectedParticipant = projectedParticipant;
    	this.projectedBpmn2Content = projectedBpmn2Content;
    	this.projectedTypesContent = projectedTypesContent;
    	this.correlations = correlations;
		this.setAccessToken(accessToken);

    }
	
	/*
	 * used to generate coordination delegate for prosumers participant
	 */
	public CoordinationDelegateGeneratorRequest(String choreographyName, String name, List<String> prosumerAndClientParticipants, List<String> clientParticipants, String projectedParticipant, byte[] projectedBpmn2Content, byte[] projectedTypesContent, Map<String,String> correlations, Map<String, byte[]> wsdlParticipants, String accessToken) {
    	super();
    	this.choreographyName = choreographyName;
    	this.name = name;
    	this.prosumerAndClientParticipants = prosumerAndClientParticipants;
    	this.clientParticipants = clientParticipants;
    	this.projectedParticipant = projectedParticipant;
    	this.projectedBpmn2Content = projectedBpmn2Content;
    	this.projectedTypesContent = projectedTypesContent;
    	this.correlations = correlations;
    	this.wsdlParticipants = wsdlParticipants;
		this.setAccessToken(accessToken);

    }
	
	/*
	 * used to generate coordination delegate for client participant
	 */
	public CoordinationDelegateGeneratorRequest(String choreographyName, String name, String projectedParticipant, byte[] projectedBpmn2Content, byte[] projectedTypesContent, Map<String,String> correlations, Map<String, byte[]> wsdlParticipants, String accessToken) {
    	super();
    	this.choreographyName = choreographyName;
    	this.name = name;
    	this.prosumerAndClientParticipants = new ArrayList<String>();
    	this.clientParticipants = new ArrayList<String>();
    	this.projectedParticipant = projectedParticipant;
    	this.projectedBpmn2Content = projectedBpmn2Content;
    	this.projectedTypesContent = projectedTypesContent;
    	this.correlations = correlations;
    	this.wsdlParticipants = wsdlParticipants;
		this.setAccessToken(accessToken);

    }
	
	/*
	 * used to generate coordination delegate for the consumer part of prosumers participant
	 */
	public CoordinationDelegateGeneratorRequest(String choreographyName, String name,byte[] consumerWsdlContent, String accessToken) {
    	super();
    	this.choreographyName = choreographyName;
    	this.prosumerAndClientParticipants = new ArrayList<String>();
    	this.clientParticipants = new ArrayList<String>();
    	this.wsdlParticipants = new HashMap<String, byte[]>();
    	correlations = new HashMap<String, String>();
    	this.name = name;
    	this.consumerWsdlContent = consumerWsdlContent;
		this.setAccessToken(accessToken);
    	
    }

	public String getChoreographyName() {
		return choreographyName;
	}

	public void setChoreographyName(String choreographyName) {
		this.choreographyName = choreographyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getProsumerAndClientParticipants() {
		return prosumerAndClientParticipants;
	}

	public void setProsumerAndClientParticipants(List<String> prosumerAndClientParticipants) {
		this.prosumerAndClientParticipants = prosumerAndClientParticipants;
	}

	public List<String> getClientParticipants() {
		return clientParticipants;
	}

	public void setClientParticipants(List<String> clientParticipants) {
		this.clientParticipants = clientParticipants;
	}

	public Map<String, byte[]> getWsdlParticipants() {
		return wsdlParticipants;
	}

	public void setWsdlParticipants(Map<String, byte[]> wsdlParticipants) {
		this.wsdlParticipants = wsdlParticipants;
	}

	public String getProjectedParticipant() {
		return projectedParticipant;
	}

	public void setProjectedParticipant(String projectedParticipant) {
		this.projectedParticipant = projectedParticipant;
	}

	public byte[] getProjectedBpmn2Content() {
		return projectedBpmn2Content;
	}

	public void setProjectedBpmn2Content(byte[] projectedBpmn2Content) {
		this.projectedBpmn2Content = projectedBpmn2Content;
	}

	public byte[] getProjectedTypesContent() {
		return projectedTypesContent;
	}

	public void setProjectedTypesContent(byte[] projectedTypesContent) {
		this.projectedTypesContent = projectedTypesContent;
	}

	public Map<String, String> getCorrelations() {
		return correlations;
	}

	public void setCorrelations(Map<String, String> correlations) {
		this.correlations = correlations;
	}

	public byte[] getConsumerWsdlContent() {
		return consumerWsdlContent;
	}

	public void setConsumerWsdlContent(byte[] consumerWsdlContent) {
		this.consumerWsdlContent = consumerWsdlContent;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
