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
package eu.chorevolution.synthesisprocessor.rest.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import eu.chorevolution.synthesisprocessor.rest.api.SynthesisProcessorLoginRequest;
import eu.chorevolution.synthesisprocessor.rest.api.SynthesisProcessorLoginResponse;
import eu.chorevolution.synthesisprocessor.rest.api.authentication.SynthesisProcessorEncrypter;
import eu.chorevolution.synthesisprocessor.rest.business.AccessControl;
import eu.chorevolution.synthesisprocessor.rest.business.AccessControlException;

@Service
public class AccessControlImpl implements AccessControl {


	@Override
	public SynthesisProcessorLoginResponse executeLogin(SynthesisProcessorLoginRequest loginRequest)
			throws AccessControlException {

		try {
			//here i do the auth function
			String username=SynthesisProcessorEncrypter.decrypt(SynthesisProcessorEncrypter.getKey(), SynthesisProcessorEncrypter.getInitVector(), loginRequest.getUsername());
			String password=SynthesisProcessorEncrypter.decrypt(SynthesisProcessorEncrypter.getKey(), SynthesisProcessorEncrypter.getInitVector(), loginRequest.getPassword());
			List<String> errors= new ArrayList<String>();
			SynthesisProcessorLoginResponse synthesisProcessorLoginResponse;

			if (authorizedUser(username, password)) {

				String token=issueToken(username, password);

				synthesisProcessorLoginResponse = new SynthesisProcessorLoginResponse(true, token, errors);
				synthesisProcessorLoginResponse.setUserLogged(true);
		}
		else {
				errors.add("Username or password wrong.");

				synthesisProcessorLoginResponse = new SynthesisProcessorLoginResponse(false, "", errors);
				synthesisProcessorLoginResponse.setUserLogged(false);
		}

			return synthesisProcessorLoginResponse;

		} catch (Exception e) {
			throw new AccessControlException(e);
		}

	}


	 private String issueToken(String username, String password) {
		 String token = username+":"+password+":"+Long.toString(System.currentTimeMillis());
		 String encryptedToken = SynthesisProcessorEncrypter.encrypt(SynthesisProcessorEncrypter.getKey(), SynthesisProcessorEncrypter.getInitVector(), token);

		 return encryptedToken;
	    }


	private boolean authorizedUser(String username, String password) {

		//TODO: add a database for users
		if("admin".equals(username) && "password".equals(password))
				return true;

		return false;

}

}
