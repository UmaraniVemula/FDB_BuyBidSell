import { TODO_JPA_API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class TodoDataService {

  constructor(
    private http:HttpClient
  ) { }

  createOrder(buyerName, itemId, sRating, bRating){
    const orderItem = {
      "itemId": itemId,
      "buyerName": buyerName,
      "sellerRating":sRating,
      "buyerRating":bRating
    };
    return this.http.post(
                 `${TODO_JPA_API_URL}/placeOrder`, orderItem);
    // return this.http.post(
    //           `${TODO_JPA_API_URL}/placeOrder?itemId=${itemId}&buyerName=${buyername}`, orderItem);
  }

  displayTopSeller(rating){
    return this.http.get(
      `${TODO_JPA_API_URL}/displayTopSeller/${rating}`
        );
  }
  displayTopBuyerOrder(rating){
    return this.http.get(
      `${TODO_JPA_API_URL}/displayTopBuyerOrder/${rating}`
        );
  }

  displayCertifiedRefurbishedProducts(){
    return this.http.get(
      `${TODO_JPA_API_URL}/getCertifiedRefurbishedItems`
        );
  }
  
  displaySellerByAvailableQty(quantity){
    return this.http.get(
      `${TODO_JPA_API_URL}/getSellerByAvailableQty/${quantity}`
        );
  }

  
  displayTopBiddingAmount(){
    return this.http.get(
      `${TODO_JPA_API_URL}/getTopBiddingAmount`
        );
  }

  displayItemShippingType(){
    return this.http.get(
      `${TODO_JPA_API_URL}/getItemShippingType`
      );
  }

  displayItemMinAvailableQty(quantity){
    return this.http.get(
      `${TODO_JPA_API_URL}/getItemMinAvailableQty/${quantity}`
      );
  }
}
