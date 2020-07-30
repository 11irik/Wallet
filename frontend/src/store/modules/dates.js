export default {
    state: {
        dates: ['2020-05-01', '2020-05-31']
    },

    getters: {
        dates: state => state.dates,
    },

    mutations: {
        setDatesMutation(state, dates) {
            state.dates = dates
        },
    },

    actions: {
        async setDatesAction({ commit, state }, data) {

            commit('setDatesMutation', data)
        },
    }
}