package springservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springservice.dto.OwnerRequest;
import springservice.dto.OwnerResponse;
import springservice.services.OwnerServices;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private OwnerServices ownerService;

    @PostMapping("/")
    public String addOwner(@RequestBody OwnerRequest ownerRequest) throws Exception {
        try {
            OwnerResponse ownerResponse = new OwnerResponse(ownerService.addOwner(ownerRequest));
        } catch (Exception e) {
            if (!e.getMessage().isEmpty())
                return e.getMessage();
        }
        return "User created";
    }
}