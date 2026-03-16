import { UserRole } from "./user-role";

export interface User{

    name:string;
    email:string;
    role:UserRole;
}