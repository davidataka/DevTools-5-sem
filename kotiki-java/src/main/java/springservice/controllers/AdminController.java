package springservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springservice.dto.CatResponse;
import springservice.dto.OwnerResponse;
import springservice.entity.CatsEntity;
import springservice.services.OwnerServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OwnerServices ownerService;

    @PostMapping("/makeAdmin")
    @ResponseBody
    public String makeAdmin(Integer id) {
        ownerService.addAdmin(id);
        return String.format("%d is an admin now", id);
    }


    @GetMapping("/getById")
    @ResponseBody
    public OwnerResponse getByID(Integer id) {
        return new OwnerResponse(ownerService.getOwner(id));
    }

    @GetMapping("/getCatOfOwner")
    @ResponseBody
    public List<CatResponse> getCatOfOwner(Integer id) {
        List<CatsEntity> cats = ownerService.getAllCatsOfOwner(id);
        List<CatResponse> responses = new ArrayList<>();
        for (CatsEntity i : cats) {
            responses.add(new CatResponse(i));
        }
        return responses;
    }

    @PatchMapping("/makeOwner")
    //TODO POST
    @ResponseBody
    public ResponseEntity<String> AddFriend(@RequestParam("catId") Integer catId, @RequestParam("ownerId") Integer ownerId) {
        ownerService.addOwnerOfCat(catId, ownerId);
        return new ResponseEntity<>(String.format("%d and %d are owner and cat", catId, ownerId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public ResponseEntity<String> deleteById(Integer id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(String.format("%d is deleted", id), HttpStatus.OK);
    }

}

