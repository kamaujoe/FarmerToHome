import { Component, OnInit } from '@angular/core';
import { ConsumerService } from '../user.service';
import { Consumer } from '../consumer';

@Component({
  selector: 'app-consumer-registration',
  templateUrl: './consumer-registration.component.html',
  styleUrls: ['./consumer-registration.component.css']
})
export class ConsumerRegistrationComponent implements OnInit {

  consno: number
  firstName: String
  lastName: String
  email: String
  address: String
  phone: number

  consumer: Consumer[]

  constructor(private consumerSvc:ConsumerService) {
  
  this.consno = 18
  this.firstName = "First Name"
  this.lastName = "Last Name"
  this.email = "Email Address"
  this.address = "Address"
  this.phone = 12345

  
   }

  ngOnInit() {
    this.fetchCurrentConsumerFromService()
  }

  showConsumer() {
    this.loadAllConsumers()
  }

  fetchCurrentConsumerFromService() {
    this.consumerSvc.findUserByUserId(this.consno).subscribe(
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

  registerConsumerDetails() {
    this.consumerSvc.registerUserOnServer({
      consno:this.consno, 
      firstName:this.firstName,
      lastName:this.lastName,
      email:this.email,
      address:this.address,
      phone:this.phone
    }).subscribe(
      response =>{
        this.fetchCurrentConsumerFromService()
        console.log(response)
      }
    )
  }

  loadAllConsumers() {
    this.consumerSvc.loadAllUsersOnServer()
        .subscribe(
          response => {
            this.consumer = response
          }
        )
  }
}
