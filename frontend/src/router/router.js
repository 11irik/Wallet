import Vue from 'vue'
import VueRouter from 'vue-router'

import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Account from 'pages/Account.vue';
import Vaults from "pages/Vaults.vue";
import Statistics from "pages/Statistics.vue";
import Transactions from "pages/Transactions.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/', component: Account },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '/vaults', component: Vaults },
    { path: '/stats', component: Statistics },
    { path: '/transactions', component: Transactions },
    { path: '*', component: Profile },
]

export default new VueRouter({
    mode: 'history',
    routes
})
