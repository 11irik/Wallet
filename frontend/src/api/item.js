import Vue from 'vue'

const items = Vue.resource('/item{/id}')

export default {
    add: itemDto => items.save({}, itemDto),
    update: item => items.update({id: item.id}, item),
    remove: id => items.remove({id}),
    getTransactionItems: transaction => Vue.http.post('/item/transaction', item)
}