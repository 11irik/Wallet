import Vue from 'vue'
import VueRouter from 'vue-router'

import Auth from '../pages/Auth.vue'
import Profile from '../pages/Profile.vue'
import Account from '../pages/Account.vue';
import VaultPage from '../pages/VaultPage.vue';
import Statistics from '../pages/Statistics.vue';
import TransactionPage from '../pages/TransactionPage.vue';

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Account},
    {path: '/auth', component: Auth},
    {path: '/profile', component: Profile},
    {path: '/vaults', component: VaultPage},
    {path: '/stats', component: Statistics},
    {path: '/transactions', component: TransactionPage},
    {path: '*', component: Profile},
]

export default new VueRouter({
    mode: 'history',
    routes,
})
