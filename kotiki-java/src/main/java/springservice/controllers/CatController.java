package springservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springservice.dto.CatRequest;
import springservice.dto.CatResponse;
import springservice.services.CatServices;


@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatServices catServices;


    @GetMapping("/getById")
    @ResponseBody
    public CatResponse getCat(Integer id) {
        return new CatResponse(catServices.getCat(id));
    }


    @PostMapping("/add")
    @ResponseBody
    public CatResponse addCat(@RequestBody CatRequest catDto) {
        return new CatResponse(catServices.addCat(catDto));
    }

    @PatchMapping("/makefriends")
    //TODO POST
    @ResponseBody
    public ResponseEntity<String> AddFriend(@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2) {
        catServices.AddFriend(id1, id2);
        return new ResponseEntity<>(String.format("%d and %d are friends", id1, id2), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(Integer id) {
        catServices.deleteCat(id);
        return new ResponseEntity<>(String.format("%d is deleted", id), HttpStatus.OK);
    }


}
