import Vue from 'vue'
import Vuex from 'vuex'

import accounts from "./modules/accounts";
import categories from "./modules/categories";
import categoriesStats from "./modules/categoriesStats";
import daysStats from "./modules/daysStats";
import defaultAccount from "./modules/defaultAccount";
import items from "./modules/items";
import profile from "./modules/profile";
import tags from "./modules/tags";
import tagsStats from "./modules/tagsStats";
import transactions from "./modules/transactions";
import transactionTags from "./modules/transactionTags";
import usersStats from "./modules/usersStats";
import vaults from "./modules/vaults";
import dates from "./modules/dates";
import selectedTransaction from "./modules/selectedTransaction";
import date from "./modules/date";

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        accounts,
        categories,
        categoriesStats,
        daysStats,
        defaultAccount,
        items,
        profile,
        tags,
        tagsStats,
        transactions,
        transactionTags,
        usersStats,
        vaults,
        dates,
        selectedTransaction,
        date
    }
})