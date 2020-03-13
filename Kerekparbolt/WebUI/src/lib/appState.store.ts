import { Cart } from "./../models/cart";
import { BicycleResponse } from "./../services/client/bicycleService";

export interface IAppState
{
    //readonly variableName: objectType;
    readonly cart: Cart;
    readonly selectedBicycle :BicycleResponse;
}

export const setIntitialAppState = (): IAppState =>
{
    const appState: IAppState =
    {
        cart: new Cart(),
        selectedBicycle :
        {
            Ar: 0,
            Cikkszam: "",
            FelniAtmero: "",
            FelniAtmeroID: 0,
            Id: 0,
            Marka: "",
            MarkaID: 0,
            Tipus: "",
            TipusID: 0,
            URL: "",
            Valto: "",
            ValtoTipus: 0,
            Vazmeret: "",
            VazmeretID: 0
        }
    };

    return appState;
}


export const intitialAppState: IAppState = setIntitialAppState();
