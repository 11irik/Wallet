import Vue from 'vue'

const tags = Vue.resource('/tag{/id}')

export default {
    add: tag => tags.save({}, tag),
    update: tag => tags.update({id: tag.id}, tag),
    remove: id => tags.remove({id}),
    getAccountTags: account => Vue.http.post('/tag/account', account)

}