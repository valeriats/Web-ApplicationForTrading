package com.teamc.controller.rest;


import com.teamc.model.Share;
import com.teamc.service.ShareService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
public class ShareControllerRest {

    private  ShareService shareService;

    @Autowired
    public ShareControllerRest(ShareService shareService) {
        this.shareService = shareService;
    }


    @GetMapping(value = "/get/share", produces = "application/json")
    public List<Share> getShare() {
        return shareService.findAll();
    }
}