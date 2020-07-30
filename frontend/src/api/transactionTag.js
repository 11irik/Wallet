import Vue from 'vue'

const transactionTags = Vue.resource('/transaction/tag')

export default {
    getAll: () => transactionTags.get(),
}
