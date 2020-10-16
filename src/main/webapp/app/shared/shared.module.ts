import { NgModule } from '@angular/core';
import { GestionFormationSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { NgSelectModule } from '@ng-select/ng-select';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
@NgModule({
  imports: [GestionFormationSharedLibsModule, NgSelectModule, NgMultiSelectDropDownModule],
  declarations: [FindLanguageFromKeyPipe, AlertComponent, AlertErrorComponent, LoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [LoginModalComponent],
  exports: [
    GestionFormationSharedLibsModule,
    NgSelectModule,
    NgMultiSelectDropDownModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
  ],
})
export class GestionFormationSharedModule {}
