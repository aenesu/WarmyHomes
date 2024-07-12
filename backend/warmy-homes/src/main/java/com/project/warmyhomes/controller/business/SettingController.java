package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.service.business.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @PostMapping("/db-reset") // http://localhost:8080/settings/db-reset + POST
    @PreAuthorize("hasAnyAuthority('Admin')")
    public void resetDatabase(){
        settingService.resetDatabase();
    }
}
