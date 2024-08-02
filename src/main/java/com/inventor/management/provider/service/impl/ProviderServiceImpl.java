package com.inventor.management.provider.service.impl;

import com.inventor.management.provider.entity.Provider;
import com.inventor.management.providerOrder.entity.ProviderOrder;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.provider.dto.ProviderDto;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.provider.mapper.ProviderMapper;
import com.inventor.management.providerOrder.repository.ProviderOrderRepository;
import com.inventor.management.provider.repository.ProviderRepository;
import com.inventor.management.provider.service.ProviderService;
import com.inventor.management.validators.ProviderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderOrderRepository providerOrderRepository;
    private final ProviderMapper providerMapper;

    private Provider findProvider(Long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Nothing Provider with ID ="+ providerId + "was found in DataBase",
                        ErrorCodes.PROVIDER_NOT_FOUND)
                );
    }

    @Override
    public ProviderDto saveProvider(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if(!errors.isEmpty()){
            log.error("Provider is invalid" + providerDto);
            throw new InvalidEntityException("Provider is invalid", ErrorCodes.PROVIDER_NOT_VALID,errors);
        }

        Provider provider = providerMapper.fromProviderDto(providerDto);
        Provider savedProvider = providerRepository.save(provider);
        return providerMapper.fromProvider(savedProvider);
    }

    @Override
    public ProviderDto updateProvider(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid" + providerDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.PROVIDER_NOT_VALID,errors);
        }

        var updatedProvider = providerRepository.save(providerMapper.fromProviderDto(providerDto));
        return providerMapper.fromProvider(updatedProvider);
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
        List<Provider> providerList = providerRepository.findAll();

        return providerList.stream()
                .map(providerMapper::fromProvider).collect(Collectors.toList());
    }

    @Override
    public void deleteProvider(Long id) {
        if(id == null){
            log.error("id is invalid");
            return;
        }

        List<ProviderOrder> providerOrders = providerOrderRepository.findAllByProviderId(id);
        if(!providerOrders.isEmpty()){
            throw new InvalidOperationException("Unable to delete a provider that has already provider orders ",
                    ErrorCodes.PROVIDER_ALREADY_IN_USE);
        }

        providerRepository.deleteById(id);
    }
}
