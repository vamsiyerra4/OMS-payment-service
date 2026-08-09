package com.orderms.payment_service.mapper;

import com.orderms.payment_service.dto.PaymentRequestDTO;
import com.orderms.payment_service.dto.PaymentResponseDTO;
import com.orderms.payment_service.entity.Payment;
import com.orderms.payment_service.entity.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequestDTO paymentRequestDTO) {

        return Payment.builder()
                .OrderId(paymentRequestDTO.getOrderId())
                .amount(paymentRequestDTO.getAmount())
                .paymentMethod(paymentRequestDTO.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

    }

    public PaymentResponseDTO toResponseDTO(Payment payment) {

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .OrderId(payment.getOrderId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
