import { Routes } from '@angular/router';
import { SignInComponent } from './views/account/sign-in/sign-in.component';
import { SignUpComponent } from './views/account/sign-up/sign-up.component';
import { NotFoundComponent } from './views/not-found/not-found.component';
import { MainComponent } from './views/app/main/main.component';
import { MyProfileComponent } from './views/account/my-profile/my-profile.component';
import { DenunciaComponent } from './views/denuncia/denuncia.component';
import { ListarDenunciaComponent } from './views/listar-denuncia/listar-denuncia.component';


export const routes: Routes = [
  {
    path: 'account/sign-in',
    component: SignInComponent
  },
  {
    path: 'account/sign-up',
    component: SignUpComponent
  },
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: 'my-profile',
        component: MyProfileComponent
      },
      {
        path: 'denuncia',
        component: DenunciaComponent
      },
      {
        path: 'listar-denuncia',
        component: ListarDenunciaComponent
      },
      {
        path: '**',
        component: NotFoundComponent
      },
    ],

  },
];
