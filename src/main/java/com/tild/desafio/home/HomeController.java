package com.tild.desafio.home;

import com.tild.desafio.blog.data.PostRepository;
import com.tild.desafio.blog.data.TagRepository;
import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.Post;
import com.tild.desafio.blog.model.Tag;
import com.tild.desafio.blog.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    @Autowired
    public HomeController(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "tags", required=false) List<String> tags,
            @RequestParam(name = "string", required=false) String string) {

        System.out.println(string);

        ModelAndView mv = new ModelAndView("index");
        if (tags != null) {
            List<Tag> tagList = tagRepository.findByNameIn(tags);
            if (tagList.size() > 0) {
                mv.addObject("posts", postRepository.findByTagsIn(tagList));
                return mv;
            }
        }

        if (string != null) {
            List<User> userList = userRepository.findByNameIgnoreCaseContaining(string);
            if (userList.size() > 0) {
                mv.addObject("posts", postRepository.findByTitleIgnoreCaseContainingOrUserIn(string, userList));
                return mv;
            } else {
                mv.addObject("posts", postRepository.findByTitleIgnoreCaseContaining(string));
                return mv;
            }
        }

        mv.addObject("posts", postRepository.findAll());
        return mv;
    }
}
