import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserBuyerProfileComponent } from './user-buyer-profile/user-buyer-profile.component';

@Injectable({
  providedIn: 'root'
})
export class ConsumerService {

  rootURL: String
  constructor(private httpsvc:HttpClient) { 
    this.rootURL="http://localhost:8080/consumer"
  }

  findUserByUserId(consno):Observable<UserBuyerProfileComponent>{
    return this.httpsvc.get<UserBuyerProfileComponent>
    (this.rootURL+"/find/"+consno)
  }

  updateUserOnServer(consumer):Observable<UserBuyerProfileComponent> {
    const httpOptions = {
      headers: new HttpHeaders(
        {"Content-Type":"application/x-ww-form-urlencoded"}
      )
    }
    var reqBody = "consno="+consumer.consno+"&name="+consumer.consumer_name+"&address="+consumer.address+"&phone="+consumer.phone
    return this.httpsvc.post<UserBuyerProfileComponent>(
                                      this.rootURL+"/register",
                                      reqBody,httpOptions
    )
  }
}
