package com.example.mongo_user.app.controllers;

import com.example.mongo_user.app.dtos.LuckySpinGiftDTO;
import com.example.mongo_user.domain.entities.LuckySpinGift;
import com.example.mongo_user.domain.services.LuckySpinGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "v1/metrics/luckyspingifts")
public class LuckySpinGiftController {

    @Autowired
    private LuckySpinGiftService luckySpinGiftService;

    @GetMapping(path = "")
    public List<LuckySpinGift> getLuckySpinGift() throws IOException {
        return luckySpinGiftService.getLuckySpinGifts();
    }

    @PostMapping(path = "")
    public Integer createLuckySpinGift(@RequestBody LuckySpinGiftDTO dto) {
        return luckySpinGiftService.create(dto);
    }
}
