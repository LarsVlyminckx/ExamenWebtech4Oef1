package edu.ap.spring.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.spring.redis.RedisService;

public class ExamenController {
private RedisService service;
	
	@Autowired
	public void setService(RedisService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String cocktail() {
		return "Cocktail";
	}
	
	@PostMapping("/inhaalexamen")
	public String setCocktail(
			@RequestParam("ing1") String student,
			@RequestParam("ing2") String exam,
			@RequestParam("ing3") String reason,
			@RequestParam("ing4") String date) {
		
		String key = "inhaalexamen:" + student;
		Map<String, String> ingredienten = new HashMap<String,String>();
		
		ingredienten.put("Ingredient1", student);
		ingredienten.put("Ingredient2", exam);
		ingredienten.put("Ingredient3", reason);
		ingredienten.put("Ingredient4", date);
		service.hset(key, ingredienten);
		
		return "Inhaalexamen";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	   public String list() {

		   String html = "<HTML><head>\n" + 
		   		"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n" + 
		   		"\n" + 
		   		"<link rel='stylesheet' href='/resources/css/bootstrap.min.css'>\n" + 
		   		"\n" + 
		   		"<title>Grade List</title>\n" + 
		   		"</head>";
		   /*
		   // get the bitcount of our counter
		   html += "<BODY><br/><br/><ul>";
		   
		   Set<String[]> cocktails = service.keys("cocktails:*");
		   for(String[] m : cocktails) {
			   // make a key from the byte array
			   String key = new String(m);
			   // get hash with actors
			   Map<Object, Object> ingredients = service.hgetAll(key);
			   // get all parts of the key, eg : ["cocktails", "gin", "classic"]
			   String[] parts = key.split(":");
			   
			   html += "<li>" + parts[0] + " " + parts[1] + "<br/>";
			   html += "ingredients : ";
			   // iterate over actors
			   for(Entry<Object, Object> entry : ingredients.entrySet()) {
				   html += entry.getValue() + ", ";
			   }
			   // strip off last ', '
			   html = html.substring(0, html.length() - 2);
		   }*/
		   html += "</BODY></HTML>";
		   
		   return html;
	   }

}
