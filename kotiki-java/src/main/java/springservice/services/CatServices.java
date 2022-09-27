package springservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springservice.dto.CatRequest;
import springservice.entity.CatsEntity;
import springservice.repository.CatRepository;
import springservice.repository.ColorRepository;

@Service
public class CatServices {

    @Autowired
    private CatRepository catRepository;
    @Autowired
    private ColorRepository colorRepository;


    public CatsEntity addCat(CatRequest catDto) {
        CatsEntity cat = new CatsEntity();
        cat.setName(catDto.getName());
        cat.setDatebirth(catDto.getDataBirth());
        cat.setBreed(catDto.getBreed());
        if (catDto.getColorId() != null)
            cat.setColorsByColorid(colorRepository.getById(catDto.getColorId()));
        return catRepository.save(cat);
    }

    public CatsEntity getCat(Integer id) {
        return catRepository.getById(id);
    }


    public void deleteCat(Integer id) {
        CatsEntity cat = getCat(id);
        catRepository.delete(cat);
    }

    public CatsEntity AddFriend(Integer id, Integer id2) {
        CatsEntity cat = getCat(id);
        CatsEntity cat1 = getCat(id2);
        if (id < id2) {
            catRepository.addFriendcat(cat,cat1);
            catRepository.save(cat);
        } else if (id2 < id) {
            catRepository.addFriendcat(cat1,cat);
            catRepository.save(cat1);
        }
        return cat;
    }


}
