package com.inventor.management.inventor_management.provider.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.repository.ProviderRepository;
import com.inventor.management.inventor_management.provider.entity.Provider;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.provider.mapper.ProviderMapper;
import com.inventor.management.inventor_management.providerOrder.repository.ProviderOrderRepository;
import com.inventor.management.inventor_management.provider.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderOrderRepository providerOrderRepository;
    private final ProviderMapper providerMapper;
    private final ObjectValidator validator;

    private Provider findProvider(Long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Nothing Provider with ID ="+ providerId + "was found in DataBase")
                );
    }

    @Override
    public ProviderDto saveProvider(ProviderDto providerDto) {
        validator.validate(providerDto);

        return providerMapper.fromProvider(
                providerRepository.save(providerMapper.fromProviderDto(providerDto))
        );
    }

    @Override
    public ProviderDto updateProvider(ProviderDto providerDto, Long id) {
        validator.validate(providerDto);

        var provider = this.getProvider(id);
        provider.setName(providerDto.getName());
        provider.setMail(providerDto.getMail());
        provider.setPicture(providerDto.getPicture());
        provider.setNumTel(providerDto.getNumTel());
        provider.setAddressDto(providerDto.getAddressDto());
        provider.setId_enterprise(providerDto.getId_enterprise());
        provider.setSurname(provider.getSurname());

        return providerMapper.fromProvider(
                providerRepository.save(
                        providerMapper.fromProviderDto(providerDto)
                )
        );
    }

    @Override
    public ProviderDto getProvider(Long id) {
        if(id == null) {
            log.error("Provider ID is null");
            return null;
        }

        return providerMapper.fromProvider(findProvider(id));
    }

    @Override
    public List<ProviderDto> listProvider() {
        return providerRepository.findAll()
                .stream()
                .map(providerMapper::fromProvider)
                .toList();
    }

    @Override
    public void deleteProvider(Long id) {
        if(id == null){
            log.error("id is invalid");
            return;
        }

        var providerOrders = providerOrderRepository.findAllByProviderId(id);
        if(!providerOrders.isEmpty()){
            throw new InvalidOperationException("Unable to delete a provider that has already provider orders");
        }

        providerRepository.deleteById(id);
    }
}
