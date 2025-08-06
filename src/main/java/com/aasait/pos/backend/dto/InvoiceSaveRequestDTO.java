package com.aasait.pos.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceSaveRequestDTO {
    private String saleInvoiceId;
    private List<InvoiceDTO> invoices;
}
