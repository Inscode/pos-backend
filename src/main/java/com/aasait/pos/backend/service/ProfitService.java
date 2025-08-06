// ProfitService.java
package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.NetProfitDTO;

import java.time.YearMonth;

public interface ProfitService {
    NetProfitDTO calculateNetProfit(YearMonth month);
}
