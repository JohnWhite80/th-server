package com.github.th.server.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.th.server.service.CoreService;

@Controller
public class CoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(CoreController.class);
	
	@Autowired
	private CoreService coreService; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String home() throws Exception {
		return "Hello World"; 
	}
	
	@RequestMapping(value = "/download/peer.apk", method = RequestMethod.GET)
	public @ResponseBody FileSystemResource download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		File client = coreService.getCurrentClientFile();
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", client.getName());
        response.setHeader(headerKey, headerValue);
		return new FileSystemResource(client); 
	}
	
	@RequestMapping(value = "/demo.json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map<String, String>> getDemo(@RequestParam("current_user_email") String current_user_email) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "OK");
		result.put("message", "welcome:" + current_user_email);
		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/demo.json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, String>> postDemo(@RequestParam("name") String name) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "OK");
		result.put("message", "created:" + name);
		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/demo.json", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Map<String, String>> putDemo(@RequestParam("name") String name) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "OK");
		result.put("message", "updated:" + name);
		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/demo.json", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Map<String, String>> deleteDemo(@RequestParam("name") String name) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", "OK");
		result.put("message", "deleted:" + name);
		return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> postLogin(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
		Object o = coreService.login(email, password);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "OK");
		result.put("message", "created:" + o);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
