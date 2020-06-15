import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'folder/Inbox',
    pathMatch: 'full'
  },
  {
    path: 'folder/Inbox',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
 
  {
    path: 'folder/:id',
    loadChildren: () => import('./folder/folder.module').then( m => m.FolderPageModule)
  },
  {
    path: 'test',
    loadChildren: () => import('./test/test.module').then( m => m.TestPageModule)
  }
];

const routes1: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  }
];

@NgModule({
  imports: [
   RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
   RouterModule.forRoot(routes1, { preloadingStrategy: PreloadAllModules })

  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
