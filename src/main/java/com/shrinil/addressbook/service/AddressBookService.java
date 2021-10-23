package com.shrinil.addressbook.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressBookService {

    @RequestMapping("home")
    public void home(){
    }
}
