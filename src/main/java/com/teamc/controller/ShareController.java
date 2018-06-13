package com.teamc.controller;

import com.teamc.model.Share;
import com.teamc.service.ShareService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@NoArgsConstructor
public class ShareController {

    private ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/share")
    public String getShares(Model model) {
        model.addAttribute("share", new Share());
        return "share";
    }

    @PostMapping("/share")
    public String submitShares(@ModelAttribute("share") @Validated Share share, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            shareService.save(share);
        }
        return "share";
    }
}
