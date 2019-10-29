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

import javax.xml.bind.annotation.XmlRootElement;

import eu.chorevolution.synthesisprocessor.rest.business.model.BindingComponentProtocolType;
import eu.chorevolution.synthesisprocessor.rest.business.model.InterfaceDescriptionType;

@XmlRootElement
public class BindingComponentGeneratorRequest implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639112L;
	private String choreographyName;

	private String accessToken;

	private String bindingComponentName;
	private byte[] interfaceDescriptionContent;
	private InterfaceDescriptionType interfaceDescriptionType;
	private BindingComponentProtocolType bindingComponentProtocolType;
	
	//Amleto: Added 
	private int bcGenerationType = BC_GENERATION_TYPE_SRC;
	public static final int BC_GENERATION_TYPE_SRC = 1;
	public static final int BC_GENERATION_TYPE_WAR = 2;

	public BindingComponentGeneratorRequest() {
		super();
	}

	public BindingComponentGeneratorRequest(String choreographyName, String bindingComponentName,
			byte[] interfaceDescriptionContent, InterfaceDescriptionType interfaceDescriptionType,
			BindingComponentProtocolType bindingComponentProtocolType, String accessToken, int bcGenerationType) {
		super();
		this.bindingComponentName = bindingComponentName;
		this.choreographyName = choreographyName;
		this.interfaceDescriptionContent = interfaceDescriptionContent;
		this.interfaceDescriptionType = interfaceDescriptionType;
		this.bindingComponentProtocolType = bindingComponentProtocolType;
		this.bcGenerationType = bcGenerationType;
		this.setAccessToken(accessToken);
	}

	public String getChoreographyName() {
		return choreographyName;
	}

	public void setChoreographyName(String choreographyName) {
		this.choreographyName = choreographyName;
	}

	public String getBindingComponentName() {
		return bindingComponentName;
	}

	public void setBindingComponentName(String bindingComponentName) {
		this.bindingComponentName = bindingComponentName;
	}

	public byte[] getInterfaceDescriptionContent() {
		return interfaceDescriptionContent;
	}

	public void setInterfaceDescriptionContent(byte[] interfaceDescriptionContent) {
		this.interfaceDescriptionContent = interfaceDescriptionContent;
	}

	public InterfaceDescriptionType getInterfaceDescriptionType() {
		return interfaceDescriptionType;
	}

	public void setInterfaceDescriptionType(InterfaceDescriptionType interfaceDescriptionType) {
		this.interfaceDescriptionType = interfaceDescriptionType;
	}

	public BindingComponentProtocolType getBindingComponentProtocolType() {
		return bindingComponentProtocolType;
	}

	public void setBindingComponentProtocolType(BindingComponentProtocolType bindingComponentProtocolType) {
		this.bindingComponentProtocolType = bindingComponentProtocolType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getBcGenerationType() {
		return bcGenerationType;
	}

	public void setBcGenerationType(int bcGenerationType) {
		this.bcGenerationType = bcGenerationType;
	}
	
}
