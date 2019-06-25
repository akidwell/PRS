export class JsonResponse {
    data: object;
    errors: object;
    meta: object;
  
    constructor(Data: object, Errors: object, Meta: object) {
        this.data = Data;
        this.errors = Errors;
        this.meta = Meta;
    }
  }