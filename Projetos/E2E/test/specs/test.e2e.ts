import { expect } from '@wdio/globals'
import LoginPage from '../pageobjects/login.page'
import SecurePage from '../pageobjects/secure.page'

describe('My Login application', () => {
    it('should login with valid credentials', async () => {
        await LoginPage.open()

        await LoginPage.login('admin@email.com', 'admin')
        await SecurePage.waitForToastToExist(5000)
        await expect(SecurePage.toastMessage).toBeExisting()
        await expect(SecurePage.toastMessage).toHaveText(
            expect.stringContaining('Login efetuado com sucesso.'))
    })
})

