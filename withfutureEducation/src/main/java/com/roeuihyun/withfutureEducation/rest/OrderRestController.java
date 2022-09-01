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
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roeuihyun.withfutureEducation.entity.OrderEO;
import com.roeuihyun.withfutureEducation.entityid.OrderID;
import com.roeuihyun.withfutureEducation.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(value = "주문 API" , tags = {"주문 API"} )
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {
	
	private final OrderService orderService;
	
	@ApiOperation(value = "주문 추가", notes = "주문 하나를 추가합니다.")
	@PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<?> insertOrder(@RequestBody OrderEO orderEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", orderEO.getOrder_id());
		param.put("user_id", orderEO.getUser_id());
		param.put("orderID", new OrderID(orderEO.getOrder_id(), orderEO.getUser_id()) );
		param.put("orderEO", orderEO);
		return ResponseEntity.ok().body(orderService.insertOrder(param));
	}
	
	@ApiOperation(value = "주문 전체 조회", notes = "주문 전체를 조회합니다.")
	@GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<?> getAllOrder(
			@ApiParam(value = "PagingNumber", required = true, example = "1", defaultValue = "1")
			@RequestParam int pageNum,
			@ApiParam(value = "PagingSize", required = true, example = "10", defaultValue = "10")
			@RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return ResponseEntity.ok().body(PageInfo.of(orderService.getAllOrder()));
	}
	
	@ApiOperation(value = "주문 조회", notes = "주문 하나를 조회합니다.")
	@GetMapping(value="/{order_id}/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<?> getOrderById(@PathVariable Long order_id, @PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", order_id);
		param.put("orderID", new OrderID(order_id, user_id) );
		return ResponseEntity.ok().body(orderService.getOrderById(param));
	}
	
	@ApiOperation(value = "주문 수정", notes = "주문 하나를 수정합니다.")
	@PutMapping(produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<?> putOrder( @RequestBody OrderEO orderEO) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", orderEO.getOrder_id());
		param.put("user_id", orderEO.getUser_id());
		param.put("orderID", new OrderID(orderEO.getOrder_id(), orderEO.getUser_id()) );
		param.put("orderEO", orderEO);
		return ResponseEntity.ok().body(orderService.putOrder(param));
	}
	
	@ApiOperation(value = "주문 삭제", notes = "주문 하나를 삭제합니다.")
	@DeleteMapping(value="/{order_id}/{user_id}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<?> deleteOrderById(@PathVariable Long order_id, @PathVariable Long user_id) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("order_id", order_id);
		param.put("orderID", new OrderID(order_id, user_id) );
		return ResponseEntity.ok().body(orderService.deleteOrderById(param));
	}
}
