import transactionApi from "../../api/transaction";

export default {
    state: {
        categoriesStats: [],
    },

    getters: {
        categoriesStats: state => state.categoriesStats
    },

    mutations: {
        retrieveCategoriesStatsMutation(state, stats) {
            const values = [];
            const labels = [];
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
            const result = await transactionApi.getGroups(dto)
            const data = await result.json()

            commit('retrieveCategoriesStatsMutation', data)
        },
    }
}