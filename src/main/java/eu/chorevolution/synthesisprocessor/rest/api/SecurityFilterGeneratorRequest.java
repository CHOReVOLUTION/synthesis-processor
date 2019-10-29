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
public class SecurityFilterGeneratorRequest implements java.io.Serializable{
	private static final long serialVersionUID = 9043942153918639104L;
	private String choreographyName;
	
	private String accessToken;
	
	private String name;
	private String serviceInventoryDomain;
	private List<String> securityRoles;
	private byte[] securityDescriptionContent;
	private byte[] customAuthJar;
	private ConnectionAccountType connectionAccountType;
	private String usernameConnectionAccount;
	private String passwordConnectionAccount;
	
	public SecurityFilterGeneratorRequest() {
		super();
		securityRoles = new ArrayList<String>();
	}
	
	/*
	 * used to generate security filter for client service 
	 */
	public SecurityFilterGeneratorRequest(String choreographyName, String name, String serviceInventoryDomain, List<String> securityRoles, String accessToken) {
		super();
		this.choreographyName = choreographyName;
		this.name = name;
		this.serviceInventoryDomain = serviceInventoryDomain;
		this.securityRoles = securityRoles;
		this.customAuthJar = null;
		this.setAccessToken(accessToken);

	}
	
	/*
	 * used to generate security filter for provider service that require (or not) authentication 
	 */
	public SecurityFilterGeneratorRequest(String choreographyName, String name, String serviceInventoryDomain, List<String> securityRoles,
			byte[] securityDescriptionContent, ConnectionAccountType connectionAccountType, String usernameConnectionAccount, 
			String passwordConnectionAccount, byte[] customAuthJar, String accessToken) {
		super();
		this.choreographyName = choreographyName;
		this.name = name;
		this.serviceInventoryDomain = serviceInventoryDomain;
		this.securityRoles = securityRoles;
		this.securityDescriptionContent = securityDescriptionContent;
		this.connectionAccountType = connectionAccountType;
		this.usernameConnectionAccount = usernameConnectionAccount;
		this.passwordConnectionAccount = passwordConnectionAccount;
		this.customAuthJar = customAuthJar;
		this.setAccessToken(accessToken);

	}

	public byte[] getCustomAuthJar() {
		return customAuthJar;
	}

	public void setCustomAuthJar(byte[] customAuthJar) {
		this.customAuthJar = customAuthJar;
	}

	public String getChoreographyName() {
		return choreographyName;
	}

	public void setChoreographyName(String choreographyName) {
		this.choreographyName = choreographyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceInventoryDomain() {
		return serviceInventoryDomain;
	}

	public void setServiceInventoryDomain(String serviceInventoryDomain) {
		this.serviceInventoryDomain = serviceInventoryDomain;
	}

	public List<String> getSecurityRoles() {
		return securityRoles;
	}

	public void setSecurityRoles(List<String> securityRoles) {
		this.securityRoles = securityRoles;
	}

	public byte[] getSecurityDescriptionContent() {
		return securityDescriptionContent;
	}

	public void setSecurityDescriptionContent(byte[] securityDescriptionContent) {
		this.securityDescriptionContent = securityDescriptionContent;
	}

	public ConnectionAccountType getConnectionAccountType() {
		return connectionAccountType;
	}

	public void setConnectionAccountType(ConnectionAccountType connectionAccountType) {
		this.connectionAccountType = connectionAccountType;
	}

	public String getUsernameConnectionAccount() {
		return usernameConnectionAccount;
	}

	public void setUsernameConnectionAccount(String usernameConnectionAccount) {
		this.usernameConnectionAccount = usernameConnectionAccount;
	}

	public String getPasswordConnectionAccount() {
		return passwordConnectionAccount;
	}

	public void setPasswordConnectionAccount(String passwordConnectionAccount) {
		this.passwordConnectionAccount = passwordConnectionAccount;
	}	
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}