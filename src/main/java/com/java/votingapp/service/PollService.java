package com.java.votingapp.service;

import com.java.votingapp.model.OptionVotes;
import com.java.votingapp.model.Poll;
import com.java.votingapp.repo.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepo;

    public Poll createPoll(Poll poll){
            return pollRepo.save(poll);

    }

    public List<Poll> getAllPolls() {

        return pollRepo.findAll();
    }

    public Optional<Poll> getPollById(Long id) {

 return pollRepo.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {

        //Get all poll from db
        Poll poll=pollRepo.findById(pollId).orElseThrow(()->new RuntimeException("Poll not found"));
        //Get all options
        List<OptionVotes> options = poll.getOptions();
        //if index from poll is not valid,throw error
        if (optionIndex<0 || optionIndex>=options.size()){
            throw new IllegalArgumentException("invalid option index");
        }
        //Get selected option
        OptionVotes selectedOption=options.get(optionIndex);
        //increment vote for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount()+1);
        pollRepo.save(poll);


    }
}
