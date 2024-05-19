package com.project.warmyhomes.controller.business;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

    @PostMapping("/db-reset") // /settings/db-reset
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> dbReset(){
        return ResponseEntity.ok("");
    }
}
