package com.mmk.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmk.project.RedisManager.RedisManager;
import com.mmk.project.Service.AccountService;
import com.mmk.project.Service.PhoneNumberService;

@RestController
@RequestMapping("/api")
public class SMSController {
	
	@Autowired
	PhoneNumberService phoneNumberService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	RedisManager redisManager;
	
	@RequestMapping(method = RequestMethod.POST, value = "/inbound/sms")
	public JSONObject inapi(@RequestParam(required = true) String from,@RequestParam(required = true) String to, 
			@RequestParam(required = true) String text) throws JSONException {
		
		try{
			
			boolean f1 = false;	boolean f2 = false; boolean f3 = false;
			
			JSONObject blah = new JSONObject();
			
			if(from.isBlank()){
				blah.put("message", "");
				blah.put("error", "from param is missing");
				return blah;
			}
			
			if(to.isBlank()){
				blah.put("message", "");
				blah.put("error", "to param is missing");
				return blah;
			}
			
			if(text.isBlank()){
				blah.put("message", "");
				blah.put("error", "text param is missing");
				return blah;
			}
	
	
			if( from.length()>=6 && from.length()<=16 )
				f1=true;
	
			if( to.length()>=6 && to.length()<=16 )
				f2=true;
	
			if( text.length()>=1 && from.length()<=120 )
				f3=true;
			
			if(!f1){
				blah.put("message", "");
				blah.put("error", "from param is invalid");
				return blah;
			}
			
			if(!f2){
				blah.put("message", "");
				blah.put("error", "to param is invalid");
				return blah;
			}
			
			if(!f3){
				blah.put("message", "");
				blah.put("error", "text param is invalid");
				return blah;
			}
	
			if(text.strip().equals("STOP"))
			{
				redisManager.setKey(to, from, "" , "STOP");
				blah.put("message", "MESSAGE CACHED");
				blah.put("error", "");	
				return blah;
			}
			
			if(f1 && f2 && f3) {
				
				int curr_id = 1; //find current session id
				if(phoneNumberService.getPhoneByAccount_id(curr_id).contains(to) == false) {
					blah.put("message", "");
					blah.put("error", "to parameter not found");
					return blah;
				}
				
				blah.put("message", "inbound sms ok");
				blah.put("error", "");	
				return blah;
			}
			
			blah.put("message", "");
			blah.put("error", "unknown failure");	
			return blah;
	
		}
		catch (Exception e) {
			JSONObject blah = new JSONObject();
			
			blah.put("message", "");
			blah.put("error", "Unexpected Error");	
			return blah;
		}
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST , value = "/outbound/sms")
	public JSONObject outapi(@RequestParam(required = true) String from,@RequestParam(required = true) String to,
			@RequestParam(required = true) String text) throws JSONException {
		
		try {
			
			boolean f1 = false; boolean f2 = false; boolean f3 = false;
			
			JSONObject blah = new JSONObject();
			
			if(from.isBlank()){
				blah.put("message", "");
				blah.put("error", "from param is missing");
				return blah;
			}
			
			if(to.isBlank()){
				blah.put("message", "");
				blah.put("error", "to param is missing");
				return blah;
			}
			
			if(text.isBlank()){
				blah.put("message", "");
				blah.put("error", "text param is missing");
				return blah;
			}
	
			if( from.length()>=6 & from.length()<=16 )
				f1=true;
	
			if( to.length()>=6 & to.length()<=16 )
				f2=true;
	
			if( text.length()>=1 & from.length()<=120 )
				f3=true;
			
			if(!f1){
				blah.put("message", "");
				blah.put("error", "from param is invalid");
				return blah;
			}
			
			if(!f2){
				blah.put("message", "");
				blah.put("error", "to param is invalid");
				return blah;
			}
			
			if(!f3){
				blah.put("message", "");
				blah.put("error", "text param is invalid");
				return blah;
			}
			
			
			String attempts = "0" ; 
			if(f1 && f2 && f3) {
				
				if(redisManager.hasKey(to, from, "STOP")) {
					blah.put("message", "");
					blah.put("error", "sms from " + from  + " to " + to + " blocked by STOP request");
					return blah;
				}
				
				int curr_id = 1;
				if(phoneNumberService.getPhoneByAccount_id(curr_id).contains(from) == false) {
					blah.put("message", "");
					blah.put("error", "from parameter not found");
					return blah;
				}
					
				
				if(redisManager.hasKey("", from, "RATE_LIMIT"))
				{
					attempts = redisManager.getKey( "" , from, "RATE_LIMIT");
					if(Integer.parseInt(attempts) > 50){
						blah.put("message", "");
						blah.put("error", "limit reached for from" + from);
						return blah;
					}
					
				}
				redisManager.setKey( "" , from, "RATE_LIMIT", String.valueOf(Integer.parseInt(attempts)+1));
				blah.put("message", "outbound sms ok");
				blah.put("error", "");	
				return blah;
			}
			
			
			blah.put("message", "");
			blah.put("error", "unknown failure");
			return blah;
		}
		catch (Exception e) {
			JSONObject blah = new JSONObject();
			blah.put("message", "");
			blah.put("error", "Unexpected Error");
			return blah;
			
		}
		
	}

}
