package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.InvoiceSaveRequestDTO;

public interface InvoiceService {
    void saveInvoices(InvoiceSaveRequestDTO requestDTO);
}
