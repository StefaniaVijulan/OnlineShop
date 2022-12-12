import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {ShopCart} from "../models/shopCart";
import {Comments} from "../models/comments";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  private baseUrl = environment.baseUrl;
  loggedUser = localStorage.getItem('user');

  constructor(private _http: HttpClient, private _router: Router) {
  }

  public addComment(comment: Comments){
    return this._http.post<Comments>(this.baseUrl + '/comments/addComments', comment);
  }

  public getCommentsForProduct(productId: number){
    return this._http.get<Comments[]>(this.baseUrl + '/comments/getAllComments/' + productId);
  }

  public updateTextComment(comments: Comments, newText: string){
    return this._http.patch<Comments>(this.baseUrl + '/comments/updateComment/' + newText, comments);
  }

  public deleteComment(commentId: number){
    return this._http.delete(this.baseUrl + '/comments/deleteComment/' + commentId);

  }
}
