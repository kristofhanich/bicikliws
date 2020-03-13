export abstract class Validation
{
    public static IsEmail = (input: string) : boolean =>
    {
        const emailRegEx:RegExp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        return emailRegEx.test(input)
    }

    

    
}

export default interface ICreditCardValidationResult
{
    IsValid: boolean;
    Type: string;
    Number?: number
    FormatedNumber?: string;
    Message: string;
}