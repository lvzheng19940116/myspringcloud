package myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import myspring.bean.User;
import myspring.repository.UserRepository;

/**
 * @author LvZheng 2018年1月19日下午3:22:43
 */
@RestController
public class UserController {
	@Autowired
	private UserRepository userService;

	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/get")
	public Object get() {
		User findOne = userService.findOne(1);

		return findOne;
	}

	@RequestMapping(value = "get1/{name}/{age}")
	public List<User> get1(@PathVariable("name") String name, @PathVariable("age") String age) {
		List<User> findByLike = userService.findByLike(name, age);
		return findByLike;
	}

	@RequestMapping(value = "get2")
	public List<User> get2() {
		String name = "380";
		String age = "";
		List<User> findByLike = userService.findByLike(name, age);
		return findByLike;
	}
	 @GetMapping("/rest")
	   // @LoadBalanced   ribbon
	    public Object rest() {
	        RestTemplate restTemplate=new RestTemplate();
	        Map<String,String> map= new HashMap<String,String>();
	        map.put("name","乔峰");
	        map.put("sect","丐帮");
	      //  "http://localhost:7777/get?name={name}"
	        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:7777/find/{name}/{sect}", String.class,map);
	        String body = forEntity.getBody();
	        return body;
	    }
}
