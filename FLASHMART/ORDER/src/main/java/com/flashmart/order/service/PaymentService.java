package com.flashmart.order.service;

import com.flashmart.order.dto.PaymentDTO;
import com.flashmart.order.model.payment;
import com.flashmart.order.repository.PaymentRepository;
import com.flashmart.order.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public String savePayment(PaymentDTO paymentDTO){
        if(paymentRepository.existsById(paymentDTO.getPaymentId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            paymentRepository.save(modelMapper.map(paymentDTO, payment.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public List<PaymentDTO> getAllPayment(){
        List<payment> paymentList = paymentRepository.findAll();
        return modelMapper.map(paymentList, new TypeToken<ArrayList<PaymentDTO>>(){
        }.getType());
    }

    public PaymentDTO getPaymentById(int orderId){
        if(paymentRepository.existsById(orderId)){
            payment Payment = paymentRepository.findById(orderId).orElse(null);
            return modelMapper.map(Payment, PaymentDTO.class);
        } else {
            return null;
        }
    }

    public String updatePayment(PaymentDTO paymentDTO){
        if(paymentRepository.existsById(paymentDTO.getPaymentId())){
            paymentRepository.save(modelMapper.map(paymentDTO, payment.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}

