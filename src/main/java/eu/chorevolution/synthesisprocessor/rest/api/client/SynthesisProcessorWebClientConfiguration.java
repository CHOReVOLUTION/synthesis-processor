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
package eu.chorevolution.synthesisprocessor.rest.api.client;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class SynthesisProcessorWebClientConfiguration {
	private String host;
	
	public SynthesisProcessorWebClientConfiguration(String host){
		this.host = host;
	}
	
	protected WebClient setupClient() {
		WebClient client = WebClient.create(host);

		// remove time out
		// not proud of it!
		HTTPConduit http = (HTTPConduit) WebClient.getConfig(client).getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(0);// indefined
		httpClientPolicy.setReceiveTimeout(0);// indefined
		http.setClient(httpClientPolicy);

		return client;
	}

}
