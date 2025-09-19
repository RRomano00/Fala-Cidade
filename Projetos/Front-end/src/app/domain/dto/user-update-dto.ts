export interface PasswordUpdateDto {
    id?: number
    email: string;
    oldPassword: string;
    newPassword: string;
}