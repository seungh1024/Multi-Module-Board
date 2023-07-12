package com.seungh1024.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
 * 공통으로 사용되는 필드
 *
 * @Author 강승훈
 * @Since 2023.07.11
 *
 * */
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass // 이 어노테이션이 선언되어 있는 클래스는 엔티티가 아니다. 단순히 해당 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공한다.
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
