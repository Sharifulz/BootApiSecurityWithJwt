package com.ctrends.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ctrends.model.MyUser;

public interface IUserDao extends JpaRepository<MyUser, UUID> {
	
	@Query("FROM MyUser WHERE username=?1")
	public MyUser getUserByUsername(String username);
}
