package com.aasait.pos.backend.dto;

import lombok.Data;

@Data
public class InvoiceDTO {
    private String batchId;
    private Long itemId;
    private Integer quantity;
}
