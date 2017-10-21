package com.tild.desafio.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tild.desafio.blog.data.PostRepository;
import com.tild.desafio.blog.data.TagRepository;
import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.Post;

@Controller
@RequestMapping("/posts")
public class PostsController {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    @Autowired
    public PostsController(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/new")
    public ModelAndView newPost(){
        ModelAndView mv = new ModelAndView("new");

        mv.addObject("users", userRepository.findAll());
        mv.addObject("allTags", tagRepository.findAll());
        mv.addObject("newPost", new Post());

        return mv;
    }
    
    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "post", required=false) List<String> post) {
        ModelAndView mv = new ModelAndView("post-page");
        List<String> comments = Arrays.asList("jsahdkashdkjashdkajshdkjashdkj", "akjhfkjashfajkshfkajshfkjahsf"); 
        mv.addObject("comments", comments);
        mv.addObject("posts", postRepository.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView createPost(Post post) {
        if(post.isValid())
            postRepository.save(post);

        return new ModelAndView("redirect:/");
    }
}
