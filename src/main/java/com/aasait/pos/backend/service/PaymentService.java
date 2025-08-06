package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.PaymentDTO;

public interface PaymentService {
    void savePayment(PaymentDTO dto);
}
