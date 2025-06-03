package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Actor;
import com.nt.repository.IActorRepository;

@Service
public class ActorMgmtServiceImpl implements IActorMgmtService {
	@Autowired
	private IActorRepository actorRepo;
	
	@Override
	public List<Actor> showAllActors() {
		return actorRepo.findAll();
	}

	@Override
	public String registerActor(Actor actor) {
		actor.setCreatedBy(System.getProperty("user.name"));
		//save the Object
		int idVal=actorRepo.save(actor).getAid();
		return "Actor is Saved With "+idVal + " "+"ID Value";
	}

	@Override
	public Actor fetchActorById(int id) {
		return actorRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id"));
	}

	@Override
	public String editActor(Actor actor) {
		Optional<Actor> opt=actorRepo.findById(actor.getAid());
		if(opt.isPresent()) {
			Actor actor1=opt.get();
			actor.setUpdateCount(actor1.getUpdateCount());
			actor.setUpdatedBy(System.getProperty("user.name"));
			actorRepo.save(actor);
			return actor.getAid()+" Actor Data is Modified";
		}
		return actor.getAid()+"Actor Not Found";
	}

	@Override
	public String deleteActor(int id) {
		Optional<Actor> opt = actorRepo.findById(id);
		if(opt.isPresent()) {
			actorRepo.deleteById(id);
			System.out.println("ActorMgmtServiceImpl.deleteActor()");
			return id + "Actor is Deleted";
		}
		return "Actor is Not Found";
	}

}
