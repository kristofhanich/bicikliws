export enum Routes
{
    //public
    Home = "",

    //prptected
    Products = "products",
    Details = "details",
    Cart = "cart",

    //admin
    AddProduct = "admin/product/add",
    AddShifter = "admin/shifter/add"
}

export module Urls
{
    //public
    export const home = `/${Routes.Home}`;

    //protected
    export const products = `/${Routes.Products}`;
    export const details = `/${Routes.Details}`;
    export const cart = `/${Routes.Cart}`;
    
    //admin
    export const addProduct = `/${Routes.AddProduct}`;
    export const addShifter = `/${Routes.AddShifter}`;
}