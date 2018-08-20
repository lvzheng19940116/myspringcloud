package myspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import myspring.bean.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	User findByid(int id);
	
	@Query(value = "SELECT * FROM user g WHERE g.name like %:name% and g.age like %:age% ", nativeQuery = true)  
	List<User> findByLike(@Param(value="name") String name,@Param(value="age") String age);
}

//select * from initiationinfoes where  InitiationName like '%显示%' and ITCode like '%25%';