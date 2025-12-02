import { $ } from '@wdio/globals'
import Page from './page';

/**
 * sub page containing specific selectors and methods for a specific page
 */
class SecurePage extends Page {
    /**
     * define selectors using getter methods
     */
    public get flashAlert() {
        return $('#flash');
    }

    public get toastContainer() {
        return $('.toast-container');
    }

    public get toastSuccess() {
        return $('.toast.toast-success');
    }

    public get toastMessage() {
        return $('.toast .toast-message');
    }

    public async waitForToastToExist(timeout = 5000) {
        await this.toastMessage.waitForExist({ timeout });
    }

    public async getToastText() {
        return this.toastMessage.getText();
    }
}

export default new SecurePage();
