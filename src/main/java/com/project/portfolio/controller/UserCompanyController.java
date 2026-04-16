package com.project.portfolio.controller;


import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.dto.UserCompanyRequest;
import com.project.portfolio.dto.UserCompanyResponce;
import com.project.portfolio.model.UserCompany;
import com.project.portfolio.service.UserCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyMessages.COMPANY_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    @PostMapping
    public ResponseEntity<UserCompany> createUserCompany(@RequestBody UserCompanyRequest userCompanyRequest){

            UserCompany userCompany=    userCompanyService.createUserCompany(userCompanyRequest);
            return  ResponseEntity.ok().body(userCompany);

    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<UserCompanyResponce>> getUserCompanyByUserId(@PathVariable String userid){
      List<UserCompanyResponce> userCompanies= userCompanyService.getUserCompanyByUserId(userid);
      return  ResponseEntity.ok().body(userCompanies);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserCompanyResponce> getUserCompanyById(@PathVariable String id){
        UserCompanyResponce userCompanyById =userCompanyService.getUserCompanyById(id);
        return  ResponseEntity.ok().body(userCompanyById);
    }

    @PutMapping()
    public ResponseEntity<UserCompanyResponce> updateUserCompany(@RequestBody UserCompanyRequest userCompanyRequest,
                                                         @RequestParam String companyId){
        UserCompanyResponce userCompany= userCompanyService.updateUserCompany(userCompanyRequest,companyId);
       return  ResponseEntity.ok().body(userCompany);
    }

    @DeleteMapping
    public String deleteUserCompany(@RequestParam String id){
      return   userCompanyService.deleteUserCompany(id);
    }

}
