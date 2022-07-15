package com.netflix.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.netflix.api.entity.NetflixData;

public interface NetflixDataRepository extends JpaRepository<NetflixData,String> {

}
