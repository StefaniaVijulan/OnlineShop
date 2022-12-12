import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {Designer} from "../models/designer";

@Injectable({
  providedIn: 'root'
})
export class DesignerService {

  private baseUrl = environment.baseUrl;

  public myProductId:any;
  constructor(private _http: HttpClient, private _router: Router) {  }

  public getAllDesigners(){
    return this._http.get<Designer[]>(this.baseUrl + '/designer/all');
  }
  public getMyProduct(){
    return this._http.get<Designer[]>(this.baseUrl + '/designer/myproducts?id=' + localStorage.getItem("id_user"));
  }
  public editStatus(status: any){
    console.log(status)
    console.log(this.myProductId)
    return this._http.put<any>(this.baseUrl + "/designer/editproduct?id="+this.myProductId+"&status=" +status, null)
  }
  public addPhoto(photo: any){
    console.log(photo)
    console.log(this.myProductId)
    return this._http.put<any>(this.baseUrl + "/designer/addImg?id="+this.myProductId+"&img=" +photo, null)
  }
  public editPrice(price: any){
    console.log(price)
    console.log(this.myProductId)
    return this._http.put<any>(this.baseUrl + "/designer/editprice?id="+this.myProductId+"&price=" +price, null)
  }
}
