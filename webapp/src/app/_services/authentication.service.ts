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

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${config.apiUrl}/usuarios/authenticate`, { username, password })
            .pipe(map(user => {
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