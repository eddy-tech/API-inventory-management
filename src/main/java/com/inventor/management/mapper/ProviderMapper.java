package com.inventor.management.mapper;

import com.inventor.management.dto.ArticleDto;
import com.inventor.management.dto.ProviderDto;
import com.inventor.management.dto.ProviderOrderDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.entities.Article;
import com.inventor.management.entities.Provider;
import com.inventor.management.entities.ProviderOrder;
import com.inventor.management.entities.ProviderOrderLine;
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
