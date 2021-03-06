import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouteReuseStrategy } from '@angular/router';
import { AngularFireModule } from 'angularfire2';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireDatabaseModule, AngularFireDatabase } from 'angularfire2/database';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import { GooglePlus } from '@ionic-native/google-plus/ngx';
import { PipesModule } from '../pipes/pipes.module';


import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { ImageCropperModule } from 'ngx-image-cropper';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { environment } from 'src/environments/environment';
import { FaceBookLoginService } from './services/facebook-login.service';
import { ModalPageModule } from 'src/pages/modal/modal.module';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    PipesModule,
    IonicModule.forRoot(),
    AgmCoreModule.forRoot({ apiKey: 'AIzaSyCxjWoBYpJNCtd1wGvNk3n8nEgR4ryvqA8' , libraries: ["places"]}),
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    AngularFirestoreModule,
    AppRoutingModule,
    ModalPageModule,
    ImageCropperModule

  ],
  providers: [
    StatusBar,
    SplashScreen,
    GooglePlus,
    FaceBookLoginService,
   { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
