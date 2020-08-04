import transactionApi from '../../api/transaction';

export default {
    state: {
        categoriesStats: [],
    },

    getters: {
        categoriesStats: state => state.categoriesStats
    },

    mutations: {
        retrieveCategoriesStatsMutation(state, stats) {
            let values = [];
            let labels = [];
            let a = stats
            for (const key in a) {
                labels.push(key)
                values.push(a[key])
            }

            state.categoriesStats = [labels, values]
        },
    },

    actions: {
        async retrieveCategoriesStatsAction({ commit, state }, dto) {
            let result = await transactionApi.getGroups(dto)
            let data = await result.json()

            commit('retrieveCategoriesStatsMutation', data)
        },
    }
}
