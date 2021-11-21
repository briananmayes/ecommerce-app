package com.bmayes.ecommerce.component;

import com.bmayes.ecommerce.config.MyAppConfig;
import com.bmayes.ecommerce.entity.Order;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.net.ApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


@Component
public class StripeClient {

    private Charge charge;

    private MyAppConfig myAppConfig;

    private Order order;

    @Autowired
    StripeClient(MyAppConfig myAppConfig, Order order) throws FileNotFoundException {
        this.myAppConfig = myAppConfig;
        this.order = order;
        Stripe.apiKey = myAppConfig.getStripeKey();
    }

    public Charge chargeCreditCard(String token, String amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", Math.round(Double.parseDouble(amount) * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        order.setStripeChargeId(token);
        charge = Charge.create(chargeParams);
        return ApiResource.GSON.fromJson(charge.getLastResponse().body(), Charge.class);
    }
}
