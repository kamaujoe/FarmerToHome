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
  this.firstName = "Miles"
  this.lastName = "Smith"
  this.email = "test"
  this.address = "consumer"
  this.phone = 54321

  
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
