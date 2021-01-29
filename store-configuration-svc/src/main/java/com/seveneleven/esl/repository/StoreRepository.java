package com.seveneleven.esl.repository;

import com.seveneleven.esl.entity.StoreConfigurationV22;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreConfigurationV22,String> {

    @Query(value ="SELECT * FROM mdlw.STORE_CONFIGURATION_V22 WHERE STORE_ID = :storeId", nativeQuery = true)
    List<StoreConfigurationV22> findByStoreID(@Param("storeId") int storeId);

}