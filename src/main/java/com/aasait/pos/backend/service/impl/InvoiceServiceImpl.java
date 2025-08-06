package com.aasait.pos.backend.service.impl;

import com.aasait.pos.backend.dto.InvoiceSaveRequestDTO;
import com.aasait.pos.backend.entity.Invoice;
import com.aasait.pos.backend.repository.InvoiceRepository;
import com.aasait.pos.backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public void saveInvoices(InvoiceSaveRequestDTO requestDTO) {
        String saleInvoiceId = requestDTO.getSaleInvoiceId();

        List<Invoice> invoices = requestDTO.getInvoices().stream()
                .map(dto -> Invoice.builder()
                        .batchId(dto.getBatchId())
                        .itemId(dto.getItemId())
                        .quantity(dto.getQuantity())
                        .saleInvoiceId(saleInvoiceId)
                        .build())
                .toList();

        invoiceRepository.saveAll(invoices);
    }

}
