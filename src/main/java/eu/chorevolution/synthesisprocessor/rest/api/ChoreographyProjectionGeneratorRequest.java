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
public class ChoreographyProjectionGeneratorRequest implements java.io.Serializable{
	private static final long serialVersionUID = 9043942153918639102L;
	
	private String accessToken;
	
	private byte[] bpmn2Content;
	private String participant;
	
	public ChoreographyProjectionGeneratorRequest() {
        super();
    }

    public ChoreographyProjectionGeneratorRequest(final byte[] bpmn2Content, final String participant, String accessToken) {
        super();
        this.bpmn2Content = bpmn2Content;
        this.participant = participant;
		this.setAccessToken(accessToken);
    }

	public byte[] getBpmn2Content() {
		return bpmn2Content;
	}

	public void setBpmn2Content(byte[] bpmn2Content) {
		this.bpmn2Content = bpmn2Content;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
    
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
