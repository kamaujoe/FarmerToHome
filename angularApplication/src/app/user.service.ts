import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserBuyerProfileComponent } from './user-buyer-profile/user-buyer-profile.component';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  rootURL: String
  constructor(private httpsvc:HttpClient) { 
    this.rootURL="http://localhost:9999/user-profile"
  }

  findUserByUserId(consno):Observable<UserBuyerProfileComponent>{
    return this.httpsvc.get<UserBuyerProfileComponent>
    (this.rootURL+"/find/"+consno)
  }

  updateUserOnServer(user):Observable<UserBuyerProfileComponent> {
    const httpOptions = {
      headers: new HttpHeaders(
        {"Content-Type":"application/x-ww-form-urlencoded"}
      )
    }
    var reqBody = "consno="+user.consno+"&name="+user.name+"&address="+user.address+"&phone="+user.phone
    return this.httpsvc.post<UserBuyerProfileComponent>(
                                      this.rootURL+"/register",
                                      reqBody,httpOptions
    )
  }
}
