import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-buyer-profile',
  templateUrl: './user-buyer-profile.component.html',
  styleUrls: ['./user-buyer-profile.component.css']
})
export class UserBuyerProfileComponent implements OnInit {

  consno: number
  name: String
  address: String
  phone: number

  isEditable: boolean
  isUserFormVisable: boolean

  isUserFormValid: boolean
  invalidFromMessage: boolean

  constructor(private userSvc:UserService) {
    this.isEditable = false
    this.isUserFormVisable = false
    this.isUserFormValid = true

    this.consno=12
    this.name="Consumer"
    this.address="Example"
    this.phone=12345
   }

  ngOnInit() {
    this.fetchCurrentUserFromService()
  }

  fetchCurrentUserFromService(){
    this.userSvc.findUserByUserId(this.consno).subscribe(
      response => {
        this.consno = response.consno
        this.name = response.name
        this.address = response.address
        this.phone = response.phone
      }
    )
  }

  toggleEdits() {
    this.isEditable = !this.isEditable
    this.updateUserDetails()
  }

  updateUserDetails() {
    this.userSvc.updateUserOnServer({
      consno:this.consno, name:this.name,
      address:this.address, phone:this.phone
    }).subscribe(
      response => {
        this.fetchCurrentUserFromService()
      }
    )
  }

}
