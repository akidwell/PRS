import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JsonResponse } from '@model/json-response.class';
import { HttpClient } from '@angular/common/http';
import { PurchaseRequest } from '@model/purchase-request.class';

@Injectable({
  providedIn: 'root'
})
export class PurchaseRequestService {

  url: string = "http://localhost:8080/purchase-requests/";

  constructor(private http: HttpClient) { }
  
  list(): Observable<JsonResponse> {
    return this.http.get(this.url) as Observable<JsonResponse>;
  }

  create(pr: PurchaseRequest): Observable<JsonResponse> {
    return this.http.post(this.url+"submit-new", pr) as Observable<JsonResponse>;
  }

  get(prId: string): Observable<JsonResponse>{
    return this.http.get(this.url+prId) as Observable<JsonResponse>;
 }
 
 edit(prId: PurchaseRequest): Observable<JsonResponse>{
   return this.http.put(this.url, prId) as Observable<JsonResponse>;
   
 }
 
 remove(pr: PurchaseRequest): Observable<JsonResponse>{
   return this.http.delete(this.url+pr.id) as Observable<JsonResponse>;
 }

 submitForReview(pr: PurchaseRequest): Observable<JsonResponse>{
   return this.http.put(this.url+"submit-review", pr) as Observable<JsonResponse>;
 }

 listForReview(id: number): Observable<JsonResponse> {
  return this.http.get(this.url+"list-review/"+id) as Observable<JsonResponse>;
}

approve(pr: PurchaseRequest):Observable<JsonResponse>{
  return this.http.put(this.url+"approve", pr) as Observable<JsonResponse>;

}

reject(pr: PurchaseRequest):Observable<JsonResponse>{
  return this.http.put(this.url+"reject", pr) as Observable<JsonResponse>;

}
}