import transactionApi from '../../api/transaction';

export default {
    state: {
        daysStats: [],
    },

    getters: {
        daysStats: state => state.daysStats
    },

    mutations: {
        retrieveDaysStatsMutation(state, stats) {
            let values = []
            let labels = []
            let a = stats
            for (let key in a) {
                labels.push(key)
                values.push(a[key])
            }

            state.daysStats = [labels, values]
        },
    },

    actions: {
        async retrieveDaysStatsAction({ commit, state }, dto) {
            let result = await transactionApi.getDays(dto)
            let data = await result.json()

            commit('retrieveDaysStatsMutation', data)
        },
    }
}
