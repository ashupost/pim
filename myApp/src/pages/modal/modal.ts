import { Component, Input } from '@angular/core';
import {  NavController, NavParams, ModalController } from '@ionic/angular';
import * as firebase from 'firebase/app';
import { PhoneLoginService } from 'src/app/services/phone-login.service';

@Component({
  selector: 'page-modal',
  templateUrl: 'modal.html',
  providers: [PhoneLoginService]
})
export class ModalPage {
  //@Input() website: string;
  message: string;
  public recaptchaVerifier: firebase.auth.RecaptchaVerifier;

  constructor(public navCtrl: NavController,
    private modalController: ModalController,
    private __navCtrl: NavController,
    private _phoneLoginService: PhoneLoginService,
    public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ModalPage');
    this.message = this.navParams.get('message');
    this.recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
    
  }

  signInPhone(number: string) {
   this._phoneLoginService.signInPhone(number, this.recaptchaVerifier);
  }

  public closeModal() {
    this.modalController.dismiss();
  }
}