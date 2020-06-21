import { NgModule } from '@angular/core';
import { MessagesPage } from './messages';
import { PipesModule } from '../../pipes/pipes.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ExploreContainerComponentModule } from 'src/app/explore-container/explore-container.module';


@NgModule({
  declarations: [
    MessagesPage
  ],
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    ExploreContainerComponentModule,
    PipesModule
   ],

})
export class MessagesPageModule {}