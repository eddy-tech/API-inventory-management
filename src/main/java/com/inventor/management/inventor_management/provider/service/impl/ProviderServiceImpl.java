package com.inventor.management.inventor_management.provider.service.impl;

import com.inventor.management.core.exceptions.BusinessException;
import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import com.inventor.management.inventor_management.enterprise.repository.EnterpriseRepository;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.dto.ProviderRequest;
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
    private final EnterpriseRepository enterpriseRepository;
    private final ProviderMapper providerMapper;
    private final EnterpriseMapper enterpriseMapper;
    private final ObjectValidator validator;

    @Override
    public Provider findById(Long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Nothing Provider with ID ="+ providerId + "was found in DataBase")
                );
    }

    @Override
    public ProviderDto saveProvider(ProviderRequest providerRequest) {
        validator.validate(providerRequest);
        var enterprise = this.getEnterprise(providerRequest.id_enterprise());

        return providerMapper.fromProvider(
                providerRepository.save(
                        providerMapper.fromProviderRequest(providerRequest, enterprise)
                )
        );
    }

    @Override
    public ProviderDto updateProvider(ProviderRequest providerRequest, Long id) {
        validator.validate(providerRequest);
        var provider = this.getProvider(id);
        var enterprise = this.getEnterprise(providerRequest.id_enterprise());

        provider.setName(providerRequest.name());
        provider.setMail(providerRequest.mail());
        provider.setPicture(providerRequest.picture());
        provider.setNumTel(providerRequest.numTel());
        provider.setAddressDto(providerRequest.addressDto());
        provider.setId_enterprise(enterpriseMapper.fromEnterprise(enterprise));
        provider.setSurname(provider.getSurname());

        return providerMapper.fromProvider(
                providerRepository.save(
                        providerMapper.fromProviderRequest(providerRequest, enterprise)
                )
        );
    }

    @Override
    public ProviderDto getProvider(Long id) {
        if(id == null) {
            log.error("Provider ID is null");
            return null;
        }

        return providerMapper.fromProvider(findById(id));
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

    private Enterprise getEnterprise(Long id) {
        return enterpriseRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Enterprise not found"));
    }
}
