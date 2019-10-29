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
public class SynthesisProcessorLoginResponse implements java.io.Serializable {
	private static final long serialVersionUID = 9043942153918639111L;

	private boolean userLogged;
	private String token;
	private List<String> errors;

	public SynthesisProcessorLoginResponse() {
		super();
		this.userLogged = true;
		this.token = token;
		this.errors = new ArrayList<String>();
	}
	
	public SynthesisProcessorLoginResponse(boolean userLogged, String token, List<String> errors) {
		super();
		this.userLogged = userLogged;
		this.token = token;
		this.errors = errors;
	}

	public boolean isUserLogged() {
		return userLogged;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setUserLogged(boolean userLogged) {
		this.userLogged = userLogged;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
