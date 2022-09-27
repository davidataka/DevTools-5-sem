package springservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springservice.dto.CatResponse;
import springservice.entity.CatsEntity;
import springservice.entity.OwnersEntity;
import springservice.services.OwnerServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerServices ownerService;


    @GetMapping("/getMyCats")
    @ResponseBody
    public List<CatResponse> getMyCats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OwnersEntity owner=ownerService.findByUserName(authentication.getName());
        List<CatsEntity> cats = ownerService.getAllCatsOfOwner(owner.getId());
        List<CatResponse> responses = new ArrayList<>();
        for (CatsEntity i : cats) {
            responses.add(new CatResponse(i));
        }
        return responses;
    }

    @GetMapping("/getByColorId")
    @ResponseBody
    public List<CatResponse> getByColorId(Integer id) {
        List<CatsEntity> cats = ownerService.getByColorId(id);
        List<CatResponse> catResponses = new ArrayList<>();
        for (CatsEntity i : cats) {
            catResponses.add(new CatResponse(i));
        }
        return catResponses;
    }

    @GetMapping("/getCatFriendsId")
    @ResponseBody
    public List<Integer> getCatFriendsId(Integer id) {
        return ownerService.getCatFriendsId(id);
    }

}

