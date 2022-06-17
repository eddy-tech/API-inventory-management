package com.invertor.management.services.impl;

import com.invertor.management.dto.EnterpriseDto;
import com.invertor.management.dto.ProviderDto;
import com.invertor.management.entities.Enterprise;
import com.invertor.management.entities.Provider;
import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.ErrorCodes;
import com.invertor.management.exceptions.InvalidEntityException;
import com.invertor.management.mapper.StockMapperImpl;
import com.invertor.management.repository.ProviderRepository;
import com.invertor.management.services.interfaces.ProviderService;
import com.invertor.management.validators.ProviderValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepository providerRepository;
    private StockMapperImpl dtoMapper;


    @Override
    public ProviderDto saveProvider(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if(!errors.isEmpty()){
            log.error("Provider is invalid",providerDto);
            throw new InvalidEntityException("Provider is invalid", ErrorCodes.PROVIDER_NOT_VALID,errors);
        }

        Provider provider = dtoMapper.fromProviderDto(providerDto);
        Provider savedProvider = providerRepository.save(provider);
        return dtoMapper.fromProvider(savedProvider);
    }

    @Override
    public ProviderDto updateProvider(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid",providerDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.PROVIDER_NOT_VALID,errors);
        }

        Provider updatedProvider = providerRepository.save(dtoMapper.fromProviderDto(providerDto));
        return dtoMapper.fromProvider(updatedProvider);
    }

    @Override
    public ProviderDto getProvider(Long id) {
        if(id == null) {
            log.error("Provider ID is null");
            return null;
        }

        Provider provider = providerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing Provider with ID ="+ id + "was found in DataBase",ErrorCodes.PROVIDER_NOT_FOUND));
        return dtoMapper.fromProvider(provider);
    }

    @Override
    public List<ProviderDto> listProvider() {
        List<Provider> providerList = providerRepository.findAll();
        List<ProviderDto> providerDtoList = providerList.stream()
                .map(provider -> dtoMapper.fromProvider(provider)).collect(Collectors.toList());

        return providerDtoList;
    }

    @Override
    public void deleteProvider(Long id) {
        if(id == null){
            log.error("id is invalid");
            return;
        }

        providerRepository.deleteById(id);

    }
}
