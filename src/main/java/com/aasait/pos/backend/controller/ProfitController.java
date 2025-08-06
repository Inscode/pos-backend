// ProfitController.java
package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.NetProfitDTO;
import com.aasait.pos.backend.service.ProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/v1/profit")
@RequiredArgsConstructor
public class ProfitController {

    private final ProfitService profitService;

    @GetMapping("/net")
    public ResponseEntity<NetProfitDTO> getNetProfit(
            @RequestParam int year,
            @RequestParam int month) {
        YearMonth targetMonth = YearMonth.of(year, month);
        NetProfitDTO result = profitService.calculateNetProfit(targetMonth);
        return ResponseEntity.ok(result);
    }
}
