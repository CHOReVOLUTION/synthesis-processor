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
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChoreographyValidatorResponse implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639111L;

	private boolean choreographyValidated;
	private List<String> errors;

	public ChoreographyValidatorResponse() {
		super();
		this.choreographyValidated = true;
		this.errors = new ArrayList<String>();
	}
	
	public ChoreographyValidatorResponse(boolean choreographyValidated, List<String> errors) {
		super();
		this.choreographyValidated = choreographyValidated;
		this.errors = errors;
	}

	public boolean isChoreographyValidated() {
		return choreographyValidated;
	}

	public void setChoreographyValidated(boolean choreographyValidated) {
		this.choreographyValidated = choreographyValidated;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
