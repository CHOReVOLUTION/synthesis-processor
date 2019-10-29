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
public class AdapterGeneratorResponse implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639111L;

	private boolean adapterGenerated;
	private byte[] wsdlFile;
	private byte[] adapterContent;
	private String location;

	public AdapterGeneratorResponse() {
		super();
		this.adapterGenerated = true;
	}

	public AdapterGeneratorResponse(boolean adapterGenerated, byte[] wsdlFile, byte[] adapterContent, String location) {
		super();
		this.adapterGenerated = adapterGenerated;
		this.wsdlFile = wsdlFile;
		this.adapterContent = adapterContent;
		this.location = location;
	}

	public byte[] getWsdlFile() {
		return wsdlFile;
	}

	public void setWsdlFile(byte[] wsdlFile) {
		this.wsdlFile = wsdlFile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isAdapterGenerated() {
		return adapterGenerated;
	}

	public void setAdapterGenerated(boolean adapterGenerated) {
		this.adapterGenerated = adapterGenerated;
	}

	public byte[] getAdapterContent() {
		return adapterContent;
	}

	public void setAdapterContent(byte[] adapterContent) {
		this.adapterContent = adapterContent;
	}
	
}
