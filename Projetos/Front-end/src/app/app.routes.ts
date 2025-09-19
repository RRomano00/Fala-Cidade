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
import { authenticationGuard } from './services/security/guard/authentication.guard';
import { HomeComponent } from './views/app/home/home.component';
import { StatisticsComponent } from './views/denuncias/statistics/statistics.component';
import { UserAddComponent } from './views/user/user-add/user-add.component';


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
    canActivate: [authenticationGuard],
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'my-profile',
        component: MyProfileComponent
      },
      {
        path: 'help',
        component: HelpComponent
      },
      {
        path: 'denuncia',
        children: [
          {
            path: 'create',
            component: CreateDenunciaComponent
            ,
          },
          {
            path: 'list',
            component: ListarDenunciaComponent
          },
          {
            path: 'detail/:id',
            component: DetailDenunciaComponent
          },
          {
            path: 'statistics',
            component: StatisticsComponent
          }
        ],
      },
      {
        path: 'user',
        children: [
          {
            path: 'add',
            component: UserAddComponent
          }
        ]
      },
      {
        path: '**',
        component: NotFoundComponent
      },
    ],

  },
];
