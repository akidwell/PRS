import { User } from './user.class';

export class PurchaseRequest {
   
	id: string;
	user: User;
	description: string;
	justification: string;
	dateNeeded: Date;
	deliveryMode: string;
	status: string;
	total: number;
	submittedDate: Date;
    reasonForRejection: string;
        
        constructor (id: number = 0, user: User= null, desc: string = "", 
                justi: string = "", dateNeeded = null, deliveryMode: string = "", status: string="",
                total: number=0, submittedDate: Date= null, reasonForRej: string = null) {

    }
    about(): string{
        return "Purchase Request: id= " + this.id +", " + "user= " + this.user.userName +", " 
        + "description= "+ this.description + ", " +"justification= " + this.justification +", " 
        + "dateNeeded = " + this.dateNeeded +", " + "deliveryMode = "  + this.deliveryMode +", " 
        +"status= " + this.status + ", " + "total= " + this.total + ", " + "submittedDate= " + 
        this.submittedDate + ", " + "reasonForRejection= " + this.reasonForRejection;
    }        
}
