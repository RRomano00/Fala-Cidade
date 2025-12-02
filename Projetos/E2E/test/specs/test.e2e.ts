import { expect } from '@wdio/globals'
import LoginPage from '../pageobjects/login.page'
import SecurePage from '../pageobjects/secure.page'

describe('My Login application', () => {
    it('should login with valid credentials', async () => {
        await LoginPage.open();
        await LoginPage.login('admin@email.com', 'admin');

        await SecurePage.toastContainer.waitForExist({ timeout: 10000 });
        if (await SecurePage.toastMessage.isExisting()) {
            await expect(SecurePage.toastMessage).toHaveText(expect.stringContaining('Login efetuado com sucesso.'));
        }
    })


    it('should login with invalid credentials', async () => {
        await LoginPage.open();
        await LoginPage.login('a@email', 'admin');

        await SecurePage.toastContainer.waitForExist({ timeout: 10000 });
        if (await SecurePage.toastMessage.isExisting()) {
            await expect(SecurePage.toastMessage).toHaveText(expect.stringContaining('Login falhou. Tente novamente'));
        }
        console.log('teste 1 ok')

    })

})

