package com.bmayes.ecommerce.controller;

import com.bmayes.ecommerce.component.StripeClient;
import com.bmayes.ecommerce.dto.Purchase;
import com.bmayes.ecommerce.dto.PurchaseResponse;
import com.bmayes.ecommerce.service.CheckoutService;
import com.stripe.model.Charge;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    private StripeClient stripeClient;

    public CheckoutController(CheckoutService checkoutService, StripeClient stripeClient) {
        this.checkoutService = checkoutService;
        this.stripeClient = stripeClient;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase, String token, String amount) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }

    @PostMapping("/charge")
    public Charge chargeCard(@RequestBody String token, String amount) throws Exception {
        return this.stripeClient.chargeCreditCard(token, amount);
    }

}
