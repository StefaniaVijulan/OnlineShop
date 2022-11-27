import { Component, OnInit } from '@angular/core';
import {Designer} from "../../models/designer";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {DesignerService} from "../../services/designer.service";

@Component({
  selector: 'app-designers-tab',
  templateUrl: './designers-tab.component.html',
  styleUrls: ['./designers-tab.component.css']
})
export class DesignersTabComponent implements OnInit {

  designerList: Designer[] = [];

  constructor(private _httpClient: HttpClient, private _router:Router, private _designerService: DesignerService) { }

  ngOnInit(): void {
    this.getDesigners();
  }

  getDesigners(){
    return this._designerService.getAllDesigners()
      .subscribe((res: Designer[]) => {
        this.designerList = res;
        console.log(this.designerList)
      })
  }

}
