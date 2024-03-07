package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusCreateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusReadDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.service.PaymentStatusService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.paymentstatus.PaymentStatusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-statuses")
@RequiredArgsConstructor
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;

    private final PaymentStatusMapper paymentStatusMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentStatusReadDto> getAllPaymentStatuses() {
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.findAll());
    }

    //    TODO: fix decimal(12,2) problem because the data base does not even throw exception
    //     but instead rounds the number
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentStatusReadDto savePaymentStatus(
            @RequestBody @Valid PaymentStatusCreateDto PaymentStatusReadDto) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(PaymentStatusReadDto);
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.save(paymentStatus));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusReadDto getPaymentStatusById(@PathVariable Long id) {
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusReadDto deletePaymentStatusById(@PathVariable Long id) {
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.deleteById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusReadDto updatePaymentStatus(
            @RequestBody @Valid PaymentStatusPartialUpdateDto PaymentStatusReadDto, @PathVariable Long id) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(PaymentStatusReadDto);
        return paymentStatusMapper.toPaymentStatusDto(paymentStatusService.updateById(id, paymentStatus));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusReadDto updateOrCreatePaymentStatus(
            @RequestBody @Valid PaymentStatusCreateDto PaymentStatusReadDto, @PathVariable Long id) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(PaymentStatusReadDto);
        return paymentStatusMapper.toPaymentStatusDto(
                paymentStatusService.updateOrCreateById(id, paymentStatus));
    }

}
