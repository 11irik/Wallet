import Vue from 'vue'

const user = Vue.resource('/user')

export default {
    setAccount: account => user.save(account),
}
