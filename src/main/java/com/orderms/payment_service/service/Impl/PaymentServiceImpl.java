package com.orderms.payment_service.service.Impl;

import com.orderms.payment_service.dto.PaymentRequestDTO;
import com.orderms.payment_service.dto.PaymentResponseDTO;
import com.orderms.payment_service.entity.Payment;
import com.orderms.payment_service.entity.PaymentStatus;
import com.orderms.payment_service.exception.InvalidPaymentException;
import com.orderms.payment_service.exception.PaymentNotFoundException;
import com.orderms.payment_service.mapper.PaymentMapper;
import com.orderms.payment_service.repository.PaymentRepository;
import com.orderms.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {

        if(paymentRequestDTO.getAmount().signum() <= 0){
            throw new InvalidPaymentException("Payment must be greater than 0");
        }

        Payment payment = paymentMapper.toEntity(paymentRequestDTO);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toResponseDTO(savedPayment);
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment Not Found with id" + id));
        return paymentMapper.toResponseDTO(payment);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {

        return paymentRepository.findAll()
                .stream().map(paymentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment Not Found with id" + id));

        if(paymentRequestDTO.getAmount().signum() <= 0){
            throw new InvalidPaymentException("Payment must be greater than 0");
        }

        payment.setOrderId(paymentRequestDTO.getOrderId());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());

        Payment updatedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponseDTO(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment Not Found with id" + id));
        paymentRepository.delete(payment);



    }
}
