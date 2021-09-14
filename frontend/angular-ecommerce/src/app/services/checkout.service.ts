import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Purchase } from '../common/purchase';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  private purchaseUrl = environment.apiUrl + '/checkout/purchase';
  private chargeUrl = environment.apiUrl + '/checkout/charge';


  constructor(private httpClient: HttpClient) { }

  placeOrder(purchase: Purchase): Observable<any> {
    return this.httpClient.post<Purchase>(this.purchaseUrl, purchase);
  }

  //method to send Stripe token to backend server
  chargeCard(token: string, purchase: Purchase) {
    console.log("token was created, now hitting the service...");
    const headers = new HttpHeaders({'token': token, 'amount': purchase.order.totalPrice.toString()});
    this.httpClient.post(this.chargeUrl, {}, {headers: headers}).subscribe(resp => {console.log(resp);
    })
  }
}
