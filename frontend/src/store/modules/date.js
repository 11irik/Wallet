export default {
    state: {
        date: ['2020-05-01']
    },

    getters: {
        date: state => state.date,
    },

    mutations: {
        setDateMutation(state, date) {
            state.date = date
        },
    },

    actions: {
        async setDateAction({ commit, state }, data) {
            commit('setDateMutation', data)
        },
    }
}
