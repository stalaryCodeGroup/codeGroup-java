package com.stalary.codeGroup.repo;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @Author:Stalary
 * @Description:配置JPA
 * @Date Created in 下午6:07 17/7/25
 */
public class BaseRepoImpl<T, Integer extends Serializable> extends SimpleJpaRepository <T, Integer> implements BaseRepo <T, Integer> {


    private final EntityManager entityManager;

    public BaseRepoImpl(JpaEntityInformation <T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;

    }


}
