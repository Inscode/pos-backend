package com.aasait.pos.backend.service.impl;

import com.aasait.pos.backend.dto.PaymentDTO;
import com.aasait.pos.backend.entity.Invoice;
import com.aasait.pos.backend.entity.Payment;
import com.aasait.pos.backend.repository.InvoiceRepository;
import com.aasait.pos.backend.repository.PaymentRepository;
import com.aasait.pos.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void savePayment(PaymentDTO dto) {

        Payment payment = new Payment();
        payment.setType(dto.getType());
        payment.setAmount(dto.getAmount());
        payment.setFileName(dto.getFileName());
        payment.setSaleInvoiceId(dto.getSalesInvoiceId());
        // date is set automatically via @PrePersist

        paymentRepository.save(payment);
    }
}
