import transactionApi from '../../api/transaction';

export default {
    state: {
        usersStats: []
    },

    getters: {
        usersStats: state => state.usersStats,
    },

    mutations: {
        retrieveUsersStatsMutation(state, stats) {
            let values = [];
            let labels = []
            let a = stats
            for (let key in a) {
                labels.push(key)
                values.push(a[key])
            }

            state.usersStats = [labels, values]
        }
    },

    actions: {
        async retrieveUsersStatsAction({ commit, state }, dto) {
            let result = await transactionApi.getUsers(dto)
            let data = await result.json()

            commit('retrieveUsersStatsMutation', data)
        },
    }
}
