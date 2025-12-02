import { $ } from '@wdio/globals'
import Page from './page';

/**
 * sub page containing specific selectors and methods for a specific page
 */
class LoginPage extends Page {
    /**
     * define selectors using getter methods
     */
    public get inputUsername() {
        return $('#mat-input-0');
    }

    public get inputPassword() {
        return $('#mat-input-1');
    }

    public get btnSubmit() {
        return $('button[mat-raised-button][color="primary"]');
    }

    /**
     * a method to encapsule automation code to interact with the page
     * e.g. to login using username and password
     */
    public async login(username: string, password: string) {
        await this.inputUsername.setValue(username);
        await this.inputPassword.setValue(password);
        await this.btnSubmit.waitForClickable({ timeout: 5000 });
        await this.btnSubmit.click();
    }

    /**
     * overwrite specific options to adapt it to page object
     */
    public open() {
        return super.open('account/sign-in');
    }
}

export default new LoginPage();
