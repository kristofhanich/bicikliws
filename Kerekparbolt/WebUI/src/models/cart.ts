import { BicycleResponse } from "./../services/client/bicycleService";
import { BehaviorSubject } from "rxjs";

export class Cart
{
    private _bicycles: BicycleResponse[] = [];

    private _count: BehaviorSubject<number> = new BehaviorSubject(0);
    public count$ = this._count.asObservable();

    public add = (bicycle: BicycleResponse): void =>
    {
        this._bicycles.push(bicycle);

        this._count.next(this._bicycles.length);
    }

    public remove = (bicycle: BicycleResponse): void =>
    {
        this._bicycles.remove(bicycle);
    }

    public clear = (): void =>
    {
        this._bicycles = [];
    }

    public content = (): BicycleResponse[] =>
    {
        return this._bicycles;
    }

    public sum = (): number =>
    {
        return this._bicycles.toEnum().Sum(x => x.Ar!);
    }
}