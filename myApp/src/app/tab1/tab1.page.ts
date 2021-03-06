import { Component } from '@angular/core';
import { GoogleLoginService } from '../services/google-login.service';
import { AngularFireAuth } from 'angularfire2/auth';
import { Observable } from 'rxjs';
import { UserDetails, UserStatus } from '../services/userdetails';
import { FaceBookLoginService } from '../services/facebook-login.service';
import { ModalController } from '@ionic/angular';
import { ModalPage } from 'src/pages/modal/modal';


@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {
  user: Observable<firebase.User>;
  userInformation: UserDetails = new UserDetails();


  constructor(private __afAuth: AngularFireAuth,
    private __modalCtrl: ModalController,
    private __googleLoginService: GoogleLoginService,
    private __faceBookLoginService: FaceBookLoginService) {

      this.user = this.__afAuth.authState;
    this.__afAuth.authState.subscribe(res => {
      if (res && res.uid) {
        this.userInformation.uid = res.uid;
        this.userInformation.email = res.email;
        this.userInformation.photoURL = res.photoURL;
        this.userInformation.name = res.displayName;
        this.userInformation.status = UserStatus.SIGNOUT;
        this.userInformation.accountType = res.providerData[0].providerId;
        if (this.userInformation.accountType === 'phone') {
          this.userInformation.name = res.phoneNumber;
        }
      }
    });
    }


    
    public  async phonelogin() {
      let modalPage = await  this.__modalCtrl.create({ component: ModalPage,
        componentProps: { message: '31616692719' },
        cssClass: '' });
        await modalPage.present();

      //let profileModal = this.__modalCtrl.create(ModalPage);
   //profileModal.present();
      //const modalPage = this.__modalCtrl.create('ModalPage', { message: 'test' });
      //modalPage.present();
    }
    
    googlePlusLogin() {
      this.__googleLoginService.googlePlusLogin();
    }
    facebookLogin() {
      this.__faceBookLoginService.facebookLogin();
    }
    signOut() {
      this.__afAuth.auth.signOut();
    }

    


}
