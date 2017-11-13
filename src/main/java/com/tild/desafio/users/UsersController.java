package com.tild.desafio.users;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/list")
    public ModelAndView listUsers(@RequestParam(name = "page", required=false) Integer pageNumber) {
    	ModelAndView mv = new ModelAndView("user-list");
    	//List<User> users = userRepository.findAll();
    	
    	//PagedListHolder<User> pagedListUsers = new PagedListHolder<>(users);
    	//pagedListUsers.setPageSize(10);
    	
    	
    	int pageSize = 15;
    	int numPages = (int) (userRepository.count() / pageSize);
    	
    	
    	int currPage;
    	if (pageNumber == null){
    		currPage = 1;
    	} else if (pageNumber < 1) {
    		currPage = 1;
    	} else if (pageNumber > numPages)  {
    		currPage = pageNumber;
    	} else {
    		currPage = pageNumber;
    	}
    	System.out.println(currPage);
    	//pagedListUsers.setPage(currPage-1);
    	
    	Page<User> users  = getPage(currPage, pageSize);
    	
    	List<Integer> pageList =  Arrays.asList(1,2,3,4,null,10);
    	mv.addObject("users", users);
    	mv.addObject("numPages", numPages);
    	mv.addObject("currPage", currPage);
    	mv.addObject("pageList", pageList);

        return mv;
    }

	private Page<User> getPage(Integer pageNumber, int pageSize) {
		PageRequest pageRequest = generatePageRequest(pageNumber-1, pageSize);
		Page<User> page = userRepository.findAll(pageRequest);
		
		return page;
	}

	private PageRequest generatePageRequest(Integer pageNumber, Integer pageSize) {
		//CHECKS
    	PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
    	
		return pageRequest;
	}

    @PostMapping
    public ModelAndView createUser(User user) {
        if (user.isValid()) {
            userRepository.save(user);
        }
       
        return new ModelAndView("redirect:/");
    }
}
