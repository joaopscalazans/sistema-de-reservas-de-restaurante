import { DiningTableStatus } from "./dining-table-status";


export interface DiningTableRequest {
    name:string;
    capacity:number;
    status?:DiningTableStatus;
}
