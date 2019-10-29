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
public class ChoreographyArchitectureGeneratorResponse implements java.io.Serializable{
	private static final long serialVersionUID = 9043942153918639109L;

	private byte[] choreographyArchitectureContent;

    public ChoreographyArchitectureGeneratorResponse() {
    	super();
    }
    
    public ChoreographyArchitectureGeneratorResponse(byte[] choreographyArchitectureContent) {
        super();
        this.choreographyArchitectureContent = choreographyArchitectureContent;
    }

    public byte[] getChoreographyArchitectureContent() {
        return choreographyArchitectureContent;
    }

    public void setChoreographyArchitectureContent(byte[] choreographyArchitectureContent) {
        this.choreographyArchitectureContent = choreographyArchitectureContent;
    }

}
