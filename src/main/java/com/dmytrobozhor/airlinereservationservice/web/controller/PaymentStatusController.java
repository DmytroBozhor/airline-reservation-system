package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.AirportDto;
import com.dmytrobozhor.airlinereservationservice.dto.AirportUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusDto;
import com.dmytrobozhor.airlinereservationservice.dto.PaymentStatusUpdateDto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentStatusDto savePaymentStatus(@RequestBody @Valid PaymentStatusDto paymentStatusDto) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        return paymentStatusMapper.toPaymentStatusDto(
                paymentStatusService.save(paymentStatus));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentStatus(@RequestBody @Valid PaymentStatusDto paymentStatusDto) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        paymentStatusService.delete(paymentStatus);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusDto getPaymentStatus(@PathVariable Integer id) {
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
            @RequestBody @Valid PaymentStatusUpdateDto paymentStatusDto, @PathVariable Integer id) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        return paymentStatusMapper.toPaymentStatusDto(
                paymentStatusService.updateById(id, paymentStatus));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentStatusDto updateOrCreatePaymentStatus(
            @RequestBody @Valid PaymentStatusDto paymentStatusDto, @PathVariable Integer id) {
        var paymentStatus = paymentStatusMapper.toPaymentStatus(paymentStatusDto);
        return paymentStatusMapper.toPaymentStatusDto(
                paymentStatusService.updateOrCreateById(id, paymentStatus));
    }

}
