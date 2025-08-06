package com.aasait.pos.backend.dto.request;

import lombok.Data;

@Data
public class AddOrderDTO {
    private Long id;
    private String itemId;
    private String batchId;
    private double quantity;
    private double unitPrice;
    private double sellPrice;
}
