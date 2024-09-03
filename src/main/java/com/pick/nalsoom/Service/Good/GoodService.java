package com.pick.nalsoom.Service.Good;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.Domain.Good.Good;
import com.pick.nalsoom.Repository.Good.GoodRepository;

@Service("goodService")
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    public List<Good> getGoodData(Long userProperNum) {
        Optional<List<Good>> goods = goodRepository.findByUserProperNum(userProperNum);
        if(goods.isPresent()) {
            return goods.get();
        } else {
            return null;
        }
    }
    
}
