package com.cg.hims;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.hims.entities.User;
import com.cg.hims.repository.IUserRepository;
import com.cg.hims.service.IUserService;

@SpringBootTest
class IUserServiceTest {
	
	@Autowired
	IUserService userservice;
	
	@MockBean
	IUserRepository userrepository;

	@Test
	void TestAdduser() {
		User u1=new User();
		u1.setUserid(2);
		u1.setUserName("virat");
		u1.setPassword("king");
		u1.setRole("ceo");
		 Mockito.when((User)this.userrepository.save(u1)).thenReturn(u1);
	        assertThat(this.userservice.addUser(u1)).isEqualTo(u1);
	        
		}

	@Test
	void testGetUser() {
		  User u1 = new User();
		  u1.setUserid(2);
		  u1.setUserName("virat");
		  u1.setPassword("king");
		  u1.setRole("ceo");
	  
	        Optional<User> u2 = Optional.of(u1);
	        Mockito.when(this.userrepository.findById(1)).thenReturn(u2);
	        assertThat(this.userservice.getUser(1)).isEqualTo(u2);
		
	}
	@Test
	void testUpdateUser() throws Exception {
		User u1=new User();
		u1.setUserid(1);
		u1.setUserName("Ravi");
		u1.setRole("Agent");
		
		Optional<User> u2=Optional.of(u1);
		Mockito.when(this.userrepository.findById(1)).thenReturn(u2);
		u1.setUserName("Tharun");
   Mockito.when(this.userrepository.save(u1)).thenReturn(u1);
   assertThat(this.userservice.updateUser(u1)).isEqualTo(u1);
  
	}
	@Test
	void testGetUsers() {
		User u1=new User();
		u1.setUserid(1);
		u1.setUserName("Pujara");
		u1.setRole("Wall");
		
		User u2=new User();
		u2.setUserid(2);
		u2.setUserName("Rahane");
		u2.setRole("ViceCap");
		
		List<User> UserList = new ArrayList<>();
		UserList.add(u1);
		UserList.add(u2);
		
		Mockito.when(this.userrepository.findAll()).thenReturn(UserList);
		
		assertThat(this.userservice.getUsers()).isEqualTo(UserList);
	}
	@Test
	void testFindUserById() {
		User u1=new User();
		u1.setUserid(1);
		u1.setUserName("Pant");
		u1.setRole("Kepper");
		Optional<User> u2=Optional.of(u1);
   Mockito.when(this.userrepository.findById(1)).thenReturn(u2);
   assertThat(this.userservice.findUserById(1)).isEqualTo(u2);
	}

}	

