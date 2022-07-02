import Vue from 'vue'

const accounts = Vue.resource('/account{/id}');


export default {
    add: account => accounts.save({}, account),
    update: account => accounts.update({id: account.id}, account),
    remove: id => accounts.remove({id}),
    get: id => accounts.get({id}),
    addUser: data => Vue.http.post('/account/user', data)
}
