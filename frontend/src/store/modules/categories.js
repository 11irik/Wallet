import categoryApi from '../../api/category';

export default {
    state: {
        categories: frontendData.categories,
    },

    getters: {
        categories: state => state.categories
    },

    mutations: {
        addCategoryMutation(state, category) {
            state.categories = [
                ...state.categories,
                category
            ]
        },

        retrieveCategoriesMutation(state, categories) {
            state.categories = categories
        },

        updateCategoryMutation(state, category) {
            const updateIndex = state.categories.find(item => item.id === category.id)

            Object.assign(updateIndex, category)
        },
    },

    actions: {
        async addCategoryAction({ commit, state }, category) {
            const result = await categoryApi.add(category)
            const data = await result.json()

            commit('addCategoryMutation', data)
        },

        async retrieveCategoryAction({ commit, dispatch }, account) {
            const result = await categoryApi.getAccountCategories(account)
            const data = await result.json()

            commit('retrieveCategoriesMutation', data)
        },

        async insertTagAction({ commit, state }, dto) {
            const result = await categoryApi.addItemInCategory(dto)
            const data = await result.json()

            commit('updateCategoryMutation', data)
        },

        async removeTagAction({ commit, state }, dto) {
            const result = await categoryApi.removeItemFromCategory(dto)
            const data = await result.json()

            commit('updateCategoryMutation', data)
        },
    }
}