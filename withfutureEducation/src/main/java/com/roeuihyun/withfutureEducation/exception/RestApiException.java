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
package com.roeuihyun.withfutureEducation.exception;

import com.roeuihyun.withfutureEducation.enums.StatusCode;

import lombok.Getter;

@Getter
public class RestApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final StatusCode statusCode;
	
	public RestApiException ( StatusCode statusCode ) {
		super(statusCode.getMessage());
		this.statusCode = statusCode;
	}

}