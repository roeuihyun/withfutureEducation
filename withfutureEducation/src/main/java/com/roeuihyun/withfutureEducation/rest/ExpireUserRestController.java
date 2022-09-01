/*=================================================================================
 *                        Copyright(c) 2022 WithFuture
 *
 * Project                : withfutureEducation
 * Description            :
 * Author                 : 노의현
 * Version                : 1.0.0
 * Created Date           : 2022.09.01
 * Updated Date           : 2022.09.01
 * Last Modifier          : 노의현
 * Updated Contents       : 
 * 2022.09.01 최초 작성
 *===============================================================================*/
package com.roeuihyun.withfutureEducation.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roeuihyun.withfutureEducation.entity.UserEO;
import com.roeuihyun.withfutureEducation.entityid.UserID;
import com.roeuihyun.withfutureEducation.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "만료된 사용자 API" , tags = {"만료된 사용자 API"} )
@RestController
@RequestMapping("/ExpireUsers")
public class ExpireUserRestController {
	
	@Autowired
	@Qualifier("expireUserServiceImpl")
	private UserService userService;
	
	@ApiOperation(value = "EXPIRE USER 추가", notes = "EXPIRE USER 하나를 추가합니다.")
	@PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> insertUser(@RequestBody UserEO userEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", userEO.getUser_id());
		param.put("userID", new UserID(userEO.getUser_id()) );
		param.put("userEO", userEO);
		return ResponseEntity.ok().body(userService.insertUser(param));
	}
	
	@ApiOperation(value = "EXPIRE USER 전체 조회", notes = "EXPIRE USER 전체를 조회합니다.")
	@GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAllUser(
			@ApiParam(value = "PagingNumber", required = true, example = "1", defaultValue = "1")
			@RequestParam int pageNum,
			@ApiParam(value = "PagingSize", required = true, example = "10", defaultValue = "10")
			@RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return ResponseEntity.ok().body(PageInfo.of(userService.getAllUser()));
	}
	
	@ApiOperation(value = "EXPIRE USER 조회", notes = "EXPIRE USER 하나를 조회합니다.")
	@GetMapping(value="/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getUserById(@PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", user_id);
		param.put("userID", new UserID(user_id) );
		return ResponseEntity.ok().body(userService.getUserById(param));
	}
	
	@ApiOperation(value = "EXPIRE USER 수정", notes = "EXPIRE USER 하나를 수정합니다.")
	@PutMapping(produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> putUser( @RequestBody UserEO userEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", userEO.getUser_id());
		param.put("userID", new UserID(userEO.getUser_id()) );
		param.put("userEO", userEO);
		return ResponseEntity.ok().body(userService.putUser(param));
	}
	
	@ApiOperation(value = "EXPIRE USER 삭제", notes = "EXPIRE USER 하나를 삭제합니다.")
	@DeleteMapping(value="/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteUserById(@PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user_id", user_id);
		param.put("userID", new UserID(user_id) );
		return ResponseEntity.ok().body(userService.deleteUserById(param));
	}
}
