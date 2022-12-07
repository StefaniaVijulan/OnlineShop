import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../../models/product";
import {ProductService} from "../../../services/product.service";
import {ShopCartService} from "../../../services/shopCart.service";
import {User} from "../../../models/user";
import {ShopCart} from "../../../models/shopCart";
import {NgForm} from "@angular/forms";
import {Comments} from "../../../models/comments";
import {CommentsService} from "../../../services/comments.service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  idProduct: number = 0;
  currentProduct: Product = new Product();
  addedToCart: boolean = false;
  loggedUserStorage = localStorage.getItem('user');
  userTypeStorage = localStorage.getItem('type');
  userType: String = '';
  loggedUser: User = new User();
  productSaved: boolean = false;
  shopCartSaved: ShopCart = new ShopCart();
  commentsList: Comments[] = [];
  editCommentBool: boolean = false;
  editCommentId: number = 0;
  editCommentText: string = ''

  constructor(private _router:Router, private _route: ActivatedRoute, private _productService: ProductService,
              private _shopCartService: ShopCartService, private _commentsService: CommentsService) { }

  ngOnInit(): void {
    if(this.loggedUserStorage){
      this.loggedUser = JSON.parse(this.loggedUserStorage);
    }
    if(this.userTypeStorage){
      this.userType = this.userTypeStorage;
    }
    this.idProduct = Number(this._route.snapshot.url.toString().replace('productDetails,',''));
    this.getCurrentProduct();
  }

  getCurrentProduct(){
    return this._productService.getProductById(this.idProduct)
      .subscribe((res: Product) => {
        this.currentProduct = res;
        this.getAllCommentsForProduct();
        if(this.userType == 'user'){
          this._shopCartService.checkProductInShopCart(this.idProduct, this.loggedUser.id).subscribe((resp: boolean) =>
              this.addedToCart = resp
          )
          this._shopCartService.checkIfProductIsSaved(this.idProduct, this.loggedUser.id).subscribe((resp: ShopCart) => {
            if (resp == null) {
                  this.productSaved = false;
              }
              else{
                this.shopCartSaved = resp;
                this.productSaved = true;
            }
            }
          )
        }
      })
  }

  addToCart(){
    this._shopCartService.addProductToCart(this.currentProduct, false).subscribe();
    this.addedToCart = true;
  }

  saveOrDeleteProductFromSaves(){
    if(!this.productSaved){
      this._shopCartService.addProductToCart(this.currentProduct, true).subscribe((resp: ShopCart) =>{
        this.shopCartSaved = resp;
        this.productSaved = true;
    })}
    else{
      this._shopCartService.deleteProductInCart(this.shopCartSaved.id).subscribe();
      this.productSaved = false;
    }
  }

  addComment(addCommentForm: NgForm) {
    if (addCommentForm.form.value.commentText == '') {
      return;
    }

    const transientComment = new Comments();
    transientComment.product = this.currentProduct;
    transientComment.client = this.loggedUser;
    transientComment.description = addCommentForm.form.value.commentText
    transientComment.date = new Date().toISOString();

    this._commentsService.addComment(transientComment).subscribe((res:Comments) => {
      addCommentForm.resetForm();
      this.getAllCommentsForProduct();
    });
  }

  getAllCommentsForProduct(){
    return this._commentsService.getCommentsForProduct(this.currentProduct.id)
      .subscribe((res: Comments[]) => {
        this.commentsList = res;
      })
  }

  editComment(com: Comments){
    this.editCommentBool = true;
    this.editCommentId = com.id;
    this.editCommentText = com.description;
  }

  deleteComment(com: Comments){
    this._commentsService.deleteComment(com.id)
      .subscribe(() => {
        this.getAllCommentsForProduct();
      })
  }


  submitEditComment(editCommentForm: NgForm, com:Comments) {

    if (editCommentForm.form.value.editText == "") {
      this.editCommentText = com.description
      return
    }

    this._commentsService.updateTextComment(com, this.editCommentText).subscribe((res:Comments) => {
      this.getAllCommentsForProduct();
      this.editCommentBool = false;
      this.editCommentId = 0;
    });
  }
}
