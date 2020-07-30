import Vue from 'vue'

const categories = Vue.resource('/category{/id}');

export default {
    add: category => categories.save({}, category),
    update: category => categories.update({id: category.id}, category),
    remove: id => categories.remove({id}),
    getAccountCategories: account => Vue.http.post('/category/account', account),
    addItemInCategory: dto => Vue.http.post('/category/tag', dto),
    removeItemFromCategory: dto => Vue.http.post('/category/tag/remove', dto)
}