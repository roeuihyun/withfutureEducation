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

import com.roeuihyun.withfutureEducation.entity.OrderEO;

public interface OrderService {

	OrderEO insertOrder(HashMap<String, Object> param);

	List<OrderEO> getAllOrder();

	OrderEO getOrderById(HashMap<String, Object> param);

	OrderEO putOrder(HashMap<String, Object> param);

	OrderEO deleteOrderById(HashMap<String, Object> param);

}
