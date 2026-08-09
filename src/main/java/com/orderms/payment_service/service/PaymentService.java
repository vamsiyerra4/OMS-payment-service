package com.orderms.payment_service.service;

import com.orderms.payment_service.dto.PaymentRequestDTO;
import com.orderms.payment_service.dto.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO getPaymentById(Long id);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO);
    void deletePayment(Long id);
}
