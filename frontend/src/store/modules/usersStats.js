import transactionApi from "../../api/transaction";

export default {
    state: {
        usersStats: []
    },

    getters: {
        usersStats: state => state.usersStats,
    },

    mutations: {
        retrieveUsersStatsMutation(state, stats) {
            var values = [];
            var labels = []
            let a = stats
            for (var key in a) {
                labels.push(key)
                values.push(a[key])
            }

            let b = [labels, values]
            state.usersStats = b
        }
    },

    actions: {
        async retrieveUsersStatsAction({ commit, state }, dto) {
            const result = await transactionApi.getUsers(dto)
            const data = await result.json()

            commit('retrieveUsersStatsMutation', data)
        },
    }
}