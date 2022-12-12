import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DesignerService } from 'src/app/services/designer.service';

@Component({
  selector: 'app-edit-photo',
  templateUrl: './edit-photo.component.html',
  styleUrls: ['./edit-photo.component.css']
})
export class EditPhotoComponent implements OnInit {
  form: any ={};
  constructor(private designer: DesignerService,  private dialogref: MatDialogRef <EditPhotoComponent>) { }

  ngOnInit(): void {
  }
  uploadAvatar($event: any){
    this.form.avatar = $event;

  }
  savePhoto(){
    this.designer.addPhoto(this.form.avatar).subscribe((res)=>{
      console.log(res)
      window.location.reload();
      this.dialogref.close("add");
    })
  }
}
