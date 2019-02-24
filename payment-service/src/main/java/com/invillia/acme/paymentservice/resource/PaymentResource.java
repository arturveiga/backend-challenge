package com.invillia.acme.paymentservice.resource;


import com.invillia.acme.paymentservice.config.ResourceRepository;
import com.invillia.acme.paymentservice.model.Payment;
import com.invillia.acme.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentResource extends ResourceRepository<Payment> {
    private PaymentService paymentService;
    @Autowired
    public PaymentResource(JpaRepository<Payment, Long> repository, PaymentService paymentService) {
        super(repository);
        this.paymentService = paymentService;
    }


    @PostMapping(value = "/order/{idOrder}/paid")
    public ResponseEntity<?> paidOrder(@PathVariable("idOrder")Long idOrder, @RequestBody Payment payment){
        return ResponseEntity.ok(paymentService.savePaidOrder(idOrder,payment));
    }


    @GetMapping(value = "/refundedPayment/{idOrder}")
    public ResponseEntity<?> refundedPayment(@PathVariable("idOrder") Long idOrder){
        return ResponseEntity.ok(paymentService.refundedPayment(idOrder));
    }

}
