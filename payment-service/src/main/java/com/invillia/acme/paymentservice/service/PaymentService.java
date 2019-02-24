package com.invillia.acme.paymentservice.service;

import com.invillia.acme.paymentservice.client.OrderClient;
import com.invillia.acme.paymentservice.model.Payment;
import com.invillia.acme.paymentservice.model.enums.PaymentStatus;
import com.invillia.acme.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    private OrderClient orderClient;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(OrderClient orderClient, PaymentRepository paymentRepository) {
        this.orderClient = orderClient;
        this.paymentRepository = paymentRepository;
    }

    public Payment savePaidOrder(Long idOrder, Payment payment){
        try {
            if(!orderClient.findById(idOrder).isPresent()){
                throw new RuntimeException("Order invalid");
            }
            if(paymentRepository.findByIdOrder(idOrder).isPresent()){
                throw new RuntimeException("Order Paid!");
            }
            payment.setIdOrder(idOrder);
            payment.setStatus(PaymentStatus.PAID);
            payment.setPaymentDate(LocalDateTime.now());
            paymentRepository.save(payment);

            //Paid Order
            orderClient.paymentOrder(idOrder,payment.getPaymentDate());
            return payment;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Payment refundedPayment(Long idOrder){
        try {
            Payment payment = paymentRepository.findByIdOrder(idOrder).get();
            if(payment.getStatus().getId().equals(PaymentStatus.REFUNDED.getId())){
                throw new RuntimeException("Payment is refunded");
            }
            payment.setStatus(PaymentStatus.REFUNDED);
            paymentRepository.save(payment);
            return payment;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
