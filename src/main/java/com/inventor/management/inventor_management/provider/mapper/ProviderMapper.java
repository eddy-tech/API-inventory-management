package com.inventor.management.inventor_management.provider.mapper;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.core.mapper.AddressMapper;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.dto.ProviderRequest;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderRequest;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.provider.entity.Provider;
import com.inventor.management.inventor_management.providerOrder.entity.ProviderOrder;
import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProviderMapper {
    private final EnterpriseMapper enterpriseMapper;
    private final AddressMapper addressMapper;
    private final ArticleMapper articleMapper;

    public Provider fromProviderRequest (ProviderRequest providerRequest, Enterprise enterprise){
        return Provider.builder()
                .name(providerRequest.name())
                .surname(providerRequest.surname())
                .mail(providerRequest.mail())
                .address(addressMapper.toAddress(providerRequest.addressDto()))
                .picture(providerRequest.picture())
                .numTel(providerRequest.numTel())
                .enterprise(enterprise)
                .creationTime(Instant.now())
                .build();
    }

    public ProviderDto fromProvider (Provider provider){
        return ProviderDto.builder()
                .id(provider.getId())
                .name(provider.getName())
                .mail(provider.getMail())
                .surname(provider.getSurname())
                .picture(provider.getPicture())
                .id_enterprise(enterpriseMapper.fromEnterprise(provider.getEnterprise()))
                .addressDto(addressMapper.fromAddress(provider.getAddress()))
                .numTel(provider.getNumTel())
                .build();
    }

    public ProviderRequest toProviderRequest(ProviderDto providerDto) {
        return ProviderRequest.builder()
                .name(providerDto.getName())
                .surname(providerDto.getSurname())
                .mail(providerDto.getMail())
                .addressDto(providerDto.getAddressDto())
                .numTel(providerDto.getNumTel())
                .picture(providerDto.getPicture())
                .id_enterprise(providerDto.getId_enterprise().getId())
                .build();
    }

    public Provider toProvider(ProviderDto providerDto) {
        return Provider.builder()
                .name(providerDto.getName())
                .surname(providerDto.getSurname())
                .mail(providerDto.getMail())
                .address(addressMapper.toAddress(providerDto.getAddressDto()))
                .numTel(providerDto.getNumTel())
                .picture(providerDto.getPicture())
                .enterprise(enterpriseMapper.fromEnterpriseDto(providerDto.getId_enterprise()))
                .creationTime(Instant.now())
                .build();
    }

    public ProviderOrder fromProviderOrderRequest(ProviderOrderRequest providerOrderRequest, Provider provider){
        return ProviderOrder.builder()
                .dateOrdering(providerOrderRequest.dateOrder())
                .stateOrder(providerOrderRequest.stateOrder())
                .provider(provider)
                .creationTime(Instant.now())
                .build();
    }

    public ProviderOrderDto fromProviderOrder (ProviderOrder providerOrder){
        return ProviderOrderDto.builder()
                .codeProviderOrder(providerOrder.getCodeProviderOrder())
                .dateOrdering(providerOrder.getDateOrdering())
                .stateOrder(providerOrder.getStateOrder())
                .providerDto(this.fromProvider(providerOrder.getProvider()))
                .id_enterprise(providerOrder.getProvider().getEnterprise().getId())
                .build();
    }

    public ProviderOrder toProviderOrder (ProviderOrderDto providerOrderDto){
        return ProviderOrder.builder()
                .dateOrdering(providerOrderDto.getDateOrdering())
                .stateOrder(providerOrderDto.getStateOrder())
                .provider(this.toProvider(providerOrderDto.getProviderDto()))
                .creationTime(Instant.now())
                .build();
    }

    public ProviderOrderLineDto fromProviderOrderLine (ProviderOrderLine providerOrderLine){
        return ProviderOrderLineDto.builder()
                .unitPrice(providerOrderLine.getUnitPrice())
                .quantity(providerOrderLine.getQuantity())
                .articleDto(articleMapper.fromArticleDto(providerOrderLine.getArticle()))
                .providerOrderDto(this.fromProviderOrder(providerOrderLine.getProviderOrder()))
                .build();
    }

    public ProviderOrderLine fromProviderOrderLineDto (
            ProviderOrderLineDto providerOrderLineDto, Article article, ProviderOrder providerOrder
    ){
        return ProviderOrderLine.builder()
                .quantity(providerOrderLineDto.getQuantity())
                .unitPrice(providerOrderLineDto.getUnitPrice())
                .article(article)
                .providerOrder(providerOrder)
                .creationTime(Instant.now())
                .build();
    }
}
