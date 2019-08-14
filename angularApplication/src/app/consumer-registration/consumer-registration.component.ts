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
  consumerUsername: String
  consumerPassword: String

  consumer: Consumer[]

  constructor(private consumerSvc:ConsumerService) {
  
  this.consno=1
  this.firstName
  this.lastName
  this.email
  this.address
  this.phone
  this.consumerUsername
  this.consumerPassword

  
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
        this.consumerUsername = response.consumerUsername
        this.consumerPassword = response.consumerPassword
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
      phone:this.phone,
      consumerUsername:this.consumerUsername,
      consumerPassword:this.consumerPassword
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
