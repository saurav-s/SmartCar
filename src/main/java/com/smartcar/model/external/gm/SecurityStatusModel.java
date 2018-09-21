package com.smartcar.model.external.gm;


public class SecurityStatusModel {

	private DoorSecurityModels doors;

	
	public DoorSecurityModels getDoors() {
		return doors;
	}

	
	public void setDoors(DoorSecurityModels doors) {
		this.doors = doors;
	}
	
	/**
	 * Security
			Request:
			
			POST /getSecurityStatusService
			Content-Type: application/json
			
			{
			  "id": "1234",
			  "responseType": "JSON"
			}
			Response:
			
			{
			  "service": "getSecurityStatus",
			  "status": "200",
			  "data": {
			    "doors": {
			      "type": "Array",
			      "values": [
			        {
			          "location": {
			            "type": "String",
			            "value": "frontLeft"
			          },
			          "locked": {
			            "type": "Boolean",
			            "value": "False"
			          }
			        },
			        {
			          "location": {
			            "type": "String",
			            "value": "frontRight"
			          },
			          "locked": {
			            "type": "Boolean",
			            "value": "True"
			          }
			        }
			      ]
			    }
			  }
			}
	 */

}
