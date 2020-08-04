import transactionApi from '../../api/transaction';

export default {
    state: {
        tagsStats: [],
    },

    getters: {
        tagsStats: state => state.tagsStats
    },

    mutations: {
        retrieveTagsStatsMutation(state, stats) {
            let values = []
            let labels = []
            let a = stats
            for (let key in a) {
                labels.push(key)
                values.push(a[key])
            }

            state.tagsStats = [labels, values]
        },
    },

    actions: {
        async retrieveTagsStatsAction({ commit, state }, dto) {
            let result = await transactionApi.getTags(dto)
            let data = await result.json()

            commit('retrieveTagsStatsMutation', data)
        },

    }
}
