package com.tild.desafio.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tild.desafio.blog.data.CommentRepository;
import com.tild.desafio.blog.data.PostRepository;
import com.tild.desafio.blog.data.TagRepository;
import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.Comment;
import com.tild.desafio.blog.model.Post;

@Controller
@RequestMapping("/posts")
public class PostsController {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostsController(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/new")
    public ModelAndView newPost(){
        ModelAndView mv = new ModelAndView("new");

        mv.addObject("users", userRepository.findAll());
        mv.addObject("allTags", tagRepository.findAll());
        mv.addObject("newPost", new Post());

        return mv;
    }
    
    @GetMapping
    public ModelAndView getPost(@RequestParam(name = "post", required=false) Long postId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("newComment", new Comment());
        if (postId != null) {
            mv.setViewName("post-page");

            Post post = postRepository.findOne(postId);

            if (post != null) {
                mv.addObject("post", post);
                return mv;
            }
        }
        
        mv.setViewName("redirect:/");
        return mv;
    }

    @PostMapping
    public ModelAndView createPost(Post post) {
        if(post.isValid())
            postRepository.save(post);

        return new ModelAndView("redirect:/");
    }
    
    @PostMapping("/comment")
    public ModelAndView createPost(@RequestParam(name = "post", required=false) Long postId, Comment comment) {
        commentRepository.save(comment); 
        return new ModelAndView("redirect:/posts?post="+postId);
    }
}
