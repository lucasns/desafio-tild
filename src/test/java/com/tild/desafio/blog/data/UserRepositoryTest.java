package com.tild.desafio.blog.data;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tild.desafio.blog.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenFindByNameIgnoreCaseContaining_returnUsers() {
		User user1 = new User();
		user1.setName("Test User 1");
		user1.setTwitter("test1");
		entityManager.persistAndFlush(user1);
		
		User user2 = new User();
		user2.setName("Test User 2");
		user2.setTwitter("test2");
		entityManager.persistAndFlush(user2);
		
	    List<User> found = userRepository.findByNameIgnoreCaseContaining("test");
	 
	    Assert.assertThat(found.size(), Matchers.is(2));
	    Assert.assertThat(found.get(0).getName(), Matchers.is("Test User 1"));
	    Assert.assertThat(found.get(1).getName(), Matchers.is("Test User 2"));
	}
}
