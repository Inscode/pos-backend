// NetProfitDTO.java
package com.aasait.pos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NetProfitDTO {
    private double totalIncome;
    private double totalCost;
    private double netProfit;
}
