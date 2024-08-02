package com.inventor.management.sale.mapper;

import com.inventor.management.sale.dto.SaleDto;
import com.inventor.management.saleLine.dto.SaleLineDto;
import com.inventor.management.sale.entity.Sale;
import com.inventor.management.saleLine.entity.SaleLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SaleMapper {
    public SaleDto fromSale (Sale sale){
        SaleDto saleDto = new SaleDto();
        BeanUtils.copyProperties(sale,saleDto);
        return saleDto;
    }

    public Sale fromSaleDto (SaleDto saleDto){
        Sale sale = new Sale();
        BeanUtils.copyProperties(saleDto,sale);
        return sale;
    }

    public SaleLineDto fromSaleLine (SaleLine saleLine){
        SaleLineDto saleLineDto = new SaleLineDto();
        BeanUtils.copyProperties(saleLine,saleLineDto);
        return saleLineDto;
    }

    public SaleLine fromSaleLineDto (SaleLine saleLineDto) {
        SaleLine saleLine = new SaleLine();
        BeanUtils.copyProperties(saleLineDto,saleLine);
        return saleLine;
    }
}
