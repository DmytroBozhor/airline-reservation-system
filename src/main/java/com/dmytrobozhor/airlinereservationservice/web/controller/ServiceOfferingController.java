package com.dmytrobozhor.airlinereservationservice.web.controller;

import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingPartialUpdateDto;
import com.dmytrobozhor.airlinereservationservice.dto.ServiceOfferingSaveDto;
import com.dmytrobozhor.airlinereservationservice.service.AbstractServiceOfferingService;
import com.dmytrobozhor.airlinereservationservice.util.mappers.ServiceOfferingMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: decide whether I need this controller or not because it causes too many bugs
@RestController
@RequestMapping("/service-offerings")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final AbstractServiceOfferingService serviceOfferingService;

    private final ServiceOfferingMapper serviceOfferingMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceOfferingDto> getAllServiceOfferings() {
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.findAll());
    }

    //    TODO: for some reason the save endpoint does not work correctly
    //     it deletes the entity after saving and eve though the updatable=false we still can update nested entities
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOfferingDto saveServiceOffering(@RequestBody @Valid ServiceOfferingSaveDto serviceOfferingDto) {
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        var serviceOfferingId = ServiceOfferingId.builder()
                .travelClassId(serviceOffering.getTravelClass().getId())
                .flightServiceId(serviceOffering.getFlightService().getId()).build();
        serviceOffering.setId(serviceOfferingId);
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.save(serviceOffering));
    }

    @GetMapping("/travel-class/{travelClassId}/flight-service/{flightServiceId}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingDto getServiceOfferingById(
            @PathVariable Integer travelClassId, @PathVariable Integer flightServiceId) {
        var serviceOfferingId = ServiceOfferingId.builder()
                .travelClassId(travelClassId).flightServiceId(flightServiceId).build();
        return serviceOfferingMapper.toServiceOfferingDto(serviceOfferingService.findById(serviceOfferingId));
    }

    @DeleteMapping("/travel-class/{travelClassId}/flight-service/{flightServiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceOfferingById(
            @PathVariable Integer travelClassId, @PathVariable Integer flightServiceId) {
        var serviceOfferingId = ServiceOfferingId.builder()
                .travelClassId(travelClassId).flightServiceId(flightServiceId).build();
        serviceOfferingService.deleteById(serviceOfferingId);
    }

    @PatchMapping("/travel-class/{travelClassId}/flight-service/{flightServiceId}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceOfferingDto updateServiceOffering(
            @RequestBody @Valid ServiceOfferingPartialUpdateDto serviceOfferingDto,
            @PathVariable Integer travelClassId, @PathVariable Integer flightServiceId) {
        var serviceOfferingId = ServiceOfferingId.builder()
                .travelClassId(travelClassId).flightServiceId(flightServiceId).build();
        var serviceOffering = serviceOfferingMapper.toServiceOffering(serviceOfferingDto);
        return serviceOfferingMapper.toServiceOfferingDto(
                serviceOfferingService.updateById(serviceOfferingId, serviceOffering));
    }

}
