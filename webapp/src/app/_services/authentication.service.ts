import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../_models/user';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {  
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;
    baseUrl: string = 'http://localhost:8080/api/usuarios';    
    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(user) {        
        return this.http.post<any>(`${this.baseUrl}`, user)
            .pipe(map(user => {
                console.log("sa")
                // se houver um jwt no retorno, o login foi realizado com sucesso
                if (user && user.token) {
                    // armazena o usuário e o token no local storage
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }
                return user;
            }));
    }

    logout() {
        // remove o usuário do local storage
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}
