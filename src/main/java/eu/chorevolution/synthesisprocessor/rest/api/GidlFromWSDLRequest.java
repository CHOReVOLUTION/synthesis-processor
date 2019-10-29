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
public class GidlFromWSDLRequest implements java.io.Serializable {
	
	private String choreographyName;

	private String accessToken;

	private byte[] wsdl;

	private String ptName;
	
	public String getChoreographyName() {
		return choreographyName;
	}

	public void setChoreographyName(String choreographyName) {
		this.choreographyName = choreographyName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public byte[] getWsdl() {
		return wsdl;
	}

	public void setWsdl(byte[] wsdl) {
		this.wsdl = wsdl;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	
	

}
