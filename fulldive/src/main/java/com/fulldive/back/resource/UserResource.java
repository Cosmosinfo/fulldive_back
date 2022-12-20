package com.fulldive.back.resource;

import com.fulldive.back.config.RandomConfig;
import com.fulldive.back.entity.UserEntity;
import com.fulldive.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserResource{
	
	//create random Id 
	RandomConfig randomConfig = new RandomConfig();
	@Autowired
	UserService userService;
	
	
	
	private Map<String, Object> result = new HashMap<String, Object>(); 
	
	/*
	 * 이메일로 유저정보 조회
	*/
	@PostMapping(value = "/user/findByUserEmail")
	public ResponseEntity<List<UserEntity>> findByUserEmail(@RequestBody Map<String, String> params) {
		String userEmail = params.get("userEmail");
        List<UserEntity> member = userService.findByUserEmail(userEmail);
        return new ResponseEntity<List<UserEntity>>(member, HttpStatus.OK);
    }

	/*
	 * 사용자 로그인
	*/
	@PostMapping(value = "/user/login")
	public Map<String, Object> userLogin(@RequestBody Map<String, String> params) throws Exception {
		
//		System.out.println("jwt: " + this.makeJwtToken());

		System.out.println("RequestParams: " + params);
		List<UserEntity> userList = userService.userLogin(params);
		result.put("responses", 400);
		result.put("exception", "");
		System.out.println(userList.size());
		if(userList.size() == 0 || userList == null) {
			System.out.println("DATA NOT EXSIST");
			    throw new Exception("아이디를 확인해 주시기 바랍니다");
		}else {
			System.out.println("Resource: " + userList.get(0).getUserEmail());
			if(userList.get(0).getUserPassword().equals(params.get("userPassword"))) {
				System.out.println("DATA EXSIST");
				result.put("responses", 200);
				result.put("jwt", "Test");
				result.put("admin", "1");
				result.put("userInfo", userList);
			}else {
				throw new Exception("비밀번호를 확인해 주시기 바랍니다.");
			}
		}		

		return result;	
	}
	
	/*
	 * 사용자 로그아웃
	 * 일단보류
	*/
//	@PostMapping(value = "/user/logout")
//	public int userLogout(@RequestBody Map<String, Object> params) {
//		
//		System.out.println("params: " + params);
//		int result = userService.userJoin(params);
//		return result;
//	}
	
	/*
	 * 사용자 회원가입
	*/
	@PostMapping(value = "/user/join")
	public int userJoin(@RequestBody Map<String, Object> params) {
		
		System.out.println("params: " + params);
		int idChk = this.userJoinIdChk(params);
		if(idChk == 200) {
			String generatedString = randomConfig.getRandom();
			params.put("userId", generatedString);
			int result = userService.userJoin(params);
			return result;
		}else {
			return 400;
		}
	}
	
	
	/*
	 * 사용자 회원가입 (아이디중복확인)
	*/
	@PostMapping(value = "/user/joinIdChk")
	public int userJoinIdChk(@RequestBody Map<String, Object> params) {
		int result = 200;
		System.out.println("params: " + params);
		List<UserEntity> resultList = userService.userJoinIdChk(params);
		if(resultList.size() != 0) {result = 400;}
		return result;
	}
	
	/*
	 * 사용자 정보수정 
	*/
	@PostMapping(value = "/user/userUpdate")
	public int userUpdate(@RequestBody Map<String, Object> params) {
		int result = 200;
		System.out.println("params: " + params);
		int resultList = userService.userUpdate(params);
		System.out.println(resultList);
		if(resultList == 0) {result = 400;}
		return result;
	}
	
	/*
	 * 사용자 정보삭제
	*/
	@PostMapping(value = "/user/userDelete")
	public int userDelete(@RequestBody Map<String, Object> params) {
		int result = 200;
		System.out.println("params: " + params);
		int resultList = userService.userDelete(params);
		System.out.println(resultList);
		if(resultList == 0) {result = 400;}
		return result;
	}
	
//	@GetMapping(value = "/user/jwtToken")
//	public String makeJwtToken() {
//	    Date now = new Date();
//	    
//	    System.out.println("size:" + "MilkoMedaServiceM12312312312312312312312311".length());
//
//	    return Jwts.builder()
//	        .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
//	        .setIssuer("fresh") // (2)
//	        .setIssuedAt(now) // (3)
//	        .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
//	        .claim("id", "아이디") // (5)
//	        .claim("email", "ajufresh@gmail.com")
//	        .signWith(SignatureAlgorithm.HS256, "MilkoMedaServiceM12312312312312312312312311") // (6)
//	        .compact();
//	  }
	
	
	
}
