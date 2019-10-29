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

@XmlRootElement
public class AdapterGeneratorRequest implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639110L;

	private String accessToken;

	private String choreographyName;
	private byte[] bpmn2Content;
	private byte[] typesContent;

	private String adapterName;
	private byte[] adapterModel; // need to be generated, changed type to AdapterModelType
	private byte[] wsdl; // is the WSDL of the BC or of the service (if needs adaptation the BC wsdl,
							// else the service WSDL)

	// Amleto: Added
	private int adGenerationType = AD_GENERATION_TYPE_SRC;
	public static final int AD_GENERATION_TYPE_SRC = 1;
	public static final int AD_GENERATION_TYPE_WAR = 2;

	public AdapterGeneratorRequest() {
		super();
	}

	public AdapterGeneratorRequest(String choreographyName, byte[] bpmn2Content, byte[] typesContent,
			String adapterName, byte[] adapterModel, byte[] wsdl, String accessToken, int adGenerationType) {
		super();
		this.choreographyName = choreographyName;
		this.bpmn2Content = bpmn2Content;
		this.typesContent = typesContent;
		this.adapterName = adapterName;
		this.adapterModel = adapterModel;
		this.wsdl = wsdl;
		this.setAccessToken(accessToken);
		this.adGenerationType = adGenerationType;
	}

	public String getChoreographyName() {
		return choreographyName;
	}

	public void setChoreographyName(String choreographyName) {
		this.choreographyName = choreographyName;
	}

	public byte[] getBpmn2Content() {
		return bpmn2Content;
	}

	public void setBpmn2Content(byte[] bpmn2Content) {
		this.bpmn2Content = bpmn2Content;
	}

	public byte[] getTypesContent() {
		return typesContent;
	}

	public void setTypesContent(byte[] typesContent) {
		this.typesContent = typesContent;
	}

	public String getAdapterName() {
		return adapterName;
	}

	public void setAdapterName(String adapterName) {
		this.adapterName = adapterName;
	}

	public byte[] getAdapterModel() {
		return adapterModel;
	}

	public void setAdapterModel(byte[] adapterModel) {
		this.adapterModel = adapterModel;
	}

	public byte[] getWsdl() {
		return wsdl;
	}

	public void setWsdl(byte[] wsdl) {
		this.wsdl = wsdl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getAdGenerationType() {
		return adGenerationType;
	}

	public void setAdGenerationType(int adGenerationType) {
		this.adGenerationType = adGenerationType;
	}
	
	

}