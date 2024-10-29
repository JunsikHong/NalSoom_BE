package com.pick.nalsoom.Repository.Good;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pick.nalsoom.Domain.Good.Good;

public interface GoodRepository extends JpaRepository<Good, Long>{
    // @Query("SELECT g FROM Good g WHERE g.user.id = :userId")
    // List<Good> findGoodsByUserProperNum(@Param("userId") Long userId);

    List<Good> findByUserProperNum(Long userProperNum);
    List<Good> findByUserProperNumAndShelterProperNum(Long userProperNum, Long shelterProperNum);
}
