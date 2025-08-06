package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.*;
import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.service.InvoiceService;
import com.aasait.pos.backend.service.ItemService;
import com.aasait.pos.backend.service.OrderService;
import com.aasait.pos.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cashier")
@RequiredArgsConstructor
public class ItemController {

    private  final ItemService itemService;
    @GetMapping("/get-items")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> itemDTOs = itemService.getAllItems();
        return ResponseEntity.ok(itemDTOs);
    }

    private final OrderService orderService;

    @PutMapping("/bulk-update")
    public ResponseEntity<List<OrderDTO>> bulkUpdateOrders(@RequestBody List<OrderDTO> orderDTOs) {
        List<OrderDTO> updated = orderService.updateOrders(orderDTOs);
        return ResponseEntity.ok(updated);
    }

    private final InvoiceService invoiceService;

    @PostMapping("/invoices")
    public ResponseEntity<String> saveInvoices(@RequestBody InvoiceSaveRequestDTO requestDTO) {
        invoiceService.saveInvoices(requestDTO);
        return ResponseEntity.ok("Invoices saved successfully.");
    }

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> createPayment(@RequestBody PaymentDTO dto) {
        paymentService.savePayment(dto);
        return ResponseEntity.ok("Payment saved successfully");
    }



}
