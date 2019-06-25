export class User {

    id: number;
    userName: string;
    password: string;
    firstName: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    reviewer: boolean;
    admin: boolean;
           
    constructor (id: number = 0, userName: string="", password: string = "", 
                firstName: string = "", lastName: string="", phoneNumber: string = "", 
                email: string = "", review: boolean = false, admin: boolean = false) {

    }
    about(): string{
        return "User: id= " + this.id +", " + "username= " + this.userName +", " 
        + "password= "+ this.password + ", " +"firstname= " + this.firstName +", " 
        + "lastname = " + this.lastName +", " + "phonenumber = "  + this.phoneNumber +", " 
        +"email = " + this.email + ", " + " is reviewer = " + this.reviewer +", " 
        + "is admin = " + this.admin;
    }        
}
