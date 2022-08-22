package com.appsdeveloperblog.app.ws.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.appsdeveloperblog.app.ws.ul.model.request.UserDetails;

public interface ItemRepository extends MongoRepository<UserDetails, String>{
//	@Query("{userId:'?0'}")
//	UserDetails findItemByName(String userId);
	
//	@Query(value="{category:'?0'}", fields="{'name':1, 'quantity': 1}")
//	List<UserDetails> findAll(String firstName);
	
	public long count();
	
}
