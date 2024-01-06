package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractPaymentStatusService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.PaymentStatusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-statuses")
@RequiredArgsConstructor
public class PaymentStatusController {

    private final AbstractPaymentStatusService paymentStatusService;

    private final PaymentStatusMapper paymentStatusMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentStatusDto> getAllPaymentStatuses() {
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.findAll());
    }

    //    TODO: fix decimal(12,2) problem because the data base does not even throw exception
    //     but instead rounds the number
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentStatusDto savePaymentStatus(
            @RequestBody @Valid PaymentStatusSaveDto paymentStatusDto) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.save(paymentStatus));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusDto getPaymentStatusById(@PathVariable Integer id) {
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentStatusById(@PathVariable Integer id) {
        paymentStatusService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusDto updatePaymentStatus(
            @RequestBody @Valid PaymentStatusPartialUpdateDto paymentStatusDto, @PathVariable Integer id) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.updateById(id, paymentStatus));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusDto updateOrCreatePaymentStatus(
            @RequestBody @Valid PaymentStatusSaveDto paymentStatusDto, @PathVariable Integer id) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        return paymentStatusMapper.toPaymentStatusDto(
                paymentStatusService.updateOrCreateById(id, paymentStatus));
    }

}
