import Vue from 'vue'
import VueRouter from 'vue-router'

import AuthPage from '../pages/AuthPage.vue'
import ProfilePage from '../pages/ProfilePage.vue'
import AccountPage from '../pages/AccountPage.vue';
import VaultPage from '../pages/VaultPage.vue';
import StatisticsPage from '../pages/StatisticsPage.vue';
import TransactionPage from '../pages/TransactionPage.vue';

Vue.use(VueRouter)

const routes = [
    {path: '/', component: AccountPage},
    {path: '/auth', component: AuthPage},
    {path: '/profile', component: ProfilePage},
    {path: '/vaults', component: VaultPage},
    {path: '/stats', component: StatisticsPage},
    {path: '/transactions', component: TransactionPage},
    {path: '*', component: ProfilePage},
]

export default new VueRouter({
    mode: 'history',
    routes,
})
