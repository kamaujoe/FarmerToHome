import { Component, OnInit } from '@angular/core';
import { ConsumerService } from '../user.service';

@Component({
  selector: 'app-user-buyer-profile',
  templateUrl: './user-buyer-profile.component.html',
  styleUrls: ['./user-buyer-profile.component.css']
})
export class UserBuyerProfileComponent implements OnInit {

  consno: number
  firstName: String
  lastName: String
  email: String
  address: String
  phone: number

  isEditable: boolean
  isUserFormVisable: boolean

  isUserFormValid: boolean
  invalidFromMessage: boolean

  constructor(private userSvc:ConsumerService) {
    this.isEditable = false
    this.isUserFormVisable = false
    this.isUserFormValid = true

    this.consno=9
    this.firstName="Consumer"
    this.lastName="Last"
    this.email="consumerLast@test.com"
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
        this.firstName = response.firstName
        this.lastName = response.lastName
        this.email = response.email
        this.address = response.address
        this.phone = response.phone
      }
    )
  }

  toggleEdits() {
    this.isEditable = !this.isEditable
   // this.updateUserDetails()
  }

<<<<<<< HEAD
  // updateUserDetails() {
  //   this.userSvc.updateUserOnServer({
  //     consno:this.consno, 
  //     firstName:this.firstName,
  //     lastName:this.lastName,
  //     email:this.email,
  //     address:this.address, 
  //     phone:this.phone
  //   }).subscribe(
  //     response => {
  //       this.fetchCurrentUserFromService()
  //     }
  //   )
  // }
=======
  updateUserDetails() {
    this.userSvc.registerUserOnServer({
      consno:this.consno, 
      firstName:this.firstName,
      lastName:this.lastName,
      email:this.email,
      address:this.address, 
      phone:this.phone
    }).subscribe(
      response => {
        this.fetchCurrentUserFromService()
      }
    )
  }
>>>>>>> a3ff255f7fc51d9ccd0dfdde10c0ad31d137acaf

}
