// ProfitServiceImpl.java
package com.aasait.pos.backend.service.impl;

import com.aasait.pos.backend.dto.NetProfitDTO;
import com.aasait.pos.backend.entity.Payment;
import com.aasait.pos.backend.entity.Invoice;
import com.aasait.pos.backend.entity.Order;
import com.aasait.pos.backend.repository.PaymentRepository;
import com.aasait.pos.backend.service.ProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfitServiceImpl implements ProfitService {

    private final PaymentRepository paymentRepository;

    @Override
    public NetProfitDTO calculateNetProfit(YearMonth month) {
        LocalDateTime startDate = month.atDay(1).atStartOfDay();
        LocalDateTime endDate = month.atEndOfMonth().atTime(23, 59, 59);

        List<Payment> monthlyPayments = paymentRepository.findByDateBetween(startDate, endDate);

        double totalIncome = 0;
        double totalCost = 0;

        for (Payment payment : monthlyPayments) {
            Invoice invoice = payment.getSalesInvoice();
            Order order = invoice.getOrder();

            double sellPrice = order.getSellPrice();
            double unitPrice = order.getUnitPrice();
            int quantity = invoice.getQuantity();

            totalIncome += sellPrice * quantity;
            totalCost += unitPrice * quantity;
        }

        double netProfit = totalIncome - totalCost;
        return new NetProfitDTO(totalIncome, totalCost, netProfit);
    }
}
