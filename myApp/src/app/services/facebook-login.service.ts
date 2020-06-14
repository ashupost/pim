import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import { Platform } from '@ionic/angular';
import { Plugins, registerWebPlugin } from '@capacitor/core';
import { FacebookLoginResponse } from '@rdlabo/capacitor-facebook-login';
import { NavigationExtras } from '@angular/router';
const { FacebookLogin } = Plugins;

const FACEBOOK_PERMISSIONS = ['email', 'user_birthday', 'user_photos', 'user_gender'];

@Injectable()
export class FaceBookLoginService {
    
    constructor(
        private __platform: Platform,
        private __afAuth: AngularFireAuth) {
    }
    
    facebookLogin() {
        if (this.__platform.is('cordova')) {
            this.facebookLoginCordova();
        } else {
            this.facebookLoginWeb();
        }
    }

    async facebookLoginCordova(): Promise<void> {
        try {
            FacebookLogin.login({ permissions: FACEBOOK_PERMISSIONS })
                .then((response: FacebookLoginResponse) => {
                    let authCredential =  firebase.auth.FacebookAuthProvider.credential(response.accessToken.token);
                    return this.__afAuth.auth.signInWithCredential(authCredential);
                });
        } catch (err) {
            alert('FaceBook Login error Cordova' + err);
        }
    }


  
    async facebookLoginWeb(): Promise<any> {
        try {
            const provider = new firebase.auth.FacebookAuthProvider();
            return await this.__afAuth.auth.signInWithPopup(provider);
        } catch (err) {
            alert('FaceBook Login error web' + err);
        }
    }

    logout() {
        this.__afAuth.auth.signOut();
    }
}