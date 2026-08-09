package com.orderms.payment_service.controller;

import com.orderms.payment_service.dto.PaymentRequestDTO;
import com.orderms.payment_service.dto.PaymentResponseDTO;
import com.orderms.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/addPayment")
    public ResponseEntity<PaymentResponseDTO> createPayment(
            @Valid @RequestBody  PaymentRequestDTO paymentRequestDTO) {
       PaymentResponseDTO response = paymentService.createPayment(paymentRequestDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}" )
    public ResponseEntity<PaymentResponseDTO> getPaymentById(
            @PathVariable Long id) {

        PaymentResponseDTO response = paymentService.getPaymentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments(){
        List<PaymentResponseDTO> response = paymentService.getAllPayments();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO>  updatePayment(
            @PathVariable Long id,
            @Valid @RequestBody  PaymentRequestDTO paymentRequestDTO
    ){
        PaymentResponseDTO response = paymentService.updatePayment(id,paymentRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }





}
