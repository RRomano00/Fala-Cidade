import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import{MatToolbarModule} from '@angular/material/toolbar';
import{FormsModule} from '@angular/forms';
import{MatButtonModule} from '@angular/material/button';
import{MatSidenavModule} from '@angular/material/sidenav';
import{MatMenuModule} from '@angular/material/menu';
import{MatIconModule} from '@angular/material/icon';
import{MatListModule} from '@angular/material/list';
import{MatTooltipModule} from '@angular/material/tooltip';
import{MatExpansionModule} from '@angular/material/expansion';
import{FontAwesomeModule} from '@fortawesome/angular-fontawesome';


@Component({
  selector: 'app-denuncia',
  imports: [
    RouterOutlet,
    MatToolbarModule,
    FormsModule,
    MatButtonModule,
    MatSidenavModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule,
    MatExpansionModule,
    FontAwesomeModule,
  ],
  templateUrl: './denuncia.component.html',
  styleUrl: './denuncia.component.css'
})
export class DenunciaComponent {

}
