package com.orderms.payment_service.mapper;

import com.orderms.payment_service.dto.PaymentRequestDTO;
import com.orderms.payment_service.dto.PaymentResponseDTO;
import com.orderms.payment_service.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequestDTO dto) {

        return Payment.builder()
                .orderId(dto.getOrderId())
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .build();
    }

    public PaymentResponseDTO toResponseDTO(Payment payment) {

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
