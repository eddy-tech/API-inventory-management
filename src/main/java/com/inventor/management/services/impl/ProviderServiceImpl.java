package com.inventor.management.services.impl;

import com.inventor.management.entities.Provider;
import com.inventor.management.entities.ProviderOrder;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.ProviderDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.ProviderMapper;
import com.inventor.management.repository.ProviderOrderRepository;
import com.inventor.management.repository.ProviderRepository;
import com.inventor.management.services.interfaces.ProviderService;
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
