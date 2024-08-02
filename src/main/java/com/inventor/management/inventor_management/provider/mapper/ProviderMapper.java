package com.inventor.management.inventor_management.provider.mapper;

import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.provider.entity.Provider;
import com.inventor.management.inventor_management.providerOrder.entity.ProviderOrder;
import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProviderMapper {
    public ProviderDto fromProvider (Provider provider){
        ProviderDto providerDto = new ProviderDto();
        BeanUtils.copyProperties(provider,providerDto);
        return providerDto;
    }

    public Provider fromProviderDto (ProviderDto providerDto){
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerDto,provider);
        return provider;
    }

    public ProviderOrderDto fromProviderOrder (ProviderOrder providerOrder){
        ProviderOrderDto providerOrderDto = new ProviderOrderDto();
        BeanUtils.copyProperties(providerOrder,providerOrderDto);
        return providerOrderDto;
    }

    public ProviderOrder fromProviderOrderDto(ProviderOrderDto providerOrderDto){
        ProviderOrder providerOrder = new ProviderOrder();
        BeanUtils.copyProperties(providerOrderDto,providerOrder);
        return providerOrder;
    }

    public ProviderOrderLineDto fromProviderOrderLine (ProviderOrderLine providerOrderLine){
        ProviderOrderLineDto providerOrderLineDto = new ProviderOrderLineDto();
        BeanUtils.copyProperties(providerOrderLine,providerOrderLineDto);
        return providerOrderLineDto;
    }

    public ProviderOrderLine fromProviderOrderLineDto (ProviderOrderLineDto providerOrderLineDto){
        ProviderOrderLine providerOrderLine = new ProviderOrderLine();
        BeanUtils.copyProperties(providerOrderLineDto,providerOrderLine);
        return providerOrderLine;
    }
}
