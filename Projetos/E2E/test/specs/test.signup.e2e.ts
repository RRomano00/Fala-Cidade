import { expect } from '@wdio/globals'
import SecurePage from '../pageobjects/secure.page'
import signupPage from '../pageobjects/signup.page'

describe('My SignUp application', () => {
    it('create account', async () => {
        await signupPage.open();
        await signupPage.signUp('name', 'a@email.com', 'senha', 'senha');

        await SecurePage.toastContainer.waitForExist({ timeout: 10000 });
        if (await SecurePage.toastMessage.isExisting()) {
            await expect(SecurePage.toastMessage).toHaveText(expect.stringContaining('Usuário criado com sucesso!'));
        }
    })


    it('create account', async () => {
        await signupPage.open();
        await signupPage.signUp('name', 'email@email.com', 'senha', 'senha');

        await SecurePage.toastContainer.waitForExist({ timeout: 10000 });
        if (await SecurePage.toastMessage.isExisting()) {
            await expect(SecurePage.toastMessage).toHaveText(expect.stringContaining('Erro ao criar usuário!'));
        }
    })
})

