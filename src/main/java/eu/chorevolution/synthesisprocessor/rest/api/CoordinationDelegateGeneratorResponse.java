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
public class CoordinationDelegateGeneratorResponse implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639107L;

	private String name;
	private String location;
	private byte[] coordinationDelegateContent;
	private byte[] wsdlContent;
	private byte[] consumerWsdlContent;
	private byte[] consumerContent;

	public CoordinationDelegateGeneratorResponse() {
		super();
	}

	public CoordinationDelegateGeneratorResponse(String name, byte[] wsdlContent) {
		super();
		this.name = name;
		this.wsdlContent = wsdlContent;
	}

	public CoordinationDelegateGeneratorResponse(String name, String location, byte[] coordinationDelegateContent,
			byte[] consumerWsdlContent, byte[] consumerContent) {
		super();
		this.name = name;
		this.location = location;
		this.coordinationDelegateContent = coordinationDelegateContent;
		this.consumerWsdlContent = consumerWsdlContent;
		this.consumerContent = consumerContent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public byte[] getWsdlContent() {
		return wsdlContent;
	}

	public void setWsdlContent(byte[] wsdlContent) {
		this.wsdlContent = wsdlContent;
	}

	public byte[] getConsumerWsdlContent() {
		return consumerWsdlContent;
	}

	public void setConsumerWsdlContent(byte[] consumerWsdlContent) {
		this.consumerWsdlContent = consumerWsdlContent;
	}

	public byte[] getConsumerContent() {
		return consumerContent;
	}

	public void setConsumerContent(byte[] consumerContent) {
		this.consumerContent = consumerContent;
	}

	public byte[] getCoordinationDelegateContent() {
		return coordinationDelegateContent;
	}

	public void setCoordinationDelegateContent(byte[] coordinationDelegateContent) {
		this.coordinationDelegateContent = coordinationDelegateContent;
	}

	
}
