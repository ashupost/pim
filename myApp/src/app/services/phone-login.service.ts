import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';

import { AlertController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class PhoneLoginService {
  constructor(
    private alertCtrl: AlertController,
   // private __navCtrl: NavController,
    private afAuth: AngularFireAuth) {

  }


   signInPhone(phoneNumber: string, recaptchaVerifier: firebase.auth.RecaptchaVerifier) {
    alert(phoneNumber);
    var applicationVerifier = new firebase.auth.RecaptchaVerifier(
      'recaptcha-container');
  var provider = new firebase.auth.PhoneAuthProvider();
  provider.verifyPhoneNumber(phoneNumber, applicationVerifier)
      .then(function(verificationId) {
        var verificationCode = window.prompt('Please enter the verification ' +
            'code that was sent to your mobile device.');
        return firebase.auth.PhoneAuthProvider.credential(verificationId,
            verificationCode);
      })
      .then(function(phoneCredential) {
        return firebase.auth().signInWithCredential(phoneCredential);
      });
  }
}