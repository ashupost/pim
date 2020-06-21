import { Component, OnInit } from '@angular/core';
import { GroundFirebaseStoreService } from '../services/ground-firebasestore.service';
import { AngularFireAuth } from 'angularfire2/auth';
import { GroundAuthService } from '../services/ground.auth.service';
import { Observable } from 'rxjs';
import { UserDetails } from '../services/userdetails';
import { MessagesPage } from 'src/pages/messages/messages';
import {  IonApp } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {
  //@ViewChild(Nav) nav: Nav;
  items: Observable<UserDetails[]>;
  myId: string = '';
  myData: Observable<UserDetails>;
  ngOnInit(): void {
    this.items = this.__groundFirebaseStoreService.getUsers();
  }
  constructor(
    private _app: IonApp,
    private navctl: NavController,
    private activatedRoute: ActivatedRoute,
    
    private __gas: GroundAuthService,
    private __afAuth: AngularFireAuth,
    private __groundFirebaseStoreService: GroundFirebaseStoreService) {
      this.__afAuth.authState.subscribe(res => {
      if (res && res.uid) {
        this.myId = res.uid;
      }
    });
  //  this.items = this.__groundFirebaseStoreService.getUsers();
  }


  ionViewDidLoad() {
    console.log('ionViewDidLoad1 Tab1Page');
    this.items = this.__groundFirebaseStoreService.getUsers();
    console.log(this.items);

  }


  goToMessagePage(toUserDetails: UserDetails) {
    let data = { user: this.__gas.currentUser, toUserDetails: toUserDetails };
   // this._app.getRootNav().push(MessagesPage, data);
   //this.navctl.setDirection;
   this.activatedRoute.snapshot.paramMap.get('id');

  }





}
