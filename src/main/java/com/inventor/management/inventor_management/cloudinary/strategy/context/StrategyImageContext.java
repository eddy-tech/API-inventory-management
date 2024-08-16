package com.inventor.management.inventor_management.cloudinary.strategy.context;

import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.article.service.strategy.SaveArticlePicture;
import com.inventor.management.inventor_management.cloudinary.strategy.Strategy;
import com.inventor.management.inventor_management.cloudinary.strategy.UserStrategy;
import com.inventor.management.inventor_management.customer.service.strategy.SaveCustomerPicture;
import com.inventor.management.inventor_management.enterprise.service.strategy.SaveEnterprisePicture;
import com.inventor.management.inventor_management.provider.service.strategy.SaveProviderPicture;
import com.inventor.management.inventor_management.user.service.strategy.SaveUserPicture;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class StrategyImageContext {
    private Strategy strategy;
    private UserStrategy userStrategy;
    private final BeanFactory beanFactory;

    @Autowired
    public StrategyImageContext(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public Object savePicture(String context, Long id, MultipartFile file) throws ImageErrorException {
        determinateStrategy(context);
        return strategy.saveImage(id, file);
    }

    public void saveUserPicture(String id, MultipartFile file) throws ImageErrorException {
        var beanName = "userStrategy";
        this.userStrategy = this.beanFactory.getBean(beanName, SaveUserPicture.class);
        this.userStrategy.saveImage(id, file);
    }

    private void determinateStrategy(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "article":
                this.strategy = this.beanFactory.getBean(beanName, SaveArticlePicture.class);
                break;
            case "enterprise":
                this.strategy = this.beanFactory.getBean(beanName, SaveEnterprisePicture.class);
                break;
            case "customer":
                this.strategy = this.beanFactory.getBean(beanName, SaveCustomerPicture.class);
                break;
            case "provider":
                this.strategy = this.beanFactory.getBean(beanName, SaveProviderPicture.class);
                break;
            default: throw new InvalidOperationException("unknown context to saving picture");
        }
    }
}
