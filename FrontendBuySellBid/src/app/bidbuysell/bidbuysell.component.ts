import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-bidbuysell',
  templateUrl: './bidbuysell.component.html',
  styleUrls: ['./bidbuysell.component.css']
})
export class BidbuysellComponent implements OnInit {

  sellerRating:number
  buyersRating:number
  sellers:any
  buyers:any
  products:any
  items:any
  data:any
  qty:any
  minQty:any
  avlQty:any
  availData:any
  buyerName:any
  itemId:any
  showHide:any
  sRating:any
  bRating:any
  name:any
  showSellers:boolean

  constructor(private todoDataService:TodoDataService) { }

  ngOnInit() {
  }

  public getSeller(sellerRating) {
    this.todoDataService.displayTopSeller(this.sellerRating).subscribe((response) => {
      this.sellers = response;
      this.showSellers = true;
    });
  }

  public getBuyer(buyersRating) {
    this.todoDataService.displayTopBuyerOrder(this.buyersRating).subscribe((response) => {
      this.buyers = response;
    });
  }

  public getProducts(){
    this.todoDataService.displayCertifiedRefurbishedProducts().subscribe((response) => { 
      this.products=response;
    });
  }
  
  public getTopBiddingAmount(){
    this.todoDataService.displayTopBiddingAmount().subscribe((response) => { 
      this.qty=response;
    });
  }

  public getItemShippingType(){
    this.todoDataService.displayItemShippingType().subscribe((response) => { 
      this.items=response;
    });
  }

  public getItemMinAvailableQty(minQty){
    this.todoDataService.displayItemMinAvailableQty(this.minQty).subscribe((response) => { 
      this.data=response;
    });
  }

  public getAvailableQty(avlQty){
    this.todoDataService.displaySellerByAvailableQty(this.avlQty).subscribe((response) => { 
      this.availData=response;
    });
  }

  public createOrder(buyerName, itemId, sRating, bRating){
    this.todoDataService.createOrder(this.buyerName,this.itemId, this.sRating, this.bRating).subscribe((response)=>{
      this.buyerName=buyerName,this.itemId=itemId,this.sRating= sRating, this.bRating=bRating
    });
  }
  public hideSeller(){
      this.showSellers=false;
      console.log("Show hide", this.showSellers)
  }
  public hide(name){
    console.log("Name", this.name)
    this.showSellers=false;
    console.log("Show hide", this.showSellers)
}
}
