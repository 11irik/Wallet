import transactionApi from "../../api/transaction";

export default {
    state: {
        daysStats: [],
    },

    getters: {
        daysStats: state => state.daysStats
    },

    mutations: {
        retrieveDaysStatsMutation(state, stats) {
            var values = []
            var labels = []
            let a = stats
            for (var key in a) {
                labels.push(key)
                values.push(a[key])
            }

            let b = [labels, values]
            state.daysStats = b
        },
    },

    actions: {
        async retrieveDaysStatsAction({ commit, state }, dto) {
            const result = await transactionApi.getDays(dto)
            const data = await result.json()

            commit('retrieveDaysStatsMutation', data)
        },
    }
}