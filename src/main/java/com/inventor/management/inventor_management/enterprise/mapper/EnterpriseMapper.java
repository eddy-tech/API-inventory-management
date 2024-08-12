package com.inventor.management.inventor_management.enterprise.mapper;

import com.inventor.management.inventor_management.core.mapper.AddressMapper;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseMapper {
    private final AddressMapper addressMapper;
    public EnterpriseDto fromEnterprise (Enterprise enterprise){
        return EnterpriseDto.builder()
                .name(enterprise.getName())
                .description(enterprise.getDescription())
                .addressDto(addressMapper.fromAddress(enterprise.getAddress()))
                .codeFiscal(enterprise.getCodeFiscal())
                .picture(enterprise.getPicture())
                .mail(enterprise.getMail())
                .numTel(enterprise.getNumTel())
                .siteWeb(enterprise.getSiteWeb())
                .build();
    }

    public Enterprise fromEnterpriseDto (EnterpriseDto enterpriseDto){
        return Enterprise.builder()
                .name(enterpriseDto.getName())
                .description(enterpriseDto.getDescription())
                .address(addressMapper.toAddress(enterpriseDto.getAddressDto()))
                .codeFiscal(enterpriseDto.getCodeFiscal())
                .picture(enterpriseDto.getPicture())
                .mail(enterpriseDto.getMail())
                .numTel(enterpriseDto.getNumTel())
                .siteWeb(enterpriseDto.getSiteWeb())
                .build();
    }
}
