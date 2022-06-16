package com.invertor.management.mapper;

import com.invertor.management.dto.*;
import com.invertor.management.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StockMapperImpl {
     public AddressDto fromAddress (Address address){
         AddressDto addressDto = new AddressDto();
         BeanUtils.copyProperties(address,addressDto);
         return addressDto;
     }

     public Address fromAddressDto (AddressDto addressDto){
         Address address = new Address();
         BeanUtils.copyProperties(addressDto,address);
         return address;
     }

     public ArticleDto fromArticle (Article article){
         ArticleDto articleDto = new ArticleDto();
         BeanUtils.copyProperties(article,articleDto);
         return articleDto;
     }

     public Article fromArticleDto (ArticleDto articleDto){
         Article article = new Article();
         BeanUtils.copyProperties(articleDto,article);
         return article;
    }

    public CategoryDto fromCategory (Category category){
         CategoryDto categoryDto = new CategoryDto();
         BeanUtils.copyProperties(category,categoryDto);
         return categoryDto;
    }

    public Category fromCategoryDto (CategoryDto categoryDto){
         Category category = new Category();
         BeanUtils.copyProperties(categoryDto,category);
         return category;
    }

    public CustomerDto fromCustomer (Customer customer){
         CustomerDto customerDto = new CustomerDto();
         BeanUtils.copyProperties(customer,customerDto);
         return customerDto;
    }

    public Customer fromCustomerDto (CustomerDto customerDto){
         Customer customer = new Customer();
         BeanUtils.copyProperties(customerDto,customer);
         return customer;
    }

    public CustomerOrderDto fromCustomerOrder (CustomerOrder customerOrder){
         CustomerOrderDto customerOrderDto = new CustomerOrderDto();
         BeanUtils.copyProperties(customerOrder,customerOrderDto);
         return customerOrderDto;
    }

    public CustomerOrder fromCustomerOrderDto (CustomerOrderDto customerOrderDto){
        CustomerOrder customerOrder = new CustomerOrder();
        BeanUtils.copyProperties(customerOrderDto,customerOrder);
        return customerOrder;
    }

    public CustomerOrderLineDto fromCustomerOrderLine (CustomerOrderLine customerOrderLine){
         CustomerOrderLineDto customerOrderLineDto = new CustomerOrderLineDto();
         BeanUtils.copyProperties(customerOrderLine,customerOrderLineDto);
         return customerOrderLineDto;
    }

    public CustomerOrderLine fromCustomerOrderLineDto (CustomerOrderLineDto customerOrderLineDto){
         CustomerOrderLine customerOrderLine = new CustomerOrderLine();
         BeanUtils.copyProperties(customerOrderLineDto,customerOrderLine);
         return customerOrderLine;
    }

    public EnterpriseDto fromEnterprise (Enterprise enterprise){
         EnterpriseDto enterpriseDto = new EnterpriseDto();
         BeanUtils.copyProperties(enterprise,enterpriseDto);
         return enterpriseDto;
    }

    public Enterprise fromEnterpriseDto (EnterpriseDto enterpriseDto){
         Enterprise enterprise = new Enterprise();
         BeanUtils.copyProperties(enterpriseDto,enterprise);
         return enterprise;
    }

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

    public RolesDto fromRoles (Roles roles){
         RolesDto rolesDto = new RolesDto();
         BeanUtils.copyProperties(roles,rolesDto);
         return rolesDto;
    }

    public Roles fromRolesDto (RolesDto rolesDto){
         Roles roles = new Roles();
         BeanUtils.copyProperties(rolesDto,roles);
         return roles;
    }

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

    public StockMovementDto fromStockMovement (StockMovement stockMovement){
         StockMovementDto stockMovementDto = new StockMovementDto();
         BeanUtils.copyProperties(stockMovement,stockMovementDto);
         return stockMovementDto;
    }

    public StockMovement fromStockMovementDto (StockMovementDto stockMovementDto){
         StockMovement stockMovement = new StockMovement();
         BeanUtils.copyProperties(stockMovementDto,stockMovement);
         return stockMovement;
    }

    public UserDto fromUser (User user){
         UserDto userDto = new UserDto();
         BeanUtils.copyProperties(user,userDto);
         return userDto;
    }

    public User fromUserDto (UserDto userDto){
         User user = new User();
         BeanUtils.copyProperties(userDto,user);
         return user;
    }

}
