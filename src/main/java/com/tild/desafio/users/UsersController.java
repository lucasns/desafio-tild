package com.tild.desafio.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public ModelAndView newUser(){
        ModelAndView mv = new ModelAndView("new-user");
        mv.addObject("newUser", new User());

        return mv;
    }

    @PostMapping
    public ModelAndView createUser(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:/");
    }
}
