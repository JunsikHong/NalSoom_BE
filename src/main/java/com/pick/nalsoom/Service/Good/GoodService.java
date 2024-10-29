package com.pick.nalsoom.Service.Good;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pick.nalsoom.Dto.Good.GoodDto;
import com.pick.nalsoom.utils.GoodDuplicateException;
import com.pick.nalsoom.utils.NoSuchGoodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pick.nalsoom.Domain.Good.Good;
import com.pick.nalsoom.Repository.Good.GoodRepository;

import javax.swing.text.html.Option;

@Service("goodService")
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    public List<GoodDto> getGoodData(Long userProperNum) {
        List<Good> goodList = goodRepository.findByUserProperNum(userProperNum);
        if(goodList.isEmpty()) {
            throw new NoSuchGoodException("No such good");
        }
        return goodList.stream().map(Good::toDto).collect(Collectors.toList());
    }

    public GoodDto putGoodData(GoodDto goodDto) {
        List<Good> goodList = goodRepository.findByUserProperNumAndShelterProperNum(goodDto.getUserProperNum(), goodDto.getShelterProperNum());
        if(!goodList.isEmpty()) {
            throw new GoodDuplicateException("Good Already exists");
        }
        return goodRepository.save(goodDto.toEntity()).toDto();
    }

    public void deleteGoodData(long goodProperNum) {
        Optional.of(goodRepository.findById(goodProperNum))
                .orElseThrow(() -> new NoSuchGoodException("No such Good"));
        goodRepository.deleteById(goodProperNum);
    }
}
