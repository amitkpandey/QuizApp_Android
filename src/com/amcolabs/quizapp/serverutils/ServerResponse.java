package com.amcolabs.quizapp.serverutils;

import java.util.HashMap;

import android.app.Activity;

/*
 * Author : Abhinav
 */

public class ServerResponse {
	public int statusCode;
	public int messageType;
	public String payload; 
	public String payload1;
	public String payload2;
	public String payload3;
	public long responseTime;
	
		
	// below code to enumize the response codes
	public enum MessageType{
				NOT_ACTIVATED (1),

				ACTIVATED (2),

				NOT_AUTHORIZED (3),

				FAILED(300),
				
				RATING_OK(220),
				
				OK(400), REG_SAVED(500), OK_NEW_CATEGORIES(240);

				
		private int value;
		
		private MessageType(int value){
			this.setValue(value);
			//statusCodeMap.put(value, this);
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	};
	
	private static HashMap<Integer , MessageType> statusCodeMap = null;//new HashMap<Integer , MessageType>();;
		
	public MessageType getStatusCode(){
		return this.getStatusCode(this.statusCode);
	}
	public MessageType getStatusCode(int value){
		if(statusCodeMap==null){
			statusCodeMap = new HashMap<Integer, ServerResponse.MessageType>();
			for(MessageType s : MessageType.values()){
				statusCodeMap.put(s.getValue(), s);
			}
		}
		return statusCodeMap.containsKey(value) ? statusCodeMap.get(value):null;
	}
	public MessageType getStatusCode(Activity activity){ //ui based function can be removed when switching to new projects
		int value = this.statusCode;
		MessageType s= this.getStatusCode(value);
		// do any necessary processing
		switch(s){
		case ACTIVATED:
			break;
		case FAILED:
			//show standard diaogue for
			break;
		case NOT_ACTIVATED:
			break;
		case NOT_AUTHORIZED:
			//show standard diaogue for
			break;
		case OK:
			break;
		default:
			break;
		}
		return s;
	}

	public long getResponseTime() {
		return responseTime;
	}
	
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
}



//127.0.0.1:8084/func?task=saveUser&deviceId=abcdefgh&email=abhinavabcd@gmail.com
//http://127.0.0.1:8084/func?task=getAuthKey&deviceId=abcdefgh&email=abhinavabcd@gmail.com
//{"payload": "YWJjZGVmZ2h8YWJoaW5hdmFiY2RAZ21haWwuY29t|1393389556|37287ef4a1261b927e8a98d639035d81f0e7eb2c", "statusCode": 1}

//http://127.0.0.1:8084/func?task=saveImmutable&message=hello+world&properties={%22toUser%22:%22check%22,%22unlock_time%22:%220%22}&encodedKey=YWJjZGVmZ2h8YWJoaW5hdmFiY2RAZ21haWwuY29t|1393389556|37287ef4a1261b927e8a98d639035d81f0e7eb2c
//{"payload": "", "statusCode": 0}
//http://127.0.0.1:8084/func?task=getAllImmutables&encodedKey=YWJjZGVmZ2h8YWJoaW5hdmFiY2RAZ21haWwuY29t|1393389556|37287ef4a1261b927e8a98d639035d81f0e7eb2c
//{"payload": "[{\"timeStamp\": {\"$date\": 1393454946772}, \"user\": {\"$oid\": \"530d6fa0e6ea3d61a8cd5bd8\"}, \"messageId\": \"5IGKLANZ\", \"_id\": {\"$oid\": \"530e220ae6ea3d4292100317\"}, \"properties\": {\"locked\": true, \"unlock_time\": \"0\", \"toUser\": \"check\"}, \"messageContent\": \"hello world\"}]", "statusCode": 200}
