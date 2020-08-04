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
            let updateIndex = state.categories.find(item => item.id === category.id)

            Object.assign(updateIndex, category)
        },
    },

    actions: {
        async addCategoryAction({ commit, state }, category) {
            let result = await categoryApi.add(category)
            let data = await result.json()

            commit('addCategoryMutation', data)
        },

        async retrieveCategoryAction({ commit, dispatch }, account) {
            let result = await categoryApi.getAccountCategories(account)
            let data = await result.json()

            commit('retrieveCategoriesMutation', data)
        },

        async insertTagAction({ commit, state }, dto) {
            let result = await categoryApi.addItemInCategory(dto)
            let data = await result.json()

            commit('updateCategoryMutation', data)
        },

        async removeTagAction({ commit, state }, dto) {
            let result = await categoryApi.removeItemFromCategory(dto)
            let data = await result.json()

            commit('updateCategoryMutation', data)
        },
    }
}
