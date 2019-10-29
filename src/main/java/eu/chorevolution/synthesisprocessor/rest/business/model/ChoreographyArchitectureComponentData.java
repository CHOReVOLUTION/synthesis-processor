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
package eu.chorevolution.synthesisprocessor.rest.business.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChoreographyArchitectureComponentData implements java.io.Serializable {
    private static final long serialVersionUID = 103439123312914100L;

    private String participantName;
    private String name;
    private String location;

    private ChoreographyArchitectureComponentDependencyData consumerComponentData;
    private ChoreographyArchitectureComponentDependencyData securityComponentData;
    private ChoreographyArchitectureComponentDependencyData bindingComponentData;
    private ChoreographyArchitectureComponentDependencyData adapterComponentData;
   
    public ChoreographyArchitectureComponentData() {
        super();
    }
    
    public ChoreographyArchitectureComponentData(String participantName, String name, String location) {
        super();
        this.participantName = participantName;
        this.name = name;
        this.location = location;
    }
    
    public ChoreographyArchitectureComponentData(String participantName, String name, String location,
    		ChoreographyArchitectureComponentDependencyData consumerComponentData,
    		ChoreographyArchitectureComponentDependencyData securityComponentData,
            ChoreographyArchitectureComponentDependencyData bindingComponentData,
            ChoreographyArchitectureComponentDependencyData adapterComponentData) {
        super();
        this.participantName = participantName;
        this.name = name;
        this.location = location;
        this.consumerComponentData = consumerComponentData;
        this.securityComponentData = securityComponentData;
        this.bindingComponentData = bindingComponentData;
        this.adapterComponentData = adapterComponentData;
    }
    
    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ChoreographyArchitectureComponentDependencyData getConsumerComponentData() {
        return consumerComponentData;
    }

    public void setConsumerComponentData(ChoreographyArchitectureComponentDependencyData consumerComponentData) {
        this.consumerComponentData = consumerComponentData;
    }

	public ChoreographyArchitectureComponentDependencyData getSecurityComponentData() {
		return securityComponentData;
	}

	public void setSecurityComponentData(ChoreographyArchitectureComponentDependencyData securityComponentData) {
		this.securityComponentData = securityComponentData;
	}

	public ChoreographyArchitectureComponentDependencyData getBindingComponentData() {
		return bindingComponentData;
	}

	public void setBindingComponentData(ChoreographyArchitectureComponentDependencyData bindingComponentData) {
		this.bindingComponentData = bindingComponentData;
	}
	
	public ChoreographyArchitectureComponentDependencyData getAdapterComponentData() {
		return adapterComponentData;
	}

	public void setAdapterComponentData(ChoreographyArchitectureComponentDependencyData adapterComponentData) {
		this.adapterComponentData = adapterComponentData;
	}

	@Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof ChoreographyArchitectureComponentData))return false;
        ChoreographyArchitectureComponentData componentData = (ChoreographyArchitectureComponentData)obj;
        return this.getParticipantName().equals(componentData.getParticipantName());
    }

}
