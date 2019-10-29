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
package eu.chorevolution.synthesisprocessor.rest.api.authentication;


import org.springframework.stereotype.Component;

import eu.chorevolution.synthesisprocessor.rest.api.AdapterGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.BindingComponentGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyArchitectureGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyDeploymentDescriptorGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyProjectionGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.ChoreographyValidatorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.CoordinationDelegateGeneratorRequest;
import eu.chorevolution.synthesisprocessor.rest.api.GidlFromWSDLRequest;
import eu.chorevolution.synthesisprocessor.rest.api.SecurityFilterGeneratorRequest;



@Component
public class SynthesisProcessorAuthenticationProvider {

	public SynthesisProcessorAuthenticationProvider() {
	}



	 public static boolean isValidAccessToken(String accessToken) {
		 String token = SynthesisProcessorEncrypter.decrypt(SynthesisProcessorEncrypter.getKey(), SynthesisProcessorEncrypter.getInitVector(), accessToken);

		 if(accessToken.equals(SynthesisProcessorEncrypter.getDefaultToken()))
			 return true;


		 //TODO : add a database for users
		 try {
			 String [] array = token.split(":");
			 if(array[0].equals("admin")&&array[1].equals("password"))
				 return true;

		 }
		 catch (Exception e){

			 return false;
		 }

		 return false;

	 }


	public static boolean isLoggedIn(ChoreographyValidatorRequest choreographyValidatorRequest) {
		return isValidAccessToken(choreographyValidatorRequest.getAccessToken());
	}

	public static boolean isLoggedIn(BindingComponentGeneratorRequest bindingComponentGeneratorRequest) {
		return isValidAccessToken(bindingComponentGeneratorRequest.getAccessToken());
	}

	public static boolean isLoggedIn(GidlFromWSDLRequest gidlFromWSDLRequest) {
		return isValidAccessToken(gidlFromWSDLRequest.getAccessToken());
	}


	public static boolean isLoggedIn(
			ChoreographyArchitectureGeneratorRequest choreographyArchitectureGeneratorRequest) {
		return isValidAccessToken(choreographyArchitectureGeneratorRequest.getAccessToken());

	}

	public static boolean isLoggedIn(
			ChoreographyDeploymentDescriptorGeneratorRequest choreographyDeploymentDescriptorGeneratorRequest) {
		return isValidAccessToken(choreographyDeploymentDescriptorGeneratorRequest.getAccessToken());

	}



	public static boolean isLoggedIn(ChoreographyProjectionGeneratorRequest choreographyProjectionGeneratorRequest) {
		return isValidAccessToken(choreographyProjectionGeneratorRequest.getAccessToken());

	}



	public static boolean isLoggedIn(CoordinationDelegateGeneratorRequest coordinationDelegateGeneratorRequest) {
		return isValidAccessToken(coordinationDelegateGeneratorRequest.getAccessToken());

	}



	public static boolean isLoggedIn(SecurityFilterGeneratorRequest securityFilterGeneratorRequest) {
		return isValidAccessToken(securityFilterGeneratorRequest.getAccessToken());

	}


	public static boolean isLoggedIn(AdapterGeneratorRequest adapterGeneratorRequest) {
		return isValidAccessToken(adapterGeneratorRequest.getAccessToken());

	}
	

	public static boolean isLoggedIn(String string) {
		return isValidAccessToken(string);

	}






}