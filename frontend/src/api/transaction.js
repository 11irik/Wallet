import Vue from 'vue'

const transactions = Vue.resource('/transaction{/id}')

export default {
    add: transaction => transactions.save({}, transaction),
    update: transaction => transactions.update({id: transaction.id}, transaction),
    remove: id => transactions.remove({id}),
    get: id => transactions.get({id}),
    getAccountTransaction: account => Vue.http.post('/transaction/account', account),
    getTransactions: dto => Vue.http.post('/transaction/request', dto),

    getGroups: dto => Vue.http.post('/transaction/group', dto),
    getTags: dto => Vue.http.post('/transaction/tag', dto),
    getDays: dto => Vue.http.post('/transaction/day', dto),
    getUsers: dto => Vue.http.post('/transaction/user', dto),


}
