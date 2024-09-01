package com.alicansadeler.instagram.controller;

import com.alicansadeler.instagram.entity.Reaction;
import com.alicansadeler.instagram.entity.UserData;
import com.alicansadeler.instagram.repository.InstagramRepo;
import com.alicansadeler.instagram.util.InstaValidasyon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instagram")
public class InteractionsController {

    private final InstagramRepo manager;

    @Autowired
    public InteractionsController(InstagramRepo manager) {
        this.manager = manager;

    }

    @PostMapping("/users")
    public UserData saveUser(@RequestBody UserData userData) {
        InstaValidasyon.userValid(userData);
        return manager.save(userData);
    }

    @GetMapping("/users")
    public List<UserData> getUsers() {
        InstaValidasyon.allUserValid();
        return manager.getAllUser();
    }

    @GetMapping("/postReaction/{reaction}")
    public List<UserData> getPostReaction(@PathVariable Reaction reaction) {
       InstaValidasyon.postReactionValid(reaction);
        return manager.getUserReactions(reaction);
    }

    @GetMapping("/users/post/postReaction/{postId}")
    public List<Object[]> getUserPostReaction(@PathVariable Integer postId) {
        InstaValidasyon.idValid(postId);
        return manager.getUserByReactionOfPost(postId);
    }

    @GetMapping("/users/post/{id}")
    public List<Object[]> getReactionCount(@PathVariable Integer id) {
        InstaValidasyon.idValid(id);
        return manager.getCountOfReaction(id);
    }

    @GetMapping("/postReaction/most")
    public List<Object[]> getMostReactions() {
        return manager.getMostReaction();
    }
}