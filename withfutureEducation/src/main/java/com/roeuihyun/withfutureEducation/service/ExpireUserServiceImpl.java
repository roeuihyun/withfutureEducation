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
package com.roeuihyun.withfutureEducation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.roeuihyun.withfutureEducation.entity.UserEO;
import com.roeuihyun.withfutureEducation.entityid.UserID;
import com.roeuihyun.withfutureEducation.enums.BizStatusCode;
import com.roeuihyun.withfutureEducation.exception.BizException;
import com.roeuihyun.withfutureEducation.store.UserStore;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpireUserServiceImpl implements UserService{
	
	private final UserStore userStore;
	
	@Transactional
	public UserEO insertUser(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById( (UserID)param.get("userID") );
		if( currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_EXIST);
		}
		userStore.save((UserEO)param.get("userEO"));
		return userStore.findById( (UserID)param.get("userID") ).get();
	}

	public List<UserEO> getAllUser() {
		return (List<UserEO>) userStore.findAll();
	}

	public UserEO getUserById(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById( (UserID)param.get("userID") );
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		return currentUser.get();
	}

	@Transactional
	public UserEO putUser(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById( (UserID)param.get("userID") );
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		UserEO updateParam = (UserEO)param.get("userEO");
		UserEO updateReturn = currentUser.get();
		updateReturn.setUser_id(updateParam.getUser_id());
		updateReturn.setUser_name(updateParam.getUser_name());
		updateReturn.setUser_email(updateParam.getUser_email());
		updateReturn.setUser_addr(updateParam.getUser_addr());
		return updateReturn;
	}

	@Transactional
	public UserEO deleteUserById(HashMap<String, Object> param) {
		Optional<UserEO> currentUser = userStore.findById( (UserID)param.get("userID") );
		if( !currentUser.isPresent() ) {
			throw new BizException(BizStatusCode.USER_NOT_FOUND);		
		}
		userStore.deleteById( (UserID)param.get("userID") );
		return currentUser.get();
	}


}
