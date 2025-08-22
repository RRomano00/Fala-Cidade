import { Routes } from '@angular/router';
import { SignInComponent } from './views/account/sign-in/sign-in.component';
import { SignUpComponent } from './views/account/sign-up/sign-up.component';
import { NotFoundComponent } from './views/not-found/not-found.component';
import { MainComponent } from './views/app/main/main.component';
import { MyProfileComponent } from './views/account/my-profile/my-profile.component';
import { CreateDenunciaComponent } from './views/denuncias/create-denuncia/create-denuncia.component';
import { ListarDenunciaComponent } from './views/denuncias/listar-denuncia/listar-denuncia.component';
import { HelpComponent } from './views/app/help/help.component';
import { DetailDenunciaComponent } from './views/denuncias/detail-denuncia/detail-denuncia.component';


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
        path: 'help',
        component: HelpComponent
      },
      {
        path: 'create-denuncia',
        component: CreateDenunciaComponent
      },
      {
        path: 'listar-denuncia',
        component: ListarDenunciaComponent
      },
      {
        path: 'listar-denuncia/:id',
        component: DetailDenunciaComponent
      },
      {
        path: '**',
        component: NotFoundComponent
      },
    ],

  },
];
