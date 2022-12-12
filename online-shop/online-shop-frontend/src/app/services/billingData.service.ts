import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BillingData} from "../models/billingData";

@Injectable({
  providedIn: 'root'
})
export class BillingDataService {

  private baseUrl = environment.baseUrl;
  constructor(private _http: HttpClient, private _router: Router) {}

  public addBillingData(billingData: BillingData){
    return this._http.post<BillingData>(this.baseUrl + '/billingData/save', billingData);
  }

}
