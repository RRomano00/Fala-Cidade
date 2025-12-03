import Page from "./page";

class SignupPage extends Page {
    /**
     * define selectors using getter methods
     */
    public get inputUsername() {
        return $('#mat-input-0');
    }

    public get inputEmail() {
        return $('#mat-input-1');
    }

    public get inputPassword() {
        return $('#mat-input-2');
    }

    public get inputRepeatPassword() {
        return $('#mat-input-3');
    }

    public get btnSubmit() {
        return $('button[mat-raised-button][color="primary"]');
    }


    public async signUp(username: string, email: string, password: string, repeatPassword: string) {
        await this.inputUsername.setValue(username);
        await this.inputEmail.setValue(email);
        await this.inputPassword.setValue(password);
        await this.inputRepeatPassword.setValue(repeatPassword); // <-- CORRETO
        await this.btnSubmit.waitForClickable({ timeout: 5000 });
        await this.btnSubmit.click();
    }

    public get inlineSignUpError() {
        return $('.text-danger.mt-2.text-center'); // or use a data-test attr
    }

    /**
     * overwrite specific options to adapt it to page object
     */
    public open() {
        return super.open('account/sign-up');
    }
}

export default new SignupPage();
