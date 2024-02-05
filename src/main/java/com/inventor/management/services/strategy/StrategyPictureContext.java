package com.inventor.management.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPictureContext {
    private Strategy strategy;
    private final BeanFactory beanFactory;
    @Setter
    private String context;

    @Autowired
    public StrategyPictureContext(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public Object savePicture (String context, Long id, InputStream picture, String title) throws FlickrException {
        determinateContext(context);
        return strategy.savePicture(id,picture,title);
    }

    private void determinateContext (String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "article":
                beanFactory.getBean(beanName, SaveArticlePicture.class);
                break;
            case "enterprise":
                beanFactory.getBean(beanName, SaveEnterprisePicture.class);
                break;
            case "customer":
                beanFactory.getBean(beanName, SaveCustomerPicture.class);
                break;
            case "provider":
                beanFactory.getBean(beanName, SaveProviderPicture.class);
                break;
            case "user":
                beanFactory.getBean(beanName, SaveUserPicture.class);
                break;
            default: throw new InvalidOperationException("unknown context for saving picture", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }


}
