package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Actor;
import com.nt.service.IActorMgmtService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.servlet.http.HttpSession;
@Controller
public class ActorOperationsController {
	@Autowired
	private IActorMgmtService actorService;
	
	@GetMapping("/")
	public String showHomePage() {
		System.out.println("ActorOperationsController.showHomePage()");
		return "Welcome";
	}
	
	@GetMapping("/report")
	public String showReport(Map<String , Object> map) {
		System.out.println("ActorOperationsController.showReport()");
		List<Actor> list = actorService.showAllActors();
		map.put("listActors", list);
		return "show_report";
	}
	
	@GetMapping("/register")
	public String showFormForRegisterActor(@ModelAttribute("actor") Actor actor) {
		System.out.println("ActorOperationsController.showFormForRegisterActor()");
		return "actor_register_form";
	}
	
//	@PostMapping("/register")         //  Problem Here- Double posting Problem 
//	public String registerActor(@ModelAttribute("actor") Actor actor, Map<String , Object> map) {
//		String msg=actorService.registerActor(actor);
//		List<Actor> list=actorService.showAllActors();
//		//keep the result in model Attribute 
//		map.put("resultMsg", msg);
//		map.put("listActors", list);
//		//return LVN
//		return "show_report";
//	}
	
	
//	@PostMapping("/register")     // Solution Using PRG Method (Post-Redirect-Get)
//	public String registerActor(@ModelAttribute("actor") Actor actor, Map<String , Object> map) {
//		String msg=actorService.registerActor(actor);
//		System.out.println("ActorOperationsController.registerActor()");
//		//keep the result in model Attribute 
//		map.put("resultMsg", msg);
//		//return LVN
//		return "redirect:report";
//	}
	
//	@PostMapping("/register")     // Solution- 2 Using PRG Method (Post-Redirect-Get)
//	public String registerActor(@ModelAttribute("actor") Actor actor, RedirectAttributes attrs) {
//		String msg=actorService.registerActor(actor);
//		System.out.println("ActorOperationsController.registerActor()");
//		//keep the result in model Attribute 
//		attrs.addFlashAttribute("resultMsg", msg); 
//		//return LVN
//		return "redirect:report";
//	}
	
	@PostMapping("/register")     // Solution 3 Using PRG Method (Post-Redirect-Get)
	public String registerActor(@ModelAttribute("actor") Actor actor, HttpSession ses) {
		String msg=actorService.registerActor(actor);
		System.out.println("ActorOperationsController.registerActor()");
		//keep the result in model Attribute 
		ses.setAttribute("resultMsg", msg);
		//return LVN
		return "redirect:report";
	}
	@GetMapping("/edit")
	public String editActorFormPage(@ModelAttribute("actor") Actor actor, @RequestParam("no") int id) {
		//use the service
		Actor actor1=actorService.fetchActorById(id);
		//copy actor1 object data to actor object(Model Class)
		BeanUtils.copyProperties(actor1, actor);
		//return LVN
		return "actor_edit_form";
	}
	@PostMapping("/edit")
	public String editActor(@ModelAttribute("actor") Actor actor,RedirectAttributes attrs) {
		String msg=actorService.editActor(actor);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:report";
	}
	@GetMapping("/delete")
	public String deleteActor(@RequestParam("no") int id, RedirectAttributes attrs) {
		String msg=actorService.deleteActor(id);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:report";
	}
}
