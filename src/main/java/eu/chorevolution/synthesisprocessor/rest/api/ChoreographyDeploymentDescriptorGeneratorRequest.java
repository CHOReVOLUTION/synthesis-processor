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
public class ChoreographyDeploymentDescriptorGeneratorRequest implements java.io.Serializable{
	private static final long serialVersionUID = 9043942153918639100L;

	private String accessToken;
	
    private byte[] choreographyArchitectureContent;

    public ChoreographyDeploymentDescriptorGeneratorRequest() {
    	super();
    }
    
    public ChoreographyDeploymentDescriptorGeneratorRequest(byte[] choreographyArchitectureContent, String accessToken) {
        super();
        this.choreographyArchitectureContent = choreographyArchitectureContent;
		this.setAccessToken(accessToken);
    }

    public byte[] getChoreographyArchitectureContent() {
        return choreographyArchitectureContent;
    }

    public void setChoreographyArchitectureContent(byte[] choreographyArchitectureContent) {
        this.choreographyArchitectureContent = choreographyArchitectureContent;
    }
    
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
