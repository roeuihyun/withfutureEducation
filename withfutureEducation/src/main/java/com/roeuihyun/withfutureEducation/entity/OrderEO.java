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
package com.roeuihyun.withfutureEducation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.roeuihyun.withfutureEducation.entityid.OrderID;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert // insert 되기 전에 엔티티에 설정된 컬럼 정보 중 null이 아닌 컬럼만을 이용하여 동적 insert 쿼리를 생성
@DynamicUpdate // 엔티티 update 할 때, 변경된 컬럼정보만을 이용하여 동적 쿼리를 생성
@Table(name = "orders") // 테이블명과 클래스명이 다를경우
@IdClass(OrderID.class)
public class OrderEO implements AuditEntity{
	
	@Id
	@Column(name = "order_id", nullable = false)
	@ApiModelProperty(value = "주문번호", dataType = "long", required = true)  
	private long order_id;
	
	@Id
	@Column(name = "user_id", nullable = false)
	@ApiModelProperty(value = "아이디", dataType = "long", required = true)  
	private long user_id;
	
	@Column(name = "order_name", nullable = false) 
	@ApiModelProperty(value = "주문명", dataType = "string", required = false)
	private String order_name;
	
	@Column(name = "order_desc", nullable = true) 
	@ApiModelProperty(value = "주문상세", dataType = "string", required = false)
	private String order_desc;
	
	@Column(name = "order_qty", nullable = true) 
	@ApiModelProperty(value = "주문양", dataType = "string", required = false)
	private String order_qty;

	@Override
	public OrderID getId() {
		return new OrderID(order_id, user_id);
	}

	@Transient
    boolean isNew = true;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    @PostPersist
    @PostLoad
    public void modifyIsNew(){
        this.isNew = false;
    }
	
}
