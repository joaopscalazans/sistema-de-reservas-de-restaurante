import { DiningTable } from "../diningtable/dining-table";
import { User } from "../user/user";

export interface Reserve{

    id:number;
    date: Date;
    user: User;
    table: DiningTable;

}