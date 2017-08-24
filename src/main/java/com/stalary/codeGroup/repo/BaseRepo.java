package com.stalary.codeGroup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author:Stalary
 * @Description:
 * @Date Created in 下午6:07 17/7/25
 */
@NoRepositoryBean
public interface BaseRepo<T, Integer extends Serializable> extends JpaRepository<T, Integer> {

}
