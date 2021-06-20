package com.cg.hims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hims.entities.Agent;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.service.IAgentService;


@RestController
public class IAgentController{

	@Autowired
	private IAgentService agentService;
	
	@GetMapping(value="/agent/all")
	public List<Agent> getAllAgent() {
		return agentService.viewAllAgents();
	}
	
	@PostMapping(value= "/agent/add")
	public Agent addAgent(@Valid @RequestBody Agent newagent) {       
	        return agentService.addAgent(newagent);
	}
	
	@PutMapping(value= "/agent/update/{id}")
    public Agent updateAgent(@Valid @RequestBody Agent updagent, @PathVariable int id)throws AgentNotFoundException {
        Optional<Agent> agt =  agentService.findAgentById(id);
        if (!agt.isPresent()) {
        	throw new AgentNotFoundException("Could not find agent with id-" +id);  
        }
        else  {
                     updagent.setAgentId(id);
                     return agentService.updateAgent(updagent);
        }
	}
	
	
	@DeleteMapping(value= "/agent/delete/{id}")
	public void deleteAgent(@PathVariable int id) throws AgentNotFoundException {

	        Optional<Agent> agt =  agentService.findAgentById(id);
	         
	        	if(!agt.isPresent()) {
		        	throw new AgentNotFoundException("Could not find agent with id-" +id);
		        }
		        		
		        else   
		                agentService.removeAgent(id);
	        
	        
	}
	
	@GetMapping(value= "/agent/{id}")
    public Agent getAgent(@PathVariable int id)throws AgentNotFoundException {
        Optional<Agent> agent = agentService.findAgentById(id);
        if(!agent.isPresent()) {
        	throw new AgentNotFoundException("Could not find agent with id-" +id);      
        }
        else
            return agent.get();       
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
     
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
         
        return errors;
    }
}
