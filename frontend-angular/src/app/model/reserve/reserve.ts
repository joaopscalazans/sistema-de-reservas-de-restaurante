import { DiningTable } from "../diningtable/dining-table";
import { User } from "../user/user";
import { ReserveStatus } from "./reserve-status";

export interface Reserve{

    id:number;
    date: Date;
    user: User;
    table: DiningTable;
    status: ReserveStatus;

}