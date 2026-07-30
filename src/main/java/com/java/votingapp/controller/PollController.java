package com.java.votingapp.controller;


import com.java.votingapp.model.Poll;
import com.java.votingapp.request.Vote;
import com.java.votingapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping()
    public Poll createPoll(@RequestBody Poll poll){

      return   pollService.createPoll(poll);
    }

    @GetMapping()
    public List<Poll> getAllPolls(){

        return pollService.getAllPolls();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getAllPolls(@PathVariable Long id){

        return pollService.getPollById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){

         pollService.vote(vote.getPollId(),vote.getOptionIndex());
    }

}
