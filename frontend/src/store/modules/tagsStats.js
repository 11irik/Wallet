import transactionApi from "../../api/transaction";

export default {
    state: {
        tagsStats: [],
    },

    getters: {
        tagsStats: state => state.tagsStats
    },

    mutations: {
        retrieveTagsStatsMutation(state, stats) {
            var values = []
            var labels = []
            let a = stats
            for (var key in a) {
                labels.push(key)
                values.push(a[key])
            }

            let b = [labels, values]
            state.tagsStats = b
        },
    },

    actions: {
        async retrieveTagsStatsAction({ commit, state }, dto) {
            const result = await transactionApi.getTags(dto)
            const data = await result.json()

            commit('retrieveTagsStatsMutation', data)
        },

    }
}