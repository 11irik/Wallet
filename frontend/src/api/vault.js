import Vue from 'vue'

const vaults = Vue.resource('/vault{/id}')

export default {
    add: vault => vaults.save({}, vault),
    update: vault => vaults.update({id: vault.id}, vault),
    remove: id => vaults.remove({id}),
    getAccountVaults: account => Vue.http.post('/vault/account', account)
}
