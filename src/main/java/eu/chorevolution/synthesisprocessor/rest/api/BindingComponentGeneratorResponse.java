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
public class BindingComponentGeneratorResponse implements java.io.Serializable{
	private static final long serialVersionUID = 9043942153918639113L;

	private byte[] bindingComponentContent;
	private byte[] wsdlContent;
	private String location;

    public BindingComponentGeneratorResponse() {
    	super();
    }
    
	public BindingComponentGeneratorResponse(byte[] bindingComponentContent, byte[] wsdlContent, String location) {
		super();
		this.bindingComponentContent = bindingComponentContent;
		this.wsdlContent = wsdlContent;
		this.location = location;
	}

	public byte[] getBindingComponentContent() {
		return bindingComponentContent;
	}

	public void setBindingComponentContent(byte[] bindingComponentContent) {
		this.bindingComponentContent = bindingComponentContent;
	}

	public byte[] getWsdlContent() {
		return wsdlContent;
	}

	public void setWsdlContent(byte[] wsdlContent) {
		this.wsdlContent = wsdlContent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
    
    

}
