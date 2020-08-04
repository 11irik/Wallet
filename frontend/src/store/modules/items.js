import itemApi from '../../api/item';

export default {
    state: {
        items:[],
    },

    getters: {
        items: state => state.items
    },

    mutations: {
        addItemMutation(state, item) {
            state.items = [
                ...state.items,
                item
            ]
        },

        retrieveItemsMutation(state, items) {
            state.items = items
        },

    },

    actions: {
        async addItemAction({commit, state}, dto) {
            let result = await itemApi.add(dto)
            let data = await result.json()

            commit('addItemMutation', data)
        },

        async retrieveItemsAction({ commit, dispatch }, transaction) {
            let result = await itemApi.getTransactionItems(transaction)
            let data = await result.json()

            commit('retrieveItemsMutation', data)
        },
    }
}
