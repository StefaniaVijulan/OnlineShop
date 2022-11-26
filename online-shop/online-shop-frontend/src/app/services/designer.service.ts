import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {Product} from "../models/product";
import {Designer} from "../models/designer";

@Injectable({
  providedIn: 'root'
})
export class DesignerService {

  private baseUrl = environment.baseUrl;

  constructor(private _http: HttpClient, private _router: Router) {  }

  public getAllDesigners(){
    return this._http.get<Designer[]>(this.baseUrl + '/designer/all');
  }

}
